var CHIAVE_SESSIONE;

var SELECT_ROW = "selectRow";
var VALUE = "value";
var SELECTED = "selected";
var ACTION = "ACTION";
var ERRORS = "Errors";

var applySelection = false;
var idFocusField = "";
var righeCampiEdit = new Array();
var undefined;


function setupCampoEditGriglia(field) {
	var fieldParent = field;
	while(fieldParent != null) {
		if((fieldParent.tagName == "TR") && (fieldParent.className.indexOf("grid_row") >= 0)) {
			field.rowNum = fieldParent.getAttribute("rowNum");
			righeCampiEdit[field.rowNum] = field;
			fieldParent = null;
		}
		else
			fieldParent = fieldParent.parentNode;
	}
	defineEvent(field, eventCLICK,function() {selectField(field, event);});
}


function selectField(field, event) {
	applySelection = true;
	idFocusField = field.id;
}


function ajaxCallback(documentElement) {
	var xmlResponse = new XMLBuilder(documentElement);
	var kPosizInvent = xmlResponse.getAttribute("Key");

	if (xmlResponse.getChildren().length == 0) {
		if (kPosizInvent != undefined) {
			//updateGridForSave("YCaricaInventario", "UPDATE", URLDecode(kPosizInvent))
		}
	}
	else {
		for (var i = 0; i < xmlResponse.getChildren().length; i++) {
			if (xmlResponse.getChildren()[i].getRootName() == ERRORS) {
				var errore = xmlResponse.getChildren()[i].getAttribute("Text");
				messageResponse(errore);
			}
		}
	}
}


function messageResponse(msgErrors) {
	var textMessage = getMessageAlert(msgErrors);
	alert(textMessage);
}


function getMessageAlert(messageErr){
	var mesgAlert="";
	var params = messageErr.split("&");
	for(var i = 0; i < params.length; i++)
	{
		mesgAlert += "Mess. " + (i+1) + ": " + params[i] + "\n";
	}
	return mesgAlert;
}


function onBlurField(nomeCampo, chiaveRiga, classAd) {
	var idCampo = nomeCampo;
	checkCheckBox(chiaveRiga)
	onBlurRequest(idCampo, chiaveRiga, classAd.name);
}

function checkCheckBox(chiaveRiga){
	const collection = document.getElementsByName("ObjectKey");
	for (let i = 0; i < collection.length; i++) {
  		if (collection[i].value == chiaveRiga && collection[i].type == "checkbox") {
    	collection[i].checked = true;
  	}
}	
}

function onBlurRequest(idCampo, chiaveRiga, classAd) {
	var contents ={};
	var requestId ="1";
	var eventId="Blur";
	var field = document.getElementById(idCampo);
	var valore = field.value;
	contents[SELECT_ROW] = URLEncode(chiaveRiga);
	contents[VALUE] = valore;
	sendRequestAjax(requestId, eventId, classAd, contents);
}


function sendRequestAjax(requestID, eventID, classAD, contents) {
	var xmlRequest = buildXMLRequest(requestID, eventID, classAD, contents);
	var url = "/" + webAppPath + "/" + servletPath + "/" + getServletName();
	url += "?ClassAD=" + classAD;
	var c = new AJAXCall(url, xmlRequest.toXML(), true);
	c.call();
}


function sendRequestAjaxSync(requestID, eventID, classAD, contents) {
	var xmlRequest = buildXMLRequest(requestID, eventID, classAD, contents);
	var url = "/" + webAppPath + "/" + servletPath + "/" + getServletName();
	var c = new AJAXCall(url, xmlRequest.toXML(), true, false);
	c.call();
}


function buildXMLRequest(requestId, eventId, classAD, contents) {
	 xmlRequest = new XMLElement("Request");
	 xmlRequest.setAttribute("requestId", requestId);

	 var eventEl = new XMLElement("Event");
	 eventEl.setAttribute("eventId",eventId);
	 xmlRequest.appendChild(eventEl);

	 var contentEl = new XMLElement("Contents");
	 buildContents(contentEl, contents);
	 xmlRequest.appendChild(contentEl);

	 return xmlRequest;
}


function getServletName() {
  return "it.istech.thip.base.modula.web.YOrdAcqToModulaAjaxServlet";
}


function buildContents(contentEl, contents) {
	for(var key in contents) {
		contentEl.setAttribute(key,contents[key]);
	}
}