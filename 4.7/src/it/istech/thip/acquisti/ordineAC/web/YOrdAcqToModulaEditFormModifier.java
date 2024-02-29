package it.istech.thip.acquisti.ordineAC.web;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.web.WebFormModifierExtended;
import com.thera.thermfw.web.WebJSTypeList;

public class YOrdAcqToModulaEditFormModifier extends WebFormModifierExtended{

	@Override
	public void writePostHeadElements(JspWriter out) throws IOException {
		out.println(WebJSTypeList.getImportForCSS("thermweb/css/GridTextInput.css", getServletEnvironment().getRequest()));
		out.println(WebJSTypeList.getImportForJSLibrary("it/istech/thip/base/modula/YOrdAcqToModulaEdit.js", getServletEnvironment().getRequest()));
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function controlLabel(title){}");
		out.println("</script>");
	}
	
	public void writePostFormEndElements(JspWriter out) throws IOException {
		out.println(WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/ajax/ajaxUtil.js", getServletEnvironment().getRequest()));
		out.println(WebJSTypeList.getImportForJSLibrary("thermweb/factory/gui/ajax/XMLElement.js", getServletEnvironment().getRequest()));
	}

}
