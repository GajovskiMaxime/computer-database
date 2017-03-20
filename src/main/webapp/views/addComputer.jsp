<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="computerDetailManager" scope="session" class="com.excilys.mgajovski.computer_database.managers.ComputerDetailManager">
    <jsp:setProperty name="computerDetailManager" property="*"/>
</jsp:useBean>
<jsp:useBean id="computerListManager" scope="session" class="com.excilys.mgajovski.computer_database.managers.ComputerListManager">
    <jsp:setProperty name="computerListManager" property="*"/>
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

<c:choose>
    
    <c:when test="${computerDetailManager.computerFieldsAreValid() == 'success'}">
        ${computerDetailManager.addComputer()}
        ${computerListManager.updateElements()}
       <c:redirect url="dashboard.jsp"/>
    </c:when>
    
    <c:otherwise>
        <c:redirect url="addComputerView.jsp">
            <c:param value="${computerDetailManager.computerFieldsAreValid()}" name="errorMessage"></c:param>
        </c:redirect>
    </c:otherwise>
    
</c:choose>
