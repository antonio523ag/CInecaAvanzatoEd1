package dev.antoniogrillo.primoprogettospring.mapper;

import dev.antoniogrillo.primoprogettospring.dto.response.CustomPage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomPageMapper {

    public <T> CustomPage<T> toCustomPage(List<T> iterable, int pageNumber, int pagineTotali){
        CustomPage<T> cp=new CustomPage<>();
        cp.setNumeroPagina(pageNumber);
        cp.setPagineTotali(pagineTotali);
        cp.setElementi(iterable);
        return cp;
    }
}
