package co.javeriana.dw.biblioteca.controller;

import co.javeriana.dw.biblioteca.dto.BibliotecaRequestDto;
import co.javeriana.dw.biblioteca.dto.BibliotecaResponseDto;
import co.javeriana.dw.biblioteca.service.BibliotecaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bibliotecas")
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @GetMapping
    public ResponseEntity<List<BibliotecaResponseDto>> getAll() {
        return ResponseEntity.ok(bibliotecaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BibliotecaResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bibliotecaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BibliotecaResponseDto> create(@Valid @RequestBody BibliotecaRequestDto requestDto) {
        BibliotecaResponseDto created = bibliotecaService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BibliotecaResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody BibliotecaRequestDto requestDto) {
        return ResponseEntity.ok(bibliotecaService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bibliotecaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
