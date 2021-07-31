package ru.suvorov.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.suvorov.models.Review;
import ru.suvorov.repo.ReviewRepository;

@Controller
public class MainController {
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public String home (@RequestParam(name="name", required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name",  name);
        return "home";
    }

    @GetMapping("/about")
    public String about (@RequestParam(name="name", required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("title",  "Страница про нас");
        return "about";
    }
    @GetMapping("/reviews")
    public String reviews (Model model){

        model.addAttribute("title",  "Страница с отзывами");
        Iterable<Review> reviews= reviewRepository.findAll();

            model.addAttribute("reviews", reviews);

        return "reviews";
    }

    @PostMapping("/reviews-add")
    public String reviewsAdd(@RequestParam String title, @RequestParam String text, Model model){
        Review review = new Review(title, text);
        System.out.println(review.toString());
        reviewRepository.save(review);

        return "redirect:/reviews";
    }
}
