package dev.antoniogrillo.primoprogettospring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record RegistraUtenteDTO(
        @NotBlank(message = "il nome deve essere un valore inserito diverso da spazio vuoto")
        String nome,
        @NotBlank(message = "il cognome deve essere un valore inserito diverso da spazio vuoto")
        String cognome,
        @NotNull(message = "devi inserire l'email")
        @Email(message = "il campo email deve essere un indirizzo email valido")
        String email,
        @NotBlank(message = "la password deve essere un valore inserito diverso da spazio vuoto")
        String password,
        @NotNull(message = "devi inserire la data di nascita")
        @Past(message = "non puoi essere nato nel futuro")
        LocalDate dataNascita
) {
}
