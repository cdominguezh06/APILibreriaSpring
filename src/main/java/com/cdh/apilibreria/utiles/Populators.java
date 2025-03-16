package com.cdh.apilibreria.utiles;

import com.cdh.apilibreria.model.entities.*;
import com.cdh.apilibreria.model.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "spring.jpa.hibernate.ddl-auto", havingValue = "create", matchIfMissing = false)
public class Populators {
    private final LibroRepository librosRepository;
    private final AutoresRepository autorRepository;
    private final TemasRepository temasRepository;
    private final EdicionesRepository edicionRepository;
    private final FormatosRepository formatoRepository;

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
        Autor autor1 = new Autor("Stephen King");
        autorRepository.save(autor1);
        Autor autor2 = new Autor("J.K. Rowling");
        autorRepository.save(autor2);
        Autor autor3 = new Autor("George R.R. Martin");
        autorRepository.save(autor3);
        Autor autor4 = new Autor("J.R.R. Tolkien");
        autorRepository.save(autor4);
        Autor autor5 = new Autor("Isaac Asimov");
        autorRepository.save(autor5);
        Autor autor6 = new Autor("Vegetta777");
        autorRepository.save(autor6);

        // Temas
        Temas tema1 = new Temas("Terror");
        temasRepository.save(tema1);
        Temas tema2 = new Temas("Fantasia");
        temasRepository.save(tema2);
        Temas tema3 = new Temas("Ciencia Ficcion");
        temasRepository.save(tema3);
        Temas tema4 = new Temas("Historia");
        temasRepository.save(tema4);
        Temas tema5 = new Temas("Biografia");
        temasRepository.save(tema5);
        Temas tema6 = new Temas("Novela");
        temasRepository.save(tema6);

        // Ediciones
        Edicion edicion1 = new Edicion("Fisico");
        edicionRepository.save(edicion1);
        Edicion edicion2 = new Edicion("Digital");
        edicionRepository.save(edicion2);

        // Formatos
        Formato formato1 = new Formato("Tapa Dura");
        formatoRepository.save(formato1);
        Formato formato2 = new Formato("Tapa Blanda");
        formatoRepository.save(formato2);
        Formato formato3 = new Formato("PDF");
        formatoRepository.save(formato3);
        Formato formato4 = new Formato("ePub");
        formatoRepository.save(formato4);

        // Libros
        Libro libro1 = new Libro("It","123456789101D", "it.png", autor1, tema1,19.99, edicion1, formato1,10);
        librosRepository.save(libro1);
        Libro libro2 = new Libro("Harry Potter y la piedra filosofal","735125019219A", "piedrafilosofal.png", autor2, tema2,19.99, edicion1, formato1,20);
        librosRepository.save(libro2);
        Libro libro3 = new Libro("Harry Potter y la camara secreta","495115914119B", "camarasecreta.png", autor2, tema2,19.99, edicion1, formato1,9);
        librosRepository.save(libro3);
        Libro libro4 = new Libro("Wigetta","995115777188V", "wigetta_portada.png", autor6, tema2,19.99, edicion1, formato1,3);
        librosRepository.save(libro4);
    }
}