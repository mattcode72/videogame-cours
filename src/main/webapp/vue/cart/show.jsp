<table class="table" style="margin-left: 30px; margin-right: 30px;">
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
    <c:forEach items="${items}" var="item">
        <tr>
            <td><c:out value="${item.game.name}"/></td>
            <td><c:out value="${item.game.price * item.quantity}"/></td>
            <td>
                <div style="display: inline-flex;">
                    <form method="post">
                        <button type="submit" class="btn btn-outline-success" name="less" value="${item.game.id}">
                            <i class="fa-sharp fa-solid fa-minus"></i>
                        </button>
                    </form>
                    <span style="margin-left: 10px;"><c:out value="${item.quantity}"/></span>
                    <form method="post" style="margin-left: 10px;">
                        <button class="btn btn-outline-success rounded-circle" name="more" value="${item.game.id}">
                            <i class="fa-sharp fa-solid fa-plus"></i>
                        </button>
                    </form>
                </div>
            </td>
            <td>
                <form method="post" action="">
                    <button type="submit" class="btn btn-outline-danger rounded-circle" name="deleteItem"
                            value="${item.game.id}">
                        <i class="fa-sharp fa-solid fa-times"></i>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>

<div class="row" style="margin-left: 30px">
    <div class="col-2">
        <a href="#confirmCommand">
            <button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#confirmCommand">Buy</button>
        </a>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="confirmCommand" tabindex="-1" aria-labelledby="confirmCommandLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmCommandLabel">Confirm Purchase</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to proceed with this order?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                <form method="post" action="">
                    <button type="submit" class="btn btn-primary" name="confirmCart" value="true">Yes</button>
                </form>
            </div>
        </div>
    </div>
</div>