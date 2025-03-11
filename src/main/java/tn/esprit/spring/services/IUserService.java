package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.User;

public interface IUserService { 
	 
	List<User> retrieveAllUsers(); 
	User addUser(User u);
	void deleteUser(String id);
	User updateUser(User u);
	User retrieveUser(String id);

    class UserRepositoryMock {
        ArrayList<User> users = new ArrayList<>();
        long currentId = 0;

        public User save(User user) {
            if (user.getId() == null) {
                users.add(user);
                currentId ++;
                user.setId(currentId);
            }

            return user;
        }

        public void deleteById(Long id) {
            for (User user : users) {
                if (user.getId() != null && user.getId().equals(id)) {
                    users.remove(user);
                }
            }
        }

        public Optional<User> findById(Long id) {
            for (User user : users) {
                if (user.getId() != null && user.getId().equals(id)) {
                    return Optional.of(user);
                }
            }

            return Optional.empty();
        }
    }
}
 