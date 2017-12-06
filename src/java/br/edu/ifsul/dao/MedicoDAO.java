package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author eriks
 * @param <T>
 */
public class MedicoDAO<T> extends DAOGenerico<Medico> implements Serializable{
 
    public MedicoDAO(){
        super();
        super.setClassePersistente(Medico.class);
    }
    
    
}