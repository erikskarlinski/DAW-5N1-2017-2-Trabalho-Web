package br.edu.ifsul.dao;
import br.edu.ifsul.modelo.Paciente;
import java.io.Serializable;

/**
 *
 * @author eriks
 * @param <T>
 */
public class PacienteDAO<T> extends DAOGenerico<Paciente> implements Serializable {

    public PacienteDAO() {
        super();
        super.setClassePersistente(Paciente.class);
    }

}

