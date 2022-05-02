//Normal Imports
import java.util.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.*;

//File Imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PhoneBook {
	
	private static Scanner x;

	public static void main(String[] args) {

		HashMap<String, Card> phonebook = new HashMap<String, Card>();
		Trie phoneNumbers = new Trie();
		LinkedList<ContactList> contacts = new LinkedList<>();
		String choice = "";
		String name = "";
		String number = "";
		String address = "";
		String addressToSave = "";
		String line =  "";
		String filepath = "contacts.txt";
		String houseNumber = "";
		String street = "";
		String apt = "";
		String aptNum = "";
		String city = "";
		String state = "";
		String zip = "";
		String areaCode = "";
		String location = "";

		Scanner main = new Scanner(System.in);
		System.out.println("Show menu? (y/n): ");
		String run = main.nextLine();


		//------------------------------WELCOME HEADER----------------------------------//

		do {
			System.out.println("+---------------------------------------+");
			System.out.println("|     ** WELCOME TO XLOOKUP **        	|");
			System.out.println("|     Press 1. to Add Contact     	|");
			System.out.println("|     Press 2. to Display Contacts 	|");
			System.out.println("|     Press 3. to Find Contact    	|");
			System.out.println("|     Press 4. to Update Contact    	|");
			System.out.println("|     Press 5. to Delete Contact       	|");
			System.out.println("|     Press 6. to Display Contact List  |");
			System.out.println("+---------------------------------------+");

			Scanner option = new Scanner(System.in);
			System.out.print("Please enter your choice: ");
			choice = option.nextLine();

		//------------------------------------------------------------------------------//

			//-------------------------QUESTIONS---------------------------------------------//

			switch (choice)
			{
				case "1":
					/**	
						* Ask user for their name to add in phonebook
						* with assciotation to their number
					*/ 

					Scanner nam = new Scanner(System.in);
					System.out.println("What is your name?: " + "\n");
					name = nam.nextLine();

					contacts.add(new ContactList(name));

					/**	
						* Ask user for a phone number to attribute to their name
					*/ 

					Scanner n = new Scanner(System.in);
					System.out.println("\nWhat is your number?: ");
					System.out.println("\t (NO DASHES, SPACES, OR PARENTHESIS!) " + "\n");
					number = n.nextLine();

					phoneNumbers.add(number); //adds to the Trie

					/**	
						* Ask user if they live in apartment or not
						* user answer determines the format of the address
					*/ 

					System.out.println("+--------------------------------+");
					System.out.println("|    	ADDRESS INFORMATION      |");
					System.out.println("+--------------------------------+");

					Scanner a = new Scanner(System.in);
					System.out.print("\nDo you live in Apartment? (y/n): ");
					String addy = a.nextLine();
					if (addy.equals("y")) {

						//Gets House Number
						Scanner l1 = new Scanner(System.in);
						System.out.println("\nHouse Number: ");
						System.out.println("-------------\n");
						houseNumber = l1.nextLine();

						//Gets Street Name
						Scanner l2 = new Scanner(System.in);
						System.out.println("\nStreet: ");
						System.out.println("-------\n");
						street = l2.nextLine();

						//Gets Apartment Number
						Scanner l3 = new Scanner(System.in);
						System.out.println("\nApartment Number: ");
						System.out.println("-----------------\n");
						aptNum = l3.nextLine();

						//Gets City Name
						Scanner l4 = new Scanner(System.in);
						System.out.println("\nCity: ");
						System.out.println("-----\n");
						city = l4.nextLine();

						//Gets State Name
						Scanner l5 = new Scanner(System.in);
						System.out.println("\nState: ");
						System.out.println("------\n");
						state = l5.nextLine();

						//Gets Zip Code
						Scanner l6 = new Scanner(System.in);
						System.out.println("\nZip Code: ");
						System.out.println("---------\n");
						zip = l6.nextLine();

						//Adds all address information
						addressToSave = houseNumber + "," + street + "," + "Apt," + aptNum + "," + city + "," + state + "," + zip;
						address = houseNumber + " " + street + "\n\t" + "Apt " + aptNum + "\n\t" + city + ", " + state + " " + zip;

					}else if (addy.equals("n")) {

						//Gets House Number
						Scanner l1 = new Scanner(System.in);
						System.out.println("\nHouse number: ");
						System.out.println("-------------\n");
						houseNumber = l1.nextLine();

						//Gets Street Name
						Scanner l2 = new Scanner(System.in);
						System.out.println("\nStreet: ");
						System.out.println("-------\n");
						street = l2.nextLine();

						//Gets City Name
						Scanner l4 = new Scanner(System.in);
						System.out.println("\nCity: ");
						System.out.println("-----\n");
						city = l4.nextLine();

						//Gets State Name
						Scanner l5 = new Scanner(System.in);
						System.out.println("\nState: ");
						System.out.println("------\n");
						state = l5.nextLine();

						//Gets Zip Code
						Scanner l6 = new Scanner(System.in);
						System.out.println("\nZip Code: ");
						System.out.println("---------\n");
						zip = l6.nextLine();

						//Adds all address information
						addressToSave = houseNumber + "," + street + "," + city + "," + state + "," + zip;
						address = houseNumber + " " + street + "\n\t" + city + ", " + state + " " + zip;
					} else{
						addressToSave = "No Address Information";
						address = "No Addres Information";
					}

					System.out.println("\n----------------------------------");
					System.out.println("\n----------------------------------\n");

					//----------------------------------------------------------------------------------------//

					/**	
						* Reads in file and puts data into String[]
						* Checks if first three numbers of phone number
						* equals an area code in the csv file and then it grabs
						* the area code and the location.
					*/ 

					String path = "/Users/xavieragostino/Desktop/Data Structures/AC.csv";
					try {
						BufferedReader br = new BufferedReader(new FileReader(path));
						while((line = br.readLine()) != null) {
							String[] values = line.split(",");
							for (int i = 0; i < values.length; i++) {
								if(number.substring(0, 3).equals(values[i])){
									areaCode = values[i];
									location = city + ", " + values[i+2];
								}
							}
						}	
					} 
					catch (FileNotFoundException e) 
					{
						e.printStackTrace();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					} 
					catch (Exception e)
					{
						System.out.println("Something went wrong in the try");
					}

					//add items into Hashmap
					phonebook.put(number, new Card(name, number, address, areaCode, location));
					saveRecord(name, number,addressToSave, filepath);

					//----------------------------------------------------------------------------------------//
					break;

				case "2":
					//-------------------------DISPLAYS DIRECTORY---------------------------------------------//

					/**	
						* Looks through contact file to check if its empty
						* if not then it adds all the info into hashmap, trie, and linkedList
						* then it displays the phonebook with all the contacts information 
						* which is stored in a linkedList
					*/

					String pathDisplay = "/Users/xavieragostino/Desktop/Data Structures/Data Structure's Final Project/contacts.txt";
					try 
					{
						BufferedReader brD = new BufferedReader(new FileReader(pathDisplay));
						while((line = brD.readLine()) != null) {
							String[] display = line.split(",");
							int i = 0;
							if(display[i] != null && display[i].length() > 0) 
							{
								if (display.length > 7){
									name = display[i];
									number = display[i+1];
									houseNumber = display[i+2];
									street = display[i+3];
									apt = display[i+4];
									aptNum = display[i+5];
									city = display[i+6];
									state = display[i+7];
									zip = display[i+8];
									address = houseNumber + " " + street + "\n\t" + apt + " " + aptNum + "\n\t" + city + ", " + state + " " + zip;
								}else if (display.length < 8){
									name = display[i];
									number = display[i+1];
									houseNumber = display[i+2];
									street = display[i+3];
									city = display[i+4];
									state = display[i+5];
									zip = display[i+6];
									address = houseNumber + " " + street + "\n\t" + city + ", " + state + " " + zip;
								}else {
									address = "No Address Information";
								}

								String pathDisplay2 = "/Users/xavieragostino/Desktop/Data Structures/AC.csv";
								try 
								{
									BufferedReader brD1 = new BufferedReader(new FileReader(pathDisplay2));
									while((line = brD1.readLine()) != null) {
										String[] acs = line.split(",");
										for (int j = 0; j < acs.length; j++) {
											if(display[i+1].substring(0, 3).equals(acs[j])){
												areaCode = acs[j];
												location = city + ", " + acs[i+2];
											}
										}
									}	
								} 
								catch (Exception e)
								{
									System.out.println(e);
								}
							}else{
								System.out.println("File Empty");
							}
							phonebook.put(number, new Card(name, number, address, areaCode, location));
							contacts.add(new ContactList(name));
							phoneNumbers.add(number);
						}
					}
					catch (Exception e)
					{
						System.out.println(e);
					}

					displayDirectory(phonebook);
					break;

					//----------------------------------------------------------------------------------------//

				case "3":
					//-------------------------FINDS CONTACT---------------------------------------------//

					/**	
						* Ask user do they want to find a contact
						* @return contacts card
					*/ 

					String tryLookUpAgain = "n";

					boolean found = false;

					do{
						Scanner fc = new Scanner(System.in);
						System.out.println("What's the contact's phone number?:  ");
						String contactsNumber = fc.nextLine();
						String pathFind = "/Users/xavieragostino/Desktop/Data Structures/Data Structure's Final Project/contacts.txt";
						try {
							BufferedReader br1 = new BufferedReader(new FileReader(pathFind));
							while((line = br1.readLine()) != null) {
								String[] items = line.split(",");
								int i = 0;
								if (items.length > 7){
									name = items[i];
									number = items[i+1];
									houseNumber = items[i+2];
									street = items[i+3];
									apt = items[i+4];
									aptNum = items[i+5];
									city = items[i+6];
									state = items[i+7];
									zip = items[i+8];
									address = houseNumber + " " + street + "\n\t" + apt + " " + aptNum + "\n\t" + city + ", " + state + " " + zip;
								}else if (items.length < 8){
									name = items[i];
									number = items[i+1];
									houseNumber = items[i+2];
									street = items[i+3];
									city = items[i+4];
									state = items[i+5];
									zip = items[i+6];
									address = houseNumber + " " + street + "\n\t" + city + ", " + state + " " + zip;
								}else {
									address = "No Address Information";
								}

								try 
								{
									String pathFindContactAC = "/Users/xavieragostino/Desktop/Data Structures/AC.csv";
									BufferedReader br2 = new BufferedReader(new FileReader(pathFindContactAC));
									while((line = br2.readLine()) != null) 
									{
										String[] valuesF = line.split(",");
										for (int j = 0; j < valuesF.length; j++) 
										{
											if(contactsNumber.substring(0, 3).equals(valuesF[j]))
											{
												areaCode = valuesF[j];
												location = city + ", " + valuesF[j+2];
											}
										}
									}
								}
								catch (Exception e)
								{
									System.out.println("No Prior Location/Area Code Informaton");
									System.out.println(e);
								}
								if (number.equals(contactsNumber)) 
								{
									Card c2 = new Card(name, number, address, areaCode, location);
									System.out.println(c2.cardDisplay());
									found = true;
								}
							}//ends while
							if (found == false) {
									System.out.println("Person Doesn't Already Exist in Directory");
									System.out.println("The contact you entered does not live in directory.");
									Scanner runBack = new Scanner(System.in);
									System.out.println("Do you want to try again? (y/n):  ");
									tryLookUpAgain = runBack.nextLine();
								}//ends else	
						} 
						catch (Exception e)
						{
							System.out.println(e);
						}
					} while (tryLookUpAgain.equals("y"));
					//----------------------------------------------------------------------------------------//
					break;

				case "4":

					//-----------------------------------------UPDATE CONTACT---------------------------------------------//

					/**	
						* Ask user for their old phone number
						* Finds number by checking if number exist
						* inside of contacts(Hashmap, Trie, and conacts file)
						* if it exist it then deletes it from the hashmap, trie, linkedlist
						* and also it deletes the record of the person in the file
						* Once the deletion of old number information happens then 
						* it prompts user to eneter their new number and adds the record to 
						* phonebook directory while also still keeping all address information the same.
					*/

					Scanner up = new Scanner(System.in);
					System.out.println("What was your original phone number?: ");
					String updateResponse = up.nextLine();
					String updatePathFile = "/Users/xavieragostino/Desktop/Data Structures/Data Structure's Final Project/contacts.txt";
					try {
						BufferedReader br5 = new BufferedReader(new FileReader(updatePathFile));
						while((line = br5.readLine()) != null) {
							String[] uitems = line.split(",");
							int i = 0;
							if (uitems.length > 7){
								name = uitems[i];
								number = uitems[i+1];
								houseNumber = uitems[i+2];
								street = uitems[i+3];
								apt = uitems[i+4];
								aptNum = uitems[i+5];
								city = uitems[i+6];
								state = uitems[i+7];
								zip = uitems[i+8];
								addressToSave = houseNumber + "," + street + "," + "Apt," + aptNum + "," + city + "," + state + "," + zip;
								address = houseNumber + " " + street + "\n\t" + apt + " " + aptNum + "\n\t" + city + ", " + state + " " + zip;
							}else if (uitems.length < 8){
								name = uitems[i];
								number = uitems[i+1];
								houseNumber = uitems[i+2];
								street = uitems[i+3];
								city = uitems[i+4];
								state = uitems[i+5];
								zip = uitems[i+6];
								address = houseNumber + " " + street + "\n\t" + city + ", " + state + " " + zip;
								addressToSave = houseNumber + "," + street + "," + city + "," + state + "," + zip;
							}else {
								address = "No Address Information";
								addressToSave = "No Address Information";
							}
							if (number.equals(updateResponse))
							{
								contacts.remove(name);
								phoneNumbers.delete(number);
								phonebook.remove(number);
								deleteRecord(filepath, updateResponse, 2, ",");
								Scanner nn = new Scanner(System.in);
								System.out.println("Enter your new number: ");
								String newNumber = nn.nextLine();
								phoneNumbers.add(newNumber);
								String path6 = "/Users/xavieragostino/Desktop/Data Structures/AC.csv";
								try {
									BufferedReader br3 = new BufferedReader(new FileReader(path6));
									while((line = br3.readLine()) != null) {
										String[] valuesU = line.split(",");
										for (int j = 0; j < valuesU.length; j++) {
											if(newNumber.substring(0, 3).equals(valuesU[j])){
												areaCode = valuesU[j];
												location = city + ", " + valuesU[j+2];
											}
										}
									}	
								} 
								catch (Exception e)
								{
									System.out.println(e);
								}
								phonebook.put(newNumber, new Card(name, newNumber, address, areaCode, location));
								updateRecord(name, newNumber, addressToSave, filepath);
								contacts.add(new ContactList(name));
							}
						}
					} 
					catch (Exception e)
					{
						//System.out.println("Error trying to Update Record");
						System.out.println(e);
					}

					//----------------------------------------------------------------------------------------------------//
					break;
				case "5":

					//-----------------------------------------REMOVES CONTACT---------------------------------------------//	

					/**	
						* Ask User Who they want to remove
						* then find the person they want to remove.
						* remove from hashmap
						* remove from Linked List
						* 
					*/

					Scanner askWho = new Scanner(System.in);
					System.out.println("What is the phone number of the contact?: ");
					String removePerson = askWho.nextLine();
					String pathDelete = "/Users/xavieragostino/Desktop/Data Structures/Data Structure's Final Project/contacts.txt";
					try {
						BufferedReader br4 = new BufferedReader(new FileReader(pathDelete));
						while((line = br4.readLine()) != null) {
							String[] records = line.split(",");
							int i = 0;
								if (records[i + 1].equals(removePerson))
								{
									contacts.remove(records[i]);
									phoneNumbers.delete(records[i+1]);
									deleteRecord(filepath, removePerson, 2, ",");

								}
						}	
					} catch (Exception e){
						//System.out.println("Error trying to Delete Record");
						System.out.println(e);
					}
					phonebook.remove(removePerson);

				//------------------------------------------------------------------------------------------------------//
					break;

				case "6":

					//-------------------------DISPLAYS LIST OF CONTACTS---------------------------------------------//

					/**	
						* Looks if contact file is empty
						* If not then it adds all information into Hashmap, Trie, and LinkedList
						* Then it displays list view of all contact names in directory.
					*/

					String pathCL = "/Users/xavieragostino/Desktop/Data Structures/Data Structure's Final Project/contacts.txt";
					try 
					{
						BufferedReader brCL = new BufferedReader(new FileReader(pathCL));
						while((line = brCL.readLine()) != null) {
							String[] cL = line.split(",");
							int i = 0;
							if(cL[i] != null && cL[i].length() > 0) 
							{
								if (cL.length > 7){
									name = cL[i];
									number = cL[i+1];
									houseNumber = cL[i+2];
									street = cL[i+3];
									apt = cL[i+4];
									aptNum = cL[i+5];
									city = cL[i+6];
									state = cL[i+7];
									zip = cL[i+8];
									address = houseNumber + " " + street + "\n\t" + apt + " " + aptNum + "\n\t" + city + ", " + state + " " + zip;
								}else if (cL.length < 8){
									name = cL[i];
									number = cL[i+1];
									houseNumber = cL[i+2];
									street = cL[i+3];
									city = cL[i+4];
									state = cL[i+5];
									zip = cL[i+6];
									address = houseNumber + " " + street + "\n\t" + city + ", " + state + " " + zip;
								}else {
									address = "No Address Information";
								}

								String pathCL2 = "/Users/xavieragostino/Desktop/Data Structures/AC.csv";
								try 
								{
									BufferedReader brCL2 = new BufferedReader(new FileReader(pathCL2));
									while((line = brCL2.readLine()) != null) {
										String[] areaCL = line.split(",");
										for (int j = 0; j < areaCL.length; j++) {
											if(cL[i+1].substring(0, 3).equals(areaCL[j])){
												areaCode = areaCL[j];
												location = city + ", " + areaCL[i+2];
											}
										}
									}	
								} 
								catch (Exception e)
								{
									System.out.println(e);
								}
							}else{
								System.out.println("File Empty");
							}
							phonebook.put(number, new Card(name, number, address, areaCode, location));
							contacts.add(new ContactList(name));
							phoneNumbers.add(number);
						}
					}
					catch (Exception e)
					{
						System.out.println(e);
					}

					displayListOfContacts(contacts);
					break;

					//---------------------------------------------------------------------------------------//
			}
		} while (run.equals("y"));

	} //ends main

	//-------------------------SAVES RECORD OF CONTACT---------------------------------------------//

	public static void saveRecord(String name, String number,String address, String filepath) 
	{
		try
		{
			FileWriter fw = new FileWriter(filepath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			pw.println(name + "," + number + "," + address);
			pw.flush(); //makes sure all data is written to file
			pw.close();

			JOptionPane.showMessageDialog(null, "Contact saved in Directory!");
		}
		catch(Exception E)
		{
			JOptionPane.showMessageDialog(null, "Contact NOT SAVED!!");
		}
	}

	//-------------------------UPDATES RECORD OF CONTACT---------------------------------------------//

	public static void updateRecord(String name, String number,String address, String filepath) 
	{
		try
		{
			FileWriter fw = new FileWriter(filepath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			pw.println(name + "," + number + "," + address);
			pw.flush(); //makes sure all data is written to file
			pw.close();

			JOptionPane.showMessageDialog(null, "Contact was updated!");
		}
		catch(Exception E)
		{
			JOptionPane.showMessageDialog(null, "Contact was NOT UPDATED!!");
		}
	}

	//----------------------------------------------------------------------------------------------//

	//-------------------------DELETES RECORD OF CONTACT------------------------------------------------------//

	public static void deleteRecord(String filepath, String removeTerm, int positionOfTerm, String delimiter)
	{
		int position = positionOfTerm - 1;
		String tempFile = "temp.txt";
		File oldFile = new File (filepath);
		File newFile = new File (tempFile);
		String currentLine;
		String data[];
		try
		{
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader (fr);

			while ((currentLine = br.readLine()) != null)
			{
				data = currentLine.split(",");
				if (!(data[position].equalsIgnoreCase(removeTerm)))
				{
					pw.println(currentLine);
				}
			}
			pw.flush();
			pw.close();
			fr.close();
			br.close();
			bw.close();
			fw.close();

			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
			JOptionPane.showMessageDialog(null, "Contact was deleted from Directory!");
		}
		catch (Exception e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Contact was not Deleted!!");

		}
	}

	//---------------------------------------------------------------------------------------------------------//


	//-------------------------PRINTS WHOLE PHONEBOOK DIRECTORY---------------------------------------------//

	public static void displayDirectory(HashMap<String, Card> p) {
			LinkedList<Object> contactCards = new LinkedList<>(p.values());

			System.out.println(contactCards.toString());
	}

	//-----------------------------------------------------------------------------------------------------//

	//-------------------------PRINTS LIST OF CONTACTS IN DIRECTORY---------------------------------------------//

	public static void displayListOfContacts(LinkedList<ContactList> c) {
		System.out.println("\n+--------------------------------+" + "\n" + "|            CONTACTS          	 |" + "\n" + "+--------------------------------+");
		System.out.println(c.toString());
	}

	//----------------------------------------------------------------------------------------------------------//

}//end class
class phonebookD {}
