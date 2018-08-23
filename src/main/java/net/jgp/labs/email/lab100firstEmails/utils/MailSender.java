package net.jgp.labs.email.lab100firstEmails.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.validator.EmailValidator;

public class MailSender {
	/**
	 * Logger
	 */
	private static Logger log = LoggerFactory.getLogger(MailSender.class);
	
	// insures unique instance (Singleton)
	static private MailSender instance = null;
	private String smtpServer = null;
	private String smtpPassword = null;
	private String smtpUser = null;
	private String lastError = null;
	private String senderEmailAddress = null;
	private String senderName = null;
	// Constants
	public final static String MAIL_SMTP_HOST = "mail.smtp.host";
	public final static String MAIL_SMTP_USER = "mail.smtp.user";
	public final static String MAIL_SMTP_PASSWORD = "mail.smtp.password";

	private MailSender() {
	}

	/**
	 * Private constructor / instance builder.
	 */
	static private MailSender getInstance() {
		if (MailSender.instance == null) {
			MailSender.instance = new MailSender();
		}

		return MailSender.instance;
	}

	public static boolean send(MailMessage mailMessage) {
		return getInstance().send0(mailMessage);
	}

	public static void setSmtpServer(String smtpServer) {
		getInstance().setSmtpServer0(smtpServer);
	}

	public static void setSmtpUser(String smtpUser) {
		getInstance().setSmtpUser0(smtpUser);
	}

	public static void setSmtpPassword(String smtpPassword) {
		getInstance().setSmtpPassword0(smtpPassword);
	}

	public static String getLastError() {
		return getInstance().lastError;
	}

	/**
	 * Creates a SMTP session to send e-mails.
	 * 
	 * @return
	 */
	private Session createSmtpSession() throws MailException {

		Properties props = null;

		// Generic properties
		props = new Properties();
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.starttls.required", "true");
		props.setProperty("mail.smtp.auth", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		// SMTP Server
		if (this.smtpServer == null) {
			throw new MailException("SMTP server is undefined");
		}
		props.setProperty(MailSender.MAIL_SMTP_HOST, this.smtpServer);

		if (this.smtpUser == null) {
			throw new MailException("SMTP user is undefined");
		}
		props.setProperty(MailSender.MAIL_SMTP_USER, this.smtpUser);

		if (this.smtpPassword == null) {
			throw new MailException("SMTP password is undefined");
		}
		props.setProperty(MailSender.MAIL_SMTP_PASSWORD, this.smtpPassword);

		return Session.getDefaultInstance(props, null);
	}

	/**
	 * Real sender
	 * 
	 * @param mailMessage
	 * @return
	 */
	private boolean send0(MailMessage mailMessage) {
		// Variables
		Multipart multipart = null;
		MimeBodyPart htmlContentPart = null;
		MimeBodyPart textContentPart = null;
		Session mailSession = null;
		MimeMessage email = null;
		Transport transport = null;

		// Setup
		try {
			mailSession = createSmtpSession();
		} catch (MailException ex) {
			this.lastError = "Could not create session: " + ex.getMessage();
			log.error(this.lastError);
			return false;
		}
		mailSession.setDebug(true);

		try {
			transport = mailSession.getTransport("smtp");
		} catch (NoSuchProviderException ex) {
			this.lastError = "Unknown mail provider: " + ex.getMessage();
			log.error(this.lastError);
			return false;
		}
		if (transport == null) {
			this.lastError = "Could not initialize transport";
			log.error(this.lastError);
			return false;
		}

		email = new MimeMessage(mailSession);

		// building mail
		htmlContentPart = new MimeBodyPart();
		try {
			htmlContentPart.setContent(mailMessage.getHtmlContent(),
					"text/html");
		} catch (MessagingException ex) {
			this.lastError = "Error setting HTML content: " + ex.getMessage();
			log.error(this.lastError);
			return false;
		}

		textContentPart = new MimeBodyPart();
		try {
			textContentPart.setContent(mailMessage.getTextContent(),
					"text/plain");
		} catch (MessagingException ex) {
			this.lastError = "Error setting text content: " + ex.getMessage();
			log.error(this.lastError);
			return false;
		}

		multipart = new MimeMultipart("alternative");
		try {
			multipart.addBodyPart(textContentPart);
			multipart.addBodyPart(htmlContentPart);
		} catch (MessagingException ex) {
			this.lastError = "Error building MIME multipart: "
					+ ex.getMessage();
			log.error(this.lastError);
			return false;
		}

		// Ready to go

		// Process attachments
		// if (attachmentList != null) {
		// for (File file : attachmentList) {
		// MimeBodyPart attachmentPart = new MimeBodyPart();
		// FileDataSource fileDataSource = new FileDataSource(file) {
		// @Override
		// public String getContentType() {
		// return "application/octet-stream";
		// }
		// };
		// try {
		// attachmentPart.setDataHandler(new DataHandler(fileDataSource));
		// attachmentPart.setFileName(fileDataSource.getName());
		// multipart.addBodyPart(attachmentPart);
		// } catch (MessagingException ex) {
		// this.lastError =
		// "Error adding attachments: " + ex.getMessage();
		// log.error(this.lastError);
		// return false;
		// }
		// }
		// }

		try {
			email.setSubject(mailMessage.getSubject());
			String senderEmailAddressLocal = null;
			String senderNameLocal = null;

			if (senderEmailAddress == null) {
				senderEmailAddressLocal = this.smtpUser;
			}
			if (senderName == null) {
				senderNameLocal = this.smtpUser;
			}

			email.setFrom(new InternetAddress(senderEmailAddressLocal,
					senderNameLocal));
			email.setContent(multipart);
		} catch (MessagingException ex) {
			this.lastError = "Error building email: " + ex.getMessage();
			log.error(this.lastError);
			return false;
		} catch (UnsupportedEncodingException ex) {
			this.lastError = "Encoding issue in sender's email or name: "
					+ ex.getMessage();
			log.error(this.lastError);
			return false;
		}

		// Adding recipients
		ArrayList<InternetAddress> recipients = null;

		recipients = mailMessage.getToRecipients();
		try {
			addRecipientsToEmail(Message.RecipientType.TO, email, recipients);
		} catch (MessagingException ex) {
			this.lastError = "Error adding recipients (To) to email: "
					+ ex.getMessage();
			log.error(this.lastError);
			return false;
		}

		recipients = mailMessage.getCcRecipients();
		try {
			addRecipientsToEmail(Message.RecipientType.CC, email, recipients);
		} catch (MessagingException ex) {
			this.lastError = "Error adding recipients (CC) to email: "
					+ ex.getMessage();
			log.error(this.lastError);
			return false;
		}

		recipients = mailMessage.getBccRecipients();
		try {
			addRecipientsToEmail(Message.RecipientType.BCC, email, recipients);
		} catch (MessagingException ex) {
			this.lastError = "Error adding recipients (BCC) to email: "
					+ ex.getMessage();
			log.error(this.lastError);
			return false;
		}

		try {
			transport
					.connect(this.smtpServer, this.smtpUser, this.smtpPassword);
		} catch (MessagingException ex) {
			this.lastError = "Could not connect: " + ex.getMessage();
			log.error(this.lastError);
			return false;
		}

		// Final tests
		try {

			if (email.getAllRecipients() == null) {
				this.lastError = "No recipient in mail";
				log.error(this.lastError);
				return false;
			}
		} catch (MessagingException ex) {
			this.lastError = "Error while accessing recipients: "
					+ ex.getMessage();
			log.error(this.lastError);
			return false;
		}

		try {
			transport.sendMessage(email, email.getAllRecipients());
		} catch (MessagingException ex) {
			this.lastError = "Could not send message: " + ex.getMessage();
			log.error(this.lastError);
			return false;
		}

		return true;
	}

	private void setSmtpServer0(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	private void setSmtpPassword0(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	private void setSmtpUser0(String smtpUser) {
		this.smtpUser = smtpUser;
		if (this.senderEmailAddress == null) {
			this.senderEmailAddress = this.smtpUser;
		}
	}

	private void addRecipientsToEmail(RecipientType type, MimeMessage email,
			ArrayList<InternetAddress> recipients) throws MessagingException {
		EmailValidator validator = null;
		validator = EmailValidator.getInstance();

		if (recipients != null) {
			for (int i = 0; i < recipients.size(); i++) {
				if (validator.isValid(recipients.get(i).getAddress()) == true) {
					email.addRecipient(type, recipients.get(i));
				}
			}
		}
	}
}
