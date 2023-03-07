package project;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import project.Lines;
import project.route;
import project.Stop;

public class CTA {
	//Bryan Evans 12/10/2021
	//The application class of the project. Responsible for user interaction, data editing, data storage, and data addition.
	public static ArrayList<Stop> readData(){
	//Reads the CTAStops.csv info into an arraylist.
		ArrayList<Stop> stops = new ArrayList<Stop>();
		try {
			File file = new File("src/project/CTAStops.csv");
			Scanner input = new Scanner(file);
			Stop s;
			if(input.hasNextLine()){
			    input.nextLine();
			}
			while (input.hasNextLine()) {
				s=null;
				String line = input.nextLine();
				String[] values = line.split(",");
				s = new Lines(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]), values[3], Boolean.parseBoolean(values[4]),
					Integer.parseInt(values[5]), Integer.parseInt(values[6]), Integer.parseInt(values[7]), Integer.parseInt(values[8]), Integer.parseInt(values[9]), Integer.parseInt(values[10]), Integer.parseInt(values[11]));
				stops.add(s);
			}
			input.close();
			
		} catch (Exception e) {
			System.out.println("Error reading file");
		}
		return stops;
	}
	
	public static ArrayList<route> readDataroute(){
	//reads the routes.csv info into an arraylist.
		ArrayList<route> routes = new ArrayList<route>();
		try {
			File fileroute = new File("src/project/routes.csv");
			Scanner inputroute = new Scanner(fileroute);
			route r;
			while(inputroute.hasNextLine()) {
				r=null;
				String line = inputroute.nextLine();
				String[] values = line.split(",");
				r = new route(values[0], values[1], values[2], values[3], values[4]);
				
				routes.add(r);
			}
			inputroute.close();
		}catch(Exception e) {
			System.out.println("Error reading file");
		}
		return routes;
	}
	
	
	public static ArrayList<Stop> newlineorder(int redorder, int greenorder, int blueorder, int brownorder, int purpleorder, int pinkorder, int orangeorder, ArrayList<Stop> stops, ArrayList<route> routes){
		//changes the order of the lines in the stops arraylist when a stop is added.
		saveData(stops, routes);
		int[] neworders = {redorder, greenorder, blueorder, brownorder, purpleorder, pinkorder, orangeorder};
		System.out.println("Beginning Reordering...");
		try {
			File file1 = new File("src/project/CTAStops.csv");
			Scanner input1 = new Scanner(file1);
			
			int counter = 0;
			while (input1.hasNextLine()) {
				String line = input1.nextLine();

				counter++;
			}
			System.out.println("Reordering Lines...");
			input1.close();
			int[] newredorder = new int[counter+1]; int[] newgreenorder = new int[counter+1]; int[] newblueorder = new int[counter+1]; int[] newbrownorder = new int[counter+1]; int[] newpurpleorder = new int[counter+1]; int[] newpinkorder = new int[counter+1]; int[] neworangeorder = new int[counter+1];
			for(int q=0; q<neworders.length; q++) {
				int[] sortarray = new int[counter+1];
				
				Scanner input2 = new Scanner(file1);
				int arraycount = 0;
				while (input2.hasNextLine()) {
					String line = input2.nextLine();
					String[] values = line.split(",");
					sortarray[arraycount] = Integer.parseInt(values[q+5]);
					arraycount++;
				}
				input2.close();
				
				sortarray[counter+1] = neworders[q];
				boolean done1;
				do {
					done1 = true;
					for (int i=0; i<sortarray.length; i++) {
						if (sortarray[sortarray.length] < (sortarray[i])) {
							sortarray[i] = sortarray[i]+1;
							done1 = false;
						}
					}
				} while (!done1);
				
				if(q==0) {
					newredorder = sortarray;
				}else if(q==1){
					newgreenorder = sortarray;
				}else if(q==2) {
					newblueorder = sortarray;
				}else if(q==3) {
					newbrownorder = sortarray;
				}else if(q==4) {
					newpurpleorder = sortarray;
				}else if(q==5) {
					newpinkorder = sortarray;
				}else if(q==6) {
					neworangeorder = sortarray;
				}
			}
			System.out.println("Lines Reordered in Temporary List");
			ArrayList<Stop> temp = new ArrayList<Stop>();
			Scanner input3 = new Scanner(file1);
			Stop s;

			int counter1 = 0;
			while (input3.hasNextLine()) {
				
				s=null;
				String line = input3.nextLine();
				String[] values = line.split(",");
				s = new Lines(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]), values[3], Boolean.parseBoolean(values[4]),
					newredorder[counter1], newgreenorder[counter1], newblueorder[counter1], newbrownorder[counter1], newpurpleorder[counter1], newpinkorder[counter1], neworangeorder[counter1]);
				counter1++;
				temp.add(s);
				
			}
			input3.close();
			System.out.println("Lines Reordered in Main List");
			stops = temp;
		} catch (Exception e) {
			System.out.println("Error reading file");
		}
		
		
		
		return stops;
	}	
	
	public static Stop createstation(Scanner input, ArrayList<Stop> stops, ArrayList<route> routes) {
		//Used to create a new train station Stop object to be added into the stops arraylist.
		Stop s = null;
		
		System.out.print("Stop Name:");
		String stopname = input.next();
		Double latitude = inputdoublecheck(input, "Latitude (XX.XXXXX): ");//makes sure the user inputs a double
		Double longitude = inputdoublecheck(input, "Longitude (XX.XXXX):");
		
		String desc = null;
		desc = descprompt(input, desc);
		Boolean wheelchair = inputbooleancheck(input, "Wheelchair Accessibility (True or False):");//makes sure the user inputs true or false
		
		int redorder = -1; int greenorder = -1; int blueorder = -1; int brownorder = -1; int purpleorder = -1; int pinkorder = -1; int orangeorder = -1;
		System.out.println();
		System.out.println("Select each line one by one that is at this station.");
		
		boolean done = false;
		do {
			System.out.println("1. Red");
			System.out.println("2. Green");
			System.out.println("3. Blue");
			System.out.println("4. Brown");
			System.out.println("5. Purple");
			System.out.println("6. Pink");
			System.out.println("7. Orange");
			System.out.println("8. Done");
			System.out.print("Choice: ");
			String choice2 = input.next();
			System.out.println();
			switch (choice2) {
				case "1":	
					redorder = inputintegercheck(input, "What is the stop's order on the Red Line?");//makes sure the user inputs an integer
					break;
				case "2":	
					greenorder = inputintegercheck(input, "What is the stop's order on the Green Line?");
					break;
				case "3":
					blueorder = inputintegercheck(input, "What is the stop's order on the Blue Line?");
					break;
				case "4":
					brownorder = inputintegercheck(input, "What is the stop's order on the Brown Line?");
					break;
				case "5":	
					purpleorder = inputintegercheck(input, "What is the stop's order on the Purple Line?");
					break;
				case "6":	
					pinkorder = inputintegercheck(input, "What is the stop's order on the Pink Line?");
					break;
				case "7":	
					orangeorder = inputintegercheck(input, "What is the stop's order on the Orange Line?");
					break;
				case "8":
					done=true;
					break;
				default:
					System.out.println("I'm sorry, but I didn't understand that. ");
			}
		}while(!done);
		stops = newlineorder(redorder, greenorder, blueorder, brownorder, purpleorder, pinkorder, orangeorder, stops, routes);//puts the new stop orders into the arraylist then sorts it.
		System.out.println("Order of lines have been reordered.");
		s = new Lines(stopname, latitude, longitude, desc, wheelchair, redorder, greenorder, blueorder, brownorder, purpleorder, pinkorder, orangeorder);//stop object created
		System.out.println(s+"\nNew Station/Stop Added.");
		return s;
	}

	
	public static ArrayList<Stop> addstop(ArrayList<Stop> stops, Stop s) {		
		//Adds the stop object from createstation to the arraylist
		stops.add(s);
		
		return stops;
	}
	
	public static String descprompt(Scanner input, String desc) {
		//prompts the user for the description regarding the train stations
		boolean done2 = false;
		do {
			System.out.println();
			System.out.println("1. Surface");
			System.out.println("2. Elevated");
			System.out.println("3. At-Grade");
			System.out.println("4. Embankment");
			System.out.println("5. Has Subway or Only Subway");
			System.out.print("Choice: ");
			String choice1 = input.next();
			System.out.println();
			switch (choice1) {
				case "1":	
					desc = "surface";
					done2 = true;
					break;
				case "2":	
					desc = "elevated";
					done2 = true;
					break;
				case "3":
					desc = "at-grade";
					done2 = true;
					break;
				case "4":
					desc = "embankment";
					done2 = true;
					break;
				case "5":	
					System.out.println();
					System.out.println("1. Surface with Subway");
					System.out.println("2. Elevated with Subway");
					System.out.println("3. At-Grade With Subway");
					System.out.println("4. Embankment With Subway");
					System.out.println("5. Only Subway");
					System.out.print("Choice: ");
					String choice2 = input.next();
					System.out.println();
					switch (choice2) {
						case "1":	
							desc = "surface/subway";
							done2 = true;
							break;
						case "2":	
							desc = "elevated/subway";
							done2 = true;
							break;
						case "3":
							desc = "at-grade/subway";
							done2 = true;
							break;
						case "4":
							desc = "embankment/subway";
							done2 = true;
							break;
						case "5":	
							desc = "subway";
							done2 = true;
							break;
						
						default:
							System.out.println("I'm sorry, but I didn't understand that. Try Again.");
					}
					break;
				
				default:
					System.out.println("I'm sorry, but I didn't understand that. Try Again.");
			}
		}while(!done2);
		return desc;
	}
	
	public static ArrayList<Stop> editlineorder(int oldredorder, int oldgreenorder, int oldblueorder, int oldbrownorder, int oldpurpleorder, int oldpinkorder, int oldorangeorder, int redorder, int greenorder, int blueorder, int brownorder, int purpleorder, int pinkorder, int orangeorder, ArrayList<Stop> stops, ArrayList<route> routes){
		//reorders the train station orders when a station is edited instead of added.
		saveData(stops, routes);
		int[] oldorders = {oldredorder, oldgreenorder, oldblueorder, oldbrownorder, oldpurpleorder, oldpinkorder, oldorangeorder};

		int[] neworders = {redorder, greenorder, blueorder, brownorder, purpleorder, pinkorder, orangeorder};
		System.out.println("Beginning Reordering...");
		try {
			File file1 = new File("src/project/CTAStops.csv");
			Scanner input1 = new Scanner(file1);
			
			int counter = 0;
			while (input1.hasNextLine()) {
				String line = input1.nextLine();

				counter++;
			}
			System.out.println("Reordering Lines...");
			input1.close();
			int[] newredorder = new int[counter]; int[] newgreenorder = new int[counter]; int[] newblueorder = new int[counter]; int[] newbrownorder = new int[counter]; int[] newpurpleorder = new int[counter]; int[] newpinkorder = new int[counter]; int[] neworangeorder = new int[counter];
			for(int q=0; q<neworders.length; q++) {
				int[] sortarray = new int[counter];
				
				Scanner input2 = new Scanner(file1);
				int arraycount = 0;
				while (input2.hasNextLine()) {
					String line = input2.nextLine();
					String[] values = line.split(",");
					sortarray[arraycount] = Integer.parseInt(values[q+5]);
					arraycount++;
				}
				input2.close();
				int changingelement = 0;
				boolean truincfaldec = false;
				for(int u=0; u<sortarray.length; u++) {
					if(oldorders[q]>neworders[q]) {
						truincfaldec = false;
					}else {
						truincfaldec = true;
					}
					if(sortarray[u]==oldorders[q]) {
						sortarray[u]=neworders[q];
						changingelement = u;
					}
				}
				boolean done1;
				if(oldorders[q]!=neworders[q]) {
					do {
						done1 = true;
						for (int i=0; i<sortarray.length; i++) {
							if (truincfaldec==false) {
								if (sortarray[changingelement] < sortarray[i]) {
									sortarray[i] = sortarray[i]+1;
									done1 = false;
								}else if(sortarray[changingelement]==sortarray[q]&&changingelement!=q) {
									sortarray[i] = sortarray[i]+1;
									done1 = false;
								}
							}else {
								if(sortarray[changingelement] > sortarray[i]) {
									sortarray[i] = sortarray[i]-1;
									done1 = false;
								}else if(sortarray[changingelement]==sortarray[q]&&changingelement!=q) {
									sortarray[i] = sortarray[i]-1;
									done1 = false;
								}
							}
							
						}
					} while (!done1);
				}
				
				
				if(q==0) {
					newredorder = sortarray;
				}else if(q==1){
					newgreenorder = sortarray;
				}else if(q==2) {
					newblueorder = sortarray;
				}else if(q==3) {
					newbrownorder = sortarray;
				}else if(q==4) {
					newpurpleorder = sortarray;
				}else if(q==5) {
					newpinkorder = sortarray;
				}else if(q==6) {
					neworangeorder = sortarray;
				}
			}
			System.out.println("Lines Reordered in Temporary List");
			ArrayList<Stop> temp = new ArrayList<Stop>();
			Scanner input3 = new Scanner(file1);
			Stop s;

			int counter1 = 0;
			while (input3.hasNextLine()) {
				
				s=null;
				String line = input3.nextLine();
				String[] values = line.split(",");
				s = new Lines(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]), values[3], Boolean.parseBoolean(values[4]),
					newredorder[counter1], newgreenorder[counter1], newblueorder[counter1], newbrownorder[counter1], newpurpleorder[counter1], newpinkorder[counter1], neworangeorder[counter1]);
				counter1++;
				temp.add(s);
				
			}
			input3.close();
			System.out.println("Lines Reordered in Main List");
			stops = temp;
		} catch (Exception e) {
			System.out.println("Error reading file");
		}
		
		
		
		return stops;
	}
	
	public static String[] changelineorder(Scanner input, ArrayList<Stop> stops, String[] values, ArrayList<route> routes) {
		//gets the neccessary data for editing a stations train line orders.
		int redorder = Integer.parseInt(values[5]); int greenorder = Integer.parseInt(values[6]); int blueorder = Integer.parseInt(values[7]); int brownorder = Integer.parseInt(values[8]); int purpleorder = Integer.parseInt(values[9]); int pinkorder = Integer.parseInt(values[10]); int orangeorder = Integer.parseInt(values[11]);
		int oldredorder = redorder; int oldgreenorder = greenorder; int oldblueorder = blueorder; int oldbrownorder = brownorder; int oldpurpleorder = purpleorder; int oldpinkorder = pinkorder; int oldorangeorder = orangeorder;
		System.out.println();
		System.out.println("Select each line one by one to be changed at this station.");
		
		boolean done = false;
		do {
			System.out.println("1. Red");
			System.out.println("2. Green");
			System.out.println("3. Blue");
			System.out.println("4. Brown");
			System.out.println("5. Purple");
			System.out.println("6. Pink");
			System.out.println("7. Orange");
			System.out.println("8. Done");
			System.out.print("Choice: ");
			String choice2 = input.next();
			System.out.println();
			switch (choice2) {
				case "1":	
					redorder = inputintegercheck(input, "What is the stop's order on the Red Line?");
					values[5] = String.valueOf(redorder);
					break;
				case "2":	
					greenorder = inputintegercheck(input, "What is the stop's order on the Green Line?");
					values[6] = String.valueOf(greenorder);
					break;
				case "3":
					blueorder = inputintegercheck(input, "What is the stop's order on the Blue Line?");
					values[7] = String.valueOf(blueorder);
					break;
				case "4":
					brownorder = inputintegercheck(input, "What is the stop's order on the Brown Line?");
					values[8] = String.valueOf(brownorder);
					break;
				case "5":	
					purpleorder = inputintegercheck(input, "What is the stop's order on the Purple Line?");
					values[9] = String.valueOf(purpleorder);
					break;
				case "6":	
					pinkorder = inputintegercheck(input, "What is the stop's order on the Pink Line?");
					values[10] = String.valueOf(pinkorder);
					break;
				case "7":	
					orangeorder = inputintegercheck(input, "What is the stop's order on the Orange Line?");
					values[11] = String.valueOf(orangeorder);
					break;
				case "8":
					done=true;
					break;
				default:
					System.out.println("I'm sorry, but I didn't understand that. ");
			}
		}while(!done);
		stops = editlineorder(oldredorder, oldgreenorder, oldblueorder, oldbrownorder, oldpurpleorder, oldpinkorder, oldorangeorder, redorder, greenorder, blueorder, brownorder, purpleorder, pinkorder, orangeorder, stops, routes);
		//reorders all stations line orders
		return values;
	}

	
	public static Stop editstopinfo(Scanner input, ArrayList<Stop> possible, ArrayList<Stop> stops, ArrayList<route> routes) {
		//responsible for editing a station's information
		Stop s = possible.get(0);
		String temp = s.toString();
		String[] values = temp.split(",");
		System.out.println("What would you like to edit? 0-Name 1-Latitude 2-Longitude 3-Description 4-Wheelchair Accessibility 5-Line Order");
		String choice = input.next();
		switch (choice) {
		case "0":	
			System.out.println("What would you like to change the name to?");
			values[0] = input.next();
			break;
		case "1":	
			System.out.println("What would you like to change the latitude to? (XX.XXXXX)");
			values[1] = input.next();
			break;
		case "2":	
			System.out.println("What would you like to change the longitude to? (XX.XXXX)");
			values[2] = input.next();
			break;
		case "3":
			System.out.println("What would you like to change the Description to?");
			String desc = null;
			descprompt(input, desc);
			values[3] = desc;
			break;
		case "4":	
			System.out.println("What would you like to change the Wheelchair Accessibility to? (True/False)");
			values[4] = input.next();
			break;
		case "5":
			values = changelineorder(input, stops, values, routes);//responsible for changing the station's line aspects
			break;
		
		
		default:
			System.out.println("I'm sorry, but I didn't understand that. ");
		}
		Stop s1 = new Lines(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]), values[3], Boolean.parseBoolean(values[4]), Integer.parseInt(values[5]), Integer.parseInt(values[6]), Integer.parseInt(values[7]), Integer.parseInt(values[8]), Integer.parseInt(values[9]), Integer.parseInt(values[10]), Integer.parseInt(values[11]));
		//new edited stop's objct
		return s1;
	}
	public static ArrayList<Stop> editstop(Scanner input, ArrayList<Stop> stops, ArrayList<route> routes){
		//finds the stop to be edited, then switches it with the edited stop's object
		ArrayList<Stop> possible = search(input, stops);
		
		if (possible.size() == 0) {
			System.out.println("Stop not found.");
		} else if (possible.size() == 1) {
			System.out.println("Would you like to edit Stop: " + possible.get(0));
			String editchoice = inputyesnocheck(input, "Input a yes or no");
			if(editchoice.equalsIgnoreCase("yes")) {
				Stop s = null;
				s = editstopinfo(input, possible, stops, routes);
				
				int editcounter = 0;
				for (Stop current : stops) {
					
					if(current.equals(possible.get(0))){
						stops.set(editcounter, s);
					}
					editcounter++;
				}
				
				
				System.out.println(possible.get(0).getname() + " has been changed.");
			}else {
				System.out.println("Not changing... Returning to main menu.");
			}
		}else {
			int i=0;
			for(i=0; i<possible.size(); i++) {
				System.out.println(i+". "+ possible.get(i));
			}
			int editchoice1 = inputintegercheck(input, "There are multiple stops with this name. Choose which you would like to edit.\nEnter '-1' to not change any.");
			if(editchoice1 != -1) {
				
				Stop s = null;
				s = editstopinfo(input, possible, stops, routes);//returns edited stop's info
				
				int editcounter = 0;
				for (Stop current : stops) {
					
					if(current.equals(possible.get(editchoice1))){
						stops.set(editcounter, s);
					}
					editcounter++;
				}
				
				
				System.out.println(possible.get(editchoice1)+" has been changed.");
			}else {
				System.out.println("Not changing... Returning to main menu.");
			}
		}
		return stops;
	}
	
	public static ArrayList<Stop> removestop(Scanner input, ArrayList<Stop> stops) {
		//removes a stop from the array list.
		ArrayList<Stop> possible = search(input, stops);
		
		if (possible.size() == 0) {
			System.out.println("Stop not found.");
		} else if (possible.size() == 1) {
			System.out.println("Would you like to remove Stop: " + possible.get(0));
			String deletechoice = inputyesnocheck(input, "Input a yes or no");
			if(deletechoice.equalsIgnoreCase("yes")) {
				stops.remove(possible.get(0));
				System.out.println(possible.get(0).getname() + " has been removed.");
			}else {
				System.out.println("Not removing... Returning to main menu.");
			}
		}else {
			int i=0;
			for(i=0; i<possible.size(); i++) {
				System.out.println(i+". "+ possible.get(i));
			}
			int deletechoice1 = inputintegercheck(input, "There are multiple stops with this name. Choose which you would like to remove.\nEnter '-1' to not remove any.");
			if(deletechoice1 != -1) {
				stops.remove(possible.get(deletechoice1));
				System.out.println(possible.get(deletechoice1)+" has been removed.");
			}else {
				System.out.println("Not removing... Returning to main menu.");
			}
			
		}
		
		
		return stops;
	}
	
	public static ArrayList<Stop> search(Scanner input, ArrayList<Stop> stops) {
		//returns stop(s) based on inputed data
		ArrayList<Stop> searchResults = new ArrayList<Stop>();
		
		System.out.print("Train Station Name: ");
		String stationname = input.next();
		
		for (Stop current : stops) {
			if (current.getname().equalsIgnoreCase(stationname)) {
				System.out.println("found");
				searchResults.add(current);
			}
		}
		
		return searchResults;
	}
	
	public static ArrayList<Stop> searchwithconditions(Scanner input, ArrayList<Stop> stops) {
		//returns stop(s) based on other inputed data
		ArrayList<Stop> searchResults = new ArrayList<Stop>();
		
		boolean done = false;
		do {
			System.out.println();
			System.out.println("1. Search by Description");
			System.out.println("2. Search by Wheelchair Accessibility");
			System.out.println("3. Search by Train Lines");
			System.out.println("4. Exit");
			System.out.print("Choice: ");
			String choice = input.next();
			System.out.println();
			switch (choice) {
				case "1":	
					String desc = null;
					desc = descprompt(input, desc);
					for (Stop current : stops) {
						if (current.getdesc().equalsIgnoreCase(desc)) {
							System.out.println("found");
							searchResults.add(current);
						}
					}
					System.out.println("Stations with this description have been gathered. Exit to see results.");
					break;
				case "2":	
					for (Stop current : stops) {
						if (current.getwheelchair()) {
							System.out.println("found");
							searchResults.add(current);
						}
					}
					System.out.println("Stations with wheelchair accessibility have been gathered. Exit to see results.");
					break;
				case "3":
					System.out.println("\nEnter which train line you want to search:\nRed\nGreen\nBlue\nBrown\nPurple\nPink\nOrange");
					String linechoice = input.next().toLowerCase();
					switch (linechoice) {
						case "red":
							for (Stop current : stops) {
								if (current.getred() > -1) {
									System.out.println("found");
									searchResults.add(current);
								}
							}
							System.out.println("Stations on the Red Line have been gathered. Exit to see results.");
							break;
						case "green":
							for (Stop current : stops) {
								if (current.getgreen() > -1) {
									System.out.println("found");
									searchResults.add(current);
								}
							}
							System.out.println("Stations on the Green Line have been gathered. Exit to see results.");
							break;
						case "blue":
							for (Stop current : stops) {
								if (current.getblue() > -1) {
									System.out.println("found");
									searchResults.add(current);
								}
							}
							System.out.println("Stations on the Blue Line have been gathered. Exit to see results.");
							break;
						case "brown":
							for (Stop current : stops) {
								if (current.getbrown() > -1) {
									System.out.println("found");
									searchResults.add(current);
								}
							}
							System.out.println("Stations on the Brown Line have been gathered. Exit to see results.");
							break;
						case "purple":
							for (Stop current : stops) {
								if (current.getpurple() > -1) {
									System.out.println("found");
									searchResults.add(current);
								}
							}
							System.out.println("Stations on the Purple Line have been gathered. Exit to see results.");
							break;
						case "pink":
							for (Stop current : stops) {
								if (current.getpink() > -1) {
									System.out.println("found");
									searchResults.add(current);
								}
							}
							System.out.println("Stations on the Pink Line have been gathered. Exit to see results.");
							break;
						case "orange":
							for (Stop current : stops) {
								if (current.getorange() > -1) {
									System.out.println("found");
									searchResults.add(current);
								}
							}
							System.out.println("Stations on the Orange Line have been gathered. Exit to see results.");
							break;
						default:
							System.out.println("I'm sorry, but I didn't understand that. ");
					}
					
					break;
				case "4":	
					done = true;
					break;
				
				default:
					System.out.println("I'm sorry, but I didn't understand that. ");
			}
		}while(!done);
		
		
		return searchResults;
	}

	public static void saveData(ArrayList<Stop> stops, ArrayList<route> routes) {
		//saves route and stop data to their respective files
		try {
			System.out.println("Saving...");
			FileWriter out = new FileWriter("src/project/CTAStops.csv");
			FileWriter outroute = new FileWriter("src/project/CTAStops.csv");

			out.write("Name,Latitude,Longitude,Description,Wheelchair,Red:33,Green:28,Blue:33,Brown:27,Purple:27,Pink:22,Orange:16\n");
			out.flush();
			for (int i=0; i<stops.size(); i++) {
				out.write(stops.get(i).toString() + "\n");
				out.flush();
			}
			for (int i=0; i<routes.size(); i++) {
				if(routes.get(i)==null) {
					routes.remove(routes.get(i));
				}else {
					outroute.write(routes.get(i).toString() + "\n");
				}
				outroute.flush();
			}
			out.close();
			outroute.close();
			System.out.println("Saved "+routes.size()+" routes.");
			System.out.println("Saved "+stops.size()+" stops.");
		} catch (Exception t) {
			System.out.println("Could not save data.");
		}
	}

	public static Double distance(Double latitude1, Double latitude2, Double longitude1, Double longitude2) {
		//returns the distance between two points using pythagorean theorem.
		Double distance = 0.0;
		Double latitude = latitude1-latitude2;
		Double longitude = longitude1-longitude2;
		distance = Math.sqrt((latitude*latitude)+(longitude*longitude));
		
		return distance;
	}
	
	public static ArrayList<Stop> closeststop(Scanner input, ArrayList<Stop> stops) {
		//Uses distance to return the clostest stop to a given point.
		ArrayList<Stop> searchResults = new ArrayList<Stop>();

		Double latitude = inputdoublecheck(input, "You're current latitude: (XX.XXXXX)");
		Double longitude = inputdoublecheck(input, "You're current longitude: (XX.XXXX)");
		int lowestdist = 0;
		for (int i=0;i<stops.size();i++) {
			if(distance(stops.get(lowestdist).getlatitude(),latitude,stops.get(lowestdist).getlongitude(),longitude)>distance(stops.get(i).getlatitude(),latitude,stops.get(i).getlongitude(),longitude)) {
				lowestdist = i;
			}
		}
		searchResults.add(stops.get(lowestdist));
		
		return searchResults;
	} 
	
	public static ArrayList<route> addroute(ArrayList<route> routes, route r){
	//adds route object to route arraylist
		routes.add(r);
		System.out.println(r+" is the route to take.");
		return routes;
	}
	
	public static Stop createroutestart(Scanner input, Boolean continuesearch, ArrayList<Stop> stops) {
		//returns the requested stop for the starting point
		Stop s = null;
		System.out.print("Searching for Starting Stop Name:");
		
		ArrayList<Stop> possiblestart = search(input, stops);

		if (possiblestart.size() == 0) {
			System.out.println("Stop not found.");
			continuesearch = false;
		} else if (possiblestart.size() == 1) {
			System.out.println("Is " + possiblestart.get(0)+" the starting stop?");
			String startingchoice = inputyesnocheck(input, "Input a yes or no");
			if(startingchoice.equalsIgnoreCase("yes")) {
				s = possiblestart.get(0);
				System.out.print(possiblestart.get(0)+" has been selected as the starting stop.");
			}else {
				System.out.println("No Starting Stop Used... Returning to main menu.");
				continuesearch = false;
			}
		}else {
			int i=0;
			for(i=0; i<possiblestart.size(); i++) {
				System.out.println(i+". "+ possiblestart.get(i));
			}
			int startchoice1 = inputintegercheck(input, "There are multiple stops with this name. Choose which you would like to choose.\nEnter '-1' to not remove any.");
			if(startchoice1 != -1) {
				s = possiblestart.get(startchoice1);
				System.out.println(possiblestart.get(startchoice1)+" has been selected as the starting stop.");
			}else {
				System.out.println("Not Starting Stop Used... Returning to main menu.");
				continuesearch = false;
			}
		}
		return s;
	}
	
	public static Stop createrouteend(Scanner input, boolean continuesearch, ArrayList<Stop> stops) {
		//returns the requested stop for the ending point
		Stop s = null;
		if(continuesearch) {
			System.out.print("Searching for Ending Stop Name: ");
			ArrayList<Stop> possiblestop = search(input, stops);

			if (possiblestop.size() == 0) {
				System.out.println("Ending not found.");
				continuesearch = false;
			} else if (possiblestop.size() == 1) {
				System.out.println("Is " + possiblestop.get(0)+" the Ending stop?");
				String endingchoice = inputyesnocheck(input, "Input a yes or no");
				if(endingchoice.equalsIgnoreCase("yes")) {
					s = possiblestop.get(0);
					System.out.print(possiblestop.get(0)+" has been selected as the ending stop.");
				}else {
					System.out.println("No ending stop Used... Returning to main menu.");
					continuesearch = false;
				}
			}else {
				int i=0;
				for(i=0; i<possiblestop.size(); i++) {
					System.out.println(i+". "+ possiblestop.get(i));
				}
				int stopchoice1 = inputintegercheck(input, "There are multiple stops with this name. Choose which you would like to choose.\nEnter '-1' to not remove any.");
				if(stopchoice1 != -1) {
					s = possiblestop.get(stopchoice1);
					System.out.println(possiblestop.get(stopchoice1)+" has been selected as the ending stop.");
				}else {
					System.out.println("Not ending Stop Used... Returning to main menu.");
					continuesearch = false;
				}
			}
		}
		return s;
	}
	
	public static route createroutetransfer(Scanner input, ArrayList<Stop> stops, Stop start, Stop end, String train) {
		//finds the route between two stops when they aren't directly connected
		route r = null;
		Stop transferstop = null;
		String transfertrain = null;
		String startstring = start.toString();
		String[] startarray = startstring.split(",");
		String endstring = end.toString();
		String[] endarray = endstring.split(",");
		
		System.out.println("Searching for transfer stop.");
		for(Stop current : stops) {
			String currentstring = current.toString();
			String[] currentarray = currentstring.split(",");
			for(int i=5;i<startarray.length;i++) {
				if(Integer.parseInt(startarray[i])>-1&&Integer.parseInt(currentarray[i])>-1) {
					for(int q=5;q<startarray.length;q++) {
						if(Integer.parseInt(endarray[q])>-1&&Integer.parseInt(currentarray[q])>-1) {
							transferstop = current;
							
							System.out.println("Transfer Stop Found.");
							System.out.println("I = "+i+". Q = "+q+".");
							if(q==5) {
								transfertrain = "redline";
							}else if(q==6) {
								transfertrain = "greenline";
							}else if(q==7) {
								transfertrain = "bluegreenline";
							}else if(q==8) {
								transfertrain = "brownline";
							}else if(q==9) {
								transfertrain = "purpleline";
							}else if(q==10) {
								transfertrain = "pinkline";
							}else if(q==11) {
								transfertrain = "orangeline";
							}
							if(i==5) {
								train = "redline";
							}else if(i==6) {
								train = "greenline";
							}else if(i==7) {
								train = "bluegreenline";
							}else if(i==8) {
								train = "brownline";
							}else if(i==9) {
								train = "purpleline";
							}else if(i==10) {
								train = "pinkline";
							}else if(i==11) {
								train = "orangeline";
							}
							i = startarray.length;
							q = startarray.length;
						}
					}
					
					
				}
			}
		}
		if(transferstop==null || transfertrain ==null) {
			System.out.println("ERROR: there is no direct route from the starting stop to the ending stop.");
		}else {
			r = new route(start.getname(), train, end.getname(), transferstop.getname(), transfertrain);
		}
		
		
		
		
		return r;
	}
	
	public static route createroute(Scanner input, ArrayList<route> routes, ArrayList<Stop> stops){
		//creates a route between two stops
		route r = null;
		Stop start = null;
		Stop end = null;
		String train = null;
		Boolean continuesearch = true;
		start = createroutestart(input, continuesearch, stops);
		end = createrouteend(input, continuesearch, stops);
		String startstring = start.toString();
		String[] startarray = startstring.split(",");
		String endstring = end.toString();
		String[] endarray = endstring.split(",");
		for(int i=5;i<startarray.length;i++) {
			if(Integer.parseInt(startarray[i])>-1&&Integer.parseInt(endarray[i])>-1) {
				
				if(i==5) {
					train = "redline";
				}else if(i==6) {
					train = "greenline";
				}else if(i==7) {
					train = "bluegreenline";
				}else if(i==8) {
					train = "brownline";
				}else if(i==9) {
					train = "purpleline";
				}else if(i==10) {
					train = "pinkline";
				}else if(i==11) {
					train = "orangeline";
				}
				System.out.println("No Transfer Needed.");
				i = startarray.length;
			}
		}
		if(train==null) {
			System.out.println("Transfer Needed");
			r = createroutetransfer(input, stops, start, end, train);
		}else {
			r = new route(start.getname(), train, end.getname(), "Not Needed", "Not Needed");
		}
		
		
		return r;
	}
	
	public static ArrayList<route> editroute(Scanner input, ArrayList<route> routes, ArrayList<Stop> stops){
		//allows user to edit a already present route
		ArrayList<route> possible = searchroute(input, routes);

		if (possible.size() == 0) {
			System.out.println("Route not found.");
		} else if (possible.size() == 1) {
			System.out.println("Would you like to Edit Route: " + possible.get(0));
			String editchoice = inputyesnocheck(input, "Input a yes or no");
			if(editchoice.equalsIgnoreCase("yes")) {
				
				System.out.println("Original Starting Stop: "+possible.get(0).getstartstop()+"\nOriginal Ending Stop: "+possible.get(0).getendstop());
				routes.remove(possible.get(0));
				System.out.println("Input new starting stop and ending stop.");
				routes = addroute(routes, createroute(input, routes, stops));
				
			}else {
				System.out.println("Not editing... Returning to main menu.");
			}
		}else {
			int i=0;
			for(i=0; i<possible.size(); i++) {
				System.out.println(i+1+". "+ possible.get(i));
			}
			int editchoice1 = inputintegercheck(input, "There are multiple routes with these starts and stops. Choose which you would like to edit.\nEnter '-1' to not remove any.");
			if(editchoice1 != -1) {
				System.out.println("Original Starting Stop: "+possible.get(editchoice1).getstartstop()+"\nOriginal Ending Stop: "+possible.get(editchoice1).getendstop());
				routes.remove(possible.get(editchoice1-1));
				System.out.println("Input new starting stop and ending stop.");
				routes = addroute(routes, createroute(input, routes, stops));
			}else {
				System.out.println("Not editing... Returning to main menu.");
			}
			
		}
		return routes;
	}
	
	public static ArrayList<route> deleteroute(Scanner input, ArrayList<route> routes){
		//allows the user to delete an already present route from the routes array list
		ArrayList<route> possible = searchroute(input, routes);

		if (possible.size() == 0) {
			System.out.println("Route not found.");
		} else if (possible.size() == 1) {
			System.out.println("Would you like to remove Route: " + possible.get(0));
			String deletechoice = inputyesnocheck(input, "Input a yes or no");
			if(deletechoice.equalsIgnoreCase("yes")) {
				routes.remove(possible.get(0));
				System.out.println(possible.get(0)+" has been removed.");
			}else {
				System.out.println("Not removing... Returning to main menu.");
			}
		}else {
			int i=0;
			for(i=0; i<possible.size(); i++) {
				System.out.println(i+1+". "+ possible.get(i));
			}
			int deletechoice1 = inputintegercheck(input, "There are multiple routes with these starts and stops. Choose which you would like to remove.\nEnter '-1' to not remove any.");
			if(deletechoice1 != -1) {
				routes.remove(possible.get(deletechoice1-1));
				System.out.println(possible.get(deletechoice1)+" has been removed.");
			}else {
				System.out.println("Not removing... Returning to main menu.");
			}
			
		}
		return routes;
	}
	
	public static ArrayList<route> searchroute(Scanner input, ArrayList<route> routes){
		//returns routes based on the user's input
		ArrayList<route> searchResults = new ArrayList<route>();
		
		System.out.print("Starting Stop: ");
		String startstop = input.next();
		System.out.print("Ending Stop: ");
		String endstop = input.next();
		
		boolean found = false;
		for (route current : routes) {
			if (current.getstartstop().equalsIgnoreCase(startstop)&&current.getendstop().equalsIgnoreCase(endstop)) {
				System.out.println("found");
				searchResults.add(current);
				found = true;
			}
		}
		if(!found) {
			searchResults = null;
		}
		return searchResults;
	}
	
	public static int inputintegercheck(Scanner input, String command) {
		//makes sure the user inputs an integer
		int output = 0;
		while(1==1) {
			try {
				System.out.println(command);
	            output = Integer.parseInt(input.next());
	            System.out.println("User Input: "+output);
	            break;
	        } catch (Exception e) {
	            System.out.println("Invalid Entry, Try Again.");
	        }
		}
		System.out.println("Valid Input. \n");
		return output;
	}
	
	public static Double inputdoublecheck(Scanner input, String command) {
		//makes sure the user inputs a double
		Double output = 0.0;
		while(1==1) {
			try {
				System.out.println(command);
	            output = Double.parseDouble(input.next());
	            System.out.println("User Input: "+output);
	            break;
	        } catch (Exception e) {
	            System.out.println("Invalid Entry, Try Again.");
	        }
		}
		System.out.println("Valid Input. \n");
		return output;
	}
	
	public static String inputyesnocheck(Scanner input, String command) {
		//makes sure the user inputs a yes or no
		String output = null;
		while(1==1) {
			System.out.println(command);
            output = input.next();
            System.out.println("User Input: "+output);
            if(output.equalsIgnoreCase("yes") || output.equalsIgnoreCase("no")) {
            	break;
            }else {
            	System.out.println("Invalid Entry, Try Again.");
            }
		}
		System.out.println("Valid Input. \n");
		return output;
	}
	
	public static Boolean inputbooleancheck(Scanner input, String command) {
		//makes sure the user inputs true or false
		Boolean output = null;
		while(1==1) {
			try {
				System.out.println(command);
	            output = Boolean.parseBoolean(input.next());
	            System.out.println("User Input: "+output);
	            break;
	        } catch (Exception e) {
	            System.out.println("Invalid Entry, Try Again.");
	        }
		}
		System.out.println("Valid Input. \n");
		return output;
	}
	
	public static void main(String[] args) {
		// Read in data (want)
		ArrayList<Stop> stops = readData();
		ArrayList<route> routes = readDataroute();
		Scanner input = new Scanner(System.in);
		boolean done = false;
		
		do {
		// Menu (need)
			System.out.println();
			System.out.println("1. Add Stop");
			System.out.println("2. Remove Stop");
			System.out.println("3. Search Stop By Name");
			System.out.println("4. Search Stop By Other Conditions");
			System.out.println("5. Edit Stop");
			System.out.println("6. Take All Stops");
			System.out.println("7. Closest Stop");
			System.out.println("8. Create Route");
			System.out.println("9. Delete Route");
			System.out.println("10. Modify Route");
			System.out.println("11. Search Routes");
			System.out.println("12. Take All Routes");
			System.out.println("13. Exit");
			System.out.print("Choice: ");
			String choice = input.next();
			System.out.println();
			switch (choice) {
				case "1":	
					stops = addstop(stops, createstation(input, stops, routes));
					break;
				case "2":	
					stops = removestop(input, stops);
					break;
				case "3":
					ArrayList<Stop> searchresults = search(input, stops);
					if(searchresults==null) {
						System.out.println("Nothing found");
					}else {
						for(int q=0; q<searchresults.size(); q++) {
							System.out.println(searchresults.get(q));
						}
					}
					
					break;
				case "4":
					ArrayList<Stop> searchresults1 = searchwithconditions(input, stops);
					for(int q=0; q<searchresults1.size(); q++) {
						System.out.println(searchresults1.get(q));
					}
					break;
				case "5":	
					stops = editstop(input, stops, routes);
					break;
				case "6":
					for(int q=1; q<stops.size(); q++) {
						System.out.println(stops.get(q));
					}
					break;
				case "7":	
					ArrayList<Stop> closeststop = closeststop(input, stops);
					System.out.println(closeststop.get(0)+" is the closest stop to you.");
					break;
				case "8":
					routes = addroute(routes, createroute(input, routes, stops));
					break;
				case "9":
					routes = deleteroute(input, routes);
					break;
				case "10":
					routes = editroute(input, routes, stops);
					break;
				case "11":
					ArrayList<route> searchresultsroute = searchroute(input, routes);
					for(int q=0; q<searchresultsroute.size(); q++) {
						System.out.println(searchresultsroute.get(q));
					}
					break;
				case "12":
					for(int q=0; q<routes.size(); q++) {
						System.out.println(routes.get(q).trip()+"["+routes.get(q)+"]");
					}
					break;
				case "13":	
					done = true;
					break;
				
				default:
					System.out.println("I'm sorry, but I didn't understand that. ");
			}
		} while (!done);
		// Save data (want)
		saveData(stops, routes);
		
		System.out.println("Goodbye!");
	}

}
