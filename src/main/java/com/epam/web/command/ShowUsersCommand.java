package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.entity.UsersDto;

import com.epam.web.entity.enums.UserType;
import com.epam.web.pagination.PageHandler;

import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * Класс команды, отображающий всех пользователей.
 */
public class ShowUsersCommand implements Command{

    private final UserService userService;
    private final PageHandler pageHandler = new PageHandler();
    private final static Logger LOGGER = LogManager.getLogger(ShowUsersCommand.class);

    public ShowUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");

        User user = (User) request.getSession().getAttribute("user");

        if(user == null || (user.getUserType() != UserType.ADMIN)){
            LOGGER.info("Redirecting to " + Pages.HOME);
            return CommandResults.redirect(request.getRequestURI() + "?command=" + "main");
        }

        Optional<UsersDto> optionalUsersDto = userService.getUsers();
        if(optionalUsersDto.isPresent()){
            UsersDto usersDto = optionalUsersDto.get();
            List<User> users = usersDto.getUsers();

            request = pageHandler.updateRequest(request, users, "users", "show_users");

            boolean ERROR = (boolean)request.getAttribute("ERROR");

            if(ERROR){
                return CommandResults.forward(Pages.ERROR_NOT_FOUND);
            }

            request.setAttribute("list", users);

            LOGGER.info("Forwarding to " + Pages.USERS);
            return CommandResults.forward(Pages.USERS);
        }
        LOGGER.info("Forwarding to " + Pages.LOGIN);
        return CommandResults.forward(Pages.LOGIN);
    }
}
