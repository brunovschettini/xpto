package br.com.xptosystems.address;

public class Cities implements Comparable<Cities> {

    private Long ibgeId;
    private String uf;
    private String name;
    private Boolean capital;
    private Double lon;
    private Double lat;
    private String no_accents;
    private String alternative_names;
    private String micro_region;
    private String meso_region;

    public Cities() {
        this.ibgeId = null;
        this.uf = "";
        this.name = "";
        this.capital = false;
        this.lon = null;
        this.lat = null;
        this.no_accents = "";
        this.alternative_names = "";
        this.micro_region = "";
        this.meso_region = "";
    }

    public Cities(Long ibgeId, String uf, String name, Boolean capital, Double lon, Double lat, String no_accents, String alternative_names, String micro_region, String meso_region) {
        this.ibgeId = ibgeId;
        this.uf = uf;
        this.name = name;
        this.capital = capital;
        this.lon = lon;
        this.lat = lat;
        this.no_accents = no_accents;
        this.alternative_names = alternative_names;
        this.micro_region = micro_region;
        this.meso_region = meso_region;
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

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getNo_accents() {
        return no_accents;
    }

    public void setNo_accents(String no_accents) {
        this.no_accents = no_accents;
    }

    public String getAlternative_names() {
        return alternative_names;
    }

    public void setAlternative_names(String alternative_names) {
        this.alternative_names = alternative_names;
    }

    public String getMicro_region() {
        return micro_region;
    }

    public void setMicro_region(String micro_region) {
        this.micro_region = micro_region;
    }

    public String getMeso_region() {
        return meso_region;
    }

    public void setMeso_region(String meso_region) {
        this.meso_region = meso_region;
    }

    @Override
    public int compareTo(Cities c) {
        return this.name.compareTo(c.getName());
    }

}
