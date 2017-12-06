package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleMedico")
@ViewScoped
public class ControleMedico implements Serializable {

    private MedicoDAO<Medico> dao;
    private Medico objeto;

    private EspecialidadeDAO<Especialidade> daoEspecialidade;

    public ControleMedico() {
        dao = new MedicoDAO<>();
        daoEspecialidade = new EspecialidadeDAO<>();
    }

    public String listar() {
        return "/privado/medico/listar?faces-redirect=true";
    }

    public void novo() {
        setObjeto(new Medico());
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

    public void editar(Integer id) {
        setObjeto(dao.localizar(id));
    }

    public void remover(Integer id) {
        if (dao.remover(dao.localizar(id))) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public MedicoDAO<Medico> getDao() {
        return dao;
    }

    public void setDao(MedicoDAO<Medico> dao) {
        this.dao = dao;
    }

    public Medico getObjeto() {
        return objeto;
    }

    public void setObjeto(Medico objeto) {
        this.objeto = objeto;
    }

    public EspecialidadeDAO<Especialidade> getDaoEspecialidade() {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDAO<Especialidade> daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }

}
