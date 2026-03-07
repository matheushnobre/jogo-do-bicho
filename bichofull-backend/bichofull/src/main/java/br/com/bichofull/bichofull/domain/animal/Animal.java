package br.com.bichofull.bichofull.domain.animal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "animal")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Animal {

    @Id
    private Integer id;

    @Column
    private String name;
}
