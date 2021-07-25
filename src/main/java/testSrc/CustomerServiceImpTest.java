package testSrc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.web.store.domain.Customer;
import com.web.store.service.CustomerService;
import com.web.store.service.ServiceException;
import com.web.store.service.imp.CustomerServiceImp;

class CustomerServiceImpTest {
	
	CustomerService customerService;

	@BeforeEach
	void setUp() throws Exception {
		customerService = new CustomerServiceImp();
	}

	@AfterEach
	void tearDown() throws Exception {
		customerService = null;
	}

	@Test
	@DisplayName("�n�J���\")
	void testLogin() {
		
		Customer customer = new Customer();
		customer.setId("tony");
		customer.setPassword("123");
		
		assertTrue(customerService.login(customer));
		assertNotNull(customer);
		assertEquals("tony", customer.getId());
		assertEquals("test1", customer.getName());
		assertEquals("123", customer.getPassword());
		assertEquals("�]��", customer.getAddress());
		assertEquals("0926992", customer.getPhone());
		assertEquals(19980903L, customer.getBirthday().getTime());
		
	}
	
	
	@Test
	@DisplayName("�n�J����")
	void testLogin2() {
		
		Customer customer = new Customer();
		customer.setId("tony");
		customer.setPassword("223");
		
		assertFalse(customerService.login(customer));
		
		
	}

	@Test
	@DisplayName("���U���\")
	void testRegister1() throws ServiceException {
		
		Customer customer = new Customer();
		customer.setId("rejxcy");
		customer.setName("���ե�");
		customer.setPassword("123");
		customer.setAddress("���");
		customer.setPhone("0926992666");
		customer.setBirthday(new Date(19991231L));
		
		customerService.register(customer);
		
		Customer customer1 = new Customer();
		customer1.setId("rejxcy");
		customer1.setPassword("123");
		
		customerService.login(customer1);
		assertNotNull(customer1);
		assertEquals("rejxcy", customer1.getId());
		assertEquals("���ե�", customer1.getName());
		assertEquals("123", customer1.getPassword());
		assertEquals("���", customer1.getAddress());
		assertEquals("0926992666", customer1.getPhone());
		assertEquals(19991231L, customer1.getBirthday().getTime());
		
	}
	
	@Test
	@DisplayName("���U����")
	void testRegister2() {
		
		Customer customer = new Customer();
		customer.setId("rejxcy");
		customer.setPassword("123");
		
		assertThrows(ServiceException.class, ()->{
			customerService.register(customer);
		});
		
		
	}

}
