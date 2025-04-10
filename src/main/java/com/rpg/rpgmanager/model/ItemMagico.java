package com.rpg.rpgmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max; import jakarta.validation.constraints.Min;
import lombok.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor public class ItemMagico { @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    @Min(0) @Max(10)
    private int forca;

    @Min(0) @Max(10)
    private int defesa;

    @ManyToOne
    public Personagem personagem;

}
