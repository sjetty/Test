package com.momentumsystems.eeoc.cts.domain;

import com.momentumsystems.livelink.User;

import javax.persistence.*;
import java.util.Date;

/**
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $ $Date: 2009-10-29 15:54:36 $
 */
@Entity
@SequenceGenerator( name="EEOC_SEQ", sequenceName="NEWS_CLIP_ID_SEQ", allocationSize=1)
@Table(name = "DAILY_NEWS_CLIPS")
public class DailyNewsClips {
    private int controlNbr;
    private int submittalTypeCd;
    private int pageCount;
    private Date submittalDt;
    private Date publicationDt;
    private String descriptionTxt;
    private int folderId;
    private int workflowId;
    private Date changeDt;
    private Date createDt;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "EEOC_SEQ" )
    @Column(name = "CONTROL_NBR", nullable = false, length = 13)
    public int getControlNbr() {
        return controlNbr;
    }

    public void setControlNbr(int controlNbr) {
        this.controlNbr = controlNbr;
    }

    @Basic
    @Column(name = "SUBMITTAL_TYPE_CD", length = 3)
    public int getSubmittalTypeCd() {
        return submittalTypeCd;
    }

    public void setSubmittalTypeCd(int submittalTypeCd) {
        this.submittalTypeCd = submittalTypeCd;
    }

    @Basic
    @Column(name = "PAGE_COUNT", length = 5)
    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Basic
    @Column(name = "SUBMITTAL_DT", length = 7)
    public Date getSubmittalDt() {
        return submittalDt;
    }

    public void setSubmittalDt(Date submittalDt) {
        this.submittalDt = submittalDt;
    }

    @Basic
    @Column(name = "PUBLICATION_DT", length = 7)
    public Date getPublicationDt() {
        return publicationDt;
    }

    public void setPublicationDt(Date publicationDt) {
        this.publicationDt = publicationDt;
    }

    @Basic
    @Column(name = "DESCRIPTION_TXT", length = 2000)
    public String getDescriptionTxt() {
        return descriptionTxt;
    }

    public void setDescriptionTxt(String descriptionTxt) {
        this.descriptionTxt = descriptionTxt;
    }

    @Basic
    @Column(name = "FOLDER_ID", length = 22)
    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    @Basic
    @Column(name = "WORKFLOW_ID", length = 10)
    public int getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(int workflowId) {
        this.workflowId = workflowId;
    }

    @Basic
    @Column(name = "CHANGE_DT", length = 7)
    public Date getChangeDt() {
        return changeDt;
    }

    public void setChangeDt(Date changeDt) {
        this.changeDt = changeDt;
    }

    @Basic
    @Column(name = "CREATE_DT", length = 7)
    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyNewsClips that = (DailyNewsClips) o;

        if (controlNbr != that.controlNbr) return false;

        return true;
    }

    public int hashCode() {
        return controlNbr;
    }
}
