package com.Sameer.TheMessage.Repository;

import com.Sameer.TheMessage.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUserName(String userName);
    User findByUserName(String userName);
}
