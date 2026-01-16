package dev.antoniogrillo.primoprogettospring.graphql;

import dev.antoniogrillo.primoprogettospring.dto.graphql.RegistraUtenteDTO;
import dev.antoniogrillo.primoprogettospring.entity.Automobile;
import dev.antoniogrillo.primoprogettospring.entity.Utente;
import dev.antoniogrillo.primoprogettospring.exception.GraphQLException;
import dev.antoniogrillo.primoprogettospring.facade.def.GraphFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class GraphController {
    private final GraphFacade facade;

    @QueryMapping
    public Utente findUtenteById(@Argument Long id) {
        return facade.getUtenteById(id);
    }



    @PreAuthorize( "hasRole('ADMIN')")
    @QueryMapping
    public Automobile findAutomobileByID(@Argument Long id){
        return facade.getAutomobileById(id).orElseThrow(()->new GraphQLException("automobile non trovata", ErrorType.BAD_REQUEST));
    }

    @SchemaMapping
    public Utente getUtente(Automobile a){
        return facade.getProprietario(a);
    }

    @SchemaMapping
    public List<Automobile> getAutomobili(Utente u){
        return facade.getAutomobili(u);
    }

    @MutationMapping
    public Long registraUtente(@Argument RegistraUtenteDTO input){
        long id=facade.registraUtente(input);
        return id;
    }

    @BatchMapping
    public Map<Utente,List<Automobile>> getAutomobiliPerUtente(@Argument List<Long> ids){
        List<Automobile> automobili=facade.findAutomobileByIDProprietario(ids);
        return automobili.stream()
                .collect(Collectors.groupingBy(Automobile::getProprietario));
    }


}
