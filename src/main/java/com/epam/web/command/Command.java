package com.epam.web.command;

import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Интерфейс команд, реализующий метод execute.
 */
public interface Command {

    CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, SQLException, ParseException;
}
