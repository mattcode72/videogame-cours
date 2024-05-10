<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Game Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .game-info {
            margin-top: 20px;
        }

        .carousel-item img {
            max-height: 400px;
            margin: 0 auto;
        }

        .star {
            border: none;
            background: none;
            color: black;
            font-size: 2em;
            cursor: pointer;
            display: inline-block;
            margin: 0 0.25em;
        }

        #comment {
            width: 100%;
            padding: 0.5em;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-top: 1em;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-12 text-center">
            <h1>${game.name}</h1>
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <c:forEach items="${images}" var="image" varStatus="status">
                        <li data-target="#carouselExampleIndicators" data-slide-to="${status.index}" class="<c:if test='${status.index eq 0}'>active</c:if>"></li>
                    </c:forEach>
                </ol>
                <div class="carousel-inner">
                    <c:forEach items="${images}" var="image" varStatus="status">
                        <div class="carousel-item <c:if test='${status.index eq 0}'>active</c:if>">
                            <img class="d-block w-100" src="${pageContext.request.contextPath}${image.path}" alt="Slide ${status.index}">
                        </div>
                    </c:forEach>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>

    <div class="row game-info">
        <div class="col-md-12">
            <h2 class="text-center">Informations</h2>
            <table class="table table-striped">
                <tr>
                    <th>Description</th>
                    <td>${game.description}</td>
                </tr>
                <tr>
                    <th>Release Date</th>
                    <td><fmt:formatDate value="${game.releaseDate}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <th>Platforms</th>
                    <td>
                        <c:forEach items="${game.platforms}" var="platform" varStatus="status">
                            <c:out value="${platform.name}"/><c:if test="${not status.last}">, </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <th>Developers</th>
                    <td>
                        <c:forEach items="${game.developers}" var="developer" varStatus="status">
                            <c:out value="${developer.name}"/><c:if test="${not status.last}">, </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <th>Available Languages</th>
                    <td>
                        <c:forEach items="${game.langs}" var="lang" varStatus="status">
                            <c:out value="${lang.name}"/><c:if test="${not status.last}">, </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <th>Game Modes</th>
                    <td>
                        <c:forEach items="${game.gameModes}" var="gameMode" varStatus="status">
                            <c:out value="${gameMode.name}"/><c:if test="${not status.last}">, </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="col-md-12 text-center">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#rateGameModal">
            Rate this game
        </button>
    </div>
</div>

<div class="modal fade" id="rateGameModal" tabindex="-1" role="dialog" aria-labelledby="rateGameModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="rateGameModalLabel">Rate this game</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="">
                <div class="modal-body">
                    <div id="stars" style="text-align: center">
                        <button type="button" class="star" data-value="1">&#9734;</button>
                        <button type="button" class="star" data-value="2">&#9734;</button>
                        <button type="button" class="star" data-value="3">&#9734;</button>
                        <button type="button" class="star" data-value="4">&#9734;</button>
                        <button type="button" class="star" data-value="5">&#9734;</button>
                    </div>
                    <textarea id="comment" name="comment" rows="4" cols="50" placeholder="Write your comment here..."></textarea>
                    <input type="hidden" id="rating" name="rating">
                    <input type="hidden" id="gameId" name="gameId" value="${game.id}">
                    <input type="hidden" name="action" value="addToRating">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" name="addRating" class="btn btn-primary">Submit Rating</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
        $("#rateGameModal").modal();
    });
    $(document).ready(function(){
        $(".star").click(function() {
            var value = $(this).data("value");
            $("#stars .star").each(function() {
                if ($(this).data("value") <= value) {
                    $(this).html("&#9733;").css("color", "yellow");
                    $(this).css({"-webkit-text-stroke-width": "1px", "-webkit-text-stroke-color": "black"});
                } else {
                    $(this).html("&#9734;").css("color", "black");
                    $(this).css({"-webkit-text-stroke-width": "0px"});
                }
            });
            // Update the hidden input field
            $("#rating").val(value);
        });

        $("#comment").change(function() {
            // Update the hidden input field
            $("#comment").val($(this).val());
        });

        $("#rateGameModal").modal();
    });
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
