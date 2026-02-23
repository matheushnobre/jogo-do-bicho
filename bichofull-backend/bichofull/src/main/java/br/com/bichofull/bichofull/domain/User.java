package br.com.bichofull.bichofull.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @Column()
    private BigDecimal balance;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.balance = new BigDecimal("1000.00");
    }
}
