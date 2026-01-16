package dev.antoniogrillo.primoprogettospring.service.def;

import dev.antoniogrillo.primoprogettospring.dto.response.AutomobileDTO;
import dev.antoniogrillo.primoprogettospring.dto.response.CustomPage;
import dev.antoniogrillo.primoprogettospring.entity.Automobile;

import java.util.List;
import java.util.Optional;

public interface AutomobileService {

    CustomPage<AutomobileDTO> findAll(int numeroPagina);

    Optional<Automobile> getAutomobileById(long idAutomobile);

    void updateAutomobile(Automobile a);

    void salva(Automobile a);

    List<Automobile> findAutomobileByIDProprietario(List<Long> ids);
}
