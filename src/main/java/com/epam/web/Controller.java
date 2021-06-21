package com.epam.web;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.service.ServiceException;
import com.epam.web.command.CommandResults;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;


public class Controller extends HttpServlet {

    private final CommandFactory commandFactory = new CommandFactory();
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (IllegalAccessException e) {    
            LOGGER.fatal(e);
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (IllegalAccessException e) {
            LOGGER.fatal(e);
            e.printStackTrace();
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException {
        String commandType = request.getParameter("command");
        Command command = commandFactory.create(commandType);
        try {
            CommandResults view = command.execute(request, response);
            String page = view.getPage();
            boolean redirect = view.isRedirect();
            if(redirect){
                response.sendRedirect(page);
            }else{
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (SQLException | ServiceException | ParseException exception) {
            throw new ServletException(exception);
        }


    }

}
