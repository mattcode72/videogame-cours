<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Account</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .padding-20 {
            padding: 20px;
        }
    </style>
</head>
<body>
<div class="container padding-20">
    <!-- Alert Messages -->
    <c:if test="${msnType.equals('OK')}">
        <div class="alert alert-success" role="alert">
            <c:out value="${msn}"/>
        </div>
    </c:if>
    <c:if test="${msnType.equals('KO')}">
        <div class="alert alert-danger" role="alert">
            <c:out value="${msn}"/>
        </div>
    </c:if>

    <section class="container-account">
        <!-- Personal Information -->
        <h1><i class="fas fa-user"></i> My Account</h1>
        <hr>
        <h3 class="text-secondary"><i class="far fa-address-card"></i> Personal Information</h3>
        <div class="row">
            <div class="col-md-8">
                <form method="post">
                    <input type="hidden" class="form-control" name="formSubmited" value="accountInfo">
                    <div class="form-group row">
                        <label for="userPseudo" class="col-sm-3 col-form-label">Pseudo</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" class="form-control" id="userPseudo" name="userPseudo"
                                       value="<c:out value="${currentUser.pseudo}"/>" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="userEmail" class="col-sm-3 col-form-label">Email address</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                </div>
                                <input type="email" class="form-control" id="userEmail" name="userEmail"
                                       value="<c:out value="${currentUser.email}"/>" readonly>
                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editUser"><i
                            class="fas fa-edit"></i> Edit
                    </button>
                </form>
            </div>
        </div>
        <hr>

        <!-- Change Password -->
        <h3 class="text-secondary"><i class="fas fa-key"></i> Change Password</h3>
        <form method="post">
            <input type="hidden" class="form-control" name="formSubmited" value="accountPassword">
            <div class="form-row">
                <div class="form-group col-md-4">
                    <input type="password" class="form-control" id="oldPassword" name="oldPassword"
                           placeholder="Old Password" required>
                </div>
                <div class="form-group col-md-4">
                    <input type="password" class="form-control" id="newPassword" name="newPassword"
                           placeholder="New Password" required>
                </div>
                <div class="form-group col-md-4">
                    <input type="password" class="form-control" id="confPassword" name="confPassword"
                           placeholder="Confirm New Password" required>
                </div>
            </div>
            <button type="submit" class="btn btn-outline-warning"><i class="fas fa-lock"></i> Change Password</button>
        </form>
        <hr>

        <!-- Order History -->
        <h3 class="text-secondary"><i class="fas fa-shopping-bag"></i> Order History</h3>
        <ul>
            <c:forEach items="${orders}" var="order">
                <li>
                    <c:out value="${order.id}"></c:out>, <c:out value="${order.date}"></c:out>
                </li>
            </c:forEach>
        </ul>

        <!-- Disable Account -->
        <h3 class="text-secondary"><i class="fas fa-user-slash"></i> Disable Account</h3>
        <button type="button" class="btn btn-sm btn-outline-danger" data-toggle="modal" data-target="#deactivateModal">
            <i class="fas fa-times"></i> Disable My Account
        </button>

    </section>
</div>

<!-- Modal Edit User -->
<div class="modal fade" id="editUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Edit profil</h1>
                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post">
                <input type="hidden" class="form-control" name="formSubmited" value="editAccount">
                <div class="modal-body">
                    <div class="mb-3">
                        <input type="hidden" class="form-control" id="id" name="id"
                               value="<c:out value="${currentUser.id }"/>">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Pseudo</label>
                        <input type="text" class="form-control" id="pseudo" name="pseudo"
                               value="<c:out value="${currentUser.pseudo }"/>">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Email address</label>
                        <input type="email" class="form-control" id="email" name="email"
                               value="<c:out value="${currentUser.email }"/>">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Modify my account</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal Deactivate Account -->
<div class="modal fade" id="deactivateModal" tabindex="-1" aria-labelledby="deactivateModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content border-3 border-danger">
            <div class="modal-header">
                <h1 class="modal-title fs-5 text-danger" id="deactivateModalLabel">Delete account</h1>
                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="text-danger fw-bold">Are you sure you want to delete your account ?</div>
                <div class="text-danger opacity-75">Enter your password</div>
                <form id="deactivateForm" method="post">
                    <input type="hidden" class="form-control" name="formSubmited" value="suppAccount">
                    <input type="hidden" class="form-control" id="userIDtoDeactivate" name="userIDtoDeactivate">
                    <input type="password" class="form-control mt-3" id="pwdForDeactivation" name="pwdForDeactivation"
                           placeholder="Password">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="submit" form="deactivateForm" class="btn btn-outline-danger">Delete account</button>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap Scripts -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
