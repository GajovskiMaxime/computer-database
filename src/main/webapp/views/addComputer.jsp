<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../css/font-awesome.css" rel="stylesheet" media="screen">
<link href="../css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form id="addComputerForm" action="add.jsp" method="POST">
                        <fieldset>
                            
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" name="computerName" class="form-control" id="computerName" placeholder="Computer name" >
                            </div>
                            
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" id="introduced" name="introduced" class="form-control" placeholder="Introduced date">
                            </div>
                            
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" id="discontinued" name="discontinued" class="form-control" placeholder="Discontinued date">
                            </div>
                            
                            
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" >
<%--                                         <c:forEach  var="i" begin="${(computerListManager.currentPage - 2 > 0) ? (computerListManager.currentPage - 2) : 1}"  --%>
<%--                                 end="${(computerListManager.currentPage + 2 < computerListManager.maxPage) ? (computerListManager.currentPage + 2) : computerListManager.maxPage + 1}"> --%>
<%--                           <button name="currentPageButton" type="submit" value="${i - 1}" class="btn btn-default ${computerListManager.currentPage == i - 1? 'active' : ''}">${i}</button> --%>
<%--                   </c:forEach> --%>
                                    <option value="0">--</option>
                                </select>
                            </div>
                                              
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="dashboard.html" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>