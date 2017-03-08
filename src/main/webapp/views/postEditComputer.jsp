<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:useBean id="editComputerManager" scope="session"
    class="com.excilys.mgajovski.computer_database.managers.EditComputerManager">
    <jsp:setProperty name="editComputerManager" property="*" />
</jsp:useBean>

<c:choose>
    <c:when test="${editComputerManager.isAnInitializedId(param.id) == 'success'}">
        ${editComputerManager.updateComputerDTO(param.id)}
        <c:redirect url="editComputerView.jsp?id=${param.id}"/>
    </c:when>
    <c:otherwise>
        <c:redirect url="dashboard.jsp"/>
    </c:otherwise>
</c:choose>
