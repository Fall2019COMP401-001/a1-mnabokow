package a1;

import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
		// Use helper method to create array of Customer from user input
		Customer[] customers = getCustomersFromUserInput();
		
		// Use helper method to print out shopping information from customers array
		printShoppingInfo(customers);
	}
	
	/* getCustomersFromUserInpu() 
	 * Returns array of Customer from User Input
	 *
	 * Output: array of Customer
	 * 
	 * Preconditions:
	 * numberOfCustomers must be of type int
	 * customer first names must be of type String
	 * customer last names must be of type String
	 * number of items bought by customer must be of type int
	 * quantity of item bought must be of type int
	 * name of item bought must be of type String
	 * price of item bought must be of type double
	 */
	private static Customer[] getCustomersFromUserInput() {
		Scanner scan = new Scanner(System.in);

		// read in count of customers to process
		int numberOfCustomers = scan.nextInt();
		
		// Create an array of Customer class to store customers
		Customer[] customers = new Customer[numberOfCustomers];
		
		// Read customer information into array
		for (int i = 0; i < customers.length; i++) {
			String firstName = scan.next();
			String lastName = scan.next();
			int numItemsBought = scan.nextInt();
			
			// Create array of Item to store items for current customer
			Item[] itemsBought = new Item[numItemsBought];
			
			// Read item information into array
			for (int j = 0; j < itemsBought.length; j++) {
				int quantity = scan.nextInt();
				String name = scan.next();
				double price = scan.nextDouble();
				
				// Create Item to store item information
				itemsBought[j] = new Item(quantity, name, price);
			}
			
			// Create customer with processed information
			customers[i] = new Customer(firstName, lastName, itemsBought);
		}
		
		// All input parsed, so close scanner
		scan.close();
		
		// Return processed information
		return customers;
	}
	
	/* printShoppingInfo() 
	 * Prints shopping information from array of Customer
	 */
	private static void printShoppingInfo(Customer[] customers) {
		String info = "";
		
		// Add customers information to info String
		for (Customer c : customers) {
			info += c.firstName.charAt(0) + ". ";
			info += c.lastName + ": ";
			
			double total = 0;
			
			// Calculate total for current customer
			for (Item i : c.itemsBought) {
				total += i.quantity * i.price;
			}
			
			// Add total to info String in correct format
			info += String.format("%.2f", total) + "\n";
		}
		
		// Print info String
		System.out.println(info);
	}
	
	// Nested helper class
	private static class Customer {
		String firstName;
		String lastName;
		Item[] itemsBought;
		
		public Customer(String firstName, String lastName, Item[] itemsBought) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.itemsBought = itemsBought;
		}
	}
	
	// Nested helper class
	private static class Item {
		int quantity;
		String name;
		double price;
		
		public Item(int quantity, String name, double price) {
			this.quantity = quantity;
			this.name = name;
			this.price = price;
		}
	}
}
