<!DOCTYPE html>
<html>
<head>
    <title>WEBSHOP</title>
</head>
<body>
<h1>WEBSHOP</h1><hr>
<h4> Full information</h4>
<#list pictures as pic>
<p style="text-align:center">   <img src="/pictures/${pic.id}" height="150px" /></p>
 </#list>
<p style="text-align:center">
<b>Title:</b>${product.title}<br>
<b>Description:</b>${product.description}<br>
<b>Price:</b>${product.price}<br>
<b>Seller:</b> <a href="/user/${product.user.id}">${product.user.name}</a>><br></p>

<#if role=="Optional[ROLE_BUYER]" >

    <form action="/product/${product.id}/add" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Add"/>
    </form>
<#else>
    <form action="/product/delete/${product.id}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Delete"/>
    </form>
</#if>

<hr>
</body>
</html>