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
                <div class="profile_label">
                    <h1 class="profile_label_text">
                        <fmt:message key="local.profile.label"/>
                    </h1>
                </div>
                <div class="profile">
                    <div class="profile_info_tag">
                        <h3 class="profile_info_text">
                            <fmt:message key="local.profile.name"/>
                        </h3>
                        <h3 class="profile_info_text">
                            <fmt:message key="local.profile.role"/>
                        </h3>
                        <h3 class="profile_info_text">
                            <fmt:message key="local.profile.score"/>
                        </h3>
                    </div>
                    <div class="profile_info">
                        <h3 class="profile_info_text">
                            <c:out value="${user_info.userName}"/>
                        </h3>
                        <h3 class="profile_info_text">
                            <c:out value="${user_info.userType}"/>
                        </h3>
                        <h3 class="profile_info_text">
                            <c:out value="${user_info.privilegeScores}"/>
                        </h3>
                    </div>
                </div>
                <div class="profile_targets">
                    <div class="profile_label">
                        <h1 class="profile_label_text">
                            <fmt:message key="local.profile.targets"/>
                        </h1>
                    </div>
                    <div class="profile_targets_info">
                        <c:choose>
                            <c:when test="${isEmpty != null}">
                                <div class="target_box">
                                    <h1 style="font-size: 35px;">
                                        You haven't current targets! Make target from CART.
                                    </h1>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="profile_targets_info_label">
                                    <h2 class="profile_targets_info_label_text">
                                        <fmt:message key="local.profile.taget.id"/>
                                    </h2>
                                    <h2 class="profile_targets_info_label_text">
                                        <fmt:message key="local.profile.target.cost"/>
                                    </h2>
                                    <h2 class="profile_targets_info_label_text">
                                        <fmt:message key="local.profile.target.time"/>
                                    </h2>
                                    <h2 class="profile_targets_info_label_text">
                                        <fmt:message key="local.profile.target.status"/>
                                    </h2>
                                    <h2 class="profile_targets_info_label_text">
                                        <fmt:message key="local.profile.target.payment"/>
                                    </h2>
                                </div>
                                <c:forEach var="elem" items="${target_info}" begin="${begin}" end="${end}">
                                    <div class="profile_targets_info_label">
                                        <h2 class="profile_targets_info_label_text">
                                            <c:out value="${elem.ID}"/>
                                        </h2>
                                        <h2 class="profile_targets_info_label_text">
                                            <c:out value="${elem.cost}"/>
                                        </h2>
                                        <h2 class="profile_targets_info_label_text">
                                            <c:out value="${elem.time}"/>
                                        </h2>
                                        <h2 class="profile_targets_info_label_text">
                                            <c:out value="${elem.status}"/>
                                        </h2>
                                        <h2 class="profile_targets_info_label_text">
                                            <c:out value="${elem.payment}"/>
                                        </h2>
                                        <c:if test="${elem.status == 'PAID'}">
                                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                                <input type="hidden" name="command" value="make_review">
                                                <input type="hidden" name="targetId" value="${elem.ID}">
                                                <button type="submit" class="menu_add" formaction="./controller"
                                                        value="" name=food>
                                                    <img src="resources/chat--v1.png" alt="">
                                                </button>
                                            </form>
                                        </c:if>
                                    </div>
                                </c:forEach>
                                <c:import url="pagination.jsp" charEncoding="utf-8"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>