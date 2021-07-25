package com.web.store.service;

import java.util.List;

import com.web.store.domain.Goods;

public interface GoodsService {

		List<Goods> queryAll();
		
		List<Goods> queryByStartEnd(int start, int end);
		
		Goods queryDetail(long goodsid);
}
