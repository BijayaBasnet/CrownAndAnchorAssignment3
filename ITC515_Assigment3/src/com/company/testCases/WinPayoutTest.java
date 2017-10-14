package com.company.testCases;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;


import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.company.Dice;
import com.company.DiceValue;
import com.company.Game;
import com.company.Player;

public class WinPayoutTest {

	private static final String NAME = "Fred";
	private static final int startBalance = 100;
	private static final DiceValue betSymbol = DiceValue.CROWN;
	private static final int bet = 5;
	
	private Player player;
	private Dice d1;
	private Dice d2;
	private Dice d3;
	private Game game;
	
	@Before
	public void setUp() throws Exception {
		player = new Player(NAME, startBalance);
		d1 = EasyMock.createMock(Dice.class);
		d2 = EasyMock.createMock(Dice.class);
		d3 = EasyMock.createMock(Dice.class);
		
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
	public void zeroMathes() {
		
		//Create result under test (one match) with mock dice
		expect(d1.roll()).andReturn(DiceValue.ANCHOR).once();
		expect(d1.getValue()).andReturn(DiceValue.ANCHOR).atLeastOnce();
		
		expect(d2.roll()).andReturn(DiceValue.ANCHOR).once();
		expect(d2.getValue()).andReturn(DiceValue.ANCHOR).atLeastOnce();
		
		expect(d3.roll()).andReturn(DiceValue.ANCHOR).once();
		expect(d3.getValue()).andReturn(DiceValue.ANCHOR).atLeastOnce();
		
		replay(d1);
		replay(d2);
		replay(d3);
		
		game = new Game(d1, d2, d3);
			int result = game.playRound(player, betSymbol, bet);
				verify(d1);
		verify(d2);
		verify(d3);
		
		int expectedResult = 0;
		assertEquals(expectedResult, result);
				int expectedBalance = startBalance - bet;

		assertEquals(expectedBalance, player.getBalance());
	}

	@Test
	public void oneMatches() {
		expect(d1.roll()).andReturn(betSymbol).once();
		expect(d1.getValue()).andReturn(betSymbol).atLeastOnce();
		
		expect(d2.roll()).andReturn(DiceValue.ANCHOR).once();
		expect(d2.getValue()).andReturn(DiceValue.ANCHOR).atLeastOnce();
		
		expect(d3.roll()).andReturn(DiceValue.ANCHOR).once();
		expect(d3.getValue()).andReturn(DiceValue.ANCHOR).atLeastOnce();
		
		replay(d1);
		replay(d2);
		replay(d3);
		
		game = new Game(d1, d2, d3);
		
		int result = game.playRound(player, betSymbol, bet);
		
		verify(d1);
		verify(d2);
		verify(d3);
		
		int expectedResult = bet;
		assertEquals(expectedResult, result);
		
		int expectedBalance = startBalance + expectedResult;

		assertEquals(expectedBalance, player.getBalance());
	}
	

	
	@Test
	public void twoMatches() {
		expect(d1.roll()).andReturn(betSymbol).once();
		expect(d1.getValue()).andReturn(betSymbol).atLeastOnce();
		
		expect(d2.roll()).andReturn(betSymbol).once();
		expect(d2.getValue()).andReturn(betSymbol).atLeastOnce();
		
		expect(d3.roll()).andReturn(DiceValue.ANCHOR).once();
		expect(d3.getValue()).andReturn(DiceValue.ANCHOR).atLeastOnce();
		
		replay(d1);
		replay(d2);
		replay(d3);
		
		game = new Game(d1, d2, d3);
		
		int result = game.playRound(player, betSymbol, bet);
		
		verify(d1);
		verify(d2);
		verify(d3);
		
		int expectedResult = bet * 2;
		assertEquals(expectedResult, result);
		
		int expectedBalance = startBalance + expectedResult;

		assertEquals(expectedBalance, player.getBalance());
	}
	
	@Test
	public void threeMatches() {
		expect(d1.roll()).andReturn(betSymbol).once();
		expect(d1.getValue()).andReturn(betSymbol).atLeastOnce();
		
		expect(d2.roll()).andReturn(betSymbol).once();
		expect(d2.getValue()).andReturn(betSymbol).atLeastOnce();
		
		expect(d3.roll()).andReturn(betSymbol).once();
		expect(d3.getValue()).andReturn(betSymbol).atLeastOnce();
		
		replay(d1);
		replay(d2);
		replay(d3);
		
		game = new Game(d1, d2, d3);
		
		int result = game.playRound(player, betSymbol, bet);
		
		verify(d1);
		verify(d2);
		verify(d3);
		
		int expectedResult = bet * 3;
		assertEquals(expectedResult, result);
		
		int expectedBalance = startBalance + expectedResult;

		assertEquals(expectedBalance, player.getBalance());
	}
}
