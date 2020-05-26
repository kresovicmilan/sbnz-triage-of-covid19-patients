package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.integracija.example.model.AppUser;

@Repository
public interface UserRepository extends JpaRepository <AppUser, Long>{

    AppUser findByUsername(String username);

}