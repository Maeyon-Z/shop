package com.zzz.shop.utils.passwd;

import com.zzz.shop.utils.md5.MD5Hash;

/**
 * @author zzz
 * 2022-04-25 9:41
 */
public class PasswordUtils {
    public static String getPassword(String password){
        if(password == null || password.trim().isEmpty()) return password;
        for(int i = 0; i < 5; i++){
            password = MD5Hash.md5Java(password);
        }
        return password;
    }
}
