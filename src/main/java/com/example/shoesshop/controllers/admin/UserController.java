package com.example.shoesshop.controllers.admin;

import com.example.shoesshop.entities.User;

import com.example.shoesshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Repository
@RequestMapping("/admin/users")
public class UserController implements ICRUD<User>{
    @Autowired
   private UserService userService;

    @Override
    @GetMapping("add")
    public String add(Model model) {
           User user= new User();
           model.addAttribute("user",user);
        return "admin/user/add";
    }

    @Override
    @PostMapping("/do-add")
    public String doAdd(User user , RedirectAttributes flashSession) {

        if(userService.save(user)){
            flashSession.addFlashAttribute("success", "Add successfully");
        }else{
            flashSession.addFlashAttribute("failed", "Add thất bại");
        }
        return "redirect:/admin/users/add";
    }

    @Override
    @GetMapping("/")
    public String list(Model model , @RequestParam(name = "page", defaultValue = "1" ,required = false) int page ) {
        UserService.ListResult listResult = userService.getUserList(page);
        model.addAttribute("listResult",listResult);
        return "admin/user/list";
    }

    @Override
    @GetMapping("/edit")
    public String edit(Model model , @RequestParam(name="id") long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "/admin/user/edit";
    }

    @Override
    @PostMapping("/do-edit")
    public String doEdit( User user , RedirectAttributes flashSession) {
       if(userService.save(user)){
           flashSession.addFlashAttribute("success","edit Successfully");
       }else {
           flashSession.addFlashAttribute("success","edit failed");
       }
        return "redirect:/admin/users/edit?id="+user.getId();
    }

    @Override
    @GetMapping("/delete")
    public String delete( Model model ,@RequestParam(name="id") long id, RedirectAttributes flashSession ,@RequestParam(name = "page", defaultValue = "1") int page) {
        if (userService.delete(id)) {
            flashSession.addFlashAttribute("success", "delete successfully");
            UserService.ListResult listResult = userService.getUserList(page);
            model.addAttribute("listResult",listResult);
        }else{
            flashSession.addFlashAttribute("failed", "delete failed");
            model.addAttribute("listResult",new ArrayList<>());
        }

        return "admin/user/list";
    }
}
