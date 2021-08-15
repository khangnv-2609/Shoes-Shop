package com.example.shoesshop.services;

import com.example.shoesshop.entities.User;
import com.example.shoesshop.helpers.Helper;
import com.example.shoesshop.jpa.UserJPA;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired // tu dong inject doi tuong leen
    private UserJPA userJPA;

    @Autowired
    private Helper helper;



    public boolean save(User user){
        user.setPassword(helper.getMD5(user.getPassword()));
        try {
            userJPA.save(user);

        }catch (Exception e){
            return  false;
        }
        return  true;

    }

    public boolean delete(long id){

        try {
            userJPA.deleteById(id);
        }catch (Exception e){
            return  false;
        }
        return  true;
    }

    public User login(String email, String password) {
        return this.userJPA.login(email, helper.getMD5(password)).isPresent() ?
                this.userJPA.login(email, helper.getMD5(password)).get(): null;
    }

    public User getUserById(long id){
        return  userJPA.findById(id).get();
    }

public ListResult getUserList(int page ){
        ListResult listResult = new ListResult();
        listResult.setListUser( userJPA.findAll(PageRequest.of(page-1,20)));
        listResult.setActivePage(page);
        double totalPage = Math.ceil((double)userJPA.count()/20);

        listResult.setTotalPage(totalPage);
        return listResult;


}
@Data
public class  ListResult{
        Iterable<User> listUser;
        int activePage;
        double totalPage;


}
}
