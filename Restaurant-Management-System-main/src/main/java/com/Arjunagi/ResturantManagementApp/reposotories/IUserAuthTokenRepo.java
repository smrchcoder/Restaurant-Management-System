package com.Arjunagi.ResturantManagementApp.reposotories;

import com.Arjunagi.ResturantManagementApp.models.UserAuthToken;
import com.Arjunagi.ResturantManagementApp.models.dto.AuthInpDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserAuthTokenRepo extends JpaRepository<UserAuthToken,Integer> {
    UserAuthToken findFirstByValue(String authInpDto);
}
