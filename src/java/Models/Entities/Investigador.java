/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucho
 */
@Entity
@Table(name = "investigadors", catalog = "proeducativo", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Investigador.findAll", query = "SELECT i FROM Investigador i"),
    @NamedQuery(name = "Investigador.findById", query = "SELECT i FROM Investigador i WHERE i.id = :id"),
    @NamedQuery(name = "Investigador.findByNombre", query = "SELECT i FROM Investigador i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Investigador.findByApellido", query = "SELECT i FROM Investigador i WHERE i.apellido = :apellido"),
    @NamedQuery(name = "Investigador.findByTelefono", query = "SELECT i FROM Investigador i WHERE i.telefono = :telefono")})
public class Investigador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombre", length = 45)
    private String nombre;
    @Size(max = 45)
    @Column(name = "apellido", length = 45)
    private String apellido;
    @Column(name = "telefono")
    private Integer telefono;
    @OneToMany(mappedBy = "investigadorid")
    private List<Grupoinvestigacion> grupoinvestigacionList;

    public Investigador() {
    }

    public Investigador(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<Grupoinvestigacion> getGrupoinvestigacionList() {
        return grupoinvestigacionList;
    }

    public void setGrupoinvestigacionList(List<Grupoinvestigacion> grupoinvestigacionList) {
        this.grupoinvestigacionList = grupoinvestigacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Investigador)) {
            return false;
        }
        Investigador other = (Investigador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Entities.Investigador[ id=" + id + " ]";
    }
    
}
