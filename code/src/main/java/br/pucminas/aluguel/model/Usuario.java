package br.pucminas.aluguel.model;

import br.pucminas.aluguel.enumerators.TipoUsuario;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Usuario {

    private TipoUsuario tipo;
    private String email;
    private String senha;

    public boolean login(String senha) {
        return this.senha.equals(senha);
    }

}
