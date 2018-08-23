package net.jgp.labs.email.lab100firstEmails;

import net.jgp.labs.email.lab100firstEmails.utils.DebugUtils;
import net.jgp.labs.email.lab100firstEmails.utils.MailMessage;
import net.jgp.labs.email.lab100firstEmails.utils.MailSender;

public class FirstMail {

	public static void main(String[] args) {
		System.out.println("Sending e-mail");

		// config
		MailSender.setSmtpServer("smtp.gmail.com");
		MailSender.setSmtpUser("emailsender@redangus.org");
		MailSender.setSmtpPassword("SwP12%send#1");

		// Message prep
		MailMessage mm = null;
		boolean emailSentSuccessfully = false;

		mm = new MailMessage();
		mm.setSubject("Your account number request for REDSPro");
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
