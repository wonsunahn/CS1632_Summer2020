
/**
 * Code by @author Wonsun Ahn
 * CarnivalShooter: Using arbitrary trial and shooter numbers, find how many targets are hit in those trials
 */

public interface DrunkCarnivalShooter {
	/**
	 * Returns the either BeanCounterLogicImpl or BeanCounterLogicBuggy instance
	 * depending on the Config.
	 * 
	 * @param slotCount the number of slots in the machine
	 * @return BeanCounterLogic object
	 */
	public static DrunkCarnivalShooter createInstance() {
		if (Config.getBuggy()) {
			return new DrunkCarnivalShooterBuggy();
		} else {
			return new DrunkCarnivalShooterImpl();
		}
	}
	
	public String getRoundString();
	public boolean shoot(int t, StringBuilder builder);	
	public boolean takeDownTarget(int t);
	public boolean isTargetStanding(int t);
	public int getRemainingTargetNum();
}
