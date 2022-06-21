package it.unisa.tsro.controller;

import it.unisa.tsro.model.bean.SoftwareBean;
import it.unisa.tsro.model.bean.SoftwareRepositoryBean;
import it.unisa.tsro.model.dao.TsroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "repositoryServlet", value = "/repository-servlet")
public class RepositoryServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String repositoryUrl = request.getParameter("repositoryUrl");

        TsroDao tsroDao = new TsroDao();
        SoftwareRepositoryBean softwareRepository = tsroDao.recuperaSoftwareRepository(repositoryUrl);
        softwareRepository.setHasForks(tsroDao.askSeSiaFork(repositoryUrl));

        request.setAttribute("softwareRepository", softwareRepository);

        request.getRequestDispatcher("./repository.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}