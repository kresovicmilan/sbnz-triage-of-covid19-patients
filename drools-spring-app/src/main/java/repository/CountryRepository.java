package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Country;
import model.User;


@Repository
public interface CountryRepository extends JpaRepository <Country, Long>{

    Country findByCountryName(String countryName);

}
