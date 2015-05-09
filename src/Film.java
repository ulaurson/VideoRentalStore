/**
 * Created by Ülari on 8.05.2015.
 */
public class Film {

    private String filmName;
    private String filmType;
    private String customersFirstName;
    private String customersLastName;
    private boolean inStore;
    private int numberOfDaysRented = 0;

    private int PREMIUM_PRICE = 4;
    private int BASIC_PRICE = 3;

    public Film(String filmName, String filmType){
        this.filmName = filmName;
        this.filmType = filmType;
        this.inStore = true;
    }

    public void rentFilm(int numberOfDaysRented, String customersFirstName, String customersLastName){
        this.numberOfDaysRented = numberOfDaysRented;
        this.customersFirstName = customersFirstName;
        this.customersLastName = customersLastName;
        this.inStore = false;
    }

    public void returnFilm(){
        this.numberOfDaysRented = 0;
        this.customersFirstName = null;
        this.customersLastName = null;
        this.inStore = true;
    }

    public void changeFilmType(String newFilmType){
        if(newFilmType.equals("New release") || newFilmType.equals("Regular rental") || newFilmType.equals("Old film") ){
            this.filmType = newFilmType;
        }
        else{
            System.out.print("No such film type exists! Write proper film type!");
        }

    }

    public int getPriceOfOneFilm(int daysOfRent){
        if(getFilmType().equals("New release")){
            return daysOfRent * PREMIUM_PRICE;
        }
        else if(getFilmType().equals("Regular rental")){
            if(daysOfRent-3 < 1){
                return BASIC_PRICE;
            }
            else{
                return BASIC_PRICE + ((daysOfRent-3)*BASIC_PRICE);
            }

        }
        else{
            if(daysOfRent-5 < 1){
                return BASIC_PRICE;
            }
            else{
                return BASIC_PRICE + ((daysOfRent-5)*BASIC_PRICE);
            }
        }
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    public boolean isInStore() {
        return inStore;
    }

    public void setInStore(boolean inStore) {
        this.inStore = inStore;
    }

    public String getCustomersFirstName() {
        return customersFirstName;
    }

    public void setCustomersFirstName(String customersFirstName) {
        this.customersFirstName = customersFirstName;
    }

    public String getCustomersLastName() {
        return customersLastName;
    }

    public void setCustomersLastName(String customersLastName) {
        this.customersLastName = customersLastName;
    }

    public int getNumberOfDaysRented() {
        return numberOfDaysRented;
    }

    public void setNumberOfDaysRented(int numberOfDaysRented) {
        this.numberOfDaysRented = numberOfDaysRented;
    }

    public int getPriceOfOneFilmIfLate(int numberOfExtraDays) {
        if(getFilmType().equals("New release")){
            return numberOfExtraDays * PREMIUM_PRICE;
        }
        else{
            return numberOfExtraDays * BASIC_PRICE;
        }
    }
}
