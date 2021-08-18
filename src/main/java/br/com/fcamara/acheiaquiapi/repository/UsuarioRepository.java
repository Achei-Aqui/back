package br.com.fcamara.acheiaquiapi.repository;

import br.com.fcamara.acheiaquiapi.model.Perfil;
import br.com.fcamara.acheiaquiapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findBycnpj(String cnpj);

    @Override
    public Optional<Usuario> findById(Long id);

//    @Query(value = "SELECT u FROM Usuario u where u.id = 1")
}
