package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;


public class MedicoDAO implements Serializable {

    private String mensagem = "";
    private EntityManager em;
    
    public MedicoDAO(){
        em = EntityManagerUtil.getEntityManager();
    }
    
    public List<Medico> getLista(){
        return em.createQuery("from Medico order by nome").getResultList();
    }
    
    public boolean salvar(Medico obj){
        try {
            em.getTransaction().begin();
            if (obj.getId() == null){
                em.persist(obj);
            } else {
                em.merge(obj);
            }
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        } catch (Exception e){
            if (em.getTransaction().isActive() == false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao persistir objeto: " +
                    Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean remover(Medico obj){
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso!";
            return true;
        } catch (Exception e){
            if (em.getTransaction().isActive() == false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao remover objeto: " +
                    Util.getMensagemErro(e);
            return false;
        }
    }  
    
    public Medico localizar(Integer id){
        return em.find(Medico.class, id);
    }
    

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
