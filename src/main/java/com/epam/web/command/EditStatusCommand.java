package com.epam.web.command;

import com.epam.web.entity.Target;
import com.epam.web.entity.TargetDto;
import com.epam.web.pagination.PageHandler;
import com.epam.web.pagination.PageResult;
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
 * Класс команды, реализующий изменение балов у Пользователя.
 */
public class EditStatusCommand implements Command {

    private final TargetService targetService;
    private final UserService userService;
    private final PageHandler pageHandler = new PageHandler();
    private final static Logger LOGGER = LogManager.getLogger(EditStatusCommand.class);

    public EditStatusCommand(TargetService targetService, UserService userService) {
        this.targetService = targetService;
        this.userService = userService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");
        String targetID = request.getParameter("target_id");
        String statusType = request.getParameter("status");

        Optional<TargetDto> optionalTargetDto = targetService.editStatusOfTarget(Long.parseLong(targetID), statusType);

        if(optionalTargetDto.isPresent()){
            LOGGER.info("Updating scores for users.");
            userService.updateScoresForUsers();
            //targetService.refresh();

            TargetDto targetDto = optionalTargetDto.get();
            List<Target> targets = targetDto.getTargets();

            LOGGER.info("Updating request.");
            request = pageHandler.updateRequest(request, targets, "targets", "show_targets");

            request.setAttribute("list", targets);

            LOGGER.info("Redirecting to targets.jsp.");
            return CommandResults.redirect(request.getRequestURI() + "?command=show_targets");

        }
        LOGGER.info("Redirecting to " + Pages.LOGIN);
        return CommandResults.redirect(Pages.LOGIN);


    }
}
