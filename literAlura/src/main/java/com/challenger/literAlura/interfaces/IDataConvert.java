package com.challenger.literAlura.interfaces;

public interface IDataConvert {
        public <T> T getData(String json, Class<T> model);
}
