package com.challenger.literAlura.models;


public enum Language {
    ESPAÑOL("es"),
    INGLES("en"),
    FILIPINO("tl"),
    FRANCES("fr"),
    ALEMAN( "de"),
    PORTUGUES( "pt");



    private String languajeOmdb;

    Language(String languajeOmdb) {
        this.languajeOmdb = languajeOmdb;
    }

    public static Language fromString(String text) {
        for (Language lang : Language.values()) {
            if (lang.languajeOmdb.equalsIgnoreCase(text)) {
                return lang;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
}