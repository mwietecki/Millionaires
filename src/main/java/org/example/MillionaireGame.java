package org.example;

import java.io.IOException;
import java.util.Scanner;

public class MillionaireGame {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj swoje imie: ");
        String playerName = scanner.nextLine();
        Game game = new Game(playerName);
        game.start();
    }
}

