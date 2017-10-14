package com.company.testCases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.company.Dice;
import com.company.DiceValue;

public class NoSpadeRollTest {
	

	private Dice d1;
	
	@Before
	public void setUp() throws Exception {
		d1 = new Dice();
		
	}

	@After
	public void tearDown() throws Exception {

		d1 = null;

	}

	@Test
	public void testRollAllValues() {

		List<DiceValue> results = new ArrayList<DiceValue>();
		
		int numRolls = 5000;
		
		for (int roll = 0; roll < numRolls; roll++) {
			DiceValue rolled = d1.roll();
			System.out.printf("Roll #%d, rolled %s\n", roll, rolled.toString());
			results.add(rolled);
		}
		
		for (DiceValue value : DiceValue.values()) {
			assertTrue(String.format("Results do not contain: %s", value.toString()), results.contains(value));
		}
	}
	

}
