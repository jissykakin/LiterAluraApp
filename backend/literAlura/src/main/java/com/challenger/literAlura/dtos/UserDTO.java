package com.challenger.literAlura.dtos;

import jakarta.persistence.Column;

import java.util.List;

public record UserDTO(
        Long id,
        String username,
        String email

) {
}
