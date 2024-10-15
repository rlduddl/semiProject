package com.semiProject.service;

import java.util.List;

public interface CrudService<E> {
	List<E> selectList(E e);
	
	E selectOne(E e);
	
	void insert(E e);
	
	void update(E e);
	
	void delete(E e);
}
