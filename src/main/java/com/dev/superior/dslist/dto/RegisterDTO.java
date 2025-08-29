package com.dev.superior.dslist.dto;

import com.dev.superior.dslist.users.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
    
}
