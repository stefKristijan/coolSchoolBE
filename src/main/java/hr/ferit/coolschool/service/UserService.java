package hr.ferit.coolschool.service;

import hr.ferit.coolschool.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    /**
     * Inserts and returns a new User
     *
     * @param user
     * @return
     */
    User save(User user);

    /**
     * Checks if user exists and if it does, updates it and returns the updated user
     *
     * @param id
     * @param user
     * @return
     */
    User update(Long id, User user);

    /**
     * Updates the password of the user with the given id
     *
     * First it checks if the oldPassword param matches the old password
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean updatePassword(Long id, String oldPassword, String newPassword);
}
