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
 * Класс команды, реализующий изменение пароля Пользователя.
 */
public class ResetPasswordCommand implements Command {

    private final UserService userService;
    private final static Logger LOGGER = LogManager.getLogger(ResetPasswordCommand.class);

    public ResetPasswordCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");
        String userName = request.getParameter("userName");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String repeatPassword = request.getParameter("repeatPassword");

        Optional<User> optionalUser = userService.login(userName, oldPassword);

        if(!optionalUser.isPresent()){
            LOGGER.info("Forwarding to " + Pages.RESET_PASSWORD);
            request.setAttribute("message", "This user not exists. Please try again");
            return CommandResults.forward(Pages.RESET_PASSWORD);
        }

        if(newPassword.equals(repeatPassword) || !UserValidator.isValid(newPassword)){
            LOGGER.info("Forwarding to " + Pages.RESET_PASSWORD);
            request.setAttribute("message", "Incorrect data. Please try again.");
            return CommandResults.forward(Pages.RESET_PASSWORD);
        }

        userService.resetPassword(userName, newPassword);
        request.setAttribute("message", "Password was changed successfully!");
        LOGGER.info("Forwarding to " + Pages.LOGIN);
        return CommandResults.forward(Pages.LOGIN);

    }

}
