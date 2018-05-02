package multiplayer;

import java.io.Serializable;

//Et objekt som er meldingen som sendes via sockets mellom server og klient
public class Message implements Serializable {
	private String msg;
	
	public Message(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
	
}
