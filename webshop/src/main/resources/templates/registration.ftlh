<!DOCTYPE html>
<html>
<head>
    <title>WEBSHOP</title>
</head>
<body>
<h1>WEBSHOP Registration</h1><hr>

<form action="/registration" method="post">
    <p style="text-align:center"> Name: <input type="text" name="name"><br><br></p>
    <p style="text-align:center"> Email: <input type="email" name="email"><br><br></p>
    <p style="text-align:center">   Phone number: <input type="text" name="phone"><br><br></p>
    <p style="text-align:center">  Password: <input type="password" name="password"><br><br></p>
    <p style="text-align:center">  <select name="select"> <!--Supplement an id here instead of using 'name'-->
        <option value="seller">Seller</option>
        <option value="buyer" selected>Buyer</option>
    </select>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Registration"/></p>
</form>
<#if ERROR??>
    <h2 style="color:darkred">${ERROR}</h2>
</#if>
</body>
</html>