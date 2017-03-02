<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:useBean id="computerListManager" scope="session"
	class="com.excilys.mgajovski.computer_database.managers.ComputerListManager">
	<jsp:setProperty name="computerListManager" property="*" />
</jsp:useBean>

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
			<a class="navbar-brand" href="dashboard.jsp"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">

				${computerListManager.numberOfElementsFromFilteredRequest} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="updateFilter.jsp" method="POST"
						class="form-inline">

						<input type="search" id="searchbox" name="filter" class="form-control" placeholder="${computerListManager.filter}" /> 
						<input type="submit" id="searchsubmit" value="Filter by name" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer.jsp">AddComputer</a> <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
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

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>

						<th>Computer name</th>
						<th>Introduced date</th>
						<th>Discontinued date</th>
						<th>Company</th>
					</tr>
				</thead>

				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computerListManager.elements}"
						var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><jsp:element name="a">
	                            <jsp:attribute name="href">editComputer.html?id=${computer.id}</jsp:attribute>
	                            <jsp:body>
	                                <c:out value="${computer.name}" />
	                            </jsp:body>
	                        </jsp:element></td>
							<td><c:out value="${computer.introducedDate}" /></td>
							<td><c:out value="${computer.discontinuedDate}" /></td>
							<td><c:out value="${computer.company.name}" /></td>
						</tr>
					</c:forEach>


				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<div class="btn-group btn-group-sm" role="group">
			<form id="currentPageForm" action="updateCurrentPage.jsp" method="POST" class="form-inline">
					<c:if test="${computerListManager.currentPage > 0}">
						<button name="currentPageButton" type="submit" value="0" class="btn btn-default" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></button>
					</c:if>
					<c:forEach 	var="i" 
								begin="${(computerListManager.currentPage - 2 > 0) ? (computerListManager.currentPage - 2) : 1}" 
								end="${(computerListManager.currentPage + 2 < computerListManager.maxPage) ? (computerListManager.currentPage + 2) : computerListManager.maxPage + 1}">
						  <button name="currentPageButton" type="submit" value="${i - 1}" class="btn btn-default ${computerListManager.currentPage == i - 1? 'active' : ''}">${i}</button>
	              </c:forEach>
					
					<c:if test="${computerListManager.currentPage < computerListManager.maxPage - 1}">
						<button name="currentPageButton" type="submit" value="${computerListManager.maxPage}" class="btn btn-default" aria-label="Next"> <span aria-hidden="true">&raquo;</span></button>
					</c:if>

			</form>
			</div>
			<div class="btn-group btn-group-sm pull-right" role="group">
				<form id="elementsByPageForm" action="updateElementsByPage.jsp" method="POST" class="form-inline">
					<button name="elementsByPageButton" type="submit" value="10" class="btn btn-default ${computerListManager.elementsByPage == 10 ? 'active' : ''}">10</button>
					<button name="elementsByPageButton" type="submit" value="50" class="btn btn-default ${computerListManager.elementsByPage == 50 ? 'active' : ''}">50</button>
					<button name="elementsByPageButton" type="submit" value="100" class="btn btn-default ${computerListManager.elementsByPage == 100 ? 'active' : ''}">100</button>
				</form>
			</div>
		</div>

	</footer>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/dashboard.js"></script>
</body>
</html>