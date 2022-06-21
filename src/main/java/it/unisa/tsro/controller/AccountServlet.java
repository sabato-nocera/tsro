package it.unisa.tsro.controller;

import it.unisa.tsro.model.bean.AgentBean;
import it.unisa.tsro.model.bean.UserAccountBean;
import it.unisa.tsro.model.dao.TsroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "accountServlet", value = "/account-servlet")
public class AccountServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accountUrl = request.getParameter("accountUrl");

        TsroDao tsroDao = new TsroDao();
        UserAccountBean accountBean = tsroDao.recuperaUserAccount(accountUrl);

        request.setAttribute("accountBean", accountBean);
        request.getRequestDispatcher("./account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}