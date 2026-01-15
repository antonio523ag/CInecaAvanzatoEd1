package dev.antoniogrillo.primoprogettospring.service.impl;

import dev.antoniogrillo.primoprogettospring.dto.request.Param;
import dev.antoniogrillo.primoprogettospring.dto.response.AutomobileDTO;
import dev.antoniogrillo.primoprogettospring.dto.response.CustomPage;
import dev.antoniogrillo.primoprogettospring.entity.Automobile;
import dev.antoniogrillo.primoprogettospring.mapper.AutomobileMapper;
import dev.antoniogrillo.primoprogettospring.mapper.CustomPageMapper;
import dev.antoniogrillo.primoprogettospring.repository.AutomobileRepository;
import dev.antoniogrillo.primoprogettospring.repository.CriteriaAutomobileRepository;
import dev.antoniogrillo.primoprogettospring.service.def.AutomobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutomobileServiceImpl implements AutomobileService {

    private final AutomobileRepository repo;
    private final AutomobileMapper mapper;
    private final CustomPageMapper customPageMapper;
    private final CriteriaAutomobileRepository criteriaRepo;

    @Override
    public CustomPage<AutomobileDTO> findAll(int numeroPagina) {
        Sort s=Sort.by("marca").ascending().and(Sort.by("modello").ascending());
        Pageable pageable= PageRequest.of(numeroPagina,25,s);
        Page<Automobile> page=repo.findAll(pageable);
        List<AutomobileDTO> l=mapper.toAutomobileDTO(page.getContent());

        return customPageMapper.toCustomPage(l,numeroPagina,page.getTotalPages());
    }

    public List<Automobile> findByNomeUtenteAndMarca(String nome,String marca){
        Param p1=new Param("automobile","marca",marca);
        Param p2=new Param("utente","nome",nome);
        return criteriaRepo.findAll(List.of(p1,p2));
    }

    public List<Automobile> findByIdUtenteAndModello(long id,String modello){
        Param p1=new Param("automobile","modello",modello);
        Param p2=new Param("utente","id",String.valueOf(id));
        return criteriaRepo.findAll(List.of(p1,p2));
    }
}
