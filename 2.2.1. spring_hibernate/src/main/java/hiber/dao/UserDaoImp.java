package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }
   @Override
   public void add(User user, Car car) {
      sessionFactory.getCurrentSession().saveOrUpdate(car);
      sessionFactory.getCurrentSession().saveOrUpdate(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getByCar(String model, int series) {
      TypedQuery<Car> query = sessionFactory.getCurrentSession()
              .createQuery("from Car where model = :m and series = :s");
      query.setParameter("m", model);
      query.setParameter("s", series);
      Car car = query.getSingleResult();
      TypedQuery<User> query2 = sessionFactory.getCurrentSession()
              .createQuery("from User where car_id = :id");
      query2.setParameter("id", car.getId());
      return query2.getSingleResult();
   }

}
