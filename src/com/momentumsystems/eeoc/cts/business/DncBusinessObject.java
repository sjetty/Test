package com.momentumsystems.eeoc.cts.business;

import com.momentumsystems.eeoc.cts.web.transferObj.DncItem;
import com.momentumsystems.eeoc.cts.web.transferObj.HoldingBinDocument;

import java.util.List;

/**
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $ $Date: 2009-10-29 15:54:36 $
 */
public interface DncBusinessObject {

    List<HoldingBinDocument> browseHoldingBin(DncItem dncItem);
    DncItem saveDncItem(DncItem dncItem);
    
}
