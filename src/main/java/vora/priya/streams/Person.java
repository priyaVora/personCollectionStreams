package vora.priya.streams;



public class Person {
	private String firstName;
	private String lastName;
	private int age;
	private int ssn;
	
	public Person() { 
		
	}
	
	public Person(String firstName,String lastName, int age, int ssn) { 
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return firstName + "," + lastName + "," + age + "," + ssn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Person)) {
			return false;
		}
		Person other = (Person)obj;
		return this.getFirstName().equals(other.getFirstName()) &&
				this.getLastName().equals(other.getLastName()) &&
				this.getAge() == other.getAge() && 
				this.getSsn() == (other.getSsn());
	}
}
