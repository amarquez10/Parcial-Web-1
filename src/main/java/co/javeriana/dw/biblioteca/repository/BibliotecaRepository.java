package co.javeriana.dw.biblioteca.repository;

import co.javeriana.dw.biblioteca.entity.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
}
