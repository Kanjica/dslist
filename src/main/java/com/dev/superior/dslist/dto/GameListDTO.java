package com.dev.superior.dslist.dto;

import com.dev.superior.dslist.entities.GameList;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GameListDTO {
    private Long id;
    private String name;

    public GameListDTO(GameList gameList) {
        this.id = gameList.getId();
        this.name = gameList.getName();
    }
}
