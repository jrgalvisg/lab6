package sa.user.service;

import sa.user.model.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserService {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> getAllUsers(int first, int maxResult) {
        return entityManager.createNamedQuery(User.FIND_ALL)
                .setFirstResult(first).setMaxResults(maxResult).getResultList();
    }

    public User getUserById(int id){
        return entityManager.find(User.class, id);
    }

    public User createUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }

    public User updateUser(int id, User user) {
        User userToUpdate = entityManager.find(User.class, id);
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        entityManager.merge(userToUpdate);
        return entityManager.find(User.class, id);
    }

    public int deleteUser(int id) {
        User userToDelete = entityManager.find(User.class, id);
        entityManager.remove(userToDelete);
        return id;
    }
}
