package com.momentumsystems.eeoc.cts.web.transferObj;

import java.io.Serializable;
import java.util.Date;

/**
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $ $Date: 2009-10-29 15:54:36 $
 */
public class HoldingBinDocument implements Serializable {
    private String id;
    private String fileName;
    private Date dateCreated;
    private String docType;
    private String docTypeTxt;
    private String fetchUrl;
    private boolean toCopy;

    public HoldingBinDocument() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocTypeTxt() {
        return docTypeTxt;
    }

    public void setDocTypeTxt(String docTypeTxt) {
        this.docTypeTxt = docTypeTxt;
    }

    public String getFetchUrl() {
        return fetchUrl;
    }

    public void setFetchUrl(String fetchUrl) {
        this.fetchUrl = fetchUrl;
    }

    public boolean isToCopy() {
        return toCopy;
    }

    public void setToCopy(boolean toCopy) {
        this.toCopy = toCopy;
    }
}