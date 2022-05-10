package com.atguigu.web.utils;

/**
 * @author shkstart
 * @create 2022-03-13 20:20
 */
//string转为Integer类型的工具类
public class PaseUtils {
    /**
     *
     * @param str  转换的字符串
     * @param defaut   转化失败的返回值
     * @return
     */
    public static int  paserInt(String str,int defaut){
        //判断字符串是否为空串或空
        if(str!=null||str!="") {
            try {
                //将字符串转换为整数
                return   Integer.parseInt(str);
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
        return defaut;
    }

}





