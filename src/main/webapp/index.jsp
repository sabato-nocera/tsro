<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Index</title>

    <!-- Custom fonts for this template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">TSRO</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item">
            <a class="nav-link" href="index.jsp">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Index</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800 mt-4">Benvenuto!</h1>
                <p class="mb-4">
                    Interagisci con la tabella per consultare i dettagli delle repository software,
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
                                    <th>Nome</th>
                                    <th>Autore</th>
                                    <th>Topic</th>
                                    <th>Mi piace</th>
                                    <th>Commit</th>
                                    <th>Size</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Nome</th>
                                    <th>Autore</th>
                                    <th>Topic</th>
                                    <th>Mi piace</th>
                                    <th>Commit</th>
                                    <th>Size</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <tr>
                                    <td>WLB</td>
                                    <td>Mario Ruggiero</td>
                                    <td>Smartworking, prenotazione postazioni</td>
                                    <td>61</td>
                                    <td>657</td>
                                    <td>50MB</td>
                                </tr>
                                <tr>
                                    <td>EasyAid</td>
                                    <td>Sabato Nocera</td>
                                    <td>Erogazioni servizi DSA/disabili</td>
                                    <td>63</td>
                                    <td>589</td>
                                    <td>90MB</td>
                                </tr>
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
                                                <label for="size">Size totale</label>
                                                <input type="text" class="form-control form-control-user"
                                                       id="size"
                                                       placeholder="Size totale (<,>,=)"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-12 mb-3 mb-sm-0">
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
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2020</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script>

</body>

</html>