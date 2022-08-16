package com.xunmao.demo.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.xunmao.demo.pojo.Address;
import com.xunmao.demo.util.MyBatisUtil;

public class AddressMapperTest {

    @Test
    public void shouldFindAddressById() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            AddressMapper addressMapper = sqlSession.getMapper(com.xunmao.demo.dao.AddressMapper.class);

            Address address = addressMapper.findAddressById(1);
            System.out.println(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
