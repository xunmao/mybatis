package com.xunmao.demo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.xunmao.demo.pojo.Actor;
import com.xunmao.demo.util.MyBatisUtil;

public class ActorMapperTest {

    @Test
    public void shouldListActors() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            ActorMapper actorMapper = sqlSession.getMapper(com.xunmao.demo.dao.ActorMapper.class);

            List<Actor> actors = actorMapper.listActors();
            for (Actor actor : actors) {
                System.out.println(actor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldListActorsWithLimit() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            ActorMapper actorMapper = sqlSession.getMapper(com.xunmao.demo.dao.ActorMapper.class);

            Map<String, Integer> parameterMap = new HashMap<>();
            parameterMap.put("startIndex", 0);
            parameterMap.put("pageSize", 10);

            List<Actor> actors = actorMapper.listActorsWithLimit(parameterMap);
            for (Actor actor : actors) {
                System.out.println(actor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldListActorsByLastNameLike() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            ActorMapper actorMapper = sqlSession.getMapper(com.xunmao.demo.dao.ActorMapper.class);

            // 切记：不要遗漏通配符 %%
            List<Actor> actors = actorMapper.listActorsByLastNameLike("%NI%");
            for (Actor actor : actors) {
                System.out.println(actor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldListActorsLike() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            ActorMapper actorMapper = sqlSession.getMapper(com.xunmao.demo.dao.ActorMapper.class);

            Map<String, String> parameterMap = new HashMap<>();
            parameterMap.put("firstName", "%TT%");
            parameterMap.put("lastName", "%NI%");
            // 切记：不要遗漏通配符 %%
            List<Actor> actors = actorMapper.listActorsLike(parameterMap);
            for (Actor actor : actors) {
                System.out.println(actor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldFindActorById() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            ActorMapper actorMapper = sqlSession.getMapper(com.xunmao.demo.dao.ActorMapper.class);

            Actor actor = actorMapper.findActorById(9999);
            System.out.println(actor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldAddActor() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            ActorMapper actorMapper = sqlSession.getMapper(com.xunmao.demo.dao.ActorMapper.class);

            Date lastUpdate = new Date(System.currentTimeMillis());
            Actor actor = new Actor(9999, "firstName", "lastName", lastUpdate);
            actorMapper.addActor(actor);

            // 切记：增删改需要提交事物
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldUpdateActor() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            ActorMapper actorMapper = sqlSession.getMapper(com.xunmao.demo.dao.ActorMapper.class);

            Date lastUpdate = new Date(System.currentTimeMillis());
            Actor actor = new Actor(9999, "newFirstName", "newLastName", lastUpdate);
            actorMapper.updateActor(actor);

            // 切记：增删改需要提交事物
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldUpdateActorWithMap() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            ActorMapper actorMapper = sqlSession.getMapper(com.xunmao.demo.dao.ActorMapper.class);

            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("actorId", 9999);
            parameterMap.put("firstName", "newfirstName");
            parameterMap.put("lastName", null);
            actorMapper.updateActorWithMap(parameterMap);

            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldDeleteActor() {
        try (SqlSession sqlSession = new MyBatisUtil().getSqlSession()) {
            ActorMapper actorMapper = sqlSession.getMapper(com.xunmao.demo.dao.ActorMapper.class);

            actorMapper.deleteActor(9999);

            // 切记：增删改需要提交事物
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
