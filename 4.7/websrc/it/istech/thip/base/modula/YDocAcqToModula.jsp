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
  BODataCollector YDocAcqToModulaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YDocAcqToModulaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YDocAcqToModulaForm", "YDocAcqToModula", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/istech/thip/base/modula/YDocAcqToModula.js"); 
  YDocAcqToModulaForm.setServletEnvironment(se); 
  YDocAcqToModulaForm.setJSTypeList(jsList); 
  YDocAcqToModulaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YDocAcqToModulaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YDocAcqToModulaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YDocAcqToModulaForm.getMode(); 
  String key = YDocAcqToModulaForm.getKey(); 
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
        YDocAcqToModulaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YDocAcqToModulaForm.findBODataCollectorName(); 
                YDocAcqToModulaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YDocAcqToModulaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YDocAcqToModulaBODC).setServletEnvironment(se); 
        YDocAcqToModulaBODC.initialize("YDocAcqToModula", true, 0); 
        YDocAcqToModulaForm.setBODataCollector(YDocAcqToModulaBODC); 
        int rcBODC = YDocAcqToModulaForm.initSecurityServices(); 
        mode = YDocAcqToModulaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YDocAcqToModulaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YDocAcqToModulaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YDocAcqToModulaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YDocAcqToModulaForm); 
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
  myToolBarTB.setParent(YDocAcqToModulaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YDocAcqToModulaForm.getBodyOnBeforeUnload()%>" onload="<%=YDocAcqToModulaForm.getBodyOnLoad()%>" onunload="<%=YDocAcqToModulaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YDocAcqToModulaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YDocAcqToModulaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YDocAcqToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YDocAcqToModulaForm.getServlet()%>" method="post" name="YDocAcqToModulaForm" style="height:100%"><%
  YDocAcqToModulaForm.writeFormStartElements(out); 
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
          <td height="100%">
            <!--<span class="tabbed" id="mytabbed">-->
<table width="100%" height="100%" cellpadding="0" cellspacing="0" style="padding-right:1px">
   <tr valign="top">
     <td><% 
  WebTabbed mytabbed = new com.thera.thermfw.web.WebTabbed("mytabbed", "100%", "100%"); 
  mytabbed.setParent(YDocAcqToModulaForm); 
 mytabbed.addTab("tab1", "it.istech.thip.base.modula.resources.YDocAcqToModula", "tab1", "YDocAcqToModula", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocAcqToModula", "RAnnoDocAcq", null); 
   label.setParent(YDocAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RAnnoDocAcq"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocAcqToModulaRAnnoDocAcq =  
     new com.thera.thermfw.web.WebTextInput("YDocAcqToModula", "RAnnoDocAcq"); 
  YDocAcqToModulaRAnnoDocAcq.setParent(YDocAcqToModulaForm); 
%>
<input class="<%=YDocAcqToModulaRAnnoDocAcq.getClassType()%>" id="<%=YDocAcqToModulaRAnnoDocAcq.getId()%>" maxlength="<%=YDocAcqToModulaRAnnoDocAcq.getMaxLength()%>" name="<%=YDocAcqToModulaRAnnoDocAcq.getName()%>" size="<%=YDocAcqToModulaRAnnoDocAcq.getSize()%>"><% 
  YDocAcqToModulaRAnnoDocAcq.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocAcqToModula", "RNumeroDocAcq", null); 
   label.setParent(YDocAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RNumeroDocAcq"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocAcqToModulaRNumeroDocAcq =  
     new com.thera.thermfw.web.WebTextInput("YDocAcqToModula", "RNumeroDocAcq"); 
  YDocAcqToModulaRNumeroDocAcq.setParent(YDocAcqToModulaForm); 
%>
<input class="<%=YDocAcqToModulaRNumeroDocAcq.getClassType()%>" id="<%=YDocAcqToModulaRNumeroDocAcq.getId()%>" maxlength="<%=YDocAcqToModulaRNumeroDocAcq.getMaxLength()%>" name="<%=YDocAcqToModulaRNumeroDocAcq.getName()%>" size="<%=YDocAcqToModulaRNumeroDocAcq.getSize()%>"><% 
  YDocAcqToModulaRNumeroDocAcq.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocAcqToModula", "RRigaDoc", null); 
   label.setParent(YDocAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RRigaDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocAcqToModulaRRigaDoc =  
     new com.thera.thermfw.web.WebTextInput("YDocAcqToModula", "RRigaDoc"); 
  YDocAcqToModulaRRigaDoc.setParent(YDocAcqToModulaForm); 
%>
<input class="<%=YDocAcqToModulaRRigaDoc.getClassType()%>" id="<%=YDocAcqToModulaRRigaDoc.getId()%>" maxlength="<%=YDocAcqToModulaRRigaDoc.getMaxLength()%>" name="<%=YDocAcqToModulaRRigaDoc.getName()%>" size="<%=YDocAcqToModulaRRigaDoc.getSize()%>"><% 
  YDocAcqToModulaRRigaDoc.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocAcqToModula", "RDetRigaDoc", null); 
   label.setParent(YDocAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RDetRigaDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocAcqToModulaRDetRigaDoc =  
     new com.thera.thermfw.web.WebTextInput("YDocAcqToModula", "RDetRigaDoc"); 
  YDocAcqToModulaRDetRigaDoc.setParent(YDocAcqToModulaForm); 
%>
<input class="<%=YDocAcqToModulaRDetRigaDoc.getClassType()%>" id="<%=YDocAcqToModulaRDetRigaDoc.getId()%>" maxlength="<%=YDocAcqToModulaRDetRigaDoc.getMaxLength()%>" name="<%=YDocAcqToModulaRDetRigaDoc.getName()%>" size="<%=YDocAcqToModulaRDetRigaDoc.getSize()%>"><% 
  YDocAcqToModulaRDetRigaDoc.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <label for="RArticolo"></label>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YDocAcqToModulaRelArticolo =  
     new com.thera.thermfw.web.WebMultiSearchForm("YDocAcqToModula", "RelArticolo", false, false, true, 1, null, null); 
  YDocAcqToModulaRelArticolo.setParent(YDocAcqToModulaForm); 
  YDocAcqToModulaRelArticolo.write(out); 
%>
<!--<span class="multisearchform" id="RelArticolo"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocAcqToModula", "QtaDaEvadere", null); 
   label.setParent(YDocAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaDaEvadere"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocAcqToModulaQtaOriginale =  
     new com.thera.thermfw.web.WebTextInput("YDocAcqToModula", "QtaOriginale"); 
  YDocAcqToModulaQtaOriginale.setParent(YDocAcqToModulaForm); 
%>
<input class="<%=YDocAcqToModulaQtaOriginale.getClassType()%>" id="<%=YDocAcqToModulaQtaOriginale.getId()%>" maxlength="<%=YDocAcqToModulaQtaOriginale.getMaxLength()%>" name="<%=YDocAcqToModulaQtaOriginale.getName()%>" size="<%=YDocAcqToModulaQtaOriginale.getSize()%>"><% 
  YDocAcqToModulaQtaOriginale.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocAcqToModula", "QtaEvasa", null); 
   label.setParent(YDocAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaEvasa"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocAcqToModulaQtaEvasa =  
     new com.thera.thermfw.web.WebTextInput("YDocAcqToModula", "QtaEvasa"); 
  YDocAcqToModulaQtaEvasa.setParent(YDocAcqToModulaForm); 
%>
<input class="<%=YDocAcqToModulaQtaEvasa.getClassType()%>" id="<%=YDocAcqToModulaQtaEvasa.getId()%>" maxlength="<%=YDocAcqToModulaQtaEvasa.getMaxLength()%>" name="<%=YDocAcqToModulaQtaEvasa.getName()%>" size="<%=YDocAcqToModulaQtaEvasa.getSize()%>"><% 
  YDocAcqToModulaQtaEvasa.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocAcqToModula", "QtaOriginale", null); 
   label.setParent(YDocAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaOriginale"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocAcqToModulaQtaResidua =  
     new com.thera.thermfw.web.WebTextInput("YDocAcqToModula", "QtaResidua"); 
  YDocAcqToModulaQtaResidua.setParent(YDocAcqToModulaForm); 
%>
<input class="<%=YDocAcqToModulaQtaResidua.getClassType()%>" id="<%=YDocAcqToModulaQtaResidua.getId()%>" maxlength="<%=YDocAcqToModulaQtaResidua.getMaxLength()%>" name="<%=YDocAcqToModulaQtaResidua.getName()%>" size="<%=YDocAcqToModulaQtaResidua.getSize()%>"><% 
  YDocAcqToModulaQtaResidua.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocAcqToModula", "QtaResidua", null); 
   label.setParent(YDocAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaResidua"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocAcqToModulaGiacenza =  
     new com.thera.thermfw.web.WebTextInput("YDocAcqToModula", "Giacenza"); 
  YDocAcqToModulaGiacenza.setParent(YDocAcqToModulaForm); 
%>
<input class="<%=YDocAcqToModulaGiacenza.getClassType()%>" id="<%=YDocAcqToModulaGiacenza.getId()%>" maxlength="<%=YDocAcqToModulaGiacenza.getMaxLength()%>" name="<%=YDocAcqToModulaGiacenza.getName()%>" size="<%=YDocAcqToModulaGiacenza.getSize()%>"><% 
  YDocAcqToModulaGiacenza.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocAcqToModula", "Giacenza", null); 
   label.setParent(YDocAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="Giacenza"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocAcqToModulaQtaDaEvadere =  
     new com.thera.thermfw.web.WebTextInput("YDocAcqToModula", "QtaDaEvadere"); 
  YDocAcqToModulaQtaDaEvadere.setParent(YDocAcqToModulaForm); 
%>
<input class="<%=YDocAcqToModulaQtaDaEvadere.getClassType()%>" id="<%=YDocAcqToModulaQtaDaEvadere.getId()%>" maxlength="<%=YDocAcqToModulaQtaDaEvadere.getMaxLength()%>" name="<%=YDocAcqToModulaQtaDaEvadere.getName()%>" size="<%=YDocAcqToModulaQtaDaEvadere.getSize()%>"><% 
  YDocAcqToModulaQtaDaEvadere.write(out); 
%>

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
  errorList.setParent(YDocAcqToModulaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YDocAcqToModulaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YDocAcqToModulaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YDocAcqToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YDocAcqToModulaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YDocAcqToModulaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YDocAcqToModulaBODC.getErrorList().getErrors()); 
           if(YDocAcqToModulaBODC.getConflict() != null) 
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
     if(YDocAcqToModulaBODC != null && !YDocAcqToModulaBODC.close(false)) 
        errors.addAll(0, YDocAcqToModulaBODC.getErrorList().getErrors()); 
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
     String errorPage = YDocAcqToModulaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YDocAcqToModulaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YDocAcqToModulaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
