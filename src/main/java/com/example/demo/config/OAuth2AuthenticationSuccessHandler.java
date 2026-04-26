package com.example.demo.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.models.UsuarioModel;
import com.example.demo.seguridad.JwtTokenProvider;
import com.example.demo.services.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider tokenProvider;
    private final UsuarioService usuarioService;

    @Value("${redirectUri}")
    private String redirectUri;


    public OAuth2AuthenticationSuccessHandler(JwtTokenProvider tokenProvider,
                                             UsuarioService usuarioService) {
        this.tokenProvider = tokenProvider;
        this.usuarioService = usuarioService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String googleId = oAuth2User.getAttribute("sub");
        String picture = oAuth2User.getAttribute("picture");

        // Find or create user
        UsuarioModel user = usuarioService.findOrCreateUser(email, name, googleId, picture);

        // Generate JWT token
        String token = tokenProvider.generateToken(user.getId(), user.getEmail());

        // Build redirect URL with token and user info as query parameters
        String targetUrl = String.format("%s?token=%s&userId=%d&email=%s&name=%s",
                redirectUri,
                token,
                user.getId(),
                urlEncode(user.getEmail()),
                urlEncode(user.getNombre())
        );

        // Redirect back to frontend with token in URL
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String urlEncode(String value) {
        try {
            return java.net.URLEncoder.encode(value, "UTF-8");
        } catch (Exception e) {
            return value;
        }
    }
}