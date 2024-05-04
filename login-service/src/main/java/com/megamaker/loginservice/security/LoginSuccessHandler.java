package com.megamaker.loginservice.security;

import com.megamaker.loginservice.security.oauth2.CustomOAuth2User;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final Environment environment;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        String userId = ((CustomOAuth2User) authentication.getPrincipal()).getUserId();

        Key secretKey = new SecretKeySpec(Base64.getEncoder().encode(environment.getProperty("token.secret").getBytes()),
                Jwts.SIG.HS256.key().build().getAlgorithm());
        log.info(environment.getProperty("token.secret"));
        String token = Jwts.builder()
                .subject(userId)
                .expiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(secretKey)
                .compact();

        Cookie cookie = getCookie("Auth", token);
        response.addCookie(cookie);
        response.sendRedirect("http://localhost:8000/");
        //response.addHeader("token", token);
        //response.addHeader("userId", userDto.getUserId());
    }

    private static Cookie getCookie(String name, String token) {
        Cookie cookie = new Cookie(name, token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
