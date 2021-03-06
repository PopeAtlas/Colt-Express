import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;


public class Runner {
	
	private static ArrayList<String> characterNames = new ArrayList<String>();
	
	private static ArrayList<Character> finalchar = new ArrayList<Character>();
	
	private static TreeMap <Character, ArrayList <ActionCard>> hands = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private static TreeMap <Character, ArrayList <ActionCard>> bulletCards = new TreeMap <Character, ArrayList <ActionCard>> ();
		
	private static TreeMap <Character, ArrayList <ActionCard>> discard = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private static TreeMap <Character, ArrayList <ActionCard>> draw = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	public static void selectCharacters (Train mainTrain) {
		
		for(int i = 0; i < 4; i++)
		{
			System.out.println("What character? Choose: ");
			
			for(int j = 0; j<characterNames.size(); j++)
			{
				
				System.out.println((j+1) + ". " + characterNames.get(j));
				
			}
			
			Scanner key = new Scanner(System.in);
			
			int use = key.nextInt();
			
			System.out.println("Would you like to be in the Caboose(4) or the car in front of the Caboose(3)?");
			
			int pos = key.nextInt();
			
			Character created = new Character(characterNames.remove(use-1), pos, 0, 0, 0);
			
			finalchar.add(created);
			
			mainTrain.getTrainCar(pos).getPlatform(0).addPlayer(created);
			
		}
		
	}
	
	public static void randomCharacters (Train mainTrain) {
		
		ArrayList <Character> posibilities = new ArrayList <Character> ();
		
		for (int i = 0; i < 6; i++) {
			
			posibilities.add (new Character (characterNames.get (i), randomPos (), 0, 0, 0));
			
		}
		
		Collections.shuffle (posibilities);
		
		posibilities.remove (posibilities.size () - 1);
		
		posibilities.remove (posibilities.size () - 1);
		
		for (int i = 0; i < posibilities.size (); i++) {
			
			finalchar.add (posibilities.get(i));
			
			System.out.print (posibilities.get(i).getName () + " is inserted ");
			
			if (posibilities.get(i).getCurrentCar() == 4) {
				
				System.out.println (" in the caboose");
				
			}
			else {
				
				System.out.println (" in front of the caboose");
				
			}
			
			mainTrain.getTrainCar (posibilities.get(i).getCurrentCar()).getPlatform(0).addPlayer(posibilities.get(i));
			
		}
		
	}
	
	private static int randomPos () {
		
		return (int)(Math.random () * 2 + 3);
		
	}

	public static void main(String[] args)
	{
		
		RoundCardSelector rs = new RoundCardSelector ();
		
		ArrayList <RoundCard> roundCardList = rs.select ();
		
		Train mainTrain = new Train();
		
		fillOutNames();
		
		// selectCharacters (mainTrain);
		
		randomCharacters (mainTrain);
		
		
		
		//mainTrain.getTrainCar(0).getPlatform(0).addPlayer(new Character ("marshall", 0, 0, 0, 0));
		
		finalchar.add(new Character("marshall", 0, 0, 0, 0));
		
		Round round = new Round(mainTrain, finalchar, hands, bulletCards, discard, draw, roundCardList.get (0));
		
		roundCardList.add (roundCardList.remove (0));
		
		round.First();
		
		round.playRound();
				
		do
		{
			finalchar.add(finalchar.remove(0));
			
			roundCardList.add (roundCardList.remove (0));
			
			RoundCard r = roundCardList.get (0);
			
			round = new Round(mainTrain, finalchar, hands, bulletCards, discard, draw, r);
			
			round.startOfRound();
		
			round.playRound();
			
		}
		while (!round.getRoundCard ().getIsStopCard ());
	
		EndGame eg = new EndGame(finalchar);
		TreeMap<Integer, ArrayList<Character>> rankings = eg.getResults();
		ArrayList<Character> list = new ArrayList<>();
		int count = 1;
		for (int i : rankings.keySet())
		{
			for (int x = 0; x<rankings.get(i).size(); x++)
			{
				list.add(rankings.get(i).get(x));
			}
		}
		count = 1; 
		for (int x =list.size()-1; x>=0; x--)
		{
			if (!list.get(x).getName().equals("marshall"))
			{
				System.out.println(count+".\t"+list.get(x).getName()+"\t"+list.get(x).getTotal());
				count++;
			}
		}
	}
	
	public static void fillOutNames()
	{
		characterNames.add("django");
		
		characterNames.add("cheyenne");
		
		characterNames.add("doc");
		
		characterNames.add("ghost");
		
		characterNames.add("tuco");
		
		characterNames.add("belle");
	}
	
}

