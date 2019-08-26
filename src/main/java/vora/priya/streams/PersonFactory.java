package vora.priya.streams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PersonFactory {
	
	Random rnd = new Random();
	private static Random generator = new Random();

	public List<Person> generateRandomPersonList(int numOfPersons) throws FileNotFoundException {
		List<Person> originalPersonsList = new ArrayList<>();
		for (int i = 0; i < numOfPersons; i++) {
			originalPersonsList.add(generateRandomPerson());
		}
		return originalPersonsList;
	}

	public Person generateRandomPerson() throws FileNotFoundException {
		// generate firstName
		String firstName = this.getNameFromList("firstnames.txt");
		// generate lastName
		String lastName = this.getNameFromList("lastnames.txt");
		// generate Age
		int age = rnd.nextInt(151);
		// generate SSN
		int ssn = makeSSN();

		return new Person(firstName, lastName, age, ssn);
	}

	public String[] nameMaker(String txtFileName) throws FileNotFoundException {
		File path = new File(txtFileName);

		Scanner file = new Scanner(path);
		String[] nameType = new String[100];
		for (int i = 0; i < nameType.length; i++) {
			nameType[i] = file.nextLine();
			nameType[i].trim().replaceAll(" ", "");
		}
		
		
		for (int i = 0; i < nameType.length; i++) {
			if(txtFileName.equalsIgnoreCase("firstnames.txt")) { 
				int firstOne = rnd.nextInt(4);
				if(firstOne == 0) { 
					nameType[i] = "A" + nameType[i];
				} else if (firstOne == 1) { 
					nameType[i] = "R" + nameType[i];
				} else if (firstOne == 2) { 
					nameType[i] = "Q" + nameType[i];
				} 
				
			} else { 
				int firstOne = rnd.nextInt(3);
				if(firstOne == 0) { 
					nameType[i] = "S" + nameType[i];
				} else if (firstOne == 1) { 
					nameType[i] = "C" + nameType[i];
				} 
			}
		}

		return nameType;
	}

	public String getNameFromList(String txtFileName) throws FileNotFoundException {
		String name = null;
		String[] listOfNames = nameMaker(txtFileName);

		int num = rnd.nextInt(41);

		for (int i = 0; i < listOfNames.length; i++) {
			name = listOfNames[num];
		}
		return name;
	}

	public int makeSSN() {
		int theSSN = 0;

		List<Integer> cannotFirstGroupList = new ArrayList<>();
		cannotFirstGroupList.add(000);
		cannotFirstGroupList.add(666);

		for (int i = 900; i < 1000; i++) {
			cannotFirstGroupList.add(i);
		}

		int firstSection = rnd.nextInt((899 - 111) + 1) + 111;
		int middleSection = rnd.nextInt((99 - 01) + 1) + 1;
		int lastSection = rnd.nextInt((9999 - 0001) + 1) + 0001;

		String testSocialSecurityNumber = "" + firstSection + middleSection + lastSection;
		String theStringOfSSN = testSocialSecurityNumber;
		boolean isSSNValid = isValidSSN(testSocialSecurityNumber);
		if (isSSNValid == false) {
			while (isSSNValid == true) {
				firstSection = rnd.nextInt((899 - 111) + 1) + 111;
				middleSection = rnd.nextInt((99 - 01) + 1) + 1;
				lastSection = rnd.nextInt((9999 - 0001) + 1) + 0001;
				testSocialSecurityNumber = "" + firstSection + middleSection + lastSection;
				isSSNValid = isValidSSN(testSocialSecurityNumber);
			}
		}
		theSSN = Integer.parseInt(testSocialSecurityNumber);
		return theSSN;
	}

	public boolean isValidSSN(String ssn) {
		String regex = "^((?!000|666)[0-8][0-9]{2}-)((?!00)[0-9]{2}-)((?!0000)[0-9]{4})$";
		return Pattern.matches(regex, ssn);
	}

}
