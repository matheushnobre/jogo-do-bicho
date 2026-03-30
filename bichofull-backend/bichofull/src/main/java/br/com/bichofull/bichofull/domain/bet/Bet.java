package br.com.bichofull.bichofull.domain.bet;

import br.com.bichofull.bichofull.domain.results.Result;
import br.com.bichofull.bichofull.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "bet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private BigDecimal betAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BetType betType;

    @Column
    private int betNumber;

    @OneToOne
    @JoinColumn(name = "result_id")
    private Result result;

    @Column(nullable = false)
    private Instant betDate;

    @Column
    private BigDecimal payout;
}
