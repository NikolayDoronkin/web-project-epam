package com.epam.web.command;

import com.epam.web.entity.*;
import com.epam.web.pagination.PageHandler;
import com.epam.web.service.CartService;
import com.epam.web.service.MenuService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс команды, реализующий выбор элемента из меню и перенос его в корзину.
 */
public class SelectionCommand implements Command{

    private final CartService cartService;
    private final PageHandler pageHandler = new PageHandler();
    private final MenuService menuService;
    private final static Logger LOGGER = LogManager.getLogger(SelectionCommand.class);

    public SelectionCommand(CartService cartService, MenuService menuService) {
        this.cartService = cartService;
        this.menuService = menuService;
    }

    @Override
    public CommandResults execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, SQLException {
        LOGGER.info("Start executing.");
        int foodId = Integer.parseInt(request.getParameter("food_id"));
        User user = (User) request.getSession().getAttribute("user");
        String lang = (String) request.getAttribute("localization");
        if(user == null){
            return CommandResults.forward(Pages.LOGIN);
        }
        long userID = user.getID();

        Optional<Menu> menuOptional = menuService.selectFromMenu(foodId, lang);
        if(menuOptional.isPresent()){
            Menu menu = menuOptional.get();
            String submenu = request.getParameter("type");
            long menuID = menu.getID();

            cartService.pullIntoCart(userID, menuID);

            Optional<MenuDto> optionalMenuDto = menuService.showMenu(submenu, lang);
            if(optionalMenuDto.isPresent()){
                MenuDto menuDto = optionalMenuDto.get();
                List<Menu> menuList = menuDto.getMenus();
                request = pageHandler.updateRequest(request, menuList, "menu", "menu");
                request.setAttribute("menutype", submenu);

                LOGGER.info("Redirecting to " + Pages.MENU);
                return CommandResults.redirect(request.getRequestURI() + "?command=menu&type=" + submenu);
            }
        }
        request.setAttribute("errorMessage", "Incorrect user name or password");
        LOGGER.info("Redirecting to " + Pages.HOME);
        return CommandResults.forward(Pages.HOME);

    }
}
