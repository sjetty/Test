package com.momentumsystems.eeoc.cts.web;

import com.momentumsystems.eeoc.cts.web.transferObj.DncItem;
import com.momentumsystems.eeoc.cts.web.transferObj.DncDocument;
import com.momentumsystems.eeoc.cts.web.transferObj.HoldingBinDocument;
import com.momentumsystems.eeoc.cts.business.DncBusinessObject;
import com.momentumsystems.eeoc.cts.util.system.CtsSystemInfoImpl;
import com.momentumsystems.livelink.util.UserUtils;
import com.momentumsystems.livelink.User;

import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $ $Date: 2009-10-29 15:54:36 $
 */
public class DncManagerBean extends AbstractSessionManagerBean
{
    public static final String NAME = "DncManagerBean";

    private DncBusinessObject dncBusinessObject;

    public void init() {
        super.init();
    }

    public DncManagerBean() {
    }

    public String displayDnc() {
        String action = "newsClip";
        DncItem dncItem = (DncItem) getBean(DncItem.NAME);
        dncItem.reset();

        CtsSystemInfoImpl ctsSystemInfo = (CtsSystemInfoImpl) systemInfo;
        User llUser = UserUtils.getUser(ctsSystemInfo.getDncUser());
        dncItem.setUser(llUser);
        setBean(DncItem.NAME, dncItem);
        return action;
    }

    public void browseHoldingBin() {
        DncItem dncItem = (DncItem) getBean(DncItem.NAME);
        dncItem.setHoldingBinFiles(dncBusinessObject.browseHoldingBin(dncItem));
        dncItem.setShowHoldingBin(true);
        setBean(DncItem.NAME, dncItem);
    }

    public void selectHoldingBinFile(){
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String holdingBinId = params.get( "holdingBinId" );
        String holdingBinName = params.get( "holdingBinName" );
        String holdingBinUrl = params.get( "holdingBinUrl" );
        DncItem dncItem = (DncItem) getBean(DncItem.NAME);
        dncItem.setHoldingBinFileId( holdingBinId);
        dncItem.setHoldingBinFileName( holdingBinName);
        dncItem.setHoldingBinFileUrl( holdingBinUrl);
        dncItem.setShowHoldingBin( false);
        setBean(DncItem.NAME, dncItem);
    }

    public String uploadFile() {
        DncItem dncItem = (DncItem) getBean(DncItem.NAME);
        String action = "newsClip";
        if (dncItem.getLocalFile() == null && dncItem.getHoldingBinFileName() == null)
            return action;

        if (dncItem.getLocalFile() != null) {
            DncDocument dncDocument = new DncDocument();
            dncDocument.setFile(dncItem.getLocalFile());
            dncItem.setDncDocument(dncDocument);
            dncItem.setLocalFile(null);
        }
        if (dncItem.getHoldingBinFileName() != null) {
            HoldingBinDocument hbDocument = new HoldingBinDocument();
            hbDocument.setId(dncItem.getHoldingBinFileId());
            hbDocument.setFileName(dncItem.getHoldingBinFileName());
            hbDocument.setFetchUrl(dncItem.getHoldingBinFileUrl());
            dncItem.setHoldingBinDocument(hbDocument);
            dncItem.setHoldingBinFileName(null);
        }
        setBean(DncItem.NAME, dncItem);
        return action;
    }

    public String submitAction() {
        String action = "";
        DncItem dncItem = (DncItem) getBean(DncItem.NAME);
        if (dncItem.validate()) {
          
            dncItem = dncBusinessObject.saveDncItem(dncItem);
            CtsSystemInfoImpl ctsSystemInfo = (CtsSystemInfoImpl) systemInfo;
            String dncFolderUrl = ctsSystemInfo.getBaseUrl() + ctsSystemInfo.getBrowseUrl()
                                  + dncItem.getFolderUrl() + "&objAction=browse&amp;sort=name";
            dncItem.setFolderUrl(dncFolderUrl);
            setBean(DncItem.NAME, dncItem);
            action = "complete";
        }
        return action;
    }

    public DncBusinessObject getDncBusinessObject() {
        return dncBusinessObject;
    }

    public void setDncBusinessObject(DncBusinessObject dncBusinessObject) {
        this.dncBusinessObject = dncBusinessObject;
    }
}
