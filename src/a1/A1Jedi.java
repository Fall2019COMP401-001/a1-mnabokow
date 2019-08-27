package a1;

import java.util.Scanner;

public class A1Jedi {

	public static void main(String[] args) {
		// Use helper method to create array of Item from user input
		Item[] items = getItemsFromUserInput();
		
		// Use helper method to print out shopping information from items array
		printShoppingInfo(items);
	}
	
	/* getItemsFromUserInpu() 
	 * Returns array of Item from User Input
	 *
	 * Output: array of Item
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
	public static Item[] getItemsFromUserInput() {
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
						item.totalQuantityBought += quantity;
						item.totalOfCustomersBought++;
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
		return items;
	}
	
	/* printShoppingInfo() 
	 * Prints shopping information from array of Item
	 */
	public static void printShoppingInfo(Item[] items) {
		String info = "";
		for (int i = 0; i < items.length; i++) {
			if (i != 0) {
				info += "\n";
			}
			if (items[i].totalOfCustomersBought == 0) {
				info += "No customers bought: " + items[i].name;
			} else {
				info += items[i].totalOfCustomersBought + " customers bought " + items[i].totalQuantityBought + " " + items[i].name;
			}
		}
		
		System.out.println(info);
	}
	
		// Nested helper class
		public static class Customer {
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
		public static class Item {
			int totalQuantityBought;
			int totalOfCustomersBought;
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
