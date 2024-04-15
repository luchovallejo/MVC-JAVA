/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Crud;

import Models.Crud.exceptions.NonexistentEntityException;
import Models.Crud.exceptions.PreexistingEntityException;
import Models.Crud.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Entities.Grupoinvestigacion;
import Models.Entities.Investigador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author lucho
 */
public class InvestigadorJpaController implements Serializable {

    public InvestigadorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Investigador investigador) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (investigador.getGrupoinvestigacionList() == null) {
            investigador.setGrupoinvestigacionList(new ArrayList<Grupoinvestigacion>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Grupoinvestigacion> attachedGrupoinvestigacionList = new ArrayList<Grupoinvestigacion>();
            for (Grupoinvestigacion grupoinvestigacionListGrupoinvestigacionToAttach : investigador.getGrupoinvestigacionList()) {
                grupoinvestigacionListGrupoinvestigacionToAttach = em.getReference(grupoinvestigacionListGrupoinvestigacionToAttach.getClass(), grupoinvestigacionListGrupoinvestigacionToAttach.getIdlider());
                attachedGrupoinvestigacionList.add(grupoinvestigacionListGrupoinvestigacionToAttach);
            }
            investigador.setGrupoinvestigacionList(attachedGrupoinvestigacionList);
            em.persist(investigador);
            for (Grupoinvestigacion grupoinvestigacionListGrupoinvestigacion : investigador.getGrupoinvestigacionList()) {
                Investigador oldInvestigadoridOfGrupoinvestigacionListGrupoinvestigacion = grupoinvestigacionListGrupoinvestigacion.getInvestigadorid();
                grupoinvestigacionListGrupoinvestigacion.setInvestigadorid(investigador);
                grupoinvestigacionListGrupoinvestigacion = em.merge(grupoinvestigacionListGrupoinvestigacion);
                if (oldInvestigadoridOfGrupoinvestigacionListGrupoinvestigacion != null) {
                    oldInvestigadoridOfGrupoinvestigacionListGrupoinvestigacion.getGrupoinvestigacionList().remove(grupoinvestigacionListGrupoinvestigacion);
                    oldInvestigadoridOfGrupoinvestigacionListGrupoinvestigacion = em.merge(oldInvestigadoridOfGrupoinvestigacionListGrupoinvestigacion);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findInvestigador(investigador.getId()) != null) {
                throw new PreexistingEntityException("Investigador " + investigador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Investigador investigador) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Investigador persistentInvestigador = em.find(Investigador.class, investigador.getId());
            List<Grupoinvestigacion> grupoinvestigacionListOld = persistentInvestigador.getGrupoinvestigacionList();
            List<Grupoinvestigacion> grupoinvestigacionListNew = investigador.getGrupoinvestigacionList();
            List<Grupoinvestigacion> attachedGrupoinvestigacionListNew = new ArrayList<Grupoinvestigacion>();
            for (Grupoinvestigacion grupoinvestigacionListNewGrupoinvestigacionToAttach : grupoinvestigacionListNew) {
                grupoinvestigacionListNewGrupoinvestigacionToAttach = em.getReference(grupoinvestigacionListNewGrupoinvestigacionToAttach.getClass(), grupoinvestigacionListNewGrupoinvestigacionToAttach.getIdlider());
                attachedGrupoinvestigacionListNew.add(grupoinvestigacionListNewGrupoinvestigacionToAttach);
            }
            grupoinvestigacionListNew = attachedGrupoinvestigacionListNew;
            investigador.setGrupoinvestigacionList(grupoinvestigacionListNew);
            investigador = em.merge(investigador);
            for (Grupoinvestigacion grupoinvestigacionListOldGrupoinvestigacion : grupoinvestigacionListOld) {
                if (!grupoinvestigacionListNew.contains(grupoinvestigacionListOldGrupoinvestigacion)) {
                    grupoinvestigacionListOldGrupoinvestigacion.setInvestigadorid(null);
                    grupoinvestigacionListOldGrupoinvestigacion = em.merge(grupoinvestigacionListOldGrupoinvestigacion);
                }
            }
            for (Grupoinvestigacion grupoinvestigacionListNewGrupoinvestigacion : grupoinvestigacionListNew) {
                if (!grupoinvestigacionListOld.contains(grupoinvestigacionListNewGrupoinvestigacion)) {
                    Investigador oldInvestigadoridOfGrupoinvestigacionListNewGrupoinvestigacion = grupoinvestigacionListNewGrupoinvestigacion.getInvestigadorid();
                    grupoinvestigacionListNewGrupoinvestigacion.setInvestigadorid(investigador);
                    grupoinvestigacionListNewGrupoinvestigacion = em.merge(grupoinvestigacionListNewGrupoinvestigacion);
                    if (oldInvestigadoridOfGrupoinvestigacionListNewGrupoinvestigacion != null && !oldInvestigadoridOfGrupoinvestigacionListNewGrupoinvestigacion.equals(investigador)) {
                        oldInvestigadoridOfGrupoinvestigacionListNewGrupoinvestigacion.getGrupoinvestigacionList().remove(grupoinvestigacionListNewGrupoinvestigacion);
                        oldInvestigadoridOfGrupoinvestigacionListNewGrupoinvestigacion = em.merge(oldInvestigadoridOfGrupoinvestigacionListNewGrupoinvestigacion);
                    }
                }
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
                Integer id = investigador.getId();
                if (findInvestigador(id) == null) {
                    throw new NonexistentEntityException("The investigador with id " + id + " no longer exists.");
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
            Investigador investigador;
            try {
                investigador = em.getReference(Investigador.class, id);
                investigador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The investigador with id " + id + " no longer exists.", enfe);
            }
            List<Grupoinvestigacion> grupoinvestigacionList = investigador.getGrupoinvestigacionList();
            for (Grupoinvestigacion grupoinvestigacionListGrupoinvestigacion : grupoinvestigacionList) {
                grupoinvestigacionListGrupoinvestigacion.setInvestigadorid(null);
                grupoinvestigacionListGrupoinvestigacion = em.merge(grupoinvestigacionListGrupoinvestigacion);
            }
            em.remove(investigador);
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

    public List<Investigador> findInvestigadorEntities() {
        return findInvestigadorEntities(true, -1, -1);
    }

    public List<Investigador> findInvestigadorEntities(int maxResults, int firstResult) {
        return findInvestigadorEntities(false, maxResults, firstResult);
    }

    private List<Investigador> findInvestigadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Investigador.class));
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

    public Investigador findInvestigador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Investigador.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvestigadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Investigador> rt = cq.from(Investigador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
