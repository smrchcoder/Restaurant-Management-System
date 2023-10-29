package com.Arjunagi.ResturantManagementApp.reposotories;

import com.Arjunagi.ResturantManagementApp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByEmail(String email);

    User findFirstByPassword(String pass);
}
