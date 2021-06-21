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
                        ALL TARGETS
                    </h2>
                </div>
                <div class="profile_targets_info_label">
                    <h2 class="profile_targets_info_label_text">
                        <fmt:message key="local.adminmod.targets.id"/>
                    </h2>
                    <h2 class="profile_targets_info_label_text">
                        <fmt:message key="local.adminmod.targets.cost"/>
                    </h2>

                    <h2 class="profile_targets_info_label_text">
                        <fmt:message key="local.adminmod.targets.time"/>
                    </h2>

                    <h2 class="profile_targets_info_label_text">
                        <fmt:message key="local.adminmod.targets.status"/>
                    </h2>
                    <h2 class="profile_targets_info_label_text">
                        <fmt:message key="local.adminmod.targets.payment"/>
                    </h2>
                </div>
                <c:forEach var="elem" items="${targets}" begin="${begin}" end="${end}">
                    <div class="profile_targets_info_label">
                        <h2 class="profile_targets_info_label_text_target">
                                ${ elem.ID }
                        </h2>
                        <h2 class="profile_targets_info_label_text_target">
                                ${ elem.cost }
                        </h2>
                        <h2 class="profile_targets_info_label_text_target">
                                ${ elem.time }
                        </h2>
                        <h2 class="profile_targets_info_label_text_target">
                                ${ elem.status }
                        </h2>
                        <h2 class="profile_targets_info_label_text_target">
                                ${ elem.payment }
                        </h2>
                    </div>
                </c:forEach>
                <c:import url="pagination.jsp" charEncoding="utf-8"/>
                <div class="editor">
                    <form action="${pageContext.request.contextPath}/controller" method="post" class="menu_editor">
                        <p class="editor_label">
                            Edit information
                        </p>
                        <input class="index_input" type="text" name="target_id" placeholder="Target ID">
                        <input type="hidden" name="current" value="${current}">

                        <label>
                            <select class="payment_menu" name="status" style="padding-bottom: 20px;">
                                <option selected disabled>Choose status</option>
                                <option value="PREPARATION">Preparation</option>
                                <option value="ACCEPTED">Accepted</option>
                                <option value="DECLINED">Declined</option>
                                <option value="READY">Ready</option>
                                <option value="PAID">Paid</option>
                            </select>
                        </label>
                        <input type="hidden" name="command" value="edit_target">
                        <input class="index_input_sub" type="submit" value="Save">
                    </form>
                </div>
            </div>
        </div>
        </div>
    </section>
</main>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>