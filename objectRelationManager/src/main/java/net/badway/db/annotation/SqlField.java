/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author jonathan
 */
@Retention(value = RetentionPolicy.RUNTIME)
public  @interface SqlField {
    String sqlType();
    int size() default -1;
}
