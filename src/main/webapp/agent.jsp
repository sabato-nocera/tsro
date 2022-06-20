<%@ page import="it.unisa.tsro.model.bean.AgentBean" %>
<%@ page import="it.unisa.tsro.model.bean.SoftwareBean" %>
<%@ page import="it.unisa.tsro.model.bean.UserAccountBean" %>

<%
    AgentBean agentBean = (AgentBean) request.getAttribute("agentBean");
    if (agentBean == null) {
        response.sendRedirect("./index.jsp");
        return;
    }
%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle"
               value='<%= agentBean.getAuthorName() != null ? agentBean.getAuthorName().getString() : "" %>'/>
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
                        <%= agentBean.getAuthorName() != null ? agentBean.getAuthorName().getString() : "" %>
                    </h1>
                </div>

                <%
                    for (SoftwareBean softwareBean : agentBean.getSoftwareBeanList()) {
                %>
                <!-- Proprietario dei software -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            Proprietario del software:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="software-servlet?softwareUrl=<%=softwareBean.getSoftwareUrl() != null ? softwareBean.getSoftwareUrl().getURI() : ""%>">
                                                <%=softwareBean.getSoftwareTitle() != null ? softwareBean.getSoftwareTitle().getString() : ""%>
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
                    for (UserAccountBean userAccountBean : agentBean.getUserAccountBeanList()) {
                %>
                <!-- Proprietario degli account -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                            Proprietario dell'account:
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

                <!-- Ulteriori informazioni da dbpedia -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-warning shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                            Ulteriori informazioni dal web:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <%=agentBean.getFromTheCloud()%>>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

<jsp:include page="footer.jsp"/>