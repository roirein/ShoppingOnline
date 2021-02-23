package shopping;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static String chooseForCase(String choose){

        int index=-1;
        String[] Dic = {"Add WebUser","Exit","Remove WebUser","Login WebUser","Logout WebUser","Make order","Display order","Link Product","Add Product","Delete Product","ShowAllObjects","ShowObjectId" };

        for (int i=0; i<Dic.length; i++){

            if(choose.startsWith(Dic[i])) {
                index = i;
                break;
            }
        }
        if (index != -1)
            return Dic[index];
        else
            return "Default";
    }

    public static void main(String[] args) {
        ShoppingSystem shoppingSystem = new ShoppingSystem();

        boolean flag = true;

        while (flag){

            Scanner myObj = new Scanner(System.in);
            System.out.println("Please enter one of the following commnads:\n1.Add WebUser *Login_id*" +
                    "\n2.Remove WebUser *Login_id*\n3.Login WebUser *Login_id*\n4.Logout WebUser *Login_id*" +
                    "\n5.Make order\n6.Display order\n7.Link Product *Product_name*\n8.Add Product " +
                    "\n9.Delete Product *Product_name*\n10.ShowAllObjects\n11.ShowObjectId *id*\n12.Exit");

            String choose = myObj.nextLine();
            String command = chooseForCase(choose);
            String[] listChoose = choose.split(" ");


            switch (command){
                case "Add WebUser":
                    String id = listChoose[2];
                    // Arg Web User
                    System.out.println("Enter Password");
                    String Password = myObj.nextLine();
                    // Arg Customer
                    System.out.println("Enter country");
                    String country = myObj.nextLine();
                    System.out.println("Enter City");
                    String city = myObj.nextLine();
                    System.out.println("Enter Street");
                    String Street = myObj.nextLine();
                    System.out.println("Enter Phone");
                    String Phone = myObj.nextLine();
                    System.out.println("Enter Email");
                    String Email = myObj.nextLine();
                    // Arg Account
                    System.out.println("Enter Billing Address");
                    String billingAddress = myObj.nextLine();

                    System.out.println("Enter your balance (integer)");
                    String balanceString = myObj.nextLine();
                    System.out.println("Are you a Premium Account? yes/no");
                    String premium_account = myObj.nextLine();
                    premium_account=premium_account.toLowerCase();
                    int balance = Integer.parseInt(balanceString);

                    Boolean premiumAccount;

                    if(premium_account.equals("yes")){
                        premiumAccount=true;
                    }
                    else {
                        premiumAccount = false;
                    }

                    shoppingSystem.addWebUser(id,Password,country,city,balance,Street,Phone,Email,billingAddress,premiumAccount);
                    break;

                case "Remove WebUser":

                    String idRemove = listChoose[2];
                    shoppingSystem.removeWebUser(idRemove);
                    break;

                case "Login WebUser":
                    Boolean connected = false;
                    String loginId = listChoose[2];
                    String loginIdActive = shoppingSystem.getActiveUser();

                    if(loginIdActive.equals("")){
                        while (!connected){
                            System.out.println("Enter Password");
                            String password = myObj.nextLine();
                            connected = shoppingSystem.verifyLogin(loginId,password);
                        }
                    }
                    else if(loginIdActive.equals(loginId)){
                        System.out.println("You are already login");
                    }
                    else {
                        System.out.println("wait for"+ loginIdActive+"to log off");
                    }

                    break;

                case "Logout WebUser":
                    String logOutId = listChoose[2];
                    String loginIdActiveNow = shoppingSystem.getActiveUser();

                    if( loginIdActiveNow.equals(logOutId)){
                        shoppingSystem.logOut(logOutId);
                        System.out.println("Bye bye...");
                      }
                    else if(loginIdActiveNow.equals("")){
                        System.out.println("No one's login");
                    }
                    else {
                        System.out.println("you are wrong in userId");
                    }
                    break;

                case "Make order":
                    shoppingSystem.updateShoppingCartDate();

                    System.out.println("Enter Seller Name");
                    String nameseller = myObj.nextLine();
                    List<Product> listProduct = shoppingSystem.showSellerProduct(nameseller);

                   for (int i=0; i <listProduct.size() ;i++){
                        Product product = listProduct.get(i);
                        System.out.println(product);
                    }

                    Boolean flagProudct=false;

                    while (!flagProudct){
                        System.out.println("please choose a product:");
                        String product =myObj.nextLine();
                        System.out.println("Enter quantity:");
                        String quantityScanner =myObj.nextLine();
                        int quantity = Integer.parseInt(quantityScanner);

                        Product p1 = null;
                        for(int i = 0 ; i< listProduct.size();i++){
                            if (product.equals((listProduct.get(i)).getName())){
                                p1=listProduct.get(i);
                            }

                        }
                        shoppingSystem.addToShoppingCart(p1,quantity);
                        System.out.println("you want more product? Y/N");
                        String nextItem =myObj.nextLine();

                        if (nextItem.equals("N"))
                            flagProudct=true;
                    }
                    Boolean isDelayed = false;
                    System.out.println("enter one of the following:\n" +
                            "DelayedPayment or ImmediatePayment ");
                    String pay = myObj.nextLine();
                    if(pay.equals("DelayedPayment"))
                        isDelayed=true;
                    System.out.println("How many payments would you like?");
                    String numPay = myObj.nextLine();
                    int numOfPayments = Integer.parseInt(numPay);

                    shoppingSystem.makeOrder(isDelayed, numOfPayments);


                    break;

                case "Display order":
                    System.out.println(shoppingSystem.getLastOrder().toString());
                    break;

                case "Link Product":
                    String Product_name = listChoose[2];
                    System.out.println("Enter quantity");
                    String quantity = myObj.nextLine();
                    int quantity1 =Integer.parseInt(quantity);
                    System.out.println("Enter price");
                    String price = myObj.nextLine();
                    int price1 =Integer.parseInt(price);


                    shoppingSystem.addProductToAccount(Product_name,quantity1,price1);
                    break;

                case "Add Product":
                    System.out.println("Enter product name");
                    String NameNewPro = myObj.nextLine();
                    System.out.println("Enter ID");
                    String productID = myObj.nextLine();
                    System.out.println("Enter Supplier");
                    String SupplierName = myObj.nextLine();
                    System.out.println("Enter SupplierID");
                    String SupplierID = myObj.nextLine();

                    shoppingSystem.addProduct(NameNewPro,productID,SupplierName,SupplierID);
                    break;
                case "Delete Product":
                    String ProductNameDelete = listChoose[2];
                    shoppingSystem.DeleteProduct(ProductNameDelete);
                    System.out.println("Product deleted");
                    break;
                case "ShowAllObjects":
                    shoppingSystem.showAllObjects();
                    break;
                case "ShowObjectId":
                    String idObjectString = listChoose[1];
                    int idObject = Integer.parseInt(idObjectString);
                    shoppingSystem.showObjectId(idObject);
                    break;
                case "Exit":
                    flag = false;
                    break;
                default:
                    System.out.println("Please try again");
            }
        }


    }
}

