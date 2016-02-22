package com.adms.cs.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adms.cs.dao.CustomerDao;
import com.adms.cs.entity.Customer;
import com.adms.cs.service.CustomerService;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	public CustomerServiceImpl() {
		
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	public List<Customer> findAll() throws Exception {
		return customerDao.findAll();
	}

	public Customer add(Customer example, String userLogin) throws Exception {
		return customerDao.save(example);
	}
	
	public Customer update(Customer example, String userLogin) throws Exception {
		return customerDao.save(example);
	}

	public Customer find(Long id) throws Exception {
		return customerDao.find(id);
	}

	public Customer find(String citizenId)
			throws Exception
	{
		Customer example = new Customer();
		example.setCitizenId(citizenId);
		example.setVisible("Y");
		List<Customer> list = find(example);
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}
	
	public List<Customer> find(Customer example) throws Exception {
		return customerDao.find(example);
	}
	
	public List<Customer> findByHql(String hql, Object...vals) throws Exception {
		return customerDao.findByHQL(hql, vals);
	}

	public List<Customer> findByNamedQuery(String namedQuery, Object...vals) throws Exception {
		return customerDao.findByNamedQuery(namedQuery, vals);
	}
	
	public List<Customer> findByCriteria(DetachedCriteria detachedCriteria) throws Exception {
		return customerDao.findByCriteria(detachedCriteria);
	}
	
}
