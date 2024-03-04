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
  BODataCollector YDocVenToModulaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YDocVenToModulaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YDocVenToModulaForm", "YDocVenToModula", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/istech/thip/base/modula/YDocVenToModula.js"); 
  YDocVenToModulaForm.setServletEnvironment(se); 
  YDocVenToModulaForm.setJSTypeList(jsList); 
  YDocVenToModulaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YDocVenToModulaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YDocVenToModulaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YDocVenToModulaForm.getMode(); 
  String key = YDocVenToModulaForm.getKey(); 
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
        YDocVenToModulaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YDocVenToModulaForm.findBODataCollectorName(); 
                YDocVenToModulaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YDocVenToModulaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YDocVenToModulaBODC).setServletEnvironment(se); 
        YDocVenToModulaBODC.initialize("YDocVenToModula", true, 0); 
        YDocVenToModulaForm.setBODataCollector(YDocVenToModulaBODC); 
        int rcBODC = YDocVenToModulaForm.initSecurityServices(); 
        mode = YDocVenToModulaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YDocVenToModulaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YDocVenToModulaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YDocVenToModulaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YDocVenToModulaForm); 
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
  myToolBarTB.setParent(YDocVenToModulaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YDocVenToModulaForm.getBodyOnBeforeUnload()%>" onload="<%=YDocVenToModulaForm.getBodyOnLoad()%>" onunload="<%=YDocVenToModulaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YDocVenToModulaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YDocVenToModulaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YDocVenToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YDocVenToModulaForm.getServlet()%>" method="post" name="YDocVenToModulaForm" style="height:100%"><%
  YDocVenToModulaForm.writeFormStartElements(out); 
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
  WebTextInput YDocVenToModulaIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YDocVenToModula", "IdAzienda"); 
  YDocVenToModulaIdAzienda.setParent(YDocVenToModulaForm); 
%>
<input class="<%=YDocVenToModulaIdAzienda.getClassType()%>" id="<%=YDocVenToModulaIdAzienda.getId()%>" maxlength="<%=YDocVenToModulaIdAzienda.getMaxLength()%>" name="<%=YDocVenToModulaIdAzienda.getName()%>" size="<%=YDocVenToModulaIdAzienda.getSize()%>" type="hidden"><% 
  YDocVenToModulaIdAzienda.write(out); 
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
  mytabbed.setParent(YDocVenToModulaForm); 
 mytabbed.addTab("tab1", "it.istech.thip.base.modula.resources.YDocVenToModula", "tab1", "YDocVenToModula", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocVenToModula", "RAnnoDocVen", null); 
   label.setParent(YDocVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RAnnoDocVen"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocVenToModulaRAnnoDocVen =  
     new com.thera.thermfw.web.WebTextInput("YDocVenToModula", "RAnnoDocVen"); 
  YDocVenToModulaRAnnoDocVen.setParent(YDocVenToModulaForm); 
%>
<input class="<%=YDocVenToModulaRAnnoDocVen.getClassType()%>" id="<%=YDocVenToModulaRAnnoDocVen.getId()%>" maxlength="<%=YDocVenToModulaRAnnoDocVen.getMaxLength()%>" name="<%=YDocVenToModulaRAnnoDocVen.getName()%>" size="<%=YDocVenToModulaRAnnoDocVen.getSize()%>"><% 
  YDocVenToModulaRAnnoDocVen.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocVenToModula", "RNumeroDocVen", null); 
   label.setParent(YDocVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RNumeroDocVen"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocVenToModulaRNumeroDocVen =  
     new com.thera.thermfw.web.WebTextInput("YDocVenToModula", "RNumeroDocVen"); 
  YDocVenToModulaRNumeroDocVen.setParent(YDocVenToModulaForm); 
%>
<input class="<%=YDocVenToModulaRNumeroDocVen.getClassType()%>" id="<%=YDocVenToModulaRNumeroDocVen.getId()%>" maxlength="<%=YDocVenToModulaRNumeroDocVen.getMaxLength()%>" name="<%=YDocVenToModulaRNumeroDocVen.getName()%>" size="<%=YDocVenToModulaRNumeroDocVen.getSize()%>"><% 
  YDocVenToModulaRNumeroDocVen.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocVenToModula", "RRigaDoc", null); 
   label.setParent(YDocVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RRigaDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocVenToModulaRRigaDoc =  
     new com.thera.thermfw.web.WebTextInput("YDocVenToModula", "RRigaDoc"); 
  YDocVenToModulaRRigaDoc.setParent(YDocVenToModulaForm); 
%>
<input class="<%=YDocVenToModulaRRigaDoc.getClassType()%>" id="<%=YDocVenToModulaRRigaDoc.getId()%>" maxlength="<%=YDocVenToModulaRRigaDoc.getMaxLength()%>" name="<%=YDocVenToModulaRRigaDoc.getName()%>" size="<%=YDocVenToModulaRRigaDoc.getSize()%>"><% 
  YDocVenToModulaRRigaDoc.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocVenToModula", "RDetRigaDoc", null); 
   label.setParent(YDocVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RDetRigaDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocVenToModulaRDetRigaDoc =  
     new com.thera.thermfw.web.WebTextInput("YDocVenToModula", "RDetRigaDoc"); 
  YDocVenToModulaRDetRigaDoc.setParent(YDocVenToModulaForm); 
%>
<input class="<%=YDocVenToModulaRDetRigaDoc.getClassType()%>" id="<%=YDocVenToModulaRDetRigaDoc.getId()%>" maxlength="<%=YDocVenToModulaRDetRigaDoc.getMaxLength()%>" name="<%=YDocVenToModulaRDetRigaDoc.getName()%>" size="<%=YDocVenToModulaRDetRigaDoc.getSize()%>"><% 
  YDocVenToModulaRDetRigaDoc.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocVenToModula", "RArticolo", null); 
   label.setParent(YDocVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RArticolo"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocVenToModulaRArticolo =  
     new com.thera.thermfw.web.WebTextInput("YDocVenToModula", "RArticolo"); 
  YDocVenToModulaRArticolo.setParent(YDocVenToModulaForm); 
%>
<input class="<%=YDocVenToModulaRArticolo.getClassType()%>" id="<%=YDocVenToModulaRArticolo.getId()%>" maxlength="<%=YDocVenToModulaRArticolo.getMaxLength()%>" name="<%=YDocVenToModulaRArticolo.getName()%>" size="<%=YDocVenToModulaRArticolo.getSize()%>"><% 
  YDocVenToModulaRArticolo.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocVenToModula", "QtaOriginale", null); 
   label.setParent(YDocVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaOriginale"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocVenToModulaQtaOriginale =  
     new com.thera.thermfw.web.WebTextInput("YDocVenToModula", "QtaOriginale"); 
  YDocVenToModulaQtaOriginale.setParent(YDocVenToModulaForm); 
%>
<input class="<%=YDocVenToModulaQtaOriginale.getClassType()%>" id="<%=YDocVenToModulaQtaOriginale.getId()%>" maxlength="<%=YDocVenToModulaQtaOriginale.getMaxLength()%>" name="<%=YDocVenToModulaQtaOriginale.getName()%>" size="<%=YDocVenToModulaQtaOriginale.getSize()%>"><% 
  YDocVenToModulaQtaOriginale.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocVenToModula", "QtaEvasa", null); 
   label.setParent(YDocVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaEvasa"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocVenToModulaQtaEvasa =  
     new com.thera.thermfw.web.WebTextInput("YDocVenToModula", "QtaEvasa"); 
  YDocVenToModulaQtaEvasa.setParent(YDocVenToModulaForm); 
%>
<input class="<%=YDocVenToModulaQtaEvasa.getClassType()%>" id="<%=YDocVenToModulaQtaEvasa.getId()%>" maxlength="<%=YDocVenToModulaQtaEvasa.getMaxLength()%>" name="<%=YDocVenToModulaQtaEvasa.getName()%>" size="<%=YDocVenToModulaQtaEvasa.getSize()%>"><% 
  YDocVenToModulaQtaEvasa.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocVenToModula", "QtaResidua", null); 
   label.setParent(YDocVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaResidua"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocVenToModulaQtaResidua =  
     new com.thera.thermfw.web.WebTextInput("YDocVenToModula", "QtaResidua"); 
  YDocVenToModulaQtaResidua.setParent(YDocVenToModulaForm); 
%>
<input class="<%=YDocVenToModulaQtaResidua.getClassType()%>" id="<%=YDocVenToModulaQtaResidua.getId()%>" maxlength="<%=YDocVenToModulaQtaResidua.getMaxLength()%>" name="<%=YDocVenToModulaQtaResidua.getName()%>" size="<%=YDocVenToModulaQtaResidua.getSize()%>"><% 
  YDocVenToModulaQtaResidua.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocVenToModula", "Giacenza", null); 
   label.setParent(YDocVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="Giacenza"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocVenToModulaGiacenza =  
     new com.thera.thermfw.web.WebTextInput("YDocVenToModula", "Giacenza"); 
  YDocVenToModulaGiacenza.setParent(YDocVenToModulaForm); 
%>
<input class="<%=YDocVenToModulaGiacenza.getClassType()%>" id="<%=YDocVenToModulaGiacenza.getId()%>" maxlength="<%=YDocVenToModulaGiacenza.getMaxLength()%>" name="<%=YDocVenToModulaGiacenza.getName()%>" size="<%=YDocVenToModulaGiacenza.getSize()%>"><% 
  YDocVenToModulaGiacenza.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocVenToModula", "QtaDaEvadere", null); 
   label.setParent(YDocVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaDaEvadere"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocVenToModulaQtaDaEvadere =  
     new com.thera.thermfw.web.WebTextInput("YDocVenToModula", "QtaDaEvadere"); 
  YDocVenToModulaQtaDaEvadere.setParent(YDocVenToModulaForm); 
%>
<input class="<%=YDocVenToModulaQtaDaEvadere.getClassType()%>" id="<%=YDocVenToModulaQtaDaEvadere.getId()%>" maxlength="<%=YDocVenToModulaQtaDaEvadere.getMaxLength()%>" name="<%=YDocVenToModulaQtaDaEvadere.getName()%>" size="<%=YDocVenToModulaQtaDaEvadere.getSize()%>"><% 
  YDocVenToModulaQtaDaEvadere.write(out); 
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
  errorList.setParent(YDocVenToModulaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YDocVenToModulaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YDocVenToModulaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YDocVenToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YDocVenToModulaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YDocVenToModulaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YDocVenToModulaBODC.getErrorList().getErrors()); 
           if(YDocVenToModulaBODC.getConflict() != null) 
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
     if(YDocVenToModulaBODC != null && !YDocVenToModulaBODC.close(false)) 
        errors.addAll(0, YDocVenToModulaBODC.getErrorList().getErrors()); 
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
     String errorPage = YDocVenToModulaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YDocVenToModulaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YDocVenToModulaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
