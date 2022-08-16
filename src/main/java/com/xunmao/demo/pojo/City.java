package com.xunmao.demo.pojo;

import java.util.Date;

// mysql> describe city;
// +-------------+-------------------+------+-----+-------------------+-----------------------------------------------+
// | Field       | Type              | Null | Key | Default           | Extra                                         |
// +-------------+-------------------+------+-----+-------------------+-----------------------------------------------+
// | city_id     | smallint unsigned | NO   | PRI | NULL              | auto_increment                                |
// | city        | varchar(50)       | NO   |     | NULL              |                                               |
// | country_id  | smallint unsigned | NO   | MUL | NULL              |                                               |
// | last_update | timestamp         | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED on update CURRENT_TIMESTAMP |
// +-------------+-------------------+------+-----+-------------------+-----------------------------------------------+

public class City {

    private int cityId;
    private String city;
    private String countryId;
    private Address address;
    private Date lastUpdate;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "City [address=" + address + ", city=" + city + ", cityId=" + cityId + ", countryId=" + countryId
                + ", lastUpdate=" + lastUpdate + "]";
    }
}
