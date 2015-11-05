package com.keratin.action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.keratin.action.BaseAction;
import com.keratin.model.user.Customer;
import com.keratin.util.AppException;
import com.opensymphony.xwork2.ModelDriven;
/**
 * �˿�Action
 * @author Li Yongqiang
 */
@Scope("prototype")
@Controller("customerAction")
public class CustomerAction extends BaseAction implements ModelDriven<Customer>{
	private static final long serialVersionUID = 1L;
	public String login() throws Exception{
		return CUSTOMER_LOGIN;
	}
	/**
	 * �û�ע��
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		boolean unique = customerDao.isUnique(customer.getUsername());//�ж��û����Ƿ����
		if(unique){//����û�������
			customerDao.save(customer);//����ע����Ϣ
			return CUSTOMER_LOGIN;//���ػ�Ա��¼ҳ��
		}else{
			throw new AppException("���û���������");//���򷵻�ҳ�������Ϣ
		}
	}
	/**
	 * �û���¼ 
	 * @return
	 * @throws Exception
	 */
	public String logon() throws Exception{
		//��֤�û����������Ƿ���ȷ
		Customer loginCustomer = customerDao.login(customer.getUsername(), customer.getPassword());
		if(loginCustomer != null){//���ͨ����֤
			session.put("customer", loginCustomer);//����¼��Ա��Ϣ������Session��
		}else{//��֤ʧ��
			addFieldError("", "�û��������벻��ȷ��");//���ش�����Ϣ
			return CUSTOMER_LOGIN;//���ػ�Ա��¼ҳ��
		}
		return INDEX;//������վ��ҳ
	}
	/**
	 * �û��˳�
	 * @return String
	 * @throws Exception
	 */
	public String logout() throws Exception{
		if(session != null && session.size() > 0){
			session.clear();
		}
		return INDEX;
	}
	// ����Ա
	private Customer customer = new Customer();
	// ȷ������
	private String repassword;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	@Override
	public Customer getModel() {
		return customer;
	}
}
