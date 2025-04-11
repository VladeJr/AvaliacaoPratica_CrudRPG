package com.rpg.rpgmanager.controller;

import com.rpg.rpgmanager.model.ItemMagico;
import com.rpg.rpgmanager.model.Personagem;
import com.rpg.rpgmanager.service.PersonagemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    private final PersonagemService personagemService;

    public PersonagemController(PersonagemService personagemService) {
        this.personagemService = personagemService;
    }

    @PostMapping
    public Personagem criarPersonagem(@RequestBody Personagem personagem) {
        return personagemService.criarPersonagem(personagem);
    }

    @GetMapping
    public List<Personagem> listarTodos() {
        return personagemService.listarPersonagens();
    }

    @GetMapping("/{id}")
    public Personagem buscarPorId(@PathVariable Long id) {
        return personagemService.buscarPorId(id);
    }

    @PutMapping("/{id}/nomeAventureiro")
    public Personagem atualizarNome(@PathVariable Long id, @RequestParam String nome) {
        return personagemService.atualizarNomeAventureiro(id, nome);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        personagemService.removerPersonagem(id);
    }

    @PostMapping("/{id}/item")
    public ItemMagico adicionarItem(@PathVariable Long id, @RequestBody ItemMagico item) {
        return personagemService.adicionarItemAoPersonagem(id, item);
    }

    @GetMapping("/{id}/itens")
    public List<ItemMagico> listarItens(@PathVariable Long id) {
        return personagemService.listarItensPorPersonagem(id);
    }

    @GetMapping("/{id}/amuleto")
    public ItemMagico buscarAmuleto(@PathVariable Long id) {
        return personagemService.buscarAmuleto(id);
    }
}
