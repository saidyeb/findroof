package findroof.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import findroof.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}