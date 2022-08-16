package com.xunmao.demo.pojo;

import java.util.Date;
import java.util.List;

// mysql> describe country;
// +-------------+-------------------+------+-----+-------------------+-----------------------------------------------+
// | Field       | Type              | Null | Key | Default           | Extra                                         |
// +-------------+-------------------+------+-----+-------------------+-----------------------------------------------+
// | country_id  | smallint unsigned | NO   | PRI | NULL              | auto_increment                                |
// | country     | varchar(50)       | NO   |     | NULL              |                                               |
// | last_update | timestamp         | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED on update CURRENT_TIMESTAMP |
// +-------------+-------------------+------+-----+-------------------+-----------------------------------------------+

public class Country {

    private int countryId;
    private String country;
    private List<City> cities;
    private Date lastUpdate;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Country [cities=" + cities + ", country=" + country + ", countryId=" + countryId + ", lastUpdate="
                + lastUpdate + "]";
    }
}
