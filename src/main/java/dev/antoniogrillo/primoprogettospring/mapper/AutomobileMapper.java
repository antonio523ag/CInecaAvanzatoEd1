package dev.antoniogrillo.primoprogettospring.mapper;

import dev.antoniogrillo.primoprogettospring.dto.response.AutomobileDTO;
import dev.antoniogrillo.primoprogettospring.entity.Automobile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AutomobileMapper {

    private final UtenteMapper utenteMapper;

    public AutomobileDTO toAutomobileDTO(Automobile a){
        AutomobileDTO dto=new AutomobileDTO();
        dto.setId(a.getId());
        dto.setMarca(a.getMarca());
        dto.setModello(a.getModello());
        dto.setColore(a.getColore());
        dto.setProprietario(utenteMapper.ToUtenteDTO(a.getProprietario()));
        return dto;
    }

    public List<AutomobileDTO> toAutomobileDTO(List<Automobile> lista){
        return lista.stream().map(this::toAutomobileDTO).toList();
    }
}
