package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.FileImportDao;
import com.adms.batch.sales.domain.FileImport;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("fileImportDao")
public class FileImportDaoImpl extends GenericDaoHibernate<FileImport, Long> implements FileImportDao {

	public FileImportDaoImpl() {
		super(FileImport.class);
	}

}
