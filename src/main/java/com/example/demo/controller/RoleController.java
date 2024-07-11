package com.example.demo.controller;

import com.example.demo.dto.RoleDTO;
import com.example.demo.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/view")
    public String viewRole(Model model){
        List<RoleDTO> roleDTOList = roleService.findAllRole();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("role_admin", isAdmin);
        model.addAttribute("role",roleDTOList);
        return "role-view";
    }
    @GetMapping("/create")
    public String ThemRole(Model model){
        model.addAttribute("role",new RoleDTO());
        return "role-create";
    }
    @PostMapping("/create")
    public String addRole(@Valid @ModelAttribute("role") RoleDTO roleDTO , BindingResult result){
        if(result.hasErrors()){
            return "role-create";
        }
        roleService.addRole(roleDTO);
        return "redirect:/role/view";
    }
    @GetMapping("/{id}/update")
    public String editRole(@PathVariable("id") Long id,Model model){
        RoleDTO roleDTO = roleService.findIdRole(id);
        model.addAttribute("role",roleDTO);
        return "role-update";


    }
    @PostMapping("/{id}/update")
    public String updateRole(@PathVariable("id") Long id,@ModelAttribute("role") RoleDTO roleDTO){
        roleDTO.setId(id);
        roleService.updateRole(roleDTO);
        return "redirect:/role/view";

    }
    @GetMapping("/{id}/delete")
    public String deleteRole(@PathVariable("id") Long id){
       roleService.deleteRole(id);
        return "redirect:/role/view";
    }
}
