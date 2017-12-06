package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controlePaciente")
@SessionScoped
public class ControlePaciente implements Serializable{
    
    private PacienteDAO<Paciente> dao;
    private Paciente objeto;
    
    public ControlePaciente(){
        dao = new PacienteDAO<>();
    }
    
    public String listar(){
        return "/privado/paciente/listar?faces-redirect=true";
    }
    
    public void novo(){
        setObjeto(new Paciente());
    }
    
   public void salvar() {
        boolean p;
        if (getObjeto().getId() == null) {
            p = dao.persist(getObjeto());
        } else {
            p = dao.merge(getObjeto());
        }
        if (p) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    
    
    public void editar(Integer id){
        
        try {
            setObjeto(dao.localizar(id));
            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            
        }
    }
    
    public void remover(Integer id) {
        if (dao.remover(dao.localizar(id))) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public PacienteDAO<Paciente> getDao() {
        return dao;
    }

    public void setDao(PacienteDAO<Paciente> dao) {
        this.dao = dao;
    }

    public Paciente getObjeto() {
        return objeto;
    }

    public void setObjeto(Paciente objeto) {
        this.objeto = objeto;
    }
    
}

