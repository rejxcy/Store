package testSrc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.web.store.dao.CustomerDao;
import com.web.store.dao.imp.CustomerDaoImpJdbc;
import com.web.store.domain.Customer;

class CustomerDaoImpJdbcTest {
	
	CustomerDao dao;

	@BeforeEach
	void setUp() {
		dao = new CustomerDaoImpJdbc();
	}
	
	@AfterEach
	void tearDown() {
		dao = null;
	}

	@Test
	void testFindByPk() {
		Customer customer = dao.findByPk("tony");
		
		assertNotNull(customer);
		assertEquals("tony", customer.getId());
		assertEquals("test1", customer.getName());
		assertEquals("123", customer.getPassword());
		assertEquals("苗栗", customer.getAddress());
		assertEquals("0926992", customer.getPhone());
		assertEquals(19980903L, customer.getBirthday().getTime());
	}

	@Test
	void testFindAll() {
		List<Customer> list = dao.findAll();
		assertEquals(list.size(), 1);
		
	}

	@Test
	void testCreate() {
		Customer customer = new Customer();
		customer.setId("rejxcy");
		customer.setName("測試伊");
		customer.setPassword("123");
		customer.setAddress("桃園");
		customer.setPhone("0926992666");
		customer.setBirthday(new Date(19991231L));
		
		dao.create(customer);
		//查詢有沒有創建成功
			Customer customer1 = dao.findByPk("rejxcy");
			assertEquals("rejxcy", customer1.getId());
			assertEquals("測試伊", customer1.getName());
			assertEquals("123", customer1.getPassword());
			assertEquals("桃園", customer1.getAddress());
			assertEquals("0926992666", customer1.getPhone());
			assertEquals(19991231L, customer1.getBirthday().getTime());
	}

	@Test
	void testModify() {
		
		Customer customer = new Customer();
		customer.setId("rejxcy");
		customer.setName("測試二");
		customer.setPassword("1234");
		customer.setAddress("苗栗國");
		customer.setPhone("0926992666");
		customer.setBirthday(new Date(19991231L));
		
		dao.modify(customer);
		//查詢有沒有修改成功
			Customer customer1 = dao.findByPk("rejxcy");
			assertEquals("rejxcy", customer1.getId());
			assertEquals("測試二", customer1.getName());
			assertEquals("1234", customer1.getPassword());
			assertEquals("苗栗國", customer1.getAddress());
			assertEquals("0926992666", customer1.getPhone());
			assertEquals(19991231L, customer1.getBirthday().getTime());
		
	}

	@Test
	void testRemove() {
		dao.remove("rejxcy");
		//查詢有沒有刪除成功
		Customer customer = dao.findByPk("rejxcy");
		assertNull(customer);
	}
}
