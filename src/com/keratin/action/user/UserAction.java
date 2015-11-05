package com.keratin.action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.keratin.action.BaseAction;
import com.keratin.model.user.User;
import com.opensymphony.xwork2.ModelDriven;
/**
 * �û�Action
 * @author Li Yongqiang
 */
@Scope("prototype")
@Controller("userAction")
public class UserAction extends BaseAction implements ModelDriven<User>{
	private static final long serialVersionUID = 1L;
	public String login() throws Exception{
		return USER_LOGIN;
	}
	/**
	 * ��¼ 
	 * @return
	 * @throws Exception
	 */
	public String logon() throws Exception{
		//��֤�û���������
		User loginUser = userDao.login(user.getUsername(), user.getPassword());
		if(loginUser != null){//ͨ����֤
			session.put("admin", loginUser);//������Ա��Ϣ������Session������
		}else{
			addFieldError("", "�û��������벻��ȷ��");//���ش�����ʾ��Ϣ
			return USER_LOGIN;//���غ�̨��¼ҳ��
		}
		return MANAGER;//���غ�̨����ҳ��
	}
	/**
	 * �˳�
	 * @return String
	 * @throws Exception
	 */
	public String logout() throws Exception{
		if(session != null && session.size() > 0){
			session.clear();
		}
		return INDEX;
	}
	// �û�����
	private User user = new User();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public User getModel() {
		return user;
	}
}
