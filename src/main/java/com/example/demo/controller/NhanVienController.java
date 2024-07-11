package com.example.demo.controller;

import com.example.demo.dto.ChucVuDTO;
import com.example.demo.dto.DuAnDTO;
import com.example.demo.dto.NhanVienDTO;
import com.example.demo.dto.PhongBanDTO;
import com.example.demo.models.NhanVien;
import com.example.demo.service.ChucVuService;
import com.example.demo.service.DuAnService;
import com.example.demo.service.NhanVienService;
import com.example.demo.service.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("nhanvien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private ChucVuService chucVuService;

    @Autowired
    private PhongBanService phongBanService;
    @Autowired
    private DuAnService duAnService;

    @GetMapping("/view")
//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String NhanVienView(@RequestParam(required = false) String keyWord , Model model){
        List<NhanVienDTO> nhanVienDTOS = new ArrayList<>();
        if(keyWord==null){
        nhanVienDTOS = nhanVienService.findNhanVien();
        }
        else {
        nhanVienDTOS = nhanVienService.searchNhanVien(keyWord);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("role_admin", isAdmin);

        model.addAttribute("nhanvien" ,nhanVienDTOS);
        return "nhanvien-view";
    }
    @GetMapping("/create")
//    @Secured("ROLE_ADMIN")
    public String ThemNhanVien(Model model){

        model.addAttribute("nhanvien", new NhanVienDTO());

        return "nhanvien-create";
    }
    @PostMapping("/create")
//    @Secured("ROLE_ADMIN")
    public String addNhanVien(@Valid @ModelAttribute("nhanvien") NhanVienDTO nhanVienDTO , BindingResult result){
        if(result.hasErrors()){
            return "nhanvien-create";
        }
        nhanVienService.addNhanVien(nhanVienDTO);
        return "redirect:/nhanvien/view";
    }
    @GetMapping("/{maNV}/update")
//    @Secured("ROLE_ADMIN")
    public String editNhanVien(@PathVariable("maNV") Long maNV ,Model model){
        NhanVienDTO nhanVienDTO = nhanVienService.findID(maNV);
        model.addAttribute("nhanvien" , nhanVienDTO);

        return "nhanvien-update";
    }
    @PostMapping("/{maNV}/update")
//    @Secured("ROLE_ADMIN")
    public String updateNhanVien(@PathVariable("maNV") Long maNV , @ModelAttribute("nhanvien") NhanVienDTO nhanVienDTO){
        nhanVienDTO.setMaNV(maNV);
        nhanVienService.updateNhanVien(nhanVienDTO);
        return "redirect:/nhanvien/view";
    }
    @GetMapping("/{maNV}/delete")
//    @Secured("ROLE_ADMIN")
    public String deleteNhanVien(@PathVariable("maNV") Long maNV){
        nhanVienService.deleteNhanVien(maNV);
        return "redirect:/nhanvien/view";
    }
    @GetMapping("/search")
    public String searchNhanViens(@RequestParam(value = "query") String query , Model model){
        List<NhanVienDTO> nhanVienDTOS = nhanVienService.searchNhanVien(query);
        model.addAttribute("nhanvien" , nhanVienDTOS);
        return "nhanvien-view";
    }

    @ModelAttribute("phongBan")
    public List<PhongBanDTO> phongBanDTOList(){
        return phongBanService.findPhongBan();
    }
    @ModelAttribute("chucVu")
    public List<ChucVuDTO> chucVuDTOList(){
        return chucVuService.findChucVu();
    }

}
