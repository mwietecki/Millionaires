package org.example;

import java.util.ArrayList;
import java.util.Random;

public class QuestionSelector {
    private ArrayList<Question> questions;
    private Random random;

    public QuestionSelector(ArrayList<Question> questions) {
        this.questions = questions;
        random = new Random();
    }

    public Question selectQuestion() {
        int index = random.nextInt(questions.size());
        return questions.remove(index);
    }
}
