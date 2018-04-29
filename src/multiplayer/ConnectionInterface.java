package multiplayer;

public interface ConnectionInterface {
	public void sendResponse(String response);
	public String waitForResponse();
	public boolean isConnected();
}
