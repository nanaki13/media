/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.badway.db.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 *
 * @author jonathan
 */
public class Reflexivite {
    public static Method[] getObjectGetters(Class<?> clazz){
        Method[] methods = clazz.getMethods();
        int count =0;
        int nbP;
        for (Method method : methods) {
           if(isGetterObjectMethod(method))
                count++;
        }
        Method[] ret = new Method[count];
        count = 0;
        for (Method method : methods) {
            if(isGetterObjectMethod(method)) {
                ret[count] = method;
                count++;
            }
        }
        return ret;
    }
    
    public static Method[] getObjectSetters(Class<?> clazz){
        Method[] methods = clazz.getMethods();
        int count =0;
        int nbP;
        for (Method method : methods) {
         
            if(isSetterObjectMethod( method ) )
                count++;
        }
        Method[] ret = new Method[count];
        count = 0;
        for (Method method : methods) {
            if(isSetterObjectMethod( method ) ) {
                ret[count] = method;
                count++;
            }
        }
        return ret;
    }
    
    private static boolean isObjectMethod(Method m ){
        int modifiers = m.getModifiers();
        return (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) ;
    }
    
    private static boolean isGetterObjectMethod(Method method ){
         String name = method.getName();
            int nbP = method.getParameterTypes().length;
            return( nbP == 0 
                    &&((name.startsWith("get") 
                    && !name.equals("getClass")) 
                    || name.startsWith("is"))
                    && isObjectMethod(method)  );
    }
    
     private static boolean isSetterObjectMethod(Method method ){
         String name = method.getName();
            int nbP = method.getParameterTypes().length;
            return( name.startsWith("set")  && nbP == 1 
                    && isObjectMethod(method)  );
    }
     
     public static Method getMethodByName(String name, Method[] methods, int nbParam) {
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == nbParam) {
                    return method;
                }
            }
        }
        return null;

    }
     
    public static boolean haveField(String name, Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field filed : declaredFields) {
            if (filed.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public static Field getField(String name, Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field filed : declaredFields) {
            if (filed.getName().equals(name)) {
                return filed;
            }
        }
        return null;
    }
    
     public static Field getField(String name, Field[] declaredFields) {
        for (Field filed : declaredFields) {
            if (filed.getName().equals(name)) {
                return filed;
            }
        }
        return null;
    }
}
