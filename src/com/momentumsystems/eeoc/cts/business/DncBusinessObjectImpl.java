package com.momentumsystems.eeoc.cts.business;

import com.momentumsystems.eeoc.cts.web.transferObj.DncItem;
import com.momentumsystems.eeoc.cts.web.transferObj.HoldingBinDocument;
import com.momentumsystems.eeoc.cts.persistence.DailyNewsClipsDao;
import com.momentumsystems.eeoc.cts.domain.DailyNewsClips;
import com.momentumsystems.eeoc.cts.contentmanagement.CtsDocumentManagement;
import com.momentumsystems.businessobject.AbstractBaseBusinessObject;
import com.momentumsystems.livelink.LivelinkException;

import java.util.Calendar;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

/**
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $ $Date: 2009-10-29 15:54:36 $
 */
public class DncBusinessObjectImpl extends AbstractBaseBusinessObject implements DncBusinessObject {

    private DailyNewsClipsDao dailyNewsClipsDao;
    private CtsDocumentManagement ctsDocumentManagement;
    private WorkflowBusinessObject workflowBusinessObject;
    
    public List<HoldingBinDocument> browseHoldingBin(DncItem dncItem) {
        List <HoldingBinDocument> holdingBinDocs = ctsDocumentManagement.getHoldingBinFiles();
        return holdingBinDocs;
    }

    public DncItem saveDncItem(DncItem dncItem) {
        DailyNewsClips dnc = new DailyNewsClips();
        dnc.setChangeDt(Calendar.getInstance().getTime());
        dnc.setCreateDt(Calendar.getInstance().getTime());
        dnc.setDescriptionTxt(dncItem.getDescription());
        if (StringUtils.isNotBlank(dncItem.getPageCount()))
            dnc.setPageCount(Integer.parseInt(dncItem.getPageCount()));
        dnc.setPublicationDt(dncItem.getPublicationDate());
        dnc.setSubmittalDt(dncItem.getSubmittalDate());
        dnc.setSubmittalTypeCd(Integer.parseInt(dncItem.getSubmittalTypeCd()));

        DateFormat yyyymmFormat = new SimpleDateFormat( "yyyy-MM" );
        String yyyymm = yyyymmFormat.format( dncItem.getPublicationDate());
        Integer yyyymmFolderId = createDncSubFolder(yyyymm);
        dnc.setFolderId(yyyymmFolderId);
        dnc = (DailyNewsClips)dailyNewsClipsDao.save(dnc);

        dncItem.setControlNumber(Integer.toString(dnc.getControlNbr()));
        dncItem.setFolderUrl(yyyymmFolderId.toString());

        dncItem = addAttachments(dncItem, yyyymmFolderId, yyyymm);

        workflowBusinessObject.startDncWorkflow(dnc, dncItem.getUser());
        return dncItem;
    }

    private Integer createDncSubFolder(String subFolderName)
    {
        Integer folderId;
        try {
            folderId = ctsDocumentManagement.createYyyymmFolders(subFolderName);
        }
         catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to create daily news clips folder " + subFolderName);
            throw new RuntimeException(e);
        }
        return folderId;
    }

    private DncItem addAttachments(DncItem dncItem, Integer folderId, String destinationFolderName)
    {
        String formattedName = null;
        int numOfAttached = 0;
        try {
            if (dncItem.getDncDocument() != null && dncItem.getDncDocument().getFile() != null)
            {
                formattedName = dncItem.getFormattedFileName(dncItem.getDncDocument().getFile().getName(), numOfAttached);
                ctsDocumentManagement.addDncLocalFile(folderId, formattedName,
                                                      dncItem.getDncDocument().getFile());
                numOfAttached ++;
            }
            if (dncItem.getHoldingBinDocument() != null && dncItem.getHoldingBinDocument().getFileName() != null) {
                formattedName = dncItem.getFormattedFileName(dncItem.getHoldingBinDocument().getFileName(), numOfAttached);
                ctsDocumentManagement.addDncHoldingBinFile(folderId, formattedName,
                                                           dncItem.getHoldingBinDocument().getId(), false);
            }
        }
        catch (LivelinkException e) {
            e.printStackTrace();
            log.error("Unable to add daily news clips to folder " + destinationFolderName);
            throw new RuntimeException(e);
        }
        dncItem.setFileName(formattedName);
        return dncItem;
    }

    public DailyNewsClipsDao getDailyNewsClipsDao() {
        return dailyNewsClipsDao;
    }

    public void setDailyNewsClipsDao(DailyNewsClipsDao dailyNewsClipsDao) {
        this.dailyNewsClipsDao = dailyNewsClipsDao;
    }

    public CtsDocumentManagement getCtsDocumentManagement() {
        return ctsDocumentManagement;
    }

    public void setCtsDocumentManagement(CtsDocumentManagement ctsDocumentManagement) {
        this.ctsDocumentManagement = ctsDocumentManagement;
    }

    public WorkflowBusinessObject getWorkflowBusinessObject() {
        return workflowBusinessObject;
    }

    public void setWorkflowBusinessObject(WorkflowBusinessObject workflowBusinessObject) {
        this.workflowBusinessObject = workflowBusinessObject;
    }
}
