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
@Table(name = "proizvodna_jedinica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProizvodnaJedinica.findAll", query = "SELECT p FROM ProizvodnaJedinica p"),
    @NamedQuery(name = "ProizvodnaJedinica.findByIdproizvodnajedinica", query = "SELECT p FROM ProizvodnaJedinica p WHERE p.idproizvodnajedinica = :idproizvodnajedinica"),
    @NamedQuery(name = "ProizvodnaJedinica.findByNaziv", query = "SELECT p FROM ProizvodnaJedinica p WHERE p.naziv = :naziv"),
    @NamedQuery(name = "ProizvodnaJedinica.findByMesto", query = "SELECT p FROM ProizvodnaJedinica p WHERE p.mesto = :mesto"),
    @NamedQuery(name = "ProizvodnaJedinica.findByUkupankapacitetproizvodnje", query = "SELECT p FROM ProizvodnaJedinica p WHERE p.ukupankapacitetproizvodnje = :ukupankapacitetproizvodnje")})
public class ProizvodnaJedinica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPROIZVODNAJEDINICA")
    private Integer idproizvodnajedinica;
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
    @Column(name = "UKUPANKAPACITETPROIZVODNJE")
    private double ukupankapacitetproizvodnje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproizvodnajedinica")
    private Collection<SpecifikacijaProizvodnje> specifikacijaProizvodnjeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproizvodnajedinica")
    private Collection<NarudzbenicaOtpremnica> narudzbenicaOtpremnicaCollection;

    public ProizvodnaJedinica() {
    }

    public ProizvodnaJedinica(Integer idproizvodnajedinica) {
        this.idproizvodnajedinica = idproizvodnajedinica;
    }

    public ProizvodnaJedinica(Integer idproizvodnajedinica, String naziv, String mesto, double ukupankapacitetproizvodnje) {
        this.idproizvodnajedinica = idproizvodnajedinica;
        this.naziv = naziv;
        this.mesto = mesto;
        this.ukupankapacitetproizvodnje = ukupankapacitetproizvodnje;
    }

    public Integer getIdproizvodnajedinica() {
        return idproizvodnajedinica;
    }

    public void setIdproizvodnajedinica(Integer idproizvodnajedinica) {
        this.idproizvodnajedinica = idproizvodnajedinica;
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

    public double getUkupankapacitetproizvodnje() {
        return ukupankapacitetproizvodnje;
    }

    public void setUkupankapacitetproizvodnje(double ukupankapacitetproizvodnje) {
        this.ukupankapacitetproizvodnje = ukupankapacitetproizvodnje;
    }

    @XmlTransient
    public Collection<SpecifikacijaProizvodnje> getSpecifikacijaProizvodnjeCollection() {
        return specifikacijaProizvodnjeCollection;
    }

    public void setSpecifikacijaProizvodnjeCollection(Collection<SpecifikacijaProizvodnje> specifikacijaProizvodnjeCollection) {
        this.specifikacijaProizvodnjeCollection = specifikacijaProizvodnjeCollection;
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
        hash += (idproizvodnajedinica != null ? idproizvodnajedinica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProizvodnaJedinica)) {
            return false;
        }
        ProizvodnaJedinica other = (ProizvodnaJedinica) object;
        if ((this.idproizvodnajedinica == null && other.idproizvodnajedinica != null) || (this.idproizvodnajedinica != null && !this.idproizvodnajedinica.equals(other.idproizvodnajedinica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProizvodnaJedinica[ idproizvodnajedinica=" + idproizvodnajedinica + " ]";
    }
    
}
