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

	private int totalPageNumber = 0; // �`����
	private int pageSize = 10; // �C�����
	private int currentPage = 1; // �ثe�Ҧb����

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

		// �Ȥ�ݴ��檺�ާ@����
		String action = request.getParameter("action");

		if ("reg".equals(action)) {
			// ------�Τ���U
			String userid = request.getParameter("userid");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			String birthday = request.getParameter("birthday");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");

			// sever������
			List<String> errors = new ArrayList<>();
			if (userid == null || userid.equals("")) {
				errors.add("�Τ�b�����ର��!");
			}
			if (name == null || name.equals("")) {
				errors.add("�Τ�W�٤��ର��!");
			}
			if (password == null || password2 == null || !password.equals(password2)) {
				errors.add("�⦸����J�K�X���P!");
			}

			String pattern = "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))$";
			if (!Pattern.matches(pattern, birthday)) {
				errors.add("�X�ͤ���榡�L��!");
			}

			if (errors.size() > 0) { // ���ҥ���
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("customer_reg.jsp").forward(request, response);

			} else { // ���Ҧ��\
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

				// --���U--
				try {
					// ���U���\
					customerService.register(customer);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} catch (ServiceException e) {
					// ���Τ�id�w���U
					errors.add("���Τ�id�w���U�I");
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
				}
			}

		} else if ("login".equals(action)) {
			// -------�Τ�n�J------
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");

			Customer customer = new Customer();
			customer.setId(userid);
			customer.setPassword(password);

			if (customerService.login(customer)) { // �n�J���\
				HttpSession session = request.getSession();
				session.setAttribute("customer", customer);
				request.getRequestDispatcher("main.jsp").forward(request, response);

			} else { // �n�J����
				List<String> errors = new ArrayList<>();
				errors.add("�b���αK�X�����T�I");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("login.jsp").forward(request, response);

			}
		} else if ("list".equals(action)) {
			// -------�ӫ~�C��------
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
			// --------�ӫ~�C�����--------
			String page = request.getParameter("page");

			if (page.equals("prev")) { // �V�W�@��
				currentPage--;
				if (currentPage < 1) {
					currentPage = 1;
				}
			} else if (page.equals("next")) { // �U�@��
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
			// -----------�ӫ~�ԲӤ��e------------
			String goodsid = request.getParameter("id");
			Goods goods = goodsService.queryDetail(new Long(goodsid));

			request.setAttribute("goods", goods);
			request.getRequestDispatcher("goods_detail.jsp").forward(request, response);
		} else if ("add".equals(action)) {
			// -------�K�[�ʪ���------
			Long goodsid = new Long(request.getParameter("id"));
			String goodsname = request.getParameter("name");
			Float price = new Float(request.getParameter("price"));

			// �ʪ��� = list�� map ,�C�@��map �N��C�@�Ӱӫ~
			// �qsession �����X
			List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");

			// �Ĥ@�����X��null
			if (cart == null) {
				cart = new ArrayList<Map<String, Object>>();
				request.getSession().setAttribute("cart", cart);
			}

			// cart �w�g�����ӫ~
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

			// �ʪ������S���Ӱӫ~
			if (flag == 0) {
				Map<String, Object> item = new HashMap<>();
				// item map [id name quantity price]
				item.put("goodsid", goodsid);
				item.put("goodsname", goodsname);
				item.put("quantity", 1);
				item.put("price", price);

				cart.add(item);
			}
			
			//��x�˵�
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
			//------�d���ʪ���------
			// �qsession �����X
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
			//--------------����q��------------------
			// �qsession �����X
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
			
			//����q��
			String ordersid = ordersService.submitOrders(cart);
			request.setAttribute("ordersid", ordersid);
			request.getRequestDispatcher("order_finish.jsp").forward(request, response);
			//�M���ʪ���
			request.getSession().removeAttribute("cart");
		}else if ("main".equals(action)) {
			//-----�i�J�D����--------
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}else if ("logout".equals(action)) {
			//---------�n�X------------
			//�M���ʪ���
			request.getSession().removeAttribute("cart");
			//�M���b�����
			request.getSession().removeAttribute("customer");
			//���^�n�J�e��
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if ("reg_init".equals(action)) {
			//---------���U�������i�J------------
			request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
			
			
		}
		
		
	}

}
