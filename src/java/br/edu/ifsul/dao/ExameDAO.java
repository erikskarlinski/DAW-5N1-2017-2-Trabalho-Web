/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Exame;
import java.io.Serializable;

/**
 *
 * @author eriks
 */
public class ExameDAO<T> extends DAOGenerico<Exame> implements Serializable {
 
    public ExameDAO() {
        super();        
        super.setClassePersistente(Exame.class);
    }
    
    
}
