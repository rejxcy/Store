package testSrc;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.web.store.domain.Goods;
import com.web.store.service.GoodsService;
import com.web.store.service.imp.GoodsServiceImp;

class GoodsServiceImpTest {
	
	GoodsService goodsService;

	@BeforeEach
	void setUp() throws Exception {
		goodsService = new GoodsServiceImp();
	}

	@AfterEach
	void tearDown() throws Exception {
		goodsService = null;
	}

	@Test
	void testQueryAll() {
		
		List<Goods> list = goodsService.queryAll();
		assertEquals(34, list.size());
		
		Goods goods = list.get(0);     		//java=zero  sql=1
		assertEquals(1L,goods.getId());		//�_��sql�����ƾ�
		assertEquals("����(DELL)���N3470���į�ӥο줽�i���q�����", goods.getName());
		assertEquals(3399, goods.getPrice());
		assertEquals("����(DELL)���N3470���į�ӥο줽�i���q�����(�K�Ni3-8100 8G 1T �|�~�W�� ���u�乫 FHD�e��)21.5�^�o ", goods.getDescription());
		assertEquals("5ae00211N25afad2c.jpg", goods.getImage());
		assertEquals("",goods.getBrand());
		assertEquals("",goods.getCpuBrand());
		assertEquals("",goods.getCardModel());
		assertEquals("",goods.getMemoryCapacity());
		assertEquals("",goods.getHdCapacity());
		assertEquals("",goods.getDisplaysize());
		
	}

	@Test
	void testQueryByStartEnd() {
		
		List<Goods> list = goodsService.queryByStartEnd(0, 10);
		assertEquals(10, list.size());
		
		Goods goods = list.get(0);     		//java=zero  sql=1
		assertEquals(1L,goods.getId());		//�_��sql�����ƾ�
		assertEquals("����(DELL)���N3470���į�ӥο줽�i���q�����", goods.getName());
		assertEquals(3399, goods.getPrice());
		assertEquals("����(DELL)���N3470���į�ӥο줽�i���q�����(�K�Ni3-8100 8G 1T �|�~�W�� ���u�乫 FHD�e��)21.5�^�o ", goods.getDescription());
		assertEquals("5ae00211N25afad2c.jpg", goods.getImage());
		assertEquals("",goods.getBrand());
		assertEquals("",goods.getCpuBrand());
		assertEquals("",goods.getCardModel());
		assertEquals("",goods.getMemoryCapacity());
		assertEquals("",goods.getHdCapacity());
		assertEquals("",goods.getDisplaysize());
		
	}

	@Test
	void testQueryDetail() {
		
		Goods goods = goodsService.queryDetail(1);
		assertNotNull(goods);
		
		assertEquals(1L,goods.getId());		//�_��sql�����ƾ�
		assertEquals("����(DELL)���N3470���į�ӥο줽�i���q�����", goods.getName());
		assertEquals(3399, goods.getPrice());
		assertEquals("����(DELL)���N3470���į�ӥο줽�i���q�����(�K�Ni3-8100 8G 1T �|�~�W�� ���u�乫 FHD�e��)21.5�^�o ", goods.getDescription());
		assertEquals("5ae00211N25afad2c.jpg", goods.getImage());
		assertEquals("",goods.getBrand());
		assertEquals("",goods.getCpuBrand());
		assertEquals("",goods.getCardModel());
		assertEquals("",goods.getMemoryCapacity());
		assertEquals("",goods.getHdCapacity());
		assertEquals("",goods.getDisplaysize());
		
	}

}
