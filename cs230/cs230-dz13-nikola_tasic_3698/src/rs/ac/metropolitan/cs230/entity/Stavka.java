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
@Table(name = "stavka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stavka.findAll", query = "SELECT s FROM Stavka s"),
    @NamedQuery(name = "Stavka.findByIdstavka", query = "SELECT s FROM Stavka s WHERE s.idstavka = :idstavka"),
    @NamedQuery(name = "Stavka.findByKolicina", query = "SELECT s FROM Stavka s WHERE s.kolicina = :kolicina")})
public class Stavka implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSTAVKA")
    private Integer idstavka;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KOLICINA")
    private double kolicina;
    @JoinColumn(name = "ID_NARUDZBENICA_OTPREMNICA", referencedColumnName = "ID_NARUDZBENICA_OTPREMNICA")
    @ManyToOne(optional = false)
    private NarudzbenicaOtpremnica idNarudzbenicaOtpremnica;
    @JoinColumn(name = "ID_PROIZVOD", referencedColumnName = "ID_PROIZVOD")
    @ManyToOne(optional = false)
    private Proizvod idProizvod;

    public Stavka() {
    }

    public Stavka(Integer idstavka) {
        this.idstavka = idstavka;
    }

    public Stavka(Integer idstavka, double kolicina) {
        this.idstavka = idstavka;
        this.kolicina = kolicina;
    }

    public Integer getIdstavka() {
        return idstavka;
    }

    public void setIdstavka(Integer idstavka) {
        this.idstavka = idstavka;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public NarudzbenicaOtpremnica getIdNarudzbenicaOtpremnica() {
        return idNarudzbenicaOtpremnica;
    }

    public void setIdNarudzbenicaOtpremnica(NarudzbenicaOtpremnica idNarudzbenicaOtpremnica) {
        this.idNarudzbenicaOtpremnica = idNarudzbenicaOtpremnica;
    }

    public Proizvod getIdProizvod() {
        return idProizvod;
    }

    public void setIdProizvod(Proizvod idProizvod) {
        this.idProizvod = idProizvod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstavka != null ? idstavka.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stavka)) {
            return false;
        }
        Stavka other = (Stavka) object;
        if ((this.idstavka == null && other.idstavka != null) || (this.idstavka != null && !this.idstavka.equals(other.idstavka))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Stavka[ idstavka=" + idstavka + " ]";
    }
    
}
