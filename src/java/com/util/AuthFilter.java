package com.util;

import com.dao.UserDAO;
import com.model.pojo.Role;
import com.model.pojo.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {
    
    UserDAO userDao = new UserDAO();
    
    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            // check whether session variable is set
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);
            //  allow user to proceed if url is login.xhtml or user logged in or user is accessing any page in //public folder
            String reqURI = req.getRequestURI();
            
            if (reqURI.indexOf("/login.xhtml") >= 0  || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resource")){
                chain.doFilter(request, response);
            }      
            else if (ses != null && ses.getAttribute("username") != null) {
                
                String userName = ses.getAttribute("username").toString();
                User user = userDao.getUserbyUserName(userName);
                
                if(userName != null && userName.length()>0 && user!= null 
                        && user.getRole()!=null && user.getRole().size()>0){
                    
                    List<String> roles = new ArrayList<>();
                    for(Role r : user.getRole()){
                        roles.add(r.getRoleName());
                    }
                    
                    if (roles.contains("ADMIN") || (reqURI.indexOf("/role.xhtml") >= 0 && roles.contains("USER"))
                            || (reqURI.indexOf("/function.xhtml") >= 0 && roles.contains("USER"))){
                        chain.doFilter(request, response);
                    }
                    else res.sendRedirect(req.getContextPath() + "/login.xhtml");
                    
                }
                else res.sendRedirect(req.getContextPath() + "/login.xhtml"); 
                
                
            } else // user didn't log in but asking for a page that is not allowed so take user to login page
            {
                res.sendRedirect(req.getContextPath() + "/login.xhtml");  // Anonymous user. Redirect to login page
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    } //doFilter

    @Override
    public void destroy() {

    }
}
