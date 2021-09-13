package kz.special.specialfirstspring.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TestBean {
    private String text;
    public TestBean(){
        this.text = "Hello First Bean";
    }
    public String getText(){
        return "Now text is : "+this.text;
    }

    public  void setText(String text){
        this.text=text;
    }
}
