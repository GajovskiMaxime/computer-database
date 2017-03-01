<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="computerManager" scope="session" class="com.excilys.mgajovski.computer_database.managers.ComputerManager">
    <jsp:setProperty name="computerManager" property="*"/>
</jsp:useBean>
<c:out value="${param.sequence}"></c:out>
<c:out value="${computerManager.sequence}"></c:out>
<c:set target="${computerManager}" property="sequence" value="${param.sequence}"></c:set>
<c:out value="${computerManager.sequence}"></c:out>
<c:redirect url="dashboard.jsp"></c:redirect>
<%-- <c:set target="${computerManager}" property="sequence" value="${param.sequence}"></c:set> --%>
