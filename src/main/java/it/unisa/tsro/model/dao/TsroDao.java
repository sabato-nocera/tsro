package it.unisa.tsro.model.dao;

import it.unisa.tsro.model.bean.SoftwareBean;
import it.unisa.tsro.model.bean.TopicBean;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TsroDao {
    private static final Logger LOGGER = Logger.getLogger(TsroDao.class.getName());
    private final String szEndpoint = "http://localhost:8080/jena-fuseki-war-4.5.0/theSoftwareRepositoryOntology/query";

    private final String prefix = "PREFIX sd: <https://w3id.org/okn/o/sd#>\n" +
            "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
            "PREFIX fo: <http://purl.org/ontology/fo/>\n" +
            "PREFIX db: <http://dbpedia.org/>\n" +
            "PREFIX frapo: <http://purl.org/cerif/frapo/>\n" +
            "PREFIX fr: <http://w3id.org/fr/def/core#>\n" +
            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX tsro: <http://www.semanticweb.org/sabato/ontologies/2022/5/tsro#>\n" +
            "PREFIX ns: <http://rdfs.org/sioc/ns#>\n" +
            "PREFIX irao: <http://ontology.ethereal.cz/irao/>\n" +
            "PREFIX sioc: <http://rdfs.org/sioc/ns#>\n";

    public List<SoftwareBean> recuperaRepositoryPerLaTabellaInIndex() {
        List<SoftwareBean> list = new ArrayList<>();

        String szQuery = prefix + "SELECT DISTINCT *\n" +
                "WHERE {\n" +
                "  ?softwareUrl a irao:Software.\n" +
                "  ?softwareUrl dc:title ?softwareTitle.\n" +
                "  ?softwareUrl irao:isPublishedAt ?repositoryUrl.\n" +
                "  ?repositoryUrl dc:title ?repositoryName.\n" +
                "  OPTIONAL{\n" +
                "    ?softwareUrl sd:author ?authorUrl.\n" +
                "    ?authorUrl sioc:name ?authorName.\n" +
                "  }\n" +
                "} ORDER BY DESC (?authorUrl)";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(szEndpoint, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        int counter = 0;
        ResultSet rs = qexec.execSelect();

        while (rs.hasNext()) {
            SoftwareBean softwareBean = new SoftwareBean();

            QuerySolution qs = rs.next();

            softwareBean.setSoftwareTitle(qs.getLiteral("softwareTitle"));
            softwareBean.setSoftwareUrl(qs.getResource("softwareUrl"));
            softwareBean.setRepositoryName(qs.getLiteral("repositoryName"));
            softwareBean.setRepositoryUrl(qs.getResource("repositoryUrl"));
            softwareBean.setAuthorName(qs.getLiteral("authorName"));
            softwareBean.setAuthorUrl(qs.getResource("authorUrl"));

            counter++;
            LOGGER.info("Result " + counter + ": " + softwareBean);

            list.add(softwareBean);
        }
        qexec.close();
        return list;
    }

    public int recuperaRepositoryMiPiace(Resource repositoryUrl) {
        int miPiace = 0;

        String szQuery = prefix + "SELECT (count(distinct ?authorUrl) as ?miPiace)\n" +
                "WHERE {\n" +
                "    ?authorUrl tsro:likesRepository <" + repositoryUrl.getURI() + ">.\n" +
                "}\n" +
                "GROUP BY ?repositoryUrl";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(szEndpoint, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        int counter = 0;
        ResultSet rs = qexec.execSelect();

        while (rs.hasNext()) {
            QuerySolution qs = rs.next();

            miPiace = qs.getLiteral("miPiace").getInt();
            counter++;

            LOGGER.info("Result " + counter + ": " + miPiace);
        }
        qexec.close();
        return miPiace;
    }

    public int recuperaRepositoryMainBranchCommit(Resource repositoryUrl) {
        int numeroDiCommit = 0;

        String szQuery = prefix + "SELECT (count(?commitUrl) as ?numeroDiCommit)\n" +
                "WHERE {\n" +
                "  <" + repositoryUrl.getURI() + "> tsro:hasBranch ?branchUrl.\n" +
                "  ?branchUrl tsro:isMainBranch true.\n" +
                "  ?branchUrl tsro:hasCommit ?commitUrl.\n" +
                "}";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(szEndpoint, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        int counter = 0;
        ResultSet rs = qexec.execSelect();

        while (rs.hasNext()) {
            QuerySolution qs = rs.next();

            numeroDiCommit = qs.getLiteral("numeroDiCommit").getInt();
            counter++;

            LOGGER.info("Result " + counter + ": " + numeroDiCommit);
        }
        qexec.close();
        return numeroDiCommit;
    }

    public List<TopicBean> recuperaSoftwareTopic(Resource softwareUrl) {
        List<TopicBean> topicList = new ArrayList<>();

        String szQuery = prefix + "SELECT ?topic ?label\n" +
                "WHERE {\n" +
                "  <" + softwareUrl.getURI() + "> sioc:topic ?topic\n" +
                "  SERVICE <https://dbpedia.org/sparql> { ?topic rdfs:label ?label. FILTER (lang(?label) = \"en\")}\n" +
                "}";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(szEndpoint, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        int counter = 0;
        ResultSet rs = qexec.execSelect();

        while (rs.hasNext()) {
            TopicBean topicBean = new TopicBean();

            QuerySolution qs = rs.next();

            topicBean.setTopicUrl(qs.getResource("topic"));
            topicBean.setTopicLabel(qs.getLiteral("label"));

            counter++;

            LOGGER.info("Result " + counter + ": " + topicBean);

            topicList.add(topicBean);
        }
        qexec.close();
        return topicList;
    }
}
