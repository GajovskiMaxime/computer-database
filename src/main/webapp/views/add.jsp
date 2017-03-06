<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="computerDetailManager" scope="session" class="com.excilys.mgajovski.computer_database.managers.ComputerDetailManager">
    <jsp:setProperty name="computerDetailManager" property="*"/>
</jsp:useBean>

<c:set      target="${computerDetailManager}" 
            property="computerName" 
            value="${param.computerName}"/>

<c:set      target="${computerDetailManager}" 
            property="introduced" 
            value="${param.introduced}"/>
            
<c:set      target="${computerDetailManager}" 
            property="discontinued" 
            value="${param.discontinued}"/>

<c:set      target="${computerDetailManager}" 
            property="companyId" 
            value="${param.companySelect}"/>

<%-- <c:set      target="${computerDetailManager}"  --%>
<%--             property="companyId"  --%>
<%--             value="${param.company.id}"/> --%>

<c:choose>
    <c:when test="${computerDetailManager.computerFieldsAreValid()}">
        ${computerDetailManager.addComputer()}    
    </c:when>
    
    <c:otherwise>
        
    </c:otherwise>
</c:choose>
<c:redirect url="dashboard.jsp"/>