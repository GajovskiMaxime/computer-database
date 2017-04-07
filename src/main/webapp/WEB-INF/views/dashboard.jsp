<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="main.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="resources/css/main.css" rel="stylesheet" media="screen">

    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/dashboard.js"/>"></script>
    <script
        src="<c:url value="/resources/js/jquery.twbsPagination.min.js"/>"></script>
    <script>

$('#pagination-demo').twbsPagination({
    initiateStartPageClick: false,
    startPage: ${currentPage} + 1,
    totalPages: ${maxPage},
    visiblePages: 7,
    onPageClick: function (event, page) {
        window.location.href = "?currentPage=" + (page - 1);
    }
});

</script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard.jsp"><spring:message
					code="main.title" /></a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				${requestSize}
				<spring:message code="dashboard.computerfound" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="" method="GET" class="form-inline">
						<input type="search" id="searchbox" name="filter"
							class="form-control" placeholder="<spring:message code="dashboard.filterByNamePlaceHolder" />" value="${filter}" />
						<input type="submit" id="searchsubmit" value="<spring:message code="dashboard.filterByName" />"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="./computers/add">
						<spring:message code="dashboard.addComputer" />
					</a> 
					<a class="btn btn-default" id="editComputer"
						onclick="$.fn.toggleEditMode();"><spring:message
							code="dashboard.editComputer" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="./computers/delete" method="POST">
			<input type="hidden" name="idsToDelete" >
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

						<th><spring:message code="dashboard.computerName" /></th>
						<th><spring:message code="dashboard.introduced" /></th>
						<th><spring:message code="dashboard.discontinued" /></th>
						<th><spring:message code="dashboard.company" /></th>
					</tr>
				</thead>

				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach items="${computerList}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="./computers/${computer.id}/edit"
                            onclick="">${computer.name}</a></td>
							<td><c:out value="${computer.introduced}" /></td>
							<td><c:out value="${computer.discontinued}" /></td>
							<td><c:out value="${computer.company.name}" /></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>
	<div id="error" class='error' style='display: none'>${errorMessage}</div>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul id="pagination-demo" class="pagination"></ul>
			<div class="btn-group btn-group-sm pull-right" role="group">
				<a class="btn btn-default " href="?elementsByPage=10">10</a> <a
					class="btn btn-default " href="?elementsByPage=50">50</a> <a
					class="btn btn-default " href="?elementsByPage=100">100</a>
			</div>
		</div>
	</footer>

</body>
</html>