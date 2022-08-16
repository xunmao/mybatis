package com.xunmao.demo.pojo;

import java.util.Date;

// mysql> desc address;
// +-------------+-------------------+------+-----+-------------------+-----------------------------------------------+
// | Field       | Type              | Null | Key | Default           | Extra                                         |
// +-------------+-------------------+------+-----+-------------------+-----------------------------------------------+
// | address_id  | smallint unsigned | NO   | PRI | NULL              | auto_increment                                |
// | address     | varchar(50)       | NO   |     | NULL              |                                               |
// | address2    | varchar(50)       | YES  |     | NULL              |                                               |
// | district    | varchar(20)       | NO   |     | NULL              |                                               |
// | city_id     | smallint unsigned | NO   | MUL | NULL              |                                               |
// | postal_code | varchar(10)       | YES  |     | NULL              |                                               |
// | phone       | varchar(20)       | NO   |     | NULL              |                                               |
// | location    | geometry          | NO   | MUL | NULL              |                                               |
// | last_update | timestamp         | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED on update CURRENT_TIMESTAMP |
// +-------------+-------------------+------+-----+-------------------+-----------------------------------------------+
// 9 rows in set (0.01 sec)

public class Address {

    private int addressId;
    private String address;
    private String address2;
    private String district;
    private int cityId;
    private String postalCode;
    private Date lastUpdate;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Address [address=" + address + ", address2=" + address2 + ", addressId=" + addressId + ", cityId="
                + cityId + ", district=" + district + ", lastUpdate=" + lastUpdate + ", postalCode=" + postalCode + "]";
    }
}
