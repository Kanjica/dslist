package com.dev.superior.dslist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.superior.dslist.dto.GameMinDTO;
import com.dev.superior.dslist.entities.Game;
import com.dev.superior.dslist.repository.GameRepository;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameMinDTO> findAll() {
        List<Game> games = gameRepository.findAll();
        return games.stream().map(GameMinDTO::new).collect(Collectors.toList());
    }
}
