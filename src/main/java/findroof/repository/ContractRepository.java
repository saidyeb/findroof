package findroof.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import findroof.model.Contract;
import findroof.model.Person;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Integer> {
}
