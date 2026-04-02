package br.com.bichofull.bichofull.service.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import java.util.Arrays;
import java.util.Optional;

public class CookieService {

    public static void setCookie(HttpServletResponse response, String key, String value, int seconds){
        ResponseCookie cookie = ResponseCookie.from(key, value)
                .httpOnly(true)
                .secure(false)    // Mantenha false para HTTP (localhost/docker)
                .path("/")
                .maxAge(seconds)
                .sameSite("Lax")  // ESSENCIAL: Permite o envio entre 4200 e 8080
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public static String getCookie(HttpServletRequest request, String key){
        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> key.equals(cookie.getName()))
                        .findFirst())
                .map(Cookie::getValue)
                .orElse(null);
    }

}
