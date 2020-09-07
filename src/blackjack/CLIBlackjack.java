package blackjack;

import java.util.*;

public class CLIBlackjack {
	
	public enum Move {HIT, DOUBLE, STAND, SPLIT};

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
			String userCardOneRank = getCardRank(deck, cardIndex, stringRankArray);
			cardIndex++;
			String playersSecondCard = getNextCard(deck, stringRankArray, stringSuitArray, cardIndex);
			int userCardTwoValue = getCardValue(deck, cardIndex);
			String userCardTwoRank = getCardRank(deck, cardIndex, stringRankArray);
			cardIndex++;
			System.out.println(playersFirstCard + ", " + playersSecondCard + "\n");				
			int handOneTotal = userCardOneValue + userCardTwoValue;
			int handTwoTotal = 100;
			
			Scanner input = new Scanner(System.in);
			String playersNewCard;
			String userInput = null;
			Move move = null;		
			
			do {
				System.out.print("What would you like to do?  ");
				System.out.print("HIT, DOUBLE, STAND");
				if(userCardOneRank.equals(userCardTwoRank)) {
					System.out.print(", SPLIT");
				}
				System.out.println();
				userInput = input.nextLine();
				move = getUserMove(userInput);
				
				if(move == Move.HIT || move == Move.DOUBLE) {
					playersNewCard = getNextCard(deck, stringRankArray, stringSuitArray, cardIndex);
					System.out.println("Your New card is: " + playersNewCard + "\n");
					int newUserCardValue = getCardValue(deck, cardIndex);
					cardIndex++;
					handOneTotal+= newUserCardValue;
					move = null;
				}
				
				else if (move == Move.STAND) {
					// do nothing just continue to result
				}
				
				else if (move == Move.SPLIT) {
					if(!userCardOneRank.equals(userCardTwoRank)) {
						System.out.println("You cannot split if your cards are not of equal rank");
						move = null;
					}
					else {
						// Do all the splitting stuff
						System.out.println("You have split your hand");
						
						System.out.print("\nYour first hand is: ");
						playersNewCard = getNextCard(deck, stringRankArray, stringSuitArray, cardIndex);
						System.out.println(playersFirstCard + ", " + playersNewCard);
						handOneTotal = getCardValue(deck, cardIndex) + userCardOneValue;
						cardIndex++;
						
						System.out.print("Your second hand is: ");
						playersNewCard = getNextCard(deck, stringRankArray, stringSuitArray, cardIndex);
						System.out.println(playersSecondCard + ", " + playersNewCard);
						handTwoTotal = getCardValue(deck, cardIndex) + userCardTwoValue;
						cardIndex++;
						
						do {
							System.out.print("\nWhat would you like to do for your first hand?  ");
							System.out.println("HIT, DOUBLE, STAND");
							
							move = getUserMove(userInput); 
						
							try
						        {
								userInput = input.nextLine();
						           if (!"hit".equals(userInput)) {
						                throw new Exception("Invalid input, Please make sure all letters are upper acse");
						            }           
						        }
						        catch (Exception exe)
						        {   
						            System.out.println(exe);
						            return;
						        }
							
							
							if(move == Move.HIT || move == Move.DOUBLE) {
								playersNewCard = getNextCard(deck, stringRankArray, stringSuitArray, cardIndex);
								System.out.println("Your New card is: " + playersNewCard + "\n");
								int newUserCardValue = getCardValue(deck, cardIndex);
								cardIndex++;
								handOneTotal+= newUserCardValue;
								move = null;
							}
							
							else if (move == Move.STAND) {
								// do nothing just continue to result
							}
							else {
								System.out.println("You did not enter a valid move. Please make sure all letters are upper acse");
								move = null;
							}
						}
						while(move == null && handOneTotal < 21);
					
						do {
							System.out.print("\nWhat would you like to do for your second hand?  ");
							System.out.println("HIT, DOUBLE, STAND");
							userInput = input.nextLine();
							move = getUserMove(userInput);
							
							if(move == Move.HIT || move == Move.DOUBLE) {
								playersNewCard = getNextCard(deck, stringRankArray, stringSuitArray, cardIndex);
								System.out.println("Your New card is: " + playersNewCard + "\n");
								int newUserCardValue = getCardValue(deck, cardIndex);
								cardIndex++;
								handTwoTotal+= newUserCardValue;
								move = null;
							}
							
							else if (move == Move.STAND) {
								// do nothing just continue to result
							}
							else {
								System.out.println("You did not enter a valid move. Please make sure all letters are upper acse");
								move = null;
							}
						}
						while(move == null && handTwoTotal < 21);
					}
				}
				
				else {
					System.out.println("You did not enter a valid move. Please make sure all letters are upper acse");
					move = null;
				}
			}
			while(move == null && handOneTotal < 21);
				
			while(dealersTotal < 17) {
				int newDealerCardValue = getCardValue(deck, cardIndex);
				dealersTotal += newDealerCardValue;
				cardIndex++;
			}
						
			System.out.println();
			System.out.println("Result: ");
			System.out.println("Dealer's Total: " + dealersTotal);
			System.out.println("Hand One Total: " + handOneTotal);
			if(handTwoTotal!=100) {
				System.out.println("Hand Two Total: " + handTwoTotal);
			}
			System.out.println();

			
			String result = getResult(handOneTotal, handTwoTotal, dealersTotal);
			System.out.println(result);
			
			System.out.println("------------------------");	
			System.out.println("Would you like to play again? (Y / N)");
			playAgain = input.next();
			
			if(playAgain.equals("Y")) {
				System.out.println("--------------------------------------------------------------");
				System.out.println("               NEW GAME                                      ");
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
	
	public static String getCardRank(int[] deck, int cardIndex, String[] stringRankArray) {
		String cardRank =  (stringRankArray[deck[cardIndex] % 13]);
		return cardRank;
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
	
	public static String getResult(int handOneTotal, int handTwoTotal, int dealersTotal) {
		String gameOutcome = null;
		String handOneOutcome = null;
		String handTwoOutcome = null;
		
		if (handTwoTotal == 100) {
			if(handOneTotal > 21) {
				gameOutcome = "BUST :( You lose!";
			}
			else if(dealersTotal > 21 && handOneTotal <= 21) {
				gameOutcome = "Dealer Busts :) You win!";
			}
			else if(dealersTotal == 21) {
				gameOutcome = "Dealer got blackjack :( You lose!";
			}
			else if (dealersTotal < 21) {
				if(handOneTotal <  21 && handOneTotal > dealersTotal){
					gameOutcome = "You beat the dealer :) You win!";
				}
				if(handOneTotal ==  21){
					gameOutcome = "You got blackjack :) You win!";
				}
				else if(handOneTotal < dealersTotal) {
					gameOutcome = "Dealer beat you :( You lose!";

				}
				else if(handOneTotal == dealersTotal) {
					gameOutcome = "Dealer wins a draw :( You lose!";
				}
			}	
		}
		else {
			if(handOneTotal > 21) {
				handOneOutcome = "BUST :( You lose!";
			}
			else if(dealersTotal > 21 && handOneTotal <= 21) {
				handOneOutcome = "Dealer Busts :) You win!";
			}
			else if(dealersTotal == 21) {
				handOneOutcome = "Dealer got blackjack :( You lose!";
			}
			else if (dealersTotal < 21) {
				if(handOneTotal <  21 && handOneTotal > dealersTotal){
					handOneOutcome = "You beat the dealer :) You win!";
				}
				if(handOneTotal ==  21){
					handOneOutcome = "You got blackjack :) You win!";
				}
				else if(handOneTotal < dealersTotal) {
					handOneOutcome = "Dealer beat you :( You lose!";

				}
				else if(handOneTotal == dealersTotal) {
					handOneOutcome = "Dealer wins a draw :( You lose!";
				}
			}	
			
			if(handTwoTotal > 21) {
				handTwoOutcome = "BUST :( You lose!";
			}
			else if(dealersTotal > 21 && handTwoTotal <= 21) {
				handTwoOutcome = "Dealer Busts :) You win!";
			}
			else if(dealersTotal == 21) {
				handTwoOutcome = "Dealer got blackjack :( You lose!";
			}
			else if (dealersTotal < 21) {
				if(handTwoTotal <  21 && handTwoTotal > dealersTotal){
					handTwoOutcome = "You beat the dealer :) You win!";
				}
				if(handTwoTotal ==  21){
					handTwoOutcome = "You got blackjack :) You win!";
				}
				else if(handTwoTotal < dealersTotal) {
					handTwoOutcome = "Dealer beat you :( You lose!";

				}
				else if(handTwoTotal == dealersTotal) {
					handTwoOutcome = "Dealer wins a draw :( You lose!";
				}
			}
			gameOutcome = "Hand One: " + handOneOutcome + "\nHand Two: " + handTwoOutcome;
		}
		
		return gameOutcome;
	}
	
	public static Move getUserMove(String userInput) {
		Move move;
		switch(userInput) {
		case "HIT":
			move = Move.HIT;
			break;
		case "STAND":
			move = Move.STAND;
			break;
		case "DOUBLE":
			move = Move.DOUBLE;
			break;
		case "SPLIT":
			move = Move.SPLIT;
			break;
		default:
			move = null;
		}
		return move;
	}
}
	