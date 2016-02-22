package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.FileImportDao;
import com.adms.batch.sales.domain.FileImport;
import com.adms.batch.sales.service.FileImportService;

@Service("fileImportService")
@Transactional
public class FileImportServiceImpl implements FileImportService {

	@Autowired
	private FileImportDao fileImportDao;

	public void setFileImportDao(FileImportDao fileImportDao)
	{
		this.fileImportDao = fileImportDao;
	}

	public List<FileImport> findFileImportAll()
			throws Exception
	{
		return this.fileImportDao.findAll();
	}

	public FileImport findFileImportById(Long id)
			throws Exception
	{
		return this.fileImportDao.find(id);
	}

	public FileImport findFileImportByDocumentName(String documentName)
			throws Exception
	{
		FileImport example = new FileImport();
		example.setDocumentName(documentName);

		List<FileImport> fileImportList = this.fileImportDao.find(example);

		if (fileImportList.size() == 0)
		{
			return null;
		}

		if (fileImportList.size() > 1)
		{
			throw new Exception("more that 1 record found for DocumentName[" + documentName + "]");
		}

		return fileImportList.get(0);
	}

	public List<FileImport> findFileImportByExample(FileImport fileImport)
			throws Exception
	{
		return this.fileImportDao.findByExamplePaging(fileImport, null);
	}

	public List<FileImport> searchFileImportByExample(FileImport fileImport)
			throws Exception
	{
		return this.fileImportDao.searchByExamplePaging(fileImport, null);
	}

	public FileImport addFileImport(FileImport fileImport, String batchId)
			throws Exception
	{
		return this.fileImportDao.save(fileImport);
	}

	public FileImport updateFileImport(FileImport fileImport, String batchId)
			throws Exception
	{
		return this.fileImportDao.save(fileImport);
	}

	public boolean deleteFileImport(FileImport fileImport)
			throws Exception
	{
		return this.fileImportDao.delete(fileImport.getId());
	}

}
