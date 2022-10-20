package com.skupina7.jdbc;

import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.ResultSet;

/**
 * Razred UporabnikDaoImpl implementira vmesnik UporabnikDao.
 * Vsebuje JCBC operacije nad podatkovno bazo.
 * Razred je pripravljen po vzorcu singleton - instanca razreda naj se ustvari
 * samo enkrat.
 */
public class UporabnikDaoImpl implements BaseDao {

    private static UporabnikDaoImpl instance;
    private static final Logger log = Logger.getLogger(UporabnikDaoImpl.class.getName());

    private Connection connection;

    public static UporabnikDaoImpl getInstance() {
        if (instance == null) {
            instance = new UporabnikDaoImpl();
        }
        return instance;
    }

    public UporabnikDaoImpl() {
        connection = getConnection();
    }

    private Uporabnik getUporabnikFromRS(ResultSet rs) throws SQLException {
        String ime = rs.getString("ime");
        String priimek = rs.getString("priimek");
        String uporabniskoIme = rs.getString("uporabniskoime");
        return new Uporabnik(ime, priimek, uporabniskoIme);
    }

    @Override
    public Connection getConnection() {
        try {
            InitialContext initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("jdbc/SimpleJdbcDS");
            return ds.getConnection();
        } catch (Exception e) {
            log.severe("Cannot get connection" + e.getMessage());
            return null;
        }
    }

    @Override
    public Entiteta vrni(int id) {

        PreparedStatement ps = null;

        try {
            if (connection == null) {
                connection = getConnection();
            }

            String sql = "SELECT * FROM uporabnik WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return getUporabnikFromRS(rs);
            } else {
                log.info("Uporabnik ne obstaja");
            }
        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                log.severe(e.toString());
            }
        }
        return null;
    }


    @Override
    public void vstavi(Entiteta ent) {
        PreparedStatement ps = null;
        try {
            if (connection == null) {
                connection = getConnection();
            }
            String sql = "INSERT INTO uporabnik (ime, priimek, uporabniskoime) VALUES (?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, ((Uporabnik) ent).getIme());
            ps.setString(2, ((Uporabnik) ent).getPriimek());
            ps.setString(3, ((Uporabnik) ent).getUporabniskoIme());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                log.severe(e.toString());
            }
        }
    }

    @Override
    public void odstrani(int id) {
        PreparedStatement ps = null;
        try {
            if (connection == null) {
                connection = getConnection();
            }
            String sql = "DELETE FROM uporabnik WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                log.severe(e.toString());
            }
        }
    }

    @Override
    public void posodobi(Entiteta ent) {
        PreparedStatement ps = null;
        try {
            if (connection == null) {
                connection = getConnection();
            }
            String sql = "UPDATE uporabnik SET ime = ?, priimek = ?, uporabniskoime = ? WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, ((Uporabnik) ent).getIme());
            ps.setString(2, ((Uporabnik) ent).getPriimek());
            ps.setString(3, ((Uporabnik) ent).getUporabniskoIme());
            ps.setInt(4, ((Uporabnik) ent).getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                log.severe(e.toString());
            }
        }
    }

    @Override
    public List<Entiteta> vrniVse() {
        PreparedStatement ps = null;
        List<Entiteta> uporabniki = new ArrayList<Entiteta>();
        try {
            if (connection == null) {
                connection = getConnection();
            }
            String sql = "SELECT * FROM uporabnik";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                uporabniki.add(getUporabnikFromRS(rs));
            }
        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                log.severe(e.toString());
            }
        }
        return uporabniki;
    }
}
