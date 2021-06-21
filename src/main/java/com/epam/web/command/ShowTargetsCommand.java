package com.epam.web.command;

import com.epam.web.entity.Target;
import com.epam.web.entity.TargetDto;
import com.epam.web.entity.User;
import com.epam.web.entity.enums.UserType;
import com.epam.web.pagination.PageHandler;
import com.epam.web.service.ServiceException;
import com.epam.web.service.TargetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, отображающий все заказы Пользователей.
 */
public class ShowTargetsCommand implements Command{

    private final TargetService targetService;
    private final PageHandler pageHandler = new PageHandler();
    private final static Logger LOGGER = LogManager.getLogger(ShowTargetsCommand.class);

    public ShowTargetsCommand(TargetService targetService) {
        this.targetService = targetService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");

        User user = (User) request.getSession().getAttribute("user");

        if(user == null || (user.getUserType() != UserType.ADMIN)){
            LOGGER.info("Redirecting to " + Pages.HOME);
            return CommandResults.redirect(request.getRequestURI() + "?command=" + "main");
        }

        Optional<TargetDto> targetDtoOptional = targetService.findAllTargets();

        if(targetDtoOptional.isPresent()){
            TargetDto targetDto = targetDtoOptional.get();
            List<Target> targets = targetDto.getTargets();

            request = pageHandler.updateRequest(request, targets, "targets", "show_targets");

            request.setAttribute("list", targets);

            LOGGER.info("Forwarding to " + Pages.TARGETS);
            return CommandResults.forward(Pages.TARGETS);
        }
        LOGGER.info("Forwarding to " + Pages.LOGIN);
        return CommandResults.forward(Pages.LOGIN);
    }
}
