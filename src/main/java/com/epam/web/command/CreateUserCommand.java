package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.validate.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, реализующий создание Пользователя.
 */
public class CreateUserCommand implements Command{

    private final UserService userService;
    private final static Logger LOGGER = LogManager.getLogger(CreateUserCommand.class);

    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");

        Optional<User> optionalUser = userService.getUserInformation(userName);

        if(optionalUser.isPresent()){
            LOGGER.warn("Name is taken: " + userName);
            request.setAttribute("message", "This name is taken. Please choose another one.");
            LOGGER.info("Forwarding to " + Pages.SIGN_IN);
            return CommandResults.forward(Pages.SIGN_IN);
        }

        if(password.equals(repeatPassword)|| !UserValidator.isValid(userName, password)){
            LOGGER.warn("Input data is invalid.");
            request.setAttribute("message", "Invalid data. Please try again.");
            LOGGER.info("Forwarding to " + Pages.SIGN_IN);
            return CommandResults.forward(Pages.SIGN_IN);
        }

        userService.createUser(userName, password);
        request.setAttribute("message", "Registration completed successfully!");
        LOGGER.info("Forwarding to " + Pages.LOGIN);
        return CommandResults.forward(Pages.LOGIN);

    }
}
