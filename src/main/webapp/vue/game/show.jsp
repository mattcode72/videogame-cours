<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <img alt="" src="assets/img/<c:out value="${article.image}" />" width="100%">


        </div>
        <div class="col-md-6">
            <h4 class="card-title">Détail du jeu:
                <c:out value="${game.titre}"></c:out>
            </h4>
            <h6 class="card-subtitle mb-2 text-body-secondary"> Post� par:
                <c:out value="${game.developer.name } ${game.developer.description }"></c:out>
                , le <fmt:formatDate value="${game.release_date}" type="date" dateStyle="full"/>
            </h6>
            <hr>
            <p class="card-text">
                <b>Résumé:</b>
                <c:out value="${game.description }"></c:out>
            </p>
        </div>

    </div>
</div>