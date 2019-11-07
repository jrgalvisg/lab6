package sa.user.service;

import sa.user.model.User;
import sa.user.service.LdapService;
import sa.user.service.UserService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AuthService {

    @PersistenceContext
    EntityManager entityManager;

    String response;
    LdapService ldapService = new LdapService();
    UserService userService = new UserService();

    public String login(User user) {

        String username = user.getUsername();
        String password = user.getPassword();

        if (ldapService.connect()) {
            if (ldapService.validateUser(username, password)) {
                List<User> users = entityManager.createNamedQuery(User.FIND_BY_USERNAME)
                      .setParameter("username", username).getResultList();
                if (!users.isEmpty()) {
                    for (User u: users) {
                        if (u.getPassword().equals(password)) {
                            response = "¡Autenticación satisfactoria!";
                        } else {
                            response = "¡Contraseña inválida!";
                        }
                    }
                } else {
                    response = "¡El usuario no existe en la base de datos!";
                }
            } else {
                response = "¡El usuario no existe en el servidor LDAP!";
            }
        } else {
            response = "¡Error de conexión con el servidor LDAP!";
        }
        return response;
    }
}