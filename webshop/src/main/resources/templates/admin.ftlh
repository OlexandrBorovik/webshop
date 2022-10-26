<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1>
<hr>
<h4>Administration</h4>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Phone</th>
        <th>Active</th>
        <th>Role</th>
        <th>Ban</th>
        <th>More information</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.email}</th>
            <th>${user.phone}</th>
            <th><#if user.activ>true<#else>false</#if></th>
            <th><#list user.roles as role>${role}</#list></th>
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="ban">
                </form>
            </th>
            <th><a href="/user/${user.id}">More information </a></th>
        </tr>
    </#list>
</table>
</body>
</html>