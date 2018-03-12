<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ilyaj
  Date: 06.03.2018
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <li>id - ${product.productId}</li>
    <li>name - ${product.name}</li>
    <li>price - ${product.price}</li>
</ul>

<br>
<br>
<security:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
<input type="text" class="js-input-comment" id="${product.productId}">
<button class="js-add-comment">Добавить</button>

<br>
<br>
</security:authorize>

<ul class="js-list-comment">
    <c:forEach var="comment" items="${comments}">
        <li> ${comment.text}</li>
    </c:forEach>
</ul>



<script>
    const input = document.querySelector('.js-input-comment');
    const add = document.querySelector('.js-add-comment');
    const list = document.querySelector('.js-list-comment');
    if(add) {
        add.addEventListener('click', function () {
            const li = document.createElement('li');
            li.innerHTML = input.value;

            const request = new XMLHttpRequest();
            request.open('POST', 'http://localhost:8080/addComment');
            request.setRequestHeader('Content-Type', 'application/json');

            request.onreadystatechange = function () {
                if (this.readyState === 4) {
                    input.value = '';
                    list.appendChild(li);
                }
            };

            const body = {
                text: input.value,
                product: {
                    productId: +input.id
                }
            };

            request.send(JSON.stringify(body));
        });
    };


</script>


</body>
</html>
