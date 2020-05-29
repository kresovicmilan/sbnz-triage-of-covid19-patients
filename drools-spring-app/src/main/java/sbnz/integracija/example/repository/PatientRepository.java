package sbnz.integracija.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.integracija.example.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository <Patient, Long>{

    Optional<Patient> findByName(String name);
    
    Optional<Patient> findById(long id);

}
