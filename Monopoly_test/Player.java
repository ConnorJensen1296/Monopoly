package Monopoly_test;
import javax.swing.*;
import java.awt.*;

public class Player extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int position, xLocation, yLocation, firstDiceRoll, prisonCount;
	Color counterColor;
	private String name;
	private int balance, ballsOwned, birdsOwned, jailCards;
	private static int playerRollCount;
	// jailCards being the amount of get out of jail free cards the player has
	private boolean status,prisonStatus, cardStatus;
	int cardCounter;
	public Player(int x, int y, Color color) 
	{
		xLocation = x;
		yLocation = y;
		// xLocation and yLocation will be the coordinates of where the counter is with respect to the corner of whatever square it is in
		counterColor = color;
		position = 0;
		// All counters must start at the first square
		balance = 1500;
		ballsOwned = 0;
		birdsOwned = 0;
		jailCards = 0;
		status=false;
		prisonStatus=false; //indicates if the player is in prison or not 
		prisonCount=0;
		cardStatus=false;
		cardCounter=0;
	}
	public int getCardCounter()
	{
		return this.cardCounter;
	}
	public void setCardStatus(boolean val)
	{
		this.cardStatus=val;
	}
	public boolean getCardStatus()
	{
		return this.cardStatus;
	}
	public void incCounter()
	{
		this.cardCounter+=1;
	}
	public void decCounter()
	{
		this.cardCounter-=1;
	}
	public static int getRollCount(){
		return playerRollCount;
	}
	public static void setRollCount(int rollCount){
		playerRollCount = rollCount;
	}
	public void setCount()
	{
		this.prisonCount+=1;
	}
	public int getCount()
	{
		return this.prisonCount;
	}
	public void setPrisonStatus(boolean val)
	{
		this.prisonStatus=val;
	}
	public boolean getPrisonStatus()
	{
		return this.prisonStatus;
	}
	public int getJailCards()
	{
		return jailCards;
	}
	public void setJailCards(int jailCards)
	{
		this.jailCards = jailCards;
	}
	public void buyBird()
	{
		birdsOwned += 1;
	}
	public void buyBall()
	{
		ballsOwned += 1;
	}
	public void setBirdsOwned(int birdsOwned)
	{
		this.birdsOwned = birdsOwned;
	}
	public int getBirdsOwned()
	{
		return birdsOwned;
	}
	public void setBallsOwned(int ballsOwned)
	{
		this.ballsOwned = ballsOwned;
	}
	public int getBallsOwned()
	{
		return ballsOwned;
	}
	public void setStatus(boolean cond)
	{
		this.status=cond;
	}
	public boolean getStatus()
	{
		return status;
	}
	public int getPosition() 
	{
		return position;
	}
	public void setPosition(int position) 
	{
		this.position = position;
	}
	public void movePlayer(int x, int y) 
	{
		this.setBounds(x + xLocation, y + yLocation, 10, 10);
		// By changing the bounds, we change the place that the counter considers as (0, 0)
		// We put the coordinates of the corner of the square we wish to move the counter to, and it will place it on the square in its correct place
	}
	public void pay(Player recipient, int amount) 
	{
		this.setBalance(this.getBalance() - amount);
		recipient.setBalance(recipient.getBalance() + amount);
	}
	public int tax()
	{
		int amount=this.getBalance()*(1/10);
		if((this.getBalance()*0.1)<200)
		{
			this.setBalance(this.getBalance()-amount);
			return amount;
		}
		else
		{
			this.setBalance(this.getBalance()-200);
			return 200;
		}
	}
	public void purchase(BoardSquare square, int buyer)
	{
		this.setBalance(this.getBalance()-square.getPrice());
		square.setOwned(true);
		square.setOwner(buyer);
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getName() 
	{// function for returning the player's name
		return name;
	}
	public void setFirstDiceRoll(int i) 
	{
		firstDiceRoll = i;
	}
	public int getFirstDiceRoll() 
	{ //return the first dice roll of the player
		return firstDiceRoll;
	}
	public void setBalance(int balance)
	{
		this.balance = balance;
	}
	public int getBalance()
	{ //function for returning the player's current balance
		return balance;
	}
	public void bail_payment() // payment to get out of jail
	{
		this.setBalance(this.getBalance()-50);
	}
	public void paintComponent(Graphics g)
	{
		g.setColor(counterColor);
		Graphics2D g2 = (Graphics2D) g;
		Rectangle counterSquare = new Rectangle(0, 0, 10, 10);
		g2.fill(counterSquare);
		counterSquare.setBounds(0, 0, 9, 9);
		g2.setColor(Color.BLACK);
		g2.draw(counterSquare);
	}
	
}