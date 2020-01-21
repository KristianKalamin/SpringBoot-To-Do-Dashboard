package com.kalamin.tododashboard.annotations;

import com.kalamin.tododashboard.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        UserDTO userDTO = (UserDTO) obj;
        return userDTO.getPassword().equals(userDTO.getMatchingPassword());
    }
}
