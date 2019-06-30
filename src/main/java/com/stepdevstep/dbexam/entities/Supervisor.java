package com.stepdevstep.dbexam.entities;

import javax.persistence.*;

@Entity
@Table(name = "supervisor")
public class Supervisor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=-1;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "pass")
    private String pass;
    @Column(name = "role", columnDefinition = "ENUM('ROOT','ADMIN')")
    private String role;

    public Supervisor(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public Supervisor() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + "*****" + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
