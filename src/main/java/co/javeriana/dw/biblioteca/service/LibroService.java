package co.javeriana.dw.biblioteca.service;

import co.javeriana.dw.biblioteca.dto.LibroRequestDto;
import co.javeriana.dw.biblioteca.dto.LibroResponseDto;

import java.util.List;

public interface LibroService {

    List<LibroResponseDto> findAll();

    LibroResponseDto findById(Long id);

    LibroResponseDto create(LibroRequestDto requestDto);

    LibroResponseDto update(Long id, LibroRequestDto requestDto);

    boolean delete(Long id);
}
