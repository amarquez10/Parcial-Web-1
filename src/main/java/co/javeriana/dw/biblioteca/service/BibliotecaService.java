package co.javeriana.dw.biblioteca.service;

import co.javeriana.dw.biblioteca.dto.BibliotecaRequestDto;
import co.javeriana.dw.biblioteca.dto.BibliotecaResponseDto;

import java.util.List;

public interface BibliotecaService {

    List<BibliotecaResponseDto> findAll();

    BibliotecaResponseDto findById(Long id);

    BibliotecaResponseDto create(BibliotecaRequestDto requestDto);

    BibliotecaResponseDto update(Long id, BibliotecaRequestDto requestDto);

    boolean delete(Long id);
}
