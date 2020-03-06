package assJava5.repository;


import assJava5.model.Users;

public interface UserRepository extends Repository<Users> {
    Users findByUsername(String username);
}
