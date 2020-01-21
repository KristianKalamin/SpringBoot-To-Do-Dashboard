package com.kalamin.tododashboard.controller;

import com.kalamin.tododashboard.dto.ItemDTO;
import com.kalamin.tododashboard.model.Item;
import com.kalamin.tododashboard.model.User;
import com.kalamin.tododashboard.service.IItemService;
import com.kalamin.tododashboard.service.IUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ItemController {

    @Autowired
    private IItemService itemService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getAllItems(@NotNull Model model) {
        ItemDTO itemDTO = new ItemDTO();
        model.addAttribute("allItems", itemService.getAll(getAuthUser()));
        model.addAttribute("item", itemDTO);
        return "dashboard";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addItem(@Valid @ModelAttribute("item") ItemDTO itemDTO, @NotNull BindingResult result) {
        if (result.hasErrors()) {
            result.rejectValue("itemError", "error", "Can't save");
            return "dashboard";
        } else {
            itemService.saveItem(new Item(itemDTO, getAuthUser()));
            return "redirect:/dashboard";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteItem(@PathVariable String id) {
        itemService.deleteItem(Long.parseLong(id));
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateItem(@Valid @ModelAttribute("item") ItemDTO itemDTO, @NotNull BindingResult result) {
        if (result.hasErrors()) {
            result.rejectValue("itemError", "error", "Can't save");
            return "dashboard";
        } else {
            itemService.updateItem(new Item(itemDTO, getAuthUser()));
            return "redirect:dashboard";
        }
    }

    private User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        return userService.getCurrentUser(currentUserEmail);
    }
}
