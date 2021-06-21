<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="intro_fragment.jsp" charEncoding="utf-8" />
<html>
<c:import url="head.jsp" charEncoding="utf-8" />
<body>
<header class="header_in" style="position: absolute">
    <div class="index_header">
        <div class="index_wrapper">
            <div class="index_header_wrapper">
                <img src="./resources/logo_white-blue.png" alt="Title" class="index_header_img">
            </div>
        </div>
    </div>
</header>
<main class="index_main">
    <section class="index_main_login">
        <div class="index_wrapper">
            <div class="index_main_content">
                <div class="index-main_box">
                    <div class="index-box-label">
                        <h2 class="index_label">
                            <fmt:message key="local.log_in.label"/>
                        </h2>
                    </div>
                    <div class="index-box-login">
                        <form action="${pageContext.request.contextPath}/controller" method="post" class="index_form">
                            <input type="hidden" name="command" value="login"/>
                            <input class="index_input" placeholder="<fmt:message key="local.username"/>" type="text" name="userName"/><br/>
                            <input class="index_input" placeholder="<fmt:message key="local.password"/>" type="password" name="password"/><br/>
                            <input class="index_input_sub" type="submit" value="<fmt:message key="local.entry.button"/>"/><br/>
                        </form>
                        <div class="index_content_links">
                            <div class="err_box">
                                <c:if test="${message != null}">
                                    <h3 style="font-size: 25px; color: red">${message}</h3>
                                </c:if>
                            </div>
                            <div class="index_links">
                                <div class="index_link_cont">
                                    <form action="./controller" method="post">
                                        <input type="hidden" name="command" value="reset">
                                        <a href="./resetting.jsp" class="index_link"><fmt:message key="local.reset"/></a>
                                    </form>
                                </div>
                                <div class="index_link_cont">
                                    <form action="./controller" method="post">
                                        <input type="hidden" name="command" value="registration">
                                        <a href="./sign_in.jsp" class="index_link"><fmt:message key="local.sign_in.label"/></a>
                                    </form>

                                </div>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </section>
</main>
</body>
</html>
