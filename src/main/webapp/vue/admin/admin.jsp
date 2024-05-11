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


    <h1><i class="fas fa-star"></i> Review management</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Pseudo</th>
            <th scope="col">Game</th>
            <th scope="col">Rating</th>
            <th scope="col">Content</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reviews}" var="review">
            <tr>
                <td><c:out value="${review.user.pseudo}"></c:out></td>
                <td><c:out value="${review.game.name}"></c:out></td>
                <td><c:out value="${review.rating}"></c:out></td>
                <td><c:out value="${review.content}"></c:out></td>
                <td><a href="#" data-toggle="modal" data-target="#validateReviewModal"
                       data-gameid="<c:out value="${review.game.id}"/>"
                       data-userid="<c:out value="${review.user.id}"/>">View</a></td>
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
                    <a href="#" data-toggle="modal" data-target="#deleteGameModal"
                       data-id="<c:out value="${game.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="admin/addGame" class="btn btn-primary"><i class="fas fa-plus"></i> Add a Game</a>
</div>


<!-- Modal delete game -->
<div class="modal fade" id="deleteGameModal" data-id="" tabindex="-1" aria-labelledby="deleteGameModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-danger" id="deleteGameModalLabel">Delete Game</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="">
                <div class="modal-body">
                    <p class="text-danger font-weight-bold">Are you sure you want to delete this game?</p>
                    <input type="hidden" id="gameId" name="gameId">
                    <input type="hidden" name="action" value="deleteToGame">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger" name="deleteGame">Delete Game</button>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- Modal validate review -->
<div class="modal fade" id="validateReviewModal" data-id="" tabindex="-1" aria-labelledby="validateReviewModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-danger" id="validateReviewModalLabel">Validate review</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="">
                <div class="modal-body">
                    <p class="text-success font-weight-bold">Are you sure you want to validate this review?</p>
                    <input type="hidden" id="userId" name="userId">
                    <input type="hidden" id="idGame" name="idGame">
                    <input type="hidden" name="action" value="validateReview">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-success" name="deleteGame">Validate</button>
                </div>
            </form>
        </div>
    </div>
</div>


<script>
    $(document).ready(function () {
        $('#deleteGameModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            var gameId = button.data('id'); // Extract info from data-* attributes
            var modal = $(this);
            modal.find('#gameId').val(gameId);
        });

        $('#validateReviewModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var userId = button.data('userid');
            var modal = $(this);
            modal.find('#userId').val(userId);
        });

        $('#validateReviewModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var idGame = button.data('gameid');
            var modal = $(this);
            modal.find('#idGame').val(idGame);
        });
    });
</script>

<!-- Bootstrap Scripts -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
