package br.com.bichofull.bichofull.controller;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.bet.BetPostDTO;
import br.com.bichofull.bichofull.domain.bet.BetResultDTO;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.exception.ApiError;
import br.com.bichofull.bichofull.infra.security.SecurityErrorDTO;
import br.com.bichofull.bichofull.service.bet.HistoryBetsService;
import br.com.bichofull.bichofull.service.bet.PlaceBetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Bets")
@RestController
@RequestMapping("api/bets")
@CrossOrigin("*")
public class BetController {

    @Autowired
    PlaceBetService placeBetService;

    @Autowired
    HistoryBetsService historyBetsService;

    @Operation(
            summary = "Process a new bet",
            description = "Registers a bet for the authenticated user, validates current balance, and returns the immediate result of the transaction."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Bet processed successfully. Returns details of the bet and the resulting outcome.",
                    content = @Content(schema = @Schema(implementation = BetResultDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Business Logic Error: Insufficient funds or invalid betting parameters.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden: Missing, invalid, or expired authentication token.",
                    content = @Content(schema = @Schema(implementation = SecurityErrorDTO.class))
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Validation Failed: Provided data format is correct, but failed semantic rules (e.g., negative bet amount).",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error: An unexpected failure occurred during processing.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    })
    @PostMapping("/bet")
    public ResponseEntity<BetResultDTO> bet(@AuthenticationPrincipal User user, @RequestBody @Valid BetPostDTO data) {
        Bet bet = placeBetService.placeBet(user, data);
        return ResponseEntity.ok(new BetResultDTO(bet));
    }

    @Operation(
            summary = "Retrieve user bet history",
            description = "Fetches a complete list of all bets placed by the currently authenticated user. Returns an empty list if no bets are found."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Bet history retrieved successfully.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BetResultDTO.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden: Authentication is required to access this resource.",
                    content = @Content(schema = @Schema(implementation = SecurityErrorDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error: An unexpected error occurred while fetching the history.",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    })
    @GetMapping("/my-bets")
    public ResponseEntity<List<BetResultDTO>> getMyBets(@AuthenticationPrincipal User user){
        List<Bet> bets = historyBetsService.getMyBets(user);
        List<BetResultDTO> betsDTO = new ArrayList<>();

        for(Bet bet : bets){
            betsDTO.add(new BetResultDTO(bet));
        }

        return ResponseEntity.ok(betsDTO);
    }
}
