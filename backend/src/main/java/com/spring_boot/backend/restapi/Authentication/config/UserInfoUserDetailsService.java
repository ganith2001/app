package com.spring_boot.backend.restapi.Authentication.config;

import com.spring_boot.backend.restapi.entities.recruiterSignup;
import com.spring_boot.backend.restapi.repositories.recruiterSignupRepository;
import com.spring_boot.backend.restapi.entities.candidateSignup;
import com.spring_boot.backend.restapi.repositories.candidateSignupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



@Component
@Order(1)
public class UserInfoUserDetailsService implements UserDetailsService {


    @Autowired
    private candidateSignupRepository repository;
   
    @Autowired 
    private recruiterSignupRepository rrepository;

     

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean b = repository.existsByCid(username);
    
        if(b==true){
            candidateSignup userInfo = repository.findByCid(username);
            return new UserInfoUserDetails(userInfo,"CANDIDATE");
        }
        else{
            recruiterSignup userInfo2 = rrepository.findByEmpid(username);
            return new UserInfoUserDetails(userInfo2,userInfo2.getRoles());
        }

        
      
            
        
            
        
        
        

        //return userInfo.map(UserInfoUserDetails::new)
          //      .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
    
}
