<%-- 
    Document   : index
    Created on : May 19, 2014, 12:35:58 AM
    Author     : Aleksey Pemyakov
--%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<head>
    <meta charset="utf-8">
    <title>GroupDocs Annotation for Java Sample</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/groupdocs/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/groupdocs/jquery-ui-1.10.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/groupdocs/knockout-3.0.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/groupdocs/turn.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/groupdocs/modernizr.2.6.2.Transform2d.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/groupdocs/visualizador-groupdocs.js"></script>
    <script type="text/javascript">
    	configurarModernizrCssTransformers();
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/groupdocs/installableViewer.min.js"></script>
    <script type="text/javascript">
    	configurarApplicationPath();
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/groupdocs/GroupdocsViewer.all.min.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/groupdocs/bootstrap-groupdocs.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/groupdocs/GroupdocsViewer.all.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/groupdocs/jquery-ui-1.10.3.dialog.min.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/groupdocs/jquery.tinyscrollbar.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/groupdocs/GroupdocsAnnotation.all.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/groupdocs/Annotation.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/groupdocs/Annotation.Toolbox.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/groupdocs/fixes.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/groupdocs/visualizador.css" />
    <!--  -->
    <!--[if IE]>
    <style type="text/css">
        input[type="text"].input_search {
            padding-right: 30px;
            width: 65px;
        }
    </style>
    <![endif]-->
    <!--[if IE 9]>
    <style type="text/css">
        span.input_search_clear {
            left: 140px;
        }
    </style>
    <![endif]-->
    <script type="text/javascript">
    	configurarContainer();
    </script>
</head>

<body>
    <div id="container-visualizador"></div>
</body>
</html>