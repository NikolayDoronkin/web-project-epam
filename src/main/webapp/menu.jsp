<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="intro_fragment.jsp" charEncoding="utf-8"/>
<html>
<c:import url="head.jsp" charEncoding="utf-8"/>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<main class="main">
    <section class="intro_menu">
        <div class="wrapper">
            <div class="menu_cont">
                <div class="menu_label">
                    <h2 class="menu_label_text">
                        <fmt:message key="local.menu"/>
                    </h2>
                </div>
                <div class="menu_box">
                    <div class="submenu">
                        <c:forEach var="elem" items="${menu}" begin="${begin}" end="${end}">
                            <div class="element-menu_box">
                                <p class="element_text">
                                    <c:out value="${ elem.foodName }"/>
                                </p>
                                <div class="buy_cont" style="padding-right: 250px;">
                                    <p class="element_text">
                                        <c:out value="${ elem.foodPrice }"/>$
                                    </p>
                                    <c:choose>
                                        <c:when test="${user.userName!='admin'}">
                                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                                <input type="hidden" name="command" value="selection">
                                                <input type="hidden" name="current" value="${current}">
                                                <input type="hidden" name="type" value="${menutype}">
                                                <button type="submit" class="menu_add" formaction="./controller"
                                                        value="${ elem.ID }" name="food_id">
                                                    <img src="resources/add-shopping-cart.png" alt="">
                                                </button>
                                            </form>
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${user.userName=='admin'}">
                                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                                <input type="hidden" name="command" value="delete_menu">
                                                <input type="hidden" name="menuid" value="${elem.ID}">
                                                <input type="hidden" name="type" value="${menutype}">
                                                <button type="submit" class="menu_add" formaction="./controller"
                                                        value="${ elem.foodName }" name="food">
                                                    <img src="resources/delete.png" alt="">
                                                </button>
                                            </form>
                                        </c:when>
                                    </c:choose>

                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <c:import url="pagination.jsp" charEncoding="utf-8"/>
                <c:choose>
                    <c:when test="${user.userName=='admin'}">
                        <div class="editor">
                            <form action="./controller" method="post" class="menu_editor">
                                <p class="editor_label">
                                    Edit information
                                </p>
                                <input type="hidden" name="command" value="add_menu">
                                <input type="hidden" name="menuid" value="${elem.ID}">
                                <input type="hidden" name="current" value="${current}">
                                <input type="hidden" name="menutype" value="${menutype}">

                                <input class="index_input" type="text" name="foodName" placeholder="Food">
                                <input class="index_input" type="text" name="price" placeholder="Price">
                                <input class="index_input_sub" type="submit" value="Save">
                            </form>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </section>
</main>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>