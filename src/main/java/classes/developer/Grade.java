package classes.developer;

public enum Grade {
    JUNIOR (1.5f),
    MIDDLE(1.0f),
    SENIOR(0.5f);
    public final float GRADE_MODIFIER;
    Grade(float GRADE_MODIFIER) {
        this.GRADE_MODIFIER = GRADE_MODIFIER;
    }
}