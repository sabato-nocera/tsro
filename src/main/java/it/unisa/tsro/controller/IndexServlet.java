package it.unisa.tsro.controller;

import java.io.*;
import java.util.List;

import it.unisa.tsro.model.bean.SoftwareBean;
import it.unisa.tsro.model.dao.TsroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "indexServlet", value = "/index-servlet")
public class IndexServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TsroDao tsroDao = new TsroDao();
        List<SoftwareBean> softwareList = tsroDao.recuperaRepositoryPerLaTabellaInIndex();
        for (SoftwareBean softwareBean : softwareList) {
            softwareBean.setMiPiace(tsroDao.recuperaRepositoryMiPiace(softwareBean.getRepositoryUrl()));
            softwareBean.setNumeroDiCommit(tsroDao.recuperaRepositoryMainBranchCommit(softwareBean.getRepositoryUrl()));
        }

        request.getRequestDispatcher("./index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}