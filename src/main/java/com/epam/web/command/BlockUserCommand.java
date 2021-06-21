package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.entity.UsersDto;
import com.epam.web.pagination.Page;
import com.epam.web.pagination.PageHandler;
import com.epam.web.pagination.PageResult;
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
 * Класс команды, отвечающая за блокировку пользователей.
 */
public class BlockUserCommand implements Command{

    private final static Logger LOGGER = LogManager.getLogger(BlockUserCommand.class);
    private final UserService userService;
    private final PageHandler pageHandler = new PageHandler();

    public BlockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        LOGGER.info("Start executing.");
        String userName = request.getParameter("user_name");

        Optional<UsersDto> optionalUsersDto = userService.blockUser(userName);
        if(optionalUsersDto.isPresent()){
            UsersDto usersDto = optionalUsersDto.get();
            List<User> users = usersDto.getUsers();
            request = pageHandler.updateRequest(request, users, "users", "show_users");

            request.setAttribute("list", users);

            LOGGER.info("Redirect to " + "show_users.jsp");
            return CommandResults.redirect(request.getRequestURI() + "?command=show_users");
        }
        LOGGER.info("Redirect to " + "mainPage.jsp");
        return CommandResults.redirect(request.getRequestURI() + "?command=" + "main");
    }
}
