package pizzaShop;

public class Order {

	public enum Status {
		created, inPreparation, inTheOven, onTheWay, delivered, canceled
	};

	public static int orderNumber = 1000;

	private final int MaxPizzas = 4;
	private Pizza[] orderPizzas = new Pizza[MaxPizzas];
	private String customName;
	private String address;
	private double pay;
	private int amountOfPizzas;
	private int thisOrderNum;
	private Status orderStatus;

	public Order(String customName, String address) {
		this.customName = customName;
		this.address = address;
		pay = 0;
		amountOfPizzas = 0;
		thisOrderNum = orderNumber++;
		orderStatus = Status.created;
	}

	public boolean setOrderPizzas() {
		boolean canMorePizza = setAmountOfPizzas();
		if (canMorePizza) {
			for (int i = 0; i < MaxPizzas; i++) {
				if (this.orderPizzas[i] == null) {
					this.orderPizzas[i] = new Pizza();
					pay += this.orderPizzas[i].getPrice();
					return true;
				}
			}
		}
		return false;
	}

	public boolean removePizzas(int pizzaNum) {
		if (orderPizzas[pizzaNum - 1] != null) {
			orderPizzas[pizzaNum - 1] = null;
			return true;
		}
		return false;
	}

	public boolean setAmountOfPizzas() {
		if (this.amountOfPizzas < MaxPizzas) {
			this.amountOfPizzas++;
			return true;
		}
		return false;
	}

	public void setPay() {
		int freeTopping = 2;
		if (amountOfPizzas >= 2) {
			for (int i = 0; i < orderPizzas.length && freeTopping > 0; i++) {
				if (orderPizzas[i] != null) {
					if (orderPizzas[i].getFreeToppingCount() == 1) {
						freeTopping--;
						orderPizzas[i].price /= 1.1;
					} else if (orderPizzas[i].getFreeToppingCount() >= 2) {
						freeTopping -= 2;
						orderPizzas[i].price /= 1.2;
					}
				}
			}
		}
	}

	public Status setOrderStatus(String orderStatus) {
		Status[] statusArr = Status.values();

		for (int i = 0; i < statusArr.length; i++) {
			if (orderStatus.compareTo(statusArr[i].name()) == 0) {
				this.orderStatus = statusArr[i];
			}
		}
		return this.getOrderStatus();
	}

	public Status getOrderStatus() {
		return orderStatus;
	}

	public String getCustomName() {
		return customName;
	}

	public String getAddress() {
		return address;
	}

	public double getPay() {
		double pay = 0;
		for (int i = 0; i < orderPizzas.length; i++) {
			if (orderPizzas[i] != null) {
				pay += orderPizzas[i].price;
			}
		}
		return pay;
	}

	public int getAmountOfPizzas() {
		return amountOfPizzas;
	}

	public int getThisOrderNum() {
		return thisOrderNum;
	}

	public void showPizzas() {
		String str = "";
		for (int i = 0; i < orderPizzas.length; i++) {
			if (orderPizzas[i] != null) {
				str += (i + 1) + ": dough: " + orderPizzas[i].getDough() + "\tdough type: "
						+ orderPizzas[i].getDoughType() + "\tsize: " + orderPizzas[i].getSize() + "\ttopping: "
						+ orderPizzas[i].getTopping() + "\tprice: " + orderPizzas[i].price + "\n";
			}
		}
		if (str.length() > 0) {
			System.out.println(str);
		} else {
			System.out.println("You don't have any pizzas in your order");
		}
	}

	public String toString() {
		double price = 0;
		String str = "";
		for (int i = 0; i < orderPizzas.length; i++) {
			if (this.orderPizzas[i] != null) {
				price += this.orderPizzas[i].getPrice();
			}
		}
		str += "\norder number: #" + getThisOrderNum() + "\tName: " + getCustomName() + "\tAddress: " + getAddress()
				+ "\tAmount of pizzas: " + getAmountOfPizzas() + "\tStatus: " + getOrderStatus() + "\tprice: "
				+ Double.toString(price);
		return str;
	}

}
