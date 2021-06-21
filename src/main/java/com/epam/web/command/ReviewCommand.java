package com.epam.web.command;

import com.epam.web.entity.User;

import com.epam.web.service.ReviewService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;

import com.epam.web.validate.ReviewValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, реализующий отзыв пользователя.
 */
public class ReviewCommand implements Command{

    private final ReviewService reviewService;
    private final static Logger LOGGER = LogManager.getLogger(ReviewCommand.class);

    public ReviewCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException  {
        LOGGER.info("Start executing.");
        String targetId = request.getParameter("currentTarget");
        String mark = request.getParameter("mark");
        String comment = request.getParameter("comment");

        User user = (User) request.getSession().getAttribute("user");

        if(user == null){
            LOGGER.info("Redirecting to " + Pages.HOME);
            return CommandResults.redirect(request.getRequestURI() + "?command=" + "main");
        }

        if(!ReviewValidator.validateNumber(targetId) || !ReviewValidator.validateDigit(mark) || !ReviewValidator.validateText(comment)){
            request.setAttribute("isInvalid", true);
            request.setAttribute("currentTarget", targetId);
            LOGGER.info("Input data isn't valid. Redirect to " + Pages.REVIEW);
            return CommandResults.forward(Pages.REVIEW);
        }

        reviewService.makeReview(Integer.parseInt(targetId), Integer.parseInt(mark), comment);

        LOGGER.info("Redirecting to " + Pages.PROFILE);
        return CommandResults.redirect(request.getRequestURI() + "?command=profile");

    }
}
