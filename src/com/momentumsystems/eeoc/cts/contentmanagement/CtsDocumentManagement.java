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
package com.momentumsystems.eeoc.cts.contentmanagement;

import com.momentumsystems.contentmanagement.DocumentManagement;
import com.momentumsystems.livelink.LivelinkException;
import com.momentumsystems.eeoc.cts.web.transferObj.HoldingBinDocument;

import java.io.File;
import java.util.List;

/**
 *
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $   $Date: 2009-10-29 15:54:36 $
 */

public interface CtsDocumentManagement extends DocumentManagement
{
    public Integer createYyyymmFolders(String folderName) throws Exception;

    public List<HoldingBinDocument> getHoldingBinFiles();
    
    public String addDncLocalFile(int parentFolderId, String newFileName, File localFile)
        throws LivelinkException;

    public String addDncHoldingBinFile(int parentFolderId, String newFileName,
                                       String holdingBinFileId, boolean toCopy)
        throws LivelinkException;

}
