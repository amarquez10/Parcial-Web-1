package co.javeriana.dw.biblioteca.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bibliotecas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la biblioteca es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La dirección de la biblioteca es obligatoria")
    @Size(max = 200, message = "La dirección no puede superar los 200 caracteres")
    @Column(nullable = false, length = 200)
    private String direccion;

    @NotBlank(message = "El teléfono de la biblioteca es obligatorio")
    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    @Column(nullable = false, length = 20)
    private String telefono;

    @NotBlank(message = "El responsable de la biblioteca es obligatorio")
    @Size(max = 100, message = "El responsable no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String responsable;

    @ToString.Exclude
    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros = new ArrayList<>();

    public void addLibro(Libro libro) {
        libros.add(libro);
        libro.setBiblioteca(this);
    }

    public void removeLibro(Libro libro) {
        libros.remove(libro);
        libro.setBiblioteca(null);
    }
}
