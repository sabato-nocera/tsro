<%@ page isErrorPage="true" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Ops..."/>
</jsp:include>

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

                <!-- Error Text -->
                <div class="text-center">
                    <div class="error mx-auto mt-4" data-text="Ops!">Ops!</div>
                    <p class="lead text-gray-800 mb-5">Qualcosa &egrave; andato storto! </p>
                    <p class="lead text-gray-800 mb-5">Errore <%=response.getStatus()%>
                    </p>
                    <p class="lead text-gray-800 mb-5"><%
                        if (exception != null) {
                            out.flush();
                            response.getWriter().println(exception.getMessage());
                            exception.printStackTrace();
                        }
                    %></p>
                    <p class="text-gray-500 mb-0">Si &egrave; verificato un errore...</p>
                    <a href="index.jsp">&larr; Ritorna alla pagina principale</a>
                </div>

            </div>
            <!-- /.container-fluid -->
        </div>
    </div>
</div>
<!-- End of Main Content -->

<jsp:include page="footer.jsp"/>