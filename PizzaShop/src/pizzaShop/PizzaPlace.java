package pizzaShop;

import java.util.Scanner;

public class PizzaPlace {
	Scanner s = new Scanner(System.in);

	private Order[] orders = new Order[1000];
	private double gains;

	public static int countOrders = 0;

	public PizzaPlace() {

		System.out.println("Welcome to K&G Pizza Place \nPlease choose from the menu: ");
		System.out.println(
				"1. add new order \n2. cancel your order \n3. add a pizza \n4. show my pizzas \n5. delete a pizza \n6. check order status \n7. show all orders \n8. charge order \n9. all today gains");

		boolean invalid = true;
		char choose;
		do {
			System.out.println("*Write 'm' to show the menu again 'e' to quit");
			choose = s.next().charAt(0);
			switch (choose) {
			case '1':
				addOrder();
				break;
			case '2':
				removeOrder();
				break;
			case '3':
				addPizza();
				break;
			case '4':
				showPizzas();
				break;
			case '5':
				removePizza();
				break;
			case '6':
				checkOrderStatus();
				break;
			case '7':
				showAllOrders();
				break;
			case '8':
				orderCharge();
				break;
			case '9':
				allOrdersPay();
				break;
			case 'm':
				System.out.println(
						"1. add new order \n2. cancel your order \n3. add a pizza \n4. show my pizzas \n5. delete a pizza \n6. check order status \n7. show all orders \n8. charge order \n9. all today gains");
				break;

			case 'e':
				System.out.println("Thank you for your purchase in K&G Pizza Place\nplease come back soon ;)");
				invalid = false;
				break;
			default:
				System.out.println("Wrong key, please try again");
			}
		} while (invalid);
	}

	public void addOrder() {
		System.out.println("Please enter your name: ");
		String customName = s.next();
		System.out.println("Please enter your address: ");
		String address = s.next();
		orders[++countOrders] = new Order(customName, address);
	}

	public void removeOrder() {
		System.out.println("Are you sure you want to delete your order? (Y/N)");
		char ch = s.next().charAt(0);
		boolean invalid = true;
		do {
			if (ch == 'Y' || ch == 'y') {
				System.out.println("This order is deleted!");
				this.orders[countOrders].setOrderStatus("Canceled");
				invalid = true;
			} else if (ch == 'N' || ch == 'n') {
				System.out.println("The order not deleted");
				invalid = true;
			} else {
				System.out.println("Wronge key, please enter 'Y' or 'N'");
				invalid = false;
			}
		} while (!invalid);
	}

	public void orderCharge() {
		System.out.println("Are you sure you want to pay for your order? (Y/N)");
		char ch = s.next().charAt(0);
		boolean invalid;
		do {
			invalid = true;
			if (ch == 'Y' || ch == 'y') {
				this.orders[countOrders].setPay();
				System.out.println("you payed: " + this.orders[countOrders].getPay() + " nis\nenjoy :)");
				this.orders[countOrders].setOrderStatus("inPreparation");
			} else if (ch == 'N' || ch == 'n') {
				System.out.println("You don't charge");
			} else {
				System.out.println("Wronge key, please enter 'Y' or 'N'");
				invalid = false;
			}
		} while (!invalid);
	}

	public void checkOrderStatus() {
		if (this.orders[countOrders].getOrderStatus().name().compareTo("created") == 0
				|| this.orders[countOrders].getOrderStatus().name().compareTo("canceled") == 0) {
			System.out.println(this.orders[countOrders].getOrderStatus());
		}

	}

	public void allOrdersPay() {
		gains = 0;
		for (int i = 0; i < orders.length; i++) {
			if (orders[i] != null && orders[i].getOrderStatus().name().compareTo("inPreparation") == 0) {
				gains += orders[i].getPay();
			}
		}
		System.out.println("All the profit for the day are: " + gains + " nis");
	}

	public void addPizza() {
		if (this.orders[countOrders].setOrderPizzas()) {
			System.out.println("Pizza added");
		} else {
			System.out.println("You can order only 4 pizzas in each order");
		}
	}

	public void removePizza() {
		boolean invalid = false;
		showPizzas();
		int pizzaNum;
		do {
			System.out.println("Please enter the pizza number that you want to delete: ");
			pizzaNum = s.nextInt();
			if (pizzaNum > 0 && pizzaNum <= 4) {
				invalid = true;
				if (this.orders[countOrders].removePizzas(pizzaNum)) {
					System.out.println("Pizza deleted");
				} else {
					System.out.println("This pizza number does not exist");
				}
			}
		} while (!invalid);
	}

	public void showPizzas() {
		this.orders[countOrders].showPizzas();
	}

	public void showAllOrders() {
		for (int i = 0; i < orders.length; i++) {
			if (orders[i] != null) {
				System.out.println(orders[i].toString());
			}
		}
	}

}
