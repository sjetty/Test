package com.momentumsystems.eeoc.cts.web.transferObj;

import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.momentumsystems.livelink.User;

/**
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $ $Date: 2009-10-29 15:54:36 $
 */
public class DncItem implements Serializable
{
    public static final String NAME = "DncItem";

    private User user;
    private String controlNumber;
    private String submittalTypeCd;
    private String pageCount;
    private Date submittalDate;
    private Date publicationDate;
    private String description;

    private File localFile; //local file in form field
    private DncDocument dncDocument; //local file to be uploaded

    //for holding bin field
    private String holdingBinFileId;
    private String holdingBinFileName;
    private String holdingBinFileUrl;

    //holding bin file to be uploaded
    private HoldingBinDocument holdingBinDocument;

    private boolean showHoldingBin;
    private List<HoldingBinDocument> holdingBinFiles;   //the files to be displayed when browsing holding bin

    // to be displayed on complete page
    private String folderUrl;
    private String fileName;

    private List<String> errors;

    public void reset() {
        controlNumber = null;
        submittalTypeCd = "5";  //daily
        pageCount = null;
        submittalDate = new Date();
        publicationDate = new Date();
        description = null;

        localFile = null;
        dncDocument = null;

        holdingBinFileId = null;
        holdingBinFileName = null;
        holdingBinFileUrl = null;
        holdingBinDocument = null;

        holdingBinFiles = new ArrayList<HoldingBinDocument>();

        folderUrl = null;
        fileName = null;

        holdingBinDocument = null;

        errors = new ArrayList<String>();
    }

    public boolean validate() {
        errors = new ArrayList<String>();
        if (submittalDate == null)
            errors.add("Please enter Submittal Date");
        if (publicationDate == null)
            errors.add("Please enter Publication Date");
        return errors.isEmpty();
    }

    public String getFormattedFileName(String fileName, int numOfAttached) {
        DateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");

        String extension;
        String extensionPlusDot = "";
        int extensionIndex = -1;

        if (fileName!=null) {
            extensionIndex = fileName.lastIndexOf(".");
        }

        if (extensionIndex != -1) {
            extension = fileName.substring(extensionIndex + 1, fileName.length());
            if (extension != null && !extension.equals("")) {
                extensionPlusDot = "." + extension;
            }
        }

        String numOfAttachedStr = "";
        if (numOfAttached > 0)
            numOfAttachedStr = Integer.toString(numOfAttached);


        String formattedControlNbr = "00000" + controlNumber;
         formattedControlNbr = formattedControlNbr.substring(formattedControlNbr.length() -5);

        String formattedFileName;
        formattedFileName = yyyyMMddFormat.format( publicationDate )
            + "-" + formattedControlNbr
            + "-clip"
            + numOfAttachedStr
            + extensionPlusDot;

        return formattedFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getSubmittalTypeCd() {
        return submittalTypeCd;
    }

    public void setSubmittalTypeCd(String submittalTypeCd) {
        this.submittalTypeCd = submittalTypeCd;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public Date getSubmittalDate() {
        return submittalDate;
    }

    public void setSubmittalDate(Date submittalDate) {
        this.submittalDate = submittalDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getLocalFile() {
        return localFile;
    }

    public void setLocalFile(File localFile) {
        this.localFile = localFile;
    }

    public DncDocument getDncDocument() {
        return dncDocument;
    }

    public void setDncDocument(DncDocument dncDocument) {
        this.dncDocument = dncDocument;
    }

    public String getHoldingBinFileId() {
        return holdingBinFileId;
    }

    public void setHoldingBinFileId(String holdingBinFileId) {
        this.holdingBinFileId = holdingBinFileId;
    }

    public String getHoldingBinFileName() {
        return holdingBinFileName;
    }

    public void setHoldingBinFileName(String holdingBinFileName) {
        this.holdingBinFileName = holdingBinFileName;
    }

    public String getHoldingBinFileUrl() {
        return holdingBinFileUrl;
    }

    public void setHoldingBinFileUrl(String holdingBinFileUrl) {
        this.holdingBinFileUrl = holdingBinFileUrl;
    }

    public HoldingBinDocument getHoldingBinDocument() {
        return holdingBinDocument;
    }

    public void setHoldingBinDocument(HoldingBinDocument holdingBinDocument) {
        this.holdingBinDocument = holdingBinDocument;
    }

    public boolean isShowHoldingBin() {
        return showHoldingBin;
    }

    public void setShowHoldingBin(boolean showHoldingBin) {
        this.showHoldingBin = showHoldingBin;
    }

    public List<HoldingBinDocument> getHoldingBinFiles() {
        return holdingBinFiles;
    }

    public void setHoldingBinFiles(List<HoldingBinDocument> holdingBinFiles) {
        this.holdingBinFiles = holdingBinFiles;
    }

    public String getFolderUrl() {
        return folderUrl;
    }

    public void setFolderUrl(String folderUrl) {
        this.folderUrl = folderUrl;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
