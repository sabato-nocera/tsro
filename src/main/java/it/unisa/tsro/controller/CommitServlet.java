package it.unisa.tsro.controller;

import it.unisa.tsro.model.bean.CommitBean;
import it.unisa.tsro.model.dao.TsroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "commitServlet", value = "/commit-servlet")
public class CommitServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commitUrl = request.getParameter("commitUrl");

        TsroDao tsroDao = new TsroDao();
        CommitBean commit = tsroDao.recuperaCommit(commitUrl);

        request.setAttribute("commit", commit);

        request.getRequestDispatcher("./commit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}