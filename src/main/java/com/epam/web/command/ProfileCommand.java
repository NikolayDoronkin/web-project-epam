package com.epam.web.command;

import com.epam.web.entity.Target;
import com.epam.web.entity.TargetDto;
import com.epam.web.entity.User;
import com.epam.web.pagination.PageHandler;
import com.epam.web.service.ServiceException;
import com.epam.web.service.TargetService;
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
 * Класс команды, отображающий основную информацию о
 * Пользователе.
 */
public class ProfileCommand implements Command{

    private final UserService userService;
    private final TargetService targetService;
    private final PageHandler pageHandler = new PageHandler();
    private final static Logger LOGGER = LogManager.getLogger(ProfileCommand.class);

    public ProfileCommand(UserService userService, TargetService targetService) {
        this.userService = userService;
        this.targetService = targetService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, SQLException {
        LOGGER.info("Start executing.");

        User user = (User) request.getSession().getAttribute("user");
        long userID = user.getID();

        Optional<User> optionalUser = userService.getUserInformation(userID);
        Optional<TargetDto> optionalTargetDto = targetService.findTargetByUserID(userID);

        if(optionalUser.isPresent()){
            if(optionalTargetDto.isPresent()){
                user = optionalUser.get();
                TargetDto targetDto = optionalTargetDto.get();
                List<Target> targetList = targetDto.getTargets();

                request = pageHandler.updateRequest(request, targetList, "target_info", "profile");

                request.setAttribute("user_info", user);

            }else{
                request.setAttribute("user_info", user);
                request.setAttribute("isEmpty", true);
            }
            LOGGER.info("Forwarding to " + Pages.PROFILE);
            return CommandResults.forward(Pages.PROFILE);

        }
        LOGGER.info("Forwarding to " + Pages.LOGIN);
        return CommandResults.forward(Pages.LOGIN);
    }
}
