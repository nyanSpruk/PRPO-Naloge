package com.skupina7.naloga;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import java.io.IOException;

@WebServlet("/servlet") // Kje servlet poslusa
public class PrviJdbcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {

            System.out.println("This is my first step to becoming a java champion!");

            resp.getWriter().println("Hello fellow java champion!");

            final String service_name = ConfigurationUtil.getInstance().get("kumuluzee.name").get();
            final String service_version = ConfigurationUtil.getInstance().get("kumuluzee.version").get();
            final String environment_name = ConfigurationUtil.getInstance().get("kumuluzee.env.name").get();

            resp.getWriter().println("service.name: " + service_name + "\n" + 
                                     "service.version: " + service_version + "\n" +
                                     "service.environment.name: " + environment_name);
    }
}