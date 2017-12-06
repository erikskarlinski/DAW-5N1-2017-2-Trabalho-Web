/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.ExameDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.dao.ReceituarioDAO;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Exame;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.modelo.Receituario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author eriks
 */
@ManagedBean(name = "controleConsulta")
@ViewScoped
public class ControleConsulta implements Serializable {

    private ConsultaDAO<Consulta> dao;
    private Consulta objeto;
    private MedicoDAO<Medico> daoMedico;
    private PacienteDAO<Paciente> daoPaciente;
    private Boolean novoExame;
    private Exame exame;
    private ExameDAO<Exame> daoExame;
    private Boolean novoReceituario;
    private Receituario receituario;
    private ReceituarioDAO<Receituario> daoReceituario;

    public ControleConsulta() {
        dao = new ConsultaDAO<>();
        daoMedico = new MedicoDAO<>();
        daoPaciente = new PacienteDAO<>();
        daoExame = new ExameDAO<>();
        daoReceituario = new ReceituarioDAO<>();
    }

    public void adicionarExame() {
        if (getExame() != null) {
            getExame().setConsulta(getObjeto());
            getObjeto().getListaExames().add(getExame());
            Util.mensagemInformacao("Exame adicionado com sucesso");
        } else {
            System.out.println("erro ao adicionar Exame");
        }
    }

    public void novoExame() {
        setExame(new Exame());
        setNovoExame(true);
    }

    public void alterarExame(int index) {
        setExame(getObjeto().getListaExames().get(index));
        getObjeto().removerExame(index);
        setNovoExame(false);
    }

    public void removerExame(int index) {
        getObjeto().getListaExames().remove(index);
        Util.mensagemInformacao("O exame foi removido com sucesso");
    }

    public void adicionarReceituario() {
        if (getReceituario() != null) {
            getReceituario().setConsulta(getObjeto());
            getObjeto().getListaReceituarios().add(getReceituario());
            Util.mensagemInformacao("Receituario adicionado com sucesso");
        } else {
            System.out.println("Erro ao adicionar Receituario");
        }
    }

    public void novoReceituario() {
        setReceituario(new Receituario());
        setNovoReceituario(true);
    }

    public void alterarReceituario(int index) {
        setReceituario(getObjeto().getListaReceituarios().get(index));
        getObjeto().removerReceituario(index);
        setNovoReceituario((Boolean) false);
    }

    public void removerReceituario(int index) {
        getObjeto().getListaReceituarios().remove(index);
        Util.mensagemInformacao("O receituario foi removido com sucesso");
    }

    public String listar() {
        return "/privado/consulta/listar?faces-redirect=true";
    }

    public void novo() {
        setObjeto(new Consulta());
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

        try {
            setObjeto(dao.localizar(id));

        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));

        }
    }

    public void remover(Integer id) {
        if (dao.remover(dao.localizar(id))) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public ConsultaDAO<Consulta> getDao() {
        return dao;
    }

    public void setDao(ConsultaDAO<Consulta> dao) {
        this.dao = dao;
    }

    public Consulta getObjeto() {
        return objeto;
    }

    public void setObjeto(Consulta objeto) {
        this.objeto = objeto;
    }

    public MedicoDAO<Medico> getDaoMedico() {
        return daoMedico;
    }

    public void setDaoMedico(MedicoDAO<Medico> daoMedico) {
        this.daoMedico = daoMedico;
    }

    public PacienteDAO<Paciente> getDaoPaciente() {
        return daoPaciente;
    }

    public void setDaoPaciente(PacienteDAO<Paciente> daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public ExameDAO<Exame> getDaoExame() {
        return daoExame;
    }

    public void setDaoExame(ExameDAO<Exame> daoExame) {
        this.daoExame = daoExame;
    }

    public Receituario getReceituario() {
        return receituario;
    }

    public void setReceituario(Receituario receituario) {
        this.receituario = receituario;
    }

    public ReceituarioDAO<Receituario> getDaoReceituario() {
        return daoReceituario;
    }

    public void setDaoReceituario(ReceituarioDAO<Receituario> daoReceituario) {
        this.daoReceituario = daoReceituario;
    }

    public Boolean getNovoExame() {
        return novoExame;
    }

    public void setNovoExame(Boolean novoExame) {
        this.novoExame = novoExame;
    }

    public Boolean getNovoReceituario() {
        return novoReceituario;
    }

    public void setNovoReceituario(Boolean novoReceituario) {
        this.novoReceituario = novoReceituario;
    }

}
