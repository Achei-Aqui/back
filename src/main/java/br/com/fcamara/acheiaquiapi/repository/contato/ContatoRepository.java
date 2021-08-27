package br.com.fcamara.acheiaquiapi.repository.contato;

import br.com.fcamara.acheiaquiapi.model.contato.Contato;
import br.com.fcamara.acheiaquiapi.model.contato.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {


}
