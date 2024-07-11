package com.example.demo.controller;

import com.example.demo.dto.ChamCongDTO;
import com.example.demo.dto.ChamCongDTOList;
import com.example.demo.dto.NhanVienDTO;
import com.example.demo.models.ChamCong;
import com.example.demo.service.ChamCongService;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chamcong")
public class ChamCongController {

    @Autowired
    private ChamCongService chamCongService;

    @Autowired
    private NhanVienService nhanVienService;

    // Mapping for viewing ChamCong
    @GetMapping("/view")
    public String viewChamCong(Model model, @RequestParam(required = false) String ngay) {
        List<NhanVienDTO> nhanvienList = nhanVienService.findNhanVien();
        ChamCongDTOList chamCongDTOList = new ChamCongDTOList();

        LocalDate currentDate = ngay != null ? LocalDate.parse(ngay) : LocalDate.now();
        List<ChamCongDTO> chamCongDTOs = new ArrayList<>();

        for (NhanVienDTO nhanVien : nhanvienList) {
            ChamCongDTO chamCong = chamCongService.findByNhanVienAndNgay(nhanVien.getMaNV(), currentDate.toString());
            ChamCongDTO chamCongDTO = new ChamCongDTO();
            chamCongDTO.setMaNV(nhanVien.getMaNV());
            if (chamCong != null) {
                chamCongDTO.setMaChamCong(chamCong.getMaChamCong());
                chamCongDTO.setTrangThai(chamCong.getTrangThai());
            }
            chamCongDTOs.add(chamCongDTO);
        }

        chamCongDTOList.setChamCongDTOs(chamCongDTOs);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("role_admin", isAdmin);

        model.addAttribute("nhanvienList", nhanvienList);
        model.addAttribute("chamCongDTOList", chamCongDTOList);
        model.addAttribute("currentDate", currentDate);

        return "chamcong-view";
    }

    @PostMapping("/update")
    public String updateChamCong(@ModelAttribute("chamCongDTOList") ChamCongDTOList chamCongDTOList,
                                 @RequestParam("ngay") String ngay) {
        chamCongService.updateChamCongList(chamCongDTOList.getChamCongDTOs(), ngay);
        return "redirect:/chamcong/view?ngay=" + ngay; // Redirect with the updated date
    }

}
