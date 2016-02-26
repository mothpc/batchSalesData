package com.adms.cs.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.adms.cs.entity.RejectMapping;

public interface RejectMappingService {

	public RejectMapping add(RejectMapping example, String userLogin) throws Exception;

	public RejectMapping update(RejectMapping example, String userLogin) throws Exception;

	public List<RejectMapping> findAll() throws Exception;
	
	public RejectMapping find(Long id) throws Exception;
	
	public List<RejectMapping> find(RejectMapping example) throws Exception;

	public List<RejectMapping> findByHql(String hql, Object...vals) throws Exception;

	public List<RejectMapping> findByNamedQuery(String namedQuery, Object...vals) throws Exception;

	public List<RejectMapping> findByCriteria(DetachedCriteria detachedCriteria) throws Exception;

}
