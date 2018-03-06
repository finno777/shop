<%@page session="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        <%@ include file="../css/main.css"%>
    </style>
</head>
<body>
<div class="wrapper">
    <header class="header">
        <div class="container">
            <div class="header__inner">
                <div class="header__logo">Magaz</div>
                <ul class="header__actions">
                    <security:authorize access="isAuthenticated()">
                        <li><a href="/logout" class="btn btn-primary">Logout</a></li>
                        <li>
                            <button class="btn btn-success js-product">Add product</button>
                        </li>
                    </security:authorize>
                    <security:authorize access="!isAuthenticated()">
                        <li><a href="/login" class="btn btn-primary">Login</a></li>
                    </security:authorize>
                </ul>
            </div>
        </div>
    </header>

    <main class="main">
        <div class="container">
            <div class="catalog">
                <div class="row">
                    <c:forEach var="product" items="${products}">
                        <div class="col-md-4">
                            <div class="catalog__item" id="${product.productId}">
                                <security:authorize access="hasRole('ROLE_USER')">
                                    <ul class="item__action">
                                        <li>
                                            <button class="_edit js-product"></button>
                                        </li>
                                        <li>
                                            <button data-id="${product.productId}" class="_del js-remove-product"></button>
                                        </li>
                                    </ul>
                                </security:authorize>

                                <div class="item__img">
                                    <a href="${renderProductUrl}">
                                        <img src="https://dummyimage.com/350x200/f5f5f5/000000.jpg" alt="">
                                    </a>
                                </div>
                                <div class="item__body">
                                    <div class="item__title">
                                        <a href="${renderProductUrl}"> ${product.name}</a>
                                    </div>
                                    <div class="item__price">${product.price}$</div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </main>

    <footer class="footer">
        <div class="container">
            <div class="footer__copyright">
                &copy; Do not you dare copy this!
            </div>
        </div>
    </footer>
</div>

<div class="form-popup">
    <div class="form-popup__inner">
        <div class="form-group">
            <label>Name</label>
            <input type="email" class="form-control"
                   aria-describedby="emailHelp" placeholder="Name">
        </div>
        <div class="form-group">
            <label>Price</label>
            <input type="password" class="form-control"
                   placeholder="Price">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </div>
</div>

<script>
    <%@ include file="../js/common.js"%>
</script>

</body>
</html>