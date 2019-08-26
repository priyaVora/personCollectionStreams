package vora.priya.streams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

public class StreamTester {
	private final int MAX_TOTAL_PEOPLE = 2000;
	long biggiestSSN = 0;
	static int averageAge = 0;
	List<Person> specificPersonList = new ArrayList<Person>();
	public static void main(String[] args) throws IOException {
		StreamTester tester = new StreamTester();
		PersonFactory pf = new PersonFactory();
		List<Person> persons = pf.generateRandomPersonList(100);
	
		System.out.println("--------------------------------------------------------------------------------------");
		tester.printOriginalList(persons);
		System.out.println("--------------------------------------------------------------------------------------");
		List<Person> people = tester.specificNamesList(persons);
		List<Person> evenPeople = tester.getEvenSSN(people);
		System.out.println("--------------------------------------------------------------------------------------");
		tester.printEvenSSNList(evenPeople);
		System.out.println("--------------------------------------------------------------------------------------");
		List<String> firstNameList = tester.newFirstNamesList(persons);
		tester.printFirstNamesList(firstNameList);
		System.out.println("--------------------------------------------------------------------------------------");
		long biggiestNumericSSN = tester.findLargestSSN(persons);
		
		tester.printLargeSSN(biggiestNumericSSN);
		
		int theAverage = tester.averageAgeOfPeople(persons);
		averageAge = theAverage;
		tester.printAverageAge(theAverage);
		tester.makeCSVFile(persons);
		//Calculate the average age of all of people in the original list (created in step 1) as a single int value and store it in an int variable.

	}
	
	public void printOriginalList(List<Person> persons) { 		
		persons.stream().forEach(thePerson -> System.out.println("Created" + thePerson.toString()));
	}

	public List<Person> specificNamesList(List<Person> persons) {
		List<Person> people = persons.stream() // transactions to payment cards
				.filter(t -> (t.getFirstName().substring(0).contains("R") ||t.getFirstName().substring(0).contains("A") || t.getFirstName().substring(0).contains("Q")) && (t.getLastName().substring(0).contains("S") || t.getLastName().substring(0).contains("C")))
				.collect(Collectors.toList());
		return people;
	}
	
	public List<Person> getEvenSSN(List<Person> people) { 
		List<Person> evenPeople = people.stream() // transactions to payment cards
				.filter(t -> t.getSsn() % 2 == 0)
				.collect(Collectors.toList());
		return evenPeople;
	}
	
	public void printEvenSSNList(List<Person> evenPeople) { 		
		evenPeople.stream().forEach(evenSN -> System.out.println("Person With Even SSN: " + evenSN.toString()));
	}
	
	public List<String> newFirstNamesList(List<Person> persons) { 
		List<String> firstNameList = persons.stream() // transactions to payment cards
				.filter(t -> t.getFirstName().length() > 3 && t.getFirstName().length() < 8)
				.map(t-> t.getFirstName())
				.collect(Collectors.toList());
		return firstNameList;
	}

	public void printFirstNamesList(List<String> firstNameList) { 
		firstNameList.stream().forEach(fn -> System.out.println("First Name Entries between 4 to 7 Characteres in Length: " + fn));
	}
	
	public long findLargestSSN(List<Person> persons) { 
		//Find the largest (biggest numeric value) SSN in the original list and store it in a long variable.
		long biggiestNumericSSN = persons.stream().max(Comparator.comparingInt(t -> t.getSsn())).get().getSsn();
		return biggiestNumericSSN;
	}
	
	public void printLargeSSN(long biggiestNumericSSN) { 
		System.out.println("Large SSN : " + biggiestNumericSSN);
	}
	
	public int averageAgeOfPeople(List<Person> persons) { 
		double averageAge = persons.stream().mapToInt(t-> t.getAge()).average().getAsDouble();
		int theAverage = (int) Math.round(averageAge);
		return theAverage;
	}

	
	public void printAverageAge(int theAverage) { 
		System.out.println("Average Age: " + theAverage);
	}
	public void makeCSVFile(List<Person> persons) throws IOException {
		//Files.write(Paths.get("res/nashorn1-modified.js"), lines);
		
		
		//Make a CSV file containing all of the Person objects from your original list who last names have more than 4 characters in 
		//them and who's age is greater than the average age of people in the list. (CSV file = comma separated values, and new line delimited records).  
		//The output file should end with a '.csv' extension and open correctly in MS Excel.
		
		List<String> peoplesDataForCSV = persons.stream().filter(t -> t.getLastName().length() > 4 && t.getAge() > averageAge)
				.map(t -> t.toString())
				.collect(Collectors.toList());
		
		Files.write(Paths.get("TheCSVFILE.csv"), peoplesDataForCSV);
		
	}

}
