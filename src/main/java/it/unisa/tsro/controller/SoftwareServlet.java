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
        String topicUrl = request.getParameter("topicUrl");

        TsroDao tsroDao = new TsroDao();

        tsroDao.insertTopic(softwareUrl, topicUrl);

        List<SoftwareBean> softwareList = tsroDao.recuperaRepositoryPerLaTabellaInIndex(null, null);

        for (SoftwareBean softwareBean : softwareList) {
            softwareBean.setMiPiace(tsroDao.recuperaRepositoryMiPiace(softwareBean.getRepositoryUrl()));
            softwareBean.setNumeroDiCommit(tsroDao.recuperaRepositoryMainBranchCommit(softwareBean.getRepositoryUrl()));
            softwareBean.setTopicBeanList(tsroDao.recuperaSoftwareTopic(softwareBean.getSoftwareUrl(), null));
        }

        SoftwareBean software = null;

        for (SoftwareBean softwareBean : softwareList) {
            if (softwareBean.getSoftwareUrl() != null && !softwareBean.getSoftwareUrl().getURI().equals("") &&
                    softwareUrl != null && !softwareUrl.equals("") &&
                    softwareBean.getSoftwareUrl().getURI().equals(softwareUrl)) {
                software = softwareBean;
                break;
            }
        }

        if (software != null) {
            software.setLicensa(tsroDao.recuperaLicensa(softwareUrl));
        }

        request.setAttribute("software", software);

        request.getRequestDispatcher("./software.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}