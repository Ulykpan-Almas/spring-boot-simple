package kz.special.specialfirstspring.controllers;

import kz.special.specialfirstspring.beans.OtherBean;
import kz.special.specialfirstspring.beans.TestBean;
import kz.special.specialfirstspring.db.DBManager;
import kz.special.specialfirstspring.entites.Categories;
import kz.special.specialfirstspring.entites.Countries;
import kz.special.specialfirstspring.entites.Foods;
import kz.special.specialfirstspring.entites.Items;
import kz.special.specialfirstspring.repository.CategoryRepository;
import kz.special.specialfirstspring.repository.CountryRepository;
import kz.special.specialfirstspring.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller

public class HomeController {

    @Autowired
    private FoodRepository foodRepository;


    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CategoryRepository categoryRepository;




    @GetMapping(value = "/")
    public String index(Model model){

        List <Foods> foods = foodRepository.findAllByPriceGreaterThanEqualAndWeightGreaterThanEqual(0,0);
        model.addAttribute("foods",foods);

        List<Countries> countries = countryRepository.findAll();
        model.addAttribute("countries",countries);


        return "index";
    }


    @PostMapping(value = "/addfood")
    public String addfood(@RequestParam(name = "name") String name,
                          @RequestParam(name = "price") int price,
                          @RequestParam(name = "weight") double weight,
                          @RequestParam(name = "country_id") Long countryId){


        Optional<Countries> opt = countryRepository.findById(countryId);
        if(opt.isPresent()){
            Countries country = opt.get();

            Foods foods = new Foods();
            foods.setName(name);
            foods.setPrice(price);
            foods.setWeight(weight);
            foods.setAddedDate(new Date());
            foods.setCountry(country);

            foodRepository.save(foods);
        }

        return "redirect:/";
    }



    @GetMapping(value = "/Details/{foodid}")
    public String details(@PathVariable(name = "foodid") Long id,Model model){


        Foods food = foodRepository.findByIdAndPriceIsGreaterThanEqualAndWeightIsGreaterThanEqual(id,0,0);
        model.addAttribute("food",food);

        List<Countries> countries = countryRepository.findAll();
        model.addAttribute("countries",countries);

        List<Categories> categories  = categoryRepository.findAll();

        if (food.getCategories()!=null){
            categories.removeAll(food.getCategories());
        }

        model.addAttribute("categories",categories);

        return "Details";
    }




    @PostMapping(value = "/assignfood")
    public String assignfood(@RequestParam(name = "category_id") Long category_id,
                          @RequestParam(name = "food_id") Long food_id){


        Optional<Categories> opt = categoryRepository.findById(category_id);
        if(opt.isPresent()) {
            Categories cat = opt.get();
            Foods food = foodRepository.findByIdAndPriceIsGreaterThanEqualAndWeightIsGreaterThanEqual(food_id, 0, 0);

            if (food != null) {
                List<Categories> categories = food.getCategories();
                if (categories == null) {
                    categories = new ArrayList<>();
                }

                categories.add(cat);
                food.setCategories(categories);

                foodRepository.save(food);
                return "redirect:/Details/" + food_id + "#removeTable";
            }


        }
        return "redirect:/";
    }





    @PostMapping(value = "/removefood")
    public String removefood(@RequestParam(name = "category_id") Long category_id,
                             @RequestParam(name = "food_id") Long food_id){


        Optional<Categories> opt = categoryRepository.findById(category_id);
        if(opt.isPresent()) {
            Categories cat = opt.get();
            Foods food = foodRepository.findByIdAndPriceIsGreaterThanEqualAndWeightIsGreaterThanEqual(food_id, 0, 0);

            if (food != null) {
                List<Categories> categories = food.getCategories();
                if (categories == null) {
                    categories = new ArrayList<>();
                }

                categories.remove(cat);
                food.setCategories(categories);

                foodRepository.save(food);
                return "redirect:/Details/" + food_id + "#removeTable";
            }


        }
        return "redirect:/";
    }






    @PostMapping(value = "/savefood")
    public String saveFood(@RequestParam(name = "id") Long id,
                          @RequestParam(name = "name") String name,
                          @RequestParam(name = "price") int price,
                          @RequestParam(name = "weight") double weight,
                           @RequestParam(name = "country_id") Long countryId){


        Optional<Countries> opt = countryRepository.findById(countryId);
        if(opt.isPresent()) {
            Countries country = opt.get();


            Foods foods = foodRepository.findByIdAndPriceIsGreaterThanEqualAndWeightIsGreaterThanEqual(id, 0, 0);
            if (foods != null) {
                foods.setName(name);
                foods.setPrice(price);
                foods.setWeight(weight);
                foods.setCountry(country);

                foodRepository.save(foods);


            }
        }

        return "redirect:/";

    }



    @PostMapping(value = "/deleteFood")
    public String deleteFood(@RequestParam(name = "id") Long id){
        Foods foods = foodRepository.findByIdAndPriceIsGreaterThanEqualAndWeightIsGreaterThanEqual(id,0,0);
        if(foods!=null){
            foodRepository.delete(foods);
        }
        return "redirect:/";
    }

}
