package com.xunmao.demo.dao;

import com.xunmao.demo.pojo.Country;

public interface CountryMapper {

    Country findCountryById(int countryId);
}
