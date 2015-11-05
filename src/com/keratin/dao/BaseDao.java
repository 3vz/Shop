package com.keratin.dao;

import java.io.Serializable;
import java.util.Map;

import com.keratin.model.PageModel;

public interface BaseDao<T> {
	//�������ݿ��������
	public void save(Object obj);//��������
	public void saveOrUpdate(Object obj);//������޸�����
	public void update(Object obj);//�޸�����
	public void delete(Serializable ... ids);//ɾ������
	public T get(Serializable entityId);//����ʵ�����
	public T load(Serializable entityId);//����ʵ�����
	public Object uniqueResult(String hql, Object[] queryParams);//ʹ��hql������
	//��ҳ����
	public long getCount();//��ȡ����Ϣ��
	public PageModel<T> find(int pageNo, int maxResult);//��ͨ��ҳ����
	public PageModel<T> find(int pageNo, int maxResult,String where, Object[] queryParams);//������Ϣ��ҳ����
	public PageModel<T> find(int pageNo, int maxResult,Map<String, String> orderby);//��ָ�����������ҳ����
	public PageModel<T> find(String where, Object[] queryParams,
			Map<String, String> orderby, int pageNo, int maxResult);//��ָ��������ҳ������ķ�ҳ����
}
