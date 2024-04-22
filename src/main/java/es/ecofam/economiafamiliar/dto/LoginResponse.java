package es.ecofam.economiafamiliar.dto;

import java.util.List;

    public record LoginResponse(int id, String username, String nombre_completo, List<String> authorities, String token) {
    }

