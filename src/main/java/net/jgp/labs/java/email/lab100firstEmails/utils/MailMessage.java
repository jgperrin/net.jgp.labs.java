package net.jgp.labs.java.email.lab100firstEmails.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.InternetAddress;

public class MailMessage {
	private static final String HEADER = "Ranch Tested. Rancher Trusted.<br/><b>Red Angus</b><hr/>";
	private static final String SIGNATURE = "<hr/><p>This message was sent from RAAA. <br/>Red Angus Association of America (RAAA), 4201 N Interstate 35, Denton, Texas 76207-3415. Phone (940) 387-3502.</p>";
	private String subject = null;
	private String body = null;
	private String lastError = null;
	private String salutation = null;
	private ArrayList<InternetAddress> recipientsTo = null;
	private ArrayList<InternetAddress> recipientsCc = null;
	private ArrayList<InternetAddress> recipientsBcc = null;

	public MailMessage() {
		this.subject = null;
		this.body = null;
		this.salutation = null;
		this.recipientsTo = new ArrayList<InternetAddress>();
		this.recipientsCc = new ArrayList<InternetAddress>();
		this.recipientsBcc = new ArrayList<InternetAddress>();
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void addTo(String emailAddress) {
		addTo(emailAddress, emailAddress);
	}

	public void addTo(String emailAddress, String personalName) {
		try {
			this.recipientsTo.add(new InternetAddress(emailAddress,
					personalName));
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(MailMessage.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public boolean send() {
		return MailSender.send(this);
	}

	public String getLastError() {
		return MailSender.getLastError();
	}

	String getHtmlContent() {
		String msg = null;

		msg = MailMessage.HEADER + "<p>" + this.salutation + ",</p><p>"
				+ this.body + "</p>" + MailMessage.SIGNATURE;

		return msg;
	}

	String getTextContent() {
		// TODO
		return "";//HtmlUtils.htmlToText(this.getHtmlContent());
	}

	String getSubject() {
		return this.subject;
	}

	ArrayList<InternetAddress> getToRecipients() {
		return this.recipientsTo;
	}

	ArrayList<InternetAddress> getCcRecipients() {
		return this.recipientsCc;
	}

	ArrayList<InternetAddress> getBccRecipients() {
		return this.recipientsBcc;
	}
}
