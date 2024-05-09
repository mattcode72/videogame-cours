<html>
<head>
    <title>Add game</title>
</head>
<body>
    <div class="container">
        <form method="post" enctype="multipart/form-data">
            <div class="mb-4">
                <label class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name">
            </div>
            <div class="mb-4">
                <label class="form-label">Description</label>
                <textarea type="email" class="form-control" id="description" name="description"></textarea>
            </div>
            <div class="mb-4">
                <label class="form-label">ReleaseDate</label>
                <input type="date" class="form-control" id="releaseDate" name="releaseDate">
            </div>
            <div class="mb-4">
                <label class="form-label">Price</label>
                <input type="number" class="form-control" id="price" name="price">
            </div>

            <div class="mb-4">
                <label class="form-label">Thumbnail :</label>
                <input type="file" class="form-control" id="thumbnail" name="thumbnail">
            </div>

            <div class="mb-4">
                <label class="form-label">Images :</label>
                <input type="file" class="form-control" id="images" name="images" multiple>
            </div>

            <div class="mb-4">
                <label class="form-label">Videos :</label>
                <input type="text" class="form-control" id="video" name="video">
            </div>

            <div class="mb-4">
                <label class="form-label">Platforms : </label>
                <select name="platforms" id="platform" class="selectpicker" multiple>
                    <c:forEach items="${platforms}" var="platform">
                        <option value="<c:out value="${platform.id}"></c:out>">
                            <c:out value="${platform.name}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-4">
                <label class="form-label">Langs : </label>
                <select name="langs" id="lang" class="selectpicker" multiple>
                    <c:forEach items="${langs}" var="lang">
                        <option value="<c:out value="${lang.id}"></c:out>">
                            <c:out value="${lang.name}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-4">
                <label class="form-label">Developers : </label>
                <select name="developers" id="developer" class="selectpicker" multiple>
                    <c:forEach items="${developers}" var="developer">
                        <option value="<c:out value="${developer.id}"></c:out>">
                            <c:out value="${developer.name}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-4">
                <label class="form-label">Categories : </label>
                <select name="categories" id="category" class="selectpicker" multiple>
                    <c:forEach items="${categories}" var="category">
                        <option value="<c:out value="${category.id}"></c:out>">
                            <c:out value="${category.name}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-4">
                <label class="form-label">Gamemodes : </label>
                <select name="gamemodes" id="gamemode" class="selectpicker" multiple>
                    <c:forEach items="${gamemodes}" var="gamemode">
                        <option value="<c:out value="${gamemode.id}"></c:out>">
                            <c:out value="${gamemode.name}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-primary" value="${game.id}" name="id">Create</button>
        </form>
    </div>
</body>
</html>
