package com.solvd.classes.developer;

@FunctionalInterface
public interface IGetGrade<T> {
    T getGrade(String input);
}
