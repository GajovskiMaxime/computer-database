<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="computerListManager" scope="session" class="com.excilys.mgajovski.computer_database.managers.ComputerListManager">
    <jsp:setProperty name="computerListManager" property="*"/>
</jsp:useBean>

<c:set      target="${computerListManager}" 
            property="filter" 
            value="${param.filter}"/>
<c:out      value="${computerListManager.updateElements()}"/>
            
<c:redirect url="dashboard.jsp"/>
