package Monopoly_test;

import java.awt.Color;

public class Game 
{
	private int players, phase;
	private BoardSquare[] boardArray = new BoardSquare[40];
	private Player[] playerArray = new Player[6];
	private int[] profOakCards = new int[16];
	private int[] trainerBattleCards = new int[16];
	private int countNames = 0, currentTurn;
	boolean isAbleToRoll, isAbleToBuy;
	public Game() 
	{	
		int[] go = new int[]{0,0,0,0,0,0};
		int[] geodudeRent = new int[]{2,10,30,90,160,250};
		int[] trainerBattle = new int[]{0,0,0,0,0,0};
		int[] onixRent = new int[]{4,20,60,180,320,450};
		int[] rival = new int[]{0,0,0,0,0,0};
		int[] pokeBall = new int[]{0,25,50,100,200,0};
		int[] staryuRent = new int[]{6,30,90,270,400,550};
		int[] profOak = new int[]{0,0,0,0,0,0};
		int[] starmieRent = new int[]{6,30,90,270,400,550};
		int[] horseaRent = new int[]{8,40,100,300,450,600};
		int[] jail = new int[]{0,0,0,0,0,0};
		int[] voltorbRent = new int[]{10,50,150,450,625,750};
		int[] zapdos = new int[]{0,4,10,0,0,0};
		int[] electabuzzRent = new int[]{10,50,150,450,625,750};
		int[] raichuRent = new int[]{12,60,180,500,700,900};
		int[] greatBall = new int[]{0,25,50,100,200,0};
		int[] victreebelRent = new int[]{14,70,200,550,750,950};
		int[] tangelaRent = new int[]{14,70,200,550,750,950};
		int[] vileplumeRent = new int[]{16,80,220,600,800,1000};
		int[] freeParking = new int[]{0,0,0,0,0,0};
		int[] kadabraRent = new int[]{18,90,250,700,875,1050};
		int[] mrMimeRent = new int[]{18,90,250,700,875,1050};
		int[] venomothRent = new int[]{20,100,300,750,925,1100};
		int[] ultraBall = new int[]{0,25,50,100,200,0};
		int[] koffingRent = new int[]{22,100,330,800,975,1150};
		int[] mukRent = new int[]{22,100,330,800,975,1150};
		int[] articuno = new int[]{0,4,10,0,0,0};
		int[] weezingRent = new int[]{22,120,360,850,1025,1200};
		int[] goToJail = new int[]{0,0,0,0,0,0};
		int[] growlitheRent = new int[]{26,130,390,900,1100,1275};
		int[] ponytaRent = new int[]{26,130,390,900,1100,1275};
		int[] rapidashRent = new int[]{28,150,450,1000,1200,1400};
		int[] masterBall = new int[]{0,25,50,100,200,0};
		int[] nidoqueenRent = new int[]{35,175,500,1100,1300,1500};
		int[] teamRocket = new int[]{0,0,0,0,0,0};
		int[] nidokingRent = new int[]{50,200,600,1400,1700,2000};
		
		boardArray[0] = new BoardSquare(4, 491, "Go", go, 0, 0, false, "misc");
		boardArray[1] = new BoardSquare(4, 446, "Geodude", geodudeRent, 60, 30, false, "normal");
		boardArray[2] = new BoardSquare(4, 399, "Trainer Battle", trainerBattle, 0, 0, false, "card");
		boardArray[3] = new BoardSquare(4, 353, "Onix", onixRent, 60, 30, false, "normal");
		boardArray[4] = new BoardSquare(4, 308, "Rival", rival, 0, 0, false, "misc");
		boardArray[5] = new BoardSquare(4, 262, "Poké Ball",pokeBall, 200, 100, false, "ball");
		boardArray[6] = new BoardSquare(4, 216, "Staryu", staryuRent, 100, 50, false, "normal");
		boardArray[7] = new BoardSquare(4, 173, "Professor Oak", profOak, 0, 0, false, "card");
		boardArray[8] = new BoardSquare(4, 126, "Starmie", starmieRent, 100, 50, false, "normal");
		boardArray[9] = new BoardSquare(4, 77, "Horsea", horseaRent, 120, 60, false, "normal");
		boardArray[10] = new BoardSquare(4, 4, "Jail", jail, 0, 0, false, "misc");
		boardArray[11] = new BoardSquare(78, 4, "Voltorb", voltorbRent, 140, 70, false, "normal");
		boardArray[12] = new BoardSquare(123, 4, "Zapdos", zapdos, 150, 75, false, "bird");
		boardArray[13] = new BoardSquare(168, 4, "Electabuzz", electabuzzRent, 140, 70, false, "normal");
		boardArray[14] = new BoardSquare(214, 4, "Raichu", raichuRent, 160, 80, false, "normal");
		boardArray[15] = new BoardSquare(259, 4, "Great Ball",greatBall, 200, 100, false, "ball");
		boardArray[16] = new BoardSquare(305, 4, "Victreebel", victreebelRent, 180, 90, false, "normal");
		boardArray[17] = new BoardSquare(350, 4, "Trainer Battle", trainerBattle, 0, 0, false, "card");
		boardArray[18] = new BoardSquare(396, 4, "Tangela", tangelaRent, 180, 90, false, "normal");
		boardArray[19] = new BoardSquare(441, 4, "Vileplume", vileplumeRent, 200, 100, false, "normal");
		boardArray[20] = new BoardSquare(486, 4, "Free Parking", freeParking, 0, 0, false, "misc");
		boardArray[21] = new BoardSquare(486, 77, "Kadabra", kadabraRent, 220, 110, false, "normal");
		boardArray[22] = new BoardSquare(486, 126, "Professor Oak", profOak, 0, 0, false, "card");
		boardArray[23] = new BoardSquare(486, 173, "Mr. Mime", mrMimeRent, 220, 110, false, "normal");
		boardArray[24] = new BoardSquare(486, 216, "Venomoth", venomothRent, 240, 120, false, "normal");
		boardArray[25] = new BoardSquare(486, 262, "Ultra Ball", ultraBall, 200, 100, false, "ball");
		boardArray[26] = new BoardSquare(486, 308, "Koffing", koffingRent, 260, 130, false, "normal");
		boardArray[27] = new BoardSquare(486, 353, "Muk", mukRent, 260, 130, false, "normal");
		boardArray[28] = new BoardSquare(486, 399, "Articuno", articuno, 150, 75, false, "bird");
		boardArray[29] = new BoardSquare(486, 446, "Weezing", weezingRent, 280, 140, false, "normal");
		boardArray[30] = new BoardSquare(486, 491, "Go To Jail", goToJail, 0, 0, false, "misc");
		boardArray[31] = new BoardSquare(440, 491, "Growlithe", growlitheRent, 300, 150, false, "normal");
		boardArray[32] = new BoardSquare(396, 491, "Ponyta", ponytaRent, 300, 150, false, "normal");
		boardArray[33] = new BoardSquare(350, 491, "Trainer Battle", trainerBattle, 0, 0, false,"card");
		boardArray[34] = new BoardSquare(305, 491, "Rapidash", rapidashRent, 320, 160, false, "normal");
		boardArray[35] = new BoardSquare(259, 491, "Master Ball", masterBall, 200, 100, false, "ball");
		boardArray[36] = new BoardSquare(214, 491, "Professor Oak", profOak, 0, 0, false, "card");
		boardArray[37] = new BoardSquare(168, 491, "Nidoqueen", nidoqueenRent, 350, 175, false, "normal");
		boardArray[38] = new BoardSquare(123, 491, "Team Rocket", teamRocket, 0,0, false, "misc");
		boardArray[39] = new BoardSquare(78, 491, "Nidoking", nidokingRent, 400, 200, false, "normal");
		
		BoardSquare[] brown = new BoardSquare[]{boardArray[1], boardArray[3]};
		BoardSquare[] skyBlue = new BoardSquare[]{boardArray[6], boardArray[8], boardArray[9]};
		BoardSquare[] pink = new BoardSquare[]{boardArray[11], boardArray[13], boardArray[14]};
		BoardSquare[] orange = new BoardSquare[]{boardArray[16], boardArray[18], boardArray[19]};
		BoardSquare[] red = new BoardSquare[]{boardArray[21], boardArray[23], boardArray[24]};
		BoardSquare[] yellow = new BoardSquare[]{boardArray[26], boardArray[27], boardArray[29]};
		BoardSquare[] green = new BoardSquare[]{boardArray[31], boardArray[32], boardArray[34]};
		BoardSquare[] blue = new BoardSquare[]{boardArray[37], boardArray[39]};
		
		BoardSquare[] pokeBalls = new BoardSquare[]{boardArray[5], boardArray[15], boardArray[25], boardArray[35]};
		BoardSquare[] legendaryBirds = new BoardSquare[]{boardArray[12], boardArray[28]};
		
		boardArray[1].setColourGroup(brown);
		boardArray[1].setHousePrice(50);
		boardArray[3].setColourGroup(brown);
		boardArray[3].setHousePrice(50);
		boardArray[6].setColourGroup(skyBlue);
		boardArray[6].setHousePrice(50);
		boardArray[8].setColourGroup(skyBlue);
		boardArray[8].setHousePrice(50);
		boardArray[9].setColourGroup(skyBlue);
		boardArray[9].setHousePrice(50);
		boardArray[11].setColourGroup(pink);
		boardArray[11].setHousePrice(100);
		boardArray[13].setColourGroup(pink);
		boardArray[13].setHousePrice(100);
		boardArray[14].setColourGroup(pink);
		boardArray[14].setHousePrice(100);
		boardArray[16].setColourGroup(orange);
		boardArray[16].setHousePrice(100);
		boardArray[18].setColourGroup(orange);
		boardArray[18].setHousePrice(100);
		boardArray[19].setColourGroup(orange);
		boardArray[19].setHousePrice(100);
		boardArray[21].setColourGroup(red);
		boardArray[21].setHousePrice(150);
		boardArray[23].setColourGroup(red);
		boardArray[23].setHousePrice(150);
		boardArray[24].setColourGroup(red);
		boardArray[24].setHousePrice(150);
		boardArray[26].setColourGroup(yellow);
		boardArray[26].setHousePrice(150);
		boardArray[27].setColourGroup(yellow);
		boardArray[27].setHousePrice(150);
		boardArray[29].setColourGroup(yellow);
		boardArray[29].setHousePrice(150);
		boardArray[31].setColourGroup(green);
		boardArray[31].setHousePrice(200);
		boardArray[32].setColourGroup(green);
		boardArray[32].setHousePrice(200);
		boardArray[34].setColourGroup(green);
		boardArray[34].setHousePrice(200);
		boardArray[37].setColourGroup(blue);
		boardArray[37].setHousePrice(200);
		boardArray[39].setColourGroup(blue);
		boardArray[39].setHousePrice(200);
		
		boardArray[5].setColourGroup(pokeBalls);
		boardArray[15].setColourGroup(pokeBalls);
		boardArray[25].setColourGroup(pokeBalls);
		boardArray[35].setColourGroup(pokeBalls);
		
		boardArray[12].setColourGroup(legendaryBirds);
		boardArray[28].setColourGroup(legendaryBirds);

		// An array of every board square with the coordinates of its top left corner
		playerArray[0]=new Player(0, 2, Color.RED);
		// (0, 2) being the coordinates of the counter with respect to the top left corner of the square it is in
		playerArray[1]=new Player(12, 2, Color.BLUE);
		playerArray[2]=new Player(24, 2, Color.GREEN);
		playerArray[3]=new Player(0, 14, new Color(255, 0, 255));
		playerArray[4]=new Player(12, 14, new Color(255, 255, 0));
		playerArray[5]=new Player(24, 14, new Color(0, 255, 255));
		
		
		playerArray[0].setBounds(4, 493, 10, 10);
		playerArray[1].setBounds(16, 493, 10, 10);
		playerArray[2].setBounds(28, 493, 10, 10);
		playerArray[3].setBounds(4, 505, 10, 10);
		playerArray[4].setBounds(16, 505, 10, 10);
		playerArray[5].setBounds(28, 505, 10, 10);
		
		fillAndShuffleDeck(profOakCards);
		fillAndShuffleDeck(trainerBattleCards);
		
		phase = 0;
		isAbleToRoll = true;
		isAbleToBuy = false;
	}
	public void setAbleToRoll(boolean isAbleToRoll)
	{
		this.isAbleToRoll = isAbleToRoll;
	}
	public boolean isAbleToRoll()
	{
		return isAbleToRoll;
	}
	public void setAbleToBuy(boolean isAbleToBuy)
	{
		this.isAbleToBuy = isAbleToBuy;
	}
	public boolean isAbleToBuy()
	{
		return isAbleToBuy;
	}
	public Player[] getPlayerArray() 
	{
		return playerArray;
	}
	public BoardSquare[] getBoardSquareArray() 
	{
		return boardArray;
	}
	public int getPlayers() 
	{
		return players;
	}
	public void setPlayers(int players) 
	{
		this.players = players;
	}
	public void removePlayers()
	{
		this.players-=1;
	}
	public void setPhase(int phase) 
	{
		this.phase = phase;
	}
	public int getPhase() 
	{
		return phase;
	}
	public void nextPhase() 
	{
		phase++;
	}
	public int getCountNames() 
	{
		return countNames;
	}
	public void incrementCountNames() 
	{
		countNames++;
	}
	public void setCountNames(int i) 
	{
		countNames = i;
	}
	public int getCurrentTurn() 
	{
		return currentTurn;
	}
	public void nextTurn() 
	{
		currentTurn = (currentTurn + 1) % players;
	}

	public int[] getProfOakCards()
	{
		return profOakCards;
	}
	public int[] getTrainerBattleCards()
	{
		return trainerBattleCards;
	}
	
	public Player getFirst() 
	{
		Player temp = playerArray[0];
		currentTurn = 0;
		for(int i = 1; i < players; i++) 
		{
			if (playerArray[i].getFirstDiceRoll() > temp.getFirstDiceRoll()) 
			{
				temp = playerArray[i];
				currentTurn = i;
			}
		}
		return temp;
	}
	
	public void fillAndShuffleDeck(int[] cards)
	{
		for(int i = 0; i < cards.length - 1; i++)
		{
			cards[i] = i;
		}
		int newPosition;
		int temp;
		for(int i = 0; i < cards.length - 1; i++)
		{
			newPosition = (int) (Math.random() * cards.length);
			temp = cards[i];
			cards[i] = cards[newPosition];
			cards[newPosition] = temp;
		}
	}
}
