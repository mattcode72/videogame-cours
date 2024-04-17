<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach items="${games}" var="game">
            <div class="col">
                <div class="card" style="width: 18rem; margin-top: 15px;">
                    <div class="card-body">
                        <a href="show?id=<c:out value="${game.id }"/>" class="card-link" style="text-decoration: none; color: black;">
                        <h5 class="card-title col-12" style="text-align: center">
                            <c:out value="${game.name}"></c:out>
                        </h5>
                        <img class="col-12" alt="" src="${pageContext.request.contextPath}${game.thumbnail.path}"
                             width="250">
                        <div class="col-12">
                        <c:forEach items="${game.categories}" var="category">
                             <span class="badge bg-primary">
                             <c:out value="${category.name}"></c:out>
                             </span>
                        </c:forEach>
                            </div>
                        <div class="col-12">
                            <c:out value="${game.description}"></c:out>
                        </div>
                            </a>
                    </div>
                    <div class="card-footer">
                    <div class="row">
<%--                        <div class="col-6">--%>
<%--                            <a href="show?id=<c:out value="${game.id }"/>" class="card-link">--%>
<%--                                <i class="fa-regular fa-eye"></i>--%>
<%--                            </a>--%>
<%--                        </div>--%>
                        <div class="col-9">
                            <c:out value="${game.price}"></c:out> euros
                        </div>
                        <div class="col-3">
                            <form method="post">
                                <button type="submit" class="btn btn-sm btn-outline-success rounded-pill"
                                    name="addPanier" value="${game.id}">
                                    <i class="fa fa-cart-plus"></i>
                                </button>
                            </form>
                        </div>

                    </div>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>