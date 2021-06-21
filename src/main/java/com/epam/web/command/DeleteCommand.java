package com.epam.web.command;

import com.epam.web.entity.CartDto;
import com.epam.web.entity.User;
import com.epam.web.service.CartService;
import com.epam.web.service.MenuService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, реализующий удаление элемента из корзины заказов.
 */
public class DeleteCommand implements Command{

    private final CartService cartService;
    private final static Logger LOGGER = LogManager.getLogger(DeleteCommand.class);

    public DeleteCommand(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");
        long foodId = Long.parseLong(request.getParameter("food_id"));

        User user = (User) request.getSession().getAttribute("user");
        long userID = user.getID();
        String lang = (String) request.getAttribute("localization");

        cartService.deleteElements(userID, foodId);
        Optional<CartDto> optionalCartDto = cartService.getAllCart(userID, lang);
        if(optionalCartDto.isPresent()){
            CartDto cartDto = optionalCartDto.get();
            request.setAttribute("cart", cartDto.getCartList());
            request.setAttribute("total", cartDto.calculate());
            CartCommand cartCommand = new CartCommand(cartService);
            return cartCommand.execute(request, response);
        }
        LOGGER.info("Forwarding to " + Pages.CART);
        request.setAttribute("isEmpty", true);
        return CommandResults.forward(Pages.CART);
    }
}
