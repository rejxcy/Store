package testSrc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.web.store.dao.OrderLineItemDao;
import com.web.store.dao.imp.OrderLineItemDaoImpJdbc;
import com.web.store.domain.Goods;
import com.web.store.domain.OrderLineItem;
import com.web.store.domain.Orders;

class OrderLineItemDaoJdbcTest {

	OrderLineItemDao dao;
	
	@BeforeEach
	void setUp() throws Exception {
		dao = new OrderLineItemDaoImpJdbc();
	}

	@AfterEach
	void tearDown() throws Exception {
		dao = null;
	}

	@Test
	void testFindByPk() {
		OrderLineItem lineItem = dao.findByPk(1);
		assertNotNull(lineItem);
		
		assertEquals(1, lineItem.getId());
 		assertEquals(3, lineItem.getGoods().getId());
		assertEquals("200", lineItem.getOrders().getId());
		assertEquals(3, lineItem.getQuantity());
		assertEquals(9000, lineItem.getSubTotal());
		
	}

	@Test
	void testFindAll() {
		
		List<OrderLineItem> list = dao.findAll();
		assertEquals(2, list.size());
		
		OrderLineItem lineItem = list.get(0);
		assertNotNull(lineItem);
		
		assertEquals(1, lineItem.getId());
 		assertEquals(3, lineItem.getGoods().getId());
		assertEquals("200", lineItem.getOrders().getId());
		assertEquals(3, lineItem.getQuantity());
		assertEquals(9000, lineItem.getSubTotal());
	}

	@Test
	void testCreate() {
		
		OrderLineItem lineItem = new OrderLineItem();
		lineItem.setQuantity(4);
		lineItem.setSubTotal(9200);
		
		Goods goods = new Goods();
		goods.setId(23);
		lineItem.setGoods(goods);
		
		Orders orders = new Orders();
		orders.setId("100");
		lineItem.setOrders(orders);
		//創建
		dao.create(lineItem);   	
		
		//核對有無創建成功
		OrderLineItem lineItem1 = dao.findByPk(4);    
		assertNotNull(lineItem);
		
		assertEquals(4, lineItem1.getId());
 		assertEquals(23, lineItem1.getGoods().getId());
		assertEquals("100", lineItem1.getOrders().getId());
		assertEquals(4, lineItem1.getQuantity());
		assertEquals(9200, lineItem1.getSubTotal());
		
	}

	@Test
	void testModify() {
		OrderLineItem lineItem = new OrderLineItem();
		lineItem.setId(5);
		lineItem.setQuantity(4);
		lineItem.setSubTotal(19500);
		
		Goods goods = new Goods();
		goods.setId(15);
		lineItem.setGoods(goods);
		
		Orders orders = new Orders();
		orders.setId("100");
		lineItem.setOrders(orders);
		//創建
		dao.modify(lineItem);
		
		//核對有無修改成功
		OrderLineItem lineItem1 = dao.findByPk(5);    
		assertNotNull(lineItem1);
		
		assertEquals(5, lineItem1.getId());
 		assertEquals(15, lineItem1.getGoods().getId());
		assertEquals("100", lineItem1.getOrders().getId());
		assertEquals(4, lineItem1.getQuantity());
		assertEquals(19500, lineItem1.getSubTotal());
	}

	@Test
	void testRemove() {
		dao.remove(8);
        OrderLineItem lineItem = dao.findByPk(8);
        assertNull(lineItem);
	}

}
