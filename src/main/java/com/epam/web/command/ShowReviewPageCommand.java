package com.epam.web.command;

import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, отображающий формы
 * для заполнения отзыва.
 */
public class ShowReviewPageCommand implements Command{

    private final static Logger LOGGER = LogManager.getLogger(ShowReviewPageCommand.class);

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");
        String currentTarget = request.getParameter("targetId");
        request.setAttribute("currentTarget", currentTarget);
        LOGGER.info("Forwarding to " + Pages.REVIEW);
        return CommandResults.forward(Pages.REVIEW);
    }
}
