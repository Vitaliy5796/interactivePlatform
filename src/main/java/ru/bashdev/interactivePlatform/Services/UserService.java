package ru.bashdev.interactivePlatform.Services;

import ru.bashdev.interactivePlatform.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findOne(Long id);
    boolean save(User user);
    void update(Long id, User updateUser);
    void delete(Long id);
}
