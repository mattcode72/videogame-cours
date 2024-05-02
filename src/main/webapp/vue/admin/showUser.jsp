<html>
<head>
    <title>Modification d'un utilisateur</title>
</head>
<body>
    <div class="container">
        <form method="post">
            <div class="mb-4">
                <label class="form-label">Pseudo</label>
                <input type="text" class="form-control" id="pseudo" name="pseudo" value="${user.pseudo}">
            </div>
            <div class="mb-4">
                <label class="form-label">Email address</label>
                <input type="email" class="form-control" id="email" name="email" value="${user.email}">
            </div>
            <div class="mb-4">
                <label for="isAdmin" class="form-label">Admin</label>
                <input type="text" class="form-control" id="isAdmin" name="isAdmin" value="${user.isAdmin}">
            </div>

            <button type="submit" class="btn btn-primary" value="${user.id}" name="id">Modification</button>
        </form>
    </div>
</body>
</html>
