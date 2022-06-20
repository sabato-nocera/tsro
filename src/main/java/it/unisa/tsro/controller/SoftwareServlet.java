package it.unisa.tsro.controller;

import java.io.*;
import java.util.List;

import it.unisa.tsro.model.bean.SoftwareBean;
import it.unisa.tsro.model.dao.TsroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "softwareServlet", value = "/software-servlet")
public class SoftwareServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String softwareUrl = request.getParameter("softwareUrl");
        System.out.println(softwareUrl);
        request.getRequestDispatcher("./software.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}