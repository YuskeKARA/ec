package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			//userIdがその前に必要?
			// buy_idをセッションから取得?
			//			BuyDataBeans bdbll = (BuyDataBeans) session.getAttribute("bdbl");
			//			bdbll.setUserId((int) session.getAttribute("userId"));
			//			bdbll.setId((int) session.getAttribute();
			//			request.setAttribute("bdbll",  bdbll);

			//			int buyId = (int) session.getAttribute("buyId");
			//
			//
			//
			//			ItemDataBeans IDBl = (ItemDataBeans) session.getAttribute("IDBl");
			//			request.setAttribute("IDBl",  IDBl);
			//http://localhost:8080/EC/UserBuyHistoryDetail?buy_id=9
			//			ArrayList<ItemDataBeans> buyIDBList = BuyDetailDAO.getItemDataBeansListByBuyId(buyId);
			//			request.setAttribute("buyIDBList", buyIDBList);

			//			BuyDataBeans bdbll = (BuyDataBeans) session.getAttribute("bdbl");
			//			request.setAttribute("bdbll",  bdbll);

			//パスされたidをもとに情報を


			int buyId = Integer.parseInt(request.getParameter("buy_id"));

			BuyDataBeans bdb = BuyDAO.getBuyDataBeansByBuyId(buyId);
			request.setAttribute("bdb", bdb);

//			ItemDataBeans IDBl = (ItemDataBeans) session.getAttribute("IDBl");
//			request.setAttribute("IDBl", IDBl);

			ArrayList<ItemDataBeans> buyIDBList = BuyDetailDAO.getItemDataBeansListByBuyId(buyId);
			request.setAttribute("buyIDBList", buyIDBList);



			request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
