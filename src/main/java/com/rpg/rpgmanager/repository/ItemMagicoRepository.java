package com.rpg.rpgmanager.repository;

import com.rpg.rpgmanager.model.ItemMagico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemMagicoRepository extends JpaRepository<ItemMagico,
        Long> { List<ItemMagico> findByPersonagemId(Long personagemId);
    ItemMagico findByPersonagemIdAndTipo(Long personagemId, com.rpg.rpgmanager.model.TipoItem tipo);
}
