package com.momentumsystems.eeoc.cts.business;

import com.momentumsystems.businessobject.AbstractBaseBusinessObject;
import com.momentumsystems.livelink.server.SessionTool;
import com.momentumsystems.eeoc.cts.contentmanagement.CtsDocumentManagement;

/**
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $ $Date: 2009-10-29 15:54:36 $
 */
public class CtsBusinessObjectImpl extends AbstractBaseBusinessObject implements CtsBusinessObject {

    SessionTool sessionTool;
    protected CtsDocumentManagement ctsDocumentManagement;


    public SessionTool getSessionTool() {
        return sessionTool;
    }

    public void setSessionTool( SessionTool sessionTool ) {
        this.sessionTool = sessionTool;
    }

    public CtsDocumentManagement getCtsDocumentManagement() {
        return ctsDocumentManagement;
    }

    public void setCtsDocumentManagement(CtsDocumentManagement ctsDocumentManagement) {
        this.ctsDocumentManagement = ctsDocumentManagement;
    }

    public String toString()
    {
        return "com.momentumsystems.eeoc.cts.business.CtsBusinessObject{}";
    }
}
