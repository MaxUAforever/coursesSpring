package com.squirrel.courses.dataaccess.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class AppUser represents the entity of user in system with main information about him.
 *
 * @author    Bogdan Popovich
 */

@Entity
@Table(name="user")
public class AppUser {
    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "hash_pass")
    private String hashPass;

    /**
     * Var role defines user's role and rights in system.
     * It can be "admin", "lecturer" or "student".
     */
    @Column(name = "role")
    private String role;

    @Column(name = "user_name")
    private String userName;

    /** Var description keeps information that user wrote about himself. */
    @Column(name = "description")
    private String description;

    public AppUser(){
        this.login = "";
        this.hashPass = "";
        this.role = "";
        this.userName = "";
        this.description = "";
    }

    public AppUser(String login, String hashPass, String role, String userName, String description) {
        this.login = login;
        this.hashPass = hashPass;
        this.role = role;
        this.userName = userName;
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPass() {
        return hashPass;
    }

    public String getRole() {
        return role;
    }


    public String getUserName() {
        return userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
