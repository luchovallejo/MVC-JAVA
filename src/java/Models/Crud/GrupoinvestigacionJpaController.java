/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Crud;

import Models.Crud.exceptions.NonexistentEntityException;
import Models.Crud.exceptions.PreexistingEntityException;
import Models.Crud.exceptions.RollbackFailureException;
import Models.Entities.Grupoinvestigacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Entities.Investigador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author lucho
 */
public class GrupoinvestigacionJpaController implements Serializable {

    public GrupoinvestigacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupoinvestigacion grupoinvestigacion) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Investigador investigadorid = grupoinvestigacion.getInvestigadorid();
            if (investigadorid != null) {
                investigadorid = em.getReference(investigadorid.getClass(), investigadorid.getId());
                grupoinvestigacion.setInvestigadorid(investigadorid);
            }
            em.persist(grupoinvestigacion);
            if (investigadorid != null) {
                investigadorid.getGrupoinvestigacionList().add(grupoinvestigacion);
                investigadorid = em.merge(investigadorid);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findGrupoinvestigacion(grupoinvestigacion.getIdlider()) != null) {
                throw new PreexistingEntityException("Grupoinvestigacion " + grupoinvestigacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupoinvestigacion grupoinvestigacion) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Grupoinvestigacion persistentGrupoinvestigacion = em.find(Grupoinvestigacion.class, grupoinvestigacion.getIdlider());
            Investigador investigadoridOld = persistentGrupoinvestigacion.getInvestigadorid();
            Investigador investigadoridNew = grupoinvestigacion.getInvestigadorid();
            if (investigadoridNew != null) {
                investigadoridNew = em.getReference(investigadoridNew.getClass(), investigadoridNew.getId());
                grupoinvestigacion.setInvestigadorid(investigadoridNew);
            }
            grupoinvestigacion = em.merge(grupoinvestigacion);
            if (investigadoridOld != null && !investigadoridOld.equals(investigadoridNew)) {
                investigadoridOld.getGrupoinvestigacionList().remove(grupoinvestigacion);
                investigadoridOld = em.merge(investigadoridOld);
            }
            if (investigadoridNew != null && !investigadoridNew.equals(investigadoridOld)) {
                investigadoridNew.getGrupoinvestigacionList().add(grupoinvestigacion);
                investigadoridNew = em.merge(investigadoridNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grupoinvestigacion.getIdlider();
                if (findGrupoinvestigacion(id) == null) {
                    throw new NonexistentEntityException("The grupoinvestigacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Grupoinvestigacion grupoinvestigacion;
            try {
                grupoinvestigacion = em.getReference(Grupoinvestigacion.class, id);
                grupoinvestigacion.getIdlider();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupoinvestigacion with id " + id + " no longer exists.", enfe);
            }
            Investigador investigadorid = grupoinvestigacion.getInvestigadorid();
            if (investigadorid != null) {
                investigadorid.getGrupoinvestigacionList().remove(grupoinvestigacion);
                investigadorid = em.merge(investigadorid);
            }
            em.remove(grupoinvestigacion);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupoinvestigacion> findGrupoinvestigacionEntities() {
        return findGrupoinvestigacionEntities(true, -1, -1);
    }

    public List<Grupoinvestigacion> findGrupoinvestigacionEntities(int maxResults, int firstResult) {
        return findGrupoinvestigacionEntities(false, maxResults, firstResult);
    }

    private List<Grupoinvestigacion> findGrupoinvestigacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupoinvestigacion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Grupoinvestigacion findGrupoinvestigacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupoinvestigacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoinvestigacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupoinvestigacion> rt = cq.from(Grupoinvestigacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
