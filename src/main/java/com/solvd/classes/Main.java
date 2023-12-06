package com.solvd.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        //MenuSwitch menu = new MenuSwitch();
        //menu.mainMenuSwitch();
        Class<Reflect> reflectClass = Reflect.class;
        LOGGER.info("Class:\n  {}\n\n", reflectClass.getCanonicalName());
        LOGGER.info("Modifiers:\n  {}\n\n",
                Modifier.toString(reflectClass.getModifiers()));
        printMembers(reflectClass.getDeclaredConstructors(), "Constructor");
        printMembers(reflectClass.getDeclaredFields(), "Fields");
        printMembers(reflectClass.getMethods(), "Methods");
        printClasses(reflectClass);

        LOGGER.info("Type Parameters:\n");
        TypeVariable[] tv = reflectClass.getTypeParameters();
        if (tv.length != 0) {
            LOGGER.info("  ");
            for (TypeVariable t : tv)
                LOGGER.info("{} ", t.getName());
            LOGGER.info("\n\n");
        } else {
            LOGGER.info("  -- No Type Parameters --\n\n");
        }

        LOGGER.info("Implemented Interfaces:\n");
        Type[] intfs = reflectClass.getGenericInterfaces();
        if (intfs.length != 0) {
            for (Type intf : intfs)
                LOGGER.info("  {}\n", intf.toString());
            LOGGER.info("\n");
        } else {
            LOGGER.info("  -- No Implemented Interfaces --\n\n");
        }

        LOGGER.info("Inheritance Path:\n");
        List<Class> l = new ArrayList<Class>();
        printAncestor(reflectClass, l);
        if (l.size() != 0) {
            for (Class<?> cl : l)
                LOGGER.info("  {}\n", cl.getCanonicalName());
            LOGGER.info("\n");
        } else {
            LOGGER.info("  -- No Super Classes --\n\n");
        }

        LOGGER.info("Annotations:\n");
        Annotation[] ann = reflectClass.getAnnotations();
        if (ann.length != 0) {
            for (Annotation a : ann)
                LOGGER.info("  {}\n", a.toString());
            LOGGER.info("\n");
        } else {
            LOGGER.info("  -- No Annotations --\n\n");
        }
        try {
            Constructor<Reflect> reflectConstructor = reflectClass.getConstructor(String.class, int.class);
            LOGGER.info(reflectConstructor.newInstance("Hello", 1).toString());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printMembers(Member[] mbrs, String s) {
        LOGGER.info("{}:\n", s);
        for (Member mbr : mbrs) {
            if (mbr instanceof Field)
                LOGGER.info("  {}\n", ((Field) mbr).toGenericString());
            else if (mbr instanceof Constructor)
                LOGGER.info("  {}\n", ((Constructor<?>) mbr).toGenericString());
            else if (mbr instanceof Method)
                LOGGER.info("  {}\n", ((Method) mbr).toGenericString());
        }
        if (mbrs.length == 0)
            LOGGER.info("  -- No {} --\n", s);
        LOGGER.info("\n");
    }

    private static void printClasses(Class<?> c) {
        LOGGER.info("Classes:\n");
        Class<?>[] clss = c.getClasses();
        for (Class<?> cls : clss)
            LOGGER.info("  {}\n", cls.getCanonicalName());
        if (clss.length == 0)
            LOGGER.info("  -- No member interfaces, classes, or enums --\n");
        LOGGER.info("\n");
    }

    private static void printAncestor(Class<?> c, List<Class> l) {
        Class<?> ancestor = c.getSuperclass();
        if (ancestor != null) {
            l.add(ancestor);
            printAncestor(ancestor, l);
        }
    }
}

