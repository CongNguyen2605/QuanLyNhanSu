package com.example.demo.controller;

import com.example.demo.dto.ChucVuDTO;
import com.example.demo.dto.LuongDTO;
import com.example.demo.dto.NhanVienDTO;
import com.example.demo.service.LuongService;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("luong")
public class LuongController {
    @Autowired
    private LuongService luongService;
    @Autowired
    private NhanVienService nhanVienService;
    @GetMapping("/view")
    public String viewLuong(Model model){
        List<LuongDTO> luongDTOS = luongService.findAllLuong();
        model.addAttribute("luong",luongDTOS);
        return "luong-view";
    }
    @ModelAttribute("nhanvien")
    public List<NhanVienDTO> nhanVienDTOList(){
        return nhanVienService.findNhanVien();
    }
    @GetMapping("/{maNV}/update")
    public String editLuong(@PathVariable("maNV") Long maNV, Model model){
//
        NhanVienDTO nhanVienDTO = nhanVienService.findID(maNV);
        LuongDTO luongDTO = luongService.findLuongByMaNV(maNV);
        if (luongDTO == null) {
            luongDTO = new LuongDTO();
            luongDTO.setMaNV(maNV);
        }
        model.addAttribute("nhanvien", nhanVienDTO);
        model.addAttribute("luong", luongDTO);
        return "luong-update";
    }
    @PostMapping("/{maNV}/update")
    public String updateLuong(@PathVariable("maNV") Long maNV,@ModelAttribute("luong") LuongDTO luongDTO){
        luongDTO.setMaNV(maNV);

        luongService.addLuong(luongDTO);
        return "redirect:/luong/view";
    }
}