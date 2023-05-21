package ru.bashdev.interactivePlatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bashdev.interactivePlatform.models.User;

public interface UserRepositories extends JpaRepository<User, Long> {

    @Query("select distinct u from User u join fetch u.role where u.phone = :phone")
    User findByPhone(String phone);
}
