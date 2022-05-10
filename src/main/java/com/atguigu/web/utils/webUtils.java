package com.atguigu.web.utils;

import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author shkstart
 * @create 2022-03-13 17:48
 */
//将参数封装到对应类的工具类
public class webUtils {
    public static <T> T copyParamToBean(Map value,T bean){
        try {
            //返回对应的类
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
return bean;
    }
}
