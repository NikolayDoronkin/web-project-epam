package com.epam.web.security;

import com.epam.web.command.CommandType;
import com.epam.web.command.Pages;
import com.epam.web.entity.User;
import com.epam.web.entity.enums.UserType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AuthorizationFilter implements Filter {

    private Map<String, Set<UserType>> permission;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Map<String, Set<UserType>> permission = new HashMap<>();
        permission.put(CommandType.LOGIN, new HashSet<>(Collections.singletonList(UserType.ANONYMOUS)));
        permission.put(CommandType.LOGOUT, new HashSet<>(Arrays.asList(UserType.USER, UserType.ADMIN, UserType.ANONYMOUS)));
        permission.put(CommandType.CHANGE, new HashSet<>(Arrays.asList(UserType.USER, UserType.ADMIN, UserType.ANONYMOUS)));
        permission.put(CommandType.MENU, new HashSet<>(Arrays.asList(UserType.USER, UserType.ADMIN, UserType.ANONYMOUS)));
        permission.put(CommandType.SELECTION, new HashSet<>(Collections.singletonList(UserType.USER)));
        permission.put(CommandType.CART, new HashSet<>(Collections.singletonList(UserType.USER)));
        permission.put(CommandType.DELETE, new HashSet<>(Collections.singletonList(UserType.USER)));
        permission.put(CommandType.TARGET, new HashSet<>(Collections.singletonList(UserType.USER)));
        permission.put(CommandType.PROFILE, new HashSet<>(Collections.singletonList(UserType.USER)));
        permission.put(CommandType.CHANGE_PASSWORD, new HashSet<>(Collections.singletonList(UserType.ANONYMOUS)));
        permission.put(CommandType.CREATE_USER, new HashSet<>(Collections.singletonList(UserType.ANONYMOUS)));
        permission.put(CommandType.SHOW_USERS, new HashSet<>(Collections.singletonList(UserType.ADMIN)));
        permission.put(CommandType.SHOW_TARGETS, new HashSet<>(Collections.singletonList(UserType.ADMIN)));
        permission.put(CommandType.DELETE_MENU, new HashSet<>(Collections.singletonList(UserType.ADMIN)));
        permission.put(CommandType.ADD_MENU, new HashSet<>(Collections.singletonList(UserType.ADMIN)));
        permission.put(CommandType.BLOCK, new HashSet<>(Collections.singletonList(UserType.ADMIN)));
        permission.put(CommandType.EDIT_SCORES, new HashSet<>(Collections.singletonList(UserType.ADMIN)));
        permission.put(CommandType.EDIT_TARGET, new HashSet<>(Collections.singletonList(UserType.ADMIN)));
        permission.put(CommandType.REVIEW, new HashSet<>(Collections.singletonList(UserType.USER)));
        permission.put(CommandType.CONTACT, new HashSet<>(Arrays.asList(UserType.USER, UserType.ADMIN, UserType.ANONYMOUS)));
        permission.put(CommandType.MAIN, new HashSet<>(Arrays.asList(UserType.USER, UserType.ADMIN, UserType.ANONYMOUS)));
        permission.put(CommandType.RESET, new HashSet<>(Collections.singletonList(UserType.ANONYMOUS)));
        permission.put(CommandType.REGISTRATION, new HashSet<>(Collections.singletonList(UserType.ANONYMOUS)));
        permission.put(CommandType.MAKE_REVIEW, new HashSet<>(Arrays.asList(UserType.ADMIN, UserType.USER)));

        this.permission = Collections.unmodifiableMap(permission);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse){
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            String command = request.getParameter("command");
            User user = (User) request.getSession().getAttribute("user");
            UserType type = user != null ? user.getUserType() : UserType.ANONYMOUS;

            if(!permission.containsKey(command)){
                request.getRequestDispatcher(Pages.ERROR_NOT_FOUND).forward(request, response);
                return;
            }

            boolean authorized = permission.get(command).contains(type);

            if(!authorized){
                //response.sendRedirect(request.getRequestURI());
                request.getRequestDispatcher(Pages.ERROR_UNAUTHORIZED).forward(request, response);
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        permission = null;
    }
}
