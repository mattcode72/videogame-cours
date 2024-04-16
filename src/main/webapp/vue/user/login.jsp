<!--  ==============Message en cas d'erreur ================ -->
<c:if test="${not empty erreur }">

    <div class="alert alert-danger">
            ${erreur }
    </div>

</c:if>

<div class="container">
    <form method="post">
        <div class="mb-3">
            <label class="form-label">Email address</label>
            <input type="email" class="form-control" id="email" name="email" value="test@test.fr">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" value="test." name="password">
        </div>
        <button type="submit" class="btn btn-outline-primary">Submit</button>
    </form>
</div>
</body>
</html>