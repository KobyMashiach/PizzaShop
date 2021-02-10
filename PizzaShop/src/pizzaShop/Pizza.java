package pizzaShop;

import java.util.Scanner;

public class Pizza {

	Scanner s = new Scanner(System.in);

	public enum Dough {
		wheat, wholeWheat, glutenFree
	};

	public enum DoughType {
		thin, thick
	};

	public enum Size {
		personal, medium, large, family, huge
	};

	public enum Topping {
		none, onions, corns, olives, mushrooms, exstraCheese, pineapple, humiNumi
	};

	public final static double basePrice = 40;

	public double price;

	public Dough dough;
	public DoughType doughType;
	public Size size;
	public Topping[] topping;

	private int freeToppingCount;

	private String saveDough, saveDoughType, saveSize, saveTopping = "";

	public Pizza() {
		setDough();
	}

	public int getFreeToppingCount() {
		return freeToppingCount;
	}

	public void setDough() {
		Dough[] doughArr = Dough.values();

		System.out.println("Enter which dough you want?\n(wheat, wholeWheat, glutenFree)");
		String name;
		boolean invalid = false;

		do {
			name = s.next();
			invalid = false;
			for (int i = 0; i < doughArr.length; i++) {
				if (name.compareTo(doughArr[i].name()) == 0) { // check that the name is correct
					name = doughArr[i].name();
					if (doughArr[i] == Dough.wheat || doughArr[i] == Dough.wholeWheat) {
						price = basePrice;
					} else if (doughArr[i] == Dough.glutenFree) {
						price = basePrice + 10;
					}
					invalid = true;
					break;
				}
				System.out.println();
			}
			if (!invalid) {
				System.out.println("This option is not exists, please try again\n(wheat, wholeWheat, glutenFree)");
			}
		} while (!invalid);
		saveDough = name;
		setDoughType();
	}

	public void setDoughType() {
		DoughType[] DoughTypeArr = DoughType.values();

		System.out.println("Enter which DoughType you want?\n(thin, thick)");
		String name;
		boolean invalid = false;

		do {
			name = s.next();
			invalid = false;
			for (int i = 0; i < DoughTypeArr.length; i++) {
				if (name.compareTo(DoughTypeArr[i].name()) == 0) { // check that the name is correct
					name = DoughTypeArr[i].name();
					if (DoughTypeArr[i] == DoughType.thick) {
						price += 10;
					}
					invalid = true;
					break;
				}
			}
			if (!invalid) {
				System.out.println("This option is not exists, please try again\n(thin, thick)");
			}
		} while (!invalid);
		saveDoughType = name;
		setSize();
	}

	public void setSize() {
		Size[] SizeArr = Size.values();

		System.out.println("Enter which Size do you want?\n(personal, medium, large, family, huge)");
		String name;
		boolean invalid = false;

		do {
			name = s.next();
			invalid = false;
			for (int i = 0; i < SizeArr.length; i++) {
				if (name.compareTo(SizeArr[i].name()) == 0) { // check name is correct
					name = SizeArr[i].name();
					if (SizeArr[i] == Size.personal) {
						price -= 10;
					} else if (SizeArr[i] == Size.medium) {
						price -= 5;
					} else if (SizeArr[i] == Size.family) {
						price += 5;
					} else if (SizeArr[i] == Size.huge) {
						price += 10;
					}
					invalid = true;
					break;
				}
			}
			if (!invalid) {
				System.out.println(
						"This option does not exists, please try again\n(personal, medium, large, family, huge)");
			}
		} while (!invalid);
		saveSize = name;
		setTopping();
	}

	public void setTopping() {
		Topping[] ToppingArr = Topping.values();

		System.out.println(
				"Enter which Topping you want?\n(onions, corns, olives, mushrooms, exstraCheese, pineapple, humiNumi)");
		String name;
		boolean invalid = false;
		boolean invalid2 = false;
		boolean moreToppings = false;
		double savePrice = price;
		boolean first = true;
		freeToppingCount = 0; // if more than 2 pizzas - free 2 topping

		do {
			name = s.next();
			invalid = false;
			moreToppings = false;
			char ch;
			for (int i = 0; i < ToppingArr.length; i++) {
				if (name.compareTo(ToppingArr[i].name()) == 0) { // check name is correct
					freeToppingCount++;
					name = ToppingArr[i].name();
					if (first) {
						saveTopping += name;
						first = false;
					} else {
						saveTopping += ", " + name;
					}

					if (name == "none") {
						invalid = true;
						break;
					}

					price += savePrice * 0.1;
					do {
						System.out.println("Do you want more תוספות? (Y/N)");
						ch = s.next().charAt(0);
						invalid2 = false;
						if (ch == 'y' || ch == 'Y') {
							moreToppings = true;
							invalid2 = true;
							System.out.println("onions, corns, olives, mushrooms, exstraCheese, pineapple, humiNumi");
						} else if (ch == 'n' || ch == 'N') {
							invalid2 = true;
						}
						if (!invalid2) {
							System.out.println("Please enter just 'n'\'N'\'y'\'Y'");
						}
					} while (!invalid2);
				}
			}

			if (!invalid && !moreToppings && !invalid2) {
				System.out.println(
						"This option is not exists, please try again\n(onions, corns, olives, mushrooms, exstraCheese, pineapple, humiNumi)");
			}

		} while (!invalid && moreToppings);

	}

	public String getDough() {
		return saveDough;
	}

	public String getDoughType() {
		return saveDoughType;
	}

	public String getSize() {
		return saveSize;
	}

	public String getTopping() {
		return saveTopping;
	}

	public double getPrice() {
		return price;
	}

}
