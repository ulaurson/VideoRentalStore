/**
 * Created by Ülari on 8.05.2015.
 */
public class Customer {

    private String firstName;
    private String lastName;
    private int bonusPoints;

    public Customer(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.bonusPoints = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public void addBonusPoints(String filmType) {
        if(filmType.equals("New release")){
            bonusPoints += 2;
        }
        else{
            bonusPoints += 1;
        }
    }
}
