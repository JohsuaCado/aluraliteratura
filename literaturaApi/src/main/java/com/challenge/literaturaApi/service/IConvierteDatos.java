package com.challenge.literaturaApi.service;

public interface IConvierteDatos {
 <T> T obetenerDatos(String json, Class<T> clase);
}
