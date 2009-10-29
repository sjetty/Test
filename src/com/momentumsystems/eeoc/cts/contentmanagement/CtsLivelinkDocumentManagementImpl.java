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
 */

package com.momentumsystems.eeoc.cts.contentmanagement;

import com.momentumsystems.eeoc.cts.util.system.CtsSystemInfoImpl;
import com.momentumsystems.eeoc.cts.web.transferObj.HoldingBinDocument;
import com.momentumsystems.livelink.*;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 *
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $   $Date: 2009-10-29 15:54:36 $
 */

public class CtsLivelinkDocumentManagementImpl extends AbstractLivelinkDocumentManagement
		implements CtsDocumentManagement {

    protected FolderTool folderTool;
    protected NodeTool nodeTool;

    public List<HoldingBinDocument> getHoldingBinFiles()
    {
        CtsSystemInfoImpl ctsSystemInfo = ( CtsSystemInfoImpl ) systemInfo;
        int holdingBinId = ctsSystemInfo.getDncHoldingBinId();
        if (holdingBinId == 0)
            return null;

        List<NodeInformation> documents = getDocuments(holdingBinId);
        List<HoldingBinDocument> holdingBinDocuments = convertNodeInfoListToHoldingBinDocList( documents, false);
        return holdingBinDocuments;
    }
    
    public Integer createYyyymmFolders(String folderName) throws Exception
    {
        CtsSystemInfoImpl ctsSystemInfo = ( CtsSystemInfoImpl ) systemInfo;
        int parentFolderId = ctsSystemInfo.getDncFolderId();

        //Find yyyymm folder
        NodeInformation folderNode = findOrCreateSubFolder(parentFolderId, folderName);
        return folderNode.getObjectID();
    }

    public String addDncLocalFile(int parentFolderId, String newFileName, File localFile)
        throws LivelinkException
    {
        User adminUser = getAdminUser();

		ObjectReference folder = new ObjectReference();
		folder.setVolumeID(0);
		folder.setObjectID(parentFolderId);

        NodeInformation docInfo = folderTool.addDocument(folder, adminUser, newFileName, localFile.getPath(), false);
        return docInfo.getName();
    }

    public String addDncHoldingBinFile(int parentFolderId, String newFileName, String holdingBinFileId, boolean toCopy)
        throws LivelinkException
    {
        User adminUser = getAdminUser();

        ObjectReference folder = new ObjectReference();
		folder.setVolumeID(0);
		folder.setObjectID(parentFolderId);

        ObjectReference holdingBinFile = new ObjectReference();
        holdingBinFile.setVolumeID(0);
        holdingBinFile.setObjectID(Integer.parseInt(holdingBinFileId));

        NodeInformation docInfo;
        if (toCopy)
            docInfo = nodeTool.copy(holdingBinFile, folder, newFileName, adminUser);
        else {
            docInfo = nodeTool.move(holdingBinFile, folder, adminUser);
            docInfo = nodeTool.update(docInfo, null, newFileName, null, null, null);
        }
        return docInfo.getName();
    }

    public List<NodeInformation> getDocuments(int folderId)
    {
        List<NodeInformation> documents = new ArrayList<NodeInformation>();
        try {
            documents = folderTool.listFolderDocuments( new ObjectReference(0, folderId));
        } catch (Exception e)
        {
            log.error( e);
            log.error( "Unable to get documents from folder " + folderId);
        }
        return documents;
    }

    private List<HoldingBinDocument> convertNodeInfoListToHoldingBinDocList( List<NodeInformation> documents, boolean toCopy) {
        List<HoldingBinDocument> holdingBinDocuments = new ArrayList<HoldingBinDocument>();
        for (NodeInformation doc: documents)
        {
            HoldingBinDocument holdingBinDoc = new HoldingBinDocument();
            holdingBinDoc.setFileName( doc.getName());
            holdingBinDoc.setDateCreated( doc.getCreateDate());
            holdingBinDoc.setId( Integer.toString(doc.getObjectID()));
            holdingBinDoc.setFetchUrl(systemInfo.getBaseUrl() + "/" + doc.getObjectID() + "/" +
		                  doc.getName() + systemInfo.getFetchUrl() + doc.getObjectID());
            holdingBinDoc.setToCopy( toCopy);
            holdingBinDocuments.add( holdingBinDoc);
        }
        return holdingBinDocuments;
    }

    private NodeInformation findOrCreateSubFolder(int parentFolderId, String subFolderName)
        throws LivelinkException
    {
        NodeInformation nodeInfo;
        ObjectReference parentObject = new ObjectReference(0,parentFolderId);
        User adminUser = getAdminUser();
        Map permissionMap = null;
        nodeInfo = folderTool.getSubFolder(parentObject, subFolderName);
        if (nodeInfo == null)
            nodeInfo = folderTool.createFolder(parentObject,adminUser, subFolderName, permissionMap, Permission.PERM_ABSOLUTE);
        return nodeInfo;
    }

    public User getAdminUser()
    {
        CtsSystemInfoImpl ctsSystemInfo = ( CtsSystemInfoImpl ) systemInfo;

        com.momentumsystems.livelink.User adminUser = new com.momentumsystems.livelink.User();
		try	{
            adminUser = (com.momentumsystems.livelink.User)sessionTool.getFastIdentity(ctsSystemInfo.getContentManagementUser());
		}
		catch (Exception e) {
			log.error("Failed to get content management admin user id.", e);
		}
        return adminUser;
    }

    public FolderTool getFolderTool() {
        return folderTool;
    }

    public void setFolderTool(FolderTool folderTool) {
        this.folderTool = folderTool;
    }

    public NodeTool getNodeTool() {
        return nodeTool;
    }

    public void setNodeTool(NodeTool nodeTool) {
        this.nodeTool = nodeTool;
    }
}
