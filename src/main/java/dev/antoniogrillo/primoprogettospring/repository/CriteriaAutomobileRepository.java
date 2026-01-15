package dev.antoniogrillo.primoprogettospring.repository;

import dev.antoniogrillo.primoprogettospring.dto.request.Param;
import dev.antoniogrillo.primoprogettospring.entity.Automobile;
import dev.antoniogrillo.primoprogettospring.entity.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CriteriaAutomobileRepository {

    private final EntityManager manager;

    public List<Automobile> findAllByMarca(String marca){
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaQuery<Automobile> query=builder.createQuery(Automobile.class);
        Root<Automobile> root=query.from(Automobile.class);
        Predicate predicate=builder.like(root.get("marca"),"%"+marca+"%");
        query.where(predicate);
        return manager.createQuery(query).getResultList();
    }
    public List<Automobile> findAllByMarcaAndNotModello(String marca,String modello){
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaQuery<Automobile> query=builder.createQuery(Automobile.class);
        Root<Automobile> root=query.from(Automobile.class);
        Predicate predicate=builder.like(root.get("marca"),"%"+marca+"%");
        Predicate predicate2=builder.like(root.get("modello"),"%"+modello+"%");
        Predicate finale=builder.and(predicate,builder.not(predicate2));
        query.where(finale);
        return manager.createQuery(query).getResultList();
    }

    public List<Automobile> findAllByProprietario(String nome,String cognome){
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaQuery<Automobile> query=builder.createQuery(Automobile.class);
        Root<Automobile> root=query.from(Automobile.class);
        query.orderBy(builder.asc(root.get("modello")),
                builder.asc(root.get("marca")));
        Join<Automobile, Utente> join=root.join("proprietario");
        Predicate pNome=builder.equal(join.get("nome"),nome);
        Predicate pCognome=builder.equal(join.get("cognome"),cognome);
        Predicate finale=builder.and(pNome,pCognome);
        query.where(finale);
        TypedQuery<Automobile> t=manager.createQuery(query);
        t.setFirstResult(10); //parti dal valore in posizione n
        t.setMaxResults(10); //limit della query
        return t.getResultList();

    }

    public int contaAutoPerEmail(String email){
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaQuery<Long> query=builder.createQuery(Long.class);
        Root<Automobile> root=query.from(Automobile.class);
        Join<Automobile, Utente> join=root.join("proprietario");
        Predicate predicate =builder.equal(join.get("email"),email);
        query.select(builder.count(root)).where(predicate);
        return manager.createQuery(query).getSingleResult().intValue();
    }

    public List<Automobile> findAll(List<Param> params){
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaQuery<Automobile> query = builder.createQuery(Automobile.class);
        Root<Automobile> root=query.from(Automobile.class);
        Join<Automobile, Utente> join=root.join("proprietario");
        List<Predicate> p=new ArrayList<>();
        for(Param param:params){
            Predicate p1=switch (param.classe().toLowerCase()){
                case "automobile"->builder.equal(root.get(param.parametro()),param.valore());
                case "utente"->builder.equal(join.get(param.parametro()),param.valore());
                default->null;
            };
            p.add(p1);
        }
        query.where(builder.and(p.toArray(new Predicate[0])));
        return manager.createQuery(query).getResultList();

    }
}
