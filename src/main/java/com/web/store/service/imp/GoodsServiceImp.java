package com.web.store.service.imp;

import java.util.List;

import com.web.store.dao.GoodsDao;
import com.web.store.dao.imp.GoodsDaoImpJdbc;
import com.web.store.domain.Goods;
import com.web.store.service.GoodsService;

public class GoodsServiceImp implements GoodsService{
	
	GoodsDao goodsDao = new GoodsDaoImpJdbc();

	@Override
	public List<Goods> queryAll() {
		return goodsDao.findAll();
	}

	@Override
	public List<Goods> queryByStartEnd(int start, int end) {
		return goodsDao.findStartEnd(start, end);
	}

	@Override
	public Goods queryDetail(long goodsid) {
		return goodsDao.findByPk(goodsid);
	}
	

}
