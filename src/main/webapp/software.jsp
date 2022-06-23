<%@ page import="it.unisa.tsro.model.bean.SoftwareBean" %>
<%@ page import="it.unisa.tsro.model.bean.TopicBean" %>

<%
    SoftwareBean software = (SoftwareBean) request.getAttribute("software");
    if (software == null) {
        response.sendRedirect("./index.jsp");
        return;
    }
%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle"
               value='<%= software.getSoftwareTitle() != null ? software.getSoftwareTitle().getString() : "" %>'/>
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
                        <a href="software-servlet?softwareUrl=<%=software.getSoftwareUrl() != null ? software.getSoftwareUrl().getURI() : ""%>">
                            <%=software.getSoftwareTitle() != null ? software.getSoftwareTitle().getString() : ""%>
                        </a> pubblicato su <a
                            href="repository-servlet?repositoryUrl=<%=software.getRepositoryUrl() != null ? software.getRepositoryUrl().getURI() : ""%>">
                        <%=software.getRepositoryName() != null ? software.getRepositoryName().getString() : ""%>
                    </a>
                    </h1>
                </div>

                <!-- Author -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            Autore:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="agent-servlet?agentUrl=<%=software.getAuthorUrl() != null ? software.getAuthorUrl().getURI() : ""%>">
                                                <%=software.getAuthorName() != null ? software.getAuthorName().getString() : ""%>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Numero di commit -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-secondary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-secondary text-uppercase mb-1">
                                            Numero di commit:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <%=software.getNumeroDiCommit()%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Numero di mi piace -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-secondary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-secondary text-uppercase mb-1">
                                            Numero di mi piace:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <%=software.getMiPiace()%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Licensa -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                            Licensa:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="<%=software.getLicensa() != null ? software.getLicensa().getURI() : ""%>">
                                                <%=software.getLicensa() != null ? software.getLicensa().getURI() : ""%>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%
                    if (software.getTopicBeanList() != null) {
                        for (TopicBean topic : software.getTopicBeanList()) {
                %>
                <!-- Topic -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                            Topic:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="topic-servlet?topicUrl=<%=topic.getTopicUrl() != null ? topic.getTopicUrl().getURI() : ""%>">
                                                <%=topic.getTopicLabel() != null ? topic.getTopicLabel().getString() : ""%>
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
                    }
                %>
            </div>

            <div class="container-fluid">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-left">
                                        <h1 class="h4 text-gray-900 mb-4">Inserisci un nuovo topic</h1>
                                    </div>
                                    <form class="user" action="software-servlet">
                                        <input type="hidden" name="softwareUrl"
                                        value='<%=software.getSoftwareUrl() != null ? software.getSoftwareUrl().getURI() : ""%>'/>

                                        <div class="form-group row">
                                            <div class="col-sm-12">
                                                <label for="topicUrl">URL del topic</label>
                                                <input type="text" class="form-control form-control-user"
                                                       id="topicUrl" name="topicUrl"
                                                       placeholder="URL del topic"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-6">
                                                <input type="reset" class="btn btn-info btn-user btn-block"/>
                                            </div>
                                            <div class="col-sm-6">
                                                <input type="submit" class="btn btn-primary btn-user btn-block"/>
                                            </div>
                                        </div>
                                    </form>
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