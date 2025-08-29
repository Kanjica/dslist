package com.dev.superior.dslist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.superior.dslist.dto.GameListDTO;
import com.dev.superior.dslist.entities.GameList;
import com.dev.superior.dslist.projections.GameMinProjection;
import com.dev.superior.dslist.repository.GameListRepository;
import com.dev.superior.dslist.repository.GameRepository;


@Service
public class GameListService {

    private final GameListRepository gameListRepository;
    private final GameRepository gameRepository;

    public GameListService(GameListRepository gameListRepository, GameRepository gameRepository) {
        this.gameListRepository = gameListRepository;
        this.gameRepository = gameRepository;
    }
    
    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> gameLists = gameListRepository.findAll();
        return gameLists.stream().map(GameListDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {
        List <GameMinProjection> gameList = gameRepository.searchByList(listId);
        GameMinProjection obj = gameList.remove(sourceIndex);
        gameList.add(destinationIndex, obj);

        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex, destinationIndex);

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, gameList.get(i).getId(), i);
        }
    }
}