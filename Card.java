import java.util.*;

public class Card {

	private String name;
	private String number;
	private String address;
	private String areaCode;
	private String location;

	public Card(String name, String number, String address) {
		this.name = name;
		this.number = number;
		this.address = address;
	}

	public Card(String name, String number, String address, String areaCode, String location) {
		this(name, number, address);
		this.areaCode = areaCode;
		this.location = location;

	}

	public String cardDisplay() {
		String toReturn = "\n----------------------------------";
		toReturn += "\n\t" + name + "'s" + " Card";
		toReturn += "\n----------------------------------\n";
		toReturn += "\n" + " Area Code: " + areaCode;
		toReturn += "\n" + " Location: " + location;
		toReturn += "\n" + " Contact Name: " + name;
		toReturn += "\n" + " Contact Number: " + number;
		toReturn += "\n" + " Address: ";
		toReturn += "\n" + "\t" + address;
		toReturn += "\n----------------------------------";
		toReturn += "\n\n----------------------------------";
		return toReturn;

	}

	public String toString() {
		String toReturn = "\n----------------------------------";
		toReturn += "\n\t" + name + "'s" + " Card";
		toReturn += "\n----------------------------------\n";
		toReturn += "\n" + " Area Code: " + areaCode;
		toReturn += "\n" + " Location: " + location;
		toReturn += "\n" + " Contact Name: " + name;
		toReturn += "\n" + " Contact Number: " + number;
		toReturn += "\n" + " Address: ";
		toReturn += "\n" + "\t" + address;
		toReturn += "\n----------------------------------";
		toReturn += "\n\n----------------------------------";
		return toReturn;

	}
}