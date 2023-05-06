package tictactoe.main;

enum Sign {
    X('X'),
    O('O'),
    N(' ');

    final char value;

    Sign(char c) {
        value = c;
    }
}
