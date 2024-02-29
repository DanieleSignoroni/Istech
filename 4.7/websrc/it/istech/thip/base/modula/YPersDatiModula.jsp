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
  BODataCollector YPersDatiModulaBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm YPersDatiModulaForm =  
     new com.thera.thermfw.web.WebForm(request, response, "YPersDatiModulaForm", "YPersDatiModula", null, "com.thera.thermfw.web.servlet.FormActionAdapter", false, false, true, true, true, true, null, 0, true, "it/istech/thip/base/modula/YPersDatiModula.js"); 
  YPersDatiModulaForm.setServletEnvironment(se); 
  YPersDatiModulaForm.setJSTypeList(jsList); 
  YPersDatiModulaForm.setHeader("it.thera.thip.cs.PantheraHeader.jsp"); 
  YPersDatiModulaForm.setFooter("com.thera.thermfw.common.Footer.jsp"); 
  YPersDatiModulaForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = YPersDatiModulaForm.getMode(); 
  String key = YPersDatiModulaForm.getKey(); 
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
        YPersDatiModulaForm.outTraceInfo(getClass().getName()); 
        String collectorName = YPersDatiModulaForm.findBODataCollectorName(); 
                YPersDatiModulaBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (YPersDatiModulaBODC instanceof WebDataCollector) 
            ((WebDataCollector)YPersDatiModulaBODC).setServletEnvironment(se); 
        YPersDatiModulaBODC.initialize("YPersDatiModula", true, 0); 
        YPersDatiModulaForm.setBODataCollector(YPersDatiModulaBODC); 
        int rcBODC = YPersDatiModulaForm.initSecurityServices(); 
        mode = YPersDatiModulaForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           YPersDatiModulaForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = YPersDatiModulaBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              YPersDatiModulaForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 
<% 
  WebMenuBar menuBar = new com.thera.thermfw.web.WebMenuBar("HM_Array1", "150", "#000000","#000000","#A5B6CE","#E4EAEF","#FFFFFF","#000000"); 
  menuBar.setParent(YPersDatiModulaForm); 
   request.setAttribute("menuBar", menuBar); 
%> 
<jsp:include page="/com/thera/thermfw/common/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="menuBar"/> 
</jsp:include> 
<% 
  menuBar.write(out); 
  menuBar.writeChildren(out); 
%> 
<% 
  WebToolBar myToolBarTB = new com.thera.thermfw.web.WebToolBar("myToolBar", "24", "24", "16", "16", "#f7fbfd","#C8D6E1"); 
  myToolBarTB.setParent(YPersDatiModulaForm); 
   request.setAttribute("toolBar", myToolBarTB); 
%> 
<jsp:include page="/com/thera/thermfw/common/defObjMenu.jsp" flush="true"> 
<jsp:param name="partRequest" value="toolBar"/> 
</jsp:include> 
<% 
   myToolBarTB.write(out); 
%> 
</head>
  <body onbeforeunload="<%=YPersDatiModulaForm.getBodyOnBeforeUnload()%>" onload="<%=YPersDatiModulaForm.getBodyOnLoad()%>" onunload="<%=YPersDatiModulaForm.getBodyOnUnload()%>" style="margin: 0px; overflow: hidden;"><%
   YPersDatiModulaForm.writeBodyStartElements(out); 
%> 

    <table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = YPersDatiModulaForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", YPersDatiModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form action="<%=YPersDatiModulaForm.getServlet()%>" method="post" name="YPersDatiModulaForm" style="height:100%"><%
  YPersDatiModulaForm.writeFormStartElements(out); 
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
  mytabbed.setParent(YPersDatiModulaForm); 
 mytabbed.addTab("tab1", "it.istech.thip.base.modula.resources.YPersDatiModula", "tab1", "YPersDatiModula", null, null, null, null); 
 mytabbed.addTab("tab2", "it.istech.thip.base.modula.resources.YPersDatiModula", "tab2", "YPersDatiModula", null, null, null, null); 
 mytabbed.addTab("tab3", "it.istech.thip.base.modula.resources.YPersDatiModula", "tab3", "YPersDatiModula", null, null, null, null); 
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
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPersDatiModula", "RSerieDocTra", null); 
   label.setParent(YPersDatiModulaForm); 
%><label class="<%=label.getClassType()%>" for="RelSerieDocTrasf"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPersDatiModulaRelSerieDocTrasf =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPersDatiModula", "RelSerieDocTrasf", false, false, true, 2, null, null); 
  YPersDatiModulaRelSerieDocTrasf.setParent(YPersDatiModulaForm); 
  YPersDatiModulaRelSerieDocTrasf.write(out); 
%>
<!--<span class="multisearchform" id="RelSerieDocTrasf"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPersDatiModula", "RCauTesDocTra", null); 
   label.setParent(YPersDatiModulaForm); 
%><label class="<%=label.getClassType()%>" for="RelCauDocTrasf"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPersDatiModulaRelCauDocTrasf =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPersDatiModula", "RelCauDocTrasf", false, false, true, 1, null, null); 
  YPersDatiModulaRelCauDocTrasf.setParent(YPersDatiModulaForm); 
  YPersDatiModulaRelCauDocTrasf.write(out); 
%>
<!--<span class="multisearchform" id="RelCauDocTrasf"></span>-->
                    </td>
                  </tr>
                </table>
              <% mytabbed.endTab(); %> 
</div>
              <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab2")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab2"); %>
                <table style="width: 100%;">
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPersDatiModula", "RSerieDocGen", null); 
   label.setParent(YPersDatiModulaForm); 
%><label class="<%=label.getClassType()%>" for="RelSerieDocGen"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPersDatiModulaRelSerieDocGen =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPersDatiModula", "RelSerieDocGen", false, false, true, 2, null, null); 
  YPersDatiModulaRelSerieDocGen.setParent(YPersDatiModulaForm); 
  YPersDatiModulaRelSerieDocGen.write(out); 
%>
<!--<span class="multisearchform" id="RelSerieDocGen"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPersDatiModula", "RCauDocGen", null); 
   label.setParent(YPersDatiModulaForm); 
%><label class="<%=label.getClassType()%>" for="RelCauDocGen"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPersDatiModulaRelCauDocGen =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPersDatiModula", "RelCauDocGen", false, false, true, 1, null, null); 
  YPersDatiModulaRelCauDocGen.setParent(YPersDatiModulaForm); 
  YPersDatiModulaRelCauDocGen.write(out); 
%>
<!--<span class="multisearchform" id="RelCauDocGen"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPersDatiModula", "RCauDocGenRigVers", null); 
   label.setParent(YPersDatiModulaForm); 
%><label class="<%=label.getClassType()%>" for="RelCauDocRigVers"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPersDatiModulaRelCauDocRigVers =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPersDatiModula", "RelCauDocRigVers", false, false, true, 1, null, null); 
  YPersDatiModulaRelCauDocRigVers.setParent(YPersDatiModulaForm); 
  YPersDatiModulaRelCauDocRigVers.write(out); 
%>
<!--<span class="multisearchform" id="RelCauDocRigVers"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPersDatiModula", "RCauDocGenRigPrel", null); 
   label.setParent(YPersDatiModulaForm); 
%><label class="<%=label.getClassType()%>" for="RelCauDocRigPrel"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPersDatiModulaRelCauDocRigPrel =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPersDatiModula", "RelCauDocRigPrel", false, false, true, 1, null, null); 
  YPersDatiModulaRelCauDocRigPrel.setParent(YPersDatiModulaForm); 
  YPersDatiModulaRelCauDocRigPrel.write(out); 
%>
<!--<span class="multisearchform" id="RelCauDocRigPrel"></span>-->
                    </td>
                  </tr>
                </table>
              <% mytabbed.endTab(); %> 
</div>
              <div class="tabbed_page" id="<%=mytabbed.getTabPageId("tab3")%>" style="width:100%;height:100%;overflow:auto;"><% mytabbed.startTab("tab3"); %>
                <table style="width: 100%;">
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPersDatiModula", "RSerieDocRiall", null); 
   label.setParent(YPersDatiModulaForm); 
%><label class="<%=label.getClassType()%>" for="RelSerieDocRiall"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPersDatiModulaRelSerieDocRiall =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPersDatiModula", "RelSerieDocRiall", false, false, true, 2, null, null); 
  YPersDatiModulaRelSerieDocRiall.setParent(YPersDatiModulaForm); 
  YPersDatiModulaRelSerieDocRiall.write(out); 
%>
<!--<span class="multisearchform" id="RelSerieDocRiall"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPersDatiModula", "RCauDocRiall", null); 
   label.setParent(YPersDatiModulaForm); 
%><label class="<%=label.getClassType()%>" for="RelCauDocRiall"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPersDatiModulaRelCauDocRiall =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPersDatiModula", "RelCauDocRiall", false, false, true, 1, null, null); 
  YPersDatiModulaRelCauDocRiall.setParent(YPersDatiModulaForm); 
  YPersDatiModulaRelCauDocRiall.write(out); 
%>
<!--<span class="multisearchform" id="RelCauDocRiall"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPersDatiModula", "RCauDocRiallRigRetpos", null); 
   label.setParent(YPersDatiModulaForm); 
%><label class="<%=label.getClassType()%>" for="RelCauDocRigRiallPos"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPersDatiModulaRelCauDocRigRiallPos =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPersDatiModula", "RelCauDocRigRiallPos", false, false, true, 1, null, null); 
  YPersDatiModulaRelCauDocRigRiallPos.setParent(YPersDatiModulaForm); 
  YPersDatiModulaRelCauDocRigRiallPos.write(out); 
%>
<!--<span class="multisearchform" id="RelCauDocRigRiallPos"></span>-->
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">
                      <%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "YPersDatiModula", "RCauDocRiallRigRetneg", null); 
   label.setParent(YPersDatiModulaForm); 
%><label class="<%=label.getClassType()%>" for="RelCauDocRigRiallRetNeg"><%label.write(out);%></label><%}%>
                    </td>
                    <td valign="top">
                      <% 
  WebMultiSearchForm YPersDatiModulaRelCauDocRigRiallRetNeg =  
     new com.thera.thermfw.web.WebMultiSearchForm("YPersDatiModula", "RelCauDocRigRiallRetNeg", false, false, true, 1, null, null); 
  YPersDatiModulaRelCauDocRigRiallRetNeg.setParent(YPersDatiModulaForm); 
  YPersDatiModulaRelCauDocRigRiallRetNeg.write(out); 
%>
<!--<span class="multisearchform" id="RelCauDocRigRiallRetNeg"></span>-->
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
  errorList.setParent(YPersDatiModulaForm); 
  errorList.write(out); 
%>
<!--<span class="errorlist"></span>-->
          </td>
        </tr>
      </table>
    <%
  YPersDatiModulaForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = YPersDatiModulaForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", YPersDatiModulaBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


  <%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              YPersDatiModulaForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, YPersDatiModulaBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, YPersDatiModulaBODC.getErrorList().getErrors()); 
           if(YPersDatiModulaBODC.getConflict() != null) 
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
     if(YPersDatiModulaBODC != null && !YPersDatiModulaBODC.close(false)) 
        errors.addAll(0, YPersDatiModulaBODC.getErrorList().getErrors()); 
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
     String errorPage = YPersDatiModulaForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", YPersDatiModulaBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = YPersDatiModulaForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
