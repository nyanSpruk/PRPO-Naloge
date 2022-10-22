package com.skupina7.jdbc;

import java.sql.Connection;
import java.util.List;

/**
 * Pri delu s podatkovno bazo bomo sledili vzorcu DAO (Data Access Object).
 * Vmesnik BaseDao predstavlja osnovni vmesnik za dostop do podatkovne baze.
 * Vsebuje osnovne operacije nad entitietami, ki jih bomo shranjevali v podatkovno bazo.
 */
public interface BaseDao {
    Connection getConnection();

    Entiteta vrni(int id);

    void vstavi(Entiteta ent);

    void odstrani(int id);

    void posodobi(Entiteta ent);

    List<Entiteta> vrniVse();
}
