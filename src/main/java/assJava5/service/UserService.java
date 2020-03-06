package assJava5.service;


import assJava5.model.Users;

import java.util.List;

public interface UserService {
    List<Users> findAll();
    Users finById(Long id);
    void save(Users users);
    void remove(Long id);
    Users findByUserName(String username);
    boolean isUsers(String username, String password);

}
