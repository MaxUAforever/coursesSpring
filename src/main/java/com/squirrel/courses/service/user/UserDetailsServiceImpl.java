package com.squirrel.courses.service.user;

import com.squirrel.courses.dataaccess.dao.user.IUserDAO;
import com.squirrel.courses.dataaccess.model.AppUser;
import com.squirrel.courses.dataaccess.repositories.UserRepository;
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

/**
 * Class UserDetailsServiceImpl is a interlayer between controller and data-access classes for working with users.
 * Implements interface to load user-specific data.
 *
 * @author    Bogdan Popovich
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService, IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) {

        AppUser appUser = this.userRepository.findByLogin(login);

        if (appUser == null) {
            throw new UsernameNotFoundException("AppUser " + login + " was not found in the database");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        grantList.add(new SimpleGrantedAuthority(appUser.getRole()));

        return new User(appUser.getLogin(), appUser.getHashPass(), grantList);
    }

    @Override
    public void addUser(AppUser appUser) {
        userRepository.save(appUser);
    }

    @Override
    public AppUser findByLogin(String login) {
        return this.userRepository.findByLogin(login);
    }
}
