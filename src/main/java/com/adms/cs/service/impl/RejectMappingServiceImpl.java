package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.RejectMappingDao;
import com.adms.cs.entity.RejectMapping;
import com.adms.cs.service.RejectMappingService;

@Service("rejectMappingService")
@Transactional
public class RejectMappingServiceImpl implements RejectMappingService {

	@Autowired
	private RejectMappingDao customerDao;
	
	public RejectMappingServiceImpl() {
		
	}

	public void setRejectMappingDao(RejectMappingDao customerDao) {
		this.customerDao = customerDao;
	}
	
	public List<RejectMapping> findAll() throws Exception {
		return customerDao.findAll();
	}

	public RejectMapping add(RejectMapping example, String userLogin) throws Exception {
		return customerDao.save(example);
	}
	
	public RejectMapping update(RejectMapping example, String userLogin) throws Exception {
		return customerDao.save(example);
	}

	public RejectMapping find(Long id) throws Exception {
		return customerDao.find(id);
	}
	
	public List<RejectMapping> find(RejectMapping example) throws Exception {
		return customerDao.find(example);
	}
	
	public List<RejectMapping> findByHql(String hql, Object...vals) throws Exception {
		return customerDao.findByHQL(hql, vals);
	}

	public List<RejectMapping> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return customerDao.findByNamedQuery(namedQuery, vals);
	}
	
	public List<RejectMapping> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return customerDao.findByCriteria(detachedCriteria);
	}
	
}
