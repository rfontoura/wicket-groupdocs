# wicket-groupdocs
GroupDocs example app using Wicket Framework

## Configuration on Eclipse IDE
* Create a directory on `C:/temp/documentos-exemplo/` and put a PDF file named `pdf.pdf` on it. An alternative is to create a PDF file wherever you want and change the properties `groupdocs.annotation.defaultFileName` and `groupdocs.annotation.basePath` on file `group-docs.properties`
* Execute `mvn eclipse:eclipse`
* Execute `mvn install`

## Running the project

### To run the project using a simple JSP without Wicket Framework
* Open `web.xml` and change the following lines:
  * Comment the Wicket Servlet mapping on lines 72-75
  * Uncomment IndexServlet mappings on lines 83-86
* On Eclipse IDE, run class `br.com.dataeasy.chronus.web.JettyTeste` **as a Java Application**
* Open you browser at URL http://localhost:8080/chronus/visualizador/teste
* Note that the viewer works

### To run the project using Wicket Framework with HTML
* Open `web.xml` and change the following lines:
  * Uncomment the Wicket Servlet mapping on lines 72-75
  * Comment IndexServlet mappings on lines 83-86
* On Eclipse IDE, run class `br.com.dataeasy.chronus.web.JettyTeste` **as a Java Application**
* Open you browser at URL http://localhost:8080/chronus/visualizador/teste
  * The mapping to `/visualizador/teste` path is configured on class `br.com.dataeasy.chronus.web.wicket.bookmarkable.TestBookmarkablePages`

## Problems

### How to see the conflicts of `bootstrap.min.js` and `GroupdocsAnnotation.all.min.js`

* Open file `AbstractWebPage.java`
* Uncomment line 193 to enable including `resources/js/bootstrap.min.js` on the page and save
* Reopen the page http://localhost:8080/chronus/visualizador/teste
* Click on any menu ("Cadastro", "Documentos" or "Administração") and see that the submenus aren't shown

### Problem open documents on Internet Explorer 11

* Define the license file path on line 10 of `group-docs.properties` to a license that works
* Stop and start JettyTeste
* Use Internet Explorer 11 to open the page http://localhost:8080/chronus/visualizador/teste
* This problem doesn't occur on Firefox and Chrome.