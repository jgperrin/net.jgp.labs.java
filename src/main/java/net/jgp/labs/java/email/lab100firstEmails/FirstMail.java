package net.jgp.labs.java.email.lab100firstEmails;

import net.jgp.labs.java.email.lab100firstEmails.utils.MailMessage;
import net.jgp.labs.java.email.lab100firstEmails.utils.MailSender;
import net.jgp.labs.utils.DebugUtils;

public class FirstMail {

  public static void main(String[] args) {
    System.out.println("Sending e-mail");

    // config
    MailSender.setSmtpServer("smtp.gmail.com");
    MailSender.setSmtpUser("user@mailservice");
    MailSender.setSmtpPassword("password");

    // Message prep
    MailMessage mm = null;
    boolean emailSentSuccessfully = false;

    mm = new MailMessage();
    mm.setSubject(
        "Your account number request for Smurf v1");
    mm.setSalutation("Dear Jean Georges,");
    mm.setBody("Lorem Ipsum");
    mm.addTo("jgp@jgp.net", "xyz");
    emailSentSuccessfully = mm.send();
    if (emailSentSuccessfully == true) {
      System.out.println("Done");
    } else {
      System.out.println("Fail: " + mm.getLastError());
    }

    DebugUtils.printClassPath();
    DebugUtils.printSplittedClassPath();
  }

}
