package co.javeriana.dw.biblioteca.service.impl;

import co.javeriana.dw.biblioteca.dto.BibliotecaRequestDto;
import co.javeriana.dw.biblioteca.dto.BibliotecaResponseDto;
import co.javeriana.dw.biblioteca.entity.Biblioteca;
import co.javeriana.dw.biblioteca.repository.BibliotecaRepository;
import co.javeriana.dw.biblioteca.service.BibliotecaService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BibliotecaServiceImpl implements BibliotecaService {

    private static final String BIBLIOTECA_NOT_FOUND_MESSAGE = "Biblioteca no encontrada con ID: ";

    private final BibliotecaRepository bibliotecaRepository;
    private final ModelMapper modelMapper;

    public BibliotecaServiceImpl(BibliotecaRepository bibliotecaRepository, ModelMapper modelMapper) {
        this.bibliotecaRepository = bibliotecaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BibliotecaResponseDto> findAll() {
        return bibliotecaRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BibliotecaResponseDto findById(Long id) {
        return bibliotecaRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public BibliotecaResponseDto create(BibliotecaRequestDto requestDto) {
        Biblioteca biblioteca = toEntity(requestDto);
        Biblioteca savedBiblioteca = bibliotecaRepository.save(biblioteca);
        return toDto(savedBiblioteca);
    }

    @Override
    @Transactional
    public BibliotecaResponseDto update(Long id, BibliotecaRequestDto requestDto) {
        return bibliotecaRepository.findById(id)
                .map(existingBiblioteca -> {
                    existingBiblioteca.setNombre(requestDto.getNombre());
                    existingBiblioteca.setDireccion(requestDto.getDireccion());
                    existingBiblioteca.setTelefono(requestDto.getTelefono());
                    existingBiblioteca.setResponsable(requestDto.getResponsable());
                    Biblioteca updatedBiblioteca = bibliotecaRepository.save(existingBiblioteca);
                    return toDto(updatedBiblioteca);
                })
                .orElse(null);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return bibliotecaRepository.findById(id)
                .map(biblioteca -> {
                    bibliotecaRepository.delete(biblioteca);
                    return true;
                })
                .orElse(false);
    }

    private BibliotecaResponseDto toDto(Biblioteca biblioteca) {
        return modelMapper.map(biblioteca, BibliotecaResponseDto.class);
    }

    private Biblioteca toEntity(BibliotecaRequestDto requestDto) {
        return modelMapper.map(requestDto, Biblioteca.class);
    }
}
