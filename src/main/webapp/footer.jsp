<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<footer class="footer">
    <div class="wrapper">
        <div class="footer_item">
            <div class="footer_logo">
                <img src="./resources/logo_white-blue.png" alt="Title" class="footer_logo_pic">
            </div>
            <div class="footer_menus">
                <nav class="footer_nav">
                    <ul class="footer_menu">
                        <li class="footer_menu_item">
                            <h3 class="footer_label_menu_item">
                                <fmt:message key="local.footer.label.home"/>
                            </h3>
                        </li>
                        <li class="footer_menu_item">
                            <a href="#!" class="footer_menu_item_link">
                                <fmt:message key="local.footer.home.item.first"/>
                            </a>
                        </li>
                        <li class="footer_menu_item">
                            <a href="./index.jsp" class="footer_menu_item_link">
                                <fmt:message key="local.footer.home.item.second"/>
                            </a>
                        </li>
                    </ul>
                </nav>
                <nav class="footer_nav">
                    <ul class="footer_menu">
                        <li class="footer_menu_item">
                            <h3 class="footer_label_menu_item">
                                <fmt:message key="local.footer.label.order"/>
                            </h3>
                        </li>
                        <li class="footer_menu_item">
                            <a href="#!" class="footer_menu_item_link">
                                <fmt:message key="local.footer.order.item.first"/>
                            </a>
                        </li>
                        <li class="footer_menu_item">
                            <a href="#!" class="footer_menu_item_link">
                                <fmt:message key="local.footer.order.item.second"/>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</footer>