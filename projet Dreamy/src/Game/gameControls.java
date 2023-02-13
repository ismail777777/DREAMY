package Game;

public class gameControls {

	private boolean key_move;
	private boolean key_jump;
	
	public boolean isKey_move() {
		return key_move;
	}
	
	public boolean isKey_jump() {
		return key_jump;
	}
	
	public void setKey_move(boolean key) {
		this.key_move = key;
	}
	
	public void setKey_jump(boolean key) {
		this.key_jump = key;
	}
}
