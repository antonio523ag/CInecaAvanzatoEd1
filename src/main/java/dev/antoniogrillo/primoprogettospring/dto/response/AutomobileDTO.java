package dev.antoniogrillo.primoprogettospring.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutomobileDTO {
    private long id;
    private String marca;
    private String modello;
    private String colore;
    private UtenteDTO proprietario;
}
