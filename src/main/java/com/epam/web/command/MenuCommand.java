package com.epam.web.command;

import com.epam.web.entity.Menu;
import com.epam.web.entity.MenuDto;
import com.epam.web.pagination.PageHandler;
import com.epam.web.service.MenuService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, загружающий меню по категориям.
 */
public class MenuCommand implements Command{

    private final MenuService menuService;
    private final PageHandler pageHandler = new PageHandler();
    private final static Logger LOGGER = LogManager.getLogger(MenuCommand.class);

    public MenuCommand(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");

        String submenu = request.getParameter("type");
        String lang = (String) request.getAttribute("localization");

        Optional<MenuDto> optionalMenuDto = menuService.showMenu(submenu, lang);
        if (optionalMenuDto.isPresent()) {

            MenuDto menu = optionalMenuDto.get();
            List<Menu> menuList = menu.getMenus();

            LOGGER.info("Updating request.");
            request = pageHandler.updateRequest(request, menuList, "menu", "menu");

            request.setAttribute("menutype", submenu);

            request.setAttribute("list", submenu);

            LOGGER.info("Forwarding to " + Pages.MENU);
            return CommandResults.forward(Pages.MENU);
        }
        request.setAttribute("badCredentials", true);
        request.setAttribute("errorMessage", "Incorrect user name or password");
        LOGGER.info("Redirecting to mainPage.jsp.");
        return CommandResults.redirect(request.getRequestURI() + "?command=main");
    }
}
