package co.javeriana.dw.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BibliotecaResponseDto {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String responsable;
}
