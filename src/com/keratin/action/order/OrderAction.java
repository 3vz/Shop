package com.keratin.action.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.keratin.action.BaseAction;
import com.keratin.model.OrderState;
import com.keratin.model.PageModel;
import com.keratin.model.order.Order;
import com.keratin.model.order.OrderItem;
import com.keratin.model.product.ProductInfo;
import com.keratin.util.StringUitl;
import com.opensymphony.xwork2.ModelDriven;
/**
 * ����Action
 * @author Li Yongqiang
 */
@Scope("prototype")
@Controller("orderAction")
public class OrderAction extends BaseAction implements ModelDriven<Order>{
	private static final long serialVersionUID = 1L;
	
	/**
	 * �¶���
	 */
	public String add() throws Exception {
		order.setName(getLoginCustomer().getUsername());
		order.setAddress(getLoginCustomer().getAddress());
		order.setMobile(getLoginCustomer().getMobile());
		return ADD;
	}
	/**
	 * ����ȷ��
	 * @return
	 * @throws Exception
	 */
	public String confirm() throws Exception {
		return "confirm";//���ض���ȷ��ҳ��
	}
	/**
	 * ���������浽���ݿ�
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		if(getLoginCustomer() != null){//����û��ѵ�¼
			order.setOrderId(StringUitl.createOrderId());// ���ö�����
			order.setCustomer(getLoginCustomer());// ���������û�
			Set<OrderItem> cart = getCart();// ��ȡ���ﳵ
			if(cart.isEmpty()){//�ж���Ŀ��Ϣ�Ƿ�Ϊ��
				return ERROR;//���ض�����Ϣ������ʾҳ��
			}
			// ���ν����¶������е���Ʒ����������
			for(OrderItem item : cart){//�������ﳵ�еĶ�����Ŀ��Ϣ
				Integer productId = item.getProductId();//��ȡ��ƷID
				ProductInfo product = productDao.load(productId);//װ����Ʒ����
				product.setSellCount(product.getSellCount() + item.getAmount());//������Ʒ��������
				productDao.update(product);//�޸���Ʒ��Ϣ
			}
			order.setOrderItems(cart);// ���ö�����
			order.setOrderState(OrderState.DELIVERED);// ���ö���״̬
			float totalPrice = 0f;// �����ܶ�ı���
			for (OrderItem orderItem : cart) {//�������ﳵ�еĶ�����Ŀ��Ϣ
				totalPrice += orderItem.getProductPrice() * orderItem.getAmount();//��Ʒ����*��Ʒ����
			}
			order.setTotalPrice(totalPrice);//���ö������ܼ۸�
			orderDao.save(order);//���涩����Ϣ
			session.remove("cart");// ��չ��ﳵ
		}
		return findByCustomer();//���������߶�����ѯ�ķ���
	}
	/**
	 * ��ѯ�����߶���
	 * @return String
	 * @throws Exception
	 */
	public String findByCustomer() throws Exception {
		if(getLoginCustomer() != null){//����û��ѵ�¼
			String where = "where customer.id = ?";//���û�id����Ϊ��ѯ����
			Object[] queryParams = {getLoginCustomer().getId()};//������������
			Map<String, String> orderby = new HashMap<String, String>(1);//����Map����
			orderby.put("createTime", "desc");//����������������ʽ
			pageModel = orderDao.find(where, queryParams, orderby , pageNo, pageSize);//ִ�в�ѯ����
		}
		return LIST;//���ض����б�ҳ��
	}
	
	
	/**
	 * ��ѯ����
	 * @return String
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map<String, String> orderby = new HashMap<String, String>(1);//����Map����
		orderby.put("createTime", "desc");//���ð�����ʱ�䵹������
		StringBuffer whereBuffer = new StringBuffer("");//�����ַ�������
		List<Object> params = new ArrayList<Object>();
		if(order.getOrderId() != null && order.getOrderId().length() > 0){//��������Ų�Ϊ��
			whereBuffer.append("orderId = ?");//�Զ�����Ϊ��ѯ����
			params.add(order.getOrderId());//���ò���
		}
		if(order.getOrderState() != null){//�������״̬��Ϊ��
			if(params.size() > 0){
				whereBuffer.append(" and ");//���Ӳ�ѯ����
			}
			whereBuffer.append("orderState = ?");//���ö���״̬Ϊ��ѯ����
			params.add(order.getOrderState());//���ò���
		}
		if(order.getCustomer() != null && order.getCustomer().getUsername() != null 
				&& order.getCustomer().getUsername().length() > 0){//�����Ա����Ϊ��
			if(params.size() > 0) whereBuffer.append(" and ");//���Ӳ�ѯ����
			whereBuffer.append("customer.username = ?");//���û�Ա��Ϊ��ѯ����
			params.add(order.getCustomer().getUsername());//���ò���
		}
		if(order.getName() != null && order.getName().length()>0){//����տ���������Ϊ��
			if(params.size() > 0) whereBuffer.append(" and ");//���Ӳ�ѯ����
			whereBuffer.append("name = ?");//�����տ�������Ϊ��ѯ����
			params.add(order.getName());//���ò���
		}
		//���whereBufferΪ�����ѯ����Ϊ�գ�������whereBufferΪ��ѯ����
		String where = whereBuffer.length()>0 ? "where "+whereBuffer.toString() : "";
		pageModel = orderDao.find(where, params.toArray(), orderby, pageNo, pageSize);//ִ�в�ѯ����
		return LIST;//���غ�̨�����б�
	}
	/**
	 * ��ѯָ������
	 */
	public String select() throws Exception {
		order = orderDao.load(order.getOrderId());
		return SELECT;
	}
	/**
	 * ���¶���״̬
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		OrderState orderState = order.getOrderState();//��ȡ���õĶ���״̬
		order = orderDao.load(order.getOrderId());//װ�ض�������
		order.setOrderState(orderState);//���õĶ���״̬
		orderDao.update(order);//�޸Ķ���״̬
		return "update";//�Żض���״̬�޸ĳɹ�ҳ��
	}
	
	// ����
	private Order order = new Order();
	
	private PageModel<Order> pageModel;// ��ҳ���
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public PageModel<Order> getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel<Order> pageModel) {
		this.pageModel = pageModel;
	}
	@Override
	public Order getModel() {
		return order;
	}
}
