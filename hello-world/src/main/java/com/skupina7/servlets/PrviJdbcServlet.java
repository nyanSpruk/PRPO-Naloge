package com.skupina7.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import com.skupina7.jdbc.UporabnikDaoImpl;
import com.skupina7.jdbc.BaseDao;
import com.skupina7.jdbc.Uporabnik;
import java.util.List;
import com.skupina7.jdbc.Entiteta;

@WebServlet("/servlet") 
public class PrviJdbcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        // branje konfiguracije
        Optional<String> microserviceName = ConfigurationUtil.getInstance().get("kumuluzee.name");
        microserviceName.ifPresent(s -> writer.println("Ime mikrostoritve: " + s + "\n"));

        // dostop do podatkovne baze
        BaseDao uporabnikDao = UporabnikDaoImpl.getInstance();
        Uporabnik firstUser = new Uporabnik("Janez", "Novak", "janeznovak");
        Uporabnik secondUser = new Uporabnik("Edon", "Kuklec", "edone");

        // dodaj uporabnika
        writer.append("Dodajam uporabnika: \n" + firstUser.toString() + "\n");
        uporabnikDao.vstavi(firstUser);
        writer.append("Dodajam uporabnika: \n" + secondUser.toString() + "\n");
        uporabnikDao.vstavi(secondUser);

        // odstrani uporabnika
        writer.append("Odstranim uporabnika: \n" + firstUser.toString() + "\n");
        uporabnikDao.odstrani(firstUser.getId());

        // posodobi uporabnika
        writer.append("Posodabljam uporabnika: \n" + secondUser.toString() + "\n");
        secondUser.setIme("Edon");
        secondUser.setPriimek("Kuklec");
        secondUser.setUporabniskoIme("edone");
        uporabnikDao.posodobi(secondUser);

        // // izpis vseh uporabnikov
        writer.append("Seznam obstojecih uporanikov: \n");
        List<Entiteta> uporabniki = uporabnikDao.vrniVse();
        uporabniki.stream().forEach(uporabnik -> writer.append(uporabnik.toString() + "\n"));
    }
}