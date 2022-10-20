package com.skupina7.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import java.io.IOException;

@WebServlet("/servlet") 
public class PrviJdbcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {

            final String service_name = ConfigurationUtil.getInstance().get("kumuluzee.name").get();
            final String service_version = ConfigurationUtil.getInstance().get("kumuluzee.version").get();
            final String environment_name = ConfigurationUtil.getInstance().get("kumuluzee.env.name").get();

            resp.getWriter().println("Hello fellow java champion!\n\n" +
                                     "service.name: " + service_name + "\n" + 
                                     "service.version: " + service_version + "\n" +
                                     "service.environment.name: " + environment_name);
    }
}