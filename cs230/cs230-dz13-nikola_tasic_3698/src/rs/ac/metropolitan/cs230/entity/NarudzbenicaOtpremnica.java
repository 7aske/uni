package rs.ac.metropolitan.cs230.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "narudzbenica_otpremnica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NarudzbenicaOtpremnica.findAll", query = "SELECT n FROM NarudzbenicaOtpremnica n"),
    @NamedQuery(name = "NarudzbenicaOtpremnica.findByIdNarudzbenicaOtpremnica", query = "SELECT n FROM NarudzbenicaOtpremnica n WHERE n.idNarudzbenicaOtpremnica = :idNarudzbenicaOtpremnica"),
    @NamedQuery(name = "NarudzbenicaOtpremnica.findByDatumslanja", query = "SELECT n FROM NarudzbenicaOtpremnica n WHERE n.datumslanja = :datumslanja"),
    @NamedQuery(name = "NarudzbenicaOtpremnica.findBySifra", query = "SELECT n FROM NarudzbenicaOtpremnica n WHERE n.sifra = :sifra"),
    @NamedQuery(name = "NarudzbenicaOtpremnica.findByTipnO", query = "SELECT n FROM NarudzbenicaOtpremnica n WHERE n.tipnO = :tipnO")})
public class NarudzbenicaOtpremnica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_NARUDZBENICA_OTPREMNICA")
    private Integer idNarudzbenicaOtpremnica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATUMSLANJA")
    @Temporal(TemporalType.DATE)
    private Date datumslanja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "SIFRA")
    private String sifra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "TIPN_O")
    private String tipnO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNarudzbenicaOtpremnica")
    private Collection<Stavka> stavkaCollection;
    @JoinColumn(name = "IDPROIZVODNAJEDINICA", referencedColumnName = "IDPROIZVODNAJEDINICA")
    @ManyToOne(optional = false)
    private ProizvodnaJedinica idproizvodnajedinica;
    @JoinColumn(name = "IDSKLADISTE", referencedColumnName = "IDSKLADISTE")
    @ManyToOne(optional = false)
    private Skladiste idskladiste;

    public NarudzbenicaOtpremnica() {
    }

    public NarudzbenicaOtpremnica(Integer idNarudzbenicaOtpremnica) {
        this.idNarudzbenicaOtpremnica = idNarudzbenicaOtpremnica;
    }

    public NarudzbenicaOtpremnica(Integer idNarudzbenicaOtpremnica, Date datumslanja, String sifra, String tipnO) {
        this.idNarudzbenicaOtpremnica = idNarudzbenicaOtpremnica;
        this.datumslanja = datumslanja;
        this.sifra = sifra;
        this.tipnO = tipnO;
    }

    public Integer getIdNarudzbenicaOtpremnica() {
        return idNarudzbenicaOtpremnica;
    }

    public void setIdNarudzbenicaOtpremnica(Integer idNarudzbenicaOtpremnica) {
        this.idNarudzbenicaOtpremnica = idNarudzbenicaOtpremnica;
    }

    public Date getDatumslanja() {
        return datumslanja;
    }

    public void setDatumslanja(Date datumslanja) {
        this.datumslanja = datumslanja;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getTipnO() {
        return tipnO;
    }

    public void setTipnO(String tipnO) {
        this.tipnO = tipnO;
    }

    @XmlTransient
    public Collection<Stavka> getStavkaCollection() {
        return stavkaCollection;
    }

    public void setStavkaCollection(Collection<Stavka> stavkaCollection) {
        this.stavkaCollection = stavkaCollection;
    }

    public ProizvodnaJedinica getIdproizvodnajedinica() {
        return idproizvodnajedinica;
    }

    public void setIdproizvodnajedinica(ProizvodnaJedinica idproizvodnajedinica) {
        this.idproizvodnajedinica = idproizvodnajedinica;
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
        hash += (idNarudzbenicaOtpremnica != null ? idNarudzbenicaOtpremnica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NarudzbenicaOtpremnica)) {
            return false;
        }
        NarudzbenicaOtpremnica other = (NarudzbenicaOtpremnica) object;
        if ((this.idNarudzbenicaOtpremnica == null && other.idNarudzbenicaOtpremnica != null) || (this.idNarudzbenicaOtpremnica != null && !this.idNarudzbenicaOtpremnica.equals(other.idNarudzbenicaOtpremnica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.NarudzbenicaOtpremnica[ idNarudzbenicaOtpremnica=" + idNarudzbenicaOtpremnica + " ]";
    }
    
}
