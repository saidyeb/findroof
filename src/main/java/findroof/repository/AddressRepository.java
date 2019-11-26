package findroof.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import findroof.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
}
