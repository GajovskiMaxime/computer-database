<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<jsp:useBean id="computerManager" scope="page"
             class="com.excilys.mgajovski.computer_database.managers.ComputerManager">
    <jsp:setProperty name="computerManager" property="*"/>
</jsp:useBean>

<%-- <c:choose> --%>
<%--     <c:when test="${pageContext.request.method=='GET'}"> --%>
<%--         <c:set var="idx" value="name" /> --%>
<%--     <c:redirect url="${pageContext.request.requestURL}?currentPage=1&numberOfRows=50"></c:redirect> --%>
<%--         <c:set property="${param.size}" value="10"/> --%>
<%--         <c:set property="${param.page}" value="1"/> --%>

<%--         <c:if test="${param.size != null}"> --%>
<%--             <c:set property="size" target="${computerManager}" value="${param.size}"/> --%>
<%--         </c:if> --%>
        
<%--         <c:if test="${param.page != null}"> --%>
<%--             <c:set property="size" target="${computerManager}" value="${param.size}"/> --%>
<%--         </c:if> --%>
<%--     </c:when> --%>
<%-- </c:choose> --%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../css/font-awesome.css" rel="stylesheet" media="screen">
<link href="../css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.jsp"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
            
                 ${computerManager.numberOfComputersFromRequest} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" onname="sequence" class="form-control" placeholder="Search name" value="${param.sequence}"/>
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer.html">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        
                        <th>Computer name</th>
                        <th>Introduced date</th>
                        <th>Discontinued date</th>
                        <th>Company</th>
                    </tr>
                </thead>
                
                <!-- Browse attribute computers -->
                <tbody id="results">
                    <c:forEach items="${computerManager.displayedComputers}" var="computer">
	                <tr>
<!-- 	                    <td class="editMode"> -->
<%-- 	                       <input type="checkbox" name="cb" class="cb" value="${computer.id}"></td> --%>
	                    <td>
	                        <jsp:element name="a">
	                            <jsp:attribute name="href">editComputer.html?id=${computer.id}</jsp:attribute>
	                            <jsp:body>
	                                <c:out value="${computer.name}"/>
	                            </jsp:body>
	                        </jsp:element>
	                    </td>
	                    <td><c:out value="${computer.introducedDate}"/></td>
	                    <td><c:out value="${computer.discontinuedDate}"/></td>
	                    <td><c:out value="${computer.company.name}"/></td>
	                </tr>
            </c:forEach>
                 
                    
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
                <c:forEach begin="1" end="10" varStatus="number">
                    <li>
                    <a href="?currentPage=${number.count}&numberOfRows=${param.numberOfRows}">${number.count}</a>
<%--                     <c:out value="number ${no.count}"/> --%>
                    </li>
                </c:forEach>
<%--               <li><a href="?currentPage=1&numberOfRows=${param.numberOfRows}">1</a></li> --%>
<%--               <li><a href="?currentPage=2&numberOfRows=${param.numberOfRows}">2</a></li> --%>
<%--               <li><a href="?currentPage=3&numberOfRows=${param.numberOfRows}">3</a></li> --%>
<%--               <li><a href="?currentPage=4&numberOfRows=${param.numberOfRows}">4</a></li> --%>
<%--               <li><a href="?currentPage=5&numberOfRows=${param.numberOfRows}">5</a></li> --%>
              <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>

        <div class="btn-group btn-group-sm pull-right" role="group" >
            <a type="button" class="btn btn-default ${param.numberOfRows== 10 ? 'active' : ''}"href="?currentPage=${param.currentPage}&numberOfRows=10">10</a>
            <a type="button" class="btn btn-default ${param.numberOfRows == 50 ? 'active' : ''}" href="?currentPage=${param.currentPage}&numberOfRows=50">50</a>
            <a type="button" class="btn btn-default ${param.numberOfRows == 100 ? 'active' : ''}"   href="?currentPage=${param.currentPage}&numberOfRows=100">100</a>
        </div>
    </div>

    </footer>
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/dashboard.js"></script>

</body>
</html>