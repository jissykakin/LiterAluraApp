package com.challenger.literAlura.dtos;

import com.challenger.literAlura.models.Author;
import com.challenger.literAlura.models.Language;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;
import java.util.Set;

public record BookDTO(
        Long Id,
        String title,
        String subjects,
        Language language,
        String mediaType,
        Double downloadCount,
        String imageBook,
        String viewBook,
        List<String> authors

) {
}



