<!DOCTYPE html>
<html>
<head>
    <title>WEBSHOP</title>
</head>
<body>
<h1>WEBSHOP</h1><hr>
<form action="/login" method="post">
    <p style="text-align:center">   Email: <input type="email" name="username"><br><br></p>
    <p style="text-align:center">  Password: <input type="password" name="password"><br><br></p>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <p style="text-align:center">  <input type="submit" value="LogIn"/></p>
</form>
<p style="text-align:center"><a href="/registration">ZaRegistration</a></p>
</body>
</html>