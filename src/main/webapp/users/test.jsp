<div class="card shadow-sm">
  <div class="card-header">
    <h4 class="mb-0 fw-bold">Dodaj użytkownika</h4>
  </div>

  <div class="card-body">
    /user/add

    <div class="mb-3">
      <label for="userName" class="form-label">Nazwa użytkownika</label>
      <input
              type="text"
              id="userName"
              name="userName"
              class="form-control"
              placeholder="Wprowadź nazwę użytkownika"
              required>
    </div>

    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <input
              type="email"
              id="email"
              name="email"
              class="form-control"
              placeholder="np. jan.kowalski@example.com"
              required>
    </div>

    <div class="mb-3">
      <label for="password" class="form-label">Hasło</label>
      <input
              type="password"
              id="password"
              name="password"
              class="form-control"
              placeholder="Wprowadź hasło"
              required>
    </div>

    <button type="submit" class="btn btn-primary">
      Zapisz
    </button>

    /users
    Anuluj
    </a>

    </form>
  </div>
</div>