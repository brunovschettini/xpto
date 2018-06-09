package br.com.xptosystems.address;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "cities",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "uf", "capital"})
)
public class Cities implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "ibge_id", unique = true, nullable = false)
    private Long ibgeId;
    @Column(name = "uf", length = 2, nullable = false)
    private String uf;
    @Column(name = "name", length = 150, nullable = false)
    private String name;
    @Column(name = "capital", nullable = false, columnDefinition = "boolean default 0")
    private Boolean capital;
    @Digits(integer = 10, fraction = 12)
    @Column(name = "lon", nullable = true)
    private BigDecimal lon;
    @Digits(integer = 10, fraction = 12)
    @Column(name = "lat", nullable = true)
    private BigDecimal lat;
    @Column(name = "no_accents", length = 150, nullable = false)
    private String noAccents;
    @Column(name = "alternative_accents", length = 150, nullable = false)
    private String alternativeNames;
    @Column(name = "micro_region", length = 100, nullable = false)
    private String microRegion;
    @Column(name = "micro_region", length = 100, nullable = false)
    private String mesoRegion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "register_date", nullable = false)
    private Date registerDate;

    public Cities() {
        this.id = null;
        this.ibgeId = null;
        this.uf = "";
        this.name = "";
        this.capital = false;
        this.lon = null;
        this.lat = null;
        this.noAccents = "";
        this.alternativeNames = "";
        this.microRegion = "";
        this.mesoRegion = "";
        this.registerDate = new Date();
    }

    public Cities(Long id, Long ibgeId, String uf, String name, Boolean capital, BigDecimal lon, BigDecimal lat, String noAccents, String alternativeNames, String microRegion, String mesoRegion, Date registerDate) {
        this.id = id;
        this.ibgeId = ibgeId;
        this.uf = uf;
        this.name = name;
        this.capital = capital;
        this.lon = lon;
        this.lat = lat;
        this.noAccents = noAccents;
        this.alternativeNames = alternativeNames;
        this.microRegion = microRegion;
        this.mesoRegion = mesoRegion;
        this.registerDate = registerDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIbgeId() {
        return ibgeId;
    }

    public void setIbgeId(Long ibgeId) {
        this.ibgeId = ibgeId;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCapital() {
        return capital;
    }

    public void setCapital(Boolean capital) {
        this.capital = capital;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getNoAccents() {
        return noAccents;
    }

    public void setNoAccents(String noAccents) {
        this.noAccents = noAccents;
    }

    public String getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(String alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public String getMicroRegion() {
        return microRegion;
    }

    public void setMicroRegion(String microRegion) {
        this.microRegion = microRegion;
    }

    public String getMesoRegion() {
        return mesoRegion;
    }

    public void setMesoRegion(String mesoRegion) {
        this.mesoRegion = mesoRegion;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

}
