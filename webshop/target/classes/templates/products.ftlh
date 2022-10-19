<!DOCTYPE html>
<html>
<head>
    <title>WEBSHOP</title>
</head>
<body>
<h1>WEBSHOP</h1>
<hr>
<#if user.email??>
    <h3>Your name: <i>${user.name}</i></h3>


    <#if role=="Optional[ROLE_BUYER]" >
        <form>
            <a href="/bag">Bag</a>
        </form>
    </#if>

    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="LogOut"/>
    </form>
    <#if user.isAdmin()>
        <a href="/admin">Administration</a>
    </#if>

<#else>
    <a href="/login">LogIn</a></h1>
</#if>
<hr>
<h4>Our poducts</h4>
<form action="/" method="get">
    Find by title: <input type="text" name="title">
    <input type="submit" value="Search"/>
</form>
<#if role=="Optional[ROLE_BUYER]" >
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} UAH | <a href="/product/${product.id}">More ...</a></p>
    </div>
<#else>
    <h3>Empty shop</h3>
</#list>
</#if>
<#if role=="Optional[ROLE_USER]" >
    <#list productsUser as product>
        <div>
            <p><b>${product.title}</b> ${product.price} UAH | <a href="/product/${product.id}">More ...</a></p>
        </div>
    <#else>
        <h3>Empty shop</h3>
    </#list>
<#if user.email??>
    <hr>
    <h3>Add product</h3>
    <form action="/product/create" method="post" enctype="multipart/form-data">
        Title: <input type="text" name="title"/><br><br>
        Description: <input type="text" name="description"/><br><br>
        Price: <input type="number" name="price"/><br><br>
        Photo: <input type="file" name="fileOne"/><br><br>
        Photo: <input type="file" name="fileTwo"/><br><br>
        Photo: <input type="file" name="fileThree"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Add"/>
    </form>
</#if>
</#if>
</body>
</html>