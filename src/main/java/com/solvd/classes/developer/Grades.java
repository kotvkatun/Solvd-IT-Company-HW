package com.solvd.classes.developer;

public enum Grades {
    JUNIOR(1.5f),
    MIDDLE(1.0f),
    SENIOR(0.5f);
    public final float GRADE_MODIFIER;

    Grades(float GRADE_MODIFIER) {
        this.GRADE_MODIFIER = GRADE_MODIFIER;
    }
}