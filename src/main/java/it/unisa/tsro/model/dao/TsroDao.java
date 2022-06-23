package it.unisa.tsro.model.dao;

import it.unisa.tsro.model.bean.*;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TsroDao {
    private static final Logger LOGGER = Logger.getLogger(TsroDao.class.getName());

    private static final String QUERY_ENDPOINT =
            "http://localhost:8080/jena-fuseki-war-4.5.0/theSoftwareRepositoryOntology/query";

    private static final String UPDATE_ENDPOINT =
            "http://localhost:8080/jena-fuseki-war-4.5.0/theSoftwareRepositoryOntology/update";

    private static final String PREFIX = "PREFIX dcterms: <http://purl.org/dc/terms/>\n" +
            "PREFIX sd: <https://w3id.org/okn/o/sd#>\n" +
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

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

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

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

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

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

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

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

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

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

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

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

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

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

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

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

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

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

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

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        boolean result = qexec.execAsk();

        LOGGER.info("Result : " + result);

        qexec.close();

        return result;
    }

    public Model costruisciFileInUltimoCommit(String softwareRepositoryUrl) {
        String szQuery = PREFIX + "CONSTRUCT {?fileUrl dc:title ?fileName.}\n" +
                "WHERE {\n" +
                "  ?fileUrl dc:title ?fileName.\n" +
                "  ?fileUrl a dbo:File.\n" +
                "  ?fileUrl tsro:hasBeenModifiedIn ?commitUrl.\n" +
                "  ?commitUrl tsro:isCommitOf ?branchUrl.\n" +
                "  ?branchUrl tsro:isBranchOf <" + softwareRepositoryUrl + ">.\n" +
                "  ?branchUrl tsro:isMainBranch true.\n" +
                "}";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        Model result = qexec.execConstruct();

        LOGGER.info("Result : " + result.toString());

        qexec.close();

        return result;
    }

    public BranchBean recuperaBranch(String branchUrlString) {
        BranchBean branchBean = new BranchBean();
        branchBean.setCommitBeanList(new ArrayList<>());

        String szQuery = PREFIX + "SELECT ?softwareRepositoryUrl ?softwareRepositoryTitle ?branchTitle ?isMainBranch ?commitUrl ?commitNumber ?commitDate\n" +
                "WHERE {\n" +
                "  <" + branchUrlString + "> tsro:isBranchOf ?softwareRepositoryUrl.\n" +
                "  ?softwareRepositoryUrl dc:title ?softwareRepositoryTitle.\n" +
                "  <" + branchUrlString + "> dc:title ?branchTitle.\n" +
                "  <" + branchUrlString + "> tsro:isMainBranch ?isMainBranch.\n" +
                "  OPTIONAL {\n" +
                "    <" + branchUrlString + "> tsro:hasCommit ?commitUrl.\n" +
                "    ?commitUrl tsro:commitNumber ?commitNumber.\n" +
                "    ?commitUrl dcterms:created ?commitDate.\n" +
                "  }\n" +
                "}\n" +
                "ORDER BY DESC(?commitNumber)";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        int counter = 0;
        ResultSet rs = qexec.execSelect();

        branchBean.setBranchUrlString(branchUrlString);
        while (rs.hasNext()) {
            QuerySolution qs = rs.next();

            branchBean.setBranchTitle(qs.getLiteral("branchTitle"));
            branchBean.setIsMainBranch(qs.getLiteral("isMainBranch"));

            SoftwareRepositoryBean softwareRepositoryBean = new SoftwareRepositoryBean();
            softwareRepositoryBean.setSoftwareRepositoryUrl(qs.getResource("softwareRepositoryUrl"));
            softwareRepositoryBean.setSoftwareRepositoryTitle(qs.getLiteral("softwareRepositoryTitle"));
            branchBean.setSoftwareRepositoryBean(softwareRepositoryBean);

            CommitBean commitBean = new CommitBean();
            commitBean.setCommitUrl(qs.getResource("commitUrl"));
            commitBean.setCommitNumber(qs.getLiteral("commitNumber"));
            commitBean.setCommitDate(qs.getLiteral("commitDate"));

            if (!branchBean.getCommitBeanList().contains(commitBean)) {
                branchBean.getCommitBeanList().add(commitBean);
            }

            counter++;

            LOGGER.info("Result " + counter + ": " + branchBean);
        }
        qexec.close();
        return branchBean;
    }

    public CommitBean recuperaCommit(String commitUrlString) {
        CommitBean commitBean = new CommitBean();
        commitBean.setFileBeanList(new ArrayList<>());

        String szQuery = PREFIX + "SELECT ?commitNumber ?commitCreationDate ?userAccountUrl ?userAccountName ?branchUrl ?branchTitle ?fileUrl ?fileTitle ?fileExtension ?fileProgrammingLanguage\n" +
                "WHERE {\n" +
                "  <" + commitUrlString + "> tsro:commitNumber ?commitNumber.\n" +
                "  <" + commitUrlString + "> dcterms:created ?commitCreationDate.\n" +
                "  <" + commitUrlString + "> ns:has_modifier ?userAccountUrl.\n" +
                "  ?userAccountUrl sioc:name ?userAccountName.\n" +
                "  <" + commitUrlString + "> tsro:isCommitOf ?branchUrl.\n" +
                "  ?branchUrl dc:title ?branchTitle.\n" +
                "  <" + commitUrlString + "> tsro:modifiedFile ?fileUrl.\n" +
                "  ?fileUrl dc:title ?fileTitle.\n" +
                "  ?fileUrl dbo:fileExtension ?fileExtension.\n" +
                "  OPTIONAL{?fileUrl sd:programmingLanguage ?fileProgrammingLanguage.}\n" +
                "}";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_ENDPOINT, query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        int counter = 0;
        ResultSet rs = qexec.execSelect();

        commitBean.setCommitUrlString(commitUrlString);
        while (rs.hasNext()) {
            QuerySolution qs = rs.next();

            commitBean.setCommitNumber(qs.getLiteral("commitNumber"));
            commitBean.setCommitDate(qs.getLiteral("commitCreationDate"));

            UserAccountBean userAccountBean = new UserAccountBean();
            userAccountBean.setUserAccountUrl(qs.getResource("userAccountUrl"));
            userAccountBean.setUserAccountName(qs.getLiteral("userAccountName"));
            commitBean.setCommitter(userAccountBean);

            BranchBean branchBean = new BranchBean();
            branchBean.setBranchUrl(qs.getResource("branchUrl"));
            branchBean.setBranchTitle(qs.getLiteral("branchTitle"));
            commitBean.setBranchBean(branchBean);

            FileBean fileBean = new FileBean();
            fileBean.setFileUrl(qs.getResource("fileUrl"));
            fileBean.setFileTitle(qs.getLiteral("fileTitle"));
            fileBean.setExtension(qs.getLiteral("fileExtension"));
            fileBean.setProgrammingLanguage(qs.getLiteral("fileProgrammingLanguage"));

            if (!commitBean.getFileBeanList().contains(fileBean)) {
                commitBean.getFileBeanList().add(fileBean);
            }

            counter++;

            LOGGER.info("Result " + counter + ": " + commitBean);
        }
        qexec.close();
        return commitBean;
    }

    public Model describeTopic(String topicUrlString) {
        String szQuery = PREFIX + "DESCRIBE <" + topicUrlString + ">\n" +
                "WHERE { <" + topicUrlString + "> dbo:wikiPageWikiLink ?o\n" +
                "FILTER ( LANG ( ?o) = 'en' ) }";

        LOGGER.info(szQuery);

        Query query = QueryFactory.create(szQuery);

        QueryExecution qexec = QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql", query);

        ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

        Model result = qexec.execConstruct();

        LOGGER.info("Result : " + result.toString());

        qexec.close();

        return result;
    }

    public void insertTopic(String softwareUrl, String topicUrlString) {
        String szQuery = PREFIX + "INSERT DATA\n" +
                "  { \n" +
                "  <" + softwareUrl + "> sioc:topic <" + topicUrlString + ">.  \n" +
                "  }";

        UpdateRequest update = UpdateFactory.create(szQuery);
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, UPDATE_ENDPOINT);
        processor.execute();
    }
}
