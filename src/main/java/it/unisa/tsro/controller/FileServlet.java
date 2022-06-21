package it.unisa.tsro.controller;

import it.unisa.tsro.model.bean.SoftwareBean;
import it.unisa.tsro.model.bean.SoftwareRepositoryBean;
import it.unisa.tsro.model.dao.TsroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "fileServlet", value = "/file-servlet")
public class FileServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String repositoryUrl = request.getParameter("repositoryUrl");

        TsroDao tsroDao = new TsroDao();
        Model model = tsroDao.costruisciFileInUltimoCommit(repositoryUrl);

        Writer writer = response.getWriter();
        model.write(writer, "TURTLE");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}