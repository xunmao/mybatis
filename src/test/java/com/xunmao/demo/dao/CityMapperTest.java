package com.xunmao.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.xunmao.demo.pojo.City;
import com.xunmao.demo.util.MyBatisUtil;

public class CityMapperTest {

    @Test
    public void shouldListCitiesWithLimit() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            CityMapper cityMapper = sqlSession.getMapper(com.xunmao.demo.dao.CityMapper.class);

            Map<String, Integer> parameterMap = new HashMap<>();
            parameterMap.put("startIndex", 0);
            parameterMap.put("pageSize", 10);

            List<City> cities = cityMapper.listCitiesWithLimit(parameterMap);
            for (City city : cities) {
                System.out.println(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldFindCityById() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            CityMapper cityMapper = sqlSession.getMapper(com.xunmao.demo.dao.CityMapper.class);

            City city = cityMapper.findCityById(1);
            System.out.println(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
