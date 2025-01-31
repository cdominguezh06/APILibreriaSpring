package com.cdh.apilibreria.model;

import com.cdh.apilibreria.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "spring.jpa.hibernate.ddl-auto", havingValue = "create", matchIfMissing = false)
public class Populators {
    private LibroRepository librosRepository;
    private AutoresRepository autorRepository;
    private TemasRepository temasRepository;
    private EdicionesRepository edicionRepository;
    private FormatosRepository formatoRepository;

    public Populators(LibroRepository librosRepository, AutoresRepository autorRepository, TemasRepository temasRepository,
                      EdicionesRepository edicionRepository, FormatosRepository formatoRepository) {
        this.librosRepository = librosRepository;
        this.autorRepository = autorRepository;
        this.temasRepository = temasRepository;
        this.edicionRepository = edicionRepository;
        this.formatoRepository = formatoRepository;
    }

    @PostConstruct
    public void populate(){
        // Autores
        Autor autor1 = new Autor(1, "Stephen King");
        autorRepository.save(autor1);
        Autor autor2 = new Autor(2, "J.K. Rowling");
        autorRepository.save(autor2);
        Autor autor3 = new Autor(3, "George R.R. Martin");
        autorRepository.save(autor3);
        Autor autor4 = new Autor(4, "J.R.R. Tolkien");
        autorRepository.save(autor4);
        Autor autor5 = new Autor(5, "Isaac Asimov");
        autorRepository.save(autor5);
        Autor autor6 = new Autor(6, "Vegetta777");
        autorRepository.save(autor6);

        // Temas
        Temas tema1 = new Temas(1, "Terror");
        temasRepository.save(tema1);
        Temas tema2 = new Temas(2, "Fantasia");
        temasRepository.save(tema2);
        Temas tema3 = new Temas(3, "Ciencia Ficcion");
        temasRepository.save(tema3);
        Temas tema4 = new Temas(4, "Historia");
        temasRepository.save(tema4);
        Temas tema5 = new Temas(5, "Biografia");
        temasRepository.save(tema5);
        Temas tema6 = new Temas(6, "Novela");
        temasRepository.save(tema6);

        // Ediciones
        Edicion edicion1 = new Edicion(1, "Fisico");
        edicionRepository.save(edicion1);
        Edicion edicion2 = new Edicion(2, "Digital");
        edicionRepository.save(edicion2);

        // Formatos
        Formato formato1 = new Formato(1, "Tapa Dura");
        formatoRepository.save(formato1);
        Formato formato2 = new Formato(2, "Tapa Blanda");
        formatoRepository.save(formato2);
        Formato formato3 = new Formato(3, "PDF");
        formatoRepository.save(formato3);
        Formato formato4 = new Formato(4, "ePub");
        formatoRepository.save(formato4);

        // Libros
        Libro libro1 = new Libro(1, "It","123456789101D", "TODO", autor1, tema1,19.99, edicion1, formato1,10);
        librosRepository.save(libro1);
        Libro libro2 = new Libro(2, "Harry Potter y la piedra filosofal","735125019219A", "TODO", autor2, tema2,19.99, edicion1, formato1,20);
        librosRepository.save(libro2);
        Libro libro3 = new Libro(3, "Harry Potter y la camara secreta","495115914119B", "TODO", autor2, tema2,19.99, edicion1, formato1,9);
        librosRepository.save(libro3);
        Libro libro4 = new Libro(4, "Wigetta","995115777188V", "wigetta_portada.png", autor6, tema2,19.99, edicion1, formato1,3);
        librosRepository.save(libro4);
    }
}