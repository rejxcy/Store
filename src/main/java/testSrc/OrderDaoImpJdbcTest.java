package testSrc;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.web.store.dao.OrderDao;
import com.web.store.dao.imp.OrderDaoImpJdbc;
import com.web.store.domain.Orders;

class OrderDaoImpJdbcTest {
	
	OrderDao dao;

	@BeforeEach
	void setUp() throws Exception {
		dao =new OrderDaoImpJdbc();
	}

	@AfterEach
	void tearDown() throws Exception {
		dao = null;
	}

	@Test
	void testFindByPk() {
		
		Orders orders = dao.findByPk("100");
		assertNotNull(orders);
		assertEquals("100", orders.getId());
		assertEquals(20210707L, orders.getOrdersDate().getTime());
		assertEquals(1, orders.getStatus());
		assertEquals(9000, orders.getTotal());
	}

	@Test
	void testFindAll() {
		
		List<Orders> list = dao.findAll();
		assertEquals(2, list.size());
		
		Orders orders = list.get(1);
		assertNotNull(orders);
		assertEquals("200", orders.getId());
		assertEquals(20210716L, orders.getOrdersDate().getTime());
		assertEquals(0, orders.getStatus());
		assertEquals(2500, orders.getTotal());
		
	}

	@Test
	void testCreate() {
		
		Orders orders = new Orders();
		orders.setId("50");
		orders.setOrderDate(new Date(20210714L));
		orders.setStatus(1);
		orders.setTotal(3500.0);
		dao.create(orders);
		
		Orders orders1 = dao.findByPk("50");
		assertNotNull(orders1);
		assertEquals("50", orders1.getId());
		assertEquals(20210714L, orders1.getOrdersDate().getTime());
		assertEquals(1, orders1.getStatus());
		assertEquals(3500.0, orders1.getTotal());
	}

	@Test
	void testModify() {
		
		Orders orders = new Orders();
		orders.setId("50");
		orders.setOrderDate(new Date(20210614L));
		orders.setStatus(0);
		orders.setTotal(5500.0);
		
		dao.modify(orders);
		
		Orders orders1 = dao.findByPk("50");
		assertNotNull(orders1);
		assertEquals("50", orders1.getId());
		assertEquals(20210614L, orders1.getOrdersDate().getTime());
		assertEquals(0, orders1.getStatus());
		assertEquals(5500.0, orders1.getTotal());
		
		
	}

	@Test
	void testRemove() {
		dao.remove("50");
		Orders orders1 = dao.findByPk("50");
		assertNotNull(orders1);
	}

}
