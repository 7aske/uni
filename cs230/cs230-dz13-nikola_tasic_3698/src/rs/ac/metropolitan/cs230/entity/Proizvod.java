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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "proizvod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proizvod.findAll", query = "SELECT p FROM Proizvod p"),
    @NamedQuery(name = "Proizvod.findByIdProizvod", query = "SELECT p FROM Proizvod p WHERE p.idProizvod = :idProizvod"),
    @NamedQuery(name = "Proizvod.findByNaziv", query = "SELECT p FROM Proizvod p WHERE p.naziv = :naziv"),
    @NamedQuery(name = "Proizvod.findByJedinicamere", query = "SELECT p FROM Proizvod p WHERE p.jedinicamere = :jedinicamere"),
    @NamedQuery(name = "Proizvod.findByCena", query = "SELECT p FROM Proizvod p WHERE p.cena = :cena"),
    @NamedQuery(name = "Proizvod.findByTipproizvod", query = "SELECT p FROM Proizvod p WHERE p.tipproizvod = :tipproizvod")})
public class Proizvod implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROIZVOD")
    private Integer idProizvod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "NAZIV")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "JEDINICAMERE")
    private String jedinicamere;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CENA")
    private long cena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "TIPPROIZVOD")
    private String tipproizvod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProizvod")
    private Collection<Stavka> stavkaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProizvod")
    private Collection<SpecifikacijaProizvodnje> specifikacijaProizvodnjeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProizvod")
    private Collection<Kolicinaproizvoda> kolicinaproizvodaCollection;
    @JoinColumn(name = "IDRECEPT", referencedColumnName = "IDRECEPT")
    @ManyToOne(optional = false)
    private Recept idrecept;

    public Proizvod() {
    }

    public Proizvod(Integer idProizvod) {
        this.idProizvod = idProizvod;
    }

    public Proizvod(Integer idProizvod, String naziv, String jedinicamere, long cena, String tipproizvod) {
        this.idProizvod = idProizvod;
        this.naziv = naziv;
        this.jedinicamere = jedinicamere;
        this.cena = cena;
        this.tipproizvod = tipproizvod;
    }

    public Integer getIdProizvod() {
        return idProizvod;
    }

    public void setIdProizvod(Integer idProizvod) {
        this.idProizvod = idProizvod;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getJedinicamere() {
        return jedinicamere;
    }

    public void setJedinicamere(String jedinicamere) {
        this.jedinicamere = jedinicamere;
    }

    public long getCena() {
        return cena;
    }

    public void setCena(long cena) {
        this.cena = cena;
    }

    public String getTipproizvod() {
        return tipproizvod;
    }

    public void setTipproizvod(String tipproizvod) {
        this.tipproizvod = tipproizvod;
    }

    @XmlTransient
    public Collection<Stavka> getStavkaCollection() {
        return stavkaCollection;
    }

    public void setStavkaCollection(Collection<Stavka> stavkaCollection) {
        this.stavkaCollection = stavkaCollection;
    }

    @XmlTransient
    public Collection<SpecifikacijaProizvodnje> getSpecifikacijaProizvodnjeCollection() {
        return specifikacijaProizvodnjeCollection;
    }

    public void setSpecifikacijaProizvodnjeCollection(Collection<SpecifikacijaProizvodnje> specifikacijaProizvodnjeCollection) {
        this.specifikacijaProizvodnjeCollection = specifikacijaProizvodnjeCollection;
    }

    @XmlTransient
    public Collection<Kolicinaproizvoda> getKolicinaproizvodaCollection() {
        return kolicinaproizvodaCollection;
    }

    public void setKolicinaproizvodaCollection(Collection<Kolicinaproizvoda> kolicinaproizvodaCollection) {
        this.kolicinaproizvodaCollection = kolicinaproizvodaCollection;
    }

    public Recept getIdrecept() {
        return idrecept;
    }

    public void setIdrecept(Recept idrecept) {
        this.idrecept = idrecept;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProizvod != null ? idProizvod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proizvod)) {
            return false;
        }
        Proizvod other = (Proizvod) object;
        if ((this.idProizvod == null && other.idProizvod != null) || (this.idProizvod != null && !this.idProizvod.equals(other.idProizvod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Proizvod[ idProizvod=" + idProizvod + " ]";
    }
    
}
