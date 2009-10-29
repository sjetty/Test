/*
 * Copyright 2008 Momentum Systems, Inc.
 * All Rights Reserved
 * This software is the confidential and proprietary information
 * of Momentum Systems, Inc. ("Confidential Information").
 *
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with the author.
 *
 *
 */

package com.momentumsystems.eeoc.cts.util.system;

import com.momentumsystems.util.system.ApplicationSystemInfo;
import com.momentumsystems.livelink.User;

/**
 *
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $   $Date: 2009-10-29 15:54:36 $
 */

public class CtsSystemInfoImpl extends ApplicationSystemInfo implements CtsSystemInfo {

    private int dncFolderId;
    private int dncHoldingBinId;
    private int dncWorkflowId;
    private String dncUser;

    protected String dateTimeFormatString;
    protected String dateFormatString;
    private int maxResults = 100;
    private String reportUrl;
    private String serverUrl;
    private int litFolderId;
    private int nonLitFolderId;
    private int egFolderId;
    private int olFolderId;
    private int subFolderId;
    private int acFolderId;
    private int ofoFolderId;
    private int ccFolderId;
    private int cmFolderId;
    private int esLITholdingBinId;
    private int esNLTholdingBinId;
    private int ogcLITholdingBinId;
    private int ogcNLTholdingBinId;
    private String workflowManagementUser;
    private int nvsWorkflowMapId;
    private int nnhWorkflowMapId;
    private int ccWorkflowMapId;

    private int holdSheetTemplateIdAc;
    private int holdSheetTemplateIdOfo;
    private int holdSheetTemplateIdSub;
    private int ccTemplateId;
    private int ccApproveTemplateId;
    private int ofoTemplateId;
    private int subTemplateId;

    private String tempDirPath;

    public int getDncFolderId() {
        return dncFolderId;
    }

    public void setDncFolderId(int dncFolderId) {
        this.dncFolderId = dncFolderId;
    }

    public int getDncHoldingBinId() {
        return dncHoldingBinId;
    }

    public void setDncHoldingBinId(int dncHoldingBinId) {
        this.dncHoldingBinId = dncHoldingBinId;
    }

    public int getDncWorkflowId() {
        return dncWorkflowId;
    }

    public void setDncWorkflowId(int dncWorkflowId) {
        this.dncWorkflowId = dncWorkflowId;
    }

    public String getDncUser() {
        return dncUser;
    }

    public void setDncUser(String dncUser) {
        this.dncUser = dncUser;
    }

    public String getDateTimeFormatString() {
        return dateTimeFormatString;
    }

    public void setDateTimeFormatString( String dateTimeFormatString ) {
        this.dateTimeFormatString = dateTimeFormatString;
    }

    public String getDateFormatString() {
        return this.dateFormatString;
    }

    public void setDateFormatString( String dateFormatString ) {
        this.dateFormatString = dateFormatString;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults( int maxResults ) {
        this.maxResults = maxResults;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl( String serverUrl ) {
        this.serverUrl = serverUrl;
    }

    public void setReportUrl( String reportUrl ) {
        this.reportUrl = reportUrl;
    }

    public int getLitFolderId() {
        return litFolderId;
    }

    public void setLitFolderId( int litFolderId ) {
        this.litFolderId = litFolderId;
    }

    public int getNonLitFolderId() {
        return nonLitFolderId;
    }

    public void setNonLitFolderId( int nonLitFolderId ) {
        this.nonLitFolderId = nonLitFolderId;
    }

    public int getEgFolderId() {
        return egFolderId;
    }

    public void setEgFolderId( int egFolderId ) {
        this.egFolderId = egFolderId;
    }

    public int getOlFolderId() {
        return olFolderId;
    }

    public void setOlFolderId( int olFolderId ) {
        this.olFolderId = olFolderId;
    }

    public int getSubFolderId() {
        return subFolderId;
    }

    public void setSubFolderId( int subFolderId ) {
        this.subFolderId = subFolderId;
    }

    public int getAcFolderId() {
        return acFolderId;
    }

    public void setAcFolderId( int acFolderId ) {
        this.acFolderId = acFolderId;
    }

    public int getOfoFolderId() {
        return ofoFolderId;
    }

    public void setOfoFolderId( int ofoFolderId ) {
        this.ofoFolderId = ofoFolderId;
    }

    public int getCcFolderId() {
        return ccFolderId;
    }

    public void setCcFolderId( int ccFolderId ) {
        this.ccFolderId = ccFolderId;
    }

    public int getCmFolderId() {
        return cmFolderId;
    }

    public void setCmFolderId( int cmFolderId ) {
        this.cmFolderId = cmFolderId;
    }

    public int getEsLITholdingBinId() {
        return esLITholdingBinId;
    }

    public void setEsLITholdingBinId( int esLITholdingBinId ) {
        this.esLITholdingBinId = esLITholdingBinId;
    }

    public int getEsNLTholdingBinId() {
        return esNLTholdingBinId;
    }

    public void setEsNLTholdingBinId( int esNLTholdingBinId ) {
        this.esNLTholdingBinId = esNLTholdingBinId;
    }

    public int getOgcLITholdingBinId() {
        return ogcLITholdingBinId;
    }

    public void setOgcLITholdingBinId( int ogcLITholdingBinId ) {
        this.ogcLITholdingBinId = ogcLITholdingBinId;
    }

    public int getOgcNLTholdingBinId() {
        return ogcNLTholdingBinId;
    }

    public void setOgcNLTholdingBinId( int ogcNLTholdingBinId ) {
        this.ogcNLTholdingBinId = ogcNLTholdingBinId;
    }

    public String getWorkflowManagementUser() {
        return workflowManagementUser;
    }

    public void setWorkflowManagementUser( String workflowManagementUser ) {
        this.workflowManagementUser = workflowManagementUser;
    }

    public int getNvsWorkflowMapId() {
        return nvsWorkflowMapId;
    }

    public void setNvsWorkflowMapId( int nvsWorkflowMapId ) {
        this.nvsWorkflowMapId = nvsWorkflowMapId;
    }

    public int getNnhWorkflowMapId() {
        return nnhWorkflowMapId;
    }

    public void setNnhWorkflowMapId( int nnhWorkflowMapId ) {
        this.nnhWorkflowMapId = nnhWorkflowMapId;
    }

    public int getCcWorkflowMapId() {
        return ccWorkflowMapId;
    }

    public void setCcWorkflowMapId( int ccWorkflowMapId ) {
        this.ccWorkflowMapId = ccWorkflowMapId;
    }

    /**
     * set value from pageinformation.xml  file
     */
    public void setPageInformation() {
        //ignore it, not used in this framework
    }

    public int getHoldSheetTemplateIdAc() {
        return holdSheetTemplateIdAc;
    }

    public void setHoldSheetTemplateIdAc( int holdSheetTemplateIdAc ) {
        this.holdSheetTemplateIdAc = holdSheetTemplateIdAc;
    }

    public int getHoldSheetTemplateIdOfo() {
        return holdSheetTemplateIdOfo;
    }

    public void setHoldSheetTemplateIdOfo( int holdSheetTemplateIdOfo ) {
        this.holdSheetTemplateIdOfo = holdSheetTemplateIdOfo;
    }

    public int getHoldSheetTemplateIdSub() {
        return holdSheetTemplateIdSub;
    }

    public void setHoldSheetTemplateIdSub( int holdSheetTemplateIdSub ) {
        this.holdSheetTemplateIdSub = holdSheetTemplateIdSub;
    }

    public int getCcTemplateId() {
        return ccTemplateId;
    }

    public void setCcTemplateId( int ccTemplateId ) {
        this.ccTemplateId = ccTemplateId;
    }

    public int getCcApproveTemplateId() {
        return ccApproveTemplateId;
    }

    public void setCcApproveTemplateId( int ccApproveTemplateId ) {
        this.ccApproveTemplateId = ccApproveTemplateId;
    }

    public int getOfoTemplateId() {
        return ofoTemplateId;
    }

    public void setOfoTemplateId( int ofoTemplateId ) {
        this.ofoTemplateId = ofoTemplateId;
    }

    public int getSubTemplateId() {
        return subTemplateId;
    }

    public void setSubTemplateId( int subTemplateId ) {
        this.subTemplateId = subTemplateId;
    }

    public String getTempDirPath() {
        return tempDirPath;
    }

    public void setTempDirPath( String tempDirPath ) {
        this.tempDirPath = tempDirPath;
    }

    /**
     * set value from menu.xml file
     */
    public void setMenu() {
        //ignore it, not used in this framework
    }


    /**
     * set value from portal.xml  file
     */
    public void setPortal() {
        //ignore it, not used in this framework
    }
}
