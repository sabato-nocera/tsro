<%@ page import="it.unisa.tsro.model.bean.CommitBean" %>
<%@ page import="it.unisa.tsro.model.bean.FileBean" %>
<%
    CommitBean commit = (CommitBean) request.getAttribute("commit");
    if (commit == null) {
        response.sendRedirect("index-servlet");
        return;
    }
%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle"
               value='<%= commit.getCommitNumber() != null ? commit.getCommitNumber().getString() : "" %>'/>
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
                        Commit <%= commit.getCommitNumber() != null ? commit.getCommitNumber().getString() : "" %>
                        del <%= commit.getCommitDate() != null ? commit.getCommitDate().getString() : "" %> appartenente
                        al
                        branch <a
                            href="branch-servlet?branchUrl=<%=commit.getBranchBean().getBranchUrl() != null ? commit.getBranchBean().getBranchUrl().getURI() : ""%>">
                        <%=commit.getBranchBean().getBranchTitle() != null ? commit.getBranchBean().getBranchTitle().getString() : ""%>
                    </a>
                    </h1>
                </div>

                <!-- Committer -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            Account che ha effettuato il commit:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="account-servlet?accountUrl=<%=commit.getCommitter() != null && commit.getCommitter().getUserAccountUrl() != null ? commit.getCommitter().getUserAccountUrl().getURI() : ""%>">
                                                <%=commit.getCommitter() != null && commit.getCommitter().getUserAccountName() != null ? commit.getCommitter().getUserAccountName().getString() : ""%>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%
                    for (FileBean fileBean : commit.getFileBeanList()) {
                %>
                <!-- File -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            File:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            Nome: <%=fileBean.getFileTitle()%> <br/>
                                            Estensione: <%=fileBean.getExtension()%> <br/>
                                            Linguaggio di programmazione: <%=fileBean.getProgrammingLanguage()%>
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