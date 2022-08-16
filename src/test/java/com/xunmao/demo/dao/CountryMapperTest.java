package com.xunmao.demo.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.xunmao.demo.pojo.Country;
import com.xunmao.demo.util.MyBatisUtil;

public class CountryMapperTest {

    @Test
    public void shouldFindCountryById() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            CountryMapper countryMapper = sqlSession.getMapper(com.xunmao.demo.dao.CountryMapper.class);
            Country country = countryMapper.findCountryById(50);
            System.out.println(country);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
