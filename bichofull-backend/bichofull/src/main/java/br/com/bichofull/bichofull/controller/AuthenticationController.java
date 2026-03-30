package br.com.bichofull.bichofull.controller;

import br.com.bichofull.bichofull.dtos.auth.AuthenticationDTO;
import br.com.bichofull.bichofull.dtos.auth.LoginResponseDTO;
import br.com.bichofull.bichofull.dtos.auth.RegisterDTO;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.exception.ApiError;
import br.com.bichofull.bichofull.infra.security.SecurityErrorDTO;
import br.com.bichofull.bichofull.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Auth")
@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(
            summary = "User authentication",
            description = "Authenticates a user with credentials and returns an access token."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Authentication successful. Returns the access token and user details.",
                    content = @Content(schema = @Schema(implementation = LoginResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden: Invalid username or password.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Unprocessable Content: Request body is valid but contains semantic errors (e.g., malformed email).",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error: An unexpected failure occurred during authentication.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data, HttpServletResponse response){
        return ResponseEntity.ok(authenticationService.login(data, response));
    }

    @Operation(
            summary = "User registration",
            description = "Creates a new user account in the system after validating the provided data."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User created succesfully.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request: Data is missing or in an invalid format.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict: e-mail already exists in the system.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Unprocessable Content: The data is syntactically correct but fails business rules.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error: An unexpected failure occurred during authentication.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        User newUser = authenticationService.register(data);
        URI location = URI.create("/users/" + newUser.getId());

        return ResponseEntity.created(location).build();
    }

    @Operation(
            summary = "Logout user",
            description = "Invalidates the user session by instructing the client to remove the authentication cookie."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Logout successful. Authentication cookie removed.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden: Authentication is required to access this resource.",
                    content = @Content(schema = @Schema(implementation = SecurityErrorDTO.class))
            )
    })
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal User user){
        ResponseCookie cookie = ResponseCookie.from("token", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }
}
