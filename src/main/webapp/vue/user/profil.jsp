<div class="container">
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

    <section class="container-account">

        <h1><i class="bi bi-person-vcard"></i> Mon compte</h1>

        <div>Num�ro client : <c:out value="${currentUser.id}" /></div>
        <div id="accountNav" class="sticky-top">
            <a class="btn btn-primary" href="#accountInfo" role="button"><i class="bi bi-person-badge"></i> Infos</a>
            <a class="btn btn-primary" href="#accountPassword" role="button"><i class="bi bi-three-dots"></i> Mot de passe</a>
        </div>

        <hr id="accountInfo">
        <h3 class="text-secondary"><i class="bi bi-person-badge"></i> Mes informations personnelles</h3>
        <div class="row g-0 text-center">

            <div class="col-md-8">
                <form method="post">
                    <input type="hidden" class="form-control" name="formSubmited" value="accountInfo">
                    <div class="mb-3">
                        <div class="row">
                            <div class="col">
                                <label class="form-label">Pseudo</label>
                                <input type="text" class="form-control" id="userPseudo" name="userPseudo"
                                       value="<c:out value="${currentUser.pseudo}"/>" readonly>
                            </div>
                            <div class="col">
                                <label for="userEmail" class="form-label">Email address</label>
                                <input type="email" class="form-control" id="userEmail" name="userEmail"
                                       value="<c:out value="${currentUser.email}"/>" readonly>
                            </div>
                        </div>
                        <div class="spacer"></div>

                    </div>
                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editUser">Edit</button>
                </form>

            </div>
        </div>

        <!-- CHANGEMENT DE MOT DE PASSE -->
        <hr id="accountPassword">
        <h3 class="text-secondary"><i class="bi bi-three-dots"></i> Mon mot de passe</h3>
        <form method="post">
            <input type="hidden" class="form-control" name="formSubmited" value="accountPassword">
            <div class="mb-3">
                <div class="row">
                    <div class="col">
                        <label class="form-label">Ancien</label>
                        <input type="password" class="form-control" id="oldPassword" name="oldPassword" required>
                    </div>
                    <div class="col">
                        <label class="form-label">Nouveau</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                    </div>
                    <div class="col">
                        <label class="form-label">Confirmation</label>
                        <input type="password" class="form-control" id="confPassword" name="confPassword" required>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-outline-warning">Changer mon mot de passe</button>
        </form>


        <hr>
        <div class="text-end">
            <h5 class="text-secondary"><i class="bi bi-person-fill-dash text-danger"></i> Désactiver mon compte</h5>
            <button type="submit" class="btn btn-sm btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deactivateModal">Je désactive mon compte</button>
        </div>
    </section>


    <!-- Modal -->
    <div class="modal fade" id="editUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Edit profil</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>

                        <button type="submit" class="btn btn-primary">Je modifie mon compte</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal de d�sactivation de compte -->
    <div class="modal fade" id="deactivateModal" tabindex="-1" aria-labelledby="deactivateModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content border-3 border-danger">
                <div class="modal-header">
                    <h1 class="modal-title fs-5 text-danger" id="deactivateModalLabel">D�sactivation de compte</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="text-danger fw-bold">�tes-vous s�r de vouloir d�sactiver votre compte ?</div>
                    <div class="text-danger opacity-75">Si oui, veuillez confirmer votre d�cision en saisissant votre mot de passe :</div>
                    <form id="deactivateForm" method="post">
                        <input type="hidden" class="form-control" name="formSubmited" value="suppAccount">
                        <input type="hidden" class="form-control" id="userIDtoDeactivate" name="userIDtoDeactivate">
                        <input type="password" class="form-control mt-3" id="pwdForDeactivation" name="pwdForDeactivation" placeholder="Saisissez votre mot de passe">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                    <button type="submit" form="deactivateForm" class="btn btn-outline-danger">D�sactiver mon compte</button>
                </div>
            </div>
        </div>
    </div>


</div>