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


    <div class="container mt-4">
          <div class="card shadow-sm">
            <div class="card-header">
              <h4 class="mb-0 fw-bold">Szczegóły użytkownika</h4>
            </div>

            <div class="card-body">

              <p class="mb-2">
                <strong>ID:</strong> ${currentUser.id}
              </p>

              <p class="mb-2">
                <strong>Nazwa użytkownika:</strong> ${currentUser.userName}
              </p>

              <p class="mb-0">
                <strong>Email:</strong> ${currentUser.email}
              </p>

            </div>

          </div>

    </div>

  </div>
</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<%@ include file="/footer.jsp" %>