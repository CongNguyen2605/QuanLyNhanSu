package com.example.demo.controller;

import com.example.demo.dto.DuAnDTO;
import com.example.demo.dto.NhanVienDTO;
import com.example.demo.models.NhanVien;
import com.example.demo.service.DuAnService;
import com.example.demo.service.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/duan")
public class DuAnController {
    @Autowired
    private DuAnService duAnService;
    @Autowired
    private NhanVienService nhanVienService;
    @GetMapping("/view")
    public String viewDuAn(Model model){
        List<DuAnDTO> duAnDTO = duAnService.findDuAn();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("role_admin", isAdmin);
        model.addAttribute("duAn",duAnDTO);
        return "duan-view";
    }
    @GetMapping("/create")
    public String themDuAn(Model model){
        model.addAttribute("duAn", new DuAnDTO());
        return "duan-create";
    }
    @PostMapping("/create")
    public String addDuAn(@Valid @ModelAttribute("duAn") DuAnDTO duAnDTO , BindingResult result){
        if(result.hasErrors()){
            return "duan-create";
        }
        duAnService.addDuAn(duAnDTO);
        return "redirect:/duan/view";
    }
    @GetMapping("/{maDA}/update")
    public String editDuAn(@PathVariable("maDA") Long maDA , Model model){
        DuAnDTO duAnDTO = duAnService.findIdDuAn(maDA);
        model.addAttribute("duAn",duAnDTO);
        return "duan-update";
    }
    @PostMapping("/{maDA}/update")
    public String updateDuAn(@PathVariable("maDA") Long maDA , @ModelAttribute("duAn") DuAnDTO duAnDTO){
        duAnDTO.setMaDA(maDA);
        duAnService.updateDuAn(duAnDTO);
        return "redirect:/duan/view";
    }
    @GetMapping("/{maDA}/delete")
    public String deleteDuAn(@PathVariable("maDA") Long maDuAn){
        duAnService.deleteDuAn(maDuAn);
        return "redirect:/duan/view";
    }
    @ModelAttribute("nhanvien")
    public List<NhanVienDTO> nhanVienDTOList(){
        return nhanVienService.findNhanVien();
    }

    @GetMapping("/{maDA}/addDANV")
    public String addDANV(@PathVariable("maDA") Long maDA, Model model) {
        DuAnDTO duAnDTO = duAnService.findIdDuAn(maDA);
        List<NhanVienDTO> nhanVienList = nhanVienService.findNhanVien();

        // Add attributes to model
        model.addAttribute("maDA", duAnDTO);
        model.addAttribute("nhanVienList", nhanVienList);

        return "nhanvien-duan-create"; // Thymeleaf template name
    }
    @PostMapping("/{maDA}/addDANV")
    public String themDANV(@PathVariable("maDA") Long maDA, @ModelAttribute("duAn") DuAnDTO duAnDTO) {
        duAnDTO.setMaDA(maDA);
        duAnService.addNhanVienToDuAn(duAnDTO);
        return "redirect:/duan/view"; // Redirect to the project details page
    }
    @GetMapping("/{maDA}/viewDANV")
    public String viewDANV(@PathVariable("maDA") Long maDA , Model model){
        Set<NhanVien> nhanVienSet = duAnService.viewAllNhanVienInDuAn(maDA);
        model.addAttribute("duAn",nhanVienSet);
        return "nhanvien-duan-view";
    }
    @GetMapping("/{maDA}/delete/{maNV}")
    public String deleteNhanVienFromDuAn(@PathVariable Long maDA, @PathVariable Long maNV, Model model) {
        duAnService.deleteNhanVienFromDuAn(maDA, maNV);
        return "redirect:/duan/" + maDA + "/viewDANV";
    }

}
