package com.epam.web.command;

import com.epam.web.service.ServiceException;
import com.epam.web.utils.CookieHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, отвечающая за локализацию.
 */
public class ChangeLocalizationCommand implements Command {

    private static final String LOCALIZATION_COOKIE = "localization";
    private static final String LOCALIZATION_VALUE = "lang";
    private final static Logger LOGGER = LogManager.getLogger(ChangeLocalizationCommand.class);

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");
        String lang = request.getParameter(LOCALIZATION_VALUE);
        CookieHandler.setTimeLife(response, LOCALIZATION_COOKIE, lang);
        request.setAttribute(LOCALIZATION_COOKIE, lang);
        LOGGER.info("Forwarding to " + Pages.HOME);

        return CommandResults.forward(Pages.HOME);
    }
}
