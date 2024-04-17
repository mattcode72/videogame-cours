<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    td, th {
        border: 1px solid black;
    }
</style>

<div class="container">
    <div class="row">
        <div class="col-md-12" style="text-align: center">
            <h1>
                <c:out value="${game.name}"></c:out>
            </h1>
            <div class="col-12">
                <c:forEach items="${game.images}" var="image">
                             <span>
                                <img src="${pageContext.request.contextPath}${image.path}" alt="" width="400"/>
                             </span>
                </c:forEach>
            </div>
            <div class="col-12">
                <table style="border : 1px solid black;">
                    <tr>
                        <th>Description</th>
                        <td>
                            <c:out value="${game.description}"></c:out>
                        </td>
                    </tr>
                    <tr>
                        <th>Date de sortie</th>
                        <td>
                            <c:out value="${game.releaseDate}"></c:out>
                        </td>
                    </tr>
                    <tr>
                        <th>Plateformes</th>
                        <td>
                            <c:forEach items="${game.platforms}" var="platform">
                                <c:out value="${platform.name}"></c:out>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th>Developpeurs</th>
                        <td>
                            <c:forEach items="${game.developers}" var="developer">
                                <c:out value="${developer.name}"></c:out>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th>Langues disponibles</th>
                        <td>
                            <c:forEach items="${game.langs}" var="lang">
                                <c:out value="${lang.name}"></c:out>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th>Modes de jeu</th>
                        <td>
                            <c:forEach items="${game.gameModes}" var="gameMode">
                                <c:out value="${gameMode.name}"></c:out>
                            </c:forEach>
                        </td>
                    </tr>

                </table>
            </div>
        </div>

    </div>
</div>