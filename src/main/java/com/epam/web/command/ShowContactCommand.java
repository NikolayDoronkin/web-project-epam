package com.epam.web.command;

import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, отображающий контакты.
 */
public class ShowContactCommand implements Command{

    private final static Logger LOGGER = LogManager.getLogger(ShowContactCommand.class);

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");
        LOGGER.info("Redirecting to contact from " + Pages.HOME);
        return CommandResults.redirect(request.getRequestURI() + "?command=main&index_for_contact=#contact");
    }
}
