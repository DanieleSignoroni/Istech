<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"
                      "file:///C:/pthsvil/deps/T_Thip_40/Thip/4.0/websrcsvil/dtd/xhtml1-transitional.dtd">
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
  BODataCollector DocMagTrasferimentoBODC = null; 
  List errors = new ArrayList(); 
  WebJSTypeList jsList = new WebJSTypeList(); 
  WebForm DocMagTrasferimentoForm =  
     new com.thera.thermfw.web.WebForm(request, response, "DocMagTrasferimentoForm", "DocMagTrasferimento", null, "it.istech.thip.magazzino.documenti.web.YDocMagTrasfEstrattoFormActionAdapter", false, false, false, false, true, true, "it.thera.thip.magazzino.documenti.web.DocMagTrasfDataCollector", 1, false, null); 
  DocMagTrasferimentoForm.setServletEnvironment(se); 
  DocMagTrasferimentoForm.setJSTypeList(jsList); 
  DocMagTrasferimentoForm.setHeader(null); 
  DocMagTrasferimentoForm.setFooter(null); 
  DocMagTrasferimentoForm.setWebFormModifierClass("it.istech.thip.magazzino.documenti.web.YDocMagTrasfEstrattoFormModifier"); 
  DocMagTrasferimentoForm.setDeniedAttributeModeStr("hideNone"); 
  int mode = DocMagTrasferimentoForm.getMode(); 
  String key = DocMagTrasferimentoForm.getKey(); 
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
        DocMagTrasferimentoForm.outTraceInfo(getClass().getName()); 
        String collectorName = DocMagTrasferimentoForm.findBODataCollectorName(); 
                DocMagTrasferimentoBODC = (BODataCollector)Factory.createObject(collectorName); 
        if (DocMagTrasferimentoBODC instanceof WebDataCollector) 
            ((WebDataCollector)DocMagTrasferimentoBODC).setServletEnvironment(se); 
        DocMagTrasferimentoBODC.initialize("DocMagTrasferimento", true, 1); 
        DocMagTrasferimentoForm.setBODataCollector(DocMagTrasferimentoBODC); 
        int rcBODC = DocMagTrasferimentoForm.initSecurityServices(); 
        mode = DocMagTrasferimentoForm.getMode(); 
        if (rcBODC == BODataCollector.OK) 
        { 
           requestIsValid = true; 
           DocMagTrasferimentoForm.write(out); 
           if(mode != WebForm.NEW) 
              rcBODC = DocMagTrasferimentoBODC.retrieve(key); 
           if(rcBODC == BODataCollector.OK) 
           { 
              DocMagTrasferimentoForm.writeHeadElements(out); 
           // fine blocco XXX  
           // a completamento blocco di codice YYY a fine body con catch e gestione errori 
%> 

	<title></title>
</head>

<body leftmargin="0" rightmargin="0" topmargin="0" bottommargin="0" onload="<%=DocMagTrasferimentoForm.getBodyOnLoad()%>" onunload="<%=DocMagTrasferimentoForm.getBodyOnUnload()%>" onbeforeunload="<%=DocMagTrasferimentoForm.getBodyOnBeforeUnload()%>"><%
   DocMagTrasferimentoForm.writeBodyStartElements(out); 
%> 


	

	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
<tr>
<td style="height:0" valign="top">
<% String hdr = DocMagTrasferimentoForm.getCompleteHeader();
 if (hdr != null) { 
   request.setAttribute("dataCollector", DocMagTrasferimentoBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= hdr %>" flush="true"/> 
<% } %> 
</td>
</tr>

<tr>
<td valign="top" height="100%">
<form name="DocMagTrasfEstratto" action="<%=DocMagTrasferimentoForm.getServlet()%>" method="post" style="height:100%"><%
  DocMagTrasferimentoForm.writeFormStartElements(out); 
%>

		<table class="resTableEstratto">
			<tr>
				<td width="15%">
					<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocMagTrasferimento", "NumeroDocumento", null); 
   label.setParent(DocMagTrasferimentoForm); 
%><label for="NumeroDocumento" class="<%=label.getClassType()%>"><%label.write(out);%></label><%}%>
				</td>
				<td width="15%">
					<% 
  WebTextInput DocMagTrasferimentoNumeroDocumento =  
     new com.thera.thermfw.web.WebTextInput("DocMagTrasferimento", "NumeroDocumento"); 
  DocMagTrasferimentoNumeroDocumento.setParent(DocMagTrasferimentoForm); 
%>
<input type="text" size="<%=DocMagTrasferimentoNumeroDocumento.getSize()%>" id="<%=DocMagTrasferimentoNumeroDocumento.getId()%>" name="<%=DocMagTrasferimentoNumeroDocumento.getName()%>" maxlength="<%=DocMagTrasferimentoNumeroDocumento.getMaxLength()%>" class="<%=DocMagTrasferimentoNumeroDocumento.getClassType()%>"><% 
  DocMagTrasferimentoNumeroDocumento.write(out); 
%>

				</td>
				<td width="15%">
					<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocMagTrasferimento", "DataDocumento", null); 
   label.setParent(DocMagTrasferimentoForm); 
%><label for="DataDocumento" class="<%=label.getClassType()%>"><%label.write(out);%></label><%}%>
				</td>
				<td width="55%">
					<% 
  WebTextInput DocMagTrasferimentoDataDocumento =  
     new com.thera.thermfw.web.WebTextInput("DocMagTrasferimento", "DataDocumento"); 
  DocMagTrasferimentoDataDocumento.setParent(DocMagTrasferimentoForm); 
%>
<input type="text" size="<%=DocMagTrasferimentoDataDocumento.getSize()%>" id="<%=DocMagTrasferimentoDataDocumento.getId()%>" name="<%=DocMagTrasferimentoDataDocumento.getName()%>" maxlength="<%=DocMagTrasferimentoDataDocumento.getMaxLength()%>" class="<%=DocMagTrasferimentoDataDocumento.getClassType()%>"><% 
  DocMagTrasferimentoDataDocumento.write(out); 
%>

				</td>
			</tr>
			<tr>
				<td width="15%">
					<%{  WebLabelCompound label = new com.thera.thermfw.web.WebLabelCompound(null, null, "DocMagTrasferimento", "IdCau", null); 
   label.setParent(DocMagTrasferimentoForm); 
%><label for="Causale" class="<%=label.getClassType()%>"><%label.write(out);%></label><%}%>
				</td>
				<td colspan="3" nowrap>
					<% 
  WebMultiSearchForm DocMagTrasferimentoCausale =  
     new com.thera.thermfw.web.WebMultiSearchForm("DocMagTrasferimento", "Causale", false, false, true, 1, null, null); 
  DocMagTrasferimentoCausale.setParent(DocMagTrasferimentoForm); 
  DocMagTrasferimentoCausale.write(out); 
%>
<!--<span class="multisearchform" id="Causale"></span>-->
				</td>
			</tr>
		</table>
	<%
  DocMagTrasferimentoForm.writeFormEndElements(out); 
%>
</form></td>
</tr>

<tr>
<td style="height:0">
<% String ftr = DocMagTrasferimentoForm.getCompleteFooter();
 if (ftr != null) { 
   request.setAttribute("dataCollector", DocMagTrasferimentoBODC); 
   request.setAttribute("servletEnvironment", se); %>
  <jsp:include page="<%= ftr %>" flush="true"/> 
<% } %> 
</td>
</tr>
</table>


			
<%
           // blocco YYY  
           // a completamento blocco di codice XXX in head 
              DocMagTrasferimentoForm.writeBodyEndElements(out); 
           } 
           else 
              errors.addAll(0, DocMagTrasferimentoBODC.getErrorList().getErrors()); 
        } 
        else 
           errors.addAll(0, DocMagTrasferimentoBODC.getErrorList().getErrors()); 
           if(DocMagTrasferimentoBODC.getConflict() != null) 
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
     if(DocMagTrasferimentoBODC != null && !DocMagTrasferimentoBODC.close(false)) 
        errors.addAll(0, DocMagTrasferimentoBODC.getErrorList().getErrors()); 
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
     String errorPage = DocMagTrasferimentoForm.getErrorPage(); 
%> 
     <jsp:include page="<%=errorPage%>" flush="true"/> 
<% 
  } 
  else 
  { 
     request.setAttribute("ConflictMessages", DocMagTrasferimentoBODC.getConflict()); 
     request.setAttribute("ErrorMessages", errors); 
     String conflictPage = DocMagTrasferimentoForm.getConflictPage(); 
%> 
     <jsp:include page="<%=conflictPage%>" flush="true"/> 
<% 
   } 
   } 
%> 
</body>
</html>
