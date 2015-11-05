package com.keratin.action.product;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.keratin.action.BaseAction;
import com.keratin.model.PageModel;
import com.keratin.model.product.ProductCategory;
import com.opensymphony.xwork2.ModelDriven;
/**
 * ��Ʒ���Action����
 * @author Li Yongqiang
 *
 */
@Scope("prototype")
@Controller("productCategoryAction")
public class ProductCategoryAction extends BaseAction implements ModelDriven<ProductCategory>{
	private static final long serialVersionUID = 1L;
	private ProductCategory category = new ProductCategory();
	@Override
	public ProductCategory getModel() {
		return category;
	}
	
	@Override
	public String add() throws Exception {
		if(pid != null && pid > 0 ){
			ProductCategory parent = categoryDao.load(pid);
			// ��μ�1
			category.setLevel(parent.getLevel() + 1);
		}
		return INPUT;
	}
	/**
	 * ������
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		if(pid != null && pid > 0 ){//����и��ڵ�
			category.setParent(categoryDao.load(pid));//�����丸�ڵ�
		}
		categoryDao.saveOrUpdate(category);//��������Ϣ
		return list();//��������б�Ĳ��ҷ���
	}
	/**
	 * ��ѯ���
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		Object[] params = null;//��������Ϊ��
		String where;//��ѯ��������
		if(pid != null && pid > 0 ){//����и��ڵ�
			where = "where parent.id =?";//ִ�в�ѯ����
			params = new Integer[]{pid};//���ò���ֵ
		}else{
			where = "where parent is null";//��ѯ���ڵ�
		}
		pageModel = categoryDao.find(pageNo,pageSize,where,params);//ִ�з�װ�Ĳ�ѯ����
		return LIST;//���غ�̨����б�ҳ��
	}
	/**
	 * �༭���
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		if(category.getId() != null && category.getId() > 0){
			this.category = categoryDao.get(category.getId());
		}
		return EDIT;
	}
	/**
	 * ɾ�����
	 * @return String
	 * @throws Exception
	 */
	public String del() throws Exception{
		if(category.getId() != null && category.getId() > 0){//�ж��Ƿ���ID����
			categoryDao.delete(category.getId());//ִ��ɾ������
		}
		return list();//������Ʒ����б�Ĳ��ҷ���
	}
	// �����id
	private Integer pid;
	// �������
	private PageModel<ProductCategory> pageModel;
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public PageModel<ProductCategory> getPageModel() {
		return pageModel;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
}
