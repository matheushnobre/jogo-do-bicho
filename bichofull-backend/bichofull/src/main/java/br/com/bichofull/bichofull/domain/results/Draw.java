package br.com.bichofull.bichofull.domain.results;

import br.com.bichofull.bichofull.domain.animal.Animal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "draw")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Draw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int number;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}
