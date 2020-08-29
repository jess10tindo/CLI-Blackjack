package blackjack;

import java.util.*;

public class CLIBlackjack {

	public static void main(String[] args) {
		
		String playAgain = "Y";
		
		while(playAgain.equals("Y")){
		
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
			System.out.println("Your Hand: ");
			String playersFirstCard = (stringRankArray[deck[cardIndex] % 13] + " of " + stringSuitArray[deck[cardIndex] / 13]);
			cardIndex++;
			String playersSecondCard = (stringRankArray[deck[cardIndex] % 13] + " of " + stringSuitArray[deck[cardIndex] / 13]);
			cardIndex++;
			System.out.println(playersFirstCard + ", " + playersSecondCard);
			System.out.println();
			
			
			int userCardOneValue = ((deck[2] % 13)+1);
			int userCardTwoValue = ((deck[3] % 13)+1);	
			
			if (userCardOneValue > 10) {
				userCardOneValue = 10;
			}
			
			if (userCardTwoValue > 10) {
				userCardTwoValue = 10;
			}
			
			int userTotal = userCardOneValue + userCardTwoValue;
			
			Scanner input = new Scanner(System.in);
			
			if(userTotal<21) {
				System.out.println("Would you like to HIT or STAND? ");
				String userInput = input.nextLine();
				
				do {
					//System.out.println("Current total: " + userTotal);
					if(userTotal <= 21 && userInput.equals("HIT")) {
						String playersNewCard = (stringRankArray[deck[cardIndex] % 13] + " of " + stringSuitArray[deck[cardIndex] / 13]);
						System.out.println("Your New card is: " + playersNewCard);
						System.out.println();
						int newUserCard = (deck[cardIndex] % 13) +1;
						if(newUserCard > 10) {
							newUserCard = 10;
						}
						userTotal+= newUserCard;
						//System.out.println("What is being added: " + newUserCard);
						//System.out.println("Your new total: "+ userTotal);
						cardIndex++;
						if(userTotal < 21) {
							System.out.println("Would you like to HIT or STAND? ");
							userInput = input.nextLine();
						}
						else {
						}
						
					}
					else if (userTotal < 21 && userInput.equals("STAND")) {
						System.out.println("You chose to STAND - let's see what happened with the dealer...");
					}
					
					else {
					}	
				}
				while(userInput.equals("HIT") && userTotal < 21) ;	
			}
			
			//system deals dealer additional cards if total is less than 17 and makes dealer lose if over 21
		
			int dealerCardOneValue = ((deck[0] % 13)+1);
			int dealerCardTwoValue = ((deck[1] % 13)+1);
			
			// System.out.println("card 1 value: " + dealerCardOneValue); // only used for debugging
			// System.out.println("card 2 value: " + dealerCardTwoValue); // only used for debugging
			
			int dealersTotal = dealerCardOneValue + dealerCardTwoValue;
			
			//System.out.println("Dealer's Total: " + dealersTotal); // only used for debugging
			
			while(dealersTotal < 17) {
				int newDealerCard = ((deck[cardIndex] % 13)+1); // takes value of next card in sequence
				if (newDealerCard > 10) {
					newDealerCard = 10;
				}
				//System.out.println("new card value: " + newDealerCard); // only used for debugging
				dealersTotal += newDealerCard;
				//System.out.println("new dealer card: " + (stringRankArray[deck[cardIndex] % 13] + " of " + stringSuitArray[deck[cardIndex] / 13]));
				cardIndex++;
			}
			
			//System.out.println("Dealers new total: " + dealersTotal); // only used for debugging
			
			System.out.println();
			System.out.println("Result: ");
			System.out.println("Dealer's Total: " + dealersTotal);
			System.out.println("Your Total: " + userTotal);
			
			if(dealersTotal > 21 && userTotal> 21) {
				System.out.println("--------------");
				System.out.println("No one wins");
			}
			
			else if(dealersTotal > 21 && userTotal <= 21) {
				System.out.println("--------------");
				System.out.println("Dealer Busts. You Win! :)");
			}
			
			else if(dealersTotal == 21 && userTotal != 21) {
				System.out.println("--------------");
				System.out.println("Dealer got blackjack, you lose :(");
			}
			
			else if(dealersTotal == 21 && userTotal == 21) {
				System.out.println("--------------");
				System.out.println("You both got 21, but the dealer wins :(");
			}
			
			else if (dealersTotal < 21) {
				if(userTotal <  21 && userTotal > dealersTotal){
					System.out.println("--------------");
					System.out.println("You win! :)");
				}
				if(userTotal ==  21){
					System.out.println("--------------");
					System.out.println("Blackjack! You win! :)");
				}
				else if(userTotal < dealersTotal) {
					System.out.println("--------------");
					System.out.println("Sorry! Dealer wins");
				}
				else if(userTotal > 21) {
					System.out.println("--------------");
					System.out.println("You lose :(");
				}
			}
			System.out.println("--------------");
		System.out.println("Would you like to play again? (Y / N)");
		playAgain = input.next();
		}
		
		if(playAgain.equals("N")) {
			System.out.println("Goodbye!");
		}
	}
}