<%@ page import="it.unisa.tsro.model.bean.BranchBean" %>
<%@ page import="it.unisa.tsro.model.bean.CommitBean" %>
<%
    BranchBean branchBean = (BranchBean) request.getAttribute("branchBean");
    if (branchBean == null) {
        response.sendRedirect("./index.jsp");
        return;
    }
%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle"
               value='<%= branchBean.getBranchTitle() != null ? branchBean.getBranchTitle().getString() : "" %>'/>
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
                        <%= branchBean.getBranchTitle() != null ? branchBean.getBranchTitle().getString() : "" %>
                        risulta essere
                        branch <%=branchBean.getIsMainBranch() != null && branchBean.getIsMainBranch().getString().equalsIgnoreCase("true") ? "principale" : ""%>
                        del repository
                        <a href="repository-servlet?repositoryUrl=<%=branchBean.getSoftwareRepositoryBean() != null && branchBean.getSoftwareRepositoryBean().getSoftwareRepositoryUrl()!=null ? branchBean.getSoftwareRepositoryBean().getSoftwareRepositoryUrl().getURI() : ""%>">
                            <%=branchBean.getSoftwareRepositoryBean() != null && branchBean.getSoftwareRepositoryBean().getSoftwareRepositoryTitle() != null ? branchBean.getSoftwareRepositoryBean().getSoftwareRepositoryTitle().getString() : ""%>
                        </a>
                    </h1>
                </div>

                <%
                    for (CommitBean commitBean : branchBean.getCommitBeanList()) {
                %>
                <!-- Commit -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                            Commit:
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="commit-servlet?commitUrl=<%=commitBean.getCommitUrl() != null ? commitBean.getCommitUrl().getURI() : ""%>">
                                                <%=commitBean.getCommitNumber() != null ? commitBean.getCommitNumber().getString() : ""%>
                                                - <%=commitBean.getCommitDate() != null ? commitBean.getCommitDate().getString() : ""%>
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