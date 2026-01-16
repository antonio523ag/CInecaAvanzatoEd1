package dev.antoniogrillo.primoprogettospring.repository;

import dev.antoniogrillo.primoprogettospring.entity.Automobile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutomobileRepository extends JpaRepository<Automobile, Long> {

    @Query(nativeQuery = true, value = "select * from automobile where utente_fk = :id")
    List<Automobile> findByProprietarioIdSQL(long id);

    @Query("select a from Automobile a where a.proprietario.id = :id")
    List<Automobile> findByProprietarioIdJPQL(long id);

    List<Automobile> findByProprietario_Id(long id);

    Page<Automobile> findByProprietario_Id(long id, Pageable pageable);

    List<Automobile> findAllByProprietario_IdIn(List<Long> ids);

}
