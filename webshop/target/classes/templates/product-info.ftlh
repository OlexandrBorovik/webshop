<!DOCTYPE html>
<html>
<head>
    <title>WEBSHOP</title>
</head>
<body>
<h1>WEBSHOP</h1><hr>
<h4> Full information</h4>
<#list pictures as pic>
    <img src="/pictures/${pic.id}" height="150px" />
</#list>
<b>Title:</b>${product.title}<br>
<b>Description:</b>${product.description}<br>
<b>Price:</b>${product.price}<br>
<b>Seller:</b> <a href="/user/${product.user.id}">${product.user.name}</a>><br>

<#if role=="Optional[ROLE_BUYER]" >

    <form action="/product/${product.id}/add" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Add"/>
    </form>
</#if>
<hr>
</body>
</html>