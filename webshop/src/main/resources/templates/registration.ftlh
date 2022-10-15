<!DOCTYPE html>
<html>
<head>
    <title>WEBSHOP</title>
</head>
<body>
<h1>WEBSHOP</h1><hr>
<h4>Registration</h4>
<form action="/registration" method="post">
    Name: <input type="text" name="name"><br><br>
    Email: <input type="email" name="email"><br><br>
    Phone number: <input type="text" name="phone"><br><br>
   Password: <input type="password" name="password"><br><br>
    <select name="select"> <!--Supplement an id here instead of using 'name'-->
        <option value="s">Seller</option>
        <option value="b" selected>Buyer</option>
    </select>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Registration"/>
</form>
<#if ERROR??>
    <h2 style="color:darkred">${ERROR}</h2>
</#if>
</body>
</html>