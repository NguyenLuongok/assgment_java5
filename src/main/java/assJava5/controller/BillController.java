package assJava5.controller;

import assJava5.model.Bill;
import assJava5.model.Item;
import assJava5.repository.ProductPagingAndSortingRepository;
import assJava5.service.BillService;
import assJava5.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class BillController {

    @Autowired
    private ProductPagingAndSortingRepository productPagingAndSortingRepository;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private BillService billService;

//    @GetMapping("buy-now/{id}/{soLuong}")
//    public ModelAndView buyNow(@PathVariable("id") Long id, @PathVariable("soLuong") int sl){
//        ModelAndView modelAndView  = new ModelAndView("/order");
//        modelAndView.addObject("product",productsService.findById(id));
//        modelAndView.addObject("bill",new Bill());
//        modelAndView.addObject("count",sl);
//        return modelAndView;
//    }
//
//    @PostMapping("/order-now/{id}/{soLuong}")
//    public ModelAndView orderNow(@PathVariable("id") Long id, @PathVariable("soLuong") int sl, @ModelAttribute("Bill") Bill bill, @PageableDefault(size = 8) Pageable pageable, HttpSession session){
//        ModelAndView  modelAndView = new ModelAndView("/index");
//        Products product =  productsService.findById(id);
//        bill.setTenSP(product.getTenSP());
//        bill.setGiaSP(product.getGiaSP()+"");
//        bill.setTongTien((product.getGiaSP()*sl)+"");
//        bill.setSoLuong(sl+"");
//        billService.save(bill);
//        Page<Products> page = productPagingAndSortingRepository.findAll(pageable);
//        modelAndView.addObject("page", page);
//        if(session.getAttribute("cart") != null){
//            List<Item> cart = (List<Item>) session.getAttribute("cart");
//            double total = 0;
//            for (Item item : cart) {
//                total += item.getProduct().getGiaSP() * item.getQuantity();
//            }
//            modelAndView.addObject("cart",cart);
//            modelAndView.addObject("sl",cart.size());
//            modelAndView.addObject("total", total);
//        }
//        return modelAndView;
//    }

        @PostMapping("order-all")
    public ModelAndView orderAll(HttpSession session, @ModelAttribute("Bill") Bill bill) {
        ModelAndView modelAndView = new ModelAndView("/cart");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        List<Bill> bills = new ArrayList<Bill>();
        for (Item item : cart) {
            int index = this.exists(item.getProduct().getTenSP(), cart);
            bills.add(new Bill(
                    bill.getTenKH(),
                    bill.getDiaChi(),
                    bill.getSoDT(),
                    cart.get(index).getProduct().getTenSP(),
                    cart.get(index).getProduct().getGiaSP() + "",
                    cart.get(index).getQuantity() + "",
                    (cart.get(index).getProduct().getGiaSP() * cart.get(index).getQuantity()) + ""));
        }
        if(session.getAttribute("cart") != null){
            double total = 0;
            for (Item item : cart) {
                total += item.getProduct().getGiaSP() * item.getQuantity();
            }
            modelAndView.addObject("cart",cart);
            modelAndView.addObject("sl",cart.size());
            modelAndView.addObject("total", total);
        }
        billService.saveAll(bills);
        cart.removeAll(cart);
        session.setAttribute("cart", cart);
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
