package com.company.service;

import com.company.entity.User;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

        public User getUserFromAuth(AbstractAuthenticationToken token){
        Map<String,Object> attirbutes;

        if (token instanceof OAuth2AuthenticationToken){
            attirbutes=((OAuth2AuthenticationToken) token).getPrincipal().getAttributes();
        }else {
            throw new IllegalArgumentException("Error");
        }
        User user=getUser(attirbutes);
        if (user !=null){
            userRepository.save(user);
        }
        return user;
    }
    private User getUser(Map<String,Object> attirbutes){
        User user=new User();
        if (attirbutes.get("uid")!=null){
            user.setUid((String) attirbutes.get("uid"));
        }
        if (attirbutes.get("given_name")!=null){
            user.setFirstName((String) attirbutes.get("given_name"));
        }
        if (attirbutes.get("family_name")!=null){
            user.setLastName((String) attirbutes.get("family_name"));
        }
        if (attirbutes.get("locale")!=null){
            user.setLangKey((String) attirbutes.get("locale"));
        }
        if (attirbutes.get("email_verified")!=null){
            user.setActivated((Boolean) attirbutes.get("email_verified"));
        }
        if (attirbutes.get("email")!=null){
            user.setEmail((String) attirbutes.get("email"));
        }
        if (attirbutes.get("picture")!=null){
            user.setImageUrl((String) attirbutes.get("picture"));
        }

        return user;

    }

}
