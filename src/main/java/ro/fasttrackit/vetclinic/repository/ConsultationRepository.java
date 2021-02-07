package ro.fasttrackit.vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.vetclinic.model.entity.ConsultationEntity;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationEntity, Long> {
}
