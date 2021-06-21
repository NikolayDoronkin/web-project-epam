<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<header class="header">
    <div class="wrapper">
        <div class="header_wrapper">
            <div class="header_logo">
                <a href="${pageContext.request.contextPath}/controller?command=main" class="header_logo_link">
                    <img src="./resources/logo_white-blue.png" alt="Title" class="header_logo_pic">
                </a>
                <div class="header_lang">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="change">
                        <input class="header_link" type="submit" name="lang" value="en"
                               style="border: none; background: none">
                    </form>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="change">
                        <input class="header_link" type="submit" name="lang" value="ru"
                               style="border: none; background: none">
                    </form>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="change">
                        <input class="header_link" type="submit" name="lang" value="es"
                               style="border: none; background: none">
                    </form>
                </div>
            </div>

            <nav class="header_nav">
                <ul class="header_menu_list">
                    <li class="header_item">
                        <a class="header_link" href="${index_for_main}">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="main">
                                <input class="header_link" type="submit"
                                       value="<fmt:message key="local.header.menu.home"/>"
                                       style="border: none; background: none">
                            </form>
                        </a>

                    </li>
                    <li class="header_item">
                        <a href="${index_for_contact}" class="header_link"></a>
                        <a class="header_link">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="contact">
                                <input class="header_link" type="submit"
                                       value="<fmt:message key="local.header.menu.contact"/>"
                                       style="border: none; background: none">
                            </form>
                        </a>
                    </li>
                    <c:choose>
                    <c:when test="${user.userName=='admin'}">
                    <li class="header_item">
                        <a href="${pageContext.request.contextPath}/controller?command=show_users" class="header_link">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="show_users">
                                <input type="hidden" name="type" value="0">
                                <input class="header_link" type="submit" name="food"
                                       value="<fmt:message key="local.header.users"/>"
                                       style="border: none; background: none">
                            </form>
                        </a>
                    </li>
                    <li class="header_item">
                        <a href="${pageContext.request.contextPath}/controller?command=show_targets"
                           class="header_link">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="show_targets">
                                <input type="hidden" name="type" value="0">
                                <input class="header_link" type="submit" name="food"
                                       value="<fmt:message key="local.header.targets"/>"
                                       style="border: none; background: none">
                            </form>
                        </a>
                    </li>
                    </c:when>
                    </c:choose>
                    <li class="header_item_sub" style="margin-right: 150px;">
                        <a href="#!" class="header_link"><fmt:message key="local.header.menu.menu"/></a>
                        <ul class="header_submenu">
                            <li class="header_submenu_li">
                                <a href="${pageContext.request.contextPath}/controller?command=menu"
                                   class="header_link">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="menu">
                                        <input type="hidden" name="type" value="MAIN_COURSE">
                                        <input class="header_link" type="submit" name="food"
                                               value="<fmt:message key="local.menu.main"/>"
                                               style="border: none; background: none">
                                    </form>
                                </a>
                            </li>
                            <li class="header_submenu_li">
                                <a href="${pageContext.request.contextPath}/controller?command=menu"
                                   class="header_link">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="menu">
                                        <input type="hidden" name="type" value="GARNISH">
                                        <input class="header_link" type="submit" name="food"
                                               value="<fmt:message key="local.menu.garnish"/>"
                                               style="border: none; background: none">
                                    </form>
                                </a>
                            </li>
                            <li class="header_submenu_li">
                                <a href="${pageContext.request.contextPath}/controller?command=menu"
                                   class="header_link">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="menu">
                                        <input type="hidden" name="type" value="DESERT">
                                        <input class="header_link" type="submit" name="food"
                                               value="<fmt:message key="local.menu.desert"/>"
                                               style="border: none; background: none">
                                    </form>
                                </a>
                            </li>
                            <li class="header_submenu_li">
                                <a href="${pageContext.request.contextPath}/controller?command=menu"
                                   class="header_link">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="menu">
                                        <input type="hidden" name="type" value="DRINK">
                                        <input class="header_link" type="submit" name="food"
                                               value="<fmt:message key="local.menu.drink"/>"
                                               style="border: none; background: none">
                                    </form>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <c:choose>
                    <c:when test="${user.userName!='admin'}">
                    <li class="header_item">
                        <a href="${pageContext.request.contextPath}/controller?command=cart" class="header_link">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="cart">
                                <input class="header_link" type="submit"
                                       value="<fmt:message key="local.header.menu.cart"/>"
                                       style="border: none; background: none">
                            </form>
                        </a>
                    </li>
                    </c:when>
                    </c:choose>
                    <li class="header_item_sub">
                        <c:choose>
                        <c:when test="${user.userName != null}">
                        <a href="#!" class="header_link"><fmt:message key="local.header.hello"/>, ${user.userName}</a>
                        <ul class="header_submenu">
                            <c:choose>
                                <c:when test="${user.userName != 'admin'}">
                                    <li class="header_submenu_li">
                                        <a href="${pageContext.request.contextPath}/controller?command=profile"
                                           class="header_link">
                                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                                <input type="hidden" name="command" value="profile">
                                                <input type="hidden" name="type" value="1">
                                                <input class="header_link" type="submit"
                                                       value="<fmt:message key="local.header.menu.profile"/>"
                                                       style="border: none; background: none">
                                            </form>
                                        </a>
                                    </li>
                                </c:when>
                            </c:choose>
                            <li class="header_submenu_li">
                                <a href="#!" class="header_link">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="logout">
                                        <input class="header_link" type="submit"
                                               value="<fmt:message key="local.header.menu.logout"/>"
                                               style="border: none; background: none">
                                    </form>
                                </a>
                            </li>

                            </c:when>
                            <c:otherwise>
                            <a href="./index.jsp" class="header_link"><fmt:message key="local.header.menu.entry"/></a>
                            <ul class="header_submenu">
                                </c:otherwise>
                                </c:choose>

                            </ul>
                        </ul>
            </nav>
        </div>

    </div>
</header>