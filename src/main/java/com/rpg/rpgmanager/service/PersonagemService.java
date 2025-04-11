package com.rpg.rpgmanager.service;

import com.rpg.rpgmanager.model.Personagem;
import com.rpg.rpgmanager.model.ItemMagico;
import com.rpg.rpgmanager.model.TipoItem;
import com.rpg.rpgmanager.repository.PersonagemRepository;
import com.rpg.rpgmanager.repository.ItemMagicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {

    private final PersonagemRepository personagemRepository;
    private final ItemMagicoRepository itemMagicoRepository;

    public PersonagemService(PersonagemRepository personagemRepository, ItemMagicoRepository itemMagicoRepository) {
        this.personagemRepository = personagemRepository;
        this.itemMagicoRepository = itemMagicoRepository;
    }

    public Personagem criarPersonagem(Personagem personagem) {
        if (personagem.getForcaBase() + personagem.getDefesaBase() > 10) {
            throw new IllegalArgumentException("A soma de força e defesa não pode ultrapassar 10.");
        }
        return personagemRepository.save(personagem);
    }

    public List<Personagem> listarPersonagens() {
        return personagemRepository.findAll();
    }

    public Personagem buscarPorId(Long id) {
        return personagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado com ID: " + id));
    }

    public Personagem atualizarNomeAventureiro(Long id, String novoNome) {
        Personagem personagem = buscarPorId(id);
        personagem.setNomeAventureiro(novoNome);
        return personagemRepository.save(personagem);
    }

    public void removerPersonagem(Long id) {
        personagemRepository.deleteById(id);
    }

    public ItemMagico adicionarItemAoPersonagem(Long personagemId, ItemMagico item) {
        Personagem personagem = buscarPorId(personagemId);

        if (item.getForca() == 0 && item.getDefesa() == 0) {
            throw new IllegalArgumentException("Item não pode ter força e defesa zeradas.");
        }

        if (item.getTipo() == TipoItem.ARMA && item.getDefesa() > 0) {
            throw new IllegalArgumentException("Item do tipo ARMA não pode ter defesa.");
        }

        if (item.getTipo() == TipoItem.ARMADURA && item.getForca() > 0) {
            throw new IllegalArgumentException("Item do tipo ARMADURA não pode ter força.");
        }

        if (item.getTipo() == TipoItem.AMULETO) {
            ItemMagico amuletoExistente = itemMagicoRepository.findByPersonagemIdAndTipo(personagemId, TipoItem.AMULETO);
            if (amuletoExistente != null) {
                throw new IllegalArgumentException("Personagem já possui um amuleto.");
            }
        }

        item.setPersonagem(personagem);
        return itemMagicoRepository.save(item);
    }

    public List<ItemMagico> listarItensPorPersonagem(Long personagemId) {
        return itemMagicoRepository.findByPersonagemId(personagemId);
    }

    public ItemMagico buscarAmuleto(Long personagemId) {
        return itemMagicoRepository.findByPersonagemIdAndTipo(personagemId, TipoItem.AMULETO);
    }

    public void removerItemMagico(Long itemId) {
        itemMagicoRepository.deleteById(itemId);
    }
}
