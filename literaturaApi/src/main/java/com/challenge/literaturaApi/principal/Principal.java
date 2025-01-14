package com.challenge.literaturaApi.principal;


import com.challenge.literaturaApi.model.Autor;
import com.challenge.literaturaApi.model.DatosBusqueda;
import com.challenge.literaturaApi.model.Libro;
import com.challenge.literaturaApi.repository.AutorRepository;
import com.challenge.literaturaApi.repository.LibroRepository;
import com.challenge.literaturaApi.service.ConsumoApi;
import com.challenge.literaturaApi.service.ConvierteDatos;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Principal {
    private Scanner teclado=new Scanner(System.in);
    private ConsumoApi consumoApi=new ConsumoApi();
    private final String URL_BASE="https://gutendex.com/books/?search=";
    private ConvierteDatos convierteDatos=new ConvierteDatos();
    private List<DatosBusqueda>datosBusquedas=new ArrayList<>();
    private LibroRepository repository;
    private AutorRepository autorRepository;



    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repository=repository;
        this.autorRepository=autorRepository;
    }


    public void menu(){
        var opcion=-1;
        while (opcion!=0){
            var menu= """
                    Eliga la ocpion de deseada:
                    1-Buscar Libro por Titulo
                    2-Listar Libros consultados
                    3-Listar Autores registrados
                    4-Listar Autores vivos por año
                    5-Listar Libros por idioma
                    0-Salir""";
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){

                case 1:
                    buscarLibro();
                    break;
                case 2:
                    getLibrosBuscados();
                    break;
                case 3:
                    getAutoresRegistrados();
                    break;
                case 4:
                    getAutorVivoPorAño();
                    break;
                case 5:
                    getLibroIdioma();
                    break;
            }
        }
    }
    private DatosBusqueda getDatosLibros(){
        System.out.println("Escribre el Titulo del Libro:");
        var tituloLibro=teclado.nextLine();
        var json=consumoApi.obtenerDatos(URL_BASE+tituloLibro.replace(" ","%20"));
        System.out.println(json);
        DatosBusqueda datos=convierteDatos.obetenerDatos(json, DatosBusqueda.class);
        return datos;
    }


    private void buscarLibro(){
        DatosBusqueda datos=getDatosLibros();
        Libro libro=new Libro(datos.resultados().get(0));
        System.out.println(libro);
        Autor autor = libro.getAutor();
        if (autor != null) {
            System.out.println(autor);
            autorRepository.save(autor);
        }
        repository.save(libro);
        System.out.println(libro);
    }

    private void getLibrosBuscados(){
        List<Libro>libros=repository.findAll();
        libros.forEach(System.out::println);
    }

    private void getAutoresRegistrados(){
        List<Autor>autors=autorRepository.findAll();
        autors.forEach(System.out::println);
    }

    private void getAutorVivoPorAño(){
        System.out.println("Ingresa la fecha del autor vivo");
        int anio=teclado.nextInt();
        teclado.nextLine();
        List<Autor> autors = autorRepository.findAutoresVivosEnAnio(anio);
        autors.forEach(autor -> System.out.println(autor.getNombreAutor()));
    }

    private void getLibroIdioma(){
        System.out.println("Ingresa el idioma del libro:");
        var idioma=teclado.nextLine();
        teclado.nextLine();
        List<Libro>libros=repository.findLibrosPorIdioma(idioma);
        libros.forEach(libro -> System.out.println(libro.getTitulo()));
    }
}
