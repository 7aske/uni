package rs.ac.metropolitan.cs230.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "specifikacija_proizvodnje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SpecifikacijaProizvodnje.findAll", query = "SELECT s FROM SpecifikacijaProizvodnje s"),
    @NamedQuery(name = "SpecifikacijaProizvodnje.findByIdSpecifikacijaproizvodnje", query = "SELECT s FROM SpecifikacijaProizvodnje s WHERE s.idSpecifikacijaproizvodnje = :idSpecifikacijaproizvodnje"),
    @NamedQuery(name = "SpecifikacijaProizvodnje.findByKolicinaproizvoda", query = "SELECT s FROM SpecifikacijaProizvodnje s WHERE s.kolicinaproizvoda = :kolicinaproizvoda"),
    @NamedQuery(name = "SpecifikacijaProizvodnje.findByMinimalnikapacitet", query = "SELECT s FROM SpecifikacijaProizvodnje s WHERE s.minimalnikapacitet = :minimalnikapacitet"),
    @NamedQuery(name = "SpecifikacijaProizvodnje.findByMaksimalnikapacitet", query = "SELECT s FROM SpecifikacijaProizvodnje s WHERE s.maksimalnikapacitet = :maksimalnikapacitet")})
public class SpecifikacijaProizvodnje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SPECIFIKACIJAPROIZVODNJE")
    private Integer idSpecifikacijaproizvodnje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KOLICINAPROIZVODA")
    private double kolicinaproizvoda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MINIMALNIKAPACITET")
    private double minimalnikapacitet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAKSIMALNIKAPACITET")
    private double maksimalnikapacitet;
    @JoinColumn(name = "ID_PROIZVOD", referencedColumnName = "ID_PROIZVOD")
    @ManyToOne(optional = false)
    private Proizvod idProizvod;
    @JoinColumn(name = "IDPROIZVODNAJEDINICA", referencedColumnName = "IDPROIZVODNAJEDINICA")
    @ManyToOne(optional = false)
    private ProizvodnaJedinica idproizvodnajedinica;

    public SpecifikacijaProizvodnje() {
    }

    public SpecifikacijaProizvodnje(Integer idSpecifikacijaproizvodnje) {
        this.idSpecifikacijaproizvodnje = idSpecifikacijaproizvodnje;
    }

    public SpecifikacijaProizvodnje(Integer idSpecifikacijaproizvodnje, double kolicinaproizvoda, double minimalnikapacitet, double maksimalnikapacitet) {
        this.idSpecifikacijaproizvodnje = idSpecifikacijaproizvodnje;
        this.kolicinaproizvoda = kolicinaproizvoda;
        this.minimalnikapacitet = minimalnikapacitet;
        this.maksimalnikapacitet = maksimalnikapacitet;
    }

    public Integer getIdSpecifikacijaproizvodnje() {
        return idSpecifikacijaproizvodnje;
    }

    public void setIdSpecifikacijaproizvodnje(Integer idSpecifikacijaproizvodnje) {
        this.idSpecifikacijaproizvodnje = idSpecifikacijaproizvodnje;
    }

    public double getKolicinaproizvoda() {
        return kolicinaproizvoda;
    }

    public void setKolicinaproizvoda(double kolicinaproizvoda) {
        this.kolicinaproizvoda = kolicinaproizvoda;
    }

    public double getMinimalnikapacitet() {
        return minimalnikapacitet;
    }

    public void setMinimalnikapacitet(double minimalnikapacitet) {
        this.minimalnikapacitet = minimalnikapacitet;
    }

    public double getMaksimalnikapacitet() {
        return maksimalnikapacitet;
    }

    public void setMaksimalnikapacitet(double maksimalnikapacitet) {
        this.maksimalnikapacitet = maksimalnikapacitet;
    }

    public Proizvod getIdProizvod() {
        return idProizvod;
    }

    public void setIdProizvod(Proizvod idProizvod) {
        this.idProizvod = idProizvod;
    }

    public ProizvodnaJedinica getIdproizvodnajedinica() {
        return idproizvodnajedinica;
    }

    public void setIdproizvodnajedinica(ProizvodnaJedinica idproizvodnajedinica) {
        this.idproizvodnajedinica = idproizvodnajedinica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSpecifikacijaproizvodnje != null ? idSpecifikacijaproizvodnje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpecifikacijaProizvodnje)) {
            return false;
        }
        SpecifikacijaProizvodnje other = (SpecifikacijaProizvodnje) object;
        if ((this.idSpecifikacijaproizvodnje == null && other.idSpecifikacijaproizvodnje != null) || (this.idSpecifikacijaproizvodnje != null && !this.idSpecifikacijaproizvodnje.equals(other.idSpecifikacijaproizvodnje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SpecifikacijaProizvodnje[ idSpecifikacijaproizvodnje=" + idSpecifikacijaproizvodnje + " ]";
    }
    
}
