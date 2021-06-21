<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="pagination">
    <div>
        <h2 style="font-size: 30px">
            Page ${curPage} from ${allPages}
        </h2>
    </div>
    <div class="pagination_link" style="display: flex; ">
        <c:choose>
            <c:when test="${curPage > 1}">
                <div>
                    <a href="">
                        <form action="./controller" method="post" class="pagination_link">
                            <input type="hidden" name="command" value="${command}">
                            <input type="hidden" name="type" value="${list}">
                            <input type="hidden" name="current" value="<c:out value="${curPage - 1}"/>">
                            <input class="pagination_link" type="submit" name="nextPage"
                                   value="<<Previous"
                                   style="border: none; background: none; margin-right: 0">
                        </form>
                    </a>
                </div>
            </c:when>
        </c:choose>
        <c:forEach var="element" items="${pagination}">
            <a href="" class="pagination_link">
                <form action="./controller" method="post" class="pagination_link"  style="margin-right: 0">
                    <input type="hidden" name="command" value="${command}">
                    <input type="hidden" name="type" value="${list}">
                    <c:choose>
                        <c:when test="${element == curPage}">
                            <input class="pagination_link" type="submit" name="current"
                                   value="<c:out value="${element}"/>"
                                   style="border: none; background: none; color: forestgreen; margin-right: 0">
                        </c:when>
                        <c:otherwise>
                            <input class="pagination_link" type="submit" name="current"
                                   value="<c:out value="${element}"/>"
                                   style="border: none; background: none; margin-right: 0">
                        </c:otherwise>
                    </c:choose>
                </form>
            </a>
        </c:forEach>
        <c:choose>
            <c:when test="${curPage < allPages}">
                <div>
                    <a href="">
                        <form action="./controller" method="post" class="pagination_link">
                            <input type="hidden" name="command" value="${command}">
                            <input type="hidden" name="type" value="${list}">
                            <input type="hidden" name="current" value="<c:out value="${(curPage % allPages) + 1}"/>">
                            <input class="pagination_link" type="submit" name="nextPage"
                                   value="Next>>"
                                   style="border: none; background: none; margin-right: 0">
                        </form>
                    </a>
                </div>
            </c:when>
        </c:choose>
    </div>
</div>