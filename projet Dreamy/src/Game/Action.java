package Game;

/**
 * Action allows to manage the actions generated by the boosters
 * available on the store
 * 
 * @author ismail El Alout
 */
public class Action {

	// true if the corresponding booster is purchased 
	private boolean state;
	private int number;

	/**
	 * Action constructor
	 */
	public Action() {
		this.state = false;
		this.number = 0;
	}

	/**
	 * set the state of the action
	 * @param state
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * set the number of the action
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
     * the state of the action
     *
     * @return state
     */
	public boolean getState() {
		return this.state;
	}
	/**
     * the number of the action
     *
     * @return number
     */
	public int getNumber() {
		return this.number;
	}

	public boolean isValid() {
		if (this.getState() && this.getNumber() != 0) {
			return true;
		} else {
			return false;
		}
	}
}