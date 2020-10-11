package org.jakarta.mail;

import com.vaadin.cdi.annotation.VaadinSessionScoped;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.mail.Session;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@VaadinSessionScoped
public class MailConfig {
       
    //Injectar un Recurso
    @Resource(name = "JakartaMail")
    private Session mailSession;
        
    //Injectando properties con micro-profile
    @Inject
    @ConfigProperty(name = "mail.smtp.starttls.enable")
    private Boolean starttls;
        
    @Inject
    @ConfigProperty(name = "mail.smtp.password")
    private String password;
    
    @Inject
    @ConfigProperty(name = "mail.smtp.port")
    private String port;
    
    @Inject
    @ConfigProperty(name = "mail.smtp.auth")
    private Boolean auth;
    
    
    

    public void MailConfig() {
        this.mailSession = mailSession;
        this.starttls = starttls;
        this.password = password;
        this.port = port;
        this.auth = auth;
               
      }

    public Session getMailSession() {
        return mailSession;
    }

    public Boolean getStarttls() {
        return starttls;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }

    public Boolean getAuth() {
        return auth;
    }
    
    
    
  
    
    
    

    
    
    
    
    
    

    
}
