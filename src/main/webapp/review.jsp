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
                        <fmt:message key="local.review.label"/>${currentTarget}
                    </h1>
                </div>
                <div class="profile">
                    <div class="editor">
                        <input action="${pageContext.request.contextPath}/controller" class="menu_editor" style="border: none; margin-left: 400px; margin-right: 500px">
                            <textarea name="comment" class="index_input" placeholder=<fmt:message key="local.review.comment"/> maxlength="250" cols="150px" rows="5px" style="resize: none"></textarea>
                            <label>
                                <select class="payment_menu" name="mark" style="padding-bottom: 20px;">
                                    <option selected disabled><fmt:message key="local.review.mark"/></option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                            </label>
                            <input type="hidden" name="command" value="review">
                            <input type="hidden" name="currentTarget" value="${currentTarget}">
                            <input type="submit" class="accept_button" value=<fmt:message key="local.review.send"/>>
                        </form>
                        <c:if test="${isInvalid != null}">
                            <h2 style="color: red; font-size: 42px; padding-top: 50px">
                                <fmt:message key="local.review.invalid"/>
                            </h2>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<c:import url="footer.jsp" charEncoding="utf-8"/>
</body>
</html>