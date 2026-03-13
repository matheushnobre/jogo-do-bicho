package br.com.bichofull.bichofull.domain.results;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "result")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_draw_id")
    private Draw mainDraw;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private List<Draw> secondaryDraws = new ArrayList<>();
}
