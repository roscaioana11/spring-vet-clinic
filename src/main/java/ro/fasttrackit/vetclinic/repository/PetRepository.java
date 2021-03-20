package ro.fasttrackit.vetclinic.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.vetclinic.model.entity.PetEntity;


@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {

}
