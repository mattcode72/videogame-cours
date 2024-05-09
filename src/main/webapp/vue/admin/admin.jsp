<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .padding-20 {
            padding: 20px;
        }
    </style>
</head>
<body>
<div class="container padding-20">
    <h1><i class="fas fa-users-cog"></i> User Management</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Pseudo</th>
            <th scope="col">Email</th>
            <th scope="col">Admin</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.pseudo}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:if test="${user.isAdmin}">Yes</c:if><c:if test="${!user.isAdmin}">No</c:if></td>
                <td><a href="admin/showUser?id=<c:out value="${user.id}"/>">View</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr>
    <h1><i class="fas fa-gamepad"></i> Game Management</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Release Date</th>
            <th scope="col">Price</th>
            <th scope="col">Action</th>
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
                <td><a href="admin/showGame?id=<c:out value="${game.id}"/>">View</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="admin/addGame" class="btn btn-primary"><i class="fas fa-plus"></i> Add a Game</a>
</div>

<!-- Bootstrap Scripts -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
