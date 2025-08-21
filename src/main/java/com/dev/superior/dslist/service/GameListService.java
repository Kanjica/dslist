package com.dev.superior.dslist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.superior.dslist.dto.GameListDTO;
import com.dev.superior.dslist.entities.GameList;
import com.dev.superior.dslist.repository.GameListRepository;


@Service
public class GameListService {

    private final GameListRepository gameListRepository;

    public GameListService(GameListRepository gameListRepository) {
        this.gameListRepository = gameListRepository;
    }
    
    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> gameLists = gameListRepository.findAll();
        return gameLists.stream().map(GameListDTO::new).collect(Collectors.toList());
    }
}
