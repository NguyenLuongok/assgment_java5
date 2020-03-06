package assJava5.controller;

import assJava5.model.Item;
import assJava5.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class CartController {

    @Autowired
    private ProductsService productsService;


    @GetMapping("add-to-cart/{name}")
    public ModelAndView addToCart(@PathVariable("name") String name, HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/cart");
        if(session.getAttribute("cart")==null){
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(productsService.findByName(name), 1));
            session.setAttribute("cart", cart);
        }
        else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = this.exists(name, cart);
            if (index == -1) {
                cart.add(new Item(productsService.findByName(name), 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double total = 0;
        for (Item item : cart) {
            total += item.getProduct().getGiaSP() * item.getQuantity();
        }
        modelAndView.addObject("sl",cart.size());
        modelAndView.addObject("cart",cart);
        modelAndView.addObject("total", total);
        return modelAndView;
    }

    @PostMapping("add-to-cart-single/{name}")
    public ModelAndView addToCartToSingle(@PathVariable("name") String name,@RequestParam("soLuong") int sl, HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/cart");
        if(session.getAttribute("cart")==null){
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(productsService.findByName(name), sl));
            session.setAttribute("cart", cart);
        }
        else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = this.exists(name, cart);
            if (index == -1) {
                cart.add(new Item(productsService.findByName(name), sl));
            } else {
                int quantity = cart.get(index).getQuantity() + sl;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double total = 0;
        for (Item item : cart) {
            total += item.getProduct().getGiaSP() * item.getQuantity();
        }
        modelAndView.addObject("sl",cart.size());
        modelAndView.addObject("cart",cart);
        modelAndView.addObject("total", total);
        return modelAndView;
    }

    @GetMapping("remove-cart/{name}")
    public ModelAndView removeCart(@PathVariable("name") String name,HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/cart");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = this.exists(name, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        double total = 0;
        for (Item item : cart) {
            total += item.getProduct().getGiaSP() * item.getQuantity();
        }
        modelAndView.addObject("sl",cart.size());
        modelAndView.addObject("cart",cart);
        modelAndView.addObject("total", total);
        return modelAndView;
    }

    @GetMapping("edit-cart/{name}")
    public ModelAndView editCart(@PathVariable("name") String name,@RequestParam("count") int count,HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/cart");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = this.exists(name,cart);
        cart.get(index).setQuantity(count);
        double total = 0;
        for (Item item : cart) {
            total += item.getProduct().getGiaSP() * item.getQuantity();
        }
        modelAndView.addObject("sl",cart.size());
        modelAndView.addObject("cart",cart);
        modelAndView.addObject("total", total);
        return modelAndView;
    }

    private int exists(String id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getTenSP().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
}
