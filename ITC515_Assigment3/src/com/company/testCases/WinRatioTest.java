package com.company.testCases;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.company.Dice;
import com.company.DiceValue;
import com.company.Game;
import com.company.Player;

public class WinRatioTest {

	private static final String NAME = "Fred";
	private static final int startBalance = 1000;
	private static final int BetLimit = 0;
	private static final DiceValue PICK_CROWN = DiceValue.CROWN;
	private static final int BetAmount = 5;
	
	private static final double WIN_RATE = 0.42;
	private static final double WIN_RATE_VARIANCE = 0.03;
	private static final double MAX_WIN_RATE = WIN_RATE + WIN_RATE_VARIANCE;
	private static final double MIN_WIN_RATE = WIN_RATE - WIN_RATE_VARIANCE;
	
	private Player player;
	private Dice d1;
	private Dice d2;
	private Dice d3;
	private Game game;
	
	@Before
	public void setUp() throws Exception {
		d1 = new Dice();
		d2 = new Dice();
		d3 = new Dice();
		
		player = new Player(NAME, startBalance);
		player.setLimit(BetLimit);
		
		game = new Game(d1, d2, d3);
	}

	@After
	public void tearDown() throws Exception {
		player = null;
		d1 = null;
		d2 = null;
		d3 = null;
		game = null;
	}

	@Test
	public void test() {
		
		int winCount = 0;
		int lossCount = 0;
		
		
		int numRounds = 1000;
		
		for (int round = 1; round <= numRounds; round++) { 
	        	
			if (player.balanceExceedsLimitBy(BetAmount)) {
				
	        	int winnings = game.playRound(player, PICK_CROWN, BetAmount);
	            List<DiceValue> cdv = game.getDiceValues();
	            
	            System.out.printf("Rolled %s, %s, %s\n",
	            		cdv.get(0), cdv.get(1), cdv.get(2));
	            
	            if (winnings > 0) {
	                System.out.printf("%s won %d, balance now %d\n\n",
	                		player.getName(), winnings, player.getBalance());
	                winCount++;
	            }
	            else {
	                System.out.printf("%s lost, balance now %d\n\n",
	                		player.getName(), player.getBalance());
	                lossCount++;
	            }
			}
		}
		
		double winRate = (winCount * 1.0)/(winCount + lossCount*1.0);
		assertTrue(String.format("Win loss ratio too great: %.2f", winRate), winRate <= MAX_WIN_RATE);
		assertTrue(String.format("Win loss ratio too little: %.2f", winRate), winRate >= MIN_WIN_RATE);
		
		
	}
	

}
