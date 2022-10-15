<!DOCTYPE html>
<html>
<head>
    <title>WEBSHOP</title>
</head>
<body>
<h1>WEBSHOP</h1><hr>
<h4> Full information</h4>
<#list pictures as pic>
    <pic srs="/pictures/${pic.id}" height="50px"/><br><br>
</#list>
<b>Title:</b>${product.title}<br>
<b>Description:</b>${product.description}<br>
<b>Price:</b>${product.price}<br>
<b>Seller:</b> <a href="/user/${product.user.id}">${product.user.name}</a>><br>

<#if role=="Optional[ROLE_USER]" >
    <form>
        <input type="submit" value="Add" onclick="/product/${product.id}/add">
    </form>
</#if>
<hr>
</body>
</html>