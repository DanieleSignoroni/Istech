package it.istech.thip.base.modula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.ajax.XMLElement;
import com.thera.thermfw.web.ajax.XMLElementBuilder;
import com.thera.thermfw.web.servlet.BaseServlet;

import it.istech.thip.base.modula.YDocAcqToModula;

public class YDocAcqToModulaAjaxServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String EVENT_ELEMENT = "Event";
	public static final String EVENTID_ATTRIBUTE = "eventId";

	public static final String MSG_NUM_SEL = "MsgNumSel";
	public static final String BLUR = "Blur";
	public static final String SELECT_ROW = "SelectRow";
	public static final String VALUE = "value";


	public ErrorMessage getGenericErrorMessage(String text){
		return new ErrorMessage("BAS0000078",text);
	}   

	@SuppressWarnings("rawtypes")
	protected void processAction(ServletEnvironment se) throws Exception {
		String classAD = getStringParameter(se.getRequest(), "ClassAD");
		XMLElementBuilder builder = new XMLElementBuilder();
		XMLElement xmlElement = builder.parseStream(se.getRequest().getInputStream());

		XMLElement response = null;
		XMLElement event = xmlElement.getFirstChild(EVENT_ELEMENT);
		String eventId = event.getAttribute(EVENTID_ATTRIBUTE);
		if (eventId.equals(BLUR)) {
			List errori = processBlurForSave(xmlElement, classAD, se);
			String kManutEti = getAttributeFromRequest(xmlElement, "selectRow");

			response = new XMLElement("response");
			response.setAttribute("Key", kManutEti);
			if (!errori.isEmpty()){
				XMLElement erroriXml = new XMLElement("Errors");
				erroriXml.setAttribute("Text", getDescrizioneErrori(errori));
				response.appendChild(erroriXml);
			}

		}

		replyXML(response, se.getResponse());

	}


	@SuppressWarnings("rawtypes")
	public String getDescrizioneErrori(List errori) {
		String ret = null;

		Iterator iter = errori.iterator();
		while (iter.hasNext()) {
			ErrorMessage err = (ErrorMessage)iter.next();
			if (ret == null) {
				ret = err.getQualifiedLongText();
			}
			else {
				ret += " | " + err.getQualifiedLongText();
			}
		}

		return ret;
	}


	protected void replyXML(XMLElement xmlRes, HttpServletResponse res) throws IOException {
		if(xmlRes != null) {
			res.setContentType("text/xml; charset=windows-1252");
			PrintWriter pw = res.getWriter();
			pw.println(xmlRes.toXMLString("windows-1252"));

			pw.flush();
		}
	}


	@SuppressWarnings({ "rawtypes" })
	protected List processBlurForSave(XMLElement xmlReq, String classAD, ServletEnvironment se) throws IOException {
		List ret = new ArrayList(); 
		try {
			String chiaveRiga = getAttributeFromRequest(xmlReq, "selectRow");
			chiaveRiga = URLDecoder.decode(chiaveRiga,"UTF-8");
			String valore = getAttributeFromRequest(xmlReq, "value");
			chiaveRiga = chiaveRiga.replace("/", KeyHelper.KEY_SEPARATOR);
			YDocAcqToModula gestoreEv = (YDocAcqToModula)YDocAcqToModula.elementWithKey(YDocAcqToModula.class, chiaveRiga, 0);
			if(classAD.equals("QtaDaEvadere")) {
				gestoreEv.setQtaDaEvadere(new BigDecimal(valore));
			}
			if(gestoreEv.save() >= 0) {
				ConnectionManager.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return ret;
	}


	@SuppressWarnings({ "deprecation", "unused" })
	protected XMLElement processSelect(XMLElement xmlReq) {
		XMLElement response = null;

		String chiaveRiga = getAttributeFromRequest(xmlReq, "selectRow");
		chiaveRiga = URLDecoder.decode(chiaveRiga);
		String select = getAttributeFromRequest(xmlReq, "selected");
		String valore = getAttributeFromRequest(xmlReq, "value");

		return response;
	}


	@SuppressWarnings("unused")
	protected XMLElement processMsgNumSel(XMLElement xmlReq) {
		XMLElement response = null;

		XMLElement xmlMessge = new XMLElement("response");

		return response;
	}


	public String getAttributeFromRequest(XMLElement xmlRequest, String attName) {
		XMLElement contents = xmlRequest.getFirstChild("Contents");
		return contents.getAttribute(attName);
	}

}
