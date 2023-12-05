package com.solvd.classes;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        //MenuSwitch menu = new MenuSwitch();
        //menu.mainMenuSwitch();
        Class<Reflect> reflectClass = Reflect.class;
        out.format("Class:%n  %s%n%n", reflectClass.getCanonicalName());
        out.format("Modifiers:%n  %s%n%n",
                Modifier.toString(reflectClass.getModifiers()));
        printMembers(reflectClass.getDeclaredConstructors(), "Constructor");
        printMembers(reflectClass.getDeclaredFields(), "Fields");
        printMembers(reflectClass.getMethods(), "Methods");
        printClasses(reflectClass);

        out.format("Type Parameters:%n");
        TypeVariable[] tv = reflectClass.getTypeParameters();
        if (tv.length != 0) {
            out.format("  ");
            for (TypeVariable t : tv)
                out.format("%s ", t.getName());
            out.format("%n%n");
        } else {
            out.format("  -- No Type Parameters --%n%n");
        }

        out.format("Implemented Interfaces:%n");
        Type[] intfs = reflectClass.getGenericInterfaces();
        if (intfs.length != 0) {
            for (Type intf : intfs)
                out.format("  %s%n", intf.toString());
            out.format("%n");
        } else {
            out.format("  -- No Implemented Interfaces --%n%n");
        }

        out.format("Inheritance Path:%n");
        List<Class> l = new ArrayList<Class>();
        printAncestor(reflectClass, l);
        if (l.size() != 0) {
            for (Class<?> cl : l)
                out.format("  %s%n", cl.getCanonicalName());
            out.format("%n");
        } else {
            out.format("  -- No Super Classes --%n%n");
        }

        out.format("Annotations:%n");
        Annotation[] ann = reflectClass.getAnnotations();
        if (ann.length != 0) {
            for (Annotation a : ann)
                out.format("  %s%n", a.toString());
            out.format("%n");
        } else {
            out.format("  -- No Annotations --%n%n");
        }
        try {
            Constructor<Reflect> reflectConstructor = reflectClass.getConstructor(String.class, int.class);
            out.println(reflectConstructor.newInstance("Hello", 1).toString());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printMembers(Member[] mbrs, String s) {
        out.format("%s:%n", s);
        for (Member mbr : mbrs) {
            if (mbr instanceof Field)
                out.format("  %s%n", ((Field) mbr).toGenericString());
            else if (mbr instanceof Constructor)
                out.format("  %s%n", ((Constructor<?>) mbr).toGenericString());
            else if (mbr instanceof Method)
                out.format("  %s%n", ((Method) mbr).toGenericString());
        }
        if (mbrs.length == 0)
            out.format("  -- No %s --%n", s);
        out.format("%n");
    }

    private static void printClasses(Class<?> c) {
        out.format("Classes:%n");
        Class<?>[] clss = c.getClasses();
        for (Class<?> cls : clss)
            out.format("  %s%n", cls.getCanonicalName());
        if (clss.length == 0)
            out.format("  -- No member interfaces, classes, or enums --%n");
        out.format("%n");
    }

    private static void printAncestor(Class<?> c, List<Class> l) {
        Class<?> ancestor = c.getSuperclass();
        if (ancestor != null) {
            l.add(ancestor);
            printAncestor(ancestor, l);
        }
    }
}

