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
  BODataCollector YOrdAcqToModulaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YOrdAcqToModulaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YOrdAcqToModulaForm", "YOrdAcqToModula", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/istech/thip/base/modula/YOrdAcqToModula.js"); 
  YOrdAcqToModulaForm.setServletEnvironment(se); 
  YOrdAcqToModulaForm.setJSTypeList(jsList); 
  YOrdAcqToModulaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YOrdAcqToModulaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YOrdAcqToModulaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YOrdAcqToModulaForm.getMode(); 
  String key = YOrdAcqToModulaForm.getKey(); 
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
        YOrdAcqToModulaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YOrdAcqToModulaForm.findBODataCollectorName(); 
                YOrdAcqToModulaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YOrdAcqToModulaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YOrdAcqToModulaBODC).setServletEnvironment(se); 
        YOrdAcqToModulaBODC.initialize("YOrdAcqToModula", true, 0); 
        YOrdAcqToModulaForm.setBODataCollector(YOrdAcqToModulaBODC); 
        int rcBODC = YOrdAcqToModulaForm.initSecurityServices(); 
        mode = YOrdAcqToModulaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YOrdAcqToModulaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YOrdAcqToModulaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YOrdAcqToModulaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YOrdAcqToModulaForm); 
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
  myToolBarTB.setParent(YOrdAcqToModulaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YOrdAcqToModulaForm.getBodyOnBeforeUnload()%>" onload="<%=YOrdAcqToModulaForm.getBodyOnLoad()%>" onunload="<%=YOrdAcqToModulaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YOrdAcqToModulaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YOrdAcqToModulaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YOrdAcqToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YOrdAcqToModulaForm.getServlet()%>" method="post" name="YOrdAcqToModulaForm" style="height:100%"><%
  YOrdAcqToModulaForm.writeFormStartElements(out); 
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
  mytabbed.setParent(YOrdAcqToModulaForm); 
 mytabbed.addTab("tab1", "it.istech.thip.base.modula.resources.YOrdAcqToModula", "tab1", "YOrdAcqToModula", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdAcqToModula", "RAnnoOrdAcq", null); 
   label.setParent(YOrdAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RAnnoOrdAcq"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdAcqToModulaRAnnoOrdAcq =  
     new com.thera.thermfw.web.WebTextInput("YOrdAcqToModula", "RAnnoOrdAcq"); 
  YOrdAcqToModulaRAnnoOrdAcq.setParent(YOrdAcqToModulaForm); 
%>
<input class="<%=YOrdAcqToModulaRAnnoOrdAcq.getClassType()%>" id="<%=YOrdAcqToModulaRAnnoOrdAcq.getId()%>" maxlength="<%=YOrdAcqToModulaRAnnoOrdAcq.getMaxLength()%>" name="<%=YOrdAcqToModulaRAnnoOrdAcq.getName()%>" size="<%=YOrdAcqToModulaRAnnoOrdAcq.getSize()%>"><% 
  YOrdAcqToModulaRAnnoOrdAcq.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdAcqToModula", "RNumeroOrdAcq", null); 
   label.setParent(YOrdAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RNumeroOrdAcq"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdAcqToModulaRNumeroOrdAcq =  
     new com.thera.thermfw.web.WebTextInput("YOrdAcqToModula", "RNumeroOrdAcq"); 
  YOrdAcqToModulaRNumeroOrdAcq.setParent(YOrdAcqToModulaForm); 
%>
<input class="<%=YOrdAcqToModulaRNumeroOrdAcq.getClassType()%>" id="<%=YOrdAcqToModulaRNumeroOrdAcq.getId()%>" maxlength="<%=YOrdAcqToModulaRNumeroOrdAcq.getMaxLength()%>" name="<%=YOrdAcqToModulaRNumeroOrdAcq.getName()%>" size="<%=YOrdAcqToModulaRNumeroOrdAcq.getSize()%>"><% 
  YOrdAcqToModulaRNumeroOrdAcq.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdAcqToModula", "RRigaOrd", null); 
   label.setParent(YOrdAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RRigaOrd"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdAcqToModulaRRigaOrd =  
     new com.thera.thermfw.web.WebTextInput("YOrdAcqToModula", "RRigaOrd"); 
  YOrdAcqToModulaRRigaOrd.setParent(YOrdAcqToModulaForm); 
%>
<input class="<%=YOrdAcqToModulaRRigaOrd.getClassType()%>" id="<%=YOrdAcqToModulaRRigaOrd.getId()%>" maxlength="<%=YOrdAcqToModulaRRigaOrd.getMaxLength()%>" name="<%=YOrdAcqToModulaRRigaOrd.getName()%>" size="<%=YOrdAcqToModulaRRigaOrd.getSize()%>"><% 
  YOrdAcqToModulaRRigaOrd.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdAcqToModula", "RDetRigaOrd", null); 
   label.setParent(YOrdAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RDetRigaOrd"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdAcqToModulaRDetRigaOrd =  
     new com.thera.thermfw.web.WebTextInput("YOrdAcqToModula", "RDetRigaOrd"); 
  YOrdAcqToModulaRDetRigaOrd.setParent(YOrdAcqToModulaForm); 
%>
<input class="<%=YOrdAcqToModulaRDetRigaOrd.getClassType()%>" id="<%=YOrdAcqToModulaRDetRigaOrd.getId()%>" maxlength="<%=YOrdAcqToModulaRDetRigaOrd.getMaxLength()%>" name="<%=YOrdAcqToModulaRDetRigaOrd.getName()%>" size="<%=YOrdAcqToModulaRDetRigaOrd.getSize()%>"><% 
  YOrdAcqToModulaRDetRigaOrd.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <label for="RArticolo"></label>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YOrdAcqToModulaRelArticolo =  
     new com.thera.thermfw.web.WebMultiSearchForm("YOrdAcqToModula", "RelArticolo", false, false, true, 1, null, null); 
  YOrdAcqToModulaRelArticolo.setParent(YOrdAcqToModulaForm); 
  YOrdAcqToModulaRelArticolo.write(out); 
%>
<!--<span class="multisearchform" id="RelArticolo"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdAcqToModula", "QtaDaEvadere", null); 
   label.setParent(YOrdAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaDaEvadere"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdAcqToModulaGiacenza =  
     new com.thera.thermfw.web.WebTextInput("YOrdAcqToModula", "Giacenza"); 
  YOrdAcqToModulaGiacenza.setParent(YOrdAcqToModulaForm); 
%>
<input class="<%=YOrdAcqToModulaGiacenza.getClassType()%>" id="<%=YOrdAcqToModulaGiacenza.getId()%>" maxlength="<%=YOrdAcqToModulaGiacenza.getMaxLength()%>" name="<%=YOrdAcqToModulaGiacenza.getName()%>" size="<%=YOrdAcqToModulaGiacenza.getSize()%>"><% 
  YOrdAcqToModulaGiacenza.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdAcqToModula", "QtaEvasa", null); 
   label.setParent(YOrdAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaEvasa"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdAcqToModulaQtaResidua =  
     new com.thera.thermfw.web.WebTextInput("YOrdAcqToModula", "QtaResidua"); 
  YOrdAcqToModulaQtaResidua.setParent(YOrdAcqToModulaForm); 
%>
<input class="<%=YOrdAcqToModulaQtaResidua.getClassType()%>" id="<%=YOrdAcqToModulaQtaResidua.getId()%>" maxlength="<%=YOrdAcqToModulaQtaResidua.getMaxLength()%>" name="<%=YOrdAcqToModulaQtaResidua.getName()%>" size="<%=YOrdAcqToModulaQtaResidua.getSize()%>"><% 
  YOrdAcqToModulaQtaResidua.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdAcqToModula", "QtaOriginale", null); 
   label.setParent(YOrdAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaOriginale"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdAcqToModulaQtaEvasa =  
     new com.thera.thermfw.web.WebTextInput("YOrdAcqToModula", "QtaEvasa"); 
  YOrdAcqToModulaQtaEvasa.setParent(YOrdAcqToModulaForm); 
%>
<input class="<%=YOrdAcqToModulaQtaEvasa.getClassType()%>" id="<%=YOrdAcqToModulaQtaEvasa.getId()%>" maxlength="<%=YOrdAcqToModulaQtaEvasa.getMaxLength()%>" name="<%=YOrdAcqToModulaQtaEvasa.getName()%>" size="<%=YOrdAcqToModulaQtaEvasa.getSize()%>"><% 
  YOrdAcqToModulaQtaEvasa.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdAcqToModula", "QtaResidua", null); 
   label.setParent(YOrdAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaResidua"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdAcqToModulaQtaDaEvadere =  
     new com.thera.thermfw.web.WebTextInput("YOrdAcqToModula", "QtaDaEvadere"); 
  YOrdAcqToModulaQtaDaEvadere.setParent(YOrdAcqToModulaForm); 
%>
<input class="<%=YOrdAcqToModulaQtaDaEvadere.getClassType()%>" id="<%=YOrdAcqToModulaQtaDaEvadere.getId()%>" maxlength="<%=YOrdAcqToModulaQtaDaEvadere.getMaxLength()%>" name="<%=YOrdAcqToModulaQtaDaEvadere.getName()%>" size="<%=YOrdAcqToModulaQtaDaEvadere.getSize()%>"><% 
  YOrdAcqToModulaQtaDaEvadere.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdAcqToModula", "Giacenza", null); 
   label.setParent(YOrdAcqToModulaForm); 
%><label class="<%=label.getClassType()%>" for="Giacenza"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdAcqToModulaQtaOriginale =  
     new com.thera.thermfw.web.WebTextInput("YOrdAcqToModula", "QtaOriginale"); 
  YOrdAcqToModulaQtaOriginale.setParent(YOrdAcqToModulaForm); 
%>
<input class="<%=YOrdAcqToModulaQtaOriginale.getClassType()%>" id="<%=YOrdAcqToModulaQtaOriginale.getId()%>" maxlength="<%=YOrdAcqToModulaQtaOriginale.getMaxLength()%>" name="<%=YOrdAcqToModulaQtaOriginale.getName()%>" size="<%=YOrdAcqToModulaQtaOriginale.getSize()%>"><% 
  YOrdAcqToModulaQtaOriginale.write(out); 
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
  errorList.setParent(YOrdAcqToModulaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YOrdAcqToModulaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YOrdAcqToModulaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YOrdAcqToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YOrdAcqToModulaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YOrdAcqToModulaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YOrdAcqToModulaBODC.getErrorList().getErrors()); 
           if(YOrdAcqToModulaBODC.getConflict() != null) 
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
     if(YOrdAcqToModulaBODC != null && !YOrdAcqToModulaBODC.close(false)) 
        errors.addAll(0, YOrdAcqToModulaBODC.getErrorList().getErrors()); 
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
     String errorPage = YOrdAcqToModulaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YOrdAcqToModulaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YOrdAcqToModulaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
