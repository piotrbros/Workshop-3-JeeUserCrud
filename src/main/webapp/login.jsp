<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/header.jsp" %>
<!-- Begin Page Content -->
<div class="container-fluid">

  <!-- Page Heading -->
  <div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">UsersCRUD</h1>
    <a href="/user/add" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
            class="fas fa-download fa-sm text-white-50"></i>Dodaj użytkownika</a>
  </div>

  <!-- Content Row -->
  <div class="row">

    <!-- IF instruction - printing communication about empty allUser list,
    or constructing table with users when allUsers !empty. -->
    <div class="container mt-4">
          <div class="card shadow-sm">
            <div class="card-header">
              <h4 class="mb-0 fw-bold">Zaloguj się do panelu administratora.</h4>
            </div>

            <div class="card-body">
              <form action="/login" method="post">

                <div class="mb-3">
                  <label for="email" class="form-label">Email</label>
                  <input
                          type="email"
                          id="email"
                          name="email"
                          class="form-control"
                          placeholder="Adres email"
                          required>
                </div>

                <div class="mb-3">
                  <label for="password" class="form-label">Hasło</label>
                  <input
                          type="password"
                          id="password"
                          name="password"
                          class="form-control"
                          placeholder="Hasło"
                          required>
                </div>
                <button type="submit" class="btn btn-primary">
                  Zapisz
                </button>
              </form>
            </div>

          </div>

    </div>

  </div>
</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<%@ include file="/footer.jsp" %>