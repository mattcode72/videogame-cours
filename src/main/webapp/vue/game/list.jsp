<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach items="${games}" var="game">
            <div class="col">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">
                            <c:out value="${game.name}"></c:out>
                        </h5>
                                                    <c:forEach items="${game.categories}" var="category">
                                                        <span class="badge bg-primary">
                                                            <c:out value="${category.name}"></c:out>
                                                        </span>
                                                    </c:forEach>
<%--                        <h6 class="card-subtitle mb-2 text-body-secondary"> By--%>
<%--                            <c:out value="${game.developer.name }"></c:out>--%>
<%--                        </h6>--%>
<%--                        <p class="card-text">--%>
<%--                            <c:out value="${game }"></c:out>--%>
<%--                        </p>--%>
                    </div>
                    <div class="card-footer">
                        <!-- SHOW ARTICLE -->
                        <a href="show?id=<c:out value="${game.id }"/>" class="card-link">
                            <i class="fa-regular fa-eye"></i>
                        </a>
                        <!-- FIN SHOW ARTICLE -->

                        <!-- PANIER =============================-->
                        <form method="post">
                            <button type="submit" class="btn btn-sm btn-outline-success rounded-pill"
                                    name="addPanier" value="${game.id}">
                                <i class="fa fa-cart-plus"></i>
                            </button>
                        </form>
                        <!-- PANIER =============================-->

                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>