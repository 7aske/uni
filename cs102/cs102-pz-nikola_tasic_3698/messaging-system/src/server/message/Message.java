package server.message;

import http.Response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
	private long messageid;
	private String sentTo;
	private String sentFrom;
	private String messageText;
	private long dateSent;
	public static final String[] fieldList = {"messageid", "sentTo", "sentFrom", "messageText", "dateSent"};

	private Message() {
	}

	public Message(String sentTo, String sentFrom, String messageText, long dateSent) {
		this.sentTo = sentTo;
		this.sentFrom = sentFrom;
		this.messageText = messageText;
		this.dateSent = dateSent;
	}

	public Message(long id, String sentTo, String sentFrom, String messageText, long dateSent) {
		this.messageid = id;
		this.sentTo = sentTo;
		this.sentFrom = sentFrom;
		this.messageText = messageText;
		this.dateSent = dateSent;
	}

	public static Message fromForm(String formString) {
		HashMap<String, String> form = new HashMap<>();
		String[] pairs = formString.split("&");
		for (String pair : pairs) {
			String[] fields = pair.split("=");
			if (fields.length == 2) {
				form.put(fields[0], fields[1]);
			}
		}
		return Message.fromForm(form);
	}

	public static Message fromForm(HashMap<String, String> form) {
		Message message = new Message();
		for (Map.Entry<String, String> kv : form.entrySet()) {
			switch (kv.getKey()) {
				case "sentTo":
					message.sentTo = kv.getValue();
					break;
				case "sentFrom":
					message.sentFrom = kv.getValue();
					break;
				case "messageText":
					message.messageText = kv.getValue();
					break;
				case "dateSent":
					message.dateSent = Long.parseLong(kv.getValue().trim());
					break;
			}
		}
		return message;
	}

	public long getMessageid() {
		return messageid;
	}

	public void setMessageid(long messageid) {
		this.messageid = messageid;
	}

	public String getSentFrom() {
		return sentFrom;
	}

	public void setSentFrom(String sentFrom) {
		this.sentFrom = sentFrom;
	}

	public String getSentTo() {
		return sentTo;
	}

	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public long getDateSent() {
		return dateSent;
	}

	public void setDateSent(long dateSent) {
		this.dateSent = dateSent;
	}

	public boolean isValid() {
		if (this.getSentTo() == null)
			return false;
		if (this.getSentFrom() == null)
			return false;
		if (this.getMessageText() == null)
			return false;
		return this.getDateSent() != 0;
	}
	public String asRequestString() {
		return "sentFrom=" + sentFrom +
				"&sentTo=" + sentTo +
				"&messageText=" + messageText +
				"&dateSent=" + dateSent + Response.CLRF;
	}
	public String asResponseString() {
		return "messageid=" + messageid +
				"&sentFrom=" + sentFrom +
				"&sentTo=" + sentTo +
				"&messageText=" + messageText +
				"&dateSent=" + dateSent + Response.CLRF;
	}

	@Override
	public String toString() {
		return "Message{" +
				"messageid=" + messageid +
				", sentFrom='" + sentFrom + '\'' +
				", sentTo='" + sentTo + '\'' +
				", messageText='" + messageText + '\'' +
				", dateSent=" + dateSent +
				'}';
	}

	public String toPrettyString() {
		return "From: " + sentFrom +
				"\nTo: "  + sentTo +
				"\nMessage:\n" + messageText;
	}
}
