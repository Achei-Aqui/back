package br.com.fcamara.acheiaquiapi.repository;

import br.com.fcamara.acheiaquiapi.model.authentication.Perfil;
import br.com.fcamara.acheiaquiapi.model.authentication.Usuario;
import br.com.fcamara.acheiaquiapi.model.contato.Categoria;
import br.com.fcamara.acheiaquiapi.model.contato.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findBycnpj(String cnpj);

    @Override
    public Optional<Usuario> findById(Long id);

    List<Usuario> findAllByPerfisIn(List<Perfil> perfis);

    List<Usuario> findAllByContato_Categoria(Categoria categoriaEnum);

    List<Usuario> findAllByPerfisInAndContato_Categoria(List<Perfil> perfis, Categoria categoriaEnum);
}
