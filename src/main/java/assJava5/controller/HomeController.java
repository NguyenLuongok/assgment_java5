package assJava5.controller;

import assJava5.model.Bill;
import assJava5.model.Item;
import assJava5.model.Products;
import assJava5.repository.ProductPagingAndSortingRepository;
import assJava5.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductPagingAndSortingRepository productPagingAndSortingRepository;

    @GetMapping("/")
    public ModelAndView viewIndex(@PageableDefault(size = 8) Pageable pageable,HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/index");
        Page<Products> page = productPagingAndSortingRepository.findAll(pageable);
        modelAndView.addObject("page", page);
        if(session.getAttribute("cart") != null){
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            double total = 0;
            for (Item item : cart) {
                total += item.getProduct().getGiaSP() * item.getQuantity();
            }
            modelAndView.addObject("cart",cart);
            modelAndView.addObject("sl",cart.size());
            modelAndView.addObject("total", total);
        }
        return modelAndView;
    }
    @GetMapping("view-product")
    public ModelAndView viewProduct(@PageableDefault(size = 12) Pageable pageable, HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/products");
        Page<Products> page = productPagingAndSortingRepository.findAll(pageable);
        modelAndView.addObject("page", page);
        if(session.getAttribute("cart") != null){
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            double total = 0;
            for (Item item : cart) {
                total += item.getProduct().getGiaSP() * item.getQuantity();
            }
            modelAndView.addObject("cart",cart);
            modelAndView.addObject("sl",cart.size());
            modelAndView.addObject("total", total);
        }
        return modelAndView;
    }
    @GetMapping("view-single/{id}")
    public ModelAndView viewSingle(@PathVariable("id") Long id,HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/single");
        modelAndView.addObject("singleProduct",productsService.findById(id));
        if(session.getAttribute("cart") != null){
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            double total = 0;
            for (Item item : cart) {
                total += item.getProduct().getGiaSP() * item.getQuantity();
            }
            modelAndView.addObject("cart",cart);
            modelAndView.addObject("sl",cart.size());
            modelAndView.addObject("total", total);
        }
        return modelAndView;
    }
    @GetMapping("view-contact")
    public ModelAndView viewContact(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/contact");
        if(session.getAttribute("cart") != null){
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            double total = 0;
            for (Item item : cart) {
                total += item.getProduct().getGiaSP() * item.getQuantity();
            }
            modelAndView.addObject("cart",cart);
            modelAndView.addObject("sl",cart.size());
            modelAndView.addObject("total", total);
        }
        return modelAndView;
    }
    @GetMapping("view-cart")
    public ModelAndView viewCart(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/cart");
        if(session.getAttribute("cart") != null){
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            double total = 0;
            for (Item item : cart) {
                total += item.getProduct().getGiaSP() * item.getQuantity();
            }
            modelAndView.addObject("cart",cart);
            modelAndView.addObject("sl",cart.size());
            modelAndView.addObject("total", total);
        }
        return modelAndView;
    }
    @GetMapping("view-order")
    public ModelAndView viewOder(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/order");
        modelAndView.addObject("bill",new Bill());
        if(session.getAttribute("cart") != null){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double total = 0;
        for (Item item : cart) {
            total += item.getProduct().getGiaSP() * item.getQuantity();
        }
        modelAndView.addObject("cart",cart);
        modelAndView.addObject("sl",cart.size());
        modelAndView.addObject("total", total);
        }
        return modelAndView;
    }

    @PostMapping("search-home")
    public ModelAndView searchHome(@RequestParam("search") String value,HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/index");
        if(session.getAttribute("cart") != null){
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            double total = 0;
            for (Item item : cart) {
                total += item.getProduct().getGiaSP() * item.getQuantity();
            }
            modelAndView.addObject("cart",cart);
            modelAndView.addObject("sl",cart.size());
            modelAndView.addObject("total", total);
        }
        modelAndView.addObject("page",productsService.findByNameSession(value));
        return modelAndView;
    }
}
