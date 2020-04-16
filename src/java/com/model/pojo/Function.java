package com.model.pojo;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "function")
public class Function {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "function_id")
    private long functionID;

    @Column(name = "function_name")
    private String functionName;
    
    @Column(name = "function_url")
    private String functionURL;

    public String getFunctionURL() {
        return functionURL;
    }

    public void setFunctionURL(String functionURL) {
        this.functionURL = functionURL;
    }

    public long getFunctionID() {
        return functionID;
    }

    public void setFunctionID(long functionID) {
        this.functionID = functionID;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

   
   
    
}
