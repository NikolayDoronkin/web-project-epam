package com.epam.web.command;

import com.epam.web.entity.Menu;
import com.epam.web.entity.MenuDto;
import com.epam.web.pagination.PageHandler;
import com.epam.web.service.MenuService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, реализующий удаление элемента из меню.
 */
public class DeleteFromMenuCommand implements Command{

    private final MenuService menuService;
    private final PageHandler pageHandler = new PageHandler();
    private final static Logger LOGGER = LogManager.getLogger(DeleteFromMenuCommand.class);

    public DeleteFromMenuCommand(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Start executing.");
        long menuID = Long.parseLong(request.getParameter("menuid"));
        String menuType = request.getParameter("type");
        String lang = (String) request.getAttribute("localization");

        Optional<MenuDto> optionalMenuDto = menuService.deleteFromMenu(menuID, menuType, lang);

        if(optionalMenuDto.isPresent()){
            MenuDto menuDto = optionalMenuDto.get();
            List<Menu> menu = menuDto.getMenus();

            request = pageHandler.updateRequest(request, menu, "menu", "menu");
            request.setAttribute("menutype", menuType);

            request.setAttribute("list", menuType);

            LOGGER.info("Redirecting to " + Pages.MENU);
            return CommandResults.redirect(request.getRequestURI() + "?command=menu&type=" + menuType);
        }
        LOGGER.info("Redirecting to" + Pages.LOGIN);
        return CommandResults.redirect(Pages.LOGIN);
    }
}
