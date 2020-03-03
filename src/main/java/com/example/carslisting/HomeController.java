package com.example.carslisting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.utils.ObjectUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String index(Model model){
        //pull all categories from repo --> template
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cars", carRepository.findAll());

        return "index";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/addCategory")
    public String formCategory(Model model){
        model.addAttribute("category", new Category());
        return "formCategory";
    }

    @PostMapping("/processCategory")
    public String processForm(@Valid Category category, BindingResult result){
        if (result.hasErrors()){
            return "formCategory";
        }
        categoryRepository.save(category);
        return "redirect:/addCar";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/addCar")
    public String formCar(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("categories", categoryRepository.findAll());
        return "formCar";
    }

    @PostMapping("/processCar")
    public String processForm(@Valid @ModelAttribute Car car, BindingResult result, /*@RequestParam("pic") String pic,*/  @RequestParam("category") long id, @RequestParam("file") MultipartFile file){
        if (result.hasErrors()){
            return "formCar";
        }

        if (file.isEmpty()){
//            car.setImage(pic);
        }
        else {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                car.setImage(uploadResult.get("url").toString());
                carRepository.save(car);
            } catch (IOException e){
                e.printStackTrace();
                return "redirect:/addCar";
            }
        }
        Category category = categoryRepository.findById(id).get();
        car.setCategory(category);
        carRepository.save(car);
        return "redirect:/";

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/detail/{id}")
    public String showCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());

        Car car=carRepository.findById(id).get();
//        String pic=car.getImage();                //THE HARD WAY USING REQUESTPARAM
//        model.addAttribute("pic",pic);            //THE HARD WAY USING REQUESTPARAM
        model.addAttribute("car", car);

        model.addAttribute("categories", categoryRepository.findAll());
        return "formCar";
    }

    @RequestMapping("/delete/{id}")
    public String delCar(@PathVariable("id") long id, Model model) {
        carRepository.deleteById(id);
        return "redirect:/";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/category/{id}")
    public String category(@PathVariable("id") long id, Model model) {
        model.addAttribute("category", categoryRepository.findById(id).get());
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "category";
    }


}
