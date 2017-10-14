package com.company.testCases;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.company.Dice;
import com.company.Game;



public class DiceRollTest {
    
    Dice dice; 
    Dice dice2;
    Dice dice3;
    
    Game game;
    
    @Before
    public void setUp() {
        dice = new Dice();
        dice2=new Dice();
        dice3=new Dice();
    }
    
    @After
    public void tearDown() {
        dice = null;
        dice2=null;
        dice3=null;
    }
    
    @Test
    public void testDiceValueUpdate() {
        for(int i = 0; i <= 10;  i++) {
            dice.roll();
            System.out.println(dice.getValue());
            System.out.println(dice2.getValue());
            System.out.println(dice3.getValue());
            System.out.println("---- Next Round-------------------");
            
        }
    }
    
}
