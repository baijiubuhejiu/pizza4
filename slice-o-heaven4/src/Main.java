import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calender;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class PizzaStore {

    private String storeName;
    private String storeAddress;
    private String storeEmail;
    private String storePhone;

    private List<String> storeMenu;
    private Map<String, List<String>> pizzaIngredients;
    private Map<String, Double> pizzaPrice;
    private List<String> sides;
    private List<String> drinks;

    private String orderID;
    private double orderTotal;
    private List<Order> orders;


    private static final String DEF_ORDER_ID = "DEF-SOH-099";
    private static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private static final double DEF_ORDER_TOTAL = 15.00;


    private static final List<Long> BLACKLISTED_CARDS = new ArrayList<>();

    static {
        BLACKLISTED_CARDS.add("1234567890123456");
    }


    public PizzaStore() {
        storeName = "Slice-o-Heaven";
        storeAddress = "123 Pizza Street";
        storeEmail = "info@sliceoheaven.com";
        storePhone = "123 - 456 - 7890";

        storeMenu = new ArrayList<>();
        storeMenu.add("Margherita");
        storeMenu.add("Pepperoni");
        storeMenu.add("Hawaiian");

        pizzaIngredients = new HashMap<>();
        List<String> defaultIngredients = new ArrayList<>();
        defaultIngredients.add(DEF_PIZZA_INGREDIENTS);
        for (String pizza : storeMenu) {
            pizzaIngredients.put(pizza, defaultIngredients);
        }

        pizzaPrice = new HashMap<>();
        pizzaPrice.put("Margherita", 10.99);
        pizzaPrice.put("Pepperoni", 12.99);
        pizzaPrice.put("Hawaiian", 13.99);

        sides = new ArrayList<>();
        sides.add("Garlic Bread");
        sides.add("Onion Rings");

        drinks = new ArrayList<>();
        drinks.add("Coke");
        drinks.add("Pepsi");

        orderID = DEF_ORDER_ID;
        orderTotal = DEF_ORDER_TOTAL;
        orders = new ArrayList<>();
    }


    public PizzaStore(String orderID, Map<String, List<String>> pizzaIngredients, double orderTotal) {
        this();
        this.orderID = orderID;
        this.pizzaIngredients = pizzaIngredients;
        this.orderTotal = orderTotal;
    }


    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public List<String> getStoreMenu() {
        return storeMenu;
    }

    public void setStoreMenu(List<String> storeMenu) {
        this.storeMenu = storeMenu;
    }

    public Map<String, List<String>> getPizzaIngredients() {
        return pizzaIngredients;
    }

    public void setPizzaIngredients(Map<String, List<String>> pizzaIngredients) {
        this.pizzaIngredients = pizzaIngredients;
    }

    public Map<String, Double> getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(Map<String, Double> pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public List<String> getSides() {
        return sides;
    }

    public void setSides(List<String> sides) {
        this.sides = sides;
    }

    public List<String> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<String> drinks) {
        this.drinks = drinks;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to " + storeName + "!");

        System.out.print("Enter pizza ingredients: ");
        String pizzaIngredientsInput = scanner.nextLine();

        System.out.print("Enter pizza size (e.g.,small, medium, large): ");
        String pizzaSize = scanner.nextLine();

        System.out.print("Do you want extra cheese? (yes/no): ");
        String extraCheese = scanner.nextLine();

        Ststem.out.print("Enter side dish: ");
        String side = scanner.nextLine();

        System.out.print("Enter drink: ");
        String drink = scanner.nextLine();

        System.out.print("Do you want a half - price discount? (yes/no): ");
        String discountChoice = scanner.nextLine();

        if("yes".equalsIgnoreCase(discountChoice)){
            isItYourBirthday();
        }else{
            makeCardPayment();
        }

        double pizzaBasePrice = 10.0;
        double sidePrice = 3.0;
        double drinkPrice = 2.0;
        if("large".equalsIgnoreCase(pizzaSize)){
            pizzaBasePrice += 5.0;
        }else if("medium".equalsIgnoreCase(pizzaSize)){
            pizzaBasePrice +=3.0;
        }
        if("yes".equalsIgnoreCase(extraCheese)){
            pizzaBasePrice +=2.0;
        }
        if(side.contains(side)){
            orderTotal +=sidePrice;
        }
        if(drinks.contains(drink)){
            orderTotal +=drinkPrice;
        }
        orderTotal += pizzaBasePrice;

        Order order = new Order(orderID, pizzaIngredientsInput, pizzaSize, extraCheese, side, drink, orderTotal);
        orders.add(order);
        orderID = String.valueOf(Integer.parseInt(orderID.split("-")[2]) + 1);

        printReceipt();
    }

    public void isItYourBirthday(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your birthday (in yyyy - MM - dd format): ");
        String birthdayInput = scanner.nextLin();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy - MM - dd");
        try{
            Date birthday = sdf.parse(birthdayInput);
            Calendar cal = Calendar.getInstance();
            cal.setTime(birthday);
            int birthYear = cal.get(Calendar.YEAR);
            int birthMonth = cal.get(Calendar.MONTH);
            int birthDay = cal.get(Calendar.DAY_OF_MONTH);

            cal.setTime(new Date());
            int currentYear = cal.get(Calendar.YEAR);
            int currentMonth = cal.get(Calendar.MONTH);
            int currentDay = cal.get(Calendar.DAY_OF_MONTH);

            int age = currentYear - birthYear;
            if (birthMonth > currentMonth || (birthMonth == currentMonth && birthDay > currentDay)) {
                age--;
            }

            if (age < 18 && birthMonth == currentMonth && birthDay == currentDay) {
                System.out.println("You are eligible for a half - price discount!");
                orderTotal /= 2;
            } else {
                System.out.println("Sorry, you don't meet the criteria for a half - price discount.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
    }

    public void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter card number: ");
        long cardNumber = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter card expiration date: ");
        String expiryDate = scanner.nextLine();
        System.out.print("Enter CVV code: ");
        int cvv = scanner.nextInt();
        processCardPayment(cardNumber, expiryDate, cvv);
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = String.valueOf(cardNumber);
        if (cardNumberStr.length() != 16) {
            System.out.println("Invalid card");
            return;
        }
        System.out.println("Card accepted");

        int firstDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
        System.out.println("First digit of the card number: " + firstDigit);

        if (BLACKLISTED_CARDS.contains(cardNumber)) {
            System.out.println("This card is blacklisted. Payment declined.");
            return;
        }

        int lastFourDigits = Integer.parseInt(cardNumberStr.substring(12));
        System.out.println("Last four digits of the card number: " + lastFourDigits);

        String maskedCardNumber = cardNumberStr.substring(0, 4) + "**** ****" + cardNumberStr.substring(12);
        System.out.println("Masked card number: " + maskedCardNumber);
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        StringBuilder specialInfo = new StringBuilder();
        specialInfo.append("Special of the day:\n");
        specialInfo.append("Pizza: ").append(pizzaOfTheDay).append("\n");
        specialInfo.append("Side: ").append(sideOfTheDay).append("\n");
        specialInfo.append("Price: $").append(specialPrice).append("\n");
        System.out.println(specialInfo.toString());
    }

    // 更新 printReceipt 方法
    public void printReceipt() {
        System.out.println("Receipt for " + storeName);
        System.out.println("Address: " + storeAddress);
        System.out.println("Email: " + storeEmail);
        System.out.println("Phone: " + storePhone);
        System.out.println("------------------------------");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Pizza Ingredients: " + order.getPizzaIngredients());
            System.out.println("Pizza Size: " + order.getPizzaSize());
            System.out.println("Extra Cheese: " + order.getExtraCheese());
            System.out.println("Side Dish: " + order.getSide());
            System.out.println("Drink: " + order.getDrink());
            System.out.println("Total: $" + order.getTotal());
            System.out.println("------------------------------");
        }
        System.out.println("Grand Total: $" + orderTotal);
    }
}

class Order {
    private String orderID;
    private String pizza;
    private int quantity;
    private String side;
    private String drink;
    private double total;

    public Order(String orderID, String pizza, int quantity, String side, String drink, double total) {
        this.orderID = orderID;
        this.pizza = pizza;
        this.quantity = quantity;
        this.side = side;
        this.drink = drink;
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getPizza() {
        return pizza;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSide() {
        return side;
    }

    public String getDrink() {
        return drink;
    }

    public double getTotal() {
        return total;
    }
}

public class Main {
    public static void main(String[] args) {
        PizzaStore pizzaStore = new PizzaStore();
        pizzaStore.takeOrder();


        pizzaStore.specialOfTheDay("Supreme", "Fries", "15.99");
    }
}