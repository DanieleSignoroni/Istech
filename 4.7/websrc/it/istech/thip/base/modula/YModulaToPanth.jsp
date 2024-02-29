<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///K:/Thip/4.7.0/websrcsvil/dtd/xhtml1-transitional.dtd">
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
  BODataCollector YModulaToPanthBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YModulaToPanthForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YModulaToPanthForm", "YModulaToPanth", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/istech/thip/base/modula/YModulaToPanth.js"); 
  YModulaToPanthForm.setServletEnvironment(se); 
  YModulaToPanthForm.setJSTypeList(jsList); 
  YModulaToPanthForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YModulaToPanthForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YModulaToPanthForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YModulaToPanthForm.getMode(); 
  String key = YModulaToPanthForm.getKey(); 
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
        YModulaToPanthForm.outTraceInfo(getClass().getName()); 
        String collectorName = YModulaToPanthForm.findBODataCollectorName(); 
                YModulaToPanthBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YModulaToPanthBODC instanceof WebDataCollector) 
            ((WebDataCollector)YModulaToPanthBODC).setServletEnvironment(se); 
        YModulaToPanthBODC.initialize("YModulaToPanth", true, 0); 
        YModulaToPanthForm.setBODataCollector(YModulaToPanthBODC); 
        int rcBODC = YModulaToPanthForm.initSecurityServices(); 
        mode = YModulaToPanthForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YModulaToPanthForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YModulaToPanthBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YModulaToPanthForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
</head>
  <body onbeforeunload="<%=YModulaToPanthForm.getBodyOnBeforeUnload()%>" onload="<%=YModulaToPanthForm.getBodyOnLoad()%>" onunload="<%=YModulaToPanthForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YModulaToPanthForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YModulaToPanthForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YModulaToPanthBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YModulaToPanthForm.getServlet()%>" method="post" name="YModulaToPanthForm" style="height:100%"><%
  YModulaToPanthForm.writeFormStartElements(out); 
%>

      <table cellpadding="0" cellspacing="0" height="100%" id="emptyborder" width="100%">
       
        <tr>
          <td>
            <% 
  WebTextInput YModulaToPanthIdAzienda =  
     new com.thera.thermfw.web.WebTextInput("YModulaToPanth", "IdAzienda"); 
  YModulaToPanthIdAzienda.setParent(YModulaToPanthForm); 
%>
<input class="<%=YModulaToPanthIdAzienda.getClassType()%>" id="<%=YModulaToPanthIdAzienda.getId()%>" maxlength="<%=YModulaToPanthIdAzienda.getMaxLength()%>" name="<%=YModulaToPanthIdAzienda.getName()%>" size="<%=YModulaToPanthIdAzienda.getSize()%>" type="hidden"><% 
  YModulaToPanthIdAzienda.write(out); 
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
  mytabbed.setParent(YModulaToPanthForm); 
 mytabbed.addTab("tab1", "it.istech.thip.base.modula.resources.YModulaToPanth", "tab1", "YModulaToPanth", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YModulaToPanth", "Id", null); 
   label.setParent(YModulaToPanthForm); 
%><label class="<%=label.getClassType()%>" for="Id"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YModulaToPanthId =  
     new com.thera.thermfw.web.WebTextInput("YModulaToPanth", "Id"); 
  YModulaToPanthId.setParent(YModulaToPanthForm); 
%>
<input class="<%=YModulaToPanthId.getClassType()%>" id="<%=YModulaToPanthId.getId()%>" maxlength="<%=YModulaToPanthId.getMaxLength()%>" name="<%=YModulaToPanthId.getName()%>" size="<%=YModulaToPanthId.getSize()%>"><% 
  YModulaToPanthId.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YModulaToPanth", "Ordine", null); 
   label.setParent(YModulaToPanthForm); 
%><label class="<%=label.getClassType()%>" for="Ordine"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YModulaToPanthOrdine =  
     new com.thera.thermfw.web.WebTextInput("YModulaToPanth", "Ordine"); 
  YModulaToPanthOrdine.setParent(YModulaToPanthForm); 
%>
<input class="<%=YModulaToPanthOrdine.getClassType()%>" id="<%=YModulaToPanthOrdine.getId()%>" maxlength="<%=YModulaToPanthOrdine.getMaxLength()%>" name="<%=YModulaToPanthOrdine.getName()%>" size="<%=YModulaToPanthOrdine.getSize()%>"><% 
  YModulaToPanthOrdine.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YModulaToPanth", "Articolo", null); 
   label.setParent(YModulaToPanthForm); 
%><label class="<%=label.getClassType()%>" for="Articolo"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YModulaToPanthArticolo =  
     new com.thera.thermfw.web.WebTextInput("YModulaToPanth", "Articolo"); 
  YModulaToPanthArticolo.setParent(YModulaToPanthForm); 
%>
<input class="<%=YModulaToPanthArticolo.getClassType()%>" id="<%=YModulaToPanthArticolo.getId()%>" maxlength="<%=YModulaToPanthArticolo.getMaxLength()%>" name="<%=YModulaToPanthArticolo.getName()%>" size="<%=YModulaToPanthArticolo.getSize()%>"><% 
  YModulaToPanthArticolo.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YModulaToPanth", "TipoMov", null); 
   label.setParent(YModulaToPanthForm); 
%><label class="<%=label.getClassType()%>" for="TipoMov"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebComboBox YModulaToPanthTipoMov =  
     new com.thera.thermfw.web.WebComboBox("YModulaToPanth", "TipoMov", null); 
  YModulaToPanthTipoMov.setParent(YModulaToPanthForm); 
%>
<select id="<%=YModulaToPanthTipoMov.getId()%>" name="<%=YModulaToPanthTipoMov.getName()%>"><% 
  YModulaToPanthTipoMov.write(out); 
%> 
</select>
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YModulaToPanth", "TipoDoc", null); 
   label.setParent(YModulaToPanthForm); 
%><label class="<%=label.getClassType()%>" for="TipoDoc"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebComboBox YModulaToPanthTipoDoc =  
     new com.thera.thermfw.web.WebComboBox("YModulaToPanth", "TipoDoc", null); 
  YModulaToPanthTipoDoc.setParent(YModulaToPanthForm); 
%>
<select id="<%=YModulaToPanthTipoDoc.getId()%>" name="<%=YModulaToPanthTipoDoc.getName()%>"><% 
  YModulaToPanthTipoDoc.write(out); 
%> 
</select>
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YModulaToPanth", "QtaEvasaUmPrm", null); 
   label.setParent(YModulaToPanthForm); 
%><label class="<%=label.getClassType()%>" for="QtaEvasaUmPrm"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebTextInput YModulaToPanthQtaEvasaUmPrm =  
     new com.thera.thermfw.web.WebTextInput("YModulaToPanth", "QtaEvasaUmPrm"); 
  YModulaToPanthQtaEvasaUmPrm.setParent(YModulaToPanthForm); 
%>
<input class="<%=YModulaToPanthQtaEvasaUmPrm.getClassType()%>" id="<%=YModulaToPanthQtaEvasaUmPrm.getId()%>" maxlength="<%=YModulaToPanthQtaEvasaUmPrm.getMaxLength()%>" name="<%=YModulaToPanthQtaEvasaUmPrm.getName()%>" size="<%=YModulaToPanthQtaEvasaUmPrm.getSize()%>"><% 
  YModulaToPanthQtaEvasaUmPrm.write(out); 
%>

                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <% 
   request.setAttribute("parentForm", YModulaToPanthForm); 
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
  errorList.setParent(YModulaToPanthForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YModulaToPanthForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YModulaToPanthForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YModulaToPanthBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YModulaToPanthForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YModulaToPanthBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YModulaToPanthBODC.getErrorList().getErrors()); 
           if(YModulaToPanthBODC.getConflict() != null) 
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
     if(YModulaToPanthBODC != null && !YModulaToPanthBODC.close(false)) 
        errors.addAll(0, YModulaToPanthBODC.getErrorList().getErrors()); 
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
     String errorPage = YModulaToPanthForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YModulaToPanthBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YModulaToPanthForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
