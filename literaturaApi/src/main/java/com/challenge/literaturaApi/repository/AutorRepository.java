package com.challenge.literaturaApi.repository;

import com.challenge.literaturaApi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    @Query("SELECT a FROM Autor a WHERE :anio BETWEEN a.fechaDeNacimiento AND COALESCE(a.fechaDeDefuncion, :anio)")
    List<Autor> findAutoresVivosEnAnio(int anio);
}
