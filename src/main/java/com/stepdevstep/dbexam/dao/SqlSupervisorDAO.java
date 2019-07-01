package com.stepdevstep.dbexam.dao;

import com.stepdevstep.dbexam.persistence.SQLConnectionBuilder;
import com.stepdevstep.dbexam.entities.Supervisor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlSupervisorDAO implements SupervisorDAOi {
    private static final String INSERT = "INSERT INTO supervisor (login,pass) VALUES(?,?)";
    private static final String UPDATE = "UPDATE supervisor SET login=?, pass=? WHERE id=?";
    private static final String DELETE = "DELETE FROM supervisor WHERE id=?";
    private static final String SELECT = "SELECT * FROM supervisor WHERE login=? AND pass=?";
    private static final String SELECT_ALL = "SELECT * FROM supervisor";
    private static final String EXISTS = "SELECT EXISTS(SELECT * FROM supervisor WHERE login=? AND pass=?)";

    @Override
    public int add(Supervisor sup) {

        int id = -1;
        try (Connection con = SQLConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT, new String[]{"id"})) {
            ps.setString(1, sup.getLogin());
            ps.setString(2, sup.getPass());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Book creating failed, no rows affected.");

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(Supervisor sup) {
        try (Connection con = SQLConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE)) {
            ps.setString(1, sup.getLogin());
            ps.setString(2, sup.getPass());
            ps.setInt(3, sup.getId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0)
                throw new SQLException("Supervisor updating failed, no rows affected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = SQLConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(String login, String pass) {
        boolean value = false;
        try (Connection con = SQLConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(EXISTS)) {
            ps.setString(1, login);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    value = rs.getBoolean(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public Supervisor get(String login, String pass) {
        Supervisor sup = null;
        try (Connection con = SQLConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT)) {
            ps.setString(1, login);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    sup = fill(rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sup;
    }

    @Override
    public List<Supervisor> getAll() {
        List<Supervisor> sup = new ArrayList<>();
        try (Connection con = SQLConnectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    sup.add(fill(rs));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sup;
    }

    private Supervisor fill(ResultSet rs) {
        Supervisor sup = new Supervisor();
        try {
            sup.setId(rs.getInt("id"));
            sup.setLogin(rs.getString("login"));
            sup.setPass(rs.getString("pass"));
            sup.setRole(rs.getString("role"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sup;
    }
}
