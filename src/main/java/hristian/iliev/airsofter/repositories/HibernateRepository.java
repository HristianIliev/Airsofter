package hristian.iliev.airsofter.repositories;

import hristian.iliev.airsofter.contracts.IModel;
import hristian.iliev.airsofter.contracts.IRepository;
import hristian.iliev.airsofter.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class HibernateRepository<T extends IModel> implements IRepository<T> {
  private Class<T> entityClass;

  @Override
  public List<T> getAll() {
    Session session = HibernateUtils.getSessionFactory().openSession();
    Transaction transaction = session.getTransaction();
    transaction.begin();

    CriteriaBuilder builder = session.getCriteriaBuilder();

    CriteriaQuery<T> criteriaQuery = builder.createQuery(this.getEntityClass());
    criteriaQuery.from(this.getEntityClass());

    List<T> entities = session.createQuery(criteriaQuery)
            .getResultList();

    transaction.commit();
    session.close();

    return entities;
  }

  @Override
  public T getById(int id) {
    Session session = HibernateUtils.getSessionFactory().openSession();
    Transaction transaction = session.getTransaction();
    transaction.begin();

    T entity = session.get(this.getEntityClass(), id);

    transaction.commit();
    session.close();

    return entity;
  }

  @Override
  public T create(T entity) {
    Session session = HibernateUtils.getSessionFactory().openSession();
    Transaction transaction = session.getTransaction();
    transaction.begin();

    session.save(entity);

    transaction.commit();
    session.close();

    return entity;
  }

  @Override
  public T delete(T entity) {
    Session session = HibernateUtils.getSessionFactory().openSession();
    Transaction transaction = session.getTransaction();
    transaction.begin();

    session.remove(entity);

    transaction.commit();
    session.close();

    return entity;
  }

  @Override
  public T update(T entity) {
    Session session = HibernateUtils.getSessionFactory().openSession();
    Transaction transaction = session.getTransaction();
    transaction.begin();

    session.update(entity);

    transaction.commit();
    session.close();

    return entity;
  }

  public Class<T> getEntityClass() {
    return entityClass;
  }

  public void setEntityClass(Class<T> entityClass) {
    this.entityClass = entityClass;
  }
}
