package com.kalamin.tododashboard.controller;

import com.kalamin.tododashboard.dto.UserDTO;
import com.kalamin.tododashboard.exceptions.EmailExistsException;
import com.kalamin.tododashboard.model.User;
import com.kalamin.tododashboard.service.IUserService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

   @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, @NotNull Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDTO accountDto, HttpServletRequest request, @NotNull BindingResult result) {
        User user = new User();
        if (!result.hasErrors()) {
            user = createUserAccount(accountDto);
        }

        if (user == null) {
            result.rejectValue("userError", "error", "User already exits!");
        }

        if (result.hasErrors()) {
            return new ModelAndView("register", "user", accountDto);
        } else {
            try {
                request.login(Objects.requireNonNull(user).getEmail(), user.getPassword());
                return new ModelAndView("redirect:/dashboard", HttpStatus.PERMANENT_REDIRECT);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("register", "user", accountDto);
    }

    @Nullable
    private User createUserAccount(UserDTO accountDto) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

}
