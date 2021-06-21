package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.entity.UsersDto;
import com.epam.web.pagination.PageHandler;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, реализующий изменение балов у Пользователя.
 */
public class EditScoresCommand implements Command{

    private final UserService userService;
    private final PageHandler pageHandler = new PageHandler();
    private final static Logger LOGGER = LogManager.getLogger(EditScoresCommand.class);

    public EditScoresCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");
        String userName = request.getParameter("user_name");
        String scores = request.getParameter("score");

        Optional<UsersDto> optionalUsersDto = userService.editScores(userName, Long.parseLong(scores));

        if(optionalUsersDto.isPresent()){
            UsersDto usersDto = optionalUsersDto.get();
            List<User> users = usersDto.getUsers();

            request = pageHandler.updateRequest(request, users, "users", "show_users");

            request.setAttribute("list", users);

            LOGGER.info("Redirecting to users.jsp.");
            return CommandResults.redirect(request.getRequestURI() + "?command=show_users");
        }
        LOGGER.info("Redirecting to users.jsp.");
        return CommandResults.redirect(request.getRequestURI() + "?command=" + "show_users");
    }
}
