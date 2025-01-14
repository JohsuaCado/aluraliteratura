package com.challenge.literaturaApi.model;
import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreAutor;
    private Integer fechaDeNacimiento;
    private Integer fechaDeDefuncion;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;
    
    public Autor(){}


    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", autor='" + nombreAutor + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", fechaDeDefuncion=" + fechaDeDefuncion +
                '}';
    }

    public Autor(DatosAutor datosAutor){
        this.nombreAutor =datosAutor.nombreAutor();
        this.fechaDeNacimiento=datosAutor.fechaDeNacimiento();
        this.fechaDeDefuncion=datosAutor.fechaDeDefuncion();
    }


    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeDefuncion() {
        return fechaDeDefuncion;
    }

    public void setFechaDeDefuncion(Integer fechaDeDefuncion) {
        this.fechaDeDefuncion = fechaDeDefuncion;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
