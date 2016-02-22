package com.adms.batch.sales.service;

import java.util.List;

import com.adms.batch.sales.domain.DdopMapping2ndResult;

public interface DdopMapping2ndResultService {

	public List<DdopMapping2ndResult> findDdopMapping2ndResultAll()
			throws Exception;

	public DdopMapping2ndResult findDdopMapping2ndResultById(Long id)
			throws Exception;

	public DdopMapping2ndResult findDdopMapping2ndResultByxReference(String xReference)
			throws Exception;

	public List<DdopMapping2ndResult> findDdopMapping2ndResultByExample(DdopMapping2ndResult ddopMapping2ndResult)
			throws Exception;

	public List<DdopMapping2ndResult> searchDdopMapping2ndResultByExample(DdopMapping2ndResult ddopMapping2ndResult)
			throws Exception;

	public DdopMapping2ndResult addDdopMapping2ndResult(DdopMapping2ndResult ddopMapping2ndResult, String batchId)
			throws Exception;

	public DdopMapping2ndResult updateDdopMapping2ndResult(DdopMapping2ndResult ddopMapping2ndResult, String batchId)
			throws Exception;

	public boolean deleteDdopMapping2ndResult(DdopMapping2ndResult ddopMapping2ndResult)
			throws Exception;

}
