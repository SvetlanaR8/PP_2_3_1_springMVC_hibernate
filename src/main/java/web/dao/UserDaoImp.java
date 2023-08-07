package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }
    @Override
    public User show(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.detach(user);
        return user;
    }

    @Override
    public void update(Long id, User user) {
        User userToBeUpdated = entityManager.find(User.class, id);
        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setLastName(user.getLastName());
        entityManager.merge(userToBeUpdated);
    }

    @Override
    public void delete(Long id) {
        User userToBeDeleted = entityManager.find(User.class, id);
        entityManager.remove(userToBeDeleted);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Query query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }
}
