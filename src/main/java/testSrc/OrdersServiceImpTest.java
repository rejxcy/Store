package testSrc;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.web.store.dao.OrderDao;
import com.web.store.dao.OrderLineItemDao;
import com.web.store.dao.imp.OrderDaoImpJdbc;
import com.web.store.dao.imp.OrderLineItemDaoImpJdbc;
import com.web.store.domain.OrderLineItem;
import com.web.store.domain.Orders;
import com.web.store.service.OrdersService;
import com.web.store.service.imp.OrdersServiceImp;

class OrdersServiceImpTest {

	OrdersService ordersService;

    OrderDao orderDao = new OrderDaoImpJdbc();
    OrderLineItemDao orderLineItemDao = new OrderLineItemDaoImpJdbc();

    @BeforeEach
    void setUp() {
    	ordersService = new OrdersServiceImp();
    }

    @AfterEach
    void tearDown() {
    	ordersService = null;
    }

    @Test
    void submitOrders() {

        List<Map<String, Object>> cart = new ArrayList<Map<String, Object>>();
        
        Map<String, Object> item1 = new HashMap<String, Object>();
        item1.put("goodsid", 3L);
        item1.put("quantity", 2);
        cart.add(item1);

        Map<String, Object> item2 = new HashMap<String, Object>();
        item2.put("goodsid", 1L);
        item2.put("quantity", 3);
        cart.add(item2);
        
        String ordersid = ordersService.submitOrders(cart);
        assertNotNull(ordersid);

        Orders orders = orderDao.findByPk(ordersid);
        assertNotNull(orders);
        assertEquals(1, orders.getStatus());

        double total = 3099 * 2 + 3399 * 3;
        assertEquals(total, orders.getTotal());


        List<OrderLineItem> list = orderLineItemDao.findAll();
        List<OrderLineItem> orderLineItemList = new ArrayList<OrderLineItem>();
        for (OrderLineItem lineItem : list) {
            if (lineItem.getOrders().getId().equals(ordersid)) {
                orderLineItemList.add(lineItem);

                if (lineItem.getGoods().getId() == 3L) {
                    assertEquals(2, lineItem.getQuantity());
                    assertEquals(3099 * 2, lineItem.getSubTotal());
                }

                if (lineItem.getGoods().getId() == 1L) {
                    assertEquals(3, lineItem.getQuantity());
                    assertEquals(3399 * 3, lineItem.getSubTotal());
                }
            }
        }

        assertEquals(2, orderLineItemList.size());
    }
}
