package com.hotel.reservation.infrastructure;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class EmailSender {

    private static final String FROM = "oceanviewhotelsystem@gmail.com\n";
    private static final String PASSWORD = "hzvjjgmgibcxijxc";

    public void sendEmail(String to, String subject, String messageText) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM, PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);

            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException("Email sending failed", e);
        }
    }
}
