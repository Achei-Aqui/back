package br.com.fcamara.acheiaquiapi.repository;

import br.com.fcamara.acheiaquiapi.model.authentication.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Optional<Perfil> findByNome(String nome);
}
