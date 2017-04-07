<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet" media="screen" />
<link href="<c:url value="/resources/css/font-awesome.css"/>"
	rel="stylesheet" media="screen" />
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet"
	media="screen" />
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href=""> Application - Computer Database
			</a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">

<!-- 					<h1> -->
<%-- 						<c:when test="${computerForm['new']}"> --%>
<!-- 							<h1> -->
<%-- 								<spring:message code="addComputer.title" /> --%>
<!-- 							</h1> -->
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<!-- 							<h1> -->
<%-- 								<spring:message code="addComputer.title" /> --%>
<!-- 							</h1> -->
<%-- 						</c:otherwise> --%>
						
<%-- 						<spring:message code="addComputer.title" /> --%>
<!-- 					</h1> -->

					<form:form modelAttribute="computer" name="computer" method="POST">
						<fieldset>

							<spring:bind path="name">
								<div class="form-group">
									<label for="computerName"><spring:message
											code="addComputer.computerName" /></label>
									<spring:message code="addComputer.computerNamePlaceHolder"
										var="computerNamePlaceHolder" />
									<form:input path="name" type="text" name="computerName"
										class="form-control" id="computerName"
										placeholder="${computerNamePlaceHolder}" />
								</div>
								<c:if test="${status.error}">
									<div class="alert alert-danger fade in">
										<form:errors path="name" />
									</div>
								</c:if>
							</spring:bind>

							<spring:bind path="introduced">

								<div class="form-group">
									<label for="introduced"><spring:message
											code="addComputer.introduced" /></label>
									<spring:message code="addComputer.datePlaceHolder"
										var="datePlaceHolder" />
									<form:input path="introduced" name="introduced"
										class="form-control" id="introduced"
										placeholder="${datePlaceHolder}" />

								</div>
								<c:if test="${status.error}">
									<div class="alert alert-danger fade in">
										<form:errors path="introduced" />
									</div>
								</c:if>
							</spring:bind>

							<spring:bind path="discontinued">
								<div class="form-group">
									<label for="discontinued"><spring:message
											code="addComputer.discontinued" /></label>
									<form:input path="discontinued" name="discontinued"
										class="form-control" id="discontinued"
										placeholder="${datePlaceHolder}" />
									<c:if test="${status.error}">
										<div class="alert alert-danger fade in">
											<form:errors path="discontinued" />
										</div>
									</c:if>
								</div>
							</spring:bind>

							<spring:bind path="company.id">
								<div class="form-group">
									<spring:message code="addComputer.companySelect"
										var="companySelect" />
									<form:select path="company.id">
										<form:option value="0" label="${companySelect}" />
										<form:options items="${companies}" itemValue="id"
											itemLabel="name" />
									</form:select>
									<c:if test="${status.error}">
										<div class="alert alert-danger fade in">
											<form:errors path="discontinued" />
										</div>
									</c:if>
								</div>
							</spring:bind>

						</fieldset>
						<div class="actions pull-right">
							<input type="submit"
								value="<spring:message code="addComputer.add" />"
								class="btn btn-primary">
							<spring:message code="addComputer.or" />
							<a href="database" class="btn btn-default"><spring:message
									code="addComputer.cancel" /></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>

	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<%--     <script src="<c:url value="/resources/js/addComputer.js"/>"></script> --%>

</body>
</html>