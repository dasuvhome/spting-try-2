package ru.suvorov.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.suvorov.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);
}
