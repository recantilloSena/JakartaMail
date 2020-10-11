package org.jakarta.mail;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


@Route("cdi")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends VerticalLayout {

    
    //CDI con el JDNI de Jakarta Mail
    @Inject
    private MailConfig mailConfig;
    

    @PostConstruct
    public void init() {
        
        EmailField email = new EmailField("e-mail Destino ");
        TextArea bodyEmail = new TextArea("Texto del Mensaje");
                
        Button button = new Button("Enviar e-mail con Jakarta Mail");
        
        add(email, bodyEmail, button);
        
        
        //Configurar el mesaje de e.mail 
        Message messaje = new MimeMessage(mailConfig.getMailSession());
        
        
        button.addClickListener((event) -> {
            
            try {
                    messaje.setSubject("Hello World from Jakarta EE Mail API!");
                    messaje.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getValue()));
                    MimeMultipart mailContent = new MimeMultipart();
                    MimeBodyPart mailMessage = new MimeBodyPart();
                    mailMessage.setContent("<p>Bienvenido a <b>Jakarta Mail</b> API</p> <br>"+bodyEmail.getValue() , "text/html;");
                    mailContent.addBodyPart(mailMessage);
                    messaje.setContent(mailContent);
                    Transport.send(messaje);

                    Notification.show("Mensaje Enviado.!! a " + email.getValue());
                  } catch (MessagingException e) {
                    e.printStackTrace();
                  }
            
            
            
            
                     
            
        });
        

        
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

       
        button.addClickShortcut(Key.ENTER);

        

        
    }

}
