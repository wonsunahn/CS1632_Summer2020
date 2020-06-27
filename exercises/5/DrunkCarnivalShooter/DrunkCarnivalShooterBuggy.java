
/**
 * Code by @author Wonsun Ahn
 * DrunkCarnivalShooter: A carnival shooter with four targets, but while drunk!
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DrunkCarnivalShooterBuggy implements DrunkCarnivalShooter {
	Random rand;

	private static ArrayList<Boolean> targets;
	private static int remainingTargetNum;

	private static int roundNum;

	DrunkCarnivalShooterBuggy() {
		rand = new Random();
		targets = new ArrayList<Boolean>();
		remainingTargetNum = 4;
		for (int i = 0; i < remainingTargetNum; i++) {
			targets.add(true);
		}
	}

	private int shootFuzz(int t, StringBuilder builder) {
		int offsetNum = rand.nextInt(3) - 1;
		int fuzzedT = t + offsetNum;
		if(offsetNum > 0) {
			builder.append("You aimed at target #" + t + " but the Force pulls your bullet to the right.\n");
		}
		else if(offsetNum < 0) {
			builder.append("You aimed at target #" + t + " but the Force pulls your bullet to the left.\n");
		}
		return fuzzedT;
	}
	
	public String getRoundString() {
		String ret = "Round #" + roundNum + ":";
		for (boolean standing : targets) {
			if (standing) {
				ret += "  ||  ";
			}
			else {
				ret += "      ";
			}
		}
		return ret;
	}

	public boolean shoot(int t, StringBuilder builder) {
		int newT = shootFuzz(t, builder);
		if (takeDownTarget(newT)) {
			builder.append("You hit target #" + newT + "! \"The Force is strong with this one.\", Darth opines.\n");
			remainingTargetNum--;
			return true;
		} else {
			builder.append("You miss! \"Do or do not. There is no try.\", Yoda chides.\n");
			return false;
		}
	}
	
	public boolean takeDownTarget(int t) {
		if(isTargetStanding(t)) {
			targets.set(t, false);
			remainingTargetNum--;
			return true;
		}
		return false;
	}
	
	public boolean isTargetStanding(int t) {
		return t >=0 && t < targets.size() && targets.get(t);
	}

	public int getRemainingTargetNum() {
		return remainingTargetNum;
	}

	public static void main(String[] args) {
		int t;
		DrunkCarnivalShooterBuggy shooter = new DrunkCarnivalShooterBuggy();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println(shooter.getRoundString());
			System.out.println("Choose your target (0-3): ");
			t = scanner.nextInt();
			
			// Shoot the target
			StringBuilder builder = new StringBuilder();
			shooter.shoot(t, builder);
			
			// Print result of the shooting
			System.out.println(builder.toString());
			
			// If no remaining targets, game over!
			if (shooter.getRemainingTargetNum() == 0) {
				System.out.println("You decimate all the targets. Where is my prize?");
				break;
			}
			
			// Increment round sequence number
			roundNum++;

		}
		scanner.close();
	}
}
