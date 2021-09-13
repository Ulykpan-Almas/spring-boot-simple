package kz.special.specialfirstspring.controllers;

import kz.special.specialfirstspring.beans.OtherBean;
import kz.special.specialfirstspring.beans.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;

@Controller
@RequestMapping(value = "/admin")
public class OtherController {


    @Autowired
    private TestBean testBean;

    @Autowired
    private OtherBean otherBean;

    @GetMapping(value = "/index")
    public String index(Model model){

        String text = testBean.getText();
        model.addAttribute("text",text);

        String otherText = otherBean.getText();
        model.addAttribute("otherText",otherText);

        return "index";
    }
}
