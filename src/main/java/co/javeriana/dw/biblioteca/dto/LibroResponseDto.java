package co.javeriana.dw.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibroResponseDto {

    private Long id;
    private String titulo;
    private String autor;
    private String categoria;
    private Long bibliotecaId;
}
