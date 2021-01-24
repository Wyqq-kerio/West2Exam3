package domain;

public class District {
    private Integer iso_dis;
    private String  provinceName;
    private Integer recovered;
    private Integer confirmed;
    private String  updated;
    private  Integer deaths;

    public Integer getIso_dis() {
        return iso_dis;
    }

    public void setIso_dis(Integer iso_dis) {
        this.iso_dis = iso_dis;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    @Override
    public String toString() {
        return "District{" +
                "iso_dis=" + iso_dis +
                ", provinceName='" + provinceName + '\'' +
                ", recovered=" + recovered +
                ", confirmed=" + confirmed +
                ", updated='" + updated + '\'' +
                ", deaths=" + deaths +
                '}';
    }
}
