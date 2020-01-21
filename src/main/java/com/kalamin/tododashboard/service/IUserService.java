package com.kalamin.tododashboard.service;

import com.kalamin.tododashboard.dto.UserDTO;
import com.kalamin.tododashboard.exceptions.EmailExistsException;
import com.kalamin.tododashboard.model.User;

public interface IUserService {
    User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException;
    User getCurrentUser(String email);
}
