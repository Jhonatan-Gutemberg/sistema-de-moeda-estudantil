package com.coinsystem.system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coinsystem.system.DTO.LoginDTO;
import com.coinsystem.system.DTO.ResponseDTO;
import com.coinsystem.system.controller.ApiResponse.ApiResponseLogin;
import com.coinsystem.system.infra.ITokenService;
import com.coinsystem.system.model.Users;
import com.coinsystem.system.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final ITokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseLogin<ResponseDTO>> login(@RequestBody LoginDTO loginDTO) {
        try {
            Users user = this.usersRepository.findByEmail(loginDTO.email())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
                String token = this.tokenService.generateToken(user);

                ResponseDTO responseDTO = new ResponseDTO(user.getName(), token);
                ApiResponseLogin<ResponseDTO> response = new ApiResponseLogin<>(true, "Login successful", responseDTO,
                        token);

                return ResponseEntity.ok(response);
            } else {
                ApiResponseLogin<ResponseDTO> errorResponse = new ApiResponseLogin<>(false, "Invalid password", null,
                        null);
                return ResponseEntity.badRequest().body(errorResponse);
            }

        } catch (Exception e) {
            ApiResponseLogin<ResponseDTO> errorResponse = new ApiResponseLogin<>(false, e.getMessage(), null, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}