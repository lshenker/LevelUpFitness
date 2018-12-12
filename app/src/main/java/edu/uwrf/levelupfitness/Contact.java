package edu.uwrf.levelupfitness;

public class Contact {

    public String id, userName, pass, firstName, lastName, height,
            weight, phone;

    // setters
    public void setId(String id){
        this.id = id;
    }
    public void setUsername(String userName){
        this.userName = userName;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setHeight(String height){
        this.height = height;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }


    // getters
    public String getId(){
        return this.id;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getPass(){
        return this.pass;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getHeight(){
        return this.height;
    }
    public String getWeight(){
        return this.weight;
    }
    public String getPhone(){
        return this.phone;
    }

}
