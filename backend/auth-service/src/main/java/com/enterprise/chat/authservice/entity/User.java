package com.enterprise.chat.authservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    @NotBlank
    @Size(min=3, max=50)
    @Column(nullable=false,unique = true,length = 50)
    private String userName;
    @NotBlank
    @Email
    @Column(nullable=false,unique = true)
    private String email;
    @NotBlank
    @Size(min=8,max=100,message="Password must be between 8 and 100 characters")
    @Column(nullable=false)
    private String password;
    @NotBlank
    @Column(nullable=false)
    private String firstName;
    @NotBlank
    @Column(nullable=false)
    private String lastName;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10,15}$",message="Phone number must contain between 10 and 15 digits")
    @Column(nullable = false, length=15)
    private String phoneNumber;
    @Column(nullable=false)
    private boolean enabled=true;
    @Column(nullable=false)
    private boolean accountNonExpired=true;
    @Column(nullable=false)
    private boolean accountNonLocked=true;
    @Column(nullable=false)
    private boolean credentialsNonExpired=true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles=new HashSet<>();

    public User(){}

    public User(String userName, String email, String password, String firstName, String lastName, String phoneNumber, boolean enabled, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, Set<Role> roles) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.roles = roles;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
