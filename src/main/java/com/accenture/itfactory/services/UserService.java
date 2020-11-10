package com.accenture.itfactory.services;

import com.accenture.itfactory.access.DataRepository;
import com.accenture.itfactory.configurations.ConstDB;

import java.util.ArrayList;

public class UserService {

    private static UserService instance = null;

    private UserService() { }

    public static UserService getInstance() {
        if(instance == null){
            synchronized (UserService.class) {
                if(instance == null){
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    public String register(String login, String password, String name) {
        int id= DataRepository.getInstance().getId(ConstDB.getUserTable());
        String sql="select * from users where login='"+login+"'";
        ArrayList<String> list = DataRepository.getInstance().select(sql);
        if(!list.isEmpty()){
            return "Пользователь уже зарегестрирован";
        }
        sql="insert into users(id,login,password,name,role) values("+id+",'"+login+"','"+password+"','"+name+"','user')";
        if(!DataRepository.getInstance().request(sql)){
            return "Ошибка, попробуйте еще раз";
        }
        return "Вы зарегестрировались!";
    }

    public Integer authorized(String login, String password) {
        String sql="select id from users where login='"+login+"' and password='"+password+"'";
        ArrayList<String> list= DataRepository.getInstance().select(sql);
        if(list.isEmpty()){
            return -1;
        }
        return Integer.parseInt(list.get(0).replaceAll("\\s",""));
    }

    public String getRole(int idUser){
        String sql="select role from users where id="+idUser;
        ArrayList<String> list= DataRepository.getInstance().select(sql);
        return list.get(0).replaceAll("\\s","");
    }

    public String getName(int idUser){
        String sql="select name from users where id="+idUser;
        ArrayList<String> list= DataRepository.getInstance().select(sql);
        return list.get(0).replaceAll("\\s","");
    }

}
