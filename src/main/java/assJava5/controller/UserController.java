package assJava5.controller;

import assJava5.model.Bill;
import assJava5.model.Products;
import assJava5.model.Users;
import assJava5.repository.BillPagingAndSortingRepository;
import assJava5.repository.ProductPagingAndSortingRepository;
import assJava5.service.BillService;
import assJava5.service.ProductsService;
import assJava5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping("admin/")
public class UserController {
    private static Users USER = null;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductPagingAndSortingRepository productPagingAndSortingRepository;

    @Autowired
    private BillPagingAndSortingRepository billPagingAndSortingRepository;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private BillService billService;

    private File path = new File("D:\\untitled\\src\\main\\resources\\static\\images");

    @GetMapping("/")
    public ModelAndView viewLogin(){
        ModelAndView modelAndView = new ModelAndView("/admin/login");
        modelAndView.addObject("user", new Users());
        return modelAndView;
    }

    @PostMapping("login.htm")
    public ModelAndView login(@Validated @ModelAttribute("User") Users users, BindingResult bindingResult){
        ModelAndView modelAndView ;
//        if(bindingResult.hasErrors()){
//            modelAndView = new ModelAndView("/admin/login");
//            modelAndView.addObject("user", new Users());
//            return modelAndView;
//        }
        if(userService.isUsers(users.getTaiKhoan(),users.getMatKhau())){
            try {
                Users user = userService.findByUserName(users.getTaiKhoan());
                USER = user;
                modelAndView = new ModelAndView("/admin/index");
                modelAndView.addObject("users",userService.findAll());
                modelAndView.addObject("products",productsService.findAll());
                modelAndView.addObject("bills",billService.findAll());
                modelAndView.addObject("user",USER);
                modelAndView.addObject("top10Products",billService.findByTop10Product());
                modelAndView.addObject("top10Bills",billService.findByTop10Bill());
            }
            catch (Exception e){
                modelAndView = new ModelAndView("/admin/login");
                modelAndView.addObject("user", new Users());
            }
        }
        else {
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        return modelAndView;
    }

    @GetMapping("logout-user.htm")
    public ModelAndView logout(){
        USER= null;
        ModelAndView  modelAndView = new ModelAndView("/admin/login");
        modelAndView.addObject("user", new Users());
        return modelAndView;
    }

    @GetMapping("view-index.htm")
    public ModelAndView viewIndex(){
        ModelAndView modelAndView ;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else {
            modelAndView = new ModelAndView("/admin/index");
            modelAndView.addObject("users", userService.findAll());
            modelAndView.addObject("products", productsService.findAll());
            modelAndView.addObject("bills", billService.findAll());
            modelAndView.addObject("user", USER);
            modelAndView.addObject("top10Products",billService.findByTop10Product());
            modelAndView.addObject("top10Bills",billService.findByTop10Bill());
        }
        return modelAndView;
    }

    @GetMapping("view-user.htm")
    public ModelAndView viewUser(){
        ModelAndView modelAndView ;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else {
            modelAndView = new ModelAndView("/admin/user");
            modelAndView.addObject("users", userService.findAll());
            modelAndView.addObject("user", USER);
        }
        return modelAndView;
    }

    @GetMapping("create-user.htm")
    public ModelAndView viewCreateUser(){
        ModelAndView modelAndView ;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else{
            modelAndView = new ModelAndView("/admin/create-user") ;
            modelAndView.addObject("createUser",new Users());
            modelAndView.addObject("user",USER);
        }
        return modelAndView;
    }

    @PostMapping("create-user.htm")
    public ModelAndView createUser(@ModelAttribute("User") Users users){
        ModelAndView modelAndView ;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else {
            modelAndView = new ModelAndView("/admin/user");
            userService.save(users);
            modelAndView.addObject("users", userService.findAll());
            modelAndView.addObject("user",USER);
        }
        return modelAndView;
    }

    @GetMapping("update-user.htm/{id}")
    public ModelAndView viewUpdateUser(@PathVariable("id") Long id){
        ModelAndView modelAndView ;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else {
            modelAndView = new ModelAndView("/admin/update-user");
            modelAndView.addObject("user", USER);
            modelAndView.addObject("userUpdate", userService.finById(id));
        }
        return modelAndView;
}
    @PostMapping("update-user.htm/{id}")
    public  ModelAndView updateUser(@PathVariable("id") Long id,@ModelAttribute("User") Users users){
        ModelAndView modelAndView ;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else {
            users.setId(id);
            modelAndView = new ModelAndView("/admin/user");
            userService.save(users);
            modelAndView.addObject("users", userService.findAll());
            modelAndView.addObject("user",USER);
        }
        return modelAndView;
    }

    @GetMapping("remove-user.htm/{id}")
    public ModelAndView removeUser(@PathVariable("id") Long id) {
        userService.remove(id);
        ModelAndView modelAndView = new ModelAndView("/admin/user");
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("user",USER);
        return modelAndView;
    }

//    @PostMapping("search-product")
//    public ModelAndView searchProduct(@RequestParam("search") String name){
//        ModelAndView modelAndView = new ModelAndView("/admin/products");
//        modelAndView.addObject("user",USER);
//        modelAndView.addObject("page", productsService.findByNameSession(name));
//        return modelAndView;
//    }


    @GetMapping("view-product-manager.htm")
    public ModelAndView viewProductManager(@PageableDefault(size = 8) Pageable pageable){
        ModelAndView modelAndView;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else {
            modelAndView = new ModelAndView("/admin/products");
            Page<Products> page = productPagingAndSortingRepository.findAll(pageable);
            modelAndView.addObject("user",USER);
            modelAndView.addObject("page", page);
        }
        return modelAndView;
    }

    @GetMapping("create-product.htm")
    public ModelAndView viewCreateProduct(){
        ModelAndView modelAndView;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else {
            modelAndView = new ModelAndView("/admin/create-product");
            modelAndView.addObject( "product", new Products());
            modelAndView.addObject("user",USER);
        }
        return modelAndView;
    }

    @PostMapping("create-product.htm")
    public ModelAndView createProduct(@ModelAttribute("Products") Products products, @RequestParam("files") CommonsMultipartFile file, @PageableDefault(size = 8) Pageable pageable)throws Exception{
        ModelAndView modelAndView;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else {
            if(!file.isEmpty()){
                FileOutputStream fileOutputStream;
                fileOutputStream = new FileOutputStream(path+"\\"+ file.getOriginalFilename());
                fileOutputStream.write(file.getBytes());
                fileOutputStream.close();
                if (file.getOriginalFilename() != null) {
                    products.setHinhAnh(file.getOriginalFilename());
                }
            }
            modelAndView = new ModelAndView("/admin/products");
            productsService.save(products);
            Page<Products> page = productPagingAndSortingRepository.findAll(pageable);
            modelAndView.addObject("page", page);
            modelAndView.addObject("user",USER);
        }
        return modelAndView;
    }

    @GetMapping("update-product.htm/{id}")
    public ModelAndView viewUpdateProduct(@PathVariable("id") Long id){
        ModelAndView modelAndView;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else {
            modelAndView = new ModelAndView("/admin/update-product");
            modelAndView.addObject("product",productsService.findById(id));
            modelAndView.addObject("user",USER);
        }
        return modelAndView;
    }

    @PostMapping("update-product.htm/{id}")
    public ModelAndView updateProduct(@PathVariable("id") Long id, @RequestParam("imagesLogo")MultipartFile file, @ModelAttribute("Product") Products products, @PageableDefault(size = 8) Pageable pageable) throws Exception{
        ModelAndView modelAndView;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else{
            if(!file.isEmpty()){
                FileOutputStream fileOutputStream;
                fileOutputStream = new FileOutputStream(path+"\\"+ file.getOriginalFilename());
                fileOutputStream.write(file.getBytes());
                fileOutputStream.close();
                if (file.getOriginalFilename() != null) {
                    products.setHinhAnh(file.getOriginalFilename());
                }
            }
            else{
                Products products1 = productsService.findById(id);
                products.setHinhAnh(products1.getHinhAnh());
            }
            modelAndView  = new ModelAndView("/admin/products");
            products.setId(id);
            productsService.save(products);
            Page<Products> page = productPagingAndSortingRepository.findAll(pageable);
            modelAndView.addObject("page", page);
            modelAndView.addObject("user",USER);
        }
        return modelAndView;
    }

    @GetMapping("remove-products/{id}")
    public ModelAndView removeProduct(@PathVariable("id") Long id,@PageableDefault(size = 8) Pageable pageable){
        ModelAndView modelAndView;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else {
            modelAndView = new ModelAndView("/admin/products");
            productsService.remove(id);
            Page<Products> page = productPagingAndSortingRepository.findAll(pageable);
            modelAndView.addObject("user",USER);
            modelAndView.addObject("page", page);
        }
        return modelAndView;
    }

    @GetMapping("view-bill-manager.htm")
    public ModelAndView viewBill(@PageableDefault(size = 12) Pageable pageable){
        ModelAndView modelAndView;
        if(USER==null){
            modelAndView = new ModelAndView("/admin/login");
            modelAndView.addObject("user", new Users());
        }
        else {
            modelAndView = new ModelAndView("/admin/bill");
            modelAndView.addObject("user",USER);
            Page<Bill> page = billPagingAndSortingRepository.findAll(pageable);
            modelAndView.addObject("page", page);
        }
        return modelAndView;
    }
}
