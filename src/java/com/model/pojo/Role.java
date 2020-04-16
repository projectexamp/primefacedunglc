package com.model.pojo;

import java.util.ArrayList;
import java.util.List;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "function_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "function_id"))
    private Set<Function> function;

    @Transient
    private List<String> functionString;

    public List<String> getFunctionString() {
        List<String> functionID = new ArrayList<>();
        if (function != null) {
            for (Function f : function) {
                functionID.add(String.valueOf(f.getFunctionID()));
            }
        }
        functionString = functionID;
        return functionString;
    }

    public void setFunctionString(List<String> functionString) {
        this.functionString = functionString;
    }

    public List<String> getFunctionStr() {
        return functionString;
    }

    public Set<Function> getFunction() {
        return function;
    }

    public void setFunction(Set<Function> function) {
        this.function = function;
    }

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
