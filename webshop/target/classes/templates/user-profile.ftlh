<!DOCTYPE html>
<html>
<head>
    <title>WEBSHOP</title>
</head>
<body>
<h1>WEBSHOP</h1>
<h2>User information</h2>
<b>Phone number </b> ${user.phone} <br>
<h3>Products by   ${user.name} </h3>
<#list products as product>
<div>
    <p> <b> ${product.title}</b>${product.price} UAH | <a href="/product/${product.id}" > </p>
</div>
<#else >
<h3> Empty shop</h3>
</#list>
<#if ERROR ??>
    <h2 style="...">${ERROR}</h2>
</#if>
</body>
</html>