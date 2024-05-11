<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    .delimiter-line {
        border: 0;
        border-top: 2px solid #000;
        margin: 20px 0;
        max-width: 1170px;
    }
</style>

<div class="container">

    <form method="post" action="">
        <input type="hidden" name="action" value="addToFilter">
        <div class="row align-items-center">
            <div class="col-2">
                <label for="filterCategory">Categories :</label>
                <select id="filterCategory" name="filterCategory" class="selectpicker" multiple>
                    <c:forEach items="${categories}" var="category">
                        <option value="<c:out value="${category.id}"></c:out>">
                            <c:out value="${category.name}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-2" style="margin-left: 10px;">
                <label for="filterPlatform">Platforms :</label>
                <select id="filterPlatform" name="filterPlatform" class="selectpicker" multiple>
                    <c:forEach items="${platforms}" var="platform">
                        <option value="<c:out value="${platform.id}"></c:out>">
                            <c:out value="${platform.name}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-2" style="margin-left: 10px;">
                <label for="filterLang">Languages :</label>
                <select id="filterLang" name="filterLang" class="selectpicker" multiple>
                    <c:forEach items="${langs}" var="lang">
                        <option value="<c:out value="${lang.id}"></c:out>">
                            <c:out value="${lang.name}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-2" style="margin-left: 10px;">
                <label for="filterName"></label>
                <input type="text" id="filterName" name="filterName" placeholder="Game name" class="form-control">
            </div>
            <div class="col-2" style="margin-left: 10px;">
                <button type="submit" class="btn btn-primary mt-4">Search</button>
            </div>
        </div>
    </form>

    <hr class="delimiter-line">

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
                        <div class="col-9">
                            <c:out value="${game.price}"></c:out> euros
                        </div>
                        <div class="col-3">
                            <form method="post" action="">
                                <input type="hidden" name="action" value="addToCart">
                                <button type="submit" class="btn btn-sm btn-outline-success rounded-pill"
                                    name="addCart" value="${game.id}">
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