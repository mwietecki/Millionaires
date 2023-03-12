package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class QuestionReader {
    private ArrayList<Question> questions;

    public QuestionReader(String filePath) throws IOException {
        questions = new ArrayList<Question>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String parts[] = line.split(",");
            String text = parts[0];
            String[] options = Arrays.copyOfRange(parts,1,5);
            int correctAnswer = Integer.parseInt(parts[5]);

            Question question = new Question(text, options, correctAnswer);
            questions.add(question);
        }

        reader.close();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
