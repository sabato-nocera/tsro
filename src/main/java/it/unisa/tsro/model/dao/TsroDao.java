package it.unisa.tsro.model.dao;

import it.unisa.tsro.model.bean.*;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TsroDao {
    private static final Logger LOGGER = Logger.getLogger(TsroDao.class.getName());

    private static final String ENDPOINT =
            "http://localhost:8080/jena-fuseki-war-4.5.0/theSoftwareRepositoryOntology/query";

    private static final String PREFIX = "PREFIX sd: <https://w3id.org/okn/o/sd#>\n" +
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

    public List<SoftwareBean> recuperaRepositoryPerLaTabellaInIndex(String softwareTitle, String authorName) {
        List<SoftwareBean> list = new ArrayList<>();

        String szQuery = PREFIX + "SELECT DISTINCT *\n" +
                "WHERE {\n" +
                "  ?softwareUrl a irao:Software.\n" +
                "  ?softwareUrl dc:title ?softwareTitle.\n" +
                "  ?softwareUrl irao:isPublishedAt ?repositoryUrl.\n" +
                "  ?repositoryUrl dc:title ?repositoryName.\n" +
                "  OPTIONAL{\n" +
                "    ?softwareUrl sd:author ?authorUrl.\n" +
                "    ?authorUrl sioc:name ?authorName.\n" +
                "  }\n";

        if (softwareTitle != null && !softwareTitle.equals("")) {
            szQuery += "FILTER regex(?softwareTitle, \".*" + softwareTitle + ".*\", \"i\")\n";
        }

        if (authorName != null && !authorName.equals("")) {
            szQuery += "FILTER regex(STR(?authorName), \".*" + authorName + ".*\", \"i\")\n";
        }

        szQuery += "} ORDER BY DESC (?authorUrl)";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(ENDPOINT, query);

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

        String szQuery = PREFIX + "SELECT (count(distinct ?authorUrl) as ?miPiace)\n" +
                "WHERE {\n" +
                "    ?authorUrl tsro:likesRepository <" + repositoryUrl.getURI() + ">.\n" +
                "}\n" +
                "GROUP BY ?repositoryUrl\n";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(ENDPOINT, query);

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

        String szQuery = PREFIX + "SELECT (count(?commitUrl) as ?numeroDiCommit)\n" +
                "WHERE {\n" +
                "  <" + repositoryUrl.getURI() + "> tsro:hasBranch ?branchUrl.\n" +
                "  ?branchUrl tsro:isMainBranch true.\n" +
                "  ?branchUrl tsro:hasCommit ?commitUrl.\n" +
                "}";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(ENDPOINT, query);

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

    public List<TopicBean> recuperaSoftwareTopic(Resource softwareUrl, String topicLabel) {
        List<TopicBean> topicList = new ArrayList<>();

        String szQuery = PREFIX + "SELECT ?topic ?label\n" +
                "WHERE {\n" +
                "  <" + softwareUrl.getURI() + "> sioc:topic ?topic\n" +
                "  SERVICE <https://dbpedia.org/sparql> { ?topic rdfs:label ?label. FILTER (lang(?label) = \"en\")}\n";

        if (topicLabel != null && !topicLabel.equals("")) {
            szQuery += "FILTER regex(STR(?label), \".*" + topicLabel + ".*\", \"i\")\n";
        }

        szQuery += "}";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(ENDPOINT, query);

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

    public AgentBean recuperaAgent(String authorUrl) {
        AgentBean agentBean = new AgentBean();
        agentBean.setSoftwareBeanList(new ArrayList<>());
        agentBean.setUserAccountBeanList(new ArrayList<>());

        String szQuery = PREFIX + "SELECT ?authorName ?softwareUrl ?softwareTitle ?userAccountUrl ?userAccountName\n" +
                "WHERE {\n" +
                "  ?softwareUrl sd:author <" + authorUrl + ">.\n" +
                "  ?softwareUrl dc:title ?softwareTitle.\n" +
                "  <" + authorUrl + "> sioc:name ?authorName.\n" +
                "  ?userAccountUrl ns:account_of <" + authorUrl + ">.\n" +
                "  ?userAccountUrl sioc:name ?userAccountName.\n" +
                "}";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(ENDPOINT, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        int counter = 0;
        ResultSet rs = qexec.execSelect();

        agentBean.setAuthorUrl(authorUrl);
        while (rs.hasNext()) {
            QuerySolution qs = rs.next();

            agentBean.setAuthorName(qs.getLiteral("authorName"));

            SoftwareBean softwareBean = new SoftwareBean();
            softwareBean.setSoftwareTitle(qs.getLiteral("softwareTitle"));
            softwareBean.setSoftwareUrl(qs.getResource("softwareUrl"));

            UserAccountBean userAccountBean = new UserAccountBean();
            userAccountBean.setUserAccountUrl(qs.getResource("userAccountUrl"));
            userAccountBean.setUserAccountName(qs.getLiteral("userAccountName"));

            if (!agentBean.getSoftwareBeanList().contains(softwareBean)) {
                agentBean.getSoftwareBeanList().add(softwareBean);
            }

            if (!agentBean.getUserAccountBeanList().contains(userAccountBean)) {
                agentBean.getUserAccountBeanList().add(userAccountBean);
            }

            counter++;

            LOGGER.info("Result " + counter + ": " + agentBean);
        }
        qexec.close();
        return agentBean;
    }

    public String recuperaAgentInfosFromTheCloud(String authorUrl) {
        String infosFromTheCloud = "";

        String szQuery = PREFIX + "SELECT ?fromTheCloud\n" +
                "WHERE {\n" +
                "SERVICE <https://dbpedia.org/sparql> { \n" +
                "    <" + authorUrl + "> ?p ?fromTheCloud.\n" +
                "    FILTER (lang(?fromTheCloud) = 'en')\n" +
                "  }\n" +
                "}\n" +
                "ORDER BY DESC(strlen(str(?fromTheCloud)))\n" +
                "LIMIT 1";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(ENDPOINT, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        int counter = 0;
        ResultSet rs = qexec.execSelect();

        while (rs.hasNext()) {
            QuerySolution qs = rs.next();

            infosFromTheCloud = qs.get("fromTheCloud").toString();

            counter++;

            LOGGER.info("Result " + counter + ": " + infosFromTheCloud);
        }
        qexec.close();
        return infosFromTheCloud;
    }

    public UserAccountBean recuperaUserAccount(String userAccountUrl) {
        UserAccountBean userAccountBean = new UserAccountBean();
        userAccountBean.setAgentBeanList(new ArrayList<>());
        userAccountBean.setSoftwareCreatedList(new ArrayList<>());
        userAccountBean.setSoftwareLikedList(new ArrayList<>());
        userAccountBean.setUserAccountFollowedList(new ArrayList<>());
        userAccountBean.setUserAccountFollowingList(new ArrayList<>());

        String szQuery = PREFIX + "SELECT DISTINCT *\n" +
                "WHERE {\n" +
                "    <" + userAccountUrl + "> sioc:name ?userAccountName.\n" +
                "    OPTIONAL {\n" +
                "      <" + userAccountUrl + "> ns:account_of ?authorUrl.\n" +
                "      ?authorUrl sioc:name ?authorName.\n" +
                "    }\n" +
                "    OPTIONAL {\n" +
                "      <" + userAccountUrl + "> ns:creator_of ?softwareRepositoryCreatedUrl.\n" +
                "      ?softwareRepositoryCreatedUrl dc:title ?softwareRepositoryCreatedTitle.\n" +
                "    }\n" +
                "    OPTIONAL {\n" +
                "      <" + userAccountUrl + "> tsro:likesRepository ?softwareRepositoryLikedUrl.\n" +
                "      ?softwareRepositoryLikedUrl dc:title ?softwareRepositoryLikedName.\n" +
                "    }\n" +
                "    OPTIONAL {\n" +
                "      <" + userAccountUrl + "> ns:follows ?userAccountFollowedUrl.\n" +
                "      ?userAccountFollowedUrl sioc:name ?userAccountFollowedName.\n" +
                "    }\n" +
                "    OPTIONAL {\n" +
                "      ?userAccountFollowingUrl ns:follows <" + userAccountUrl + ">.\n" +
                "      ?userAccountFollowingUrl sioc:name ?userAccountFollowingName.\n" +
                "    }\n" +
                "}";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(ENDPOINT, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        int counter = 0;
        ResultSet rs = qexec.execSelect();

        userAccountBean.setUserAccountUrlString(userAccountUrl);
        while (rs.hasNext()) {
            QuerySolution qs = rs.next();

            userAccountBean.setUserAccountName(qs.getLiteral("authorName"));

            AgentBean agentBean = new AgentBean();
            agentBean.setAuthorUrlResource(qs.getResource("authorUrl"));
            agentBean.setAuthorName(qs.getLiteral("authorName"));

            SoftwareRepositoryBean softwareRepositoryCreated = new SoftwareRepositoryBean();
            softwareRepositoryCreated.setSoftwareRepositoryTitle(qs.getLiteral("softwareRepositoryCreatedTitle"));
            softwareRepositoryCreated.setSoftwareRepositoryUrl(qs.getResource("softwareRepositoryCreatedUrl"));

            SoftwareRepositoryBean softwareRepositoryLiked = new SoftwareRepositoryBean();
            softwareRepositoryLiked.setSoftwareRepositoryTitle(qs.getLiteral("softwareRepositoryLikedName"));
            softwareRepositoryLiked.setSoftwareRepositoryUrl(qs.getResource("softwareRepositoryLikedUrl"));

            UserAccountBean accountFollowed = new UserAccountBean();
            accountFollowed.setUserAccountUrl(qs.getResource("userAccountFollowedUrl"));
            accountFollowed.setUserAccountName(qs.getLiteral("userAccountFollowedName"));

            UserAccountBean accountFollowing = new UserAccountBean();
            accountFollowing.setUserAccountUrl(qs.getResource("userAccountFollowingUrl"));
            accountFollowing.setUserAccountName(qs.getLiteral("userAccountFollowingName"));

            if (!userAccountBean.getAgentBeanList().contains(agentBean)) {
                userAccountBean.getAgentBeanList().add(agentBean);
            }

            if (!userAccountBean.getSoftwareCreatedList().contains(softwareRepositoryCreated)) {
                userAccountBean.getSoftwareCreatedList().add(softwareRepositoryCreated);
            }

            if (!userAccountBean.getSoftwareLikedList().contains(softwareRepositoryLiked)) {
                userAccountBean.getSoftwareLikedList().add(softwareRepositoryLiked);
            }

            if (!userAccountBean.getUserAccountFollowedList().contains(accountFollowed)) {
                userAccountBean.getUserAccountFollowedList().add(accountFollowed);
            }

            if (!userAccountBean.getUserAccountFollowingList().contains(accountFollowing)) {
                userAccountBean.getUserAccountFollowingList().add(accountFollowing);
            }

            counter++;

            LOGGER.info("Result " + counter + ": " + userAccountBean);
        }
        qexec.close();
        return userAccountBean;
    }

    public Resource recuperaLicensa(String softwareUrl) {
        Resource licensa = null;

        String szQuery = PREFIX + "SELECT DISTINCT ?licenseUrl\n" +
                "WHERE {\n" +
                "  <" + softwareUrl + "> irao:hasLicense ?licenseUrl\n" +
                "}";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(ENDPOINT, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        int counter = 0;
        ResultSet rs = qexec.execSelect();

        while (rs.hasNext()) {
            QuerySolution qs = rs.next();

            licensa = qs.getResource("licenseUrl");

            counter++;

            LOGGER.info("Result " + counter + ": " + licensa);
        }
        qexec.close();
        return licensa;
    }

    public SoftwareRepositoryBean recuperaSoftwareRepository(String softwareRepositoryUrl) {
        SoftwareRepositoryBean softwareRepositoryBean = new SoftwareRepositoryBean();
        softwareRepositoryBean.setBranchBeanList(new ArrayList<>());
        softwareRepositoryBean.setRepositoriesForkList(new ArrayList<>());

        String szQuery = PREFIX + "SELECT ?softwareRepositoryTitle ?softwareUrl ?softwareTitle ?repositoryHtmlUrl ?repositoryCloneUrl ?repositoryForkUrl ?repositoryForkTitle ?repositoryCreatorUrl ?repositoryCreatorName ?branchUrl ?branchTitle ?isMainBranch\n" +
                "WHERE {\n" +
                "  <" + softwareRepositoryUrl + "> dc:title ?softwareRepositoryTitle.\n" +
                "  OPTIONAL {\n" +
                "    ?softwareUrl irao:isPublishedAt <" + softwareRepositoryUrl + ">.\n" +
                "    ?softwareUrl dc:title ?softwareTitle.\n" +
                "  }\n" +
                "  OPTIONAL {\n" +
                "    <" + softwareRepositoryUrl + "> tsro:hasHtmlUrl ?repositoryHtmlUrl.\n" +
                "  }\n" +
                "  OPTIONAL {\n" +
                "    <" + softwareRepositoryUrl + "> tsro:hasCloneUrl ?repositoryCloneUrl.\n" +
                "  }\n" +
                "  OPTIONAL {\n" +
                "    <" + softwareRepositoryUrl + "> tsro:hasFork ?repositoryForkUrl.\n" +
                "    ?repositoryForkUrl dc:title ?repositoryForkTitle.\n" +
                "  }\n" +
                "  OPTIONAL {\n" +
                "    ?repositoryCreatorUrl ns:creator_of <" + softwareRepositoryUrl + ">.\n" +
                "    ?repositoryCreatorUrl sioc:name ?repositoryCreatorName.\n" +
                "  }\n" +
                "  OPTIONAL {\n" +
                "    <" + softwareRepositoryUrl + "> tsro:hasBranch ?branchUrl.\n" +
                "    ?branchUrl dc:title ?branchTitle.\n" +
                "    ?branchUrl tsro:isMainBranch ?isMainBranch.\n" +
                "  }\n" +
                "}";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(ENDPOINT, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        int counter = 0;
        ResultSet rs = qexec.execSelect();

        softwareRepositoryBean.setSoftwareRepositoryUrlString(softwareRepositoryUrl);
        while (rs.hasNext()) {
            QuerySolution qs = rs.next();

            softwareRepositoryBean.setSoftwareRepositoryTitle(qs.getLiteral("softwareRepositoryTitle"));
            softwareRepositoryBean.setRepositoryCloneUrl(qs.getLiteral("repositoryHtmlUrl"));
            softwareRepositoryBean.setRepositoryHtmlUrl(qs.getLiteral("repositoryCloneUrl"));

            SoftwareBean softwareBean = new SoftwareBean();
            softwareBean.setSoftwareTitle(qs.getLiteral("softwareTitle"));
            softwareBean.setSoftwareUrl(qs.getResource("softwareUrl"));
            softwareRepositoryBean.setSoftwareBean(softwareBean);

            UserAccountBean creator = new UserAccountBean();
            creator.setUserAccountUrl(qs.getResource("repositoryCreatorUrl"));
            creator.setUserAccountName(qs.getLiteral("repositoryCreatorName"));
            softwareRepositoryBean.setUserAccountBean(creator);

            SoftwareRepositoryBean fork = new SoftwareRepositoryBean();
            fork.setSoftwareRepositoryTitle(qs.getLiteral("repositoryForkTitle"));
            fork.setSoftwareRepositoryUrl(qs.getResource("repositoryForkUrl"));

            BranchBean branch = new BranchBean();
            branch.setBranchUrl(qs.getResource("branchUrl"));
            branch.setBranchTitle(qs.getLiteral("branchTitle"));
            branch.setIsMainBranch(qs.getLiteral("isMainBranch"));

            if (!softwareRepositoryBean.getRepositoriesForkList().contains(fork)) {
                softwareRepositoryBean.getRepositoriesForkList().add(fork);
            }

            if (!softwareRepositoryBean.getBranchBeanList().contains(branch)) {
                softwareRepositoryBean.getBranchBeanList().add(branch);
            }

            counter++;

            LOGGER.info("Result " + counter + ": " + softwareRepositoryBean);
        }
        qexec.close();
        return softwareRepositoryBean;
    }

    public boolean askSeSiaFork(String softwareRepositoryUrl) {
        String szQuery = PREFIX + "ASK { \n" +
                "  <" + softwareRepositoryUrl + "> tsro:isForkOf ?s\n" +
                "}";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(ENDPOINT, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        boolean result = qexec.execAsk();

        LOGGER.info("Result : " + result);

        qexec.close();

        return result;
    }
}
