package com.model.pojo;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private long roleID;

    @Column(name = "role_name")
    private String roleName;

   
    public long getRoleID() {
        return roleID;
    }

    public void setRoleID(long RoleID) {
        this.roleID = RoleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

   
    
}
