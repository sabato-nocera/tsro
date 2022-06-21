<%@ page import="it.unisa.tsro.model.bean.UserAccountBean" %>
<%@ page import="it.unisa.tsro.model.bean.AgentBean" %>
<%@ page import="it.unisa.tsro.model.bean.SoftwareRepositoryBean" %>

<%
    UserAccountBean accountBean = (UserAccountBean) request.getAttribute("accountBean");
    if (accountBean == null) {
        response.sendRedirect("./index.jsp");
        return;
    }
%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle"
               value='<%= accountBean.getUserAccountName() != null ? accountBean.getUserAccountName().getString() : "" %>'/>
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
                        <%= accountBean.getUserAccountName() != null ? accountBean.getUserAccountName().getString() : "" %>
                </div>

                <%
                    for (AgentBean agentBean : accountBean.getAgentBeanList()) {
                %>
                <!-- Proprietario dell'account -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            Proprietario dell'account:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="agent-servlet?agentUrl=<%=agentBean.getAuthorUrlResource() != null ? agentBean.getAuthorUrlResource().getURI() : agentBean.getAuthorUrl()%>">
                                                <%=agentBean.getAuthorName() != null ? agentBean.getAuthorName().getString() : ""%>
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
                    for (SoftwareRepositoryBean softwareRepositoryBean : accountBean.getSoftwareCreatedList()) {
                %>
                <!-- Repository create -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                            Creatore della repository:
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
                    for (SoftwareRepositoryBean softwareRepositoryBean : accountBean.getSoftwareLikedList()) {
                %>
                <!-- Repository create -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                            Piace la repository:
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
                    for (UserAccountBean userAccountBean : accountBean.getUserAccountFollowedList()) {
                %>
                <!-- Follower -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                            Segue account:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="account-servlet?accountUrl=<%=userAccountBean.getUserAccountUrl() != null ? userAccountBean.getUserAccountUrl().getURI() : ""%>">
                                                <%=userAccountBean.getUserAccountName() != null ? userAccountBean.getUserAccountName().getString() : ""%>
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
                    for (UserAccountBean userAccountBean : accountBean.getUserAccountFollowingList()) {
                %>
                <!-- Following -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                            Seguito dall'account:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="account-servlet?accountUrl=<%=userAccountBean.getUserAccountUrl() != null ? userAccountBean.getUserAccountUrl().getURI() : ""%>">
                                                <%=userAccountBean.getUserAccountName() != null ? userAccountBean.getUserAccountName().getString() : ""%>
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
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

<jsp:include page="footer.jsp"/>