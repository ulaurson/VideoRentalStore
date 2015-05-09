import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Ülari on 8.05.2015.
 */
public class TestClass {

    Customer peeter = new Customer("Peeter", "Kaasik");


    @Test
    public void testAddBonusPoints() {

        assertEquals("Bonus points counts must be 0", 0, peeter.getBonusPoints());

        peeter.addBonusPoints("New release");
        assertEquals("Bonus points counts must be 2", 2, peeter.getBonusPoints());

        peeter.addBonusPoints("Old film");
        assertEquals("Bonus points counts must be 3", 3, peeter.getBonusPoints());
    }

    @Test
    public void testRentFilm() throws Exception {

        Customer reet = new Customer("Reet", "Rebane");
        Customer mari = new Customer("Mari", "Mets");

        Film avengers = new Film("Avengers", "New release");
        Film hulk = new Film("Hulk", "Regular rental");

        assertTrue("Film is in store, value must be true", avengers.isInStore());
        assertEquals("Film is in store and number of days rented must be 0", 0, avengers.getNumberOfDaysRented());
        assertNull("Film has rented to nobody, customer first name must be null ", avengers.getCustomersFirstName());

        avengers.rentFilm(2, reet.getFirstName(), reet.getLastName());
        assertFalse("Film is in not store, value must be false", avengers.isInStore());
        assertEquals("Film is not in store and number of days rented must be 2", 2, avengers.getNumberOfDaysRented());
        assertEquals("Film has rented to customer, customer first name must be 'Reet'", "Reet", avengers.getCustomersFirstName());

        hulk.rentFilm(5, mari.getFirstName(), mari.getLastName());
        assertFalse("Film is in not store, value must be false", hulk.isInStore());
        assertEquals("Film is not in store and number of days rented must be 5", 5, hulk.getNumberOfDaysRented());
        assertEquals("Film has rented to customer, customer last name must be 'Mets'", "Mets", hulk.getCustomersLastName());
    }

    @Test
    public void testReturnFilm() throws Exception {

        Customer reet = new Customer("Reet", "Rebane");
        Film max = new Film("Mad Max", "Old film");

        max.rentFilm(2, reet.getFirstName(), reet.getLastName());
        max.returnFilm();
        assertTrue("Film is in store, value must be true", max.isInStore());
        assertEquals("Film is in store and number of days rented must be 0", 0, max.getNumberOfDaysRented());
        assertNull("Film has rented to nobody, customer last name must be null", max.getCustomersLastName());

    }

    @Test
    public void testChangeFilmType() throws Exception {

        Film avengers = new Film("Avengers", "New release");
        Film hulk = new Film("Hulk", "Regular rental");
        Film max = new Film("Mad Max", "Old film");

        assertEquals("Film type must be 'Old film'", "Old film", max.getFilmType());
        assertEquals("Film type must be 'New release'", "New release", avengers.getFilmType());
        assertEquals("Film type must be 'Regular rental'", "Regular rental", hulk.getFilmType());

        avengers.changeFilmType("Regular rental");
        assertEquals("Film type must be 'Regular film'", "Regular rental", avengers.getFilmType());

        hulk.changeFilmType("Old film");
        assertEquals("Film type must be 'Old film'", "Old film", hulk.getFilmType());
    }

    @Test
    public void testGetPriceOfOneFilm() throws Exception {

        Film avengers = new Film("Avengers", "New release");
        Film hulk = new Film("Hulk", "Regular rental");
        Film max = new Film("Mad Max", "Old film");

        /* Tests of film type "New release". */
        assertEquals("Film price must be 4", 4, avengers.getPriceOfOneFilm(1));
        assertEquals("Film price must be 8", 8, avengers.getPriceOfOneFilm(2));
        assertEquals("Film price must be 40", 40, avengers.getPriceOfOneFilm(10));

        /* Tests of film type "Regular rental". */
        assertEquals("Film price must be 3", 3, hulk.getPriceOfOneFilm(1));
        assertEquals("Film price must be 3", 3, hulk.getPriceOfOneFilm(2));
        assertEquals("Film price must be 3", 3, hulk.getPriceOfOneFilm(3));
        assertEquals("Film price must be 6", 6, hulk.getPriceOfOneFilm(4));
        assertEquals("Film price must be 4", 24, hulk.getPriceOfOneFilm(10));

        /* Tests of film type "Old film". */
        assertEquals("Film price must be 3", 3, max.getPriceOfOneFilm(1));
        assertEquals("Film price must be 3", 3, max.getPriceOfOneFilm(3));
        assertEquals("Film price must be 3", 3, max.getPriceOfOneFilm(5));
        assertEquals("Film price must be 6", 6, max.getPriceOfOneFilm(6));
        assertEquals("Film price must be 18", 18, max.getPriceOfOneFilm(10));

    }

    @Test
    public void testGetPriceOfOneFilmIfLate() throws Exception {

        Film avengers = new Film("Avengers", "New release");
        Film hulk = new Film("Hulk", "Regular rental");
        Film max = new Film("Mad Max", "Old film");

        /* Tests of film type "New release". */
        assertEquals("Film price must be 4", 4, avengers.getPriceOfOneFilmIfLate(1));
        assertEquals("Film price must be 8", 8, avengers.getPriceOfOneFilmIfLate(2));
        assertEquals("Film price must be 40", 40, avengers.getPriceOfOneFilmIfLate(10));

        /* Tests of film type "Regular rental". */
        assertEquals("Film price must be 3", 3, hulk.getPriceOfOneFilmIfLate(1));
        assertEquals("Film price must be 9", 9, hulk.getPriceOfOneFilmIfLate(3));
        assertEquals("Film price must be 30", 30, hulk.getPriceOfOneFilmIfLate(10));

        /* Tests of film type "Old film". */
        assertEquals("Film price must be 3", 3, max.getPriceOfOneFilmIfLate(1));
        assertEquals("Film price must be 9", 9, max.getPriceOfOneFilmIfLate(3));
        assertEquals("Film price must be 15", 15, max.getPriceOfOneFilmIfLate(5));
        assertEquals("Film price must be 30", 30, max.getPriceOfOneFilmIfLate(10));
    }

    @Test
    public void testAddNewFilmToInventory() throws Exception {

        Inventory storeInventory = new Inventory();
        assertEquals("Inventory must be empty, size must be 0", 0, storeInventory.getInventory().size());

        storeInventory.addNewFilmToInventory("Spider man 2", "Regular rental");
        storeInventory.addNewFilmToInventory("Out of africa", "Old film");
        assertEquals("Inventory is not empty, two movies added. Size must be 2", 2, storeInventory.getInventory().size());
        assertTrue("The 'Spider man 2' movie has been added to the inventory index must be >= 0", storeInventory.getFilmPositionInInventoryArray("Spider man 2") >= 0);
        assertTrue("The 'Out of africa' movie has been added to the inventory index must be >= 0", storeInventory.getFilmPositionInInventoryArray("Out of africa") >= 0);
        assertFalse("The 'Aliens' movie is not in the inventory return value must be < 0", storeInventory.getFilmPositionInInventoryArray("Alien") >= 0);

        storeInventory.addNewFilmToInventory("Alien", "Regular rental");
        storeInventory.addNewFilmToInventory("Mad Max", "Regular rental");
        assertEquals("Inventory is not empty, four movies added. Size must be 4", 4, storeInventory.getInventory().size());
        assertTrue("The 'Alien' movie has been added to the inventory index must be >= 0", storeInventory.getFilmPositionInInventoryArray("Alien") >= 0);
        assertTrue("The 'Mad Max' movie has been added to the inventory index must be >= 0", storeInventory.getFilmPositionInInventoryArray("Mad Max") >= 0);
    }

    @Test
    public void testRemoveFilmFromInventory() throws Exception {

        Inventory storeInventory = new Inventory();

        storeInventory.addNewFilmToInventory("Spider man 2", "Regular rental");
        storeInventory.addNewFilmToInventory("Out of africa", "Old film");
        storeInventory.addNewFilmToInventory("Alien", "Regular rental");
        storeInventory.addNewFilmToInventory("Mad Max", "Regular rental");

        storeInventory.removeFilmFromInventory("Spider man 2");
        assertEquals("Inventory is not empty, two movies added. Size must be 3", 3, storeInventory.getInventory().size());
        assertFalse("The 'Spider man 2' movie has been deleted from inventory return value must be < 0", storeInventory.getFilmPositionInInventoryArray("Spider man 2") >= 0);
        assertTrue("The 'Out of africa' is still in the inventory index must be >= 0", storeInventory.getFilmPositionInInventoryArray("Out of africa") >= 0);

        storeInventory.removeFilmFromInventory("Out of africa");
        storeInventory.removeFilmFromInventory("Alien");
        assertEquals("Inventory is not empty, two movies added. Size must be 1", 1, storeInventory.getInventory().size());
        assertFalse("The 'Alien' movie has been deleted from inventory return value must be < 0", storeInventory.getFilmPositionInInventoryArray("Alien") >= 0);

    }

    @Test
    public void testChangeFilmTypeInInventory() throws Exception {

        Inventory storeInventory = new Inventory();

        storeInventory.addNewFilmToInventory("Spider man 2", "Regular rental");
        storeInventory.addNewFilmToInventory("Out of africa", "Old film");
        storeInventory.addNewFilmToInventory("Alien", "Regular rental");
        storeInventory.addNewFilmToInventory("Mad Max", "Regular rental");

        storeInventory.changeFilmTypeInInventory("Spider man 2", "Old film");
        assertEquals("The 'Spider man 2' type has changed. New type 'Old film'", "Old film",
                storeInventory.getInventory().get(storeInventory.getFilmPositionInInventoryArray("Spider man 2")).getFilmType());

        storeInventory.changeFilmTypeInInventory("Alien", "New release");
        assertEquals("The 'Alien' type has changed. New type 'New release'", "New release",
                storeInventory.getInventory().get(storeInventory.getFilmPositionInInventoryArray("Alien")).getFilmType());

        assertEquals("The 'Mad Max' type has not changed. The type must be Regular rental'", "Regular rental",
                storeInventory.getInventory().get(storeInventory.getFilmPositionInInventoryArray("Mad Max")).getFilmType());

    }

    @Test
    public void testRentFilms() throws Exception {

        Inventory storeInventory = new Inventory();

        storeInventory.addNewFilmToInventory("Spider man 2", "Regular rental");
        storeInventory.addNewFilmToInventory("Out of africa", "Old film");
        storeInventory.addNewFilmToInventory("Alien", "Regular rental");
        storeInventory.addNewFilmToInventory("Mad Max", "Regular rental");
        storeInventory.addNewFilmToInventory("Avengers", "New release");

        Customer reet = new Customer("Reet", "Rebane");


        Map<String, Integer> reetRentals = new HashMap<String, Integer>();
        reetRentals.put("Alien", 1);
        reetRentals.put("Spider man 2", 2);
        reetRentals.put("Out of africa", 7);
        reetRentals.put("Avengers", 7);

        storeInventory.rentFilms(reet, reetRentals, false);
        assertEquals("Bonus point count must be 5", 5, reet.getBonusPoints());
        assertFalse("Film 'Avenger' is not in store, value must be false",
                storeInventory.getInventory().get(storeInventory.getFilmPositionInInventoryArray("Avengers")).isInStore());
        assertEquals("Film 'Avenger' is not in store and number of days rented must be 7", 7,
                storeInventory.getInventory().get(storeInventory.getFilmPositionInInventoryArray("Avengers")).getNumberOfDaysRented());
        assertEquals("Film 'Avenger' has rented to customer, customer first name must be 'Reet'", "Reet",
                storeInventory.getInventory().get(storeInventory.getFilmPositionInInventoryArray("Avengers")).getCustomersFirstName());

        assertTrue("Film 'Mad Max' is still in store, value must be true",
                storeInventory.getInventory().get(storeInventory.getFilmPositionInInventoryArray("Mad Max")).isInStore());

    }

    @Test
    public void testReturnFilms() throws Exception {

        Inventory storeInventory = new Inventory();

        storeInventory.addNewFilmToInventory("Spider man 2", "Regular rental");
        storeInventory.addNewFilmToInventory("Out of africa", "Old film");
        storeInventory.addNewFilmToInventory("Alien", "Regular rental");
        storeInventory.addNewFilmToInventory("Mad Max", "Regular rental");
        storeInventory.addNewFilmToInventory("Avengers", "New release");

        Customer reet = new Customer("Reet", "Rebane");

        Map<String, Integer> reetRentals = new HashMap<String, Integer>();
        reetRentals.put("Alien", 1);
        reetRentals.put("Spider man 2", 2);
        reetRentals.put("Out of africa", 7);
        reetRentals.put("Avengers", 7);

        Map<String, Integer> reetReturnedFilms = new HashMap<String, Integer>();
        reetReturnedFilms.put("Alien", 1);
        reetReturnedFilms.put("Spider man 2", 2);
        reetReturnedFilms.put("Out of africa", 7);
        reetReturnedFilms.put("Avengers", 7);

        storeInventory.rentFilms(reet, reetRentals, false);
        storeInventory.returnFilms(reet, reetReturnedFilms);

        assertEquals("Bonus point count must be 5", 5, reet.getBonusPoints());
        assertTrue("Film 'Avenger' is back in store, value must be true",
                storeInventory.getInventory().get(storeInventory.getFilmPositionInInventoryArray("Avengers")).isInStore());
        assertEquals("Film 'Avenger' is in store and number of days rented must be 0", 0,
                storeInventory.getInventory().get(storeInventory.getFilmPositionInInventoryArray("Avengers")).getNumberOfDaysRented());
        assertNull("Film 'Avenger' has returned, customer first name must be 'null'",
                storeInventory.getInventory().get(storeInventory.getFilmPositionInInventoryArray("Avengers")).getCustomersFirstName());

        Map<String, Integer> reetRentals2 = new HashMap<String, Integer>();
        reetRentals2.put("Alien", 1);
        reetRentals2.put("Spider man 2", 2);
        reetRentals2.put("Out of africa", 7);
        reetRentals2.put("Avengers", 1);

        reet.setBonusPoints(50);
        storeInventory.rentFilms(reet, reetRentals2, true);
        assertEquals("Bonus point count must be 30", 30, reet.getBonusPoints());

    }

}
