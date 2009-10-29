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

import com.momentumsystems.util.system.SystemInfo;

/**
 *
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $   $Date: 2009-10-29 15:54:36 $
 */

public interface CtsSystemInfo extends SystemInfo {

    public int getDncFolderId();

    public int getDncHoldingBinId();

    public int getDncWorkflowId();

    public String getDateTimeFormatString();

    public void setDateTimeFormatString( String dateTimeFormatString );

    public String getDateFormatString();

    public void setDateFormatString( String dateFormatString );

    public int getMaxResults();

    public void setMaxResults( int maxResults );

    public String getServerUrl();

    public void setServerUrl( String serverUrl );

    public String getReportUrl();

    public void setReportUrl( String reportUrl );

    public int getLitFolderId();

    public void setLitFolderId( int litFolderId );

    public int getNonLitFolderId();

    public void setNonLitFolderId( int nonLitFolderId );

    public int getSubFolderId();

    public void setSubFolderId( int subFolderId );

    public int getAcFolderId();

    public void setAcFolderId( int acFolderId );

    public int getOfoFolderId();

    public void setOfoFolderId( int ofoFolderId );

    public int getCcFolderId();

    public void setCcFolderId( int ccFolderId );

    public int getCmFolderId();

    public void setCmFolderId( int cmFolderId );

    public int getEsLITholdingBinId();

    public void setEsLITholdingBinId( int esLITholdingBinId );

    public int getEsNLTholdingBinId();

    public void setEsNLTholdingBinId( int esNLTholdingBinId );

    public int getOgcLITholdingBinId();

    public void setOgcLITholdingBinId( int ogcLITholdingBinId );

    public int getOgcNLTholdingBinId();

    public void setOgcNLTholdingBinId( int ogcNLTholdingBinId );

    public String getWorkflowManagementUser();

    public void setWorkflowManagementUser( String workflowManagementUser );

    public int getNvsWorkflowMapId();

    public void setNvsWorkflowMapId( int nvsWorkflowMapId );

    public int getNnhWorkflowMapId();

    public void setNnhWorkflowMapId( int nnhWorkflowMapId );

    public int getHoldSheetTemplateIdAc();

    public void setHoldSheetTemplateIdAc( int holdSheetTemplateIdAc );

    public int getHoldSheetTemplateIdOfo();

    public void setHoldSheetTemplateIdOfo( int holdSheetTemplateIdOfo );

    public int getHoldSheetTemplateIdSub();

    public void setHoldSheetTemplateIdSub( int holdSheetTemplateIdSub );
    
    public int getCcTemplateId();

    public void setCcTemplateId( int commissionerChargeTemplateId );

    public int getCcApproveTemplateId();

    public void setCcApproveTemplateId( int ccApproveTemplateId );

    public int getOfoTemplateId();

    public void setOfoTemplateId( int ofoTemplateId );

    public int getSubTemplateId();

    public void setSubTemplateId( int subTemplateId );
}
