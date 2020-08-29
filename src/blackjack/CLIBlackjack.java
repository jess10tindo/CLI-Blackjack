package blackjack;

import java.util.*;

public class CLIBlackjack {

	public static void main(String[] args) {
		
		//create deck as list of ints from 0-51 and print
		int[] deck = new int[52];
		//System.out.print("initial deck: " );  // just for printing initial deck
		for(int i = 0; i < deck.length; i++) {
			deck[i] = i;
			//System.out.print(deck[i] + " "); // just for printing initial deck
		}
		//System.out.println();  // just for printing initial deck and spacing
		
		
		// shuffle cards
		for(int i = 0; i < deck.length; i++) {
			int index = (int)(Math.random() * deck.length);
			int temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
		
//		// printing shuffled deck --- not needed for prompt, just for debugging
//		System.out.println();
//		System.out.print("shuffled deck: " );
//		for(int i = 0; i < deck.length; i++) {
//			System.out.print(deck[i] + " ");
//		}
		
		
		//initializing  suit and rank arrays
		String[] stringSuitArray = {"Spades", "Hearts", "Diamonds", "Clubs"};
		String[] stringRankArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
		
		
		// Dealing dealer's hand and printing first card
		System.out.println();
		int cardIndex = 0; // initializing the card index that is being used to ensure each card is only used once

		System.out.println("Dealer's Hand: ");
		String dealersFirstCard = (stringRankArray[deck[cardIndex] % 13] + " of " + stringSuitArray[deck[cardIndex] / 13]);
		cardIndex++; // moving to next card in deck
		String dealersSecondCard = (stringRankArray[deck[cardIndex] % 13] + " of " + stringSuitArray[deck[cardIndex] / 13]);
		cardIndex++;
		System.out.println(dealersFirstCard);
		//System.out.println(dealersSecondCard); // used only to verify later parts of code
		System.out.println();
		
		
		// Dealing Player's Hand and printing both cards
		System.out.println("Player's Hand: ");
		String playersFirstCard = (stringRankArray[deck[cardIndex] % 13] + " of " + stringSuitArray[deck[cardIndex] / 13]);
		cardIndex++;
		String playersSecondCard = (stringRankArray[deck[cardIndex] % 13] + " of " + stringSuitArray[deck[cardIndex] / 13]);
		cardIndex++;
		System.out.println(playersFirstCard + ", " + playersSecondCard);
		System.out.println();
		
		
		
		
		
		// add HIT or STAND functionality here
		
		
		
		
		
		
		//system deals dealer additional cards if total is less than 17 and makes dealer lose if over 21
	
		int dealerCardOneValue = ((deck[0] % 13)+1);
		int dealerCardTwoValue = ((deck[1] % 13)+1);
		
		// System.out.println("card 1 value: " + dealerCardOneValue); // only used for debugging
		// System.out.println("card 2 value: " + dealerCardTwoValue); // only used for debugging
		
		int dealersTotal = dealerCardOneValue + dealerCardTwoValue;
		
		//System.out.println("Dealer's Total: " + dealersTotal); // only used for debugging
		
		while(dealersTotal < 17) {
			int newCardValue = ((deck[cardIndex] % 13)+1); // takes value of next card in sequence
			//System.out.println("new card value: " + newCardValue); // only used for debugging
			dealersTotal += newCardValue;
		}
		//System.out.println("Dealers new total: " + dealersTotal); // only used for debugging
		
		if(dealersTotal > 21) {
			System.out.println("Dealer Busts, you win!");
		}
		else if(dealersTotal == 21) {
			System.out.println("Dealer got blackjack, you lose :(");
		}
		else {
			//compare players hand to dealers hand and display who wins
		}
		
	}
}