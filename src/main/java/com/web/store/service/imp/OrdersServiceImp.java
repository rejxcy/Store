package com.web.store.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.web.store.dao.GoodsDao;
import com.web.store.dao.OrderDao;
import com.web.store.dao.OrderLineItemDao;
import com.web.store.dao.imp.GoodsDaoImpJdbc;
import com.web.store.dao.imp.OrderDaoImpJdbc;
import com.web.store.dao.imp.OrderLineItemDaoImpJdbc;
import com.web.store.domain.Goods;
import com.web.store.domain.OrderLineItem;
import com.web.store.domain.Orders;
import com.web.store.service.OrdersService;

public class OrdersServiceImp implements OrdersService{
	
	GoodsDao goodsDao = new GoodsDaoImpJdbc();
    OrderDao orderDao = new OrderDaoImpJdbc();
    OrderLineItemDao orderLineItemDao = new OrderLineItemDaoImpJdbc();

	@Override
	public String submitOrders(List<Map<String, Object>> cart) {
		
		Orders orders = new Orders();
		Date date = new Date();
		
		//訂單id
		String ordersid = String.valueOf(date.getTime())
                + String.valueOf((int) (Math.random() * 100));
        orders.setId(ordersid);
        orders.setOrderDate(date);
        orders.setStatus(1);
        orders.setTotal(0.0f);
		
		//訂單訊息插入sql中
		orderDao.create(orders);
		//初始總金額
		double total = 0.0f;
		
		for (Map item : cart) {
			System.out.println(item);
			//item結構 [商品id, 數量]
			Long goodsid = (Long) item.get("goodsid");
            Integer quantity = (Integer) item.get("quantity");
            Goods goods = goodsDao.findByPk(goodsid);
            
			//總計
            double subtotal = quantity * goods.getPrice();
            total += subtotal;
            
			//訂單的詳細內容
            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setQuantity(quantity);
            lineItem.setGoods(goods);
            lineItem.setOrders(orders);
            lineItem.setSubTotal(subtotal);
			
            //將其插入到數據庫中
			orderLineItemDao.create(lineItem); 	
			
		}
		
		orders.setTotal(total);
		orderDao.modify(orders);
		
		return ordersid;
	}	
}
