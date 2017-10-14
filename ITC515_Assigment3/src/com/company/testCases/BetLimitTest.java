package com.company.testCases;


import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.company.Dice;
import com.company.DiceValue;
import com.company.Game;
import com.company.Player;

public class BetLimitTest {

	private static final String NAME = "Fred";
	private static final int startBalance = 10;
	private static final int betLimit = 0;
	private static final DiceValue bet_on_symbol = DiceValue.CROWN;
	private static final int betAmount = 5;
	
	private Player player;
	private Dice d1;
	private Dice d2;
	private Dice d3;
	private Game game;
	
	@Before
	public void setUp() throws Exception {
		player = new Player(NAME, startBalance);
		player.setLimit(betLimit);
		d1=new Dice();
		d2=new Dice();
		d3=new Dice();
		
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
		game = new Game(d1, d2, d3);
		
		do {
        	game.playRound(player, bet_on_symbol, betAmount);
		} while (player.balanceExceedsLimitBy(betAmount));
		

		
		assertEquals(betLimit, player.getBalance());
	}
	
	@Test
	public void testPlayerBalanceExceedsLimit() {
		assertTrue("Player balance does not exceed limit", player.balanceExceedsLimit());
		assertTrue("Player balance does not reach end limit of 0", player.balanceExceedsLimitBy(startBalance));
	}
	

}
