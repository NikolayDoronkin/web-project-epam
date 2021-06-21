package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.service.UserService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, реализующие аутентификацию пользователя.
 */
public class LoginCommand implements Command {

    private final UserService userService;
    private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        LOGGER.info("Entering into application.");
        Optional<User> optionalUser = userService.login(userName, password);

        if (optionalUser.isPresent()) {
            request.getSession().setAttribute("user", optionalUser.get());
            LOGGER.info("Redirecting to " + Pages.HOME);
            return CommandResults.redirect(request.getRequestURI() + "?command=" + "main");
        }
        request.setAttribute("message", "Incorrect user name or password");
        LOGGER.info("Forwarding to " + Pages.LOGIN);
        return CommandResults.forward(Pages.LOGIN);
    }
}
