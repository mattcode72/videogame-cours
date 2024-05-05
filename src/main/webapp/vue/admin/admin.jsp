
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Gestion des users</h1>

<table class="table">
    <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">pseudo</th>
            <th scope="col">email</th>
            <th scope="col">admin</th>
            <th scope="col">action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.pseudo}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <c:if test="${user.isAdmin}">
                    <td>oui</td>
                </c:if>
                <c:if test="${!user.isAdmin}">
                    <td>non</td>
                </c:if>
                <td><a href="admin/showUser?id=<c:out value="${user.id}"/>">Voir</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<br>
<hr>
<br>

<h1>Gestion des games</h1>

<table class="table">
    <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">name</th>
            <th scope="col">description</th>
            <th scope="col">release date</th>
            <th scope="col">price</th>
            <th scope="col">action</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${games}" var="game">
        <tr>
            <td><c:out value="${game.id}"></c:out></td>
            <td><c:out value="${game.name}"></c:out></td>
            <td><c:out value="${game.description}"></c:out></td>
            <td><c:out value="${game.releaseDate}"></c:out></td>
            <td><c:out value="${game.price}"></c:out></td>
            <td><a href="admin/showGame?id=<c:out value="${game.id}"/>">Voir</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="admin/addGame">Add a game</a>

</body>
</html>
