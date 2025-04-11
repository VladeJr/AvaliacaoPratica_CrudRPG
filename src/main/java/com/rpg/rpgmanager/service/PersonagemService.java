package com.rpg.rpgmanager.service;

import com.rpg.rpgmanager.model.*;
import com.rpg.rpgmanager.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {
    private final PersonagemRepository personagemRepo;
    private final ItemMagicoRepository itemRepo;

    public PersonagemService(PersonagemRepository personagemRepo, ItemMagicoRepository itemRepo) {
        this.personagemRepo = personagemRepo;
        this.itemRepo = itemRepo;
    }

    public Personagem criarPersonagem(Personagem p) {
        if (p.getForcaBase() + p.getDefesaBase() > 10)
            throw new IllegalArgumentException("A soma de Força e Defesa base deve ser no máximo 10.");
        return personagemRepo.save(p);
    }

    public List<Personagem> listar() {
        return personagemRepo.findAll();
    }

    public Personagem buscarPorId(Long id) {
        return personagemRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado"));
    }

    public void remover(Long id) {
        personagemRepo.deleteById(id);
    }

    public Personagem atualizarNomeAventureiro(Long id, String novoNome) {
        Personagem p = buscarPorId(id);
        p.setNomeAventureiro(novoNome);
        return personagemRepo.save(p);
    }

    public ItemMagico adicionarItem(Long personagemId, ItemMagico item) {
        Personagem p = buscarPorId(personagemId);

        if (item.getForca() == 0 && item.getDefesa() == 0)
            throw new IllegalArgumentException("Item não pode ter Força e Defesa zeradas.");

        if (item.getTipo() == TipoItem.ARMA && item.getDefesa() > 0)
            throw new IllegalArgumentException("Armas não podem ter Defesa.");
        if (item.getTipo() == TipoItem.ARMADURA && item.getForca() > 0)
            throw new IllegalArgumentException("Armaduras não podem ter Força.");
        if (item.getTipo() == TipoItem.AMULETO) {
            ItemMagico amuletoExistente = itemRepo.findByPersonagemIdAndTipo(personagemId, TipoItem.AMULETO);
            if (amuletoExistente != null)
                throw new IllegalArgumentException("O personagem já possui um amuleto.");
        }

        item.setPersonagem(p);
        return itemRepo.save(item);
    }

    public List<ItemMagico> listarItensPorPersonagem(Long personagemId) {
        return itemRepo.findByPersonagemId(personagemId);
    }

    public void removerItem(Long itemId) {
        itemRepo.deleteById(itemId);
    }

    public ItemMagico buscarAmuleto(Long personagemId) {
        return itemRepo.findByPersonagemIdAndTipo(personagemId, TipoItem.AMULETO);
    }
}