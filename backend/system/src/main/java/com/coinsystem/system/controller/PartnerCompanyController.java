package com.coinsystem.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coinsystem.system.DTO.PartnerCompanyDTO;
import com.coinsystem.system.controller.ApiResponse.ApiResponse;
import com.coinsystem.system.controller.ApiResponse.ApiResponseLogin;
import com.coinsystem.system.infra.ITokenService;
import com.coinsystem.system.mappers.PartnerCompanyMapper;
import com.coinsystem.system.model.PartnerCompany;
import com.coinsystem.system.service.interfaces.IPartnerCompanyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/partnercompany")
@RequiredArgsConstructor
public class PartnerCompanyController {

    @Autowired
    private IPartnerCompanyService partnerCompanyService;
    private final ITokenService tokenService;

    @PostMapping("/register")
    // refatorar aqui pra tirar o institution education e colocar um dto de resposta
    public ResponseEntity<ApiResponseLogin<PartnerCompanyDTO>> register(
            @RequestBody @Valid PartnerCompanyDTO partnerCompanyDTO) {
        try {
            PartnerCompany partnerCompany = partnerCompanyService.register(partnerCompanyDTO);
            PartnerCompanyDTO responsePartnerCompanyDTO = PartnerCompanyMapper.partnerCompanyToPartnerCompanyDTO(partnerCompany);
            String token = this.tokenService.generateToken(partnerCompany);
            ApiResponseLogin<PartnerCompanyDTO> response = new ApiResponseLogin<PartnerCompanyDTO>(true, "User registered succesfully",
                responsePartnerCompanyDTO,token);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponseLogin<PartnerCompanyDTO> errorResponse = new ApiResponseLogin<PartnerCompanyDTO>(false, e.getMessage(), null,null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PartnerCompanyDTO>>> getAllPartnerCompany() {
        try {
            List<PartnerCompany> partnerCompany = partnerCompanyService.getAllPartnerCompany();
            List<PartnerCompanyDTO> partnerCompanyDTOs = new ArrayList<>();
            
            for (PartnerCompany partnerCompany2 : partnerCompany) {
                partnerCompanyDTOs.add(PartnerCompanyMapper.partnerCompanyToPartnerCompanyDTO(partnerCompany2));
            }

            ApiResponse<List<PartnerCompanyDTO>> response = new ApiResponse<>(true,
                    "All PartnerCompany fetched successfully", partnerCompanyDTOs);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<PartnerCompanyDTO>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PartnerCompanyDTO>> getPartnerCompanyById(@PathVariable Long id) {
        try {
            PartnerCompany partnerCompany = partnerCompanyService.getPartnerCompanyById(id);
            if (partnerCompany != null) {
                PartnerCompanyDTO partnerCompanyDTO = PartnerCompanyMapper.partnerCompanyToPartnerCompanyDTO(partnerCompany);
                ApiResponse<PartnerCompanyDTO> response = new ApiResponse<>(true, "PartnerCompany found successfully",
                partnerCompanyDTO);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<PartnerCompanyDTO> response = new ApiResponse<>(false, "PartnerCompany not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<PartnerCompanyDTO> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<PartnerCompanyDTO>> update(@PathVariable Long id,
            @RequestBody @Valid PartnerCompanyDTO partnerCompanyDTO) {
        try {
            PartnerCompany updatedPartnerCompany = partnerCompanyService.update(id, partnerCompanyDTO);
            if (updatedPartnerCompany != null) {
                PartnerCompanyDTO updatedPartnerCompanyDTO = PartnerCompanyMapper.partnerCompanyToPartnerCompanyDTO(updatedPartnerCompany);

                ApiResponse<PartnerCompanyDTO> response = new ApiResponse<>(true, "PartnerCompany updated successfully",
                updatedPartnerCompanyDTO);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<PartnerCompanyDTO> response = new ApiResponse<>(false, "PartnerCompany not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<PartnerCompanyDTO> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            boolean isDeleted = partnerCompanyService.delete(id);
            if (isDeleted) {
                ApiResponse<Void> response = new ApiResponse<>(true, "Partner Company deleted successfully", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(false, "Partner Company not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
