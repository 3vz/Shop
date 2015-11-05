package com.keratin.action.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.keratin.action.BaseAction;
import com.keratin.model.PageModel;
import com.keratin.model.product.ProductCategory;
import com.keratin.model.product.ProductInfo;
import com.keratin.model.product.UploadFile;
import com.keratin.util.StringUitl;
import com.opensymphony.xwork2.ModelDriven;
/**
 * ��ƷAction
 * @author Li Yongqiang
 */
@Scope("prototype")
@Controller("productAction")
public class ProductAction extends BaseAction implements ModelDriven<ProductInfo>{
	private static final long serialVersionUID = 1L;
	/**
	 * ����id�鿴��Ʒ��Ϣ(�鿴����������������)
	 * @return String
	 * @throws Exception
	 */
	public String select() throws Exception {
		if(product.getId() != null && product.getId() > 0){
			product = productDao.get(product.getId());
			product.setClickcount(product.getClickcount() + 1);
			productDao.update(product);
		}
		return SELECT;
	}
	/**
	 * �������id��ѯ������Ʒ��Ϣ
	 * @return String
	 * @throws Exception
	 */
	public String getByCategoryId() throws Exception{
		if(product.getCategory().getId() != null){
			String where = "where category.id = ?";
			Object[] queryParams = {product.getCategory().getId()};
			pageModel = productDao.find(pageNo, pageSize, where, queryParams);
		}
		return LIST;
	}
	/**
	 * ��Ʒ����
	 * @return
	 * @throws Exception
	 */
	public String findNewProduct() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();//����Map����
		orderby.put("createTime", "desc");//ΪMap���ϸ�ֵ
		pageModel = productDao.find(1, 5, orderby );//ִ�в��ҷ���
		image.put("url", "01.gif");//���ø�����ͼƬ
		return "list";//������Ʒ�б�ҳ��
	}
	/**
	 * ������Ʒ
	 * @return
	 * @throws Exception
	 */
	public String findSellProduct() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();//����Map����
		orderby.put("sellCount", "desc");//ΪMap���ϸ�ֵ
		pageModel = productDao.find(1, 5, orderby );//ִ�в��ҷ���
		image.put("url", "03.gif");
		return "list";//������Ʒ�б�ҳ��
	}
	/**
	 * �Ƽ���Ʒ
	 * @return
	 * @throws Exception
	 */
	public String findCommendProduct() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();//����Map����
		orderby.put("createTime", "desc");//ΪMap���ϸ�ֵ
		String where = "where commend = ?";//�����������
		Object[] queryParams = {true};//���ò���ֵ
		pageModel = productDao.find(where, queryParams, orderby, 1, 5);//ִ�в�ѯ����
		image.put("url", "06.gif");
		return "list";//������Ʒ�б�ҳ��
	}
	/**
	 * ������Ʒ
	 * @return
	 * @throws Exception
	 */
	public String findEnjoyProduct() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();//����Map����
		orderby.put("clickcount", "desc");//ΪMap���ϸ�ֵ
		pageModel = productDao.find(1, 5, orderby );//ִ�в��ҷ���
		image.put("url", "07.gif");
		return "list";//������Ʒ�б�ҳ��
	}
	/**
	 * ��������ģ����ѯ
	 * @return String
	 * @throws Exception
	 */
	public String findByName() throws Exception {
		if(product.getName() != null){
			String where = "where name like ?";//��ѯ���������
			Object[] queryParams = {"%" + product.getName() + "%"};//Ϊ������ֵ
			pageModel = productDao.find(pageNo, pageSize, where, queryParams );//ִ�в�ѯ����
		}
		image.put("url", "04.gif");
		return LIST;//�����б���ҳ
	}
	/**
	 * ��������ѯ
	 * @return String
	 * @throws Exception
	 */
	public String findByClick() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();//����Map����
		orderby.put("clickcount", "desc");//ΪMap���ϸ�ֵ
		pageModel = productDao.find(1, 8, orderby );//ִ�в��ҷ���
		return "clickList";//����product_click_list.jspҳ��
	}
	/**
	 * ���Ƽ���ѯ
	 * @return String
	 * @throws Exception
	 */
	public String findByCommend() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();//����Map����
		orderby.put("sellCount", "desc");//ΪMap���ϸ�ֵ
		String where = "where commend = ?";//�����������
		Object[] queryParams = {true};//���ò���ֵ
		pageModel = productDao.find(where, queryParams, orderby, pageNo, pageSize);//ִ�в�ѯ����
		return "findList";//����product_find_list.jspҳ��
	}
	/**
	 * ��������ѯ
	 * @return String
	 * @throws Exception
	 */
	public String findBySellCount() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();//����Map����
		orderby.put("sellCount", "desc");//ΪMap���ϸ�ֵ
		pageModel = productDao.find(1, 6, orderby );//ִ�в�ѯ����
		return "findList";//����������Ʒ�б�
	}
	
	
	/**
	 * �����Ʒ
	 */
	@Override
	public String add() throws Exception {
		createCategoryTree();
		return INPUT;
	}
	/**
	 * ������Ʒ
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		if(file != null ){//����ļ�·����Ϊ��
			//��ȡ�������ľ���·��
			String path = ServletActionContext.getServletContext().getRealPath("/upload");
			File dir = new File(path);
			if(!dir.exists()){//����ļ��в�����
				dir.mkdir();//�����ļ���
			}
			String fileName = StringUitl.getStringTime() + ".jpg";//�Զ���ͼƬ����
			FileInputStream fis = null;//������
			FileOutputStream fos = null;//�����
			try {
				fis = new FileInputStream(file);//�����ϴ��ļ�����InputStreamʵ��
				fos = new FileOutputStream(new File(dir,fileName)); //����д���������ַ�����������
				byte[] bs = new byte[1024 * 4]; //�����ֽ�����ʵ��
				int len = -1;
				while((len = fis.read(bs)) != -1){//ѭ����ȡ�ļ�
					fos.write(bs, 0, len);//��ָ�����ļ�����д����
				}
				UploadFile uploadFile = new UploadFile();//ʵ��������
				uploadFile.setPath(fileName);//�����ļ�����
				product.setUploadFile(uploadFile);//�����ϴ�·��
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				fos.flush();
				fos.close();
				fis.close();
			}
		}
		//�����Ʒ������Ʒ���ID��Ϊ�գ��򱣴���Ʒ�����Ϣ
		if(product.getCategory() != null && product.getCategory().getId() != null){
			product.setCategory(categoryDao.load(product.getCategory().getId()));
		}
		//����ϴ��ļ����ϴ��ļ�ID��Ϊ�գ��򱣴��ļ����ϴ�·����Ϣ
		if(product.getUploadFile() != null && product.getUploadFile().getId() != null){
			product.setUploadFile(uploadFileDao.load(product.getUploadFile().getId()));
		}
		productDao.saveOrUpdate(product);//������Ʒ��Ϣ
		return list();
	}
	/**
	 * ��ѯ��Ʒ
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		pageModel = productDao.find(pageNo, pageSize);//���ù����Ĳ�ѯ����
		return LIST;//���غ�̨��Ʒ�б�ҳ��
	}
	/**
	 * �༭��Ʒ
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		this.product = productDao.get(product.getId());//ִ�з�װ�Ĳ�ѯ����
		createCategoryTree();//������Ʒ�������
		return EDIT;//������Ʒ��Ϣ�༭ҳ��
	}
	/**
	 * ɾ����Ʒ
	 * @return String
	 * @throws Exception
	 */
	public String del() throws Exception{
		productDao.delete(product.getId());//ִ��ɾ������
		return list();//������Ʒ�б���ҷ���
	}
	/**
	 * ���������
	 */
	private void createCategoryTree(){
		String where = "where level=1";//��ѯһ���ڵ�
		PageModel<ProductCategory> pageModel = categoryDao.find(-1, -1,where ,null);//ִ�в�ѯ����
		List<ProductCategory> allCategorys = pageModel.getList();
		map = new LinkedHashMap<Integer, String>();//�����µļ���
		for(ProductCategory category : allCategorys){//�������е�һ���ڵ�
			setNodeMap(map,category,false);//���ڵ���ӵ�Map������
		}
	}
	/**
	 * ���ڵ����õ�Map��
	 * @param map �����Map
	 * @param node ���
	 * @param flag �Ƿ�Ϊĩ�ڵ�
	 */
	private void setNodeMap(Map<Integer, String> map,ProductCategory node,boolean flag){
		if (node == null) {//����ڵ�Ϊ��
			return;//���ؿգ�������������
		}
		int level = node.getLevel();//��ȡ�ڵ㼶��
		StringBuffer sb = new StringBuffer();//�����ַ�������
		if (level > 1) {//������Ǹ��ڵ�
			for (int i = 0; i < level; i++) {
				 sb.append("��");//��ӿո�
			}
			sb.append(flag ? "��" : "��");//���Ϊĩ�ڵ����"��"����֮���"��"
		}
		map.put(node.getId(), sb.append(node.getName()).toString());//���ڵ���ӵļ�����
		Set<ProductCategory> children = node.getChildren();//��ȡ���ӽڵ�	
		// ���������
		if(children != null &&  children.size() > 0){//����ڵ㲻Ϊ��
			int i = 0;
			// ���������
			for (ProductCategory child : children) {
				boolean b = true;
				if(i == children.size()-1){//����ӽڵ㳤�ȼ�1Ϊi,˵��Ϊĩ�ڵ�
					b = false;//���ò�������Ϊfalse
				}
				setNodeMap(map,child,b);//���µ��ø÷���
			}
		}
	}
	/**
	 * ��֤��Ʒ��Ϣ�Ƿ���Ч
	 */
	public void validateSave() {
		if(!StringUitl.validateString(product.getName())){
			addFieldError("name", "��Ʒ���Ʋ���Ϊ�գ�");
		}
		if(!StringUitl.validateFloat(product.getBaseprice())){
			addFieldError("baseprice", "�ɹ��۸���д����");
		}
		if(!StringUitl.validateFloat(product.getMarketprice())){
			addFieldError("marketprice", "�г��۸���д����");
		}
		if(!StringUitl.validateFloat(product.getSellprice())){
			addFieldError("sellprice", "���ۼ۸���д����");
		}
		if(!StringUitl.validateString(product.getDescription())){
			addFieldError("name", "��Ʒ˵������Ϊ�գ�");
		}
		createCategoryTree();
	}


	// ��Ʒ����
	private ProductInfo product = new ProductInfo();
	// �ϴ��ļ�
	private File file;
	// �������
	private Map<Integer, String> map;
	// ��ҳ���
	private PageModel<ProductInfo> pageModel;
	
	Map<String, String> image = new HashMap<String, String>();
	
	public Map<String, String> getImage() {
		return image;
	}
	public void setImage(Map<String, String> image) {
		this.image = image;
	}
	@Override
	public ProductInfo getModel() {
		return product;
	}
	public ProductInfo getProduct() {
		return product;
	}
	public void setProduct(ProductInfo product) {
		this.product = product;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Map<Integer, String> getMap() {
		return map;
	}
	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}
	public PageModel<ProductInfo> getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel<ProductInfo> pageModel) {
		this.pageModel = pageModel;
	}
}
