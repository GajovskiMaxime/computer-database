<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.excilys.mgajovski.computer_database.entities.Computer" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="computerManager" scope="session" class="com.excilys.mgajovski.computer_database.managers.ComputerManager">
    <jsp:setProperty name="computerManager" property="*"/>
</jsp:useBean>

<%
// Computer.Builder computer = Computer.builder();
// computer.name(name)
%>

<c:redirect url="dashboard.jsp"></c:redirect>