package com.stepdevstep.dbexam.dao;

import com.stepdevstep.dbexam.entities.Supervisor;

import java.util.List;

public interface SupervisorDAOi {
    int add(Supervisor sup);

    void update(Supervisor sup);

    void delete(int id);

    Supervisor get(String login, String pass);

    List<Supervisor> getAll();

    boolean exists(String login, String pass);
}
