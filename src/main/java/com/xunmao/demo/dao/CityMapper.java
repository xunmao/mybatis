package com.xunmao.demo.dao;

import java.util.List;
import java.util.Map;

import com.xunmao.demo.pojo.City;

public interface CityMapper {

    public List<City> listCitiesWithLimit(Map<String, Integer> parameterMap);

    public City findCityById(int cityId);
}
