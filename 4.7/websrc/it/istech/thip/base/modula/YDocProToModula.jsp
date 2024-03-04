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
  BODataCollector YDocProToModulaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YDocProToModulaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YDocProToModulaForm", "YDocProToModula", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/istech/thip/base/modula/YDocProToModula.js"); 
  YDocProToModulaForm.setServletEnvironment(se); 
  YDocProToModulaForm.setJSTypeList(jsList); 
  YDocProToModulaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YDocProToModulaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YDocProToModulaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YDocProToModulaForm.getMode(); 
  String key = YDocProToModulaForm.getKey(); 
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
        YDocProToModulaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YDocProToModulaForm.findBODataCollectorName(); 
                YDocProToModulaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YDocProToModulaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YDocProToModulaBODC).setServletEnvironment(se); 
        YDocProToModulaBODC.initialize("YDocProToModula", true, 0); 
        YDocProToModulaForm.setBODataCollector(YDocProToModulaBODC); 
        int rcBODC = YDocProToModulaForm.initSecurityServices(); 
        mode = YDocProToModulaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YDocProToModulaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YDocProToModulaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YDocProToModulaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YDocProToModulaForm); 
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
  myToolBarTB.setParent(YDocProToModulaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YDocProToModulaForm.getBodyOnBeforeUnload()%>" onload="<%=YDocProToModulaForm.getBodyOnLoad()%>" onunload="<%=YDocProToModulaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YDocProToModulaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YDocProToModulaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YDocProToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YDocProToModulaForm.getServlet()%>" method="post" name="YDocProToModulaForm" style="height:100%"><%
  YDocProToModulaForm.writeFormStartElements(out); 
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
  WebTextInput YDocProToModulaIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YDocProToModula", "IdAzienda"); 
  YDocProToModulaIdAzienda.setParent(YDocProToModulaForm); 
%>
<input class="<%=YDocProToModulaIdAzienda.getClassType()%>" id="<%=YDocProToModulaIdAzienda.getId()%>" maxlength="<%=YDocProToModulaIdAzienda.getMaxLength()%>" name="<%=YDocProToModulaIdAzienda.getName()%>" size="<%=YDocProToModulaIdAzienda.getSize()%>" type="hidden"><% 
  YDocProToModulaIdAzienda.write(out); 
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
  mytabbed.setParent(YDocProToModulaForm); 
 mytabbed.addTab("tab1", "it.istech.thip.base.modula.resources.YDocProToModula", "tab1", "YDocProToModula", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocProToModula", "RAnnoDocPro", null); 
   label.setParent(YDocProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RAnnoDocPro"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocProToModulaRAnnoDocPro =  
     new com.thera.thermfw.web.WebTextInput("YDocProToModula", "RAnnoDocPro"); 
  YDocProToModulaRAnnoDocPro.setParent(YDocProToModulaForm); 
%>
<input class="<%=YDocProToModulaRAnnoDocPro.getClassType()%>" id="<%=YDocProToModulaRAnnoDocPro.getId()%>" maxlength="<%=YDocProToModulaRAnnoDocPro.getMaxLength()%>" name="<%=YDocProToModulaRAnnoDocPro.getName()%>" size="<%=YDocProToModulaRAnnoDocPro.getSize()%>"><% 
  YDocProToModulaRAnnoDocPro.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocProToModula", "RNumeroDocPro", null); 
   label.setParent(YDocProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RNumeroDocPro"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocProToModulaRNumeroDocPro =  
     new com.thera.thermfw.web.WebTextInput("YDocProToModula", "RNumeroDocPro"); 
  YDocProToModulaRNumeroDocPro.setParent(YDocProToModulaForm); 
%>
<input class="<%=YDocProToModulaRNumeroDocPro.getClassType()%>" id="<%=YDocProToModulaRNumeroDocPro.getId()%>" maxlength="<%=YDocProToModulaRNumeroDocPro.getMaxLength()%>" name="<%=YDocProToModulaRNumeroDocPro.getName()%>" size="<%=YDocProToModulaRNumeroDocPro.getSize()%>"><% 
  YDocProToModulaRNumeroDocPro.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocProToModula", "RRigaDoc", null); 
   label.setParent(YDocProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RRigaDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocProToModulaRRigaDoc =  
     new com.thera.thermfw.web.WebTextInput("YDocProToModula", "RRigaDoc"); 
  YDocProToModulaRRigaDoc.setParent(YDocProToModulaForm); 
%>
<input class="<%=YDocProToModulaRRigaDoc.getClassType()%>" id="<%=YDocProToModulaRRigaDoc.getId()%>" maxlength="<%=YDocProToModulaRRigaDoc.getMaxLength()%>" name="<%=YDocProToModulaRRigaDoc.getName()%>" size="<%=YDocProToModulaRRigaDoc.getSize()%>"><% 
  YDocProToModulaRRigaDoc.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocProToModula", "RDetRigaDoc", null); 
   label.setParent(YDocProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RDetRigaDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocProToModulaRDetRigaDoc =  
     new com.thera.thermfw.web.WebTextInput("YDocProToModula", "RDetRigaDoc"); 
  YDocProToModulaRDetRigaDoc.setParent(YDocProToModulaForm); 
%>
<input class="<%=YDocProToModulaRDetRigaDoc.getClassType()%>" id="<%=YDocProToModulaRDetRigaDoc.getId()%>" maxlength="<%=YDocProToModulaRDetRigaDoc.getMaxLength()%>" name="<%=YDocProToModulaRDetRigaDoc.getName()%>" size="<%=YDocProToModulaRDetRigaDoc.getSize()%>"><% 
  YDocProToModulaRDetRigaDoc.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocProToModula", "RArticolo", null); 
   label.setParent(YDocProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RArticolo"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocProToModulaRArticolo =  
     new com.thera.thermfw.web.WebTextInput("YDocProToModula", "RArticolo"); 
  YDocProToModulaRArticolo.setParent(YDocProToModulaForm); 
%>
<input class="<%=YDocProToModulaRArticolo.getClassType()%>" id="<%=YDocProToModulaRArticolo.getId()%>" maxlength="<%=YDocProToModulaRArticolo.getMaxLength()%>" name="<%=YDocProToModulaRArticolo.getName()%>" size="<%=YDocProToModulaRArticolo.getSize()%>"><% 
  YDocProToModulaRArticolo.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocProToModula", "QtaOriginale", null); 
   label.setParent(YDocProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaOriginale"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocProToModulaQtaResidua =  
     new com.thera.thermfw.web.WebTextInput("YDocProToModula", "QtaResidua"); 
  YDocProToModulaQtaResidua.setParent(YDocProToModulaForm); 
%>
<input class="<%=YDocProToModulaQtaResidua.getClassType()%>" id="<%=YDocProToModulaQtaResidua.getId()%>" maxlength="<%=YDocProToModulaQtaResidua.getMaxLength()%>" name="<%=YDocProToModulaQtaResidua.getName()%>" size="<%=YDocProToModulaQtaResidua.getSize()%>"><% 
  YDocProToModulaQtaResidua.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocProToModula", "QtaEvasa", null); 
   label.setParent(YDocProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaEvasa"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocProToModulaQtaEvasa =  
     new com.thera.thermfw.web.WebTextInput("YDocProToModula", "QtaEvasa"); 
  YDocProToModulaQtaEvasa.setParent(YDocProToModulaForm); 
%>
<input class="<%=YDocProToModulaQtaEvasa.getClassType()%>" id="<%=YDocProToModulaQtaEvasa.getId()%>" maxlength="<%=YDocProToModulaQtaEvasa.getMaxLength()%>" name="<%=YDocProToModulaQtaEvasa.getName()%>" size="<%=YDocProToModulaQtaEvasa.getSize()%>"><% 
  YDocProToModulaQtaEvasa.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocProToModula", "QtaResidua", null); 
   label.setParent(YDocProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaResidua"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocProToModulaQtaOriginale =  
     new com.thera.thermfw.web.WebTextInput("YDocProToModula", "QtaOriginale"); 
  YDocProToModulaQtaOriginale.setParent(YDocProToModulaForm); 
%>
<input class="<%=YDocProToModulaQtaOriginale.getClassType()%>" id="<%=YDocProToModulaQtaOriginale.getId()%>" maxlength="<%=YDocProToModulaQtaOriginale.getMaxLength()%>" name="<%=YDocProToModulaQtaOriginale.getName()%>" size="<%=YDocProToModulaQtaOriginale.getSize()%>"><% 
  YDocProToModulaQtaOriginale.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocProToModula", "Giacenza", null); 
   label.setParent(YDocProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="Giacenza"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocProToModulaGiacenza =  
     new com.thera.thermfw.web.WebTextInput("YDocProToModula", "Giacenza"); 
  YDocProToModulaGiacenza.setParent(YDocProToModulaForm); 
%>
<input class="<%=YDocProToModulaGiacenza.getClassType()%>" id="<%=YDocProToModulaGiacenza.getId()%>" maxlength="<%=YDocProToModulaGiacenza.getMaxLength()%>" name="<%=YDocProToModulaGiacenza.getName()%>" size="<%=YDocProToModulaGiacenza.getSize()%>"><% 
  YDocProToModulaGiacenza.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocProToModula", "QtaDaEvadere", null); 
   label.setParent(YDocProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaDaEvadere"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocProToModulaQtaDaEvadere =  
     new com.thera.thermfw.web.WebTextInput("YDocProToModula", "QtaDaEvadere"); 
  YDocProToModulaQtaDaEvadere.setParent(YDocProToModulaForm); 
%>
<input class="<%=YDocProToModulaQtaDaEvadere.getClassType()%>" id="<%=YDocProToModulaQtaDaEvadere.getId()%>" maxlength="<%=YDocProToModulaQtaDaEvadere.getMaxLength()%>" name="<%=YDocProToModulaQtaDaEvadere.getName()%>" size="<%=YDocProToModulaQtaDaEvadere.getSize()%>"><% 
  YDocProToModulaQtaDaEvadere.write(out); 
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
  errorList.setParent(YDocProToModulaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YDocProToModulaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YDocProToModulaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YDocProToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YDocProToModulaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YDocProToModulaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YDocProToModulaBODC.getErrorList().getErrors()); 
           if(YDocProToModulaBODC.getConflict() != null) 
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
     if(YDocProToModulaBODC != null && !YDocProToModulaBODC.close(false)) 
        errors.addAll(0, YDocProToModulaBODC.getErrorList().getErrors()); 
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
     String errorPage = YDocProToModulaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YDocProToModulaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YDocProToModulaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
