package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private Player player;
    private QuestionReader reader;
    private QuestionSelector selector;
    private ArrayList<Integer> checkpoints;
    private int currentLevel;
    private int currentQuestion;

    public Game(String playerName) throws IOException {
        player = new Player(playerName);
        reader = new QuestionReader("C:/Junior Dev Nauka wszystko/Millionaires/src/main/resources/questions.txt");
        selector = new QuestionSelector(reader.getQuestions());
        checkpoints = new ArrayList<Integer>(Arrays.asList(6,11));
        currentLevel = 1;
        currentQuestion =1;
    }

    public void start() {
        System.out.println("Witaj w grze Milionerzy, " + player.getName() + "!");
        System.out.println("W tej grze masz 3 koła ratunkowe, które pomogą Ci podczas rozgrywki");

        while (currentLevel < 12) {
            System.out.println("Poziom " + currentLevel + " - " + getLevelPoints(currentLevel) + " złotych:");
            Question question = selector.selectQuestion();
            displayQuestion(question);
            int answer = getAnswerFromPlayer();
            checkAnswer(question, answer);
            if (currentLevel == 6 || currentLevel == 11) {
                handleCheckpoint();
            }
            if (player.getLifebuoys() == 0) {
                end();
                return;
            }
        }
        System.out.println("Poziom 12 - Pytanie o milion złotych!");
        System.out.println("UWAŻAJ! W OSTATNIM PYTANIU NIE OBOWIĄZUJE KOŁO RATUNKOWE. JEŚLI ŹLE ODPOWIESZ PRZEGRYWASZ");
        Question question = selector.selectQuestion();
        displayQuestion(question);
        int answer = getAnswerFromPlayer();
        checkAnswer(question,answer);
        end();
    }

    public void displayQuestion(Question question) {
        System.out.println(question.getText());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i+1) + ". " + options[i]);
        }
    }

    private int getAnswerFromPlayer() {
        Scanner scanner = new Scanner(System.in);
        int answer = 0;
        while (answer < 1 || answer > 4) {
            System.out.println("Wprowadź swoją odpowiedź (1-4): ");
            answer = scanner.nextInt();
        }
        return answer;
    }

    private void checkAnswer(Question question, int answer) {
        if (answer == question.getCorrectAnswer()) {
            int points = getLevelPoints(currentLevel);
            player.setMoney(points);
            if (points != 1000000) {
                System.out.println("Odpowiedź prawidłowa, masz już: " + points + " złotych");
                currentLevel++;  // move to the next level
                currentQuestion = 1;  // start with a new question at the next level
            }
        } else {
            if (currentLevel <= 11) {
                player.useLifebuoy();
                System.out.println("Niestety, odpowiedź niepoprawna. Straciłeś jedno życie");
                int remainLifebuoys = player.getLifebuoys();
                if (remainLifebuoys == 2) {
                    System.out.println("Pozostało Ci 1 koło ratunkowe");
                } else if (remainLifebuoys == 1) {
                    System.out.println("Wykorzystałeś, ostanie koło ratunkowe. Następna zła odpowiedź będzię skutkować zakończeniem gry.");
                }
                // stay at the current level with a new question
                currentQuestion++;
            }
        }
    }

    private void handleCheckpoint() {
        int points = getLevelPoints(checkpoints.get(0));
        if (!checkpoints.isEmpty()) {
            if (currentLevel == 6) {
                System.out.println("Dotarłeś do punktu kontrolnego! Masz gwarantowane 10000 złotych.");
                if (player.hasLifebuoys()) {
                    System.out.println("Masz koła ratunkowe. Jeśli odpowiesz nieprawidłowo na następne pytanie, nic nie stracisz.");
                } else {
                    System.out.println("Jeśli odpowiesz nieprawidłowo na następne pytanie, otrzymasz tylko " + points + " złotych.");
                }
            } else if (currentLevel == 11) {
                System.out.println("Dotarłeś do punktu kontrolnego! Masz gwarantowane 250000 złotych.");
                if (player.hasLifebuoys()) {
                    System.out.println("Masz koła ratunkowe. Jeśli odpowiesz nieprawidłowo na następne pytanie, nic nie stracisz.");
                } else {
                    System.out.println("Jeśli odpowiesz nieprawidłowo na następne pytanie, otrzymasz tylko " + points + " złotych.");
                }
            }
            checkpoints.remove(0);
        }
    }

    private void end() {
        if (player.getMoney() == 0) {
            System.out.println("Niestety, " + player.getName() + ", nie udało Ci się wygrać żadnych pieniędzy. Więcej szczęścia następnym razem.");
        } else {
            System.out.println("Gratulacje, " + player.getName() + "! Wygrałeś " + player.getMoney() + " złotych!");
            if (player.getMoney() >= 1000000) {
                System.out.println("Jesteś milionerem!");
            } else {
                System.out.println("Więcej szczęścia następnym razem.");
            }
        }
    }

    private int getLevelPoints(int level) {
        switch (level) {
            case 1:
                return 500;
            case 2:
                return 1000;
            case 3:
                return 2000;
            case 4:
                return 5000;
            case 5:
                return 10000;
            case 6:
                return 20000;
            case 7:
                return 40000;
            case 8:
                return 75000;
            case 9:
                return 125000;
            case 10:
                return 250000;
            case 11:
                return 500000;
            case 12:
                return 1000000;
            default:
                return 0;
        }
    }
}
