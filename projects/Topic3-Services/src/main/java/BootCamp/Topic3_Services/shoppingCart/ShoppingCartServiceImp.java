package BootCamp.Topic3_Services.shoppingCart;

import java.util.ArrayList;
import java.util.List;

import BootCamp.Topic3_Services.users.User;

public class ShoppingCartServiceImp implements ShoppingCartService {

    User user;
    List<String> contents;

    public void initialize(User user){
        this.user = user;
        this.contents = new ArrayList<String>();
    }

    public void addItem(String itemName) {
        contents.add(itemName);
    }

    public void removeItem(String itemName) throws ShoppingCartException {
        boolean result = contents.remove(itemName);
        if (result == false) {
            throw new ShoppingCartException(itemName + " not in cart.");
        }
    }

    public List<String> getContents() {
        return contents;
    }

   
}
