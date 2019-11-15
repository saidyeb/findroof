package findroof.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import findroof.model.UserTest;


@Repository
public interface UserRepository extends CrudRepository<UserTest, Integer>{
}