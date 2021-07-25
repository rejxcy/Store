package com.web.store.dao;

import java.util.List;

import com.web.store.domain.Goods;

public interface GoodsDao {
	
	Goods findByPk(long pk);

	List<Goods> findAll();
	
	/**
	 * ���Ѥ����d��
	 * @param start �}�l���� ���ޱq0�}�l
	 * @param end	�������� ���ޱq0�}�l
	 * @return  	�ӫ~�C��
	 */
	List<Goods> findStartEnd(int start, int end);

	void create(Goods goods);

	void modify(Goods goods);

	void remove(long pk);
	
	
}
