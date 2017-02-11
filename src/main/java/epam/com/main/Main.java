package epam.com.main;

import epam.com.services.menucrudservice.MenuCrudService;
import epam.com.services.menucrudservice.impl.MenuCrudServiceImpl;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        MenuCrudService menuCrudService = new MenuCrudServiceImpl();
        menuCrudService.menuCrudOperations();
    }

}
