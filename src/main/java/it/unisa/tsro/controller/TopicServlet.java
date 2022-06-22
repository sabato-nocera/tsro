package it.unisa.tsro.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@WebServlet(name = "topicServlet", value = "/topic-servlet")
public class TopicServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String topicUrl = request.getParameter("topicUrl");
        String targetURL = "https://dbpedia.org/sparql";
        String urlParameters = "query=DESCRIBE+<" + topicUrl + ">";
        String httpGetRequestURL = targetURL + "?" + urlParameters;

        URL url = new URL(httpGetRequestURL);
        URLConnection urlConnection = url.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        StringBuilder string = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            string.append(inputLine).append("\n");
        in.close();

        response.setContentType("text/html");
        response.getWriter().write(String.valueOf(string));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}