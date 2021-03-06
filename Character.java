import java.util.ArrayList;
import java.util.Random;


public class Character extends Player implements Comparable {

	
	private String name;
	private String playerImage;
	private ArrayList<Bag> bags = new ArrayList<Bag>();
	private ArrayList<Ruby> rubies = new ArrayList<Ruby>();
	private ArrayList<LockBox> lockboxes = new ArrayList<LockBox>();
	private ArrayList <ActionCard> cardInventory = new ArrayList <ActionCard> ();
	private int x, y;
	
	public Character(String n, int c, int le, int x, int y){
		currentCar = c;
		currentLevel = le;
		name = n;
		
		switch(n){
			case "django":
				playerImage = "images/Django_Idle";
				break;
			case "ghost":
				playerImage = "images/Ghost_Idle";
				break;
			case "cheyenne":
				playerImage = "images/Cheyenne_Idle";
				break;
			case "tuco":
				playerImage = "images/Tuco_Idle";
				break;
			case "doc":
				playerImage = "images/Doc_Idle";
				break;
			case "belle":
				playerImage = "images/Belle_Idle";
				break;
		}
		
		for (int i = 0; i < 6; i++) {
			
			cardInventory.add(new ActionCard ("bullet"));
			
		}
		
	}
	
	public ActionCard getBulletCard () {
		
		if (cardInventory.size () > 0) {
			
			return cardInventory.remove (0);
			
		}
		
		return null;
		
	}
	
	public String getName(){
		return name;
	}
	
	public int getCurrentCar(){
		return currentCar;
	}
	
	public void updateCurrentCar (int x) {
		
		currentCar = x;
		
	}
	
	public void addBags(Bag b){
		bags.add(b);
	}
		
	public void addRubies(Ruby r){
		rubies.add(r);
	}
	public void addLockBoxes(LockBox L)
	{
		lockboxes.add(L);
	}
	
	
	public ArrayList<Bag> getBags(){
		return bags;
	}
	public ArrayList<Ruby> getRubies(){
		return rubies;
	}
	public ArrayList<LockBox> getLockboxes(){
		return lockboxes;
	}
	public int getLevel(){
		return currentLevel;
	}
	
	public void updateLevel (int x) {
		
		currentLevel = x;
		
	}
	
	public Bag removeBag(){
		try {
			int index = (int)(Math.random()*bags.size());
			bags.remove(index);
			return bags.get(index);
		}
		catch (IndexOutOfBoundsException e) {
			
			return null;
			
		}
	}
	public Ruby removeRuby()
	{
		try {
			int index = (int)(Math.random()*bags.size());
			rubies.remove(index);
			return rubies.get(index);
		}
		catch (IndexOutOfBoundsException e) {
			
			return null;
			
		}
	}
	public LockBox removeLockBox()
	{
		try {
			int index = (int)(Math.random()*bags.size());
			lockboxes.remove(index);
			return lockboxes.get(index);
		}
		catch (IndexOutOfBoundsException e) {
			
			return null;
			
		}
	}
	
	public void update(){
		
	}
	
	public int getTotal()
	{
		int sum = 0;
		for (int y = 0; y<getRubies().size(); y++)
		{
			try {
				sum+=getRubies().get(y).getAmount();
			}
			catch (NullPointerException e) {
				
			}
		}
		for (int y = 0; y<getBags().size(); y++)
		{
			try {
				sum+=getBags().get(y).getAmount();
			}
			catch (NullPointerException e) {
				
			}
		}
		for (int y = 0; y<getLockboxes().size(); y++)
		{
			try {
				sum+=getLockboxes().get(y).getAmount();
			}
			catch (NullPointerException e) {
				
			}
		}
		return sum;
	}

	@Override
	public int compareTo(Object o) {
		
		return ((Character) o).getName ().compareTo(this.getName ());
		
	}
	
}
