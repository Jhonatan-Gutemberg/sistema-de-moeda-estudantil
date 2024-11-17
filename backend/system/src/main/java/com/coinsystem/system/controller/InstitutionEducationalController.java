package com.coinsystem.system.controller;

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

import java.util.List;

import com.coinsystem.system.DTO.InstitutionEducationDTO;
import com.coinsystem.system.controller.ApiResponse.ApiResponse;
import com.coinsystem.system.controller.ApiResponse.ApiResponseLogin;
import com.coinsystem.system.infra.ITokenService;
import com.coinsystem.system.model.InstitutionEducation;
import com.coinsystem.system.service.interfaces.IInstitutionEducationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/institutionEducational")
@RequiredArgsConstructor
public class InstitutionEducationalController {

    @Autowired
    private IInstitutionEducationService institutionEducationService;
    private final ITokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseLogin<InstitutionEducation>> register(
            @RequestBody @Valid InstitutionEducationDTO institutionEducationDTO) {
        try {
            InstitutionEducation institutionEducation = institutionEducationService.register(institutionEducationDTO);
            String token = this.tokenService.generateToken(institutionEducation);
            ApiResponseLogin<InstitutionEducation> response = new ApiResponseLogin<InstitutionEducation>(true,
                    "User registered succesfully",
                    institutionEducation, token);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponseLogin<InstitutionEducation> errorResponse = new ApiResponseLogin<InstitutionEducation>(false,
                    e.getMessage(), null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<InstitutionEducation>>> getAllInstitutionEducation() {
        try {
            List<InstitutionEducation> institutionEducation = institutionEducationService.getAllInstitutionEducation();
            ApiResponse<List<InstitutionEducation>> response = new ApiResponse<>(true,
                    "All InstitutionEducation fetched successfully", institutionEducation);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<InstitutionEducation>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InstitutionEducation>> getInstitutionEducationById(@PathVariable Long id) {
        try {
            InstitutionEducation institutionEducation = institutionEducationService.getInstitutionEducationById(id);
            if (institutionEducation != null) {
                ApiResponse<InstitutionEducation> response = new ApiResponse<>(true,
                        "InstitutionEducation found successfully",
                        institutionEducation);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<InstitutionEducation> response = new ApiResponse<>(false, "InstitutionEducation not found",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<InstitutionEducation> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<InstitutionEducation>> update(@PathVariable Long id,
            @RequestBody @Valid InstitutionEducationDTO InstitutionEducationDTO) {
        try {
            InstitutionEducation updatedInstitutionEducation = institutionEducationService.update(id,
                    InstitutionEducationDTO);
            if (updatedInstitutionEducation != null) {
                ApiResponse<InstitutionEducation> response = new ApiResponse<>(true,
                        "InstitutionEducation updated successfully",
                        updatedInstitutionEducation);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<InstitutionEducation> response = new ApiResponse<>(false, "InstitutionEducation not found",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<InstitutionEducation> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            boolean isDeleted = institutionEducationService.delete(id);
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
