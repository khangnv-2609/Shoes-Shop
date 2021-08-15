package com.example.shoesshop.controllers.admin;

import com.example.shoesshop.entities.Categories;
import com.example.shoesshop.entities.Product;
import com.example.shoesshop.services.CategoryService;
import com.example.shoesshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin/products")
public class ProductController implements IWithImageCRUD<Product> {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Override
    @GetMapping("/add")
    public String add(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll());
        return "admin/product/add";
    }

    @Override
    @PostMapping("/do-add")
    public String doAdd(Product product, RedirectAttributes flashSession,
                        @RequestParam(name = "img") MultipartFile multipartFile) {
        Product product1 = (Product) product;
        if (productService.save(product1, multipartFile)) {
            flashSession.addFlashAttribute("success", "Add successfully");
        } else {
            flashSession.addFlashAttribute("failed", "Add failed");
        }
        return "redirect:/admin/products/add";
    }

    @Override
    public String doAdd(Object product, RedirectAttributes flashSession) {
        return null;
    }

    @Override
    public String doEdit(Object product, RedirectAttributes flashSession) {
        return null;
    }

    @Override
    @RequestMapping("/")
    public String list(Model model, @RequestParam(name="page", defaultValue = "1" , required = false) int page) {
        ProductService.ListResult listResult = productService.getProductList(page);
        model.addAttribute("listResult", listResult);
        return "admin/product/list";
    }

    @Override
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name="id") long id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/edit";
    }



    @Override
    public String delete(Model model,@RequestParam(name="id") long id, RedirectAttributes flashSession ,@RequestParam(name = "page", defaultValue = "1") int page) {
        if (productService.delete(id)) {
            flashSession.addFlashAttribute("success", "delete successfully");
            ProductService.ListResult listResult = productService.getProductList(page);
            model.addAttribute("listResult",listResult);
        } else {
            flashSession.addFlashAttribute("failed", "delete failed");
            model.addAttribute("listResult",new ArrayList<>());
        }
        return  "/admin/product/list";
    }
}
