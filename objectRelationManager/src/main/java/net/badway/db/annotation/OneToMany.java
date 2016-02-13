/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;

/**
 *
 * @author jonathan
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface OneToMany {
        String target();
        Class<?> clazz();
        Class<? extends Collection> container();
}
