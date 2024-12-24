package com.challenger.literAlura.services;

import com.challenger.literAlura.interfaces.IDataConvert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertDataToClass implements IDataConvert {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> model) {
        try {
            return mapper.readValue(json, model);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
