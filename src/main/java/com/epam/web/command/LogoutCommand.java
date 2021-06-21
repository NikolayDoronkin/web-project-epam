package com.epam.web.command;

import com.epam.web.service.ServiceException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, реализующий выход Пользователя из аккаунта.
 */
public class LogoutCommand implements Command{

    private final static Logger LOGGER = LogManager.getLogger(LogoutCommand.class);

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, SQLException, ParseException {
        LOGGER.info("Start executing.");
        request.getSession().invalidate();
        LOGGER.info("Forwarding to " + Pages.HOME);
        return CommandResults.forward(Pages.HOME);
    }
}
