package com.rpg.rpgmanager.repository;

import com.rpg.rpgmanager.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}
