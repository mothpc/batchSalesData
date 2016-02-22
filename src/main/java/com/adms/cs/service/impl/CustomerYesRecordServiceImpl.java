package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.CustomerYesRecordDao;
import com.adms.cs.entity.CustomerYesRecord;
import com.adms.cs.service.CustomerYesRecordService;

@Service("customerYesRecordService")
@Transactional
public class CustomerYesRecordServiceImpl implements CustomerYesRecordService {

	@Autowired
	private CustomerYesRecordDao customerYesRecordDao;
	
	public CustomerYesRecordServiceImpl() {
		
	}

	public void setCustomerYesRecordDao(CustomerYesRecordDao customerYesRecordDao) {
		this.customerYesRecordDao = customerYesRecordDao;
	}
	
	
	public List<CustomerYesRecord> findAll() throws Exception {
		return customerYesRecordDao.findAll();
	}

	
	public CustomerYesRecord add(CustomerYesRecord example, String userLogin) throws Exception {
		return customerYesRecordDao.save(example);
	}
	
	
	public CustomerYesRecord update(CustomerYesRecord example, String userLogin) throws Exception {
		return customerYesRecordDao.save(example);
	}

	
	public CustomerYesRecord find(String referenceNo)
			throws Exception
	{
		CustomerYesRecord example = new CustomerYesRecord();
		example.setReferenceNo(referenceNo);
		List<CustomerYesRecord> list = find(example);
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}
	
	
	public List<CustomerYesRecord> find(CustomerYesRecord example) throws Exception {
		return customerYesRecordDao.find(example);
	}
	
	
	public List<CustomerYesRecord> findByHql(String hql, Object...vals) throws Exception {
		return customerYesRecordDao.findByHQL(hql, vals);
	}

	
	public List<CustomerYesRecord> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return customerYesRecordDao.findByNamedQuery(namedQuery, vals);
	}
	
	
	public List<CustomerYesRecord> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return customerYesRecordDao.findByCriteria(detachedCriteria);
	}
	
}
