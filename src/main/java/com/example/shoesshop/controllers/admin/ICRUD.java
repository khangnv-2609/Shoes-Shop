package com.example.shoesshop.controllers.admin;

import com.example.shoesshop.entities.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ICRUD<T> {
    public String add(Model model);

    public String doAdd(T user , RedirectAttributes flashSession);

    public String list(Model model ,@RequestParam(name = "page", defaultValue = "1") int page);

    public String edit(Model model ,@RequestParam(name="id") long id);

    public String doEdit(T user , RedirectAttributes flashSession);

    public String delete(Model model,@RequestParam(name="id") long id, RedirectAttributes flashSession ,@RequestParam(name = "page", defaultValue = "1") int page);
}
