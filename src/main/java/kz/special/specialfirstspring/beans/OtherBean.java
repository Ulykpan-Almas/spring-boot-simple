package kz.special.specialfirstspring.beans;

public class OtherBean {

    private  String name;
    private int age;

    public OtherBean(){
        this.name="Almas";
        this.age=20;
    }
    public OtherBean (String name, int age){
        this.name=name;
        this.age=age;
    }

    public String getText(){
        return "Now text is "+this.name + this.age ;
    }

}
