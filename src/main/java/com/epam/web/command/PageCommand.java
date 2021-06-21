package com.epam.web.command;

import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, отображающий страницу.
 */
public class PageCommand implements Command{

    private final String page;
    private final static Logger LOGGER = LogManager.getLogger(PageCommand.class);

    public PageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");
        LOGGER.info("Forwarding to " + page);
        return CommandResults.forward(page);
    }
}
