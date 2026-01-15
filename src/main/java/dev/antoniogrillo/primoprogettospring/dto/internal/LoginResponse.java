package dev.antoniogrillo.primoprogettospring.dto.internal;

import dev.antoniogrillo.primoprogettospring.dto.response.UtenteDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private UtenteDTO utente;
    private String token;
}
