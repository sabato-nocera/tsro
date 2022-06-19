<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Commit - "/>
</jsp:include>
<%//TODO: Inserire il nome preso dalla request%>

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
                    <h1 class="h3 mb-0 text-gray-800"><%//TODO: Inserire il nome del commit%>
                        Commit del <a href="branch.jsp">Branch</a> del <a href="repository.jsp"> repository;
                            informazioni sulla data e sul numero di commit</a>
                    </h1>
                </div>

                <!-- File modificati -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            File modificato
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            Nome file modificato, estensione e linguaggio di
                                            programmazione <%//TODO: inserire commit%>
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