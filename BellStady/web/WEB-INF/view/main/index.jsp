<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">My stady project</h1>
                    <ol class="breadcrumb">
                        <li class="active">Query list</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <div class="list-group">
                        <button type="button" onclick="Registration()" class="list-group-item">New registration</button>
                        <button type="button" onclick="SendActivationCode()" class="list-group-item">Get activation code</button>
                        <button type="button" onclick="CheckAuthentication()" class="list-group-item">Check authentication</button>
                        <button type="button" onclick="FilterOrganizations()" class="list-group-item">Finding an organizations by filter</button>
                        <button type="button" onclick="GetOrganizationById()" class="list-group-item">Get organization by ID</button>
                        <button type="button" onclick="UpdateOrganization()" class="list-group-item">Update organization</button>
                        <button type="button" onclick="SaveOrganization()" class="list-group-item">Save organization</button>
                        <button type="button" onclick="FilterOffices()" class="list-group-item">Finding an offices by filter</button>
                        <button type="button" onclick="DeleteOfficeById()" class="list-group-item">Delete office by ID</button>
                        <button type="button" onclick="UpdateOffice()" class="list-group-item">Update office</button>
                        <button type="button" onclick="SaveOffice()" class="list-group-item">Save office</button>
                        <button type="button" onclick="FilterEmployees()" class="list-group-item">Finding an employees by filter</button>
                        <button type="button" onclick="DeleteEmployeeById()" class="list-group-item">Delete employee by ID</button>
                        <button type="button" onclick="UpdateEmployee()" class="list-group-item">Update employee</button>
                        <button type="button" onclick="SaveEmployee()" class="list-group-item">Save employee</button>
                        <button type="button" onclick="AllDocs()" class="list-group-item">All docs</button>
                        <button type="button" onclick="AllCountries()" class="list-group-item">All countries</button>
                    </div>
                </div>
                <div class="col-md-9">
                    <pre id = 'result'></pre>
                </div>
            </div>
            <hr>
        </div>
    </jsp:body>
</page:template>

