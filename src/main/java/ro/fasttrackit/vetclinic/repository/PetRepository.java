package ro.fasttrackit.vetclinic.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.vetclinic.model.Pet;
import ro.fasttrackit.vetclinic.model.Species;
import ro.fasttrackit.vetclinic.model.entity.PetEntity;

import java.util.Collection;
import java.util.List;


@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {

    //Collection<Pet> findAll(String ceva);

}
