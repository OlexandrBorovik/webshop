<!DOCTYPE html>
<html>
<head>
    <title>WEBSHOP</title>
</head>
<body>
<h1>WEBSHOP</h1>
<hr>

<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} UAH | </p>
    </div>
<#else>
    <h3>Empty shop</h3>
</#list>
</body>
</html>