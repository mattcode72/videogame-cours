<html>
<head>
    <title>Modification d'un game</title>
</head>
<body>
<div class="container">
    <form method="post">
        <div class="mb-4">
            <label class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="${game.name}">
        </div>
        <div class="mb-4">
            <label class="form-label">Description</label>
            <textarea type="email" class="form-control" id="description" name="description">${game.description}</textarea>
        </div>
        <div class="mb-4">
            <label class="form-label">ReleaseDate</label>
            <input type="text" class="form-control" id="releaseDate" name="releaseDate" value="${game.releaseDate}">
        </div>
        <div class="mb-4">
            <label class="form-label">Price</label>
            <input type="text" class="form-control" id="price" name="price" value="${game.price}">
        </div>

        <button type="submit" class="btn btn-primary" value="${game.id}" name="id">Modification</button>
    </form>
</div>
</body>
</html>
