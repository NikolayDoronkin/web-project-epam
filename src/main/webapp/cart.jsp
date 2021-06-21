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
                        <fmt:message key="local.cart.label"/>
                    </h2>
                </div>
                <div class="target_box" style="justify-content: center">
                    <c:choose>
                        <c:when test="${isEmpty != null}">
                            <h1 style="font-size: 35px">
                                <fmt:message key="local.cart.empty"/>
                            </h1>
                        </c:when>
                        <c:otherwise>
                            <div class="menu_box">
                                <c:forEach var="elem" items="${cart}" varStatus="status">
                                    <div class="element-menu_box">
                                        <p class="element_text_lab">
                                            <c:out value="${ elem.foodName }"/>
                                        </p>
                                        <div class="buy_cont">
                                            <p class="element_text" style="font-size: 35px;">
                                                <c:out value="${ elem.foodPrice }"/>$
                                            </p>
                                            <form action="./controller" method="post">
                                                <input type="hidden" name="command" value="delete">
                                                <button type="submit" class="menu_add" formaction="./controller"
                                                        value="${ elem.ID }" name="food_id">
                                                    <img src="resources/delete.png" alt="">
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="setbox">
                                <div class="res_box">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <div>
                                            <h2 class="total_label_text" name="total" style="
                                                        font-size: 42px;
                                                        padding-bottom: 20px;">
                                                <fmt:message key="local.cart.total"/>: ${total}$
                                            </h2>
                                        </div>
                                        <label for="appt-time" style="font-size: 20px;">
                                            <fmt:message key="local.cart.alert"/> </label>
                                        <br/>
                                        <br/>
                                        <br/>
                                        <input id="appt-time" type="time" name="time"
                                               min="10:00" max="18:59">
                                        <span class="validity"></span>
                                        <label>
                                            <select class="payment_menu" name="payment">
                                                <option selected disabled><fmt:message
                                                        key="local.cart.payment"/></option>
                                                <option value="BY_CARD"><fmt:message
                                                        key="local.cart.payment.first"/></option>
                                                <option value="CASH"><fmt:message
                                                        key="local.cart.payment.second"/></option>
                                                <option value="APPLE_PAY"><fmt:message
                                                        key="local.cart.payment.third"/></option>
                                            </select>
                                        </label>
                                        <input type="hidden" name="command" value="target">
                                        <input class="accept_button" type="submit"
                                               value="<fmt:message key="local.cart.accept"/>">
                                    </form>
                                    <div>
                                        <h2 style="font-size: 42px; color:red">
                                                ${errorMessage}
                                        </h2>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>
</main>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>