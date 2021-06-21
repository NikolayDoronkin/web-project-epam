package com.epam.web.command;

import com.epam.web.entity.CartDto;
import com.epam.web.entity.TargetDto;
import com.epam.web.entity.User;
import com.epam.web.service.CartService;
import com.epam.web.service.MenuService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.TargetService;
import com.epam.web.validate.TimeValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TargetCommand implements Command{

    private final TargetService targetService;
    private final CartService cartService;
    private final TimeValidator timeValidator;
    private final static Logger LOGGER = LogManager.getLogger(TargetCommand.class);

    public TargetCommand(TargetService targetService, CartService cartService) {
        this.targetService = targetService;
        this.cartService = cartService;
        timeValidator = new TimeValidator();
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, SQLException {
        LOGGER.info("Start executing.");
        String paymentType = request.getParameter("payment");

        if(paymentType == null){
            request.setAttribute("errorMessage", "Выберите тип оплаты!");
            CartCommand cartCommand = new CartCommand(cartService);
            return cartCommand.execute(request, response);
        }

        CartDto cartDto = (CartDto) request.getSession().getAttribute("totalDto");
        long cost = cartDto.calculate();

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            LOGGER.info("Forwarding to " + Pages.LOGIN);
            return CommandResults.forward(Pages.LOGIN);
        }

        long userID = user.getID();

        String time = request.getParameter("time");
        if(timeValidator.validate(time)){
            Optional<TargetDto> targetDtoOptional = targetService.createTarget(userID, cost, time, paymentType);

            if(targetDtoOptional.isPresent()){
                cartService.refreshCartByID(userID);
                LOGGER.info("Redirecting to " + Pages.CART);
                return CommandResults.redirect(request.getRequestURI() + "?command=cart");
            }
            LOGGER.info("Redirecting to " + Pages.HOME);
            return CommandResults.redirect(request.getRequestURI() + "?command=main");
        }
        request.setAttribute("errorMessage", "Данное время недоступно, повторите попытку!");
        CartCommand cartCommand = new CartCommand(cartService);
        return cartCommand.execute(request, response);
    }
}
