package com.epam.web.command;

import com.epam.web.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс фабрики команд, который реализует нужную нам команду.
 */
public class CommandFactory {

    private final ServiceFactory serviceFactory = new ServiceFactory();
    private final static Logger LOGGER = LogManager.getLogger(CommandFactory.class);


    public Command create(String type) throws IllegalAccessException {
        switch (type) {
            case "login":
                LOGGER.info("Creating Login Command.");
                return new LoginCommand(serviceFactory.createUserService());
            case "logout":
                LOGGER.info("Creating Logout Command.");
                return new LogoutCommand();
            case "change":
                LOGGER.info("Creating Localization Command.");
                return new ChangeLocalizationCommand();
            case "menu":
                LOGGER.info("Creating Menu Command.");
                return new MenuCommand(serviceFactory.createMenuService());
            case "selection":
                LOGGER.info("Creating Selection Command.");
                return new SelectionCommand(serviceFactory.createCartService(), serviceFactory.createMenuService());
            case "cart":
                LOGGER.info("Creating Cart Command.");
                return new CartCommand(serviceFactory.createCartService());
            case "delete":
                LOGGER.info("Creating Delete Command.");
                return new DeleteCommand(serviceFactory.createCartService());
            case "target":
                LOGGER.info("Creating Target Command.");
                return new TargetCommand(serviceFactory.createTargetService(), serviceFactory.createCartService());
            case "profile":
                LOGGER.info("Creating Profile Command.");
                return new ProfileCommand(serviceFactory.createUserService(), serviceFactory.createTargetService());
            case "change_password":
                LOGGER.info("Creating Change Password Command.");
                return new ResetPasswordCommand(serviceFactory.createUserService());
            case "create_user":
                LOGGER.info("Creating Registration Command.");
                return new CreateUserCommand(serviceFactory.createUserService());
            case "show_users":
                LOGGER.info("Creating Show Users Command.");
                return new ShowUsersCommand(serviceFactory.createUserService());
            case "show_targets":
                LOGGER.info("Creating Show Targets Command.");
                return new ShowTargetsCommand(serviceFactory.createTargetService());
            case "delete_menu":
                LOGGER.info("Creating Delete Item From Menu Command.");
                return new DeleteFromMenuCommand(serviceFactory.createMenuService());
            case "add_menu":
                LOGGER.info("Creating Adding into Menu Command.");
                return new AddMenuCommand(serviceFactory.createMenuService());
            case "block":
                LOGGER.info("Creating Block User Command.");
                return new BlockUserCommand(serviceFactory.createUserService());
            case "edit_scores":
                LOGGER.info("Creating Editing Scores Command.");
                return new EditScoresCommand(serviceFactory.createUserService());
            case "edit_target":
                LOGGER.info("Creating Editing Status Target Command.");
                return new EditStatusCommand(serviceFactory.createTargetService(), serviceFactory.createUserService());
            case "review":
                LOGGER.info("Creating Review Command.");
                return new ReviewCommand(serviceFactory.createReviewService());


            case "contact":
                LOGGER.info("Showing Contact Page Command.");
                return new ShowContactCommand();
            case "main":
                LOGGER.info("Showing Home Page Command.");
                return new PageCommand(Pages.HOME);
            case "reset":
                LOGGER.info("Showing Resetting Page Command.");
                return new PageCommand(Pages.RESET_PASSWORD);
            case "registration":
                LOGGER.info("Showing Registration Page Command.");
                return new PageCommand(Pages.SIGN_IN);
            case "make_review":
                LOGGER.info("Showing Review Page Command.");
                return new ShowReviewPageCommand();

            default:
                LOGGER.error("Unknown type of command: " + type);
                return new PageCommand(Pages.ERROR_NOT_FOUND);
                //throw new IllegalAccessException("Unknown type of command " + type);
        }
    }

}
