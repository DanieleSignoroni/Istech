package it.sicons.thip.vendite.ordineVE.web;

import com.thera.thermfw.ad.*;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.web.*;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class WebTextInputGrid extends WebTextInput {

	protected String objectKey;
	protected String size;
	protected String onClick;
	protected String onClickValue;
	protected static int a = 0;

	public static int getNumeratore() {
		return a;
	}
	
	public WebTextInputGrid(String classHdr, String classAD, String size, String resourceTooltipFile, String resourceTooltipId) {
		super(classHdr, classAD, resourceTooltipFile, resourceTooltipId);
		this.size = size;
	}


	public WebTextInputGrid(ClassAD classAD, String size, String resourceTooltipFile, String resourceTooltipId) {
		super(classAD.getClassName(), classAD.getAttributeName(), resourceTooltipFile, resourceTooltipId);
		classADObject = classAD;
		this.size = size;
	}


	public String getObjectKey() {
		return objectKey;
	}


	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}


	public String getKeyName() {
		return ( getName() + "" + getObjectKey() ).replace(KeyHelper.KEY_SEPARATOR, "/");

	}


	public String getKeyId() {
		return getId() + "" +  getObjectKey();
	}


	public String getId() {
		extractParam();
		String name;
		if (getOwnerForm() instanceof WebFormForInternalRowForm)
			name = ((WebFormForInternalRowForm)getOwnerForm()).editGridCDName + "$$" + completeClassAD;
		else
			name = completeClassAD;
		return WebElement.getIdFromName(name, classCD);
	}


	public String getClassType() {
		return "gridEditText";
	}

	public String getNewKeyId() {
		String newKey = getKeyId();
		if(newKey.contains("-")) 
			newKey = newKey.replace("-", "");
		if(newKey.contains(","))
			newKey = newKey.replace(",", "");
		if(newKey.contains(";"))
			newKey = newKey.replace(";", "");
		if(newKey.contains("/"))
			newKey = newKey.replace("/", "");
		if(newKey.contains("."))
			newKey = newKey.replace(".", "");
		if(newKey.contains("^"))
			newKey = newKey.replace("^", "");
		if(newKey.contains("("))
			newKey = newKey.replace("(", "");
		if(newKey.contains(")"))
			newKey = newKey.replace(")", "");
		if(newKey.contains(" "))
			newKey = newKey.replace(" ", "");
		if(newKey.contains(KeyHelper.KEY_SEPARATOR))
			newKey = newKey.replace(KeyHelper.KEY_SEPARATOR, "/");
		return newKey + a;
	}
	
	public String getNewKeyName() {
		String newKey = getKeyName();
		if(newKey.contains("-")) 
			newKey = newKey.replace("-", "");
		if(newKey.contains(","))
			newKey = newKey.replace(",", "");
		if(newKey.contains(";"))
			newKey = newKey.replace(";", "");
		if(newKey.contains("/"))
			newKey = newKey.replace("/", "");
		if(newKey.contains("."))
			newKey = newKey.replace(".", "");
		if(newKey.contains("^"))
			newKey = newKey.replace("^", "");
		if(newKey.contains("("))
			newKey = newKey.replace("(", "");
		if(newKey.contains(")"))
			newKey = newKey.replace(")", "");
		if(newKey.contains(" "))
			newKey = newKey.replace(" ", "");
		if(newKey.contains(KeyHelper.KEY_SEPARATOR))
			newKey = newKey.replace(KeyHelper.KEY_SEPARATOR, "/");
		return newKey + a;
	}
	
	@SuppressWarnings("unused")
	public String getElementOut() {
		String ret = "";
		extractParam();
		ownerForm = (WebForm)getParent().getParent();
		String str = size;
		String fieldElement = "document.forms[0]." + getNewKeyId();
		String fieldValue = "'" + str + "'";
		ret += 
				"\n" + 
						"<input type=\"text\"" +
						" name=" + getQuotedString(getNewKeyName()) +
						" id=" + getQuotedString(getKeyId()) +
						" size=" + getQuotedString(getSize()) +
						" maxlength=" + getQuotedString(getMaxLength()) +
						" class=" + getQuotedString(getClassType()) + 
						" value=" + getQuotedString(str) + 
						"/>";

		elementNumber = WebSvilInfoManager.getInstance(getServletEnvironment()).registerWebElement(this);
		customizeExtend();
		elementNumber = WebSvilInfoManager.getInstance(getServletEnvironment()).updateWebElement(this);
		
		String fieldNLS = "'" + formatValue(classADObject.findLabelText()) + "'";
		boolean isFieldCheckAll = isCheckAll;
		boolean isFieldKey = classADObject.getKeySequence() > 0;
		if (generateType()) {
			String typeName = ownerForm.getJSTypeList().startNewType(classADObject.getType());
			String inportForType = ownerForm.getJSTypeList().getImportForCurrentType(ownerForm);
			if(inportForType.length() > 0)
				ret += "\n" + inportForType;

			ret += "\n" + "<script language=\"JavaScript1.2\">";
			ret += "\n" + " " + ownerForm.getJSTypeList().getConstructorForCurrentType(WebForm.FORM_PREF + getNewKeyId(), classADObject.isMandatory());

			String fieldType = ownerForm.getJSTypeList().getCurrentTypeName();
			boolean isFieldActionEnable = actionEnable;

			String fieldClassType = "'" + getClassType() + "'";

			String fieldTooltipText = "null";
			if(tooltipText != null) {
				fieldTooltipText = "'" + tooltipText + "'";
			}

			fieldTooltipText = getSvilInfoTooltipText(classADObject, fieldTooltipText);

			if(actionEnable) {
				if (onBlur != null) {
					ret += "\n" + fieldElement + ".addOB = function(){" + onBlur + "};";
				}

				if (onFocus != null) {
					ret += "\n" + fieldElement + ".addOF = function(){" + onFocus + "};";
				}

				if (onChange != null) {
					ret += "\n" + fieldElement + ".addOC = function(){" + onChange + "};";
				}
			}
			ret += "\n" + " "+fieldElement + ".fieldSecurity=\""+getSecurityFieldString(findClassADObject().getAuthorizationLevel())+"\";";

			ret += "\n" + fieldElement + ".object_key =\"" + objectKey + "\";";

			ret += "\n" + " setupNF(" + fieldElement + "," + fieldType + "," +
					fieldValue + "," + fieldNLS + "," +
					isFieldCheckAll + "," + isFieldKey + "," +
					isFieldActionEnable + "," + fieldClassType + "," + fieldTooltipText + ");";
			ret += "  addIdFromName('" + getNewKeyName() + "','" + getNewKeyId() + "');\n";
			ret += "  setupCampoEditGriglia(" + fieldElement + ");";
			ret += "\n" + "</script>";
		}
		else {
			ret += "\n" + "<script language=\"JavaScript1.2\">";
			ret += "\n" + " setupNoTF(" + fieldElement + "," +
					fieldValue + "," + fieldNLS + "," +
					isFieldCheckAll + "," + isFieldKey + ");";
			ret += "  addIdFromName('" + getNewKeyName() + "','" + getNewKeyId() + "');\n";
			ret += "\n" + "</script>";
		}
		a++;
		return ret;

	}
	
	@SuppressWarnings("unused")
	public String getElementOutCheckBox() {
		String ret = "";
		extractParam();
		String checked = size != null && size.equals("Vero") ? "true" : "false";
		ownerForm = (WebForm)getParent().getParent();

		ret += 
				"\n" + 
						"<input type=\"checkbox\"" +
						" name=" + getQuotedString(getNewKeyName()) +
						" id=" + getQuotedString(getNewKeyId());
						if(checked.equals("true"))
							ret += " checked=" + checked;
						ret += " class=" + getQuotedString(getClassType()) + 
						"/>";


		elementNumber = WebSvilInfoManager.getInstance(getServletEnvironment()).registerWebElement(this);
		customizeExtend();
		elementNumber = WebSvilInfoManager.getInstance(getServletEnvironment()).updateWebElement(this);
		String str = size;
		String fieldElement = "document.forms[0]." + getNewKeyId();
		String fieldValue = "'" + str + "'";
		String fieldNLS = "'" + formatValue(classADObject.findLabelText()) + "'";
		boolean isFieldCheckAll = isCheckAll;
		boolean isFieldKey = classADObject.getKeySequence() > 0;
			String typeName = ownerForm.getJSTypeList().startNewType(classADObject.getType());
			String inportForType = ownerForm.getJSTypeList().getImportForCurrentType(ownerForm);
			if(inportForType.length() > 0)
				ret += "\n" + inportForType;

			ret += "\n" + "<script language=\"JavaScript1.2\">";
			ret += "\n" + " " + ownerForm.getJSTypeList().getConstructorForCurrentType(WebForm.FORM_PREF + getNewKeyId(), classADObject.isMandatory());

			String fieldType = ownerForm.getJSTypeList().getCurrentTypeName();
			boolean isFieldActionEnable = actionEnable;

			String fieldClassType = "'" + getClassType() + "'";

			String fieldTooltipText = "null";
			if(tooltipText != null) {
				fieldTooltipText = "'" + tooltipText + "'";
			}

			fieldTooltipText = getSvilInfoTooltipText(classADObject, fieldTooltipText);

			if(actionEnable) {
				if (onBlur != null) {
					ret += "\n" + fieldElement + ".addOB = function(){" + onBlur + "};";
				}

				if (onFocus != null) {
					ret += "\n" + fieldElement + ".addOF = function(){" + onFocus + "};";
				}

				if (onClick != null) {
					ret += "\n" + fieldElement + ".addOC = function(){" + onClick + "};";
				}
			}
			ret += "\n" + " "+fieldElement + ".fieldSecurity=\""+getSecurityFieldString(findClassADObject().getAuthorizationLevel())+"\";";

			ret += "\n" + fieldElement + ".object_key =\"" + objectKey + "\";";

			ret += "\n" + " setupNF(" + fieldElement + "," + fieldType + "," +
					checked + "," + fieldNLS + "," +
					isFieldCheckAll + "," + isFieldKey + "," +
					isFieldActionEnable + "," + fieldClassType + "," + fieldTooltipText + ");";
			ret += "  addIdFromName('" + getNewKeyName() + "','" + getNewKeyId() + "');\n";
			ret += "  setupCampoEditGriglia(" + fieldElement + ");";
			ret += "\n" + "</script>";
		return ret;

	}
	
	public void setOnClick(String onClick)
	{
		this.onClick = onClick;
	}

	public String getOnClick()
	{
		extractParam();
		return onClick;
	}


}