package it.unisa.tsro.model.dao;

import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import java.util.Iterator;

public class TsroDao {

    private final String szEndpoint = "http://localhost:8080/jena-fuseki-war-4.5.0/tsro/query";

    private final String prefix = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX fo: <http://purl.org/ontology/fo/>\n" +
            "PREFIX db: <https://dbpedia.org/>\n" +
            "PREFIX frapo: <http://purl.org/cerif/frapo/>\n" +
            "PREFIX fr: <https://w3id.org/fr/def/core#>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX tsro: <http://www.semanticweb.org/sabato/ontologies/2022/5/tsro#>\n" +
            "PREFIX ns: <http://rdfs.org/sioc/ns#>\n";

    public void retrieve() {

        String szQuery = prefix + "SELECT ?company WHERE { { ?userAccount ns:account_of ?company. ?company a <https://dbpedia.org/ontology/Company>. } }";

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(szEndpoint, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        int iCount = 0;
        ResultSet rs = qexec.execSelect();
        while (rs.hasNext()) {
            QuerySolution qs = rs.next();

            Iterator<String> itVars = qs.varNames();

            iCount++;
            System.out.println("Result " + iCount + ": ");

            while (itVars.hasNext()) {
                String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();

                System.out.println("[" + szVar + "]: " + szVal);
            }
        }
        qexec.close();
    }
}
