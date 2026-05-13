<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="layout/header.jsp" %>
        <h3>${title}</h3>
        <form action="/webapp-headers/login" method="POST">
            <div>
                <input class="form-control" name="username" id="username" type="text">
                <label class="form-label" for="username">Username</label>
            </div>
            <div>
                <input class="form-control" name="password" id="password" type="password">
                <label class="form-label" for="password">Password</label>
            </div>
            <div>
                <button class="btn btn-primary" type="submit">Login</button>
            </div>
        </form>
<%@include file="layout/footer.jsp" %>