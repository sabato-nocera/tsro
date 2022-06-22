package it.unisa.tsro.controller;

import it.unisa.tsro.model.bean.BranchBean;
import it.unisa.tsro.model.dao.TsroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "branchServlet", value = "/branch-servlet")
public class BranchServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String branchUrl = request.getParameter("branchUrl");

        TsroDao tsroDao = new TsroDao();
        BranchBean branchBean = tsroDao.recuperaBranch(branchUrl);

        request.setAttribute("branchBean", branchBean);

        request.getRequestDispatcher("./branch.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}