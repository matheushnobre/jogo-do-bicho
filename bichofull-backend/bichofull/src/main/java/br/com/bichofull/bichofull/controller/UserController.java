package br.com.bichofull.bichofull.controller;

import br.com.bichofull.bichofull.domain.deposit.DepositDTO;
import br.com.bichofull.bichofull.domain.deposit.DepositResponseDTO;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.domain.user.UserDTO;
import br.com.bichofull.bichofull.exception.ApiError;
import br.com.bichofull.bichofull.infra.security.SecurityErrorDTO;
import br.com.bichofull.bichofull.service.deposit.DepositService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Tag(name = "Users")
@RestController
@RequestMapping("api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    DepositService depositService;

    @Operation(summary = "Returns authenticated user data")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Profile data retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied: User not authenticated or invalid token",
                    content = @Content(schema = @Schema(implementation = SecurityErrorDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error: An unexpected failure occurred during processing.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    })
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getProfile(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(new UserDTO(user));
    }

    @Operation(summary = "Deposit a specific amount into the authenticated user's balance")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Deposit processed successfully"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied: User not authenticated or invalid token",
                    content = @Content(schema = @Schema(implementation = SecurityErrorDTO.class))
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Unprocessable Entity: Validation failed for the deposit amount",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error: An unexpected failure occurred during processing.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    })
    @PostMapping("/deposit")
    public ResponseEntity<DepositResponseDTO> deposit(@AuthenticationPrincipal User user, @RequestBody @Valid DepositDTO data){
        BigDecimal newBalance = depositService.deposit(user, data.value());
        return ResponseEntity.ok(new DepositResponseDTO(newBalance));
    }
}
