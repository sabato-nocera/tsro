package it.unisa.tsro.controller;

import it.unisa.tsro.model.bean.AgentBean;
import it.unisa.tsro.model.dao.TsroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "agentServlet", value = "/agent-servlet")
public class AgentServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String agentUrl = request.getParameter("agentUrl");

        TsroDao tsroDao = new TsroDao();
        AgentBean agentBean = tsroDao.recuperaAgent(agentUrl);
        agentBean.setFromTheCloud(tsroDao.recuperaAgentInfosFromTheCloud(agentUrl));

        request.setAttribute("agentBean", agentBean);

        request.getRequestDispatcher("./agent.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}