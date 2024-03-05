package it.istech.thip.base.modula.web;

import com.thera.thermfw.ad.ClassAD;
import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.ad.ClassADCollectionManager;
import com.thera.thermfw.ad.DescriptorAuthorizable;
import com.thera.thermfw.gui.cnr.DisplayObject;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.web.WebGridStylistDefault;

import it.sicons.thip.vendite.ordineVE.web.WebTextInputGrid;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class YDocVenToModulaStylist extends WebGridStylistDefault{

	public ClassADCollection getClassADCollection() throws Exception{
		return ClassADCollectionManager.collectionWithName("YDocVenToModula");
	}


	public  String getFormattedValueForTD(DisplayObject curDO, int adIndex, ClassAD[] classADs) {
		String ret = "";
		int indexQtaDaEvadere = 0;
		for(ClassAD classAD : classADs) {
			if("QtaDaEvadere".equals(classAD.getAttributeName())){
				break;
			}
			indexQtaDaEvadere++;
		}
		if(indexQtaDaEvadere == adIndex) {
			ClassAD classAD = classADs[indexQtaDaEvadere];

			if (!classAD.isReadOnly() && classAD.getAuthorizationLevel() == DescriptorAuthorizable.WRITE) {
				String value = null;
				if (value == null) {
					value = (String)curDO.getFormattedValue(adIndex + 1);
					value = value.equals(DisplayObject.NULL_VIDEO_VALUE) ? "" : value;
				}

				WebTextInputGrid ti = new WebTextInputGrid(classAD, value, null, null);
				String objKey = curDO.getObjectKey().replace(KeyHelper.KEY_SEPARATOR, "/");
				ti.setObjectKey(objKey);
				ti.setParent(getGridForm());
				ti.setOnChange("onBlurField" + "(" + getQuotedString(ti.getKeyName()) + "," + getQuotedString(objKey) + "," + classAD.getAttributeName() + ")" );
				ret = ti.getElementOut();
			}
			else {
				ret = super.getFormattedValueForTD(curDO, adIndex, classADs);
			}
		}else {
			ret = super.getFormattedValueForTD(curDO, adIndex, classADs);
		}


		return ret;
	}

}
