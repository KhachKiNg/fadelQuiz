package com.fadel.demo.app.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.fadel.app.entities.User;
import com.fadel.demo.app.DAO.UserDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class UserDAOImpl implements UserDAO{

	private SessionFactory sessionFactory;
	 
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public List<User> list() {
		@SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listUser;
	}

}
