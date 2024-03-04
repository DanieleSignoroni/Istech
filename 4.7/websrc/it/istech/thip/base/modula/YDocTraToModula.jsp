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
  BODataCollector YDocTraToModulaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YDocTraToModulaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YDocTraToModulaForm", "YDocTraToModula", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/istech/thip/base/modula/YDocTraToModula.js"); 
  YDocTraToModulaForm.setServletEnvironment(se); 
  YDocTraToModulaForm.setJSTypeList(jsList); 
  YDocTraToModulaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YDocTraToModulaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YDocTraToModulaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YDocTraToModulaForm.getMode(); 
  String key = YDocTraToModulaForm.getKey(); 
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
        YDocTraToModulaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YDocTraToModulaForm.findBODataCollectorName(); 
                YDocTraToModulaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YDocTraToModulaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YDocTraToModulaBODC).setServletEnvironment(se); 
        YDocTraToModulaBODC.initialize("YDocTraToModula", true, 0); 
        YDocTraToModulaForm.setBODataCollector(YDocTraToModulaBODC); 
        int rcBODC = YDocTraToModulaForm.initSecurityServices(); 
        mode = YDocTraToModulaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YDocTraToModulaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YDocTraToModulaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YDocTraToModulaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YDocTraToModulaForm); 
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
  myToolBarTB.setParent(YDocTraToModulaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YDocTraToModulaForm.getBodyOnBeforeUnload()%>" onload="<%=YDocTraToModulaForm.getBodyOnLoad()%>" onunload="<%=YDocTraToModulaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YDocTraToModulaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YDocTraToModulaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YDocTraToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YDocTraToModulaForm.getServlet()%>" method="post" name="YDocTraToModulaForm" style="height:100%"><%
  YDocTraToModulaForm.writeFormStartElements(out); 
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
  WebTextInput YDocTraToModulaIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YDocTraToModula", "IdAzienda"); 
  YDocTraToModulaIdAzienda.setParent(YDocTraToModulaForm); 
%>
<input class="<%=YDocTraToModulaIdAzienda.getClassType()%>" id="<%=YDocTraToModulaIdAzienda.getId()%>" maxlength="<%=YDocTraToModulaIdAzienda.getMaxLength()%>" name="<%=YDocTraToModulaIdAzienda.getName()%>" size="<%=YDocTraToModulaIdAzienda.getSize()%>" type="hidden"><% 
  YDocTraToModulaIdAzienda.write(out); 
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
  mytabbed.setParent(YDocTraToModulaForm); 
 mytabbed.addTab("tab1", "it.istech.thip.base.modula.resources.YDocTraToModula", "tab1", "YDocTraToModula", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocTraToModula", "RAnnoDocTra", null); 
   label.setParent(YDocTraToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RAnnoDocTra"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocTraToModulaRAnnoDocTra =  
     new com.thera.thermfw.web.WebTextInput("YDocTraToModula", "RAnnoDocTra"); 
  YDocTraToModulaRAnnoDocTra.setParent(YDocTraToModulaForm); 
%>
<input class="<%=YDocTraToModulaRAnnoDocTra.getClassType()%>" id="<%=YDocTraToModulaRAnnoDocTra.getId()%>" maxlength="<%=YDocTraToModulaRAnnoDocTra.getMaxLength()%>" name="<%=YDocTraToModulaRAnnoDocTra.getName()%>" size="<%=YDocTraToModulaRAnnoDocTra.getSize()%>"><% 
  YDocTraToModulaRAnnoDocTra.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocTraToModula", "RNumeroDocTra", null); 
   label.setParent(YDocTraToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RNumeroDocTra"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocTraToModulaRNumeroDocTra =  
     new com.thera.thermfw.web.WebTextInput("YDocTraToModula", "RNumeroDocTra"); 
  YDocTraToModulaRNumeroDocTra.setParent(YDocTraToModulaForm); 
%>
<input class="<%=YDocTraToModulaRNumeroDocTra.getClassType()%>" id="<%=YDocTraToModulaRNumeroDocTra.getId()%>" maxlength="<%=YDocTraToModulaRNumeroDocTra.getMaxLength()%>" name="<%=YDocTraToModulaRNumeroDocTra.getName()%>" size="<%=YDocTraToModulaRNumeroDocTra.getSize()%>"><% 
  YDocTraToModulaRNumeroDocTra.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocTraToModula", "RRigaDoc", null); 
   label.setParent(YDocTraToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RRigaDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocTraToModulaRRigaDoc =  
     new com.thera.thermfw.web.WebTextInput("YDocTraToModula", "RRigaDoc"); 
  YDocTraToModulaRRigaDoc.setParent(YDocTraToModulaForm); 
%>
<input class="<%=YDocTraToModulaRRigaDoc.getClassType()%>" id="<%=YDocTraToModulaRRigaDoc.getId()%>" maxlength="<%=YDocTraToModulaRRigaDoc.getMaxLength()%>" name="<%=YDocTraToModulaRRigaDoc.getName()%>" size="<%=YDocTraToModulaRRigaDoc.getSize()%>"><% 
  YDocTraToModulaRRigaDoc.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocTraToModula", "RDetRigaDoc", null); 
   label.setParent(YDocTraToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RDetRigaDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocTraToModulaRDetRigaDoc =  
     new com.thera.thermfw.web.WebTextInput("YDocTraToModula", "RDetRigaDoc"); 
  YDocTraToModulaRDetRigaDoc.setParent(YDocTraToModulaForm); 
%>
<input class="<%=YDocTraToModulaRDetRigaDoc.getClassType()%>" id="<%=YDocTraToModulaRDetRigaDoc.getId()%>" maxlength="<%=YDocTraToModulaRDetRigaDoc.getMaxLength()%>" name="<%=YDocTraToModulaRDetRigaDoc.getName()%>" size="<%=YDocTraToModulaRDetRigaDoc.getSize()%>"><% 
  YDocTraToModulaRDetRigaDoc.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocTraToModula", "RArticolo", null); 
   label.setParent(YDocTraToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RArticolo"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocTraToModulaRArticolo =  
     new com.thera.thermfw.web.WebTextInput("YDocTraToModula", "RArticolo"); 
  YDocTraToModulaRArticolo.setParent(YDocTraToModulaForm); 
%>
<input class="<%=YDocTraToModulaRArticolo.getClassType()%>" id="<%=YDocTraToModulaRArticolo.getId()%>" maxlength="<%=YDocTraToModulaRArticolo.getMaxLength()%>" name="<%=YDocTraToModulaRArticolo.getName()%>" size="<%=YDocTraToModulaRArticolo.getSize()%>"><% 
  YDocTraToModulaRArticolo.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocTraToModula", "QtaOriginale", null); 
   label.setParent(YDocTraToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaOriginale"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocTraToModulaQtaOriginale =  
     new com.thera.thermfw.web.WebTextInput("YDocTraToModula", "QtaOriginale"); 
  YDocTraToModulaQtaOriginale.setParent(YDocTraToModulaForm); 
%>
<input class="<%=YDocTraToModulaQtaOriginale.getClassType()%>" id="<%=YDocTraToModulaQtaOriginale.getId()%>" maxlength="<%=YDocTraToModulaQtaOriginale.getMaxLength()%>" name="<%=YDocTraToModulaQtaOriginale.getName()%>" size="<%=YDocTraToModulaQtaOriginale.getSize()%>"><% 
  YDocTraToModulaQtaOriginale.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocTraToModula", "QtaEvasa", null); 
   label.setParent(YDocTraToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaEvasa"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocTraToModulaGiacenza =  
     new com.thera.thermfw.web.WebTextInput("YDocTraToModula", "Giacenza"); 
  YDocTraToModulaGiacenza.setParent(YDocTraToModulaForm); 
%>
<input class="<%=YDocTraToModulaGiacenza.getClassType()%>" id="<%=YDocTraToModulaGiacenza.getId()%>" maxlength="<%=YDocTraToModulaGiacenza.getMaxLength()%>" name="<%=YDocTraToModulaGiacenza.getName()%>" size="<%=YDocTraToModulaGiacenza.getSize()%>"><% 
  YDocTraToModulaGiacenza.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocTraToModula", "QtaResidua", null); 
   label.setParent(YDocTraToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaResidua"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocTraToModulaQtaResidua =  
     new com.thera.thermfw.web.WebTextInput("YDocTraToModula", "QtaResidua"); 
  YDocTraToModulaQtaResidua.setParent(YDocTraToModulaForm); 
%>
<input class="<%=YDocTraToModulaQtaResidua.getClassType()%>" id="<%=YDocTraToModulaQtaResidua.getId()%>" maxlength="<%=YDocTraToModulaQtaResidua.getMaxLength()%>" name="<%=YDocTraToModulaQtaResidua.getName()%>" size="<%=YDocTraToModulaQtaResidua.getSize()%>"><% 
  YDocTraToModulaQtaResidua.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocTraToModula", "Giacenza", null); 
   label.setParent(YDocTraToModulaForm); 
%><label class="<%=label.getClassType()%>" for="Giacenza"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocTraToModulaQtaEvasa =  
     new com.thera.thermfw.web.WebTextInput("YDocTraToModula", "QtaEvasa"); 
  YDocTraToModulaQtaEvasa.setParent(YDocTraToModulaForm); 
%>
<input class="<%=YDocTraToModulaQtaEvasa.getClassType()%>" id="<%=YDocTraToModulaQtaEvasa.getId()%>" maxlength="<%=YDocTraToModulaQtaEvasa.getMaxLength()%>" name="<%=YDocTraToModulaQtaEvasa.getName()%>" size="<%=YDocTraToModulaQtaEvasa.getSize()%>"><% 
  YDocTraToModulaQtaEvasa.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YDocTraToModula", "QtaDaEvadere", null); 
   label.setParent(YDocTraToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaDaEvadere"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YDocTraToModulaQtaDaEvadere =  
     new com.thera.thermfw.web.WebTextInput("YDocTraToModula", "QtaDaEvadere"); 
  YDocTraToModulaQtaDaEvadere.setParent(YDocTraToModulaForm); 
%>
<input class="<%=YDocTraToModulaQtaDaEvadere.getClassType()%>" id="<%=YDocTraToModulaQtaDaEvadere.getId()%>" maxlength="<%=YDocTraToModulaQtaDaEvadere.getMaxLength()%>" name="<%=YDocTraToModulaQtaDaEvadere.getName()%>" size="<%=YDocTraToModulaQtaDaEvadere.getSize()%>"><% 
  YDocTraToModulaQtaDaEvadere.write(out); 
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
  errorList.setParent(YDocTraToModulaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YDocTraToModulaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YDocTraToModulaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YDocTraToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YDocTraToModulaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YDocTraToModulaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YDocTraToModulaBODC.getErrorList().getErrors()); 
           if(YDocTraToModulaBODC.getConflict() != null) 
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
     if(YDocTraToModulaBODC != null && !YDocTraToModulaBODC.close(false)) 
        errors.addAll(0, YDocTraToModulaBODC.getErrorList().getErrors()); 
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
     String errorPage = YDocTraToModulaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YDocTraToModulaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YDocTraToModulaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
