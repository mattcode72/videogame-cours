<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>

    <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/videogame/">
            <img alt="" src="${pageContext.request.contextPath}/assets/img/videogame.jpg" width="75">
        </a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="about">A propos de nous</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="contact">Nous contacter</a>
                </li>

            </ul>
            <div class="d-flex" style="margin-right: 5vw;">
                <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll">
                    <c:if test="${not empty currentUser}">
                        <li class="nav-item dropdown">

                            <button type="button" class="btn btn-outline-success dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                <c:out value="${currentUser.pseudo}"/>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="profil">Mon compte</a></li>
                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="logout">Se deconnecter</a></li>
                            </ul>
                        </li>
                    </c:if>

                    <c:if test="${ empty currentUser}">
                        <a href="login">
                            <button class="btn btn-outline-primary me-4">
                                Se connecter
                            </button>
                        </a>
                        <a href="inscription">
                            <button class="btn btn-outline-primary me-4">S'inscrire</button>
                        </a>
                    </c:if>
                </ul>

                <!-- GESTION PANIER -->
                <a href="cart">
                    <button type="button" class="btn btn-outline-success position-relative">
                        <i class="fa-solid fa-cart-shopping"></i>
                        <c:if test="${panier.count() > 0}">
		<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
		<c:out value="${panier.count() }"></c:out>
		</span>
                        </c:if>
                    </button>
                </a>
                <!-- FIN GESTION PANIER -->

            </div>
        </div>
    </div>
</nav>