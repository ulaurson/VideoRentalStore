import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ülari on 8.05.2015.
 */
public class Main {
    public static void main(String[] args) {

        Inventory storeInventory = new Inventory();

        /* Adding new films to inventory. */
        storeInventory.addNewFilmToInventory("Matrix 11", "New release");
        storeInventory.addNewFilmToInventory("Avengers", "New release");
        storeInventory.addNewFilmToInventory("Spider man", "Regular rental");
        storeInventory.addNewFilmToInventory("Spider man 2", "Regular rental");
        storeInventory.addNewFilmToInventory("Out of africa", "Old film");
        storeInventory.addNewFilmToInventory("Alien", "Regular rental");
        storeInventory.addNewFilmToInventory("Mad Max", "Regular rental");

        System.out.println("All the films are in both list:");

        /* List all films in inventory. */
        storeInventory.listAllFilms();

        /* List all films in inventory, that are not rented at the moment. */
        storeInventory.listAllNotRentedFilms();

        System.out.println("\nThe film 'Alien' type has changed:");

        /* Change film type. */
        storeInventory.changeFilmTypeInInventory("Alien", "Old film");

        storeInventory.listAllFilms();

        System.out.println("\nThe film 'Alien' has deleted from inventory:");

        /* Remove film from inventory. */
        storeInventory.removeFilmFromInventory("Alien");

        storeInventory.listAllFilms();

        /* Making new customer. */
        Customer jaak = new Customer("Jaak", "Mets");

        /* Making HashMap of films that jaak wants to rent.
            Key is the movie name and value is how many days customer want to rent that film.
         */
        Map<String, Integer> jaakRentals = new HashMap<String, Integer>();
        jaakRentals.put("Matrix 11", 1);
        jaakRentals.put("Avengers", 1);
        jaakRentals.put("Spider man", 5);
        jaakRentals.put("Spider man 2", 2);
        jaakRentals.put("Out of africa", 7);

        System.out.println("\nThe price of rentals and total price:");

        /* Jaak renting the movies. First argument is the customers object
            and second argument is HashMap of movies that customer wants to rent.
            Boolean argument determines, if customer want use bonus points.
            If false, then he/she does not use the bonus points.
            If true, then customer wants to use bonus points.
         */
        storeInventory.rentFilms(jaak, jaakRentals, false);

        storeInventory.listAllFilms();

        System.out.println("\nNot all the films are in store:");

        storeInventory.listAllNotRentedFilms();


        /* Making HashMap of films that jaak wants to return.
            Key is the movie name and value is how many days customer held the film.
         */
        Map<String, Integer> jaakReturnedFilms = new HashMap<String, Integer>();
        jaakReturnedFilms.put("Matrix 11", 3);
        jaakReturnedFilms.put("Spider man", 6);
        jaakReturnedFilms.put("Avengers", 1);
        jaakReturnedFilms.put("Spider man 2", 2);
        jaakReturnedFilms.put("Out of africa", 7);

        System.out.println("\nShows which films were returned late and how much money customer has to pay:");

        /* Jaak returning the movies. First argument is the customers object
            and second argument is HashMap of movies that customer wants to return.
         */
        storeInventory.returnFilms(jaak, jaakReturnedFilms);

        storeInventory.listAllFilms();

        System.out.println("\nAll the films are in the store:");

        storeInventory.listAllNotRentedFilms();

        /* Setting Bonus points to 25. */
        jaak.setBonusPoints(50);

        Map<String, Integer> jaakRentals2 = new HashMap<String, Integer>();
        jaakRentals2.put("Matrix 11", 1);
        jaakRentals2.put("Avengers", 1);
        jaakRentals2.put("Out of africa", 7);

        System.out.println("\nUsing Bonus points to pay for renting:");

        /* Jaak uses bonus points to pay for the films. */
        storeInventory.rentFilms(jaak, jaakRentals2, true);

        storeInventory.listAllFilms();
        storeInventory.listAllNotRentedFilms();

        Map<String, Integer> jaakReturnedFilms2 = new HashMap<String, Integer>();
        jaakReturnedFilms2.put("Matrix 11", 1);
        jaakReturnedFilms2.put("Avengers", 1);
        jaakReturnedFilms2.put("Out of africa", 7);

        storeInventory.returnFilms(jaak, jaakReturnedFilms2);

        storeInventory.listAllFilms();
        storeInventory.listAllNotRentedFilms();


        /* Other functions that are used only inside other functions. */

        System.out.println("\nJaak bonus before and after adding new bonus points");
        System.out.println(jaak.getBonusPoints());

        /* Adds 2 bonus points if film type is "New release" otherwise adds 1 points. */
        jaak.addBonusPoints("New release");

        System.out.println(jaak.getBonusPoints());

        Film matrix = new Film("Matrix", "Old film");

        System.out.println("\nShows how film variables change, if customer rents a film: ");
        System.out.println("Customers (who rent this movie) first name: " + matrix.getCustomersFirstName());
        System.out.println("Customers (who rent this movie) last name: " + matrix.getCustomersLastName());
        System.out.println("Film is in store: " + matrix.getNumberOfDaysRented());
        System.out.println("How many days customer rented the movie: " + matrix.getNumberOfDaysRented());

        /* Jaak rents one movie.
            First arguments is how many days customers wants to rent the film.
         */
        matrix.rentFilm(1, jaak.getFirstName(), jaak.getLastName());

        System.out.println("\nCustomers (who rent this movie) first name: " + matrix.getCustomersFirstName());
        System.out.println("Customers (who rent this movie) last name: " + matrix.getCustomersLastName());
        System.out.println("Film is in store: " + matrix.getNumberOfDaysRented());
        System.out.println("How many days customer rented the movie: " + matrix.getNumberOfDaysRented());

        System.out.println("\nThe film is returned to the inventory:");

        /* The film is returned to the inventory. */
        matrix.returnFilm();

        System.out.println("\nCustomers (who rent this movie) first name: " + matrix.getCustomersFirstName());
        System.out.println("Customers (who rent this movie) last name: " + matrix.getCustomersLastName());
        System.out.println("Film is in store: " + matrix.getNumberOfDaysRented());
        System.out.println("How many days customer rented the movie: " + matrix.getNumberOfDaysRented());

        /* Gets the total price of one movie.
            Argument indicates, how many days customer rented the film.
         */
        System.out.println("\nHow much cost renting the film: " + matrix.getPriceOfOneFilm(7));

        /* Gets the price, if the film is returned late.
            Argument indicates, how many extra days customer rented the film.
         */
        System.out.println("\nHow much cost renting the film: " + matrix.getPriceOfOneFilmIfLate(2));
    }


}
