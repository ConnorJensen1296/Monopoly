package Monopoly_test;
import java.awt.*;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;

public class MyFrame extends JFrame 
{
		
    private static final long serialVersionUID=1L;

	private final JSplitPane split=new JSplitPane();
	private final JLayeredPane Panel1=new JLayeredPane();
	private final JPanel Panel2=new JPanel(new BorderLayout());
	private final JButton button=new JButton("Submit");
	private final JTextArea textArea=new JTextArea();
	private final JScrollPane scrollPane= new JScrollPane(textArea);
	private final JPanel inputPanel=new JPanel();
	private final JTextField textField=new JTextField();
	String errorMessage = new String("\nSorry, that is not a valid command, please try again");
	public MyFrame()
	{
		setPreferredSize(new Dimension(960,600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// We don't want to resize our window
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(split);
		split.setDividerSize(0);
		// To prevent resizing the sides of the split pane
		
		// Code for split panel
		split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		split.setResizeWeight(0.68);
		split.setLeftComponent(Panel1);
		split.setRightComponent(Panel2);
		
		//code for LeftPanel
		image obj=new image();
		Panel1.add(obj, 1);
		// Add it on layer 1, behind all the components on layer 0
		Panel1.setVisible(true);
		obj.setBounds(0, 0, 562, 569);
		// 562 x 569 is the dimensions of the board
		
		//Code for RightPanel
		Panel2.setLayout(new BoxLayout(Panel2, BoxLayout.Y_AXIS));
		Panel2.add(scrollPane);
		textArea.setFont(textArea.getFont().deriveFont(20f)); //Customizing the default text in the text area.
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false); // to disable editing within the text area
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Panel2.add(inputPanel);
		inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
		inputPanel.add(textField);
		inputPanel.add(button);
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.append("Welcome to Pokémon Monopoly!\n\nPlease enter the amount of players for this game");
		this.getRootPane().setDefaultButton(button);
		// Sets the submit button to be pressed upon pressing "Enter"
		Game newGame = new Game();
		startGame(newGame);
	}
	public void startGame(Game game) 
	{
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!textField.getText().trim().isEmpty()) 
				{
					String temp = textField.getText();
					textArea.append("\n> "+temp);
					Player tempPlayer = game.getPlayerArray()[game.getCurrentTurn()];
					if(tempPlayer.getPrisonStatus()==true)
					{
						int pos=0;
						if(tempPlayer.getCount()<=3){
						switch(temp)
						{
							case "roll": pos=jail_roll(tempPlayer, game); // rolling for while in jail
										 break;
							case "pay":	 bail(tempPlayer, game);
										break;
							case "card":
										if(tempPlayer.getCardStatus()==true)
										{
											tempPlayer.setPrisonStatus(false);
											tempPlayer.decCounter();
											if(tempPlayer.getCardCounter()==0)
											{
												tempPlayer.setCardStatus(false);
											}
											else
											{
												tempPlayer.setCardStatus(true);
											}
											textArea.append("\nType \"done\" to end your turn");
											
										}
										else
										{
											textArea.append("\nSorry you do not have a get out of jail free card");
											textArea.append("\nType \"done\" to end your turn");
										}
										break;
							case "done":endTurn(tempPlayer, game);
										break;
							default: textArea.append("\nYou entered a wrong choice. While in jail you can only roll, pay or use a card to get out");
						}
						}
						else{
							textArea.append("You have failed to roll a double for the last 3 turns, so automatically 50 is deducted from your bank account"
									+ " and you are free to go.");
							bail(tempPlayer, game);
							PrisonMove(tempPlayer, game, pos);
						}
					}
					else{
					switch (temp) 
					{
						case "roll":
							roll(tempPlayer, game);
							break;
						case "done":
							endTurn(tempPlayer, game);
							break;
						case "help":
							textArea.append(
									"\nHere is the list of commands;\n"
									+ "\"balance\": Shows your current bank balance\n"
									+ "\"roll\": Rolls the dice\n"
									+ "\"property\": Shows your current properties\n"
									+ "\"build <property_name> <amount>\": Builds your chosen amount of houses on property_name\n"
									+ "\"build <property_name> hotel\": Builds a hotel on property_name (must have 4 houses first)\n"
									+ "\"mortgage <property_name>\": Mortgage a property for half of its sale price\n"
									+ "\"redeem <property_name>\": Unmortgage a property for mortgage price + 10% interest\n"
									+ "\"quit\": Ends the game and shows the winner\n"
									+ "\"done\": Ends your turn\n"
									);
							break;
						case "buy":
							buy(tempPlayer, game);
							break;
						case "property":
							property(game);
							break;
						case "balance":
							balance(tempPlayer, game);
							break;
						case "quit":
							quit(game);
							break;
						case "bankrupt":
							bankrupt(tempPlayer, game);
							break;
						default:
							if(game.getPhase() == 0) 
							{
								if(Integer.parseInt(temp) >= 2 && Integer.parseInt(temp) <= 6) 
								{
									game.setPlayers(Integer.parseInt(temp));
									for(int j = 0; j < game.getPlayers(); j++) 
									{
										Panel1.add(game.getPlayerArray()[j], 0);
									}
									textArea.append("\n\nYou have chosen a " + game.getPlayers() + " player game");
									game.nextPhase();
									// Here we increment the phase, and give the introductory message for phase 1
									textArea.append("\nNow we must find out your names, please enter the name for Player 1");											// The end of phase 0 is the start of phase 1
								}
								else 
								{
									textArea.append("\nSorry, that is not valid, please enter a valid amount of players.");
								}
							}
							else if(game.getPhase() == 1) 
							{
								game.getPlayerArray()[game.getCountNames()].setName(temp);
								// getCountNames is just an accessor function for a variable we use to count through the amount of players in the current game
								textArea.append("\nThe name for Player " + (game.getCountNames() + 1) + " is now " + temp);
								game.incrementCountNames();
								// Increment our player counter
								if(game.getCountNames() != game.getPlayers()) 
								{
									// i.e. if we haven't reached the end of our list of players
									textArea.append("\nPlease enter the name for Player " + (game.getCountNames() + 1));
								}
								else 
								{
									game.nextPhase();
									game.setCountNames(0);
									textArea.append("\n\nNow we will roll to see who goes first, " + game.getPlayerArray()[0].getName() + ", type 'roll' to roll the dice.");
								}
							}
							else if(game.getPhase() == 3)
							{
								String parts[] = temp.split(" ");
								// split the typed string into an array of the words it contains
								if(parts[0].equals("build"))
								// i.e. if the first word is build
								{
									build(parts, tempPlayer, game);
								}
								else if(parts[0].equals("mortgage"))
								{
									mortgage(parts, tempPlayer, game);
								}
								else if(parts[0].equals("demolish"))
								{
									// i.e. if the first word is demolish
									demolish(parts, tempPlayer, game);
								}
								else if(parts[0].equals("redeem"))
								{
									redeem(parts, tempPlayer, game);
								}
								
							}
							else 
							{
								textArea.append(errorMessage);
							}
							break;
					}
				   }
					textField.setText(null);
				}
			}
		});
		pack();
	}
	public void roll(Player tempPlayer, Game game)
	{
		if(game.getPhase() == 2)
		{
			rollForFirst(game);
		}
		else if(game.getPhase() == 3)
		{
			rollInGame(tempPlayer, game);
		}
	}
	public void rollForFirst(Game game)
	{
		int[] tempDiceArray = new int[2];
		// int array with 2 slots for both dice
		tempDiceArray = rollDice();
		textArea.append("\n" + game.getPlayerArray()[game.getCountNames()].getName() + " rolled a " + tempDiceArray[0] + " and a " + tempDiceArray[1] + ", ");
		textArea.append("totalling " + (tempDiceArray[0] + tempDiceArray[1]));
		game.getPlayerArray()[game.getCountNames()].setFirstDiceRoll(tempDiceArray[0] + tempDiceArray[1]);
		// We use getCountNames() as an iterator that goes through the player array as we set their firstDiceRoll field to the result of the roll
		game.incrementCountNames();
		// Increment the countNames field so we can do the next player
		if(game.getCountNames() != game.getPlayers()) 
		// i.e. if we are not finished with our list
		{
			textArea.append("\n" + game.getPlayerArray()[game.getCountNames()].getName() + "'s turn, type 'roll' to roll the dice.");
			// Next players turn
		}
		else 
		{
			game.nextPhase();
			// We are finished this phase and can start the game (phase 3) now.
			game.setCountNames(0);
			// We may need this again
			textArea.append("\n\n" + game.getFirst().getName() + " rolled the highest, and now goes first, type 'roll' to roll the dice.");
			// Get first returns the Player object representing the player who rolled the highest
		}
	}
	public void rollInGame(Player tempPlayer, Game game)
	{
		if(tempPlayer.getBalance() < 0)
		{
			textArea.append(
					"\nSorry, your balance is currently at "
					+ tempPlayer.getBalance()
					+ ", please sell or mortgage your assets to make your balance positive again."
					+ "If you have no assets to mortgage/sell, you must declare bankruptcy");
		}
		else
		{	
			if(game.isAbleToRoll())
			{
				game.setAbleToRoll(false);
				int[] tempDiceArray = new int[2];
				tempDiceArray = rollDice();
				int oldPosition = (tempPlayer.getPosition());
				int newPosition = (tempPlayer.getPosition() + tempDiceArray[0] + tempDiceArray[1]) % 40;
				// Store the position before rolling and position after rolling as ints
				BoardSquare newBoardSquare = game.getBoardSquareArray()[newPosition];
				tempPlayer.movePlayer(newBoardSquare.getX(), newBoardSquare.getY());
				// Move the player to the position of the new square that we landed on
				tempPlayer.setPosition(newPosition);
				textArea.append("\nYou rolled a " + tempDiceArray[0] + " and a " + tempDiceArray[1] + ", ");
				textArea.append("totalling " + (tempDiceArray[0] + tempDiceArray[1]));
				
				int temp = Player.getRollCount();
				temp ++;
				Player.setRollCount(temp);
				
				if(tempDiceArray[0] == tempDiceArray[1]) {
					
					if(Player.getRollCount() == 3){
						textArea.append("\n You have rolled doubles 3 times so you have to go to jail");
						tempPlayer.movePlayer(4,4);
						tempPlayer.setPosition(10);
						game.setAbleToRoll(false);
						tempPlayer.setPrisonStatus(true);
					}
					else{
						textArea.append(", you rolled doubles!");
						game.setAbleToRoll(true);
						// If they rolled doubles, they can roll again
					}
				}
				else
				{
					game.setAbleToRoll(false);
					// If they didn't roll doubles, they can not
					Player.setRollCount(0);
				}
				if(newPosition < oldPosition)
				// If you pass go you move from squares 28-39 to squares 0-11, newPosition will be greater than oldPosition if and only if they passed Go
				{
					textArea.append("\nYou passed go! 200 has been put into your account.");
					tempPlayer.setBalance(tempPlayer.getBalance() + 200);
				}
				//code for rent and buying properties
				textArea.append("\nYou landed on " + newBoardSquare.getName());
				if(newBoardSquare.getType().equals("normal")) 
				{
					if(newBoardSquare.isOwned()) 
					{
						Player owner = game.getPlayerArray()[newBoardSquare.getOwner()];
						textArea.append("\nThis property is owned by " 
								+ owner.getName() 
								+ ", and you must pay rent, "
								+ newBoardSquare.getRent()[newBoardSquare.getHouses()]
								+ " has been taken from your account and paid to "
								+ owner.getName());
						tempPlayer.pay(game.getPlayerArray()[newBoardSquare.getOwner()], newBoardSquare.getRent()[newBoardSquare.getHouses()]);
					}
					else 
					{
						textArea.append("\nThis property is not owned, type \"buy\" to buy this property, ");
						game.setAbleToBuy(true);
						// This lets "buy" be an option for a text command
					}
				}
				else if(newBoardSquare.getType().equals("misc")) //code for misc stuff
				{
					if(newBoardSquare.getName().equals("Rival")||newBoardSquare.getName().equals("Team Rocket"))// code for tax stuff
					{
						int val=tempPlayer.tax();
						textArea.append("\nA tax of "+val+" was deducted from your bank account");
					}
					else if(newBoardSquare.getName().equals("Go To Jail"))
					{
						tempPlayer.movePlayer(4,4);
						tempPlayer.setPosition(10);
						game.setAbleToRoll(false);
						tempPlayer.setPrisonStatus(true);
					}
				}
				else if(newBoardSquare.getType().equals("ball"))
				{
					if(newBoardSquare.isOwned())
					{
						Player owner = game.getPlayerArray()[newBoardSquare.getOwner()];
						newBoardSquare.setHouses(owner.getBallsOwned());
						textArea.append("\nThis property is owned by " 
								+ owner.getName() 
								+ ", and you must pay rent, "
								+ newBoardSquare.getRent()[newBoardSquare.getHouses()]
								+ " has been taken from your account and paid to "
								+ owner.getName());
						tempPlayer.pay(game.getPlayerArray()[newBoardSquare.getOwner()], newBoardSquare.getRent()[newBoardSquare.getHouses()]);
						// We will use the same array of rents for houses as we did for the regular properties
						// However the rent will change with how many the player owns instead of houses built
					}
					else 
					{
						textArea.append("\nThis property is not owned, type \"buy\" to buy this property, ");
						game.setAbleToBuy(true);
					}
				}
				else if(newBoardSquare.getType().equals("bird"))
				{
					if(newBoardSquare.isOwned())
					{
						Player owner = game.getPlayerArray()[newBoardSquare.getOwner()];
						newBoardSquare.setHouses(owner.getBirdsOwned());
						textArea.append("\nThis property is owned by " 
								+ owner.getName() 
								+ ", and you must pay rent,"
								+ newBoardSquare.getRent()[newBoardSquare.getHouses()] * (tempDiceArray[0] + tempDiceArray[1])
								+ " has been taken from your account and paid to "
								+ owner.getName());
						// Again we uses the houses rent array but instead multiply it by the dice roll
						tempPlayer.pay(
								game.getPlayerArray()[newBoardSquare.getOwner()], 
								newBoardSquare.getRent()[newBoardSquare.getHouses()] * (tempDiceArray[0] + tempDiceArray[1]));
					}
					else 
					{
						textArea.append("\nThis property is not owned, type \"buy\" to buy this property, ");
						game.setAbleToBuy(true);
					}
				}
				else if(newBoardSquare.getType().equals("card"))
				{
					landOnCard(newBoardSquare, tempPlayer, game);
				}
				if(game.isAbleToRoll())
				{
					textArea.append("\nType \"roll\" to roll again");
				}
				else 
				{
					textArea.append("\nType \"done\" to end your turn");
				}
				textArea.append("\nType \"help\" for a list of additional commands.");
			}
			else
			{
				textArea.append(errorMessage);
			}
		}
	}
	public void endTurn(Player tempPlayer, Game game)
	{
		if(game.getPhase() == 3 && !game.isAbleToRoll())
		// I.e. if you are still able to roll you cannot finish your turn, i.e. if you rolled doubles or haven't rolled yet
		{
			if(tempPlayer.getBalance() < 0)
			{
				textArea.append(
						"\nSorry, your balance is currently at "
						+ tempPlayer.getBalance()
						+ ", please sell or mortgage your assets to make your balance positive again."
						+ "If you have no assets to mortgage/sell, you must declare bankruptcy");
			}
			else
			{
				game.nextTurn();
				game.setAbleToRoll(true);
				textArea.append("\nYour turn has ended, it is now " 
						+ game.getPlayerArray()[(game.getCurrentTurn()) % game.getPlayers()].getName() 
						+ "'s turn, type \"roll\" to roll the dice.");
			}
		}
		else 
		{
			textArea.append(errorMessage);
		}
	}
	public void buy(Player tempPlayer, Game game)
	{
		if(game.getPhase() == 3 && game.isAbleToBuy)
		{
			BoardSquare saleSquare = game.getBoardSquareArray()[tempPlayer.getPosition()];
			if(!saleSquare.isOwned())
			{
				switch (saleSquare.getType()) {
				case "normal":
					tempPlayer.purchase(saleSquare, game.getCurrentTurn());
					textArea.append("\nYou have successfully purchased this property for: " + saleSquare.getPrice());
					break;
				case "ball":
					tempPlayer.purchase(saleSquare, game.getCurrentTurn());
					tempPlayer.buyBall();
					textArea.append("\nYou have successfully purchased this property for: " + saleSquare.getPrice());
					break;
				case "bird":
					tempPlayer.purchase(saleSquare, game.getCurrentTurn());
					tempPlayer.buyBird();
					textArea.append("\nYou have successfully purchased this property for: " + saleSquare.getPrice());
					break;
				default:
					textArea.append("\nSorry, you cannot buy this property");
					break;
				}
				
			}
			else
			{
				textArea.append("\nSorry, this property is already owned");
			}
		}
	}
	public void property(Game game)
	{
		textArea.append("\nYour owned properties are:");
		for(int i=0; i<40; i++)
		{
			if(game.getBoardSquareArray()[i].isOwned())
			{
				if(game.getBoardSquareArray()[i].getOwner()==game.getCurrentTurn())
				{
					textArea.append("\n" + game.getBoardSquareArray()[i].getName());
				}
			}
		}
	}
	public void balance(Player tempPlayer, Game game)
	{
		if(game.getPhase() == 3)
		{
			textArea.append("\n" + tempPlayer.getName()+"'s current balance is "+tempPlayer.getBalance());
		}
		else
		{
			textArea.append(errorMessage);
		}
	}
	public void quit(Game game)
	{
		if(game.getPhase() == 3)
		{
			textArea.append("\nThe game is over now:\n");
			int arr[]=new int[game.getPlayers()];
			int count=0;
			for(int i=0;i<game.getPlayers();i++)
			{
				int total=0;
				total+=game.getPlayerArray()[i].getBalance();
					for(int j=0; j<40;j++)
					{
						if(game.getBoardSquareArray()[j].isOwned())
						{
							if(game.getBoardSquareArray()[j].getOwner()==game.getCurrentTurn())
							{
								total+=game.getBoardSquareArray()[j].getPrice();
							}
						}
				   }
				arr[count++]=total;
			}
			int max=arr[0],index=0;
			for(int i=0;i<game.getPlayers()-1;i++)
			{
				if(arr[i]>arr[i+1])
				{
					max=arr[i];
					index=i;
				}
			}
			textArea.append("\nThe Winner is: "+game.getPlayerArray()[index].getName());
			button.setEnabled(false);
		}
	}
	public void bankrupt(Player tempPlayer, Game game)
	{
		if(game.getPhase() == 3)
		{
			textArea.append("\n"+tempPlayer.getName()+" has declared bankruptcy and quit the game.\n");
			for(int j=0; j<40;j++) // releasing all the property owned by the player and demolishing all of his buildings
			{
				if(game.getBoardSquareArray()[j].isOwned())
				{
					if(game.getBoardSquareArray()[j].getOwner()==game.getCurrentTurn())
					{
						game.getBoardSquareArray()[j].setOwned(false);
						game.getBoardSquareArray()[j].setHouses(0);
						game.getBoardSquareArray()[j].setOwner(10); // a default constant value, to show that the property is not owned by anyone
						game.removePlayers();
						game.getPlayerArray()[j].setStatus(false);
					}
				}
		   }
		//if there's only one player left
			if(game.getPlayers()==1)
			{
				for(int i=0;i<game.getPlayers();i++)
				{
					if(game.getPlayerArray()[i].getStatus()==true)
					{
						textArea.append("\nThe Winner is: "+game.getPlayerArray()[i].getName());
					}
				}
				button.setEnabled(false);
			}
		//it's the next player's turn now
			game.nextTurn();
			game.setAbleToRoll(true);
			textArea.append("\nIt is now " 
					+ game.getPlayerArray()[(game.getCurrentTurn()) % game.getPlayers()].getName() 
					+ "'s turn, type \"roll\" to roll the dice.");
		}
		else 
		{
			textArea.append(errorMessage);
		}
	}
	public void build(String[] parts, Player tempPlayer, Game game)
	{
		boolean found = false;
		if(parts.length == 3)
		// expressions beginning with build need to have 3 words, otherwise they are meaningless
		{
			BoardSquare tempSquare = null;
			for(int i = 0; i < 40; i++)
			{
				if(parts[1].equals(game.getBoardSquareArray()[i].getName()) && game.getBoardSquareArray()[i].getType().equals("normal"))
				{
					found = true;
					tempSquare = game.getBoardSquareArray()[i];
					break;
				}
			}
			if(found)
			{
				boolean ableToBuild = true;
				if(tempSquare.getOwner() == game.getCurrentTurn())
				{
					for(int i = 0; i < tempSquare.getColourGroup().length - 1; i++)
					{
						if(tempSquare.getColourGroup()[i].isOwned() && tempSquare.getColourGroup()[i + 1].isOwned())
						{
							if(tempSquare.getColourGroup()[i].getOwner() != tempSquare.getColourGroup()[i + 1].getOwner())
							// Checks if they are owned by the same person
							{
								ableToBuild = false;
							}
						}
						else
						{
							ableToBuild = false;
						}
					}
				}
				else
				{
					ableToBuild = false;
				}
				if(ableToBuild)
				{
					if(parts[2].equals("hotel"))
					{
						if(tempSquare.getHouses() == 4)
						{
							tempSquare.setHouses(5);
							tempPlayer.setBalance(tempPlayer.getBalance() - tempSquare.getHousePrice());
							textArea.append("\nYou have built a hotel on " + tempSquare.getName());
						}
						else 
						{
							textArea.append("\nSorry, you cannot build a hotel on this property yet, build 4 houses before building a hotel");
						}
					}
					else if(Integer.parseInt(parts[2]) > 0 && Integer.parseInt(parts[2]) < 5 - tempSquare.getHouses())
					{
						tempSquare.setHouses(tempSquare.getHouses() + Integer.parseInt(parts[2]));
						tempPlayer.setBalance(tempPlayer.getBalance() - (Integer.parseInt(parts[2]) * tempSquare.getHousePrice()));
						textArea.append("\nYou have built " 
								+ Integer.parseInt(parts[2]) 
								+ " houses on " 
								+ tempSquare.getName());
					}
					else 
					{
						textArea.append("\nSorry, you cannot build that many houses on this square");
					}
				}
				else
				{
					textArea.append("\nSorry, you cannot build on a property until you own all properties in the colour group");
				}
			}
			else 
			{
				textArea.append("\nSorry, the property you entered is invalid, please try again");
			}
		}
		else
		{
			textArea.append(errorMessage);
		}
	}
	public void mortgage(String[] parts, Player tempPlayer, Game game)
	{
		boolean found=false;
		if(parts.length==2)
		{
			BoardSquare tempSquare = null;
			for(int i = 0; i < 40; i++)
			{
				if(parts[1].equals(game.getBoardSquareArray()[i].getName()))
				{
					found=true;
					tempSquare=game.getBoardSquareArray()[i];
					break;
				}
			}
			if(found)
			{
				if(tempSquare.getOwner()==game.getCurrentTurn())
				{
					tempSquare.setMortgageStatus(true);
					tempPlayer.setBalance(tempPlayer.getBalance()+(tempSquare.getPrice()*(1/2)));
					textArea.append("\nYou have mortgaged " 
							+parts[1] 
							+ " property " 
							+ "\n Your new balance is: " +tempPlayer.getBalance()
							);
				}
			}
		}
	}
	public void demolish(String[] parts, Player tempPlayer, Game game)
	{
		boolean found = false;
		if(parts.length == 3)
		{
		// expressions beginning with demolish need to have 3 words, otherwise they are meaningless
			BoardSquare tempSquare = null;
			for(int i = 0; i < 40; i++)
			{
				if(parts[1].equals(game.getBoardSquareArray()[i].getName()) && game.getBoardSquareArray()[i].getType().equals("normal")){
					found = true;
					tempSquare = game.getBoardSquareArray()[i];
					break;
				}
			}
			if(found){
				if(tempSquare.getOwner() == game.getCurrentTurn())
				{
					// Checks to see if current player is the owner
					if(Integer.parseInt(parts[2]) > 0 && Integer.parseInt(parts[2]) <= tempSquare.getHouses())
					{
						tempSquare.setHouses(tempSquare.getHouses() - Integer.parseInt(parts[2]));
						tempPlayer.setBalance(tempPlayer.getBalance() + (Integer.parseInt(parts[2]) * (1/2) * tempSquare.getHousePrice()));
						textArea.append("\nYou have demolished " 
								+ Integer.parseInt(parts[2]) 
								+ " houses on " 
								+ tempSquare.getName()
								+ "\n Your new balance is: " +tempPlayer.getBalance()
								);
					}
				}
			}
		}
	}
	public void redeem(String[] parts, Player tempPlayer, Game game)
	{
		boolean found=false;
		if(parts.length==2)
		{
			BoardSquare tempSquare=null;
			for(int i=0;i<40;i++)
			{
				if(parts[1].equals(game.getBoardSquareArray()[i].getName()))
				{
					found=true;
					tempSquare=game.getBoardSquareArray()[i];
					break;
				}
			}
			if(found)
			{
				if(tempSquare.getOwner()==game.getCurrentTurn())
				{
					tempSquare.setMortgageStatus(false);
					tempPlayer.setBalance(tempPlayer.getBalance()-(tempSquare.getMortgageValue()+(tempSquare.getMortgageValue()*(10/100))));
					textArea.append("\nYou have redeemed " 
							+parts[1] 
							+ " property " 
							+ "\n Your new balance is: " +tempPlayer.getBalance()
							);
				}
				else
				{
					textArea.append("\nSorry, you do not own this property");
				}
			}
			else
			{
				textArea.append(errorMessage);
			}
		}
	}
	public void bail(Player tempPlayer, Game game)
	{
		tempPlayer.bail_payment();
		textArea.append("\nYou paid a fine of 50 to get out of jail\n");
		tempPlayer.setPrisonStatus(false);
	}
	public void landOnCard(BoardSquare square, Player tempPlayer, Game game)
	{
		int[] tempArray;
		int temp;
		if(square.getName().equals("Trainer Battle"))
		{
			tempArray = game.getTrainerBattleCards();
			switch (tempArray[0]) {
			case 0:
				textArea.append("\nAdvance to Go! 200 has been deposited into your account");
				tempPlayer.movePlayer(4, 491);
				tempPlayer.setPosition(0);
				tempPlayer.setBalance(tempPlayer.getBalance() + 200);
				break;
			case 1:
				textArea.append("\nAdvance to Geodude! You passed Go! 200 has been deposited into your account");
				tempPlayer.movePlayer(4, 446);
				tempPlayer.setPosition(1);
				tempPlayer.setBalance(tempPlayer.getBalance() + 200);
				break;
			case 2:
				textArea.append("\nGo to jail. Do not pass Go. Do not collect 200");
				tempPlayer.movePlayer(4,4);
				tempPlayer.setPosition(10);
				game.setAbleToRoll(false);
				tempPlayer.setPrisonStatus(true);
				break;
			case 3:
				textArea.append("\nYou lost the trainer battle! Pay 100");
				tempPlayer.setBalance(tempPlayer.getBalance() - 100);
				break;
			case 4:
				textArea.append("\nYour pokemon was frozen! Pay 50 for ice heal");
				tempPlayer.setBalance(tempPlayer.getBalance() - 50);
				break;
			case 5:
				textArea.append("\nYour pokemon was poisoned! Pay 50 for antidote");
				tempPlayer.setBalance(tempPlayer.getBalance() - 50);
				break;
			case 6:
				textArea.append("\nYou beat a gym leader! Collect 200");
				tempPlayer.setBalance(tempPlayer.getBalance() + 200);
				break;
			case 7:
				textArea.append("\nYou beat your rival! Collect 100");
				tempPlayer.setBalance(tempPlayer.getBalance() + 100);
				break;
			case 8:
				textArea.append("\nYou found a nugget! Collect 100");
				tempPlayer.setBalance(tempPlayer.getBalance() + 100);
				break;
			case 9:
				textArea.append("\nYour mom sends you 50, 50 has been deposited into your account");
				tempPlayer.setBalance(tempPlayer.getBalance() + 50);
				break;
			case 10:
				textArea.append("\nYou receive interest from your bank account, collect 25");
				tempPlayer.setBalance(tempPlayer.getBalance() + 25);
				break;
			case 11:
				textArea.append("\nIncome tax refund. Collect 20.");
				tempPlayer.setBalance(tempPlayer.getBalance() + 20);
				break;
			case 12:
				textArea.append("\nYour Pokemon came second place in a beauty contest. Collect 10");
				tempPlayer.setBalance(tempPlayer.getBalance() + 10);
				break;
			case 13:
				//It is your birthday. Collect £10 from each player
				textArea.append("\nIt is your birthday. Collect 10 from each player");
				for(int i=0; i < game.getPlayers(); i++){
					if(game.getPlayerArray()[i] != tempPlayer){
						game.getPlayerArray()[i].setBalance(game.getPlayerArray()[i].getBalance() - 10);
						tempPlayer.setBalance(tempPlayer.getBalance() + 10);
					}
				}
				break;
			case 14:
				textArea.append("\nGet out of jail free");
				boolean cardStatus=true;
				tempPlayer.setCardStatus(true);
				tempPlayer.incCounter();
				break;
			case 15:
				//Pay a £10 fine or take a Chance
				textArea.append("\nPay a 10 fine or draw a Professor Oak Card"
						+ "\n write \"Pay Fine\" or \"Professor Oak\"");
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if(!textField.getText().trim().isEmpty()) {
							String temp = textField.getText();
							textArea.append("\n> "+temp);
							Player tempPlayer = game.getPlayerArray()[game.getCurrentTurn()];
							switch (temp) {
								case "Pay Fine":
									tempPlayer.setBalance(tempPlayer.getBalance() - 10);
									break;
								case "Chance":
									landOnCard(game.getBoardSquareArray()[7],tempPlayer,game);
									break;
							}
						}	
					};
				});
			default:
				
				break;
			}
		}
		else
		{
			tempArray = game.getProfOakCards();
			switch (tempArray[0]) {
			case 0:
				textArea.append("\nAdvance to Go! 200 has been deposited into your account");
				tempPlayer.movePlayer(4, 491);
				tempPlayer.setPosition(0);
				tempPlayer.setBalance(tempPlayer.getBalance() + 200);
				
				break;
			case 1:
				textArea.append("\nAdvance to Go! 200 has been deposited into your account");
				tempPlayer.movePlayer(4, 491);
				tempPlayer.setPosition(0);
				tempPlayer.setBalance(tempPlayer.getBalance() + 200);
				break;
			case 2:
				textArea.append("\nGo to jail. Do not pass Go. Do not collect 200");
				tempPlayer.movePlayer(4,4);
				tempPlayer.setPosition(10);
				game.setAbleToRoll(false);
				tempPlayer.setPrisonStatus(true);
				break;
			case 3:
				textArea.append("\nAdvance to Venomoth!");
				tempPlayer.movePlayer(486, 216);
				if(tempPlayer.getPosition() > 24){
					textArea.append("You have passed go and collected 200");
					tempPlayer.setBalance(tempPlayer.getBalance() + 200);
				}
				tempPlayer.setPosition(24);
				break;
			case 4:
				textArea.append("\nAdvance to Pokeball! You passed Go! 200 has been deposited into your account");
				tempPlayer.movePlayer(4, 262);
				tempPlayer.setPosition(5);
				tempPlayer.setBalance(tempPlayer.getBalance() + 200);
				break;
			case 5:
				textArea.append("\nAdvance to Electrabuzz!");
				tempPlayer.movePlayer(168, 4);
				if(tempPlayer.getPosition() > 13){
					textArea.append("You have passed go and collected 200");
					tempPlayer.setBalance(tempPlayer.getBalance() + 200);
				}
				tempPlayer.setPosition(13);
				break;
			case 6:
				textArea.append("\nAdvance to Nidoking!");
				tempPlayer.movePlayer(78, 491);
				tempPlayer.setPosition(39);
				break;

			case 7:
					textArea.append("\nMove back three spaces");
					if(tempPlayer.getPosition()>=3)
					{
						tempPlayer.setPosition(tempPlayer.getPosition()-3);
						BoardSquare newBoardSquare = game.getBoardSquareArray()[tempPlayer.getPosition()];
						tempPlayer.movePlayer(newBoardSquare.getX(), newBoardSquare.getY());
					}
					else if(tempPlayer.getPosition()==0)
					{
						tempPlayer.setPosition(37);
						BoardSquare newBoardSquare = game.getBoardSquareArray()[tempPlayer.getPosition()];
						tempPlayer.movePlayer(newBoardSquare.getX(), newBoardSquare.getY());
					}
					else if(tempPlayer.getPosition()==1)
					{
						tempPlayer.setPosition(38);
						BoardSquare newBoardSquare = game.getBoardSquareArray()[tempPlayer.getPosition()];
						tempPlayer.movePlayer(newBoardSquare.getX(), newBoardSquare.getY());
					}
					else if(tempPlayer.getPosition()==2)
					{
						tempPlayer.setPosition(39);
						BoardSquare newBoardSquare = game.getBoardSquareArray()[tempPlayer.getPosition()];
						tempPlayer.movePlayer(newBoardSquare.getX(), newBoardSquare.getY());
					}
				break;
			case 8:
					textArea.append("\nMake general repairs on all of your houses. For each house pay 25, for each hotel pay 100");
					for(int i=0;i<40;i++){
						if(game.getBoardSquareArray()[i].getOwner()==game.getCurrentTurn()){
							int price=game.getBoardSquareArray()[i].getHouses()*25;
							tempPlayer.setBalance(tempPlayer.getBalance()-price);
						}
					}
					
				break;
			case 9:
					textArea.append("\nPay school fees of 150");
					tempPlayer.setBalance(tempPlayer.getBalance()-150);
					break;
			case 10:
					textArea.append("\nDrunk in charge fine 20");
					tempPlayer.setBalance(tempPlayer.getBalance()-20);
					break;
			case 11:
					textArea.append("\nSpeeding fine 15");
					tempPlayer.setBalance(tempPlayer.getBalance()-15);
					break;
			case 12:
					textArea.append("\nYour building loan matures. Recieve 150");
					tempPlayer.setBalance(tempPlayer.getBalance()+150);
					break;
			case 13:
					textArea.append("\nYou have won a crossword competition, Collect 100");
					tempPlayer.setBalance(tempPlayer.getBalance()+100);
					break;
			case 14:
					textArea.append("\nBank pays you dividend of 50");
					tempPlayer.setBalance(tempPlayer.getBalance()+50);
					break;
			case 15:
					textArea.append("\nGet out of jail free. This card may be kept until needed or sold");
					boolean cardStatus=true;
					tempPlayer.setCardStatus(true);
					tempPlayer.incCounter();
				break;
			default:
				
				break;
			}
		}
		temp = tempArray[0];
		for(int i = 0; i < tempArray.length - 2; i++)
		{
			tempArray[i] = tempArray[i + 1];
		}
		tempArray[tempArray.length - 1] = temp;
	}
	
	public int jail_roll(Player tempPlayer, Game game) // roll function to allow user to get out off jail.
	{
		if(game.isAbleToRoll())
		{
			game.setAbleToRoll(false);
			int[] tempDiceArray = new int[2];
			tempDiceArray = rollDice();
			
			textArea.append("\nYou rolled a " + tempDiceArray[0] + " and a " + tempDiceArray[1] + ", ");
			textArea.append("totalling " + (tempDiceArray[0] + tempDiceArray[1]));
			if(tempDiceArray[0] == tempDiceArray[1]) 
			{
				int oldPosition = (tempPlayer.getPosition());
				int newPosition = (tempPlayer.getPosition() + tempDiceArray[0] + tempDiceArray[1]) % 40;
				// Store the position before rolling and position after rolling as ints
				BoardSquare newBoardSquare = game.getBoardSquareArray()[newPosition];
				tempPlayer.movePlayer(newBoardSquare.getX(), newBoardSquare.getY());
				// Move the player to the position of the new square that we landed on
				tempPlayer.setPosition(newPosition);
				textArea.append(", you rolled doubles!");
				game.setAbleToRoll(false);
				tempPlayer.setPrisonStatus(false);
				textArea.append("\nType \"done\" to end your turn");
				return newPosition;
			}
			else
			{
				textArea.append("\nYou did not role doubles, hence you are still in jail");
				game.setAbleToRoll(false);
				// If they didn't roll doubles, they can not
				textArea.append("\nType \"done\" to end your turn");
			}
		}
		return 10;
	}
	public void PrisonMove(Player tempPlayer, Game game, int pos){ //in case the player fails to role a double 3 times while in prison
		BoardSquare newBoardSquare = game.getBoardSquareArray()[pos];
		tempPlayer.movePlayer(newBoardSquare.getX(), newBoardSquare.getY());
		tempPlayer.setPosition(pos);
		game.setAbleToRoll(false);
		tempPlayer.setPrisonStatus(false);
		textArea.append("\nType \"done\" to end your turn");
		
	}
	public int[] rollDice() 
	{
		int[] diceArray = new int[2];
		Random randomGenerator = new Random();
		diceArray[0] = randomGenerator.nextInt(6) + 1;
		diceArray[1] = randomGenerator.nextInt(6) + 1;
		return diceArray;
	}
}
			
