<c:if test="${msnType.equals('OK')}">
    <div class="alert alert-success timer" role="alert">
        <c:out value="${msn }"></c:out>
    </div>
</c:if>
<c:if test="${msnType.equals('KO')}">
    <div class="alert alert-danger timer" role="alert">
        <c:out value="${msn }"></c:out>
    </div>
</c:if>

<div class="container">
    <form method="post">

        <div class="mb-4">
            <label class="form-label">Pseudo</label>
            <input type="text" class="form-control" id="pseudo" name="pseudo">
        </div>
        <div class="mb-4">
            <label class="form-label">Email address</label>
            <input type="email" class="form-control" id="email" name="email">
        </div>
        <div class="mb-4">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" name="password">
        </div>

        <button type="submit" class="btn btn-primary">Je crée mon compte</button>
    </form>
</div>
</body>
</html>