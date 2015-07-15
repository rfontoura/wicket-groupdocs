$(function() {
    var name = 'pdf.pdf'; // TODO: substituir por nome de arquivo vindo de outro lugar
    $.get(getContextPath() + '/GetFilePathHandler', {
        nomeArquivo : name
    }, function(infoString) {
        var info = JSON.parse(infoString);
        visualizarDocumentoAnotacaoComId(info);
    }).fail(function(e) {
        alert('Erro: ' + e);
    });
})

/**
 * Importação dinâmica de JS ou CSS como em http://www.javascriptkit.com/javatutors/loadjavascriptcss.shtml.
 * 
 * @param nome
 *            nome do arquivo
 * @param tipo
 *            css ou js
 */
function carregarJsOuCss(nome, tipo) {
    if (tipo == "js") { // Javascript
        var fileref = document.createElement('script')
        fileref.setAttribute("type", "text/javascript")
        fileref.setAttribute("src", nome)
    } else if (tipo == "css") { // CSS
        var fileref = document.createElement("link")
        fileref.setAttribute("rel", "stylesheet")
        fileref.setAttribute("type", "text/css")
        fileref.setAttribute("href", nome)
    }

    if (typeof fileref != "undefined") {
        document.getElementsByTagName("head")[0].appendChild(fileref)
    }
}

function configurarModernizrCssTransformers() {
    if (!window.Modernizr.csstransforms) {
        carregarJsOuCss(getBaseUrl() + '/resources/js/groupdocs/turn.html4.min.js')
    }
}

function getBaseUrl() {
    var href = window.location.href.split('/');
    return href[0] + '//' + href[2];
}

/**
 * Retorna o caminho do contexto da aplicação.
 */
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

function configurarApplicationPath() {
    // TODO: configurar e pegar contexto e porta por JS

    $.ui.groupdocsViewer.prototype.applicationPath = 'http://localhost:8080/chronus/';
    $.ui.groupdocsViewer.prototype.useHttpHandlers = true;

    window.baseUrl = 'http://localhost:8080/chronus/';
    window.isCaseSensitive = false;
    window.searchForSeparateWords = true;
}

function configurarContainer() {
    var container = window.Container || new JsInject.Container();
    container.Register('PathProvider', function(c) {
        return jSaaspose.utils;
    }, true);
    window.Container = container;
    window.groupdocsAnnotationFrontEndVersion = function() {
        var gVersion = '1.1.0';
        var gUpDate = '2014.11.19';
        return 'GroupDocs.Annotation front-end v' + gVersion + ' updated by ' + gUpDate;
    };
}

function visualizarDocumentoAnotacaoComId(info) {
    var annotationWidget = $('#container-visualizador').groupdocsAnnotation({
        localizedStrings : null,
        thumbsImageBase64Encoded : undefined,
        width : 0,
        height : 0,
        fileId : info.idArquivo,
        docViewerId : 'annotation-widget-doc-viewer',
        quality : 100,
        enableRightClickMenu : false,
        showHeader : true,
        showZoom : true,
        showPaging : true,
        showPrint : false,
        showFileExplorer : false,
        showThumbnails : true,
        showToolbar : false,
        openThumbnails : true,
        zoomToFitWidth : true,
        zoomToFitHeight : true,
        initialZoom : 100,
        preloadPagesCount : 0,
        enableSidePanel : false,
        scrollOnFocus : true,
        strikeOutColor : '#00000c',
        highlightColor : '#000017',
        underlineColor : '#FF0000',
        textFieldBackgroundColor : '#990000',
        enabledTools : 8191,
        connectorPosition : 0,
        saveReplyOnFocusLoss : false,
        clickableAnnotations : true,
        disconnectUncommented : false,
        enableStandardErrorHandling : true,
        strikeoutMode : 1,
        undoEnabled : true,
        anyToolSelection : true,
        tabNavigationEnabled : false,
        minimumImageWidth : 150,
        areaToolOptions : {
            pen : {
                width : 1,
                color : -65536,
                dashStyle : 0
            },
            brush : {
                color : -16711936
            }
        },
        polylineToolOptions : {
            pen : {
                width : 1,
                color : -65536,
                dashStyle : 0
            },
            brush : {
                color : -16711936
            }
        },
        arrowToolOptions : {
            pen : {
                width : 1,
                color : -65536,
                dashStyle : 0
            },
            brush : {
                color : -16711936
            }
        },
        distanceToolOptions : {
            pen : {
                color : -16776961
            }
        },
        sideboarContainerSelector : 'div.comments_sidebar_wrapper',
        usePageNumberInUrlHash : false,
        textSelectionSynchronousCalculation : true,
        variableHeightPageSupport : true,
        useJavaScriptDocumentDescription : true,
        isRightPanelEnabled : false,
        createMarkup : true,
        use_pdf : 'true',
        _mode : 'annotatedDocument',
        selectionContainerSelector : "[name='selection-content']",
        graphicsContainerSelector : '.annotationsContainer',
        userName : info.nomeUsuario,
        userId : info.idUsuario
    }).error(function(erro) {
        // TODO: tratar amigavelmente
        alert("erro annotation: " + erro);
    });
}

function visualizarDocumentoComId(info) {
    var localizedStrings = null, thumbsImageBase64Encoded = null;
    $('#container-visualizador').visualizarDocumentoAnotacaoComId({
        filePath : info.idArquivo,
        quality : 100,
        showThumbnails : true,
        openThumbnails : true,
        initialZoom : 100,
        zoomToFitWidth : true,
        zoomToFitHeight : false,
        width : 1000,
        height : 650,
        backgroundColor : '',
        showFolderBrowser : true,
        showPrint : true,
        showDownload : true,
        showZoom : true,
        showPaging : true,
        showViewerStyleControl : true,
        showSearch : true,
        preloadPagesCount : 0,
        viewerStyle : 1,
        supportTextSelection : true,
        usePdfPrinting : false,
        localizedStrings : localizedStrings,
        thumbsImageBase64Encoded : thumbsImageBase64Encoded,
        toolbarButtonsBoxShadowStyle : '',
        toolbarButtonsBoxShadowHoverStyle : '',
        thumbnailsContainerBackgroundColor : '',
        thumbnailsContainerBorderRightColor : '',
        toolbarBorderBottomColor : '',
        toolbarInputFieldBorderColor : '',
        toolbarButtonBorderColor : '',
        toolbarButtonBorderHoverColor : '',
        thumbnailsContainerWidth : 0,
        jqueryFileDownloadCookieName : 'jqueryFileDownloadJSForGD',
        showDownloadErrorsInPopup : false,
        showImageWidth : false,
        showHeader : true,
        minimumImageWidth : 0,
        enableStandardErrorHandling : true,
        useHtmlBasedEngine : false,
        useImageBasedPrinting : true,
        fileDisplayName : '',
        downloadPdfFile : false,
        searchForSeparateWords : false,
        preventTouchEventsBubbling : false,
        useInnerThumbnails : true,
        watermarkText : '',
        watermarkColor : '',
        supportPageReordering : false,
        onlyShrinkLargePages : true,
        searchHighlightColor : '',
        currentSearchHighlightColor : '',
        treatPhrasesInDoubleQuotesAsExactPhrases : false,
        usePngImagesForHtmlBasedEngine : false,
        showOnePageInRow : false,
        loadAllPagesOnSearch : false,
        useEmScaling : false
    });
}