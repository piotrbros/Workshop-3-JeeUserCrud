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

      <c:choose>
        <c:when test="${empty allUsers}">
          <div class="alert alert-warning">
            Brak użytkowników w bazie.
          </div>
        </c:when>

        <c:otherwise>

          <div class="card shadow-sm">
            <div class="card-header">
              <h4 class="mb-0 fw-bold">Lista użytkowników</h4>
            </div>
            <div class="card-body">

              <table class="table table-striped table-hover align-middle">
                <thead class="table-light">
                <tr>
                  <th>ID</th>
                  <th>Nazwa użytkownika</th>
                  <th>Email</th>
                  <th>Akcja</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${allUsers}" var="user">
                  <tr>
                    <td>${user.id}</td>
                    <td>${user.userName}</td>
                    <td>${user.email}</td>
                    <td>
                      <a href="/user/delete?id=${user.id}">Usuń</a>
                      <a href="/user/edit?id=${user.id}">Edytuj</a>
                      <a href="/user/show?id=${user.id}">Pokaż</a>
                    </td>
                  </tr>
                </c:forEach>
                </tbody>

              </table>

            </div>
          </div>

        </c:otherwise>
      </c:choose>

    </div>

  </div>
</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<%@ include file="/footer.jsp" %>