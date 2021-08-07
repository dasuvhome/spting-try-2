package ru.suvorov.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.suvorov.models.Review;
import ru.suvorov.models.Role;
import ru.suvorov.models.User;
import ru.suvorov.repo.ReviewRepository;
import ru.suvorov.repo.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private  UserRepository userRepository;

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

    @GetMapping("/reviews/{id}")
    public String reviewInfo(@PathVariable(value = "id") long reviewId, Model model) {
       Optional<Review> review = reviewRepository.findById(reviewId);
        ArrayList<Review> result = new ArrayList<>();
         review.ifPresent(result::add);
        System.out.println(result);
         model.addAttribute("result", result);
         return "review-info";
    }
    @GetMapping("/reviews/{id}/update")
    public String updateReviews(@PathVariable(value = "id") long reviewId, Model model){
        Optional<Review> review = reviewRepository.findById(reviewId);
        ArrayList<Review> result = new ArrayList<>();
        review.ifPresent(result::add);
       model.addAttribute("result", result);

        return "review-update";
    }
    @PostMapping("/reviews/{id}/update")
    public String reviewsUpdateForm(@PathVariable(value = "id") long reviewId, @RequestParam String title,
                            @RequestParam String text,Model model) throws ClassNotFoundException {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ClassNotFoundException());
        review.setTitle(title);
        review.setText(text);
        model.addAttribute("reviews", review);
        reviewRepository.save(review);
        return "redirect:/reviews/" + reviewId;
    }


    @PostMapping("/reviews/{id}/delete")
    public String reviewsDelete(@PathVariable(value = "id") long reviewId, Model model) throws ClassNotFoundException {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ClassNotFoundException());
           reviewRepository.delete(review);

      return "redirect:/reviews";
    }

       @GetMapping("/reg")
       public String reg(){
        return "reg";
       }

       @PostMapping("/reg")
        public String addUser(User user, Model model){
         user.setEnabled(true);
         user.setRoles(Collections.singleton(Role.USER));

         userRepository.save(user);
         return "redirect:/login";
       }

}
