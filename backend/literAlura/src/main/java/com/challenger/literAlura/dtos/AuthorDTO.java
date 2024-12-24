package com.challenger.literAlura.dtos;


import java.util.List;
import java.util.Set;

public record AuthorDTO(
         Long Id,
         String name,
         Integer birthYear,
         Integer deathYear,
         List<String> books

) {
}
