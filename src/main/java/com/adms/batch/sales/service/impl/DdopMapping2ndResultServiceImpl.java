package com.adms.batch.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.batch.sales.dao.DdopMapping2ndResultDao;
import com.adms.batch.sales.domain.DdopMapping2ndResult;
import com.adms.batch.sales.service.DdopMapping2ndResultService;

@Service("ddopMapping2ndResultService")
@Transactional
public class DdopMapping2ndResultServiceImpl implements DdopMapping2ndResultService {

	@Autowired
	private DdopMapping2ndResultDao ddopMapping2ndResultDao;

	public void setDdopMapping2ndResultDao(DdopMapping2ndResultDao ddopMapping2ndResultDao)
	{
		this.ddopMapping2ndResultDao = ddopMapping2ndResultDao;
	}

	public List<DdopMapping2ndResult> findDdopMapping2ndResultAll()
			throws Exception
	{
		return this.ddopMapping2ndResultDao.findAll();
	}

	public DdopMapping2ndResult findDdopMapping2ndResultById(Long id)
			throws Exception
	{
		return this.ddopMapping2ndResultDao.find(id);
	}

	public DdopMapping2ndResult findDdopMapping2ndResultByxReference(String xReference)
			throws Exception
	{
		List<DdopMapping2ndResult> ddopMapping2ndResultList = this.ddopMapping2ndResultDao.findByNamedQuery("findDdopMapping2ndResultByxReference", xReference);

		return ddopMapping2ndResultList.size() > 0 ? ddopMapping2ndResultList.get(0) : null;
	}

	public List<DdopMapping2ndResult> findDdopMapping2ndResultByExample(DdopMapping2ndResult ddopMapping2ndResult)
			throws Exception
	{
		return this.ddopMapping2ndResultDao.findByExamplePaging(ddopMapping2ndResult, null);
	}

	public List<DdopMapping2ndResult> searchDdopMapping2ndResultByExample(DdopMapping2ndResult ddopMapping2ndResult)
			throws Exception
	{
		return this.ddopMapping2ndResultDao.searchByExamplePaging(ddopMapping2ndResult, null);
	}

	public DdopMapping2ndResult addDdopMapping2ndResult(DdopMapping2ndResult ddopMapping2ndResult, String batchId)
			throws Exception
	{
		return this.ddopMapping2ndResultDao.save(ddopMapping2ndResult);
	}

	public DdopMapping2ndResult updateDdopMapping2ndResult(DdopMapping2ndResult ddopMapping2ndResult, String batchId)
			throws Exception
	{
		return this.ddopMapping2ndResultDao.save(ddopMapping2ndResult);
	}

	public boolean deleteDdopMapping2ndResult(DdopMapping2ndResult ddopMapping2ndResult)
			throws Exception
	{
		return this.ddopMapping2ndResultDao.delete(ddopMapping2ndResult.getId());
	}

}
