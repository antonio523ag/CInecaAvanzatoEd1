package dev.antoniogrillo.primoprogettospring.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomPage <T>{
    private int numeroPagina;
    private int pagineTotali;
    private List<T> elementi;
}
