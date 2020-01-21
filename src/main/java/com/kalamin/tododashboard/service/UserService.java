package com.kalamin.tododashboard.service;

import com.kalamin.tododashboard.dto.UserDTO;
import com.kalamin.tododashboard.exceptions.EmailExistsException;
import com.kalamin.tododashboard.model.User;
import com.kalamin.tododashboard.repository.UserRepository;
import com.kalamin.tododashboard.security.UserPrincipal;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;

@Service
@Transactional
public class UserService implements UserDetailsService, IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getCurrentUser(email);
        if (user == null)
            throw new UsernameNotFoundException("User not found");

        return new UserPrincipal(user);
    }

    @Override
    public User registerNewUserAccount(@NotNull UserDTO accountDto) throws EmailExistsException {
        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException("Account with this email already exists "+accountDto.getEmail());
        } else {
            User user = new User(accountDto.getEmail(), accountDto.getName(), accountDto.getPassword(), new ArrayList<>(Collections.singleton("ROLE_USER")));
            return userRepository.saveAndFlush(user);
        }
    }

    private boolean emailExist(String email) {
        User user = userRepository.findUserByEmailEquals(email);
        return user != null;
    }

    @Override
    public User getCurrentUser(String email) {
        return userRepository.findUserByEmailEquals(email);
    }
}
