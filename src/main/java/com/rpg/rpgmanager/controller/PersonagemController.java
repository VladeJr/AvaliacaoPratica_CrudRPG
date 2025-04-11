package com.rpg.rpgmanager.controller;

import com.rpg.rpgmanager.model.*;
import com.rpg.rpgmanager.service.PersonagemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {
    private final PersonagemService service;

    public PersonagemController(PersonagemService service) {
        this.service = service;
    }

    @PostMapping
    public Personagem criar(@RequestBody Personagem p) {
        return service.criarPersonagem(p);
    }

    @GetMapping
    public List<Personagem> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Personagem buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}/nomeAventureiro")
    public Personagem atualizarNome(@PathVariable Long id, @RequestParam String nome) {
        return service.atualizarNomeAventureiro(id, nome);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }

    @PostMapping("/{id}/item")
    public ItemMagico adicionarItem(@PathVariable Long id, @RequestBody ItemMagico item) {
        return service.adicionarItem(id, item);
    }

    @GetMapping("/{id}/itens")
    public List<ItemMagico> listarItens(@PathVariable Long id) {
        return service.listarItensPorPersonagem(id);
    }

    @GetMapping("/{id}/amuleto")
    public ItemMagico buscarAmuleto(@PathVariable Long id) {
        return service.buscarAmuleto(id);
    }
}
