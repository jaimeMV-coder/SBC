package gov.sandia.jess.example.pricing.model;

public class CreditCard{
    private String name;
    private String type;

    public CreditCard(String name, String type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

}