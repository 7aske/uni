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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "skladiste")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skladiste.findAll", query = "SELECT s FROM Skladiste s"),
    @NamedQuery(name = "Skladiste.findByIdskladiste", query = "SELECT s FROM Skladiste s WHERE s.idskladiste = :idskladiste"),
    @NamedQuery(name = "Skladiste.findByNaziv", query = "SELECT s FROM Skladiste s WHERE s.naziv = :naziv"),
    @NamedQuery(name = "Skladiste.findByMesto", query = "SELECT s FROM Skladiste s WHERE s.mesto = :mesto"),
    @NamedQuery(name = "Skladiste.findByUkupnikapacitetzaliha", query = "SELECT s FROM Skladiste s WHERE s.ukupnikapacitetzaliha = :ukupnikapacitetzaliha")})
public class Skladiste implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSKLADISTE")
    private Integer idskladiste;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "NAZIV")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "MESTO")
    private String mesto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UKUPNIKAPACITETZALIHA")
    private double ukupnikapacitetzaliha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idskladiste")
    private Collection<Kolicinaproizvoda> kolicinaproizvodaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idskladiste")
    private Collection<NarudzbenicaOtpremnica> narudzbenicaOtpremnicaCollection;

    public Skladiste() {
    }

    public Skladiste(Integer idskladiste) {
        this.idskladiste = idskladiste;
    }

    public Skladiste(Integer idskladiste, String naziv, String mesto, double ukupnikapacitetzaliha) {
        this.idskladiste = idskladiste;
        this.naziv = naziv;
        this.mesto = mesto;
        this.ukupnikapacitetzaliha = ukupnikapacitetzaliha;
    }

    public Integer getIdskladiste() {
        return idskladiste;
    }

    public void setIdskladiste(Integer idskladiste) {
        this.idskladiste = idskladiste;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public double getUkupnikapacitetzaliha() {
        return ukupnikapacitetzaliha;
    }

    public void setUkupnikapacitetzaliha(double ukupnikapacitetzaliha) {
        this.ukupnikapacitetzaliha = ukupnikapacitetzaliha;
    }

    @XmlTransient
    public Collection<Kolicinaproizvoda> getKolicinaproizvodaCollection() {
        return kolicinaproizvodaCollection;
    }

    public void setKolicinaproizvodaCollection(Collection<Kolicinaproizvoda> kolicinaproizvodaCollection) {
        this.kolicinaproizvodaCollection = kolicinaproizvodaCollection;
    }

    @XmlTransient
    public Collection<NarudzbenicaOtpremnica> getNarudzbenicaOtpremnicaCollection() {
        return narudzbenicaOtpremnicaCollection;
    }

    public void setNarudzbenicaOtpremnicaCollection(Collection<NarudzbenicaOtpremnica> narudzbenicaOtpremnicaCollection) {
        this.narudzbenicaOtpremnicaCollection = narudzbenicaOtpremnicaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idskladiste != null ? idskladiste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skladiste)) {
            return false;
        }
        Skladiste other = (Skladiste) object;
        if ((this.idskladiste == null && other.idskladiste != null) || (this.idskladiste != null && !this.idskladiste.equals(other.idskladiste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Skladiste[ idskladiste=" + idskladiste + " ]";
    }
    
}
