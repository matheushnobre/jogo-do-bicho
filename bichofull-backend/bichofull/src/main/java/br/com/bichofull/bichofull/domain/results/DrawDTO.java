package br.com.bichofull.bichofull.domain.results;

public record DrawDTO(int number,
                      String animalName
) {
    public DrawDTO(Draw draw){
        this(draw.getNumber(), draw.getAnimal().getName());
    }
}
