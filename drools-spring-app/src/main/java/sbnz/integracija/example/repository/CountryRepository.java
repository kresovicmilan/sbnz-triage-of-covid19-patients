package sbnz.integracija.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.integracija.example.model.Country;
import sbnz.integracija.example.model.AppUser;


@Repository
public interface CountryRepository extends JpaRepository <Country, Long>{

    Country findByCountryName(String countryName);

}
