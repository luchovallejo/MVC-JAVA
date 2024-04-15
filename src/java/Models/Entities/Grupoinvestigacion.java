/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucho
 */
@Entity
@Table(name = "grupoinvestigacions", catalog = "proeducativo", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupoinvestigacion.findAll", query = "SELECT g FROM Grupoinvestigacion g"),
    @NamedQuery(name = "Grupoinvestigacion.findByIdlider", query = "SELECT g FROM Grupoinvestigacion g WHERE g.idlider = :idlider"),
    @NamedQuery(name = "Grupoinvestigacion.findByNombreproyecto", query = "SELECT g FROM Grupoinvestigacion g WHERE g.nombreproyecto = :nombreproyecto")})
public class Grupoinvestigacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idlider", nullable = false)
    private Integer idlider;
    @Size(max = 45)
    @Column(name = "nombreproyecto", length = 45)
    private String nombreproyecto;
    @JoinColumn(name = "investigadorid", referencedColumnName = "id")
    @ManyToOne
    private Investigador investigadorid;

    public Grupoinvestigacion() {
    }

    public Grupoinvestigacion(Integer idlider) {
        this.idlider = idlider;
    }

    public Integer getIdlider() {
        return idlider;
    }

    public void setIdlider(Integer idlider) {
        this.idlider = idlider;
    }

    public String getNombreproyecto() {
        return nombreproyecto;
    }

    public void setNombreproyecto(String nombreproyecto) {
        this.nombreproyecto = nombreproyecto;
    }

    public Investigador getInvestigadorid() {
        return investigadorid;
    }

    public void setInvestigadorid(Investigador investigadorid) {
        this.investigadorid = investigadorid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlider != null ? idlider.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupoinvestigacion)) {
            return false;
        }
        Grupoinvestigacion other = (Grupoinvestigacion) object;
        if ((this.idlider == null && other.idlider != null) || (this.idlider != null && !this.idlider.equals(other.idlider))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Entities.Grupoinvestigacion[ idlider=" + idlider + " ]";
    }
    
}
