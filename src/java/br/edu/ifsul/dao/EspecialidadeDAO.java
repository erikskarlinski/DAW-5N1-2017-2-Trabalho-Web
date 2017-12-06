package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;


public class EspecialidadeDAO<T> extends DAOGenerico<Especialidade> implements Serializable {

    public EspecialidadeDAO() {
        super();
        classePersistente = Especialidade.class;
    }

}