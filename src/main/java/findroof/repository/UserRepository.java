package findroof.repository;

import org.springframework.data.repository.CrudRepository;

import findroof.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String userame);
}
