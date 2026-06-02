package co.javeriana.dw.biblioteca.service.impl;

import co.javeriana.dw.biblioteca.dto.LibroRequestDto;
import co.javeriana.dw.biblioteca.dto.LibroResponseDto;
import co.javeriana.dw.biblioteca.entity.Biblioteca;
import co.javeriana.dw.biblioteca.entity.Libro;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import co.javeriana.dw.biblioteca.repository.BibliotecaRepository;
import co.javeriana.dw.biblioteca.repository.LibroRepository;
import co.javeriana.dw.biblioteca.service.LibroService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServiceImpl implements LibroService {

    private static final String LIBRO_NOT_FOUND_MESSAGE = "Libro no encontrado con ID: ";
    private static final String BIBLIOTECA_NOT_FOUND_MESSAGE = "Biblioteca no encontrada con ID: ";

    private final LibroRepository libroRepository;
    private final BibliotecaRepository bibliotecaRepository;

    public LibroServiceImpl(LibroRepository libroRepository, BibliotecaRepository bibliotecaRepository) {
        this.libroRepository = libroRepository;
        this.bibliotecaRepository = bibliotecaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LibroResponseDto> findAll() {
        return libroRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LibroResponseDto findById(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, LIBRO_NOT_FOUND_MESSAGE + id));
        return toDto(libro);
    }

    @Override
    @Transactional
    public LibroResponseDto create(LibroRequestDto requestDto) {
        Biblioteca biblioteca = bibliotecaRepository.findById(requestDto.getBibliotecaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, BIBLIOTECA_NOT_FOUND_MESSAGE + requestDto.getBibliotecaId()));
        
        Libro libro = new Libro();
        libro.setTitulo(requestDto.getTitulo());
        libro.setAutor(requestDto.getAutor());
        libro.setCategoria(requestDto.getCategoria());
        libro.setBiblioteca(biblioteca);
        
        Libro savedLibro = libroRepository.save(libro);
        return toDto(savedLibro);
    }

    @Override
    @Transactional
    public LibroResponseDto update(Long id, LibroRequestDto requestDto) {
        Libro existingLibro = libroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, LIBRO_NOT_FOUND_MESSAGE + id));
        
        Biblioteca biblioteca = bibliotecaRepository.findById(requestDto.getBibliotecaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, BIBLIOTECA_NOT_FOUND_MESSAGE + requestDto.getBibliotecaId()));
        
        existingLibro.setTitulo(requestDto.getTitulo());
        existingLibro.setAutor(requestDto.getAutor());
        existingLibro.setCategoria(requestDto.getCategoria());
        existingLibro.setBiblioteca(biblioteca);
        
        Libro updatedLibro = libroRepository.save(existingLibro);
        return toDto(updatedLibro);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return libroRepository.findById(id)
                .map(libro -> {
                    libroRepository.delete(libro);
                    return true;
                })
                .orElse(false);
    }

    private LibroResponseDto toDto(Libro libro) {
        LibroResponseDto dto = new LibroResponseDto();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setAutor(libro.getAutor());
        dto.setCategoria(libro.getCategoria());
        if (libro.getBiblioteca() != null) {
            dto.setBibliotecaId(libro.getBiblioteca().getId());
        }
        return dto;
    }
}
