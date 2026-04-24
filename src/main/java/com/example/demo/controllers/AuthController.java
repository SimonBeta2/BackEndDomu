package com.example.demo.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AuthResponseDTO;
import com.example.demo.dtos.UsuarioDTO;
import com.example.demo.models.UsuarioModel;
import com.example.demo.seguridad.JwtTokenProvider;
import com.example.demo.services.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final JwtTokenProvider tokenProvider;
    private final UsuarioService userService;

    public AuthController(JwtTokenProvider tokenProvider, UsuarioService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        // Get JWT from Authorization header
        String jwt = getJwtFromRequest(request);

        if (!StringUtils.hasText(jwt)) {
            return ResponseEntity.status(401).body("No token provided");
        }

        // Validate token
        if (!tokenProvider.validateToken(jwt)) {
            return ResponseEntity.status(401).body("Invalid or expired token");
        }

        // Get user ID from token
        Integer userId = tokenProvider.getUserIdFromToken(jwt);

        // Fetch user from database
        UsuarioModel user = userService.obtenerPorId(userId);

        if (user == null) {
            return ResponseEntity.status(401).body("User not found");
        }

        UsuarioDTO userDTO = new UsuarioDTO(user.getId(), user.getEmail(), user.getNombre(), user.getTelefono());
        AuthResponseDTO response = new AuthResponseDTO(jwt, userDTO);

        return ResponseEntity.ok(response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}