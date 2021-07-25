package testSrc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.web.store.dao.GoodsDao;
import com.web.store.dao.imp.GoodsDaoImpJdbc;
import com.web.store.domain.Goods;

class GoodsDaoImpJdbcTest {
	
	GoodsDao dao;

	@BeforeEach
	void setUp() throws Exception {
		dao = new GoodsDaoImpJdbc();
	}

	@AfterEach
	void tearDown() throws Exception {
		dao = null;
	}

	@Test
	void testFindByPk() {

		Goods goods = dao.findByPk(1L);
		assertNotNull(goods);
		assertEquals(1L,goods.getId());
		assertEquals("戴爾(DELL)成就3470高效能商用辦公檯式電腦整機", goods.getName());
		assertEquals(3399, goods.getPrice());
		assertEquals("戴爾(DELL)成就3470高效能商用辦公檯式電腦整機(八代i3-8100 8G 1T 四年上門 有線鍵鼠 FHD寬屏)21.5英寸 ", goods.getDescription());
		assertEquals("5ae00211N25afad2c.jpg", goods.getImage());
		assertEquals("",goods.getBrand());
		assertEquals("",goods.getCpuBrand());
		assertEquals("",goods.getCardModel());
		assertEquals("",goods.getMemoryCapacity());
		assertEquals("",goods.getHdCapacity());
		assertEquals("",goods.getDisplaysize());
		
		
	}

	@Test
	void testFindAll() {
		List<Goods> list = dao.findAll();
		assertEquals(list.size(),34);
		
		
		Goods goods = list.get(0);
		assertNotNull(goods);
		assertEquals(1L,goods.getId());
		assertEquals("戴爾(DELL)成就3470高效能商用辦公檯式電腦整機", goods.getName());
		assertEquals(3399, goods.getPrice());
		assertEquals("戴爾(DELL)成就3470高效能商用辦公檯式電腦整機(八代i3-8100 8G 1T 四年上門 有線鍵鼠 FHD寬屏)21.5英寸 ", goods.getDescription());
		assertEquals("5ae00211N25afad2c.jpg", goods.getImage());
		assertEquals("",goods.getBrand());
		assertEquals("",goods.getCpuBrand());
		assertEquals("",goods.getCardModel());
		assertEquals("",goods.getCpuType());
		assertEquals("",goods.getMemoryCapacity());
		assertEquals("",goods.getHdCapacity());
		assertEquals("",goods.getDisplaysize());
	}

	@Test
	void testFindStartEnd() {
		
		List<Goods> list = dao.findStartEnd(0, 10);
		assertEquals(list.size(),10);
		
		Goods goods = list.get(0);
		assertNotNull(goods);
		assertEquals(1L,goods.getId());
		assertEquals("戴爾(DELL)成就3470高效能商用辦公檯式電腦整機", goods.getName());
		assertEquals(3399, goods.getPrice());
		assertEquals("戴爾(DELL)成就3470高效能商用辦公檯式電腦整機(八代i3-8100 8G 1T 四年上門 有線鍵鼠 FHD寬屏)21.5英寸 ", goods.getDescription());
		assertEquals("5ae00211N25afad2c.jpg", goods.getImage());
		assertEquals("",goods.getBrand());
		assertEquals("",goods.getCpuBrand());
		assertEquals("",goods.getCardModel());
		assertEquals("",goods.getCpuType());
		assertEquals("",goods.getMemoryCapacity());
		assertEquals("",goods.getHdCapacity());
		assertEquals("",goods.getDisplaysize());
	}

	@Test
	void testCreate() {
		Goods goods = new Goods();
        goods.setId(9999);
        goods.setName("Apple Mac Mini");
        goods.setPrice(5000);
        goods.setDescription("Apple Mac Mini 2018年初");
        goods.setBrand("Apple");
        goods.setCpuBrand("Intel");
        goods.setCpuType("i5");
        goods.setMemoryCapacity("8G");
        goods.setHdCapacity("500G");
        goods.setCardModel("GTX 9x/7x");
        goods.setDisplaysize("無");
        goods.setImage("aaa.jpg");

        dao.create(goods);
        Goods goods1 = dao.findByPk(9999);
        assertNotNull(goods1);
        assertEquals(9999, goods1.getId());
        assertEquals("Apple Mac Mini", goods1.getName());
        assertEquals(5000, goods.getPrice());
        assertEquals("Apple Mac Mini 2018年初", goods.getDescription());
        assertEquals("aaa.jpg", goods1.getImage());
        assertEquals("Apple", goods1.getBrand());
        assertEquals("Intel", goods1.getCpuBrand());
        assertEquals("i5", goods1.getCpuType());
        assertEquals("GTX 9x/7x", goods1.getCardModel());
        assertEquals("8G", goods1.getMemoryCapacity());
        assertEquals("500G", goods1.getHdCapacity());
        assertEquals("無", goods1.getDisplaysize());

	}

	@Test
	void testModify() {
		
		Goods goods = new Goods();
        goods.setId(9999);
        goods.setName("Apple Mac pro");
        goods.setPrice(9000);
        goods.setDescription("Apple Mac pro 2020年初");
        goods.setBrand("Apple");
        goods.setCpuBrand("Apple");
        goods.setCpuType("M1");
        goods.setMemoryCapacity("16G");
        goods.setHdCapacity("500G");
        goods.setCardModel("GTX");
        goods.setDisplaysize("15吋");
        goods.setImage("aba.jpg");
        
        dao.modify(goods);
        
        Goods goods1 = dao.findByPk(9999);
        assertNotNull(goods1);
        assertEquals(9999, goods1.getId());
        assertEquals("Apple Mac pro", goods1.getName());
        assertEquals(9000, goods.getPrice());
        assertEquals("Apple Mac pro 2020年初", goods.getDescription());
        assertEquals("aba.jpg", goods1.getImage());
        assertEquals("Apple", goods1.getBrand());
        assertEquals("Apple", goods1.getCpuBrand());
        assertEquals("M1", goods1.getCpuType());
        assertEquals("GTX", goods1.getCardModel());
        assertEquals("16G", goods1.getMemoryCapacity());
        assertEquals("500G", goods1.getHdCapacity());
        assertEquals("15吋", goods1.getDisplaysize());
		
	}

	@Test
	void testRemove() {
		
		dao.remove(9999);
		
		Goods goods=dao.findByPk(9999);
		assertNull(goods);
		
	}

}
