package com.web.store.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.store.domain.Customer;
import com.web.store.domain.Goods;
import com.web.store.service.CustomerService;
import com.web.store.service.GoodsService;
import com.web.store.service.OrdersService;
import com.web.store.service.ServiceException;
import com.web.store.service.imp.CustomerServiceImp;
import com.web.store.service.imp.GoodsServiceImp;
import com.web.store.service.imp.OrdersServiceImp;

//@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private CustomerService customerService = new CustomerServiceImp();
	private GoodsService goodsService = new GoodsServiceImp();
	private OrdersService ordersService = new OrdersServiceImp();

	private int totalPageNumber = 0; // 總頁數
	private int pageSize = 10; // 每頁行數
	private int currentPage = 1; // 目前所在頁數

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		pageSize = new Integer(config.getInitParameter("pageSize"));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 客戶端提交的操作物件
		String action = request.getParameter("action");

		if ("reg".equals(action)) {
			// ------用戶註冊
			String userid = request.getParameter("userid");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			String birthday = request.getParameter("birthday");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");

			// sever端驗證
			List<String> errors = new ArrayList<>();
			if (userid == null || userid.equals("")) {
				errors.add("用戶帳號不能為空!");
			}
			if (name == null || name.equals("")) {
				errors.add("用戶名稱不能為空!");
			}
			if (password == null || password2 == null || !password.equals(password2)) {
				errors.add("兩次的輸入密碼不同!");
			}

			String pattern = "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))$";
			if (!Pattern.matches(pattern, birthday)) {
				errors.add("出生日期格式無效!");
			}

			if (errors.size() > 0) { // 驗證失敗
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("customer_reg.jsp").forward(request, response);

			} else { // 驗證成功
				Customer customer = new Customer();
				customer.setId(userid);
				customer.setName(name);
				customer.setPassword(password);
				customer.setAddress(address);
				customer.setPhone(phone);
				try {
					Date d = dateFormat.parse(birthday);
					customer.setBirthday(d);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				// --註冊--
				try {
					// 註冊成功
					customerService.register(customer);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} catch (ServiceException e) {
					// 此用戶id已註冊
					errors.add("此用戶id已註冊！");
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
				}
			}

		} else if ("login".equals(action)) {
			// -------用戶登入------
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");

			Customer customer = new Customer();
			customer.setId(userid);
			customer.setPassword(password);

			if (customerService.login(customer)) { // 登入成功
				HttpSession session = request.getSession();
				session.setAttribute("customer", customer);
				request.getRequestDispatcher("main.jsp").forward(request, response);

			} else { // 登入失敗
				List<String> errors = new ArrayList<>();
				errors.add("帳號或密碼不正確！");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("login.jsp").forward(request, response);

			}
		} else if ("list".equals(action)) {
			// -------商品列表------
			List<Goods> goodsList = goodsService.queryAll();

			if (goodsList.size() % pageSize == 0) {
				totalPageNumber = goodsList.size() / pageSize;
			} else {
				totalPageNumber = goodsList.size() / pageSize + 1;
			}

			request.setAttribute("totalPageNumber", totalPageNumber);
			request.setAttribute("currentPage", currentPage);

			int start = (currentPage - 1) * pageSize;
			int end = currentPage * pageSize;
			if (currentPage == totalPageNumber) {
				end = goodsList.size();
			}

			request.setAttribute("goodsList", goodsList.subList(start, end));
			request.getRequestDispatcher("goods_list.jsp").forward(request, response);

		} else if ("paging".equals(action)) {
			// --------商品列表分頁--------
			String page = request.getParameter("page");

			if (page.equals("prev")) { // 向上一頁
				currentPage--;
				if (currentPage < 1) {
					currentPage = 1;
				}
			} else if (page.equals("next")) { // 下一頁
				currentPage++;
				if (currentPage > totalPageNumber) {
					currentPage = totalPageNumber;
				}

			} else {
				currentPage = Integer.valueOf(page);
			}

			int start = (currentPage - 1) * pageSize;
			int end = currentPage * pageSize;

			List<Goods> goodsList = goodsService.queryByStartEnd(start, end);

			request.setAttribute("totalPageNumber", totalPageNumber);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("goodsList", goodsList);
			request.getRequestDispatcher("goods_list.jsp").forward(request, response);

		} else if ("detail".equals(action)) {
			// -----------商品詳細內容------------
			String goodsid = request.getParameter("id");
			Goods goods = goodsService.queryDetail(new Long(goodsid));

			request.setAttribute("goods", goods);
			request.getRequestDispatcher("goods_detail.jsp").forward(request, response);
		} else if ("add".equals(action)) {
			// -------添加購物車------
			Long goodsid = new Long(request.getParameter("id"));
			String goodsname = request.getParameter("name");
			Float price = new Float(request.getParameter("price"));

			// 購物車 = list的 map ,每一個map 代表每一個商品
			// 從session 中取出
			List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");

			// 第一次取出為null
			if (cart == null) {
				cart = new ArrayList<Map<String, Object>>();
				request.getSession().setAttribute("cart", cart);
			}

			// cart 已經有此商品
			int flag = 0;
			for (Map<String, Object> item : cart) {
				Long goodsid2 = (Long) item.get("goodsid");
				if (goodsid.equals(goodsid2)) {

					Integer quantity = (Integer) item.get("quantity");
					quantity++;
					item.put("quantity", quantity);

					flag++;
				}
			}

			// 購物車中沒有該商品
			if (flag == 0) {
				Map<String, Object> item = new HashMap<>();
				// item map [id name quantity price]
				item.put("goodsid", goodsid);
				item.put("goodsname", goodsname);
				item.put("quantity", 1);
				item.put("price", price);

				cart.add(item);
			}
			
			//後台檢視
			System.out.println(cart);
			String pagename = request.getParameter("pagename");

			if (pagename.equals("list")) {
				int start = (currentPage - 1) * pageSize;
				int end = currentPage * pageSize;

				List<Goods> goodsList = goodsService.queryByStartEnd(start, end);

				request.setAttribute("totalPageNumber", totalPageNumber);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("goodsList", goodsList);
				request.getRequestDispatcher("goods_list.jsp").forward(request, response);

			} else if (pagename.equals("detail")) {

				Goods goods = goodsService.queryDetail(new Long(goodsid));
				request.setAttribute("goods", goods);
				request.getRequestDispatcher("goods_detail.jsp").forward(request, response);
			}
			
			
		}else if ("cart".equals(action)) {
			//------查看購物車------
			// 從session 中取出
			List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");

			double total = 0.0;
			if (cart != null) {
				
				for (Map<String, Object> item : cart) {
					
					Integer quantity = (Integer) item.get("quantity");
					Float price = (Float) item.get("price");
					double subtotal = price * quantity;
					total += subtotal;
				}
			}
			request.setAttribute("total", total);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
			
		}else if ("sub_ord".equals(action)) {
			//--------------提交訂單------------------
			// 從session 中取出
			List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");

			for (Map<String, Object> item : cart) {
				Long goodsid = (Long) item.get("goodsid");
				String strquantity = request.getParameter("quantity_"+ goodsid);
				int quantity = 0;
				try {
					quantity = new Integer(strquantity);
				}catch(Exception e) {
				}
				item.put("quantity", quantity);
			}
			
			//提交訂單
			String ordersid = ordersService.submitOrders(cart);
			request.setAttribute("ordersid", ordersid);
			request.getRequestDispatcher("order_finish.jsp").forward(request, response);
			//清空購物車
			request.getSession().removeAttribute("cart");
		}else if ("main".equals(action)) {
			//-----進入主頁面--------
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}else if ("logout".equals(action)) {
			//---------登出------------
			//清空購物車
			request.getSession().removeAttribute("cart");
			//清除帳號資料
			request.getSession().removeAttribute("customer");
			//跳回登入畫面
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if ("reg_init".equals(action)) {
			//---------註冊頁面的進入------------
			request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
			
			
		}
		
		
	}

}
