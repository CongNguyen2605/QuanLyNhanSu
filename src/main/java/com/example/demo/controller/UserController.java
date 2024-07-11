package com.example.demo.controller;

import com.example.demo.dto.NhanVienDTO;
import com.example.demo.dto.RoleDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.NhanVienService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private RoleService roleService;
    @GetMapping("/view")
    public String viewUser(Model model){
        List<UserDTO> userDTOList = userService.findAllUser();
        model.addAttribute("user",userDTOList);
        return "user-view";
    }

    @ModelAttribute("nhanvien")
    public List<NhanVienDTO> nhanVienDTOList(){
        return  nhanVienService.findNhanVien();
    }
    @ModelAttribute("role")
    public List<RoleDTO> roleDTOList(){
        return  roleService.findAllRole();
    }
    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("nhanvien", nhanVienService.findNhanVien());
        model.addAttribute("role", roleService.findAllRole());
        return "user-create";
    }

    @PostMapping("/create")
    public String addUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("nhanvien", nhanVienService.findNhanVien());
            model.addAttribute("role", roleService.findAllRole());
            return "user-create";
        }

        try {
            userService.addUser(userDTO);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("nhanvien", nhanVienService.findNhanVien());
            model.addAttribute("role", roleService.findAllRole());
            return "user-create";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Có lỗi xảy ra, vui lòng thử lại");
            model.addAttribute("nhanvien", nhanVienService.findNhanVien());
            model.addAttribute("role", roleService.findAllRole());
            return "user-create";
        }

        return "redirect:/user/view";
    }
    @GetMapping("/{id}/update")
    public String editUser(@PathVariable("id") Long id, Model model){
        UserDTO userDTO = userService.findIdUser(id);
        model.addAttribute("user",userDTO);
        return "user-update";
    }
    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id ,@ModelAttribute("user") UserDTO userDTO, Model model){

        try {
            userDTO.setId(id);
            userService.updateUser(userDTO);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Username đã tồn tại")) {
                model.addAttribute("errorMessage", "Username đã tồn tại, vui lòng nhập username khác");
            } else {
                model.addAttribute("errorMessage", "Có lỗi xảy ra, vui lòng thử lại");
            }
            return "user-update";
        }
        return "redirect:/user/view";
    }
    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id ){
        userService.deleteUser(id);
        return "redirect:/user/view";
    }
}
