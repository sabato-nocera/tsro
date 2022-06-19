<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Topic - "/>
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
                    <h1 class="h3 mb-0 text-gray-800">
                        Topic <%//TODO: Inserire il nome del topic preso dalla request%>
                    </h1>
                </div>

                <!-- Software con questo topic -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            Software
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a href="softwareBean.jsp">Software</a> <%//TODO: inserire softwareBean%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Ulteriori informazioni da dbpedia -->
                <div class="row">
                    <div class="col m-1 p-1">
                        <div class="card border-left-warning shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                            Ulteriori informazioni
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            Informazioni interessanti prese da DBPEDIA con una describe
                                            <%//TODO: inserire ulteriori informazioni prese da DBPEDIA con una describe%>
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