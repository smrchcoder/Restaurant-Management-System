package com.Arjunagi.ResturantManagementApp.services;

import com.Arjunagi.ResturantManagementApp.models.UserAuthToken;
import com.Arjunagi.ResturantManagementApp.models.dto.AuthInpDto;
import com.Arjunagi.ResturantManagementApp.models.user.User;
import com.Arjunagi.ResturantManagementApp.reposotories.IUserAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthTokenService {
    @Autowired
    IUserAuthTokenRepo userAuthTokenRepo;
     public UserAuthToken createToken(User user){
         UserAuthToken userAuthToken=new UserAuthToken(user);
         userAuthTokenRepo.save(userAuthToken);
         return userAuthToken;
     }

    public UserAuthToken getUserAuthToken(AuthInpDto authInpDto) {
         UserAuthToken userAuthToken=userAuthTokenRepo.findFirstByValue(authInpDto.getTokenValue());
         if(userAuthToken==null)return null;
         User user=userAuthToken.getUser();
         if(user.getEmail().equals(authInpDto.getEmail())&&user.getRole().equals(authInpDto.getRole()))
             return  userAuthToken;
         return null;
    }
    public boolean removeFromRecord(AuthInpDto authInpDto){
         UserAuthToken userAuthToken= getUserAuthToken(authInpDto);
         if(userAuthToken!=null){
             userAuthTokenRepo.delete(userAuthToken);
             return true;
         }
         return false;
    }
}
