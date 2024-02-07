import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Main extends Application implements Initializable {

    static String email;
     static String pass;
    static String confirmPass;
    static String name;
    static String surname;
    static  long id;
    static String card;
    static String phoneNr;

//    JAVAFX---------------------
    @FXML
    private TextField emailText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label emailError;
    @FXML
    private Label passError;
    private Stage stage;
    private Scene scene;
    private Parent rootLogin;

    public void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-page.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    static ArrayList<User> klienti = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void testLoginButton(ActionEvent event) throws IOException {
        boolean emailBoolean=false;
        boolean passBoolean=false;
        emailError.setText("");
        passError.setText("");
            try {
                email = emailText.getText();
                boolean emailCheck = false;
                for (User user : klienti) {
                    if (user.getEmail().equals(email)) {
                        emailCheck = true;
                        break;
                    }
                }
                if (emailCheck) {
                    emailBoolean = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                pass = passwordText.getText();
                boolean passCheck = false;
                for (User user : klienti) {
                    if (user.getEmail().equals(email) && user.getPassword().equals(pass)) {
                        passCheck = true;
                        break;
                    }
                }
                if (passCheck) {
                    passBoolean = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!emailBoolean && !passBoolean) {
                emailError.setText("Email is wrong!!!");
                passError.setText("Password is wrong!!!");
            } else if (!emailBoolean) {
                emailError.setText("Email is wrong!!!");
            } else if (!passBoolean) {
                passError.setText("Password is wrong!!!");
            } else {
                rootLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
                scene = new Scene(rootLogin);
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Successful");
                alert.setHeaderText(null);
                alert.setContentText("You have logged in successfully!");
                alert.showAndWait();
                alert.setResizable(false);
            }
    }

    //register
    @FXML
    private TextField nameText;
    @FXML
    private TextField surnameText;
    @FXML
    private TextField phoneNrText;
    @FXML
    private TextField emailRegisterText;
    @FXML
    private TextField cardNrText;
    @FXML
    private PasswordField passwordRegisterText;
    @FXML
    private Label emailRegisterError;
    @FXML
    private Label cardNrError;
    @FXML
    private Label passRegisterError;
    @FXML
    private Label confirmPassRegisterError;
    @FXML
    private PasswordField confirmPasswordRegisterText;
    boolean registerBoolean = false;

    public void testRegisterButton(ActionEvent event) throws IOException {
        boolean cardBoolean = false;
        boolean emailBoolean=false;
        boolean emailBoolean2=false;
        boolean emailBoolean3=false;
        boolean passBoolean=false;
        boolean confirmBoolean = false;
        emailRegisterError.setText("");
        passRegisterError.setText("");
        cardNrError.setText("");
        confirmPassRegisterError.setText("");
        try {
            name = nameText.getText();
            surname = surnameText.getText();
            phoneNr = phoneNrText.getText();
            email = emailRegisterText.getText();
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,3}$";
            if (email.matches(emailRegex)) {
                for (User user : klienti) {
                    if (user.getEmail().equals(email)) {
                        emailBoolean3 = true;
                        break;
                    }
                }
                if(emailBoolean3) {
                   emailBoolean = true;
                }else {
                    emailBoolean = true;
                    emailBoolean2 = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            card = cardNrText.getText();
            if (card!=null || !card.trim().isEmpty()) {
                for (Bank value : bank) {
                    if (value.getCardNumber().equals(card)) {
                        cardBoolean = true;
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            pass = passwordRegisterText.getText();
            confirmPass = confirmPasswordRegisterText.getText();
              if (pass.equals(confirmPass)) {
                  passBoolean=true;
                  confirmBoolean=true;
              }

            Random random = new Random();
              boolean idCheck = true;
              id = 0;
              while (idCheck) {
                  id = random.nextLong(999999999)+1;
                  for (User user : klienti) {
                      if (user.getID() == id) {
                          idCheck = false;
                          break;
                      }
                  }
                  if(idCheck){
                      idCheck = false;
                  }else{
                      idCheck = true;
                  }
              }
              if (emailBoolean2 && cardBoolean && passBoolean) {
                  klienti.add(new User(id, name, surname, card, pass, email, phoneNr));
              }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!emailBoolean) {
            emailRegisterError.setText("Email is wrong!!!");
        }
        if (emailBoolean3) {
            emailRegisterError.setText("Email already exists!!!");
        }
        if (!cardBoolean) {
            cardNrError.setText("Card Number is wrong!!!");
        }
        if (!passBoolean) {
            passRegisterError.setText("Password is wrong!!!");
        }
        if (!confirmBoolean) {
            confirmPassRegisterError.setText("Confirmed Password is wrong!!!");
        }
        if (emailBoolean2 && cardBoolean && passBoolean){
            registerBoolean = true;
            rootLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(rootLogin);
            stage.setScene(scene);
            stage.show();
        }
    }

    private void handleCloseRequest(WindowEvent event) {
        System.exit(0);
    }
    @Override
    public void start(Stage stage){

        stage.setOnCloseRequest(this::handleCloseRequest);


        User k1 = new User(1, "Leke", "Markaj",  "11", "leka", "markaj.leka@gmail.com","044806543");
        User k2 = new User(2, "Tush", "Markaj",  "1", "tush", "markaj.tush@gmail.com","043836541");
        User k3 = new User(3, "Antoneta", "Markaj",  "2", "neta", "markaj.neta@gmail.com","044106542");
        User k4 = new User(4, "Luigj", "Markaj",  "3", "lui", "markaj.lui@gmail.com","049806346");

        klienti.add(k1);
        klienti.add(k2);
        klienti.add(k3);
        klienti.add(k4);

        Bank b1 = new Bank(1, "1", "100.0");
        Bank b2 = new Bank(2, "2", "1001.0");
        Bank b3 = new Bank(3, "3", "10011.0");
        Bank b4 = new Bank(4, "4", "100111.0");
        Bank b5 = new Bank(5, "5", "100111.0");
        Bank b6 = new Bank(6, "6", "100111.0");
        Bank b7 = new Bank(7, "7", "1001111.0");
        Bank b8 = new Bank(8, "8", "10011111.0");
        Bank b9 = new Bank(9, "9", "100111111.0");
        Bank b10 = new Bank(10, "10", "1001111.0");
        Bank b11 = new Bank(11, "11", "111111.0");

        bank.add(b1);
        bank.add(b2);
        bank.add(b3);
        bank.add(b4);
        bank.add(b5);
        bank.add(b6);
        bank.add(b7);
        bank.add(b8);
        bank.add(b9);
        bank.add(b10);
        bank.add(b11);

        Restaurants r1 = new Restaurants("Route 66", Locations.PRISHTINE, "07:00 - 04:00", 5, Category.FASTFOOD, 10);
        Restaurants r2 = new Restaurants("Sushi Haven", Locations.FERIZAJ, "11:30 - 22:00", 4, Category.SEAFOOD, 0);
        Restaurants r3 = new Restaurants("Mamma Mia Pizzeria", Locations.GJAKOV, "12:00 - 23:00", 1, Category.TRADITIONAL, 0);
        Restaurants r4 = new Restaurants("Spice Kingdom", Locations.PEJE, "10:00 - 23:59", 2, Category.VEGETARIAN, 50);

        restaurants.add(r1);
        restaurants.add(r2);
        restaurants.add(r3);
        restaurants.add(r4);

        //Route 66
        Products p1 = new Products("66 Burger", 3.50, true, "Route 66");
        Products p2 = new Products("Sushi Delight", 12.00, false, "Route 66");
        Products p3 = new Products("Margherita Pizza", 9.50, false, "Route 66");
        Products p4 = new Products("Indian Spice Platter", 15.00, false, "Route 66");
        Products p5 = new Products("Classic Cheeseburger", 6.00, true, "Route 66");
        //Sushi Haven
        Products p11 = new Products("Red Velvet Cupcake", 3.00, true, "Sushi Haven");
        Products p12 = new Products("Dragon Roll", 14.50, false, "Sushi Haven");
        Products p13 = new Products("Spicy Tuna Sushi", 9.50, true, "Sushi Haven");
        Products p14 = new Products("Miso Soup", 4.50, false, "Sushi Haven");
        Products p15 = new Products("Edamame", 5.50, true, "Sushi Haven");
        //Mamma Mia Pizzeria
        Products p17 = new Products("Margarita", 3.50, true, "Mamma Mia Pizzeria");
        Products p18 = new Products("Pepperoni Pizza", 11.00, false, "Mamma Mia Pizzeria");
        Products p19 = new Products("Caprese Salad", 8.00, false, "Mamma Mia Pizzeria");
        Products p20 = new Products("Garlic Breadsticks", 5.50, false, "Mamma Mia Pizzeria");
        Products p21 = new Products("Vegetarian Calzone", 12.50, true, "Mamma Mia Pizzeria");
        // Spice Kingdom
        Products p23 = new Products("Noodles", 7.00, true, "Spice Kingdom");
        Products p24 = new Products("Butter Chicken", 12.00, false, "Spice Kingdom");
        Products p25 = new Products("Vegetable Biryani", 10.50, false, "Spice Kingdom");
        Products p26 = new Products("Paneer Tikka Masala", 9.50, false, "Spice Kingdom");
        Products p27 = new Products("Dal Tadka", 8.00, true, "Spice Kingdom");

        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);
        products.add(p11);
        products.add(p12);
        products.add(p13);
        products.add(p14);
        products.add(p15);
        products.add(p17);
        products.add(p18);
        products.add(p19);
        products.add(p20);
        products.add(p21);
        products.add(p23);
        products.add(p24);
        products.add(p25);
        products.add(p26);
        products.add(p27);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("ExpressEats");
            Image icon = new Image("delivery.png");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //---------------------------
    char euros = '€';

    static int orderId = 0;
    ArrayList<Products> cart = new ArrayList<>();
    static ArrayList<Bank> bank = new ArrayList<>();
    static ArrayList<Restaurants> restaurants = new ArrayList<>();
    static ArrayList<Products> products = new ArrayList<>();
    ArrayList<Orders> orders = new ArrayList<>();

    public void printOrders() {
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("--------------------");
            System.out.println("       Order " + (i + 1) + ": ");
            if (orders.get(i).getId() == i)
                System.out.println(orders.get(i));
            System.out.println("--------------------");
        }
    }

    public boolean printBill(Scanner scan) {
        boolean cardNumberBoolean = true;
        while (cardNumberBoolean) {
            boolean addressBoolean = false;
            System.out.println("\n-----------------------------");
            System.out.println("-------SHIPPING ADDRESS------\n");
            System.out.print("Address: ");
            String adress = scan.nextLine();
            if (adress.trim().isEmpty()){
                System.out.println("\nTHE LOCATION IS NECESSARY!!!");
                addressBoolean=true;
            }
            if (!addressBoolean){
                System.out.print("Add a comment:");
                String comment = scan.nextLine();
                System.out.print("\nAre you sure everything is correct: ");
                String answer = scan.nextLine().toLowerCase();
                if (answer.trim().isEmpty()){
                    System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                } else if (answer.contains("yes")) {
                    User tempUser = null;
                    int countCardNumber = 0;
                    for (User user : klienti) {
                        if (user.getEmail().equals(email)) {
                            tempUser = user;
                            break;
                        }
                    }

                    for (Bank value : bank) {
                        if (value.getCardNumber().equals(tempUser.getCardNumber())) {
                            countCardNumber++;
                        }
                    }

                    if (countCardNumber >= 1) {
                        System.out.println("\n-----------------------------");
                        System.out.println("------------FATURA-----------\n");
                        System.out.println("Products:");
                        printCart();
                        System.out.println("\nSubtotal: " + printCartSubTotal() + euros);
                        System.out.println("Delivery fee: 2" + euros);
                        System.out.println("Total: " + (printCartSubTotal() + 2) + euros);
                        System.out.println("\nTHANK YOU, SEE YOU SOON :)");
                        System.out.println("----------------------------");
                        cardNumberBoolean = false;

                        ArrayList<Products> productsForOrder = new ArrayList<>(cart);

                        Random r = new Random();
                        int random = r.nextInt(56) + 5;
                        Orders o = new Orders(orderId, productsForOrder, random, printCartSubTotal(), tempUser.getEmri().concat(" ").concat(tempUser.getMbiemri()), adress, tempUser.getPhoneNr(), comment);
                        orders.add(o);
                        orderId++;
                        cart.clear();
                    } else {
                        System.out.println("\nWRONG CARD NUMBER!!!");
                        System.out.print("Want to try again: ");
                        String answer0 = scan.nextLine().toLowerCase();
                        if (!answer0.contains("yes")) {
                            cardNumberBoolean = false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void printFastFoodRestaurants() {
        restaurants.stream().filter(restaurants -> restaurants.getCategory().equals(Category.FASTFOOD)).forEach(System.out::println);
    }

    public void printSeaFoodRestaurants() {
        restaurants.stream().filter(restaurants -> restaurants.getCategory().equals(Category.SEAFOOD)).forEach(System.out::println);
    }

    public void printVegFoodRestaurants() {
        restaurants.stream().filter(restaurants -> restaurants.getCategory().equals(Category.VEGETARIAN)).forEach(System.out::println);
    }

    public void printTraditionalFoodRestaurants() {
        restaurants.stream().filter(restaurants -> restaurants.getCategory().equals(Category.TRADITIONAL)).forEach(System.out::println);
    }

    public void printAllFoodRestaurants() {
        restaurants.forEach(System.out::println);
    }

    public void printFoods(String emriRestaurantit) {
        products.stream().filter(products -> products.getEmriRestaurant().equals(emriRestaurantit)).forEach(System.out::println);
    }

    public void printRestaurantRating(int opsionetRatings) {
        int count = 0;
        for (Restaurants restaurant : restaurants) {
            if (restaurant.getRatings() == opsionetRatings) {
                System.out.println(restaurant);
            } else {
                count++;
            }
        }
        if (count == restaurants.size()) {
            System.out.println("Restaurants with this rating do not exist!!!");
        }
    }

    public String balance(User k) {
        for (Bank value : bank) {
            if (k.getCardNumber().equals(value.getCardNumber())) {
                return value.getBalance();
            }
        }
        return null;
    }

    public double printCartSubTotal() {
        double total = 0;
        for (Products value : cart) {
            total += value.getPrice();
        }
        return total;
    }

    public void printCart() {
        for (Products value : cart) {
            System.out.println(value.toStringShort());
        }
    }


    public void printDiscount() {
        for (Restaurants restaurant : restaurants) {
            if (restaurant.getDiscount() > 0) {
                System.out.println(restaurant);
            }
        }
    }

    public static void main(String[] args) {

            launch(args);

            Scanner scan = new Scanner(System.in);

            Main m = new Main();

            boolean menu = true;
            while (menu) {
                System.out.println("\n-----------------------------");
                System.out.println("------------MENU-------------\n");
                System.out.print("|1.Restaurants| ");
                System.out.print("|2.Cart| ");
                System.out.print("|3.Account| ");
//                System.out.print("|4.Search| ");
                System.out.print("|4.Orders| ");
                System.out.print("|5.EXIT|\n");
                System.out.print("\nSelect Choice: ");
                int opsionetMenu = scan.nextInt();
                scan.nextLine();

                boolean booleanMenu = true;
                while (booleanMenu) {
                    if (opsionetMenu == 1) {
                        System.out.println("\n-----------------------------");
                        System.out.println("-------RESTAURANTS MENU------\n");
                        System.out.print("|1.Categories| ");
                        System.out.print("|2.Filter| ");
                        System.out.print("|3.Menu| ");
                        System.out.print("|4.EXIT|\n");
                        System.out.print("\nSelect Choice: ");
                        int opsionetProduktet = scan.nextInt();
                        scan.nextLine();

                        if (opsionetProduktet == 1) {
                            while (opsionetProduktet == 1) {
                                System.out.println("\n-----------------------------");
                                System.out.println("---------CATEGORIES----------\n");
                                System.out.print("|1.Fast Food| ");
                                System.out.print("|2.Sea Food| ");
                                System.out.print("|3.Vegetarian| ");
                                System.out.print("|4.Traditional| ");
                                System.out.print("|5.All Foods| ");
                                System.out.print("|6.Restaurant Menu| ");
                                System.out.print("|7.EXIT| \n");
                                System.out.print("\nSelect Choice: ");
                                int categoriesChoice = scan.nextInt();
                                scan.nextLine();
                                if (categoriesChoice == 1) {
                                    while (categoriesChoice == 1) {
                                        System.out.println("\n-----------------------------\n");
                                        m.printFastFoodRestaurants();
                                        System.out.print("\n|Categories Menu| ");
                                        System.out.print("|EXIT|\n");
                                        System.out.print("\nWrite Choice: ");
                                        String fastFoodRestaurantChoice = scan.nextLine().toLowerCase();
                                        Restaurants fastFoodRestaurant = null;
                                        for (Restaurants restaurant : restaurants) {
                                            if (restaurant.getName().toLowerCase().contains(fastFoodRestaurantChoice)) {
                                                fastFoodRestaurant = restaurant;
                                            }
                                        }
                                        if (fastFoodRestaurant != null) {
                                            boolean fastFoodRestaurantBoolean = true;
                                            while (fastFoodRestaurantBoolean) {
                                                System.out.println("\n-----------------------------\n");
                                                System.out.println(fastFoodRestaurant.getName().toUpperCase());
                                                System.out.println();
                                                m.printFoods(fastFoodRestaurant.getName());
                                                System.out.print("\n|Fast Food Restaurants| ");
                                                System.out.print("|EXIT|\n");
                                                System.out.print("\nWrite Choice: ");
                                                String fastFoodChoice = scan.nextLine().toLowerCase();
                                                Products fastFood = null;
                                                for (Products product : products) {
                                                    if (product.getEmriRestaurant().equals(fastFoodRestaurant.getName()) && product.getName().toLowerCase().contains(fastFoodChoice)) {
                                                        fastFood = product;
                                                    }
                                                }

                                                if (fastFood != null) {
                                                    boolean fastFoodBoolean = true;
                                                    while (fastFoodBoolean) {
                                                        System.out.println("\n-----------------------------\n");
                                                        System.out.println(fastFood);
                                                        System.out.print("\n|1.Add to cart| ");
                                                        System.out.print("|2.Show Products| ");
                                                        System.out.print("|3.Menu| ");
                                                        System.out.print("|4.EXIT|\n");
                                                        System.out.print("\nSelect Choice: ");
                                                        int fastFoodChoice2 = scan.nextInt();
                                                        scan.nextLine();

                                                        if (fastFoodChoice2 == 1) {
                                                            System.out.println("\n-----------------------------\n");
                                                            m.cart.add(fastFood);
                                                            System.out.print("|1.Check Out Now| ");
                                                            System.out.print("|2.Show Products| ");
                                                            System.out.print("|3.Menu| ");
                                                            System.out.print("|4.EXIT|");
                                                            System.out.print("\n\nSelect Choice: ");
                                                            int fastFoodChoice3 = scan.nextInt();
                                                            scan.nextLine();

                                                            if (fastFoodChoice3 == 1) {
                                                                while (fastFoodChoice3 == 1) {
                                                                    if (m.printBill(scan)) {
                                                                        fastFoodChoice3 = 0;
                                                                        fastFoodBoolean = false;
                                                                        fastFoodRestaurantBoolean = false;
                                                                        categoriesChoice = 0;
                                                                        opsionetProduktet = 0;
                                                                        booleanMenu = false;
                                                                    }
                                                                }
                                                            } else if (fastFoodChoice3 == 2) {
                                                                fastFoodBoolean = false;
                                                            } else if (fastFoodChoice3 == 3) {
                                                                fastFoodBoolean = false;
                                                                fastFoodRestaurantBoolean = false;
                                                                categoriesChoice = 0;
                                                                opsionetProduktet = 0;
                                                                booleanMenu = false;
                                                            } else if (fastFoodChoice3 == 4) {
                                                                System.exit(0);
                                                            } else {
                                                                System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                            }
                                                        } else if (fastFoodChoice2 == 2) {
                                                            fastFoodBoolean = false;
                                                        } else if (fastFoodChoice2 == 3) {
                                                            fastFoodBoolean = false;
                                                            fastFoodRestaurantBoolean = false;
                                                            categoriesChoice = 0;
                                                            opsionetProduktet = 0;
                                                            booleanMenu = false;
                                                        } else if (fastFoodChoice2 == 4) {
                                                            System.exit(0);
                                                        } else {
                                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                        }
                                                    }
                                                } else if (fastFoodChoice.contains("fast food restaurants") || fastFoodChoice.contains("fast food")) {
                                                    fastFoodRestaurantBoolean = false;
                                                } else if (fastFoodChoice.contains("exit")) {
                                                    System.exit(0);
                                                } else {
                                                    System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                }
                                            }
                                        } else if (fastFoodRestaurantChoice.contains("categories menu") || fastFoodRestaurantChoice.contains("categories") || fastFoodRestaurantChoice.contains("menu")) {
                                            categoriesChoice = 0;
                                        } else if (fastFoodRestaurantChoice.contains("exit")) {
                                            System.exit(0);
                                        } else {
                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                        }
                                    }
                                } else if (categoriesChoice == 2) {
                                    //seafoods
                                    while (categoriesChoice == 2) {
                                        System.out.println("\n-----------------------------\n");
                                        m.printSeaFoodRestaurants();
                                        System.out.print("\n|Categories Menu| ");
                                        System.out.print("|EXIT|\n");
                                        System.out.print("\nWrite Choice: ");
                                        String seaFoodRestaurantChoice = scan.nextLine().toLowerCase();
                                        Restaurants seaFoodRestaurant = null;
                                        for (Restaurants restaurant : restaurants) {
                                            if (restaurant.getName().toLowerCase().contains(seaFoodRestaurantChoice)) {
                                                seaFoodRestaurant = restaurant;
                                            }
                                        }
                                        if (seaFoodRestaurant != null) {
                                            boolean seaFoodRestaurantBoolean = true;
                                            while (seaFoodRestaurantBoolean) {
                                                System.out.println("\n-----------------------------\n");
                                                System.out.println(seaFoodRestaurant.getName().toUpperCase());
                                                System.out.println();
                                                m.printFoods(seaFoodRestaurant.getName());
                                                System.out.print("\n|Sea Food Restaurants| ");
                                                System.out.print("|EXIT|\n");
                                                System.out.print("\nWrite Choice: ");
                                                String seaFoodChoice = scan.nextLine().toLowerCase();
                                                Products seaFood = null;
                                                for (Products product : products) {
                                                    if (product.getEmriRestaurant().equals(seaFoodRestaurant.getName()) && product.getName().toLowerCase().contains(seaFoodChoice)) {
                                                        seaFood = product;
                                                    }
                                                }
                                                if (seaFood != null) {
                                                    boolean seaFoodBoolean = true;
                                                    while (seaFoodBoolean) {
                                                        System.out.println("\n-----------------------------\n");
                                                        System.out.println(seaFood);
                                                        System.out.print("\n|1.Add to cart| ");
                                                        System.out.print("|2.Show Products| ");
                                                        System.out.print("|3.Menu| ");
                                                        System.out.print("|4.EXIT|\n");
                                                        System.out.print("\nSelect Choice: ");
                                                        int seaFoodChoice2 = scan.nextInt();
                                                        scan.nextLine();

                                                        if (seaFoodChoice2 == 1) {
                                                            System.out.println("\n-----------------------------\n");
                                                            m.cart.add(seaFood);
                                                            System.out.print("|1.Check Out Now| ");
                                                            System.out.print("|2.Show Products| ");
                                                            System.out.print("|3.Menu| ");
                                                            System.out.print("|4.EXIT|");
                                                            System.out.print("\n\nSelect Choice: ");
                                                            int seaFoodChoice3 = scan.nextInt();
                                                            scan.nextLine();

                                                            if (seaFoodChoice3 == 1) {
                                                                while (seaFoodChoice3 == 1) {
                                                                    if (m.printBill(scan)) {
                                                                        seaFoodChoice3 = 0;
                                                                        seaFoodBoolean = false;
                                                                        seaFoodRestaurantBoolean = false;
                                                                        categoriesChoice = 0;
                                                                        opsionetProduktet = 0;
                                                                        booleanMenu = false;
                                                                    }
                                                                }
                                                            } else if (seaFoodChoice3 == 2) {
                                                                seaFoodBoolean = false;
                                                            } else if (seaFoodChoice3 == 3) {
                                                                seaFoodBoolean = false;
                                                                seaFoodRestaurantBoolean = false;
                                                                categoriesChoice = 0;
                                                                opsionetProduktet = 0;
                                                                booleanMenu = false;
                                                            } else if (seaFoodChoice3 == 4) {
                                                                System.exit(0);
                                                            } else {
                                                                System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                            }
                                                        } else if (seaFoodChoice2 == 2) {
                                                            seaFoodBoolean = false;
                                                        } else if (seaFoodChoice2 == 3) {
                                                            seaFoodBoolean = false;
                                                            seaFoodRestaurantBoolean = false;
                                                            categoriesChoice = 0;
                                                            opsionetProduktet = 0;
                                                            booleanMenu = false;
                                                        } else if (seaFoodChoice2 == 4) {
                                                            System.exit(0);
                                                        } else {
                                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                        }
                                                    }
                                                } else if (seaFoodChoice.contains("sea food restaurants") || seaFoodChoice.contains("sea food") || seaFoodChoice.contains("sea")) {
                                                    seaFoodRestaurantBoolean = false;
                                                } else if (seaFoodChoice.contains("exit")) {
                                                    System.exit(0);
                                                } else {
                                                    System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                }
                                            }
                                        } else if (seaFoodRestaurantChoice.contains("categories menu") || seaFoodRestaurantChoice.contains("categories") || seaFoodRestaurantChoice.contains("menu")) {
                                            categoriesChoice = 0;
                                        } else if (seaFoodRestaurantChoice.contains("exit")) {
                                            System.exit(0);
                                        } else {
                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                        }
                                    }
                                } else if (categoriesChoice == 3) {
                                    //Vegetarian
                                    while (categoriesChoice == 3) {
                                        System.out.println("\n-----------------------------\n");
                                        m.printVegFoodRestaurants();
                                        System.out.print("\n|Categories Menu| ");
                                        System.out.print("|EXIT|\n");
                                        System.out.print("\nWrite Choice: ");
                                        String vegFoodRestaurantChoice = scan.nextLine().toLowerCase();
                                        Restaurants vegFoodRestaurant = null;
                                        for (Restaurants restaurant : restaurants) {
                                            if (restaurant.getName().toLowerCase().contains(vegFoodRestaurantChoice)) {
                                                vegFoodRestaurant = restaurant;
                                            }
                                        }
                                        if (vegFoodRestaurant != null) {
                                            boolean vegFoodRestaurantBoolean = true;
                                            while (vegFoodRestaurantBoolean) {
                                                System.out.println("\n-----------------------------\n");
                                                System.out.println(vegFoodRestaurant.getName().toUpperCase());
                                                System.out.println();
                                                m.printFoods(vegFoodRestaurant.getName());
                                                System.out.print("\n|Vegetarian Food Restaurants| ");
                                                System.out.print("|EXIT|\n");
                                                System.out.print("\nWrite Choice: ");
                                                String vegFoodChoice = scan.nextLine().toLowerCase();
                                                Products vegFood = null;
                                                for (Products product : products) {
                                                    if (product.getEmriRestaurant().equals(vegFoodRestaurant.getName()) && product.getName().toLowerCase().contains(vegFoodChoice)) {
                                                        vegFood = product;
                                                    }
                                                }

                                                if (vegFood != null) {
                                                    boolean vegFoodBoolean = true;
                                                    while (vegFoodBoolean) {
                                                        System.out.println("\n-----------------------------\n");
                                                        System.out.println(vegFood);
                                                        System.out.print("\n|1.Add to cart| ");
                                                        System.out.print("|2.Show Products| ");
                                                        System.out.print("|3.Menu| ");
                                                        System.out.print("|4.EXIT|\n");
                                                        System.out.print("\nSelect Choice: ");
                                                        int vegFoodChoice2 = scan.nextInt();
                                                        scan.nextLine();
                                                        if (vegFoodChoice2 == 1) {
                                                            System.out.println("\n-----------------------------\n");
                                                            m.cart.add(vegFood);
                                                            System.out.print("|1.Check Out Now| ");
                                                            System.out.print("|2.Show Products| ");
                                                            System.out.print("|3.Menu| ");
                                                            System.out.print("|4.EXIT|");
                                                            System.out.print("\n\nSelect Choice: ");
                                                            int vegFoodChoice3 = scan.nextInt();
                                                            scan.nextLine();

                                                            if (vegFoodChoice3 == 1) {
                                                                while (vegFoodChoice3 == 1) {
                                                                    if (m.printBill(scan)) {
                                                                        vegFoodChoice3 = 0;
                                                                        vegFoodBoolean = false;
                                                                        vegFoodRestaurantBoolean = false;
                                                                        categoriesChoice = 0;
                                                                        opsionetProduktet = 0;
                                                                        booleanMenu = false;
                                                                    }
                                                                }
                                                            } else if (vegFoodChoice3 == 2) {
                                                                vegFoodBoolean = false;
                                                            } else if (vegFoodChoice3 == 3) {
                                                                vegFoodBoolean = false;
                                                                vegFoodRestaurantBoolean = false;
                                                                categoriesChoice = 0;
                                                                opsionetProduktet = 0;
                                                                booleanMenu = false;
                                                            } else if (vegFoodChoice3 == 4) {
                                                                System.exit(0);
                                                            } else {
                                                                System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                            }
                                                        } else if (vegFoodChoice2 == 2) {
                                                            vegFoodBoolean = false;
                                                        } else if (vegFoodChoice2 == 3) {
                                                            vegFoodBoolean = false;
                                                            vegFoodRestaurantBoolean = false;
                                                            categoriesChoice = 0;
                                                            opsionetProduktet = 0;
                                                            booleanMenu = false;
                                                        } else if (vegFoodChoice2 == 4) {
                                                            System.exit(0);
                                                        } else {
                                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                        }
                                                    }
                                                } else if (vegFoodChoice.contains("vegetarian food restaurants") || vegFoodChoice.contains("vegetarian") || vegFoodChoice.contains("vegetarian food")) {
                                                    vegFoodRestaurantBoolean = false;
                                                } else if (vegFoodChoice.contains("exit")) {
                                                    System.exit(0);
                                                } else {
                                                    System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                }
                                            }
                                        } else if (vegFoodRestaurantChoice.contains("categories menu") || vegFoodRestaurantChoice.contains("categories") || vegFoodRestaurantChoice.contains("menu")) {
                                            categoriesChoice = 0;
                                        } else if (vegFoodRestaurantChoice.contains("exit")) {
                                            System.exit(0);
                                        } else {
                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                        }
                                    }
                                } else if (categoriesChoice == 4) {
                                    while (categoriesChoice == 4) {
                                        System.out.println("\n-----------------------------\n");
                                        m.printTraditionalFoodRestaurants();
                                        System.out.print("\n|Categories Menu| ");
                                        System.out.print("|EXIT|\n");
                                        System.out.print("\nWrite Choice: ");
                                        String tadFoodRestaurantChoice = scan.nextLine().toLowerCase();
                                        Restaurants tadFoodRestaurant = null;
                                        for (Restaurants restaurant : restaurants) {
                                            if (restaurant.getName().toLowerCase().contains(tadFoodRestaurantChoice)) {
                                                tadFoodRestaurant = restaurant;
                                            }
                                        }

                                        if (tadFoodRestaurant != null) {
                                            boolean tadFoodRestaurantBoolean = true;
                                            while (tadFoodRestaurantBoolean) {
                                                System.out.println("\n-----------------------------\n");
                                                System.out.println(tadFoodRestaurant.getName().toUpperCase());
                                                System.out.println();
                                                m.printFoods(tadFoodRestaurant.getName());
                                                System.out.print("\n|Traditional Food Restaurants| ");
                                                System.out.print("|EXIT|\n");
                                                System.out.print("\nWrite Choice: ");
                                                String tadFoodChoice = scan.nextLine().toLowerCase();
                                                Products tadFood = null;
                                                for (Products product : products) {
                                                    if (product.getEmriRestaurant().equals(tadFoodRestaurant.getName()) && product.getName().toLowerCase().contains(tadFoodChoice)) {
                                                        tadFood = product;
                                                    }
                                                }

                                                if (tadFood != null) {
                                                    boolean tadFoodBoolean = true;
                                                    while (tadFoodBoolean) {
                                                        System.out.println("\n-----------------------------\n");
                                                        System.out.println(tadFood);
                                                        System.out.print("\n|1.Add to cart| ");
                                                        System.out.print("|2.Show Products| ");
                                                        System.out.print("|3.Menu| ");
                                                        System.out.print("|4.EXIT|\n");
                                                        System.out.print("\nSelect Choice: ");
                                                        int tadFoodChoice2 = scan.nextInt();

                                                        if (tadFoodChoice2 == 1) {
                                                            System.out.println("\n-----------------------------\n");
                                                            m.cart.add(tadFood);
                                                            System.out.print("|1.Check Out Now| ");
                                                            System.out.print("|2.Show Products| ");
                                                            System.out.print("|3.Menu| ");
                                                            System.out.print("|4.EXIT|");
                                                            System.out.print("\n\nSelect Choice: ");
                                                            int tadFoodChoice3 = scan.nextInt();
                                                            scan.nextLine();

                                                            if (tadFoodChoice3 == 1) {
                                                                while (tadFoodChoice3 == 1) {
                                                                    if (m.printBill(scan)) {
                                                                        tadFoodChoice3 = 0;
                                                                        tadFoodBoolean = false;
                                                                        tadFoodRestaurantBoolean = false;
                                                                        categoriesChoice = 0;
                                                                        opsionetProduktet = 0;
                                                                        booleanMenu = false;
                                                                    }
                                                                }
                                                            } else if (tadFoodChoice3 == 2) {
                                                                tadFoodBoolean = false;
                                                            } else if (tadFoodChoice3 == 3) {
                                                                tadFoodBoolean = false;
                                                                tadFoodRestaurantBoolean = false;
                                                                categoriesChoice = 0;
                                                                opsionetProduktet = 0;
                                                                booleanMenu = false;
                                                            } else if (tadFoodChoice3 == 4) {
                                                                System.exit(0);
                                                            } else {
                                                                System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                            }
                                                        } else if (tadFoodChoice2 == 2) {
                                                            tadFoodBoolean = false;
                                                        } else if (tadFoodChoice2 == 3) {
                                                            tadFoodBoolean = false;
                                                            tadFoodRestaurantBoolean = false;
                                                            categoriesChoice = 0;
                                                            opsionetProduktet = 0;
                                                            booleanMenu = false;
                                                        } else if (tadFoodChoice2 == 4) {
                                                            System.exit(0);
                                                        } else {
                                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                        }
                                                    }
                                                } else if (tadFoodChoice.contains("traditional food restaurants") || tadFoodChoice.contains("traditional") || tadFoodChoice.contains("traditional food")) {
                                                    tadFoodRestaurantBoolean = false;
                                                } else if (tadFoodChoice.contains("exit")) {
                                                    System.exit(0);
                                                } else {
                                                    System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                }
                                            }
                                        } else if (tadFoodRestaurantChoice.contains("categories menu") || tadFoodRestaurantChoice.contains("categories") || tadFoodRestaurantChoice.contains("menu")) {
                                            categoriesChoice = 0;
                                        } else if (tadFoodRestaurantChoice.contains("exit")) {
                                            System.exit(0);
                                        } else {
                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                        }
                                    }
                                } else if (categoriesChoice == 5) {
                                    while (categoriesChoice == 5) {
                                        System.out.println("\n-----------------------------\n");
                                        m.printAllFoodRestaurants();
                                        System.out.print("\n|Categories Menu| ");
                                        System.out.print("|EXIT|\n");
                                        System.out.print("\nWrite Choice: ");
                                        String allFoodRestaurantChoice = scan.nextLine().toLowerCase();
                                        Restaurants allRestaurantFood = null;
                                        for (Restaurants restaurant : restaurants) {
                                            if (restaurant.getName().toLowerCase().contains(allFoodRestaurantChoice)) {
                                                allRestaurantFood = restaurant;
                                            }
                                        }

                                        if (allRestaurantFood != null) {
                                            boolean allFoodRestaurantBoolean = true;
                                            while (allFoodRestaurantBoolean) {
                                                System.out.println("\n-----------------------------\n");
                                                System.out.println(allRestaurantFood.getName().toUpperCase());
                                                System.out.println();
                                                m.printFoods(allRestaurantFood.getName());
                                                System.out.print("\n|All Food Restaurants| ");
                                                System.out.print("|EXIT|\n");
                                                System.out.print("\nWrite Choice: ");
                                                String allFoodChoice = scan.nextLine().toLowerCase();
                                                Products allFood = null;
                                                for (Products product : products) {
                                                    if (product.getEmriRestaurant().equals(allRestaurantFood.getName()) && product.getName().toLowerCase().contains(allFoodChoice)) {
                                                        allFood = product;
                                                    }
                                                }

                                                if (allFood != null) {
                                                    boolean allFoodBoolean = true;
                                                    while (allFoodBoolean) {
                                                        System.out.println("\n-----------------------------\n");
                                                        System.out.println(allFood);
                                                        System.out.print("\n|1.Add to cart| ");
                                                        System.out.print("|2.Show Products| ");
                                                        System.out.print("|3.Menu| ");
                                                        System.out.print("|4.EXIT|\n");
                                                        System.out.print("\nSelect Choice: ");
                                                        int allFoodChoice2 = scan.nextInt();
                                                        scan.nextLine();

                                                        if (allFoodChoice2 == 1) {
                                                            System.out.println("\n-----------------------------\n");
                                                            m.cart.add(allFood);
                                                            System.out.print("|1.Check Out Now| ");
                                                            System.out.print("|2.Show Products| ");
                                                            System.out.print("|3.Menu| ");
                                                            System.out.print("|4.EXIT|");
                                                            System.out.print("\n\nSelect Choice: ");
                                                            int allFoodChoice3 = scan.nextInt();
                                                            scan.nextLine();

                                                            if (allFoodChoice3 == 1) {
                                                                while (allFoodChoice3 == 1) {
                                                                    if (m.printBill(scan)) {
                                                                        allFoodChoice3 = 0;
                                                                        allFoodBoolean = false;
                                                                        allFoodRestaurantBoolean = false;
                                                                        categoriesChoice = 0;
                                                                        opsionetProduktet = 0;
                                                                        booleanMenu = false;
                                                                    }
                                                                }
                                                            } else if (allFoodChoice3 == 2) {
                                                                allFoodBoolean = false;
                                                            } else if (allFoodChoice3 == 3) {
                                                                allFoodBoolean = false;
                                                                allFoodRestaurantBoolean = false;
                                                                categoriesChoice = 0;
                                                                opsionetProduktet = 0;
                                                                booleanMenu = false;
                                                            } else if (allFoodChoice3 == 4) {
                                                                System.exit(0);
                                                            } else {
                                                                System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                            }
                                                        } else if (allFoodChoice2 == 2) {
                                                            allFoodBoolean = false;
                                                        } else if (allFoodChoice2 == 3) {
                                                            allFoodBoolean = false;
                                                            allFoodRestaurantBoolean = false;
                                                            categoriesChoice = 0;
                                                            opsionetProduktet = 0;
                                                            booleanMenu = false;
                                                        } else if (allFoodChoice2 == 4) {
                                                            System.exit(0);
                                                        } else {
                                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                        }
                                                    }
                                                } else if (allFoodChoice.contains("all food restaurants") || allFoodChoice.contains("all") || allFoodChoice.contains("all food")) {
                                                    allFoodRestaurantBoolean = false;
                                                } else if (allFoodChoice.contains("exit")) {
                                                    System.exit(0);
                                                } else {
                                                    System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                }
                                            }
                                        } else if (allFoodRestaurantChoice.contains("categories menu") || allFoodRestaurantChoice.contains("categories") || allFoodRestaurantChoice.contains("menu")) {
                                            categoriesChoice = 0;
                                        } else if (allFoodRestaurantChoice.contains("exit")) {
                                            System.exit(0);
                                        } else {
                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                        }
                                    }
                                } else if (categoriesChoice == 6) {
                                    opsionetProduktet = 0;
                                } else if (categoriesChoice == 7) {
                                    System.exit(0);
                                } else {
                                    System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                }
                            }
                        } else if (opsionetProduktet == 2) {
                            while (opsionetProduktet == 2) {
                                System.out.println("\n-----------------------------");
                                System.out.println("--------FILTER MENU---------\n");
                                System.out.print("\n|1.Ratings| ");
                                System.out.print("|2.Discounts| ");
                                System.out.print("|3.Restaurants Menu| ");
                                System.out.print("|4.EXIT|");
                                System.out.print("\n\nSelect a choice:");
                                int opsionetFilter = scan.nextInt();
                                scan.nextLine();

                                if (opsionetFilter == 1) {
                                    while (opsionetFilter == 1) {
                                        System.out.println("\n-----------------------------");
                                        System.out.print("\nSelect Rating 1 - 5 : ");
                                        int opsionetRatings = scan.nextInt();
                                        scan.nextLine();
                                        boolean opsionetRatingsBoolean = true;
                                        while (opsionetRatingsBoolean) {
                                            if (opsionetRatings >= 1 && opsionetRatings <= 5) {
                                                opsionetRatingsBoolean = false;
                                            } else {
                                                System.out.println("\nWRONG INPUT!!!");
                                                System.out.print("\nSelect Rating 1 - 5 : ");
                                                opsionetRatings = scan.nextInt();
                                                scan.nextLine();
                                            }
                                        }
                                        System.out.println("\n-----------------------------\n");
                                        m.printRestaurantRating(opsionetRatings);
                                        System.out.print("\n|Filter Menu| ");
                                        System.out.print("|EXIT|\n");
                                        System.out.print("\nWrite Choice: ");
                                        String ratingFoodRestaurantChoice = scan.nextLine().toLowerCase();
                                        Restaurants ratingRestaurantFood = null;
                                        for (Restaurants restaurant : restaurants) {
                                            if (restaurant.getRatings() == opsionetRatings && restaurant.getName().toLowerCase().contains(ratingFoodRestaurantChoice)) {
                                                ratingRestaurantFood = restaurant;
                                            }
                                        }

                                        if (ratingRestaurantFood != null) {
                                            boolean ratingFoodRestaurantBoolean = true;
                                            while (ratingFoodRestaurantBoolean) {
                                                System.out.println("\n-----------------------------\n");
                                                System.out.println(ratingRestaurantFood.getName().toUpperCase());
                                                System.out.println();
                                                m.printFoods(ratingRestaurantFood.getName());
                                                System.out.print("\n|Filter Menu| ");
                                                System.out.print("|EXIT|\n");
                                                System.out.print("\nWrite Choice: ");
                                                String ratingFoodChoice = scan.nextLine().toLowerCase();
                                                Products ratingFood = null;
                                                for (Products product : products) {
                                                    if (product.getEmriRestaurant().equals(ratingRestaurantFood.getName()) && product.getName().toLowerCase().contains(ratingFoodChoice)) {
                                                        ratingFood = product;
                                                    }
                                                }

                                                if (ratingFood != null) {
                                                    boolean ratingFoodBoolean = true;
                                                    while (ratingFoodBoolean) {
                                                        System.out.println("\n-----------------------------\n");
                                                        System.out.println(ratingFood);
                                                        System.out.print("\n|1.Add to cart| ");
                                                        System.out.print("|2.Show Products| ");
                                                        System.out.print("|3.Menu| ");
                                                        System.out.print("|4.EXIT|\n");
                                                        System.out.print("\nSelect Choice: ");
                                                        int ratingFoodChoice2 = scan.nextInt();
                                                        scan.nextLine();

                                                        if (ratingFoodChoice2 == 1) {
                                                            System.out.println("\n-----------------------------\n");
                                                            m.cart.add(ratingFood);
                                                            System.out.print("|1.Check Out Now| ");
                                                            System.out.print("|2.Show Products| ");
                                                            System.out.print("|3.Menu| ");
                                                            System.out.print("|4.EXIT|");
                                                            System.out.print("\n\nSelect Choice: ");
                                                            int ratingFoodChoice3 = scan.nextInt();
                                                            scan.nextLine();

                                                            if (ratingFoodChoice3 == 1) {
                                                                while (ratingFoodChoice3 == 1) {
                                                                    if (m.printBill(scan)) {
                                                                        ratingFoodChoice3 = 0;
                                                                        ratingFoodBoolean = false;
                                                                        ratingFoodRestaurantBoolean = false;
                                                                        opsionetFilter = 0;
                                                                        opsionetProduktet = 0;
                                                                        booleanMenu = false;
                                                                    }
                                                                }
                                                            } else if (ratingFoodChoice3 == 2) {
                                                                ratingFoodBoolean = false;
                                                            } else if (ratingFoodChoice3 == 3) {
                                                                ratingFoodRestaurantBoolean=false;
                                                                opsionetFilter=0;
                                                                ratingFoodBoolean = false;
                                                                opsionetProduktet = 0;
                                                                booleanMenu = false;
                                                            } else if (ratingFoodChoice3 == 4) {
                                                                System.exit(0);
                                                            } else {
                                                                System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                            }
                                                        } else if (ratingFoodChoice2 == 2) {
                                                            ratingFoodBoolean = false;
                                                        } else if (ratingFoodChoice2 == 3) {
                                                            ratingFoodRestaurantBoolean=false;
                                                            opsionetFilter=0;
                                                            ratingFoodBoolean = false;
                                                            opsionetProduktet = 0;
                                                            booleanMenu = false;
                                                        } else if (ratingFoodChoice2 == 4) {
                                                            System.exit(0);
                                                        } else {
                                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                        }
                                                    }
                                                } else if (ratingFoodChoice.contains("filter menu") || ratingFoodChoice.contains("filter") || ratingFoodChoice.contains("menu")) {
                                                    ratingFoodRestaurantBoolean = false;
                                                    opsionetFilter = 0;
                                                } else if (ratingFoodChoice.contains("exit")) {
                                                    System.exit(0);
                                                } else {
                                                    System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                }
                                            }
                                        } else if (ratingFoodRestaurantChoice.contains("filter menu") || ratingFoodRestaurantChoice.contains("filter") || ratingFoodRestaurantChoice.contains("menu")) {
                                            opsionetFilter = 0;
                                        } else if (ratingFoodRestaurantChoice.contains("exit")) {
                                            System.exit(0);
                                        } else {
                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                        }
                                    }
                                } else if (opsionetFilter == 2) {
                                    //Discounts
                                    while (opsionetFilter == 2) {
                                        System.out.println("\n-----------------------------");
                                        System.out.println("---DISCOUNTED RESTAURANTS---\n");
                                        m.printDiscount();
                                        System.out.print("\n|Filter Menu| ");
                                        System.out.print("|EXIT|");
                                        System.out.print("\n\nWrite Choice: ");
                                        String disFoodRestaurantChoice = scan.nextLine().toLowerCase();
                                        Restaurants disRestaurantFood = null;
                                        for (Restaurants restaurant : restaurants) {
                                            if (restaurant.getDiscount() > 0 && restaurant.getName().toLowerCase().contains(disFoodRestaurantChoice)) {
                                                disRestaurantFood = restaurant;
                                            }
                                        }

                                        if (disRestaurantFood != null) {
                                            boolean disFoodRestaurantBoolean = true;
                                            while (disFoodRestaurantBoolean) {
                                                System.out.println("\n-----------------------------\n");
                                                System.out.println(disRestaurantFood.getName().toUpperCase());
                                                System.out.println();
                                                m.printFoods(disRestaurantFood.getName());
                                                System.out.print("\n|Discounts Menu| ");
                                                System.out.print("|EXIT|\n");
                                                System.out.print("\nWrite Choice: ");
                                                String disFoodChoice = scan.nextLine().toLowerCase();
                                                Products disFood = null;
                                                for (Products product : products) {
                                                    if (product.getEmriRestaurant().equals(disRestaurantFood.getName()) && product.getName().toLowerCase().contains(disFoodChoice)) {
                                                        disFood = product;
                                                    }
                                                }

                                                if (disFood != null) {
                                                    boolean disFoodBoolean = true;
                                                    while (disFoodBoolean) {
                                                        System.out.println("\n-----------------------------\n");
                                                        System.out.println(disFood);
                                                        System.out.print("\n|1.Add to cart| ");
                                                        System.out.print("|2.Show Products| ");
                                                        System.out.print("|3.Menu| ");
                                                        System.out.print("|4.EXIT|\n");
                                                        System.out.print("\nSelect Choice: ");
                                                        int disFoodChoice2 = scan.nextInt();
                                                        scan.nextLine();

                                                        if (disFoodChoice2 == 1) {
                                                            System.out.println("\n-----------------------------\n");
                                                            m.cart.add(disFood);
                                                            System.out.print("|1.Check Out Now| ");
                                                            System.out.print("|2.Show Products| ");
                                                            System.out.print("|3.Menu| ");
                                                            System.out.print("|4.EXIT|");
                                                            System.out.print("\n\nSelect Choice: ");
                                                            int disFoodChoice3 = scan.nextInt();
                                                            scan.nextLine();

                                                            if (disFoodChoice3 == 1) {
                                                                while (disFoodChoice3 == 1) {
                                                                    if (m.printBill(scan)) {
                                                                        disFoodChoice3 = 0;
                                                                        disFoodBoolean = false;
                                                                        disFoodRestaurantBoolean = false;
                                                                        opsionetFilter = 0;
                                                                        opsionetProduktet = 0;
                                                                        booleanMenu = false;
                                                                    }
                                                                }
                                                            } else if (disFoodChoice3 == 2) {
                                                                disFoodBoolean = false;
                                                            } else if (disFoodChoice3 == 3) {
                                                                disFoodBoolean = false;
                                                                disFoodRestaurantBoolean = false;
                                                                opsionetProduktet = 0;
                                                                opsionetFilter = 0;
                                                                booleanMenu = false;
                                                            } else if (disFoodChoice3 == 4) {
                                                                System.exit(0);
                                                            } else {
                                                                System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                            }
                                                        } else if (disFoodChoice2 == 2) {
                                                            disFoodBoolean = false;
                                                        } else if (disFoodChoice2 == 3) {
                                                            disFoodBoolean = false;
                                                            disFoodRestaurantBoolean = false;
                                                            opsionetProduktet = 0;
                                                            opsionetFilter = 0;
                                                            booleanMenu = false;
                                                        } else if (disFoodChoice2 == 4) {
                                                            System.exit(0);
                                                        } else {
                                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                        }
                                                    }
                                                } else if (disFoodChoice.contains("dicounts menu") || disFoodChoice.contains("dicounts") || disFoodChoice.contains("menu")) {
                                                    disFoodRestaurantBoolean = false;
                                                } else if (disFoodChoice.contains("exit")) {
                                                    System.exit(0);
                                                } else {
                                                    System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                                }
                                            }
                                        } else if (disFoodRestaurantChoice.contains("filter menu") || disFoodRestaurantChoice.contains("filter") || disFoodRestaurantChoice.contains("menu")) {
                                            opsionetFilter = 0;
                                        } else if (disFoodRestaurantChoice.contains("exit")) {
                                            System.exit(0);
                                        } else {
                                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                        }
                                    }
                                } else if (opsionetFilter == 3) {
                                    opsionetProduktet = 0;
                                } else if (opsionetFilter == 4) {
                                    System.exit(0);
                                }
                            }
                        } else if (opsionetProduktet == 3) {
                            booleanMenu = false;
                        } else if (opsionetProduktet == 4) {
                            System.exit(0);
                        } else {
                            System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                        }
                    } else if (opsionetMenu == 2) {
                        //cart
                        boolean opsionetMenu2 = true;
                        while (opsionetMenu2) {
                            System.out.println("\n-----------------------------");
                            System.out.println("-------------CART------------");
                            if (!m.cart.isEmpty()) {
                                System.out.println();
                                m.printCart();
                                System.out.println("\nSubtotal: " + m.printCartSubTotal() + m.euros);
                                System.out.println("Delivery Fee: 2" + m.euros);
                                System.out.println("Subtotal: " + (m.printCartSubTotal() + 2) + m.euros);
                                System.out.print("\n|1.Check out| ");
                                System.out.print("|2.Remove Product| ");
                                System.out.print("|3.Menu| ");
                                System.out.print("|4.EXIT|");
                                System.out.print("\n\nSelect Option: ");
                                int opsionCart = scan.nextInt();
                                scan.nextLine();
                                if (opsionCart == 1) {
                                    while (opsionCart == 1) {
                                        if (m.printBill(scan)) {
                                            opsionCart = 0;
                                            opsionetMenu2 = false;
                                            booleanMenu = false;
                                        }
                                    }
                                } else if (opsionCart == 2) {
                                    while (opsionCart == 2) {
                                        System.out.println();
                                        m.printCart();
                                        System.out.println("\nWrite Choice: ");
                                        String produkti = scan.nextLine().toLowerCase();
                                        int count = 0;
                                        for (int i = m.cart.size() - 1; i >= 0; i--) {
                                            if (m.cart.get(i).getName().toLowerCase().contains(produkti)) {
                                                m.cart.remove(i);
                                                count++;
                                            }
                                        }
                                        if (count == 0) {
                                            System.out.println("\nNO PRODUCTS REMOVED!!!");
                                            System.out.println("\nDo you want to try again?! ");
                                            String answer0 = scan.nextLine().toLowerCase();
                                            if (!answer0.equals("yes")) {
                                                opsionCart = 0;
                                            }
                                        } else {
                                            opsionCart = 0;
                                            System.out.println("\nProduct removed.");
                                        }
                                    }
                                } else if (opsionCart == 3) {
                                    opsionetMenu2 = false;
                                    booleanMenu = false;
                                } else if (opsionCart == 4) {
                                    System.exit(0);
                                } else {
                                    System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                }
                            } else {
                                System.out.println("\nCART IS EMPTY!!!");
                                opsionetMenu2 = false;
                                booleanMenu = false;
                            }
                        }
                    } else if (opsionetMenu == 3) {
                        boolean accountBoolean = true;
                        while (accountBoolean) {
                            User tempUser = null;
                            for (User user : klienti) {
                                if (user.getEmail().equals(email)) {
                                    tempUser = user;
                                    break;
                                }
                            }
                            name = tempUser.getEmri();
                            surname = tempUser.getMbiemri();
                            phoneNr = tempUser.getPhoneNr();
                            System.out.println("\n-----------------------------");
                            System.out.println("-----------PROFILE-----------\n");
                            System.out.println("ID: " +id);
                            System.out.println("Name and Surname: " + name.concat(" ").concat(surname));
                            System.out.println("Phone Number: "+phoneNr);
                            System.out.println("Email: " + email);
                            System.out.println("Balance: "+m.balance(tempUser)+ m.euros);

                            System.out.print("\n|1.Make Changes| ");
                            System.out.print("|2.Menu| ");
                            System.out.print("|3.EXIT|");
                            System.out.print("\n\nSelect Choice: ");
                            int profileChoice = scan.nextInt();
                            scan.nextLine();
                            if (profileChoice==1) {
                                while (profileChoice == 1) {
                                    System.out.println("\n-----------------------------");
                                    System.out.println("-------PROFILE CHANGES-------\n");
                                    System.out.print("|1.Change Name| ");
                                    System.out.print("|2.Change Surname| ");
                                    System.out.print("|3.Change Phone Number| ");
                                    System.out.print("|4.Change Email| ");
                                    System.out.print("|5.Profile| ");
                                    System.out.print("|6.EXIT|");
                                    System.out.print("\n\nSelect Choice: ");
                                    int profileChangesChoice = scan.nextInt();
                                    scan.nextLine();
                                    if (profileChangesChoice == 1) {
                                        System.out.println("\n-----------------------------\n");
                                        System.out.print("Enter Name: ");
                                        name = scan.nextLine();
                                        tempUser.setEmri(name);
                                        System.out.println("\nName Changed!!!");
                                    } else if (profileChangesChoice==2) {
                                        System.out.println("\n-----------------------------\n");
                                        System.out.print("Enter Surname: ");
                                        surname = scan.nextLine();
                                        tempUser.setMbiemri(surname);
                                        System.out.println("\nSurname Changed!!!");
                                    } else if (profileChangesChoice==3) {
                                        System.out.println("\n-----------------------------\n");
                                        System.out.print("Enter Phone Number: ");
                                        phoneNr = scan.nextLine();
                                        tempUser.setPhoneNr(phoneNr);
                                        System.out.println("\nPhone Number Changed!!!");
                                    } else if (profileChangesChoice==4) {
                                        boolean emailCheck = true;
                                        while (emailCheck) {
                                        System.out.println("\n-----------------------------\n");
                                        System.out.print("Enter Email: ");
                                        email = scan.nextLine();
                                        boolean emailCheck2 = false;

                                            for (User user : klienti) {
                                                if (user.getEmail().equals(email)) {
                                                    emailCheck2 = true;
                                                }
                                            }
                                            if (emailCheck2){
                                                System.out.println("\nEMAIL ALREADY EXISTS!!!");
                                            }else {
                                                tempUser.setEmail(email);
                                                emailCheck=false;
                                            }
                                        }
                                        System.out.println("\nEmail Changed!!!");
                                    } else if (profileChangesChoice==5){
                                        profileChoice=0;
                                    } else if (profileChangesChoice==6){
                                        System.exit(0);
                                    } else {
                                        System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                                    }
                                }
                            }else if (profileChoice==2){
                                booleanMenu= false;
                                accountBoolean=false;
                            } else if (profileChoice==3) {
                                System.exit(0);
                            } else {
                                System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                            }
                        }
                    }
//                    else if (opsionetMenu == 4) {
//                        //search
//                    }
                    else if (opsionetMenu == 4) {
                        System.out.println("\n-----------------------------");
                        System.out.println("------------ORDERS-----------\n");
                        if (!m.orders.isEmpty()) {
                            m.printOrders();
                        } else {
                            System.out.println("NO PREVIOUS ORDERS!!!");
                        }
                        booleanMenu = false;
                    } else if (opsionetMenu == 5) {
                        System.exit(0);
                    } else {
                        System.out.println("\nWRITE INSIDE RANGE PLS!!!");
                        booleanMenu = false;
                    }
                }
            }
    }
}


