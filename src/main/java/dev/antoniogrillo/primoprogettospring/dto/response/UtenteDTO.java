package dev.antoniogrillo.primoprogettospring.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtenteDTO {
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private int anni;
}
