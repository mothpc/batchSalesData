package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.FileImport;

public interface FileImportService {

	public List<FileImport> findFileImportAll()
			throws Exception;

	public FileImport findFileImportById(Long id)
			throws Exception;

	public FileImport findFileImportByDocumentName(String documentName)
			throws Exception;

	public List<FileImport> findFileImportByExample(FileImport fileImport)
			throws Exception;

	public List<FileImport> searchFileImportByExample(FileImport fileImport)
			throws Exception;

	public FileImport addFileImport(FileImport fileImport, String batchId)
			throws Exception;

	public FileImport updateFileImport(FileImport fileImport, String batchId)
			throws Exception;

	public boolean deleteFileImport(FileImport fileImport)
			throws Exception;

}
