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
  BODataCollector YMatProToModulaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YMatProToModulaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YMatProToModulaForm", "YMatProToModula", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/istech/thip/base/modula/YMatProToModula.js"); 
  YMatProToModulaForm.setServletEnvironment(se); 
  YMatProToModulaForm.setJSTypeList(jsList); 
  YMatProToModulaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YMatProToModulaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YMatProToModulaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YMatProToModulaForm.getMode(); 
  String key = YMatProToModulaForm.getKey(); 
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
        YMatProToModulaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YMatProToModulaForm.findBODataCollectorName(); 
                YMatProToModulaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YMatProToModulaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YMatProToModulaBODC).setServletEnvironment(se); 
        YMatProToModulaBODC.initialize("YMatProToModula", true, 0); 
        YMatProToModulaForm.setBODataCollector(YMatProToModulaBODC); 
        int rcBODC = YMatProToModulaForm.initSecurityServices(); 
        mode = YMatProToModulaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YMatProToModulaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YMatProToModulaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YMatProToModulaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YMatProToModulaForm); 
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
  myToolBarTB.setParent(YMatProToModulaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/it/thera/thip/cs/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YMatProToModulaForm.getBodyOnBeforeUnload()%>" onload="<%=YMatProToModulaForm.getBodyOnLoad()%>" onunload="<%=YMatProToModulaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YMatProToModulaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YMatProToModulaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YMatProToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YMatProToModulaForm.getServlet()%>" method="post" name="YMatProToModulaForm" style="height:100%"><%
  YMatProToModulaForm.writeFormStartElements(out); 
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
  WebTextInput YMatProToModulaIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YMatProToModula", "IdAzienda"); 
  YMatProToModulaIdAzienda.setParent(YMatProToModulaForm); 
%>
<input class="<%=YMatProToModulaIdAzienda.getClassType()%>" id="<%=YMatProToModulaIdAzienda.getId()%>" maxlength="<%=YMatProToModulaIdAzienda.getMaxLength()%>" name="<%=YMatProToModulaIdAzienda.getName()%>" size="<%=YMatProToModulaIdAzienda.getSize()%>" type="hidden"><% 
  YMatProToModulaIdAzienda.write(out); 
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
  mytabbed.setParent(YMatProToModulaForm); 
 mytabbed.addTab("tab1", "it.istech.thip.base.modula.resources.YMatProToModula", "tab1", "YMatProToModula", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YMatProToModula", "RAnnoOrd", null); 
   label.setParent(YMatProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RAnnoOrd"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YMatProToModulaRAnnoOrd =  
     new com.thera.thermfw.web.WebTextInput("YMatProToModula", "RAnnoOrd"); 
  YMatProToModulaRAnnoOrd.setParent(YMatProToModulaForm); 
%>
<input class="<%=YMatProToModulaRAnnoOrd.getClassType()%>" id="<%=YMatProToModulaRAnnoOrd.getId()%>" maxlength="<%=YMatProToModulaRAnnoOrd.getMaxLength()%>" name="<%=YMatProToModulaRAnnoOrd.getName()%>" size="<%=YMatProToModulaRAnnoOrd.getSize()%>"><% 
  YMatProToModulaRAnnoOrd.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YMatProToModula", "RNumeroOrd", null); 
   label.setParent(YMatProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RNumeroOrd"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YMatProToModulaRNumeroOrd =  
     new com.thera.thermfw.web.WebTextInput("YMatProToModula", "RNumeroOrd"); 
  YMatProToModulaRNumeroOrd.setParent(YMatProToModulaForm); 
%>
<input class="<%=YMatProToModulaRNumeroOrd.getClassType()%>" id="<%=YMatProToModulaRNumeroOrd.getId()%>" maxlength="<%=YMatProToModulaRNumeroOrd.getMaxLength()%>" name="<%=YMatProToModulaRNumeroOrd.getName()%>" size="<%=YMatProToModulaRNumeroOrd.getSize()%>"><% 
  YMatProToModulaRNumeroOrd.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YMatProToModula", "RRigaAttivita", null); 
   label.setParent(YMatProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RRigaAttivita"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YMatProToModulaRRigaAttivita =  
     new com.thera.thermfw.web.WebTextInput("YMatProToModula", "RRigaAttivita"); 
  YMatProToModulaRRigaAttivita.setParent(YMatProToModulaForm); 
%>
<input class="<%=YMatProToModulaRRigaAttivita.getClassType()%>" id="<%=YMatProToModulaRRigaAttivita.getId()%>" maxlength="<%=YMatProToModulaRRigaAttivita.getMaxLength()%>" name="<%=YMatProToModulaRRigaAttivita.getName()%>" size="<%=YMatProToModulaRRigaAttivita.getSize()%>"><% 
  YMatProToModulaRRigaAttivita.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YMatProToModula", "RRigaMateriale", null); 
   label.setParent(YMatProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RRigaMateriale"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YMatProToModulaRRigaMateriale =  
     new com.thera.thermfw.web.WebTextInput("YMatProToModula", "RRigaMateriale"); 
  YMatProToModulaRRigaMateriale.setParent(YMatProToModulaForm); 
%>
<input class="<%=YMatProToModulaRRigaMateriale.getClassType()%>" id="<%=YMatProToModulaRRigaMateriale.getId()%>" maxlength="<%=YMatProToModulaRRigaMateriale.getMaxLength()%>" name="<%=YMatProToModulaRRigaMateriale.getName()%>" size="<%=YMatProToModulaRRigaMateriale.getSize()%>"><% 
  YMatProToModulaRRigaMateriale.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YMatProToModula", "RArticolo", null); 
   label.setParent(YMatProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="RArticolo"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YMatProToModulaRArticolo =  
     new com.thera.thermfw.web.WebTextInput("YMatProToModula", "RArticolo"); 
  YMatProToModulaRArticolo.setParent(YMatProToModulaForm); 
%>
<input class="<%=YMatProToModulaRArticolo.getClassType()%>" id="<%=YMatProToModulaRArticolo.getId()%>" maxlength="<%=YMatProToModulaRArticolo.getMaxLength()%>" name="<%=YMatProToModulaRArticolo.getName()%>" size="<%=YMatProToModulaRArticolo.getSize()%>"><% 
  YMatProToModulaRArticolo.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YMatProToModula", "QtaOriginale", null); 
   label.setParent(YMatProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaOriginale"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YMatProToModulaQtaOriginale =  
     new com.thera.thermfw.web.WebTextInput("YMatProToModula", "QtaOriginale"); 
  YMatProToModulaQtaOriginale.setParent(YMatProToModulaForm); 
%>
<input class="<%=YMatProToModulaQtaOriginale.getClassType()%>" id="<%=YMatProToModulaQtaOriginale.getId()%>" maxlength="<%=YMatProToModulaQtaOriginale.getMaxLength()%>" name="<%=YMatProToModulaQtaOriginale.getName()%>" size="<%=YMatProToModulaQtaOriginale.getSize()%>"><% 
  YMatProToModulaQtaOriginale.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YMatProToModula", "QtaEvasa", null); 
   label.setParent(YMatProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaEvasa"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YMatProToModulaQtaEvasa =  
     new com.thera.thermfw.web.WebTextInput("YMatProToModula", "QtaEvasa"); 
  YMatProToModulaQtaEvasa.setParent(YMatProToModulaForm); 
%>
<input class="<%=YMatProToModulaQtaEvasa.getClassType()%>" id="<%=YMatProToModulaQtaEvasa.getId()%>" maxlength="<%=YMatProToModulaQtaEvasa.getMaxLength()%>" name="<%=YMatProToModulaQtaEvasa.getName()%>" size="<%=YMatProToModulaQtaEvasa.getSize()%>"><% 
  YMatProToModulaQtaEvasa.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YMatProToModula", "QtaResidua", null); 
   label.setParent(YMatProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaResidua"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YMatProToModulaQtaResidua =  
     new com.thera.thermfw.web.WebTextInput("YMatProToModula", "QtaResidua"); 
  YMatProToModulaQtaResidua.setParent(YMatProToModulaForm); 
%>
<input class="<%=YMatProToModulaQtaResidua.getClassType()%>" id="<%=YMatProToModulaQtaResidua.getId()%>" maxlength="<%=YMatProToModulaQtaResidua.getMaxLength()%>" name="<%=YMatProToModulaQtaResidua.getName()%>" size="<%=YMatProToModulaQtaResidua.getSize()%>"><% 
  YMatProToModulaQtaResidua.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YMatProToModula", "Giacenza", null); 
   label.setParent(YMatProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="Giacenza"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YMatProToModulaGiacenza =  
     new com.thera.thermfw.web.WebTextInput("YMatProToModula", "Giacenza"); 
  YMatProToModulaGiacenza.setParent(YMatProToModulaForm); 
%>
<input class="<%=YMatProToModulaGiacenza.getClassType()%>" id="<%=YMatProToModulaGiacenza.getId()%>" maxlength="<%=YMatProToModulaGiacenza.getMaxLength()%>" name="<%=YMatProToModulaGiacenza.getName()%>" size="<%=YMatProToModulaGiacenza.getSize()%>"><% 
  YMatProToModulaGiacenza.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YMatProToModula", "QtaDaEvadere", null); 
   label.setParent(YMatProToModulaForm); 
%><label class="<%=label.getClassType()%>" for="QtaDaEvadere"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YMatProToModulaQtaDaEvadere =  
     new com.thera.thermfw.web.WebTextInput("YMatProToModula", "QtaDaEvadere"); 
  YMatProToModulaQtaDaEvadere.setParent(YMatProToModulaForm); 
%>
<input class="<%=YMatProToModulaQtaDaEvadere.getClassType()%>" id="<%=YMatProToModulaQtaDaEvadere.getId()%>" maxlength="<%=YMatProToModulaQtaDaEvadere.getMaxLength()%>" name="<%=YMatProToModulaQtaDaEvadere.getName()%>" size="<%=YMatProToModulaQtaDaEvadere.getSize()%>"><% 
  YMatProToModulaQtaDaEvadere.write(out); 
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
  errorList.setParent(YMatProToModulaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YMatProToModulaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YMatProToModulaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YMatProToModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YMatProToModulaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YMatProToModulaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YMatProToModulaBODC.getErrorList().getErrors()); 
           if(YMatProToModulaBODC.getConflict() != null) 
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
     if(YMatProToModulaBODC != null && !YMatProToModulaBODC.close(false)) 
        errors.addAll(0, YMatProToModulaBODC.getErrorList().getErrors()); 
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
     String errorPage = YMatProToModulaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YMatProToModulaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YMatProToModulaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
