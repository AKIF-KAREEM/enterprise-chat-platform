package com.enterprise.chat.authservice.entity;

import com.enterprise.chat.authservice.enums.RoleType;
import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique=true)
    private RoleType roleName;

    public Role(){
    }

    public Role(RoleType roleName) {
        this.roleName = roleName;
    }
    public Long getRoleId() {return roleId;}
    public void setRoleId(Long roleId) {this.roleId = roleId;}
    public RoleType getRoleName() {return roleName;}
    public void setRoleName(RoleType roleName) {this.roleName = roleName;}

}
