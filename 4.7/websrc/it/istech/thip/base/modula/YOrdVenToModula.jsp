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
  BODataCollector YOrdVenToModulaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YOrdVenToModulaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YOrdVenToModulaForm", "YOrdVenToModula", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/istech/thip/base/modula/YOrdVenToModula.js"); 
  YOrdVenToModulaForm.setServletEnvironment(se); 
  YOrdVenToModulaForm.setJSTypeList(jsList); 
  YOrdVenToModulaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YOrdVenToModulaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YOrdVenToModulaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YOrdVenToModulaForm.getMode(); 
  String key = YOrdVenToModulaForm.getKey(); 
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
        YOrdVenToModulaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YOrdVenToModulaForm.findBODataCollectorName(); 
                YOrdVenToModulaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YOrdVenToModulaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YOrdVenToModulaBODC).setServletEnvironment(se); 
        YOrdVenToModulaBODC.initialize("YOrdVenToModula", true, 0); 
        YOrdVenToModulaForm.setBODataCollector(YOrdVenToModulaBODC); 
        int rcBODC = YOrdVenToModulaForm.initSecurityServices(); 
        mode = YOrdVenToModulaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YOrdVenToModulaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YOrdVenToModulaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YOrdVenToModulaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YOrdVenToModulaForm); 
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
  myToolBarTB.setParent(YOrdVenToModulaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YOrdVenToModulaForm.getBodyOnBeforeUnload()%>" onload="<%=YOrdVenToModulaForm.getBodyOnLoad()%>" onunload="<%=YOrdVenToModulaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YOrdVenToModulaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YOrdVenToModulaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YOrdVenToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YOrdVenToModulaForm.getServlet()%>" method="post" name="YOrdVenToModulaForm" style="height:100%"><%
  YOrdVenToModulaForm.writeFormStartElements(out); 
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
  mytabbed.setParent(YOrdVenToModulaForm); 
 mytabbed.addTab("tab1", "it.istech.thip.base.modula.resources.YOrdVenToModula", "tab1", "YOrdVenToModula", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdVenToModula", "RAnnoOrdVen", null); 
   label.setParent(YOrdVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RAnnoOrdVen"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdVenToModulaRAnnoOrdVen =  
     new com.thera.thermfw.web.WebTextInput("YOrdVenToModula", "RAnnoOrdVen"); 
  YOrdVenToModulaRAnnoOrdVen.setParent(YOrdVenToModulaForm); 
%>
<input class="<%=YOrdVenToModulaRAnnoOrdVen.getClassType()%>" id="<%=YOrdVenToModulaRAnnoOrdVen.getId()%>" maxlength="<%=YOrdVenToModulaRAnnoOrdVen.getMaxLength()%>" name="<%=YOrdVenToModulaRAnnoOrdVen.getName()%>" size="<%=YOrdVenToModulaRAnnoOrdVen.getSize()%>"><% 
  YOrdVenToModulaRAnnoOrdVen.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdVenToModula", "RNumeroOrdVen", null); 
   label.setParent(YOrdVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RNumeroOrdVen"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdVenToModulaRNumeroOrdVen =  
     new com.thera.thermfw.web.WebTextInput("YOrdVenToModula", "RNumeroOrdVen"); 
  YOrdVenToModulaRNumeroOrdVen.setParent(YOrdVenToModulaForm); 
%>
<input class="<%=YOrdVenToModulaRNumeroOrdVen.getClassType()%>" id="<%=YOrdVenToModulaRNumeroOrdVen.getId()%>" maxlength="<%=YOrdVenToModulaRNumeroOrdVen.getMaxLength()%>" name="<%=YOrdVenToModulaRNumeroOrdVen.getName()%>" size="<%=YOrdVenToModulaRNumeroOrdVen.getSize()%>"><% 
  YOrdVenToModulaRNumeroOrdVen.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdVenToModula", "RRigaOrd", null); 
   label.setParent(YOrdVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RRigaOrd"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdVenToModulaRRigaOrd =  
     new com.thera.thermfw.web.WebTextInput("YOrdVenToModula", "RRigaOrd"); 
  YOrdVenToModulaRRigaOrd.setParent(YOrdVenToModulaForm); 
%>
<input class="<%=YOrdVenToModulaRRigaOrd.getClassType()%>" id="<%=YOrdVenToModulaRRigaOrd.getId()%>" maxlength="<%=YOrdVenToModulaRRigaOrd.getMaxLength()%>" name="<%=YOrdVenToModulaRRigaOrd.getName()%>" size="<%=YOrdVenToModulaRRigaOrd.getSize()%>"><% 
  YOrdVenToModulaRRigaOrd.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdVenToModula", "RDetRigaOrd", null); 
   label.setParent(YOrdVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RDetRigaOrd"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdVenToModulaRDetRigaOrd =  
     new com.thera.thermfw.web.WebTextInput("YOrdVenToModula", "RDetRigaOrd"); 
  YOrdVenToModulaRDetRigaOrd.setParent(YOrdVenToModulaForm); 
%>
<input class="<%=YOrdVenToModulaRDetRigaOrd.getClassType()%>" id="<%=YOrdVenToModulaRDetRigaOrd.getId()%>" maxlength="<%=YOrdVenToModulaRDetRigaOrd.getMaxLength()%>" name="<%=YOrdVenToModulaRDetRigaOrd.getName()%>" size="<%=YOrdVenToModulaRDetRigaOrd.getSize()%>"><% 
  YOrdVenToModulaRDetRigaOrd.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdVenToModula", "RArticolo", null); 
   label.setParent(YOrdVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RelArticolo"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YOrdVenToModulaRelArticolo =  
     new com.thera.thermfw.web.WebMultiSearchForm("YOrdVenToModula", "RelArticolo", false, false, true, 1, null, null); 
  YOrdVenToModulaRelArticolo.setParent(YOrdVenToModulaForm); 
  YOrdVenToModulaRelArticolo.write(out); 
%>
<!--<span class="multisearchform" id="RelArticolo"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdVenToModula", "QtaDaEvadere", null); 
   label.setParent(YOrdVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaDaEvadere"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdVenToModulaQtaDaEvadere =  
     new com.thera.thermfw.web.WebTextInput("YOrdVenToModula", "QtaDaEvadere"); 
  YOrdVenToModulaQtaDaEvadere.setParent(YOrdVenToModulaForm); 
%>
<input class="<%=YOrdVenToModulaQtaDaEvadere.getClassType()%>" id="<%=YOrdVenToModulaQtaDaEvadere.getId()%>" maxlength="<%=YOrdVenToModulaQtaDaEvadere.getMaxLength()%>" name="<%=YOrdVenToModulaQtaDaEvadere.getName()%>" size="<%=YOrdVenToModulaQtaDaEvadere.getSize()%>"><% 
  YOrdVenToModulaQtaDaEvadere.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdVenToModula", "QtaEvasa", null); 
   label.setParent(YOrdVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaEvasa"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdVenToModulaQtaEvasa =  
     new com.thera.thermfw.web.WebTextInput("YOrdVenToModula", "QtaEvasa"); 
  YOrdVenToModulaQtaEvasa.setParent(YOrdVenToModulaForm); 
%>
<input class="<%=YOrdVenToModulaQtaEvasa.getClassType()%>" id="<%=YOrdVenToModulaQtaEvasa.getId()%>" maxlength="<%=YOrdVenToModulaQtaEvasa.getMaxLength()%>" name="<%=YOrdVenToModulaQtaEvasa.getName()%>" size="<%=YOrdVenToModulaQtaEvasa.getSize()%>"><% 
  YOrdVenToModulaQtaEvasa.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdVenToModula", "QtaOriginale", null); 
   label.setParent(YOrdVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaOriginale"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdVenToModulaQtaOriginale =  
     new com.thera.thermfw.web.WebTextInput("YOrdVenToModula", "QtaOriginale"); 
  YOrdVenToModulaQtaOriginale.setParent(YOrdVenToModulaForm); 
%>
<input class="<%=YOrdVenToModulaQtaOriginale.getClassType()%>" id="<%=YOrdVenToModulaQtaOriginale.getId()%>" maxlength="<%=YOrdVenToModulaQtaOriginale.getMaxLength()%>" name="<%=YOrdVenToModulaQtaOriginale.getName()%>" size="<%=YOrdVenToModulaQtaOriginale.getSize()%>"><% 
  YOrdVenToModulaQtaOriginale.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdVenToModula", "QtaResidua", null); 
   label.setParent(YOrdVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaResidua"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdVenToModulaQtaResidua =  
     new com.thera.thermfw.web.WebTextInput("YOrdVenToModula", "QtaResidua"); 
  YOrdVenToModulaQtaResidua.setParent(YOrdVenToModulaForm); 
%>
<input class="<%=YOrdVenToModulaQtaResidua.getClassType()%>" id="<%=YOrdVenToModulaQtaResidua.getId()%>" maxlength="<%=YOrdVenToModulaQtaResidua.getMaxLength()%>" name="<%=YOrdVenToModulaQtaResidua.getName()%>" size="<%=YOrdVenToModulaQtaResidua.getSize()%>"><% 
  YOrdVenToModulaQtaResidua.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YOrdVenToModula", "Giacenza", null); 
   label.setParent(YOrdVenToModulaForm); 
%><label class="<%=label.getClassType()%>" for="Giacenza"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YOrdVenToModulaGiacenza =  
     new com.thera.thermfw.web.WebTextInput("YOrdVenToModula", "Giacenza"); 
  YOrdVenToModulaGiacenza.setParent(YOrdVenToModulaForm); 
%>
<input class="<%=YOrdVenToModulaGiacenza.getClassType()%>" id="<%=YOrdVenToModulaGiacenza.getId()%>" maxlength="<%=YOrdVenToModulaGiacenza.getMaxLength()%>" name="<%=YOrdVenToModulaGiacenza.getName()%>" size="<%=YOrdVenToModulaGiacenza.getSize()%>"><% 
  YOrdVenToModulaGiacenza.write(out); 
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
  errorList.setParent(YOrdVenToModulaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YOrdVenToModulaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YOrdVenToModulaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YOrdVenToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YOrdVenToModulaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YOrdVenToModulaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YOrdVenToModulaBODC.getErrorList().getErrors()); 
           if(YOrdVenToModulaBODC.getConflict() != null) 
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
     if(YOrdVenToModulaBODC != null && !YOrdVenToModulaBODC.close(false)) 
        errors.addAll(0, YOrdVenToModulaBODC.getErrorList().getErrors()); 
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
     String errorPage = YOrdVenToModulaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YOrdVenToModulaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YOrdVenToModulaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
