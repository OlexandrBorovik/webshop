<!DOCTYPE html>
<html>
<head>
    <title>    Diplom    </title>
</head>
<body>
<#if user.email??>
   <form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="logout">
   </form>
    <#if user.isAdmin()>
        <a href="/admin"> Admin page</a>
    </#if>
    <#else >
        Hello | <a href="/login">LogIn</a></h1><hr>
</#if>
<form action="/" method="get">
    Search product: <input type="text" name="title"><br>
    <input type="submit" value="Search">
</form>
<#list products as product>
    <div>
        <p><b>${product.title}</b>${product.price} UAH | <a href="/product/${product.id}">More information...</a> </p>
    </div>
    <#else >
    <h3>Empty</h3>
</#list>
<#if user.email??>
<hr>
<h2> Add product</h2>
<form action="/product/create" metod="post" enctype="multipart/form-data">
    Title of product: <input type="text", name="title"/><br><br>
    Description of product: <input type="text", name="description"/><br><br>
    Price of product: <input type="number", name="price"/><br><br>
    Seller of product: <input type="text", name="seller"/><br><br>
    Photo first: <input type="file", name="fileOne"/><br><br>
    Photo second : <input type="file", name="fileTwo"/><br><br>
    Photo third : <input type="file", name="fileThree"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="ADD">
</form>
</#if>
</body>
</html>