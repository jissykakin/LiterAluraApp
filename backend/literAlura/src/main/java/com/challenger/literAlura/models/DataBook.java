package com.challenger.literAlura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
        String title,
        List<String> subjects,
        List<DataAuthor> authors,
        List<String> languages,
        @JsonAlias("media_type")  String mediaType,
        @JsonAlias("formats") Map<String, String> formats,
        @JsonAlias("download_count") Double downloadCount
) {
}
