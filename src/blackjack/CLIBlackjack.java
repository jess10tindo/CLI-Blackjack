package blackjack;

import java.util.*;

public class CLIBlackjack {

	public static void main(String[] args) {
		
		String playAgain = "Y";
		int totalWins = 0;
		
		while(playAgain.equals("Y")){
				
			int[] deck = createDeck(52);
			
			shuffleDeck(deck);	
			
			String[] stringSuitArray = {"Spades", "Hearts", "Diamonds", "Clubs"};
			String[] stringRankArray = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
			
			int cardIndex = 0;
			System.out.println("Dealer's Hand:");
			String dealersFirstCard = getNextCard(deck, stringRankArray, stringSuitArray, cardIndex);		
			int dealerCardOneValue = getCardValue(deck, cardIndex);
			cardIndex++;
			String dealersSecondCard = getNextCard(deck, stringRankArray, stringSuitArray, cardIndex);
			int dealerCardTwoValue = getCardValue(deck, cardIndex);
			cardIndex++;
			int dealersTotal = dealerCardOneValue + dealerCardTwoValue;
			System.out.println(dealersFirstCard + "\n");
			
					
			System.out.println("Your Hand: ");
			String playersFirstCard = getNextCard(deck, stringRankArray, stringSuitArray, cardIndex);
			int userCardOneValue = getCardValue(deck, cardIndex);
			cardIndex++;
			String playersSecondCard = getNextCard(deck, stringRankArray, stringSuitArray, cardIndex);
			int userCardTwoValue = getCardValue(deck, cardIndex);
			cardIndex++;
			System.out.println(playersFirstCard + ", " + playersSecondCard + "\n");				
			int userTotal = userCardOneValue + userCardTwoValue;
			
			Scanner input = new Scanner(System.in);
			
			if(userTotal<21) {
				System.out.println("Would you like to HIT or STAND? ");
				String userInput = input.nextLine();
				
				do {
					if(userTotal <= 21 && userInput.equals("HIT")) {
						String playersNewCard = getNextCard(deck, stringRankArray, stringSuitArray, cardIndex);
						System.out.println("Your New card is: " + playersNewCard + "\n");
						int newUserCardValue = getCardValue(deck, cardIndex);
					
						userTotal+= newUserCardValue;
						
						cardIndex++;
						if(userTotal < 21) {
							System.out.println("Would you like to HIT or STAND? ");
							userInput = input.nextLine();
						}
						else {
						}
					}
					
					else {
					}	
				}
				while(userInput.equals("HIT") && userTotal < 21) ;	
			}
						
			while(dealersTotal < 17) {
				int newDealerCardValue = getCardValue(deck, cardIndex);
				dealersTotal += newDealerCardValue;
				cardIndex++;
			}
						
			System.out.println();
			System.out.println("Result: ");
			System.out.println("Dealer's Total: " + dealersTotal);
			System.out.println("Your Total: " + userTotal);
			System.out.println();

			
			String result = getResult(userTotal, dealersTotal);
			System.out.println(result);
			
			if(result.equals("You win! :) ")) {
				totalWins++;
			}
			
			System.out.println("------------------------");	
			System.out.println("Would you like to play again? (Y / N)");
			playAgain = input.next();
			
			if(playAgain.equals("Y")) {
				System.out.println("--------------------------------------------------------------");
				System.out.println("               NEW GAME    (Total wins so far: " + totalWins + ")"
						);
				System.out.println("--------------------------------------------------------------");
			}
		
			if(playAgain.equals("N")) {
				System.out.println("------------------------");
				System.out.println("Thank you for playing - Goodbye!");
			}
		}	
	}
	
	public static int[] createDeck(int numOfCards) {
		int[] deck = new int[numOfCards];
		for(int i = 0; i < deck.length; i++) {
			deck[i] = i;
		}
		return deck;
	}
	
	public static int[] shuffleDeck(int[] deck) {
		
		for(int i = 0; i < deck.length; i++) {
			int index = (int)(Math.random() * deck.length);
			int temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
		return deck;
	}
	
	public static int getCardValue(int[] deck, int cardIndex) {
		int cardRank = ((deck[cardIndex] % 13)+1);
		int cardValue = cardRank;
		if(cardRank > 10) {
			cardValue = 10; 
		}
		return cardValue;
	}
	
	public static String getNextCard(int[] deck, String[] stringRankArray, String[] stringSuitArray, int cardIndex) {
		String nextCard = (stringRankArray[deck[cardIndex] % 13] + " of " + stringSuitArray[deck[cardIndex] / 13]);
		return nextCard;
	}
	
	public static String getResult(int userTotal, int dealersTotal) {
		String gameOutcome = null;
		if(userTotal > 21) {
			gameOutcome = ("You lose! :( ");
		}
		else if(dealersTotal > 21 && userTotal <= 21) {
			gameOutcome = ("You win! :) ");
		}
		else if(dealersTotal == 21) {
			gameOutcome = ("You lose! :( ");
		}
		else if (dealersTotal < 21) {
			if(userTotal <  21 && userTotal > dealersTotal){
				gameOutcome = ("You win! :) ");
			}
			if(userTotal ==  21){
				gameOutcome = ("You win! :) ");
			}
			else if(userTotal < dealersTotal) {
				gameOutcome = ("You lose! :( ");

			}
			else if(userTotal == dealersTotal) {
				gameOutcome = ("You lose! :( ");
			}
		}
		return gameOutcome;
	}
}
	