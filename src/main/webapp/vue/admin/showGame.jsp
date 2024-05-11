<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modification d'un game</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .padding-20 {
            padding: 20px;
        }
        label.form-label {
            font-weight: bold;
            margin-bottom: 5px;
        }
        .title-bar {
            border-top: 1px solid #ccc;
            margin-top: 20px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container padding-20">
    <form method="post">
        <h3 class="text-secondary"><i class="fas fa-gamepad"></i> <strong>Modify Game</strong></h3>
        <div class="title-bar"></div>
        <div class="mb-4">
            <label class="form-label"><i class="fas fa-heading"></i> Name :</label>
            <input type="text" class="form-control" id="name" name="name" value="${game.name}">
        </div>
        <div class="mb-4">
            <label class="form-label"><i class="fas fa-align-left"></i> Description :</label>
            <textarea class="form-control" id="description" name="description">${game.description}</textarea>
        </div>
        <div class="mb-4">
            <label class="form-label"><i class="fas fa-calendar-alt"></i> Release Date :</label>
            <input type="text" class="form-control" id="releaseDate" name="releaseDate" value="${game.releaseDate}">
        </div>
        <div class="mb-4">
            <label class="form-label"><i class="fas fa-dollar-sign"></i> Price :</label>
            <input type="text" class="form-control" id="price" name="price" value="${game.price}">
        </div>

        <div class="mb-4">
            <label class="form-label"><i class="fas fa-image"></i> Thumbnail :</label>
            <input type="file" class="form-control" id="thumbnail" name="thumbnail">
        </div>

        <div class="mb-4">
            <label class="form-label"><i class="fas fa-images"></i> Images :</label>
            <input type="file" class="form-control" id="images" name="images" multiple>
        </div>

        <div class="mb-4">
            <label class="form-label"><i class="fas fa-video"></i> Videos :</label>
            <input type="text" class="form-control" id="video" name="video">
        </div>

        <div class="mb-4">
            <label class="form-label"><i class="fas fa-laptop"></i> Platforms :</label>
            <select name="platforms" id="platform" class="selectpicker" multiple>
                <c:forEach items="${platforms}" var="platform">
                    <c:set var="isSelected" value="false" />
                    <c:forEach items="${game.platforms}" var="gamePlatform">
                        <c:if test="${gamePlatform.id == platform.id}">
                            <c:set var="isSelected" value="true" />
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${isSelected}">
                            <option value="<c:out value="${platform.id}"></c:out>" selected>
                                <c:out value="${platform.name}"></c:out>
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="<c:out value="${platform.id}"></c:out>">
                                <c:out value="${platform.name}"></c:out>
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </select>
        </div>

        <div class="mb-4">
            <label class="form-label">Languages : </label>
            <select name="langs" id="lang" class="selectpicker" multiple>
                <c:forEach items="${langs}" var="lang">
                    <c:set var="isSelected" value="false" />
                    <c:forEach items="${game.langs}" var="gameLang">
                        <c:if test="${gameLang.id == lang.id}">
                            <c:set var="isSelected" value="true" />
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${isSelected}">
                            <option value="<c:out value="${lang.id}"></c:out>" selected>
                                <c:out value="${lang.name}"></c:out>
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="<c:out value="${lang.id}"></c:out>">
                                <c:out value="${lang.name}"></c:out>
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>

        <div class="mb-4">
            <label class="form-label">Developers : </label>
            <select name="developers" id="developer" class="selectpicker" multiple>
                <c:forEach items="${developers}" var="developer">
                    <c:set var="isSelected" value="false" />
                    <c:forEach items="${game.developers}" var="gameDeveloper">
                        <c:if test="${gameDeveloper.id == developer.id}">
                            <c:set var="isSelected" value="true" />
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${isSelected}">
                            <option value="<c:out value="${developer.id}"></c:out>" selected>
                                <c:out value="${developer.name}"></c:out>
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="<c:out value="${developer.id}"></c:out>">
                                <c:out value="${developer.name}"></c:out>
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>

        <div class="mb-4">
            <label class="form-label">Categories : </label>
            <select name="categories" id="category" class="selectpicker" multiple>
                <c:forEach items="${categories}" var="category">
                    <c:set var="isSelected" value="false" />
                    <c:forEach items="${game.categories}" var="gameCategory">
                        <c:if test="${gameCategory.id == category.id}">
                            <c:set var="isSelected" value="true" />
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${isSelected}">
                            <option value="<c:out value="${category.id}"></c:out>" selected>
                                <c:out value="${category.name}"></c:out>
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="<c:out value="${category.id}"></c:out>">
                                <c:out value="${category.name}"></c:out>
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </select>
        </div>

        <div class="mb-4">
            <label class="form-label">Game Modes : </label>
            <select name="gamemodes" id="gamemode" class="selectpicker" multiple>
                <c:forEach items="${gameModes}" var="gamemode">
                    <c:set var="isSelected" value="false" />
                    <c:forEach items="${game.gameModes}" var="gameGameMode">
                        <c:if test="${gameGameMode.id == gamemode.id}">
                            <c:set var="isSelected" value="true" />
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${isSelected}">
                            <option value="<c:out value="${gamemode.id}"></c:out>" selected>
                                <c:out value="${gamemode.name}"></c:out>
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="<c:out value="${gamemode.id}"></c:out>">
                                <c:out value="${gamemode.name}"></c:out>
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary" value="${game.id}" name="id">Modification</button>
    </form>
</div>

<!-- Bootstrap Scripts -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
