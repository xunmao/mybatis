package com.xunmao.demo.dao;

import java.util.List;
import java.util.Map;

import com.xunmao.demo.pojo.Actor;

public interface ActorMapper {

    public List<Actor> listActors();

    public List<Actor> listActorsWithLimit(Map<String, Integer> parameterMap);

    public List<Actor> listActorsByLastNameLike(String value);

    public List<Actor> listActorsLike(Map<String, String> parameterMap);

    public Actor findActorById(int actorId);

    public void addActor(Actor actor);

    public void updateActor(Actor actor);

    public void updateActorWithMap(Map<String, Object> parameterMap);

    public void deleteActor(int actorId);
}
