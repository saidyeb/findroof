package findroof.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import findroof.model.Address;
import findroof.model.Person;
import findroof.model.Possession;

@Repository
public interface PossessionRepository extends CrudRepository<Possession, Integer> {
	List<Possession> findByHouseOwner(Person houseOwner);
	List<Possession> findByHouseHolders(Person houseHolder);
	List<Possession> findByName(String name);
	List<Possession> findByAddress(Address address);
	List<Possession> findByType(String type);
	List<Possession> findBySurface(int surface);
}
