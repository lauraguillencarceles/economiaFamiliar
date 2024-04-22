package es.ecofam.economiafamiliar.dto;

    /*
    {
        "username" : "user1",
        "password": "admin",
        "password2": "admin",
        "nombreCompleto" : "Usuario 1"
    }

   */
    public record UserRegisterDTO(String username, String password, String password2, String nombreCompleto) {
    }

