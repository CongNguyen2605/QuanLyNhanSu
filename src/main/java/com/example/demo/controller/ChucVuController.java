package com.example.demo.controller;

import com.example.demo.dto.ChucVuDTO;
import com.example.demo.models.ChucVu;
import com.example.demo.service.ChucVuService;
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
@RequestMapping("chucvu")
public class ChucVuController {
    @Autowired
    private ChucVuService chucVuService;
    @GetMapping("/view")
    public String viewChucVu(Model model){
        List<ChucVuDTO> chucVuDTOS = chucVuService.findChucVu();
        model.addAttribute("chucVu",chucVuDTOS);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("role_admin", isAdmin);
        return "chucvu-view";
    }
    @GetMapping("/create")
    public String themChucVu(Model model){
        model.addAttribute("chucVu", new ChucVuDTO());
        return "chucvu-create";
    }
    @PostMapping("/create")
    public String addChucVu(@Valid @ModelAttribute("chucVu") ChucVuDTO chucVuDTO , BindingResult result){
        if(result.hasErrors()){
            return "chucvu-create";
        }
        chucVuService.addChucVu(chucVuDTO);
        return "redirect:/chucvu/view";
    }
    @GetMapping("/{maChucVu}/update")
    public String editChucVu(@PathVariable("maChucVu") Long maChucVu , Model model){
        ChucVuDTO chucVuDTO = chucVuService.findID(maChucVu);
        model.addAttribute("chucVu",chucVuDTO);
        return "chucvu-update";
    }
    @PostMapping("/{maChucVu}/update")
    public String updateChucVu(@PathVariable("maChucVu") Long maChucVu ,@ModelAttribute("chucVu") ChucVuDTO chucVuDTO){
        chucVuDTO.setMaChucVu(maChucVu);
        chucVuService.updateChucVu(chucVuDTO);
        return "redirect:/chucvu/view";
    }
    @GetMapping("/{maChucVu}/delete")
    public String deleteChucVu(@PathVariable("maChucVu") Long maChucVu){
        chucVuService.deleteChucVu(maChucVu);
        return "redirect:/chucvu/view";
    }
}
