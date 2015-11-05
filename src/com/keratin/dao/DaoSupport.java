package com.keratin.dao;

import com.keratin.model.PageModel;
import com.keratin.util.GenericsUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * Dao support class
 * @param <T>
 */
@Transactional
@SuppressWarnings("unchecked")
public class DaoSupport<T> implements BaseDao<T>{
	// generic class
	protected Class<T> entityClass = GenericsUtils.getGenericType(this.getClass());
	// Hibernate template
	@Autowired
	protected HibernateTemplate template;
	
	public HibernateTemplate getTemplate() {
		return template;
	}
	@Override
	public void delete(Serializable ... ids) {
		for (Serializable id : ids) {
			T t = (T) getTemplate().load(this.entityClass, id);
			getTemplate().delete(t);
		}
	}
	/**
	 * load object via get(0 function, get the detail information of object.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T get(Serializable entityId) {
		return (T) getTemplate().get(this.entityClass, entityId);
	}
	/**
	 * load object's detail information
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T load(Serializable entityId) {
		return (T) getTemplate().load(this.entityClass, entityId);
	}
	/**
	 * query single object.
	 */
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Object uniqueResult(final String hql,final Object[] queryParams) {
		return getTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				setQueryParams(query, queryParams);
				return query.uniqueResult();
			}
		});
	}
	/**
	 * get dedicated object's count.
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public long getCount() {
		String hql = "select count(*) from " + GenericsUtils.getGenericName(this.entityClass);
		return (Long)uniqueResult(hql,null);
	}
	/**
	 * save object's detail.
	 */
	@Override
	public void save(Object obj) {
		getTemplate().save(obj);
	}
	@Override
	public void saveOrUpdate(Object obj) {
		getTemplate().saveOrUpdate(obj);
	}
	/**
	 * update object's detail.
	 */
	@Override
	public void update(Object obj) {
		getTemplate().update(obj);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(final int pageNo, int maxResult) {
		return find(null, null, null, pageNo, maxResult);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(int pageNo, int maxResult,
			Map<String, String> orderby) {
		return find(null, null, orderby, pageNo, maxResult);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(int pageNo, int maxResult, String where,
			Object[] queryParams) {
		return find(where, queryParams, null, pageNo, maxResult);
	}
	/**
	 * paging query
	 * @param where
	 * @param queryParams
	 * @param orderby
	 * @param pageNo
	 * @param maxResult
	 * return PageModel
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(final String where, final Object[] queryParams,
			final Map<String, String> orderby, final int pageNo,
			final int maxResult) {
		final PageModel<T> pageModel = new PageModel<T>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(maxResult);
		getTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = new StringBuffer().append("from ")
								.append(GenericsUtils.getGenericName(entityClass))
								.append(" ")
								.append(where == null ? "" : where)
								.append(createOrderBy(orderby))
								.toString();
				Query query = session.createQuery(hql);
				setQueryParams(query,queryParams);
				List<T> list = null;
				if(maxResult < 0 && pageNo < 0){
					list = query.list();
				}else{
					list = query.setFirstResult(getFirstResult(pageNo, maxResult))
								.setMaxResults(maxResult)
								.list();
					hql = new StringBuffer().append("select count(*) from ")
									.append(GenericsUtils.getGenericName(entityClass))
									.append(" ")
									.append(where == null ? "" : where)
									.toString();
					query = session.createQuery(hql);
					setQueryParams(query,queryParams);
					int totalRecords = ((Long) query.uniqueResult()).intValue();
					pageModel.setTotalRecords(totalRecords);
				}
				pageModel.setList(list);
				return null;
			}
		});
		return pageModel;
	}
	/**
	 * get initial page of result list
	 * @param pageNo
	 * @param maxResult
	 * @return
	 */
	protected int getFirstResult(int pageNo,int maxResult){
		int firstResult = (pageNo-1) * maxResult;
		return firstResult < 0 ? 0 : firstResult;
	}
	/**
	 * set query parameter
	 * @param query
	 * @param queryParams
	 */
	protected void setQueryParams(Query query, Object[] queryParams){
		if(queryParams!=null && queryParams.length>0){
			for(int i=0; i<queryParams.length; i++){
				query.setParameter(i, queryParams[i]);
			}
		}
	}
	/**
	 * create hql.
	 * @param orderby
	 * @return ordered string
	 */
	protected String createOrderBy(Map<String, String> orderby){
		StringBuffer sb = new StringBuffer("");
		if(orderby != null && orderby.size() > 0){
			sb.append(" order by ");
			for(String key : orderby.keySet()){
				sb.append(key).append(" ").append(orderby.get(key)).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	/**
	 * @return
	 */
	protected Session getSession(){
		return (!this.template.isAllowCreate() ?
		    SessionFactoryUtils.getSession(this.template.getSessionFactory(), false) :
				SessionFactoryUtils.getSession(
						this.template.getSessionFactory(),
						this.template.getEntityInterceptor(),
						this.template.getJdbcExceptionTranslator()));
	}
}
