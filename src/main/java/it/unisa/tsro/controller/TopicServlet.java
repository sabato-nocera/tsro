package it.unisa.tsro.controller;

import it.unisa.tsro.model.dao.TsroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import java.io.*;
import java.util.List;

@WebServlet(name = "topicServlet", value = "/topic-servlet")
public class TopicServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String topicUrl = request.getParameter("topicUrl");

        TsroDao tsroDao = new TsroDao();
        Model model = tsroDao.describeTopic(topicUrl);

        StmtIterator stmtIterator = model.listStatements();
        List<Statement> statementList = stmtIterator.toList();

        request.setAttribute("statementList", statementList);

        request.getRequestDispatcher("./topic.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}