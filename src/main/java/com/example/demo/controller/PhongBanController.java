package com.example.demo.controller;

import com.example.demo.dto.PhongBanDTO;
import com.example.demo.models.PhongBan;
import com.example.demo.service.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("phongban")
public class PhongBanController {
    @Autowired
    private PhongBanService phongBanService;
    @GetMapping("/view")
    public String viewPhongBan(Model model) {
        List<PhongBanDTO> phongBanDTOS = phongBanService.findPhongBan();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("role_admin", isAdmin);
        model.addAttribute("phongBan", phongBanDTOS);
        return "phongban-view"; // Ensure this matches your Thymeleaf template name

    }



    @GetMapping("/create")
    public String themPhongBan(Model model){
        model.addAttribute("phongBan",new PhongBanDTO());
        return "phongban-create";
    }
    @PostMapping("/create")
    public String addPhongBan(@Valid @ModelAttribute("phongBan") PhongBanDTO phongBanDTO, BindingResult result){
        if(result.hasErrors()){
            return "phongban-create";
        }
        phongBanService.addPhongBan(phongBanDTO);
        return "redirect:/phongban/view";
    }
    @GetMapping("/{maPB}/update")
    public String editPhongBan(@PathVariable("maPB") Long maPB ,Model model){
        PhongBanDTO phongBanDTO = phongBanService.findID(maPB);
        model.addAttribute("phongBan",phongBanDTO);
        return "phongban-update";
    }
    @PostMapping("/{maPB}/update")
    public String updatePhongBan(@PathVariable("maPB") Long maPB ,@ModelAttribute("phongBan") PhongBanDTO phongBanDTO){
        phongBanDTO.setMaPB(maPB);
        phongBanService.updatePB(phongBanDTO);
        return "redirect:/phongban/view";
    }
    @GetMapping("/{maPB}/delete")
    public String deletePhongBan(@PathVariable("maPB") Long maPB){
        phongBanService.deletePhongBan(maPB);
        return "redirect:/phongban/view";
    }
    @GetMapping("/search")
    public String searchPhongBan(@RequestParam(value = "query") String query , Model model){
        List<PhongBanDTO> phongBanDTOS = phongBanService.searchPhongBan(query);
        model.addAttribute("phongBan" , phongBanDTOS);
        return "phongban-view";
    }
}
