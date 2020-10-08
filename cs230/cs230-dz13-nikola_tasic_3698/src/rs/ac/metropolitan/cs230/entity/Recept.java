package rs.ac.metropolitan.cs230.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "recept")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recept.findAll", query = "SELECT r FROM Recept r"),
    @NamedQuery(name = "Recept.findByIdrecept", query = "SELECT r FROM Recept r WHERE r.idrecept = :idrecept")})
public class Recept implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRECEPT")
    private Integer idrecept;
    @Lob
    @Size(max = 65535)
    @Column(name = "TEKSTRECEPTA")
    private String tekstrecepta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idrecept")
    private Collection<Proizvod> proizvodCollection;

    public Recept() {
    }

    public Recept(Integer idrecept) {
        this.idrecept = idrecept;
    }

    public Integer getIdrecept() {
        return idrecept;
    }

    public void setIdrecept(Integer idrecept) {
        this.idrecept = idrecept;
    }

    public String getTekstrecepta() {
        return tekstrecepta;
    }

    public void setTekstrecepta(String tekstrecepta) {
        this.tekstrecepta = tekstrecepta;
    }

    @XmlTransient
    public Collection<Proizvod> getProizvodCollection() {
        return proizvodCollection;
    }

    public void setProizvodCollection(Collection<Proizvod> proizvodCollection) {
        this.proizvodCollection = proizvodCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrecept != null ? idrecept.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recept)) {
            return false;
        }
        Recept other = (Recept) object;
        if ((this.idrecept == null && other.idrecept != null) || (this.idrecept != null && !this.idrecept.equals(other.idrecept))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Recept[ idrecept=" + idrecept + " ]";
    }
    
}
