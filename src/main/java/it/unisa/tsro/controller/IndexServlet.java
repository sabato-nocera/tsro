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

        String softwareTitle = request.getParameter("softwareTitle");
        String authorName = request.getParameter("authorName");
        String topicLabel = request.getParameter("topicLabel");
        String miPiace = request.getParameter("miPiace");
        String numeroDiCommit = request.getParameter("numeroDiCommit");

        TsroDao tsroDao = new TsroDao();
        List<SoftwareBean> softwareList = tsroDao.recuperaRepositoryPerLaTabellaInIndex(softwareTitle, authorName);

        for (SoftwareBean softwareBean : softwareList) {
            softwareBean.setMiPiace(tsroDao.recuperaRepositoryMiPiace(softwareBean.getRepositoryUrl()));
            softwareBean.setNumeroDiCommit(tsroDao.recuperaRepositoryMainBranchCommit(softwareBean.getRepositoryUrl()));
            softwareBean.setTopicBeanList(tsroDao.recuperaSoftwareTopic(softwareBean.getSoftwareUrl(), topicLabel));
        }

        if (topicLabel != null && !topicLabel.equals("")) {
            softwareList.removeIf(tmp -> tmp.getTopicBeanList().isEmpty());
        }

        if (numeroDiCommit != null && !numeroDiCommit.equals("")) {
            String[] argomenti = numeroDiCommit.split(" ");
            switch (argomenti[0]) {
                case ">":
                    softwareList.removeIf(tmp -> tmp.getNumeroDiCommit() <= Integer.parseInt(argomenti[1]));
                    break;
                case ">=":
                    softwareList.removeIf(tmp -> tmp.getNumeroDiCommit() < Integer.parseInt(argomenti[1]));
                    break;
                case "<":
                    softwareList.removeIf(tmp -> tmp.getNumeroDiCommit() >= Integer.parseInt(argomenti[1]));
                    break;
                case "<=":
                    softwareList.removeIf(tmp -> tmp.getNumeroDiCommit() > Integer.parseInt(argomenti[1]));
                    break;
                case "=":
                    softwareList.removeIf(tmp -> tmp.getNumeroDiCommit() != Integer.parseInt(argomenti[1]));
                    break;
                default:
                    break;
            }
        }

        if (miPiace != null && !miPiace.equals("")) {
            String[] argomenti = miPiace.split(" ");
            switch (argomenti[0]) {
                case ">":
                    softwareList.removeIf(tmp -> tmp.getMiPiace() <= Integer.parseInt(argomenti[1]));
                    break;
                case ">=":
                    softwareList.removeIf(tmp -> tmp.getMiPiace() < Integer.parseInt(argomenti[1]));
                    break;
                case "<":
                    softwareList.removeIf(tmp -> tmp.getMiPiace() >= Integer.parseInt(argomenti[1]));
                    break;
                case "<=":
                    softwareList.removeIf(tmp -> tmp.getMiPiace() > Integer.parseInt(argomenti[1]));
                    break;
                case "=":
                    softwareList.removeIf(tmp -> tmp.getMiPiace() != Integer.parseInt(argomenti[1]));
                    break;
                default:
                    break;
            }
        }

        request.setAttribute("softwareList", softwareList);

        request.getRequestDispatcher("./index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}