package Monopoly_test;
public class BoardSquare {
	private int x, y, owner, salePrice, houses, housePrice, mortgage;
	// Houses will be the amount of houses currently on that property (5 houses means hotel)
	private int[] rent = new int[6];
	private BoardSquare[] colourGroup;
	private String name, type;
	private boolean owned, mortgaged;
	// mortgaged is the status of the property
	// x and y will be the coordinates of the top left corner


	public BoardSquare(int xCoord, int yCoord, String squareName, int[] squareRent, int squarePrice,  int mortgageValue, boolean mortgageStatus, String squareType) 
	{
		x = xCoord;
		y = yCoord;
		name = squareName;
		rent = squareRent;
		salePrice = squarePrice;
		type = squareType;
		owned = false;
		houses = 0;
		mortgage=mortgageValue;
		mortgaged=mortgageStatus;
	}
	public int getX() 
	{
		return x;
	}
	public int getY() 
	{
		return y;
	}
	public BoardSquare[] getColourGroup() 
	{
		return colourGroup;
	}
	public void setColourGroup(BoardSquare[] colourGroup)
	{
		this.colourGroup = colourGroup;
	}
	public int getHouses()
	{
		return houses;
	}
	public void setHouses(int houses)
	{
		this.houses = houses;
	}
	public int getHousePrice()
	{
		return housePrice;
	}
	public void setHousePrice(int housePrice)
	{
		this.housePrice = housePrice;
	}
	public int[] getRent()
	{
		return rent;
	}
	public int getPrice()
	{
		return salePrice;
	}
	public String getName() 
	{
		return name;
	}
	public String getType() 
	{
		return type;
	}
	public int getOwner() 
	{
		return owner;
	}
	public void setOwner(int owner) 
	{
		this.owner = owner;
	}
	public boolean isOwned() 
	{
		return owned;
	}
	public void setOwned(boolean owned) 
	{
		this.owned = owned;
	}
	public int getMortgageValue(){
		return mortgage;
	}
	public boolean getMortgageStatus(){
		return mortgaged;
	}
	public void setMortgageStatus(boolean mortgageStatus){
		this.mortgaged = mortgageStatus;
	}
}