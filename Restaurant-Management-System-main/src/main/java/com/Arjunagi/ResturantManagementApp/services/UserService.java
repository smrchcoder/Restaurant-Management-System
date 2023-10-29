package com.Arjunagi.ResturantManagementApp.services;

import com.Arjunagi.ResturantManagementApp.models.UserAuthToken;
import com.Arjunagi.ResturantManagementApp.models.dto.AuthInpDto;
import com.Arjunagi.ResturantManagementApp.models.dto.LoginDto;
import com.Arjunagi.ResturantManagementApp.models.dto.UserRegDto;
import com.Arjunagi.ResturantManagementApp.models.user.Role;
import com.Arjunagi.ResturantManagementApp.models.user.User;
import com.Arjunagi.ResturantManagementApp.reposotories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    UserAuthTokenService userAuthTokenService;

    public String AddNPCUser(UserRegDto userRegDto) {

        if( userRepo.findFirstByEmail(userRegDto.getEmail())!=null)return "user already exist";
        String pass;
        try {
             pass=PasswordEncryptor.encrypt(userRegDto.getPassword());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        User user=new User(userRegDto.getName(),userRegDto.getEmail(),pass, Role.NPC);
        userRepo.save(user);

        return "added sucessfully";
    }
    private boolean checkAdminEmail(String email){
        String domain="@admin.com";
        if(email.length()<=domain.length())return false;
        int n=domain.length();
        int lastInd=email.length()-1;
        while (n-->0){
            if(domain.charAt(n)!=email.charAt(lastInd--))return false;
        }
        return true;
    }

    public String AddAdminUser(UserRegDto userRegDto) {
        if( userRepo.findFirstByEmail(userRegDto.getEmail())!=null)return "user already exist";
        String pass;
        try {
            pass=PasswordEncryptor.encrypt(userRegDto.getPassword());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        if(!checkAdminEmail(userRegDto.getEmail()))return "wrong email";

        User user=new User(userRegDto.getName(),userRegDto.getEmail(),pass, Role.ADMIN);

        userRepo.save(user);
        return "added sucessfully";

    }
    public boolean validateUserByLoginDto(LoginDto loginDto,User user){

        if( user==null)return false;
        String pass;
        try {
            pass=PasswordEncryptor.encrypt(loginDto.getPassword());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return user.getPassword().equals(pass);
    }

    public AuthInpDto signInUser(LoginDto loginDto) {

        User user= userRepo.findFirstByEmail(loginDto.getEmail());
        if (user==null||!validateUserByLoginDto(loginDto,user)) return null;
        UserAuthToken userAuthToken=userAuthTokenService.createToken(user);
        return new AuthInpDto(userAuthToken.getValue(),user.getEmail(),user.getRole());

    }

    public String logoutUser(AuthInpDto authInpDto) {

        if(userAuthTokenService.removeFromRecord(authInpDto)) return "logout sucessfully";
        return "something went wrong";
    }

    public User getUser(AuthInpDto authInpDto) {

        UserAuthToken userAuthToken= userAuthTokenService.getUserAuthToken(authInpDto);
        if(userAuthToken==null)return null;
        return userAuthToken.getUser();
    }
    public boolean isAdmin(AuthInpDto authInpDto){
        UserAuthToken userAuthToken= userAuthTokenService.getUserAuthToken(authInpDto);
        return userAuthToken!=null && userAuthToken.getUser().getRole().equals(Role.ADMIN);
    }

    public List<User> getAll(AuthInpDto authInpDto){

        if(!isAdmin(authInpDto))return null;
        return userRepo.findAll();

    }

    public String deleteUserById(AuthInpDto authInpDto, Integer id) {
        if(isAdmin(authInpDto)){
            userRepo.deleteById(id);
            return "deleted sucessfully";
        }
        return "admin required";
    }
}
