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
                <td>
                    <a href="admin/showGame?id=<c:out value="${game.id}"/>">View</a>
                    <a href="#" data-toggle="modal" data-target="#deleteGameModal">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="admin/addGame" class="btn btn-primary"><i class="fas fa-plus"></i> Add a Game</a>
</div>


<!-- Modal Deactivate Account -->
<div class="modal fade" id="deleteGameModal" tabindex="-1" aria-labelledby="deleteGameModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content border-3 border-danger">
            <div class="modal-header">
                <h1 class="modal-title fs-5 text-danger" id="deleteGameModalLabel">Game delete</h1>
                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="text-danger fw-bold">Are you sure you want to delete this game ?</div>
                <form id="deactivateForm" method="post">
                    <input type="hidden" class="form-control" name="formSubmited" value="suppAccount">
                    <input type="hidden" class="form-control" id="userIDtoDeactivate" name="userIDtoDeactivate">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                <button type="submit" form="deactivateForm" class="btn btn-outline-danger" name="deleteGame">Delete game</button>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap Scripts -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
