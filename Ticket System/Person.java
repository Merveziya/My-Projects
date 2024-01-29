public abstract class Person{

    private String firstName;
    private String lastName;
    private String TC;
    private String birthDate;

    public Person(String firstName, String lastName,String TC,String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.TC = TC;
        this.birthDate=birthDate;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getTC(){
        return this.TC;
    }

    public void seTC(String TC){
        this.TC = TC;
    }

    public String getBirthDate(){
        return this.birthDate;
    }


    public static void main(String[] args) {

    }
}