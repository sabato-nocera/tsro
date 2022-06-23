<%@ page import="org.apache.jena.rdf.model.Statement" %>
<%@ page import="java.util.List" %>
<%
    List<Statement> statementList = (List<Statement>) request.getAttribute("statementList");
    if (statementList == null) {
        response.sendRedirect("./index.jsp");
        return;
    }
%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle"
               value='Topic'/>
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


                <!-- Statement -->
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Soggetto</th>
                        <th>Predicato</th>
                        <th>Oggetto</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Soggetto</th>
                        <th>Predicato</th>
                        <th>Oggetto</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <%
                        for (Statement statement : statementList) {
                    %>
                    <tr>
                        <td>
                            <a href="<%=statement.getSubject() != null ? statement.getSubject().getURI() : ""%>">
                                <%=statement.getSubject() != null ?  statement.getSubject().getURI() : ""%>
                            </a>
                        </td>
                        <td>
                            <a href="<%=statement.getPredicate() != null ? statement.getPredicate().getURI() : ""%>">
                                <%=statement.getPredicate() != null ? statement.getPredicate().getURI() : ""%>
                            </a>
                        </td>
                        <td>
                            <a href="<%=statement.getObject() != null && statement.getObject().isResource() ? statement.getObject().toString() : ""%>">
                                <%=statement.getObject() != null ? statement.getObject().toString() : ""%>
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

<jsp:include page="footer.jsp"/>