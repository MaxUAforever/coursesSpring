package com.squirrel.courses.service.user;

import com.squirrel.courses.dataaccess.dao.user.JdbcUserDAO;
import com.squirrel.courses.dataaccess.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JdbcUserDAO jdbcUserDAO;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        AppUser appUser = this.jdbcUserDAO.findByLogin(login);

        if (appUser == null) {
            System.out.println("AppUser not found! " + login);
            throw new UsernameNotFoundException("AppUser " + login + " was not found in the database");
        }

        System.out.println("Found AppUser: " + appUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        /*List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }*/

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        grantList.add(new SimpleGrantedAuthority(appUser.getRole()));

        UserDetails userDetails = (UserDetails) new User(appUser.getLogin(), appUser.getHashPass(), grantList);

        return userDetails;
    }
}
