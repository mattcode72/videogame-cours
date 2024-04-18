<table class="table">
    <thead>
    <tr>
        <th scope="col">Game</th>
        <th scope="col">Price</th>
        <th scope="col">Quantity</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <tbody>
    <c:forEach items="${cart.items}" var="item">
        <tr>
            <td><c:out value="${item.game.name}"/></td>
            <td><c:out value="${item.game.price}"/></td>
            <td>
                <div style="display: inline-flex;">
                    <form method="post">
                        <button type="submit" class="btn btn-outline-success" name="less" value="${item.game.id}">
                            <i class="fa-sharp fa-solid fa-minus"></i>
                        </button>
                    </form>
                    <span ><c:out value="${item.qty}"/></span>
                    <form method="post">
                        <button class="btn btn-outline-success rounded-circle" name="more" value="${item.game.id}">
                            <i class="fa-sharp fa-solid fa-plus"></i>
                        </button>
                    </form>
                </div>
            </td>
            <td>
                <form method="post">
                    <button class="btn btn-outline-danger rounded-circle" name="deleteItem" value="${item.game.id}">
                        <i class="fa-sharp fa-solid fa-times"></i>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>

<form method="post">
    <button class="btn btn-outline-danger" name="deleteCart" value="${item.game.id}">
        <i class="fa-sharp fa-solid fa-times"></i> Delete cart
    </button>
</form>

<div class="col-md-4 command">
    <button class="btn btn-outline-success">
        <i class="fa-regular fa-credit-card"></i>
        Total price: <c:out value="${cart.total()}"/> dollars
    </button>
    <h4>Passage � la caisse </h4>
    <a href="command">
        <button class="btn btn-warning">
            Commandez vos jeux
        </button>
    </a>
</div>
