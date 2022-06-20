<%@ page import="it.unisa.tsro.model.bean.SoftwareBean" %>
<%@ page import="java.util.List" %>
<%
    List<SoftwareBean> softwareList = (List<SoftwareBean>) request.getAttribute("softwareList");
    if (softwareList == null) {
        response.sendRedirect("index-servlet");
        return;
    }
%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Index - "/>
</jsp:include>
<%//TODO: Inserire il nome preso dalla request%>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <jsp:include page="sidebar.jsp"/>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800 mt-4">Benvenuto!</h1>
                <p class="mb-4">
                    Interagisci con la tabella per consultare i dettagli delle repository softwareBean,
                    oppure effettua una ricerca specificando i criteri che desideri.
                </p>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Repository</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Software</th>
                                    <th>Repository</th>
                                    <th>Autore</th>
                                    <th>Topic</th>
                                    <th>Mi piace</th>
                                    <th>Commit</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Software</th>
                                    <th>Repository</th>
                                    <th>Autore</th>
                                    <th>Topic</th>
                                    <th>Mi piace</th>
                                    <th>Commit</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <%
                                    for (SoftwareBean softwareBean : softwareList) {
                                %>
                                <tr><%//TODO: Sostituire con i dati della base di conoscenza e gli appositi link%>
                                    <td>
                                        <a href="software-servlet?softwareUrl="
                                                <%=softwareBean.getSoftwareUrl().getURI()%>>
                                            <%=softwareBean.getSoftwareTitle().getString()%></a>
                                    </td>
                                    <td>
                                        <a href="repository-servlet?repositoryUrl="
                                                <%=softwareBean.getRepositoryUrl().getURI()%>>
                                            <%=softwareBean.getRepositoryName().getString()%></a>
                                    </td>
                                    <td>
                                        <a href="agent-servlet?agentUrl="
                                                <%=softwareBean.getAuthorUrl().getURI()%>>
                                            <%=softwareBean.getAuthorName().getString()%></a>
                                    </td>
                                    <td>
                                        <a href="agent-servlet?agentUrl="
                                                <%=softwareBean.getAuthorUrl().getURI()%>>
                                            <%=softwareBean.getAuthorName().getString()%></a>
                                    </td>






                                    <td><a href="topic.jsp">Erogazioni servizi DSA/disabili</a> ,
                                        <a href="topic.jsp">Gestionale</a>
                                    </td>
                                    <td>63</td>
                                    <td>589</td>
                                </tr>
                                <%
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->

            <div class="container-fluid">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-left">
                                        <h1 class="h4 text-gray-900 mb-4">Ricerca avanzata</h1>
                                    </div>
                                    <form class="user" action="index-servlet">
                                        <div class="form-group row">
                                            <div class="col-sm-4 mb-3 mb-sm-0">
                                                <label for="nome">Nome</label>
                                                <input type="text" class="form-control form-control-user"
                                                       id="nome"
                                                       placeholder="Nome"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="autore">Autore</label>
                                                <input type="text" class="form-control form-control-user"
                                                       id="autore"
                                                       placeholder="Autore"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="linguaggiDiProgrammazione">Linguaggi di programmazione
                                                </label>
                                                <input type="text" class="form-control form-control-user"
                                                       id="linguaggiDiProgrammazione"
                                                       placeholder="Linguaggi di programmazione"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-4 mb-3 mb-sm-0">
                                                <label for="miPiace">Numero di mi piace</label>
                                                <input type="text" class="form-control form-control-user"
                                                       id="miPiace"
                                                       placeholder="Numero di mi piace (<,>,=)"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="commit">Numero di commit</label>
                                                <input type="text" class="form-control form-control-user"
                                                       id="commit"
                                                       placeholder="Numero di commit (<,>,=)"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="topic">Topic</label>
                                                <input type="text" class="form-control form-control-user"
                                                       id="topic"
                                                       placeholder="Topic"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-6 mb-3 mb-sm-0">
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
</div>
<!-- End of Main Content -->

<jsp:include page="footer.jsp"/>