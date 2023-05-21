package ru.bashdev.interactivePlatform.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bashdev.interactivePlatform.models.User;
import ru.bashdev.interactivePlatform.repositories.UserRepositories;
import ru.bashdev.interactivePlatform.util.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public UserServiceImpl(UserRepositories userRepositories, PasswordEncoder passwordEncoder) {
        this.userRepositories = userRepositories;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepositories.findAll();
    }

    @Override
    public User findOne(Long id) {
        Optional<User> foundUser = userRepositories.findById(id);
        return foundUser.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean save(User user) {
        User userDb = userRepositories.findByPhone(user.getPhone());

        if (userDb != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepositories.save(user);
        return true;
    }


    @Override
    public void update(Long id, User updateUser) {
        updateUser.setLeaserId(id);
        updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        userRepositories.save(updateUser);
    }

    @Override
    public void delete(Long id) {
        userRepositories.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepositories.findByPhone(phone);

        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }
}

