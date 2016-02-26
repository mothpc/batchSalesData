package com.adms.cs.dao.impl;

import org.springframework.stereotype.Repository;

import com.adms.common.dao.generic.impl.GenericDaoHibernate;
import com.adms.cs.dao.RejectMappingDao;
import com.adms.cs.entity.RejectMapping;

@Repository("rejectMappingDao")
public class RejectMappingDaoImpl extends GenericDaoHibernate<RejectMapping, Long> implements RejectMappingDao {
	
	public RejectMappingDaoImpl() {
		super(RejectMapping.class);
	}
}
