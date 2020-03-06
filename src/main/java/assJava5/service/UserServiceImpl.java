package assJava5.service;


import assJava5.model.Users;
import assJava5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users finById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(Users users) {
        userRepository.save(users);
    }

    @Override
    public void remove(Long id) {
        userRepository.remove(id);
    }

    public Users findByUserName(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isUsers(String username, String password) {
        try{
            Users user = userRepository.findByUsername(username);
            if (user != null && user.getMatKhau().equals(password)) {
                return true;
            }
            return false;
        }
        catch (Exception e){

        }
        return false;
    }
}
