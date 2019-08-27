package a1;

import java.util.Scanner;

public class A1Adept {

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

		// Read in count of items in store to process
		int numberOfItemsInStore = scan.nextInt();
		
		// Create array of Item class to store items
		Item[] items = new Item[numberOfItemsInStore];
		
		// Read item information into array
		for (int i = 0; i < items.length; i++) {
			String name = scan.next();
			double price = scan.nextDouble();
			
			items[i] = new Item(name, price);
		}
		
		// Read in count of customers to process
		int numberOfCustomers = scan.nextInt();
		
		// Create array of Customer class to store customers
		Customer[] customers = new Customer[numberOfCustomers];
		
		// Read customers information into array
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
				
				// Find item from items array and add it to current customer's bought items in correct quantity
				for (Item item : items) {					
					if (item.name.equals(name)) {
						itemsBought[j] = new Item(item);
						itemsBought[j].quantity = quantity;
						break;
					}
				}
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
		String biggestString = "Biggest: ";
		String smallestString = "Smallest: ";
		
		double max = 0.0;
		double min = 0.0;
		
		double totalMoneySpent = 0.0;
		
		// Add customers information to info String
		for (int i = 0; i < customers.length; i++) {
			double total = 0;
			
			// Calculate total for current customer
			for (Item j : customers[i].itemsBought) {
				total += j.quantity * j.price;
			}
			
			// Set biggest and smallest to first customer's total
			if (i == 0) {
				max = total;
				min = total;
			}
			
			// Add current customer's total to totalMoneySpent
			totalMoneySpent += total;
			
			// Check if total is new Max
			if (total >= max) {
				// Change biggestString accordingly
				biggestString = "Biggest: " + customers[i].firstName + " " + customers[i].lastName + " (" + String.format("%.2f", total) + ")";
				max = total;
			}
			
			// Check if total is new Min
			if (total <= min) {
				// Change smallestString accordingly
				smallestString = "Smallest: " + customers[i].firstName + " " + customers[i].lastName + " (" + String.format("%.2f", total) + ")";
				min = total;
			}
		}
		
		// Calculate average money spent
		double average = totalMoneySpent / customers.length;
		
		// Print info String
		System.out.println(biggestString + "\n" + smallestString + "\n" + "Average: " + String.format("%.2f", average));
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
		
		public Item(String name, double price) {
			this.name = name;
			this.price = price;
		}
		
		// Copy constructor
		public Item(Item item) {
			this.name = item.name;
			this.price = item.price;
		}
	}
}
