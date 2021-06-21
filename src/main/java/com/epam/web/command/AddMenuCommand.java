package com.epam.web.command;

import com.epam.web.entity.Menu;
import com.epam.web.entity.MenuDto;

import com.epam.web.pagination.PageHandler;

import com.epam.web.service.MenuService;
import com.epam.web.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * Класс Команды, отвечающая за добавление новых элементов в меню.
 */
public class AddMenuCommand implements Command {

    private final MenuService menuService;
    private final PageHandler pageHandler = new PageHandler();
    private final static Logger LOGGER = LogManager.getLogger(AddMenuCommand.class);

    public AddMenuCommand(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LOGGER.info("Starting execute.");

        String menuType = request.getParameter("menutype");

        byte[] arr = request.getParameter("foodName").getBytes(StandardCharsets.ISO_8859_1);

        //String food = request.getParameter("foodName").getBytes(StandardCharsets.UTF_8);
        String food = new String(arr, StandardCharsets.UTF_8);
        String price = request.getParameter("price");
        String lang = (String) request.getAttribute("localization");

        Optional<MenuDto> optionalMenuDto = menuService.saveMenu(menuType, food, Long.parseLong(price), lang);

        if(optionalMenuDto.isPresent()){
            MenuDto menuDto = optionalMenuDto.get();
            List<Menu> menu = menuDto.getMenus();

            request = pageHandler.updateRequest(request, menu, "menu", "menu");
            request.setAttribute("menutype", menuType);

            request.setAttribute("list", menuType);

            LOGGER.info("Redirect to menu with type = " + menuType);
            return CommandResults.redirect(request.getRequestURI() + "?command=menu&type=" + menuType);
        }
        LOGGER.info("Forwarding to " + Pages.LOGIN);
        return CommandResults.forward(Pages.LOGIN);
    }
}
