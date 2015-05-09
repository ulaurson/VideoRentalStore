import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ülari on 8.05.2015.
 */
public class Inventory {

    ArrayList<Film> inventory = null;

    public Inventory(){
        inventory = new ArrayList<Film>();
    }

    public void addNewFilmToInventory(String filmName, String filmType){
        inventory.add(new Film(filmName, filmType));
    }

    public void removeFilmFromInventory(String filmName){
        int filmIndexInInventoryArray = getFilmPositionInInventoryArray(filmName);
        if(filmIndexInInventoryArray >= 0){
            inventory.remove(inventory.get(filmIndexInInventoryArray));
        }
        else{
            System.out.println("No such film in inventory!");
        }
    }

    /* Returns film position in inventory array, if not in list then returns -1. */
    public int getFilmPositionInInventoryArray(String filmName){
        for(int index = 0; index < inventory.size(); index++){
            if(inventory.get(index).getFilmName().equals(filmName)){
                return index;
            }
        }
        return -1;
    }

    public void changeFilmTypeInInventory(String filmName, String newFilmType){
        if(newFilmType.equals("New release") || newFilmType.equals("Regular rental") || newFilmType.equals("Old film") ){
            int filmIndexInInventoryArray = getFilmPositionInInventoryArray(filmName);
            if(filmIndexInInventoryArray >= 0){
                inventory.get(filmIndexInInventoryArray).changeFilmType(newFilmType);
            }
            else{
                System.out.println("No such film in inventory!");
            }
        }
        else{
            System.out.println("No such film type exists! Write proper film type!");
        }
    }

    public void listAllFilms(){
        System.out.println("\nAll films:");
        for(Film film : inventory){
            System.out.println(film.getFilmName() + " (" + film.getFilmType() + ")");
        }
    }

    public void listAllNotRentedFilms(){
        System.out.println("\nAll films in store:");
        for(Film film : inventory){
            if(film.isInStore()){
                System.out.println(film.getFilmName() + " (" + film.getFilmType() + ")");
            }
        }
    }


    public void rentFilms(Customer customer, Map<String, Integer> rentals, boolean useBonusPoints) {
        System.out.println("\nRented films: ");
        int bonusPointsBeforeRenting = customer.getBonusPoints();
        int totalCost = 0;
        for(String filmName : rentals.keySet()){
            int filmIndexInInventoryArray = getFilmPositionInInventoryArray(filmName);
            if(filmIndexInInventoryArray >= 0){
                Film film = inventory.get(filmIndexInInventoryArray);
                int numberOfDaysRented = rentals.get(filmName);
                if(film.isInStore()){
                    customer.addBonusPoints(film.getFilmType());
                    int oneFilmPrice = 0;
                    if(useBonusPoints && film.getFilmType().equals("New release") && bonusPointsBeforeRenting >= 25){
                        int freeDays = useBonusPointsToRentFilm(customer, bonusPointsBeforeRenting, numberOfDaysRented);
                        oneFilmPrice = film.getPriceOfOneFilm(numberOfDaysRented - freeDays);
                        bonusPointsBeforeRenting -= freeDays*25;
                        System.out.println(filmName + " ("+ film.getFilmType() +") "
                                + numberOfDaysRented + " days " + "(Paid " + freeDays + " days with " + freeDays*25 +
                                " Bonus points) " + oneFilmPrice + " EUR");
                    }
                    else{
                        oneFilmPrice = film.getPriceOfOneFilm(numberOfDaysRented);
                        System.out.println(filmName + " (" + film.getFilmType() + ") "
                                + numberOfDaysRented + " days " + oneFilmPrice + " EUR");
                    }
                    totalCost += oneFilmPrice;
                    film.rentFilm(numberOfDaysRented, customer.getFirstName(), customer.getLastName());
                }
                else{
                    System.out.println(filmName + " is not available!");
                }
            }
            else{
                System.out.println("No " + filmName +" film in inventory!");
            }
        }
        System.out.println("Total price: " + totalCost + " EUR");
        if(useBonusPoints){
            System.out.println("\nRemaining Bonus points: " + customer.getBonusPoints());
        }
    }

    public void returnFilms(Customer customer, Map<String, Integer> returnedFilms) {
        System.out.println("\nFilms which were returned late: ");
        int totalLateCost = 0;
        for(String filmName : returnedFilms.keySet()){
            int filmIndexInInventoryArray = getFilmPositionInInventoryArray(filmName);
            if(filmIndexInInventoryArray >= 0){
                Film film = inventory.get(filmIndexInInventoryArray);
                if(customer.getFirstName().equals(film.getCustomersFirstName()) &&
                        customer.getLastName().equals(film.getCustomersLastName())){
                    int numberOfDaysRented = returnedFilms.get(filmName);
                    int extraDays = numberOfDaysRented - film.getNumberOfDaysRented();
                    if(extraDays > 0){
                        int oneFilmPrice = film.getPriceOfOneFilmIfLate(extraDays);
                        totalLateCost += oneFilmPrice;
                        System.out.println(filmName + " ("+ film.getFilmType() +") "
                                + extraDays + " extra days " + oneFilmPrice + " EUR");
                    }
                    film.returnFilm();                }
            }
            else{
                System.out.println("No " + filmName +" film in inventory!");
            }
        }
        System.out.println("Total late charge: " + totalLateCost + " EUR");
    }

    /* Returns how many free days customer gets, based on he or she Bonus points */
    private int useBonusPointsToRentFilm(Customer customer, int bonusPointsBeforeRenting, int numberOfDaysRented) {
        int currentBonusPoints = customer.getBonusPoints();
        int howManyFreeDays = bonusPointsBeforeRenting/25;
        if(howManyFreeDays - numberOfDaysRented >= 0){
            customer.setBonusPoints(currentBonusPoints - (numberOfDaysRented * 25));
            return numberOfDaysRented;
        }
        else {
            customer.setBonusPoints(customer.getBonusPoints() - (howManyFreeDays * 25));
            return howManyFreeDays;
        }
    }

    public ArrayList<Film> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Film> inventory) {
        this.inventory = inventory;
    }
}
