package com.dev.superior.dslist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.superior.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {
}
