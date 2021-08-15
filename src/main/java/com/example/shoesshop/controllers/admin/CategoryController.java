package com.example.shoesshop.controllers.admin;

import com.example.shoesshop.entities.Categories;
import com.example.shoesshop.services.CategoryService;
import com.example.shoesshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController implements ICRUD<Categories>{

    @Autowired
    private CategoryService categoryService;

    @Override
    @GetMapping("/add")
    public String add(Model model) {
        Categories categories = new Categories();
        model.addAttribute("categories", categories);
        return "admin/categories/add";
    }

    @Override
    @PostMapping("/do-add")
    public String doAdd(Categories categories, RedirectAttributes flashSession) {
        if(categoryService.save(categories)){
            flashSession.addFlashAttribute("success", "Add successfully");
        }else{
            flashSession.addFlashAttribute("failed", "Add thất bại");
        }
        return "redirect:/admin/categories/add";
    }

    @Override
    @GetMapping("/")
    public String list(Model model, @RequestParam(name="page", defaultValue = "1" , required = false) int page) {
        CategoryService.ListResult  listResult=  categoryService.getCategoriesList(page);
        model.addAttribute("listResult",listResult);
        return "admin/categories/list";
    }

    @Override
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name="id") long id) {
        Categories categories = categoryService.getCategoriesById(id);
        model.addAttribute("categories", categories);
        return "admin/categories/edit";
    }

    @Override
    @PostMapping("/do-edit")
    public String doEdit(Categories categories,RedirectAttributes flashSession) {
        if(categoryService.save(categories)){
            flashSession.addFlashAttribute("success", "edit successfully");
        }else{
            flashSession.addFlashAttribute("failed", "edit failed");
        }
        return "redirect:/admin/categories/edit?id="+categories.getId();
    }



    @Override
    @GetMapping("/delete")
    public String delete(Model model,@RequestParam(name="id") long id, RedirectAttributes flashSession ,@RequestParam(name = "page", defaultValue = "1") int page) {
        if (categoryService.delete(id)) {
            flashSession.addFlashAttribute("success", "delete successfully");
            CategoryService.ListResult listResult = categoryService.getCategoriesList(page);
            model.addAttribute("listResult",listResult);
        }else{
            flashSession.addFlashAttribute("failed", "delete failed");
            model.addAttribute("listResult",new ArrayList<>());
        }
        return "/admin/categories/list";
    }
}