package com.rowskx.todo.services.serviceImpl;

import com.rowskx.todo.models.Role;
import com.rowskx.todo.models.User;
import com.rowskx.todo.models.enums.ERole;
import com.rowskx.todo.repositories.RoleRepository;
import com.rowskx.todo.repositories.UserRepository;
import com.rowskx.todo.services.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void add(User newUser) {
        log.info("Saving new user {} to the database", newUser.getLogin());
        Role role = getRole(newUser.getRole().getName().toString());
        if (Objects.isNull(role)) {
            log.info("Error saving new user. No such role {}", newUser.getRole().getName());
            return;
        }
        newUser.setRole(role);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting the user from the database");
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Override
    public void update(Long id, User newUser) {
        log.info("Updating the user {} in the database", newUser.getLogin());
        Role role = getRole(newUser.getRole().getName().toString());
        if (Objects.isNull(role)) {
            log.info("Error Updating the user. No such role {}", newUser.getRole().getName());
            return;
        }
        userRepository.findById(id).ifPresent((e) -> {
            e.setLogin(newUser.getLogin());
            e.setName(newUser.getName());
            e.setPassword(passwordEncoder.encode(newUser.getPassword()));
            e.setRole(role);
            userRepository.save(e);
        });
    }

    @Override
    public void updateByLogin(String login, User newUser) {
        log.info("Updating the user in the database by login {}", login);
        User u = userRepository.findByLogin(login);
        Role role = getRole(newUser.getRole().getName().toString());
        if (Objects.isNull(role)) {
            log.info("Error Updating the user. No such role {}", newUser.getRole().getName());
            return;
        }
        u.setLogin(login);
        u.setName(newUser.getName());
        u.setPassword(passwordEncoder.encode(newUser.getPassword()));
        u.setRole(role);
        userRepository.save(u);
    }

    @Override
    public User findById(Long id) {
        log.info("Finding the user in the database by id");
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findByLoginLike(String login) {
        log.info("Getting all the users in the database by login {} (LIKE)", login);
        return userRepository.findByLoginLike(login);
    }

    @Override
    public User findByLogin(String login) {
        log.info("Finding the user in the database by login {}", login);
        Optional<User> u = Optional.ofNullable(userRepository.findByLogin(login));
        return u.orElse(null);
    }

    @Override
    public List<User> findAllByName(String name) {
        log.info("Getting all the users in the database by name {}", name);
        return userRepository.findAllByName(name);
    }

    @Override
    public List<User> findAll() {
        log.info("Getting all the users in the database");
        return userRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void setNewRoleToUser(String login, String roleName) {
        User user = findByLogin(login);
        Optional<Role> role = roleRepository.findByName(ERole.valueOf(roleName));
        if (role.isPresent()) {
            log.info("Setting new role {} to the user {}", roleName, login);
            user.setRole(role.get());
        }
    }

    private Role getRole(String name) {
        Optional<Role> role = roleRepository.findByName(ERole.valueOf(name));
        return role.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (Objects.isNull(user)) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        // Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        // return new
        // org.springframework.security.core.userdetails.User(user.getLogin(),
        // user.getPassword(), authorities);
        return UserDetailsImpl.build(user);
    }
}
