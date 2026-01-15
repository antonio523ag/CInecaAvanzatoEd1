package dev.antoniogrillo.primoprogettospring.service.def;

import dev.antoniogrillo.primoprogettospring.dto.response.AutomobileDTO;
import dev.antoniogrillo.primoprogettospring.dto.response.CustomPage;

import java.util.List;

public interface AutomobileService {

    CustomPage<AutomobileDTO> findAll(int numeroPagina);
}
