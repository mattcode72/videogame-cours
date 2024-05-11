<c:if test="${not empty erreur }">

    <div class="alert alert-danger">
            ${erreur }
    </div>

</c:if>
<title class="text-center">Login</title>
<div class="container">
    <form method="post">
        <div class="mb-3">
            <label class="form-label">Email address</label>
            <input type="email" class="form-control" id="email" name="email" value="">
        </div>
        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" class="form-control" id="password" value="" name="password">
        </div>
        <button type="submit" class="btn btn-outline-primary">Submit</button>
    </form>
</div>
</body>
</html>