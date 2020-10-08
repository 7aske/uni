package rs.ac.metropolitan.cs230.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "kolicinaproizvoda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kolicinaproizvoda.findAll", query = "SELECT k FROM Kolicinaproizvoda k"),
    @NamedQuery(name = "Kolicinaproizvoda.findByIdkolicinaproizvoda", query = "SELECT k FROM Kolicinaproizvoda k WHERE k.idkolicinaproizvoda = :idkolicinaproizvoda"),
    @NamedQuery(name = "Kolicinaproizvoda.findByKolicina", query = "SELECT k FROM Kolicinaproizvoda k WHERE k.kolicina = :kolicina"),
    @NamedQuery(name = "Kolicinaproizvoda.findByDatumproizvodnje", query = "SELECT k FROM Kolicinaproizvoda k WHERE k.datumproizvodnje = :datumproizvodnje")})
public class Kolicinaproizvoda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDKOLICINAPROIZVODA")
    private Integer idkolicinaproizvoda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KOLICINA")
    private double kolicina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATUMPROIZVODNJE")
    @Temporal(TemporalType.DATE)
    private Date datumproizvodnje;
    @JoinColumn(name = "ID_PROIZVOD", referencedColumnName = "ID_PROIZVOD")
    @ManyToOne(optional = false)
    private Proizvod idProizvod;
    @JoinColumn(name = "IDSKLADISTE", referencedColumnName = "IDSKLADISTE")
    @ManyToOne(optional = false)
    private Skladiste idskladiste;

    public Kolicinaproizvoda() {
    }

    public Kolicinaproizvoda(Integer idkolicinaproizvoda) {
        this.idkolicinaproizvoda = idkolicinaproizvoda;
    }

    public Kolicinaproizvoda(Integer idkolicinaproizvoda, double kolicina, Date datumproizvodnje) {
        this.idkolicinaproizvoda = idkolicinaproizvoda;
        this.kolicina = kolicina;
        this.datumproizvodnje = datumproizvodnje;
    }

    public Integer getIdkolicinaproizvoda() {
        return idkolicinaproizvoda;
    }

    public void setIdkolicinaproizvoda(Integer idkolicinaproizvoda) {
        this.idkolicinaproizvoda = idkolicinaproizvoda;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public Date getDatumproizvodnje() {
        return datumproizvodnje;
    }

    public void setDatumproizvodnje(Date datumproizvodnje) {
        this.datumproizvodnje = datumproizvodnje;
    }

    public Proizvod getIdProizvod() {
        return idProizvod;
    }

    public void setIdProizvod(Proizvod idProizvod) {
        this.idProizvod = idProizvod;
    }

    public Skladiste getIdskladiste() {
        return idskladiste;
    }

    public void setIdskladiste(Skladiste idskladiste) {
        this.idskladiste = idskladiste;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idkolicinaproizvoda != null ? idkolicinaproizvoda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kolicinaproizvoda)) {
            return false;
        }
        Kolicinaproizvoda other = (Kolicinaproizvoda) object;
        if ((this.idkolicinaproizvoda == null && other.idkolicinaproizvoda != null) || (this.idkolicinaproizvoda != null && !this.idkolicinaproizvoda.equals(other.idkolicinaproizvoda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Kolicinaproizvoda[ idkolicinaproizvoda=" + idkolicinaproizvoda + " ]";
    }
    
}
