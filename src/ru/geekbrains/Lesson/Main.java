package ru.geekbrains.Lesson;

import java.util.Random;
import java.util.Scanner;

public class Main
{
    static char[][] map;
    static final int SIZE = 3;
    static final int DOTS_TO_WIN = 3;

    static final char DOT_EMPTY = '.';
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';

    public static void main(String[] args)
    {
        // write your code here

        // 1. инициализировать поле
        initMap();


        // 1.1 Вывод поля
        printMap();

        // в цикле
        while (true)
        {
            // 2. Выбор игральной фишки. - ДОП К ДЗ
            // 3. Ход первого игрока.
            makeHumanTurn();
            // 4. Вывод поля.
            printMap();
            // 5. Проверка на 3 в ряд и диагональ.
            if (hasWinByLines(DOT_X) || hasWinByDiagonal(DOT_X))
            {
                System.out.println("Победил человек!");
                break;
            }
            // 6. Проверка на ничью.
            if (isMapFull())
            {
                System.out.println("Ничья");
                break;
            }

            // 7. Ход второго игрока (ИИ).
            makeAiTurn();

            // 8. Вывод поля.
            printMap();

            // 9. Проверка на 3 в ряд и диагональ.
            if (hasWinByLines(DOT_O) || hasWinByDiagonal(DOT_O))
            {
                System.out.println("Победил Т-1000");
                break;
            }

            // 10. Проверка на ничью.
            if (isMapFull())
            {
                System.out.println("Ничья");
                break;
            }
        }
    }

    // 1. Инициализация.
    static void initMap()
    {
        map = new char[SIZE][SIZE];

        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    // Вывод поля
    static void printMap()
    {
        for (int i = 0; i <= map.length; i++)
        {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0; i < map.length; i++)
        {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map[i].length; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    // Ход первого игрока (человека)
    static void makeHumanTurn()
    {
        Scanner scanner = new Scanner(System.in);

        int x;
        int y;

        do
        {
            System.out.println("Введите координаты в формате X Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));

        map[y][x] = DOT_X;
    }

    // Ход второго игрока (ИИ)
    static void makeAiTurn()
    {
        Random random = new Random();

        int x;
        int y;

        do
        {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));

        System.out.println("Компьютер сходил в точку: " + (x + 1) + " " + (y + 1));

        map[y][x] = DOT_O;
    }

    // Проверка хода
    static boolean isCellValid(int x, int y)
    {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE)
        {
            return false;
        }
        else if (map[y][x] == DOT_EMPTY)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //Проверка по диагонали
    static boolean hasWinByDiagonal(char symb) {
            boolean mainDiagonal = true;
            boolean sideDiagonal = true;
            for (int i = 0; i < map.length; i++) {
                mainDiagonal &= (map[i][i] == symb); //mainDiagonal = mainDiagonal & (map[i][i] == symb)
                sideDiagonal &= (map[i][map[i].length - i - 1] == symb);
            }
            if (mainDiagonal || sideDiagonal) return true;
            return false;
    }

    //Проверка по линиям
    static boolean hasWinByLines(char symb) {
        boolean lines, column;
        for (int i = 0; i < map.length; i++) {
            lines = true;
            column = true;
            for (int j = 0; j < map.length; j++) {
                lines &= (map[i][j] == symb);
                column &= (map[j][i] == symb);
            }
            if (lines || column) return true;
        }
        return false;
    }

    // Проверка на победу
    static boolean hasWin(char symb)
    {
        // проверка по строкам
        if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb)
        {
            return true;
        }
        if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb)
        {
            return true;
        }
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb)
        {
            return true;
        }

        // проверка по столбцам
        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb)
        {
            return true;
        }
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb)
        {
            return true;
        }
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb)
        {
            return true;
        }

        // Проверка по диагоналям
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb)
        {
            return true;
        }
        if (map[0][2] == symb && map[1][1] == symb && map[2][0] == symb)
        {
            return true;
        }

        return false;
    }


    // Ничья
    static boolean isMapFull()
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                if (map[i][j] == DOT_EMPTY)
                {
                    return false;
                }
            }
        }

        return true;
    }

}
