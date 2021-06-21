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
                        ALL USERS
                    </h2>
                </div>
                <c:forEach var="elem" items="${users}" begin="${begin}" end="${end}">
                    <div class="users_cont">
                        <div class="profile_targets_info_label_users">
                            <h2 class="profile_targets_info_label_text_users">
                                <fmt:message key="local.adminmod.users.label.username"/>: ${ elem.userName }
                            </h2>
                            <h2 class="profile_targets_info_label_text_users" style="padding-left: 50px;">
                                <fmt:message key="local.adminmod.users.label.score"/>: ${ elem.privilegeScores }
                            </h2>
                        </div>
                        <div class="delete_form">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="block">
                                <input type="hidden" name="user_name" value="${elem.userName}">
                                <input type="hidden" name="current" value="${current}">
                                <button type="submit" class="menu_add" formaction="./controller"
                                        value="" name=food>
                                    <img src="resources/remove-user-male.png" alt="">
                                </button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
                <c:import url="pagination.jsp" charEncoding="utf-8"/>
                <c:choose>
                    <c:when test="${user.userName=='admin'}">
                        <div class="editor">
                            <form action="./controller" method="post" class="menu_editor">
                                <p class="editor_label">
                                    Edit information
                                </p>
                                <input type="hidden" name="command" value="edit_scores">
                                <input type="hidden" name="userName" value="${elem.userName}">
                                <input type="hidden" name="current" value="${current}">
                                <input type="hidden" name="menutype" value="${menutype}">

                                <input class="index_input" type="text" name="user_name" placeholder="User Name">
                                <input class="index_input" type="text" name="score" placeholder="Scores">
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