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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import org.springframework.http.HttpHeaders;

import org.springframework.web.multipart.MultipartFile;

import com.coinsystem.system.DTO.VantageDTO;
import com.coinsystem.system.controller.ApiResponse.ApiResponse;
import com.coinsystem.system.model.Vantage;
import com.coinsystem.system.service.interfaces.IVantageService;

import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/vantage")
public class VantageController {

    @Autowired
    private IVantageService vantageService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Vantage>> register(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("value") int value,
            @RequestParam("quantity") int quantity,
            @RequestParam("id_partnerCompany") Long idPartnerCompany,
            @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        try {
            // Crie o VantageDTO manualmente
            VantageDTO vantageDTO = new VantageDTO(name, description, value, quantity, idPartnerCompany);

            // Chame o serviço para registrar
            Vantage vantage = vantageService.register(vantageDTO, imageFile);
            ApiResponse<Vantage> response = new ApiResponse<>(true, "User registered successfully", vantage);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Vantage> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Vantage>>> getAllVantage() {
        try {
            List<Vantage> vantage = vantageService.getAllVantage();
            ApiResponse<List<Vantage>> response = new ApiResponse<>(true, "All Vantage fetched successfully", vantage);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Vantage>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/imagem/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        try {
            // Busca o Vantage pelo ID
            Vantage vantage = vantageService.getVantageById(id); // Método que você precisa implementar
            if (vantage != null && vantage.getImage() != null) {
                byte[] imagemBytes = vantage.getImage(); // Obtem a imagem em bytes
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG); // Altere para o tipo correto se necessário
                return new ResponseEntity<>(imagemBytes, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Vantage>> getVantageById(@PathVariable Long id) {
        try {
            Vantage vantage = vantageService.getVantageById(id);
            if (vantage != null) {
                ApiResponse<Vantage> response = new ApiResponse<>(true, "Vantage found successfully", vantage);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Vantage> response = new ApiResponse<>(false, "Vantage not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Vantage> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/by-partner/{partnerCompanyId}")
    public ResponseEntity<ApiResponse<List<Vantage>>> getVantagesByPartnerCompany(@PathVariable Long partnerCompanyId) {
        try {
            List<Vantage> vantages = vantageService.getVantagesByPartnerCompanyId(partnerCompanyId);
            ApiResponse<List<Vantage>> response = new ApiResponse<>(true, "Vantages fetched successfully", vantages);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Vantage>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Vantage>> update(@PathVariable Long id,
            @RequestBody @Valid VantageDTO VantageDTO) {
        try {
            Vantage updatedVantage = vantageService.update(id, VantageDTO);
            if (updatedVantage != null) {
                ApiResponse<Vantage> response = new ApiResponse<>(true, "Vantage updated successfully", updatedVantage);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Vantage> response = new ApiResponse<>(false, "Vantage not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Vantage> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            boolean isDeleted = vantageService.delete(id);
            if (isDeleted) {
                ApiResponse<Void> response = new ApiResponse<>(true, "Vantage deleted successfully", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(false, "Vantage not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
