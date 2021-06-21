package com.epam.web.command;

import com.epam.web.entity.CartDto;
import com.epam.web.entity.User;
import com.epam.web.service.CartService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, отвечающая за отображение корзины заказов.
 */
public class CartCommand implements Command{

    private final CartService cartService;
    private final static Logger LOGGER = LogManager.getLogger(CartCommand.class);

    public CartCommand(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");
        User user = (User) request.getSession().getAttribute("user");
        String lang = (String) request.getAttribute("localization");
        if(user == null){
            LOGGER.info("Exception in entering into system.");
            request.setAttribute("errorMessage", "Incorrect user name or password");
            LOGGER.info("Forwarding to " + Pages.LOGIN);
            return CommandResults.forward(Pages.LOGIN);
        }

        long userID = user.getID();

        Optional<CartDto> optionalCartDto = cartService.getAllCart(userID, lang);
        if(optionalCartDto.isPresent()){
            CartDto cartDto = optionalCartDto.get();
            request.setAttribute("cart", cartDto.getCartList());
            request.setAttribute("total", cartDto.calculate());
            request.getSession().setAttribute("totalDto", cartDto);
            LOGGER.info("Forwarding to " + Pages.CART);
            return  CommandResults.forward(Pages.CART);
        }
        request.setAttribute("isEmpty", true);
        LOGGER.info("Forwarding to " + Pages.CART + ". Cart is empty.");
        return CommandResults.forward(Pages.CART);
    }
}
