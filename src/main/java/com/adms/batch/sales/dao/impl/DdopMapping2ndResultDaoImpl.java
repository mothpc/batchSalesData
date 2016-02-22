package com.adms.batch.sales.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.batch.sales.dao.DdopMapping2ndResultDao;
import com.adms.batch.sales.domain.DdopMapping2ndResult;
import com.adms.common.dao.generic.impl.GenericDaoHibernate;

@Repository("ddopMapping2ndResultDao")
public class DdopMapping2ndResultDaoImpl extends GenericDaoHibernate<DdopMapping2ndResult, Long> implements DdopMapping2ndResultDao {

	public DdopMapping2ndResultDaoImpl() {
		super(DdopMapping2ndResult.class);
	}

}
