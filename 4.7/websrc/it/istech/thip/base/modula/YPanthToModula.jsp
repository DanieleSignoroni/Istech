<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///W:\PthDev\Projects\Panthera\Istech\WebContent\dtd/xhtml1-transitional.dtd">
<html>
<!-- WIZGEN Therm 2.0.0 as Form - multiBrowserGen = true -->
<%=WebGenerator.writeRuntimeInfo()%>
  <head>
<%@ page contentType="text/html; charset=Cp1252"%>
<%@ page import= " 
  java.sql.*, 
  java.util.*, 
  java.lang.reflect.*, 
  javax.naming.*, 
  com.thera.thermfw.common.*, 
  com.thera.thermfw.type.*, 
  com.thera.thermfw.web.*, 
  com.thera.thermfw.security.*, 
  com.thera.thermfw.base.*, 
  com.thera.thermfw.ad.*, 
  com.thera.thermfw.persist.*, 
  com.thera.thermfw.gui.cnr.*, 
  com.thera.thermfw.setting.*, 
  com.thera.thermfw.collector.*, 
  com.thera.thermfw.batch.web.*, 
  com.thera.thermfw.batch.*, 
  com.thera.thermfw.pref.* 
"%> 
<%
  ServletEnvironment se = (ServletEnvironment)Factory.createObject("com.thera.thermfw.web.ServletEnvironment"); 
  BODataCollector YPanthToModulaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YPanthToModulaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YPanthToModulaForm", "YPanthToModula", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/istech/thip/base/modula/YPanthToModula.js"); 
  YPanthToModulaForm.setServletEnvironment(se); 
  YPanthToModulaForm.setJSTypeList(jsList); 
  YPanthToModulaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YPanthToModulaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YPanthToModulaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YPanthToModulaForm.getMode(); 
  String key = YPanthToModulaForm.getKey(); 
  String errorMessage; 
  boolean requestIsValid = false; 
  boolean leftIsKey = false; 
  boolean conflitPresent = false; 
  String leftClass = ""; 
  try 
  {
     se.initialize(request, response); 
     if(se.begin()) 
     { 
        YPanthToModulaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YPanthToModulaForm.findBODataCollectorName(); 
                YPanthToModulaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YPanthToModulaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YPanthToModulaBODC).setServletEnvironment(se); 
        YPanthToModulaBODC.initialize("YPanthToModula", true, 0); 
        YPanthToModulaForm.setBODataCollector(YPanthToModulaBODC); 
        int rcBODC = YPanthToModulaForm.initSecurityServices(); 
        mode = YPanthToModulaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YPanthToModulaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YPanthToModulaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YPanthToModulaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YPanthToModulaForm); 
   request.setAttribute("menuBar", menuBar); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="menuBar"/> 
</jsp:include> 
<% 
  menuBar.write(out); 
  menuBar.writeChildren(out); 
%> 
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(YPanthToModulaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YPanthToModulaForm.getBodyOnBeforeUnload()%>" onload="<%=YPanthToModulaForm.getBodyOnLoad()%>" onunload="<%=YPanthToModulaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YPanthToModulaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YPanthToModulaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YPanthToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YPanthToModulaForm.getServlet()%>" method="post" name="YPanthToModulaForm" style="height:100%"><%
  YPanthToModulaForm.writeFormStartElements(out); 
%>

      <table cellpadding="0" cellspacing="0" height="100%" id="emptyborder" width="100%">
        <tr>
          <td style="height:0">
            <% menuBar.writeElements(out); %> 

          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% myToolBarTB.writeChildren(out); %> 

          </td>
        </tr>
        <tr>
          <td>
            <% 
  WebTextInput YPanthToModulaIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YPanthToModula", "IdAzienda"); 
  YPanthToModulaIdAzienda.setParent(YPanthToModulaForm); 
%>
<input class="<%=YPanthToModulaIdAzienda.getClassType()%>" id="<%=YPanthToModulaIdAzienda.getId()%>" maxlength="<%=YPanthToModulaIdAzienda.getMaxLength()%>" name="<%=YPanthToModulaIdAzienda.getName()%>" size="<%=YPanthToModulaIdAzienda.getSize()%>" type="hidden"><% 
  YPanthToModulaIdAzienda.write(out); 
%>

          </td>
        </tr>
        <tr>
          <td height="100%">
            <!--<span class="tabbed" id="mytabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed mytabbed = new com.thera.thermfw.web.WebTabbed("mytabbed", "100%", "100%"); 
  mytabbed.setParent(YPanthToModulaForm); 
 mytabbed.addTab("tab1", "it.istech.thip.base.modula.resources.YPanthToModula", "tab1", "YPanthToModula", null, null, null, null); 
  mytabbed.write(out); 
%>

     </td>
   </tr>
   <tr>
     <td height="100%"><div class="tabbed_pagine" id="tabbedPagine" style="position: relative; width: 100%; height: 100%;">
              <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab1")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab1"); %>
                <table style="width: 100%;">
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPanthToModula", "TipoDoc", null); 
   label.setParent(YPanthToModulaForm); 
%><label class="<%=label.getClassType()%>" for="TipoDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebComboBox YPanthToModulaTipoDoc =  
     new com.thera.thermfw.web.WebComboBox("YPanthToModula", "TipoDoc", null); 
  YPanthToModulaTipoDoc.setParent(YPanthToModulaForm); 
%>
<select id="<%=YPanthToModulaTipoDoc.getId()%>" name="<%=YPanthToModulaTipoDoc.getName()%>"><% 
  YPanthToModulaTipoDoc.write(out); 
%> 
</select>
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPanthToModula", "IdAnnoDoc", null); 
   label.setParent(YPanthToModulaForm); 
%><label class="<%=label.getClassType()%>" for="IdAnnoDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YPanthToModulaIdAnnoDoc =  
     new com.thera.thermfw.web.WebTextInput("YPanthToModula", "IdAnnoDoc"); 
  YPanthToModulaIdAnnoDoc.setParent(YPanthToModulaForm); 
%>
<input class="<%=YPanthToModulaIdAnnoDoc.getClassType()%>" id="<%=YPanthToModulaIdAnnoDoc.getId()%>" maxlength="<%=YPanthToModulaIdAnnoDoc.getMaxLength()%>" name="<%=YPanthToModulaIdAnnoDoc.getName()%>" size="<%=YPanthToModulaIdAnnoDoc.getSize()%>"><% 
  YPanthToModulaIdAnnoDoc.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPanthToModula", "IdNumeroDoc", null); 
   label.setParent(YPanthToModulaForm); 
%><label class="<%=label.getClassType()%>" for="IdNumeroDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YPanthToModulaIdNumeroDoc =  
     new com.thera.thermfw.web.WebTextInput("YPanthToModula", "IdNumeroDoc"); 
  YPanthToModulaIdNumeroDoc.setParent(YPanthToModulaForm); 
%>
<input class="<%=YPanthToModulaIdNumeroDoc.getClassType()%>" id="<%=YPanthToModulaIdNumeroDoc.getId()%>" maxlength="<%=YPanthToModulaIdNumeroDoc.getMaxLength()%>" name="<%=YPanthToModulaIdNumeroDoc.getName()%>" size="<%=YPanthToModulaIdNumeroDoc.getSize()%>"><% 
  YPanthToModulaIdNumeroDoc.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPanthToModula", "IdRigaDoc", null); 
   label.setParent(YPanthToModulaForm); 
%><label class="<%=label.getClassType()%>" for="IdRigaDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YPanthToModulaIdRigaDoc =  
     new com.thera.thermfw.web.WebTextInput("YPanthToModula", "IdRigaDoc"); 
  YPanthToModulaIdRigaDoc.setParent(YPanthToModulaForm); 
%>
<input class="<%=YPanthToModulaIdRigaDoc.getClassType()%>" id="<%=YPanthToModulaIdRigaDoc.getId()%>" maxlength="<%=YPanthToModulaIdRigaDoc.getMaxLength()%>" name="<%=YPanthToModulaIdRigaDoc.getName()%>" size="<%=YPanthToModulaIdRigaDoc.getSize()%>"><% 
  YPanthToModulaIdRigaDoc.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPanthToModula", "IdDetRigaDoc", null); 
   label.setParent(YPanthToModulaForm); 
%><label class="<%=label.getClassType()%>" for="IdDetRigaDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YPanthToModulaIdDetRigaDoc =  
     new com.thera.thermfw.web.WebTextInput("YPanthToModula", "IdDetRigaDoc"); 
  YPanthToModulaIdDetRigaDoc.setParent(YPanthToModulaForm); 
%>
<input class="<%=YPanthToModulaIdDetRigaDoc.getClassType()%>" id="<%=YPanthToModulaIdDetRigaDoc.getId()%>" maxlength="<%=YPanthToModulaIdDetRigaDoc.getMaxLength()%>" name="<%=YPanthToModulaIdDetRigaDoc.getName()%>" size="<%=YPanthToModulaIdDetRigaDoc.getSize()%>"><% 
  YPanthToModulaIdDetRigaDoc.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPanthToModula", "TipoMov", null); 
   label.setParent(YPanthToModulaForm); 
%><label class="<%=label.getClassType()%>" for="TipoMov"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebComboBox YPanthToModulaTipoMov =  
     new com.thera.thermfw.web.WebComboBox("YPanthToModula", "TipoMov", null); 
  YPanthToModulaTipoMov.setParent(YPanthToModulaForm); 
%>
<select id="<%=YPanthToModulaTipoMov.getId()%>" name="<%=YPanthToModulaTipoMov.getName()%>"><% 
  YPanthToModulaTipoMov.write(out); 
%> 
</select>
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPanthToModula", "QtaEvasaUmPrm", null); 
   label.setParent(YPanthToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaEvasaUmPrm"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YPanthToModulaQtaEvasaUmPrm =  
     new com.thera.thermfw.web.WebTextInput("YPanthToModula", "QtaEvasaUmPrm"); 
  YPanthToModulaQtaEvasaUmPrm.setParent(YPanthToModulaForm); 
%>
<input class="<%=YPanthToModulaQtaEvasaUmPrm.getClassType()%>" id="<%=YPanthToModulaQtaEvasaUmPrm.getId()%>" maxlength="<%=YPanthToModulaQtaEvasaUmPrm.getMaxLength()%>" name="<%=YPanthToModulaQtaEvasaUmPrm.getName()%>" size="<%=YPanthToModulaQtaEvasaUmPrm.getSize()%>"><% 
  YPanthToModulaQtaEvasaUmPrm.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <% 
   request.setAttribute("parentForm", YPanthToModulaForm); 
   String CDForDatiComuniEstesi$it$thera$thip$cs$DatiComuniEstesi$jsp = "DatiComuniEstesi"; 
%>
<jsp:include page="/it/thera/thip/cs/DatiComuniEstesi.jsp" flush="true"> 
<jsp:param name="CDName" value="<%=CDForDatiComuniEstesi$it$thera$thip$cs$DatiComuniEstesi$jsp%>"/> 
</jsp:include> 
<!--<span class="subform" id="DatiComuniEstesi"></span>-->
                    </td>
                    <td valign="top">
                    </td>
                  </tr>
                </table>
              <% mytabbed.endTab(); %> 
</div>
            </div><% mytabbed.endTabbed();%> 

     </td>
   </tr>
</table><!--</span>-->
          </td>
        </tr>
        <tr>
          <td style="height:0">
            <% 
  WebErrorList errorList = new com.thera.thermfw.web.WebErrorList(); 
  errorList.setParent(YPanthToModulaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YPanthToModulaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YPanthToModulaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YPanthToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YPanthToModulaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YPanthToModulaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YPanthToModulaBODC.getErrorList().getErrors()); 
           if(YPanthToModulaBODC.getConflict() != null) 
                conflitPresent = true; 
     } 
     else 
        errors.add(new ErrorMessage("BAS0000010")); 
  } 
  catch(NamingException e) { 
     errorMessage = e.getMessage(); 
     errors.add(new ErrorMessage("CBS000025", errorMessage));  } 
  catch(SQLException e) {
     errorMessage = e.getMessage(); 
     errors.add(new ErrorMessage("BAS0000071", errorMessage));  } 
  catch(Throwable e) {
     e.printStackTrace(Trace.excStream);
  }
  finally 
  {
     if(YPanthToModulaBODC != null && !YPanthToModulaBODC.close(false)) 
        errors.addAll(0, YPanthToModulaBODC.getErrorList().getErrors()); 
     try 
     { 
        se.end(); 
     }
     catch(IllegalArgumentException e) { 
        e.printStackTrace(Trace.excStream); 
     } 
     catch(SQLException e) { 
        e.printStackTrace(Trace.excStream); 
     } 
  } 
  if(!errors.isEmpty())
  { 
      if(!conflitPresent)
  { 
     request.setAttribute("ErrorMessages", errors); 
     String errorPage = YPanthToModulaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YPanthToModulaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YPanthToModulaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
