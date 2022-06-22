<%@ page import="it.unisa.tsro.model.bean.SoftwareRepositoryBean" %>
<%@ page import="it.unisa.tsro.model.bean.BranchBean" %>

<%
    SoftwareRepositoryBean softwareRepository = (SoftwareRepositoryBean) request.getAttribute("softwareRepository");
    String newRdfModel = (String) request.getAttribute("newRdfModel");
    if (softwareRepository == null) {
        response.sendRedirect("./index.jsp");
        return;
    }
%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle"
               value='<%= softwareRepository.getSoftwareRepositoryTitle() != null ? softwareRepository.getSoftwareRepositoryTitle().getString() : "" %>'/>
</jsp:include>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <jsp:include page="sidebar.jsp"/>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column p-4">

        <!-- Main Content -->
        <div id="content">
            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">
                        <a href="repository-servlet?repositoryUrl=<%=softwareRepository.getSoftwareRepositoryUrl() != null ? softwareRepository.getSoftwareRepositoryUrl().getURI() : ""%>">
                            <%=softwareRepository.getSoftwareRepositoryTitle() != null ? softwareRepository.getSoftwareRepositoryTitle().getString() : ""%>
                        </a> risulta essere repository software di
                        <a href="software-servlet?softwareUrl=<%=softwareRepository.getSoftwareBean()!=null && softwareRepository.getSoftwareBean().getSoftwareUrl() != null ? softwareRepository.getSoftwareBean().getSoftwareUrl().getURI() : ""%>">
                            <%=softwareRepository.getSoftwareBean() != null && softwareRepository.getSoftwareBean().getSoftwareTitle() != null ? softwareRepository.getSoftwareBean().getSoftwareTitle().getString() : "nulla..."%>
                        </a>
                    </h1>
                </div>

                <!-- URL -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            Lista degli URL:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            HTML
                                            URL: <%=softwareRepository.getRepositoryHtmlUrl() != null ? softwareRepository.getRepositoryHtmlUrl().getString() : ""%>
                                            <br/>
                                            CLONE
                                            URL: <%=softwareRepository.getRepositoryCloneUrl() != null ? softwareRepository.getRepositoryCloneUrl().getString() : ""%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- È fork di un'altra repository? -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            &Egrave; fork di un'altra repository?
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <%=softwareRepository.isHasForks()%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Creatore -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                            Creatore della repository:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="account-servlet?accountUrl=<%=softwareRepository.getUserAccountBean() != null && softwareRepository.getUserAccountBean().getUserAccountUrl() != null ? softwareRepository.getUserAccountBean().getUserAccountUrl().getURI() : ""%>">
                                                <%=softwareRepository.getUserAccountBean() != null && softwareRepository.getUserAccountBean().getUserAccountName() != null ? softwareRepository.getUserAccountBean().getUserAccountName().getString() : ""%>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%
                    for (SoftwareRepositoryBean softwareRepositoryBean : softwareRepository.getRepositoriesForkList()) {
                %>
                <!-- Repository create -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                            Ha fork:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="repository-servlet?repositoryUrl=<%=softwareRepositoryBean.getSoftwareRepositoryUrl() != null ? softwareRepositoryBean.getSoftwareRepositoryUrl().getURI() : ""%>">
                                                <%=softwareRepositoryBean.getSoftwareRepositoryTitle() != null ? softwareRepositoryBean.getSoftwareRepositoryTitle().getString() : ""%>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>

                <%
                    for (BranchBean branchBean : softwareRepository.getBranchBeanList()) {
                %>
                <!-- Repository create -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                            Ha branch:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="branch-servlet?branchUrl=<%=branchBean.getBranchUrl() != null ? branchBean.getBranchUrl().getURI() : ""%>">
                                                <%=branchBean.getBranchTitle() != null ? branchBean.getBranchTitle().getString() : ""%>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
                <div class="col m-1 p-1">
                    <a href="file-servlet?repositoryUrl=<%=softwareRepository.getSoftwareRepositoryUrlString()%>"
                       class="btn btn-info btn-icon-split btn-lg">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-file"></i>
                                        </span>
                        <span class="text">Genera RDF con l'ultima versione dei file contenuti nella repository software</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

<jsp:include page="footer.jsp"/>