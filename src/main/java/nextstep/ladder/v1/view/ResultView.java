package nextstep.ladder.v1.view;

import nextstep.ladder.v1.model.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {
    private static final int MAX = 7;

    public static void printResultLetters() {
        System.out.println();
        System.out.println("실행결과");
        System.out.println();
    }

    public static void printLadder(Ladder ladder) {
        StringBuilder sb = new StringBuilder();
        List<Line> lines = ladder.getLines();
        lines.forEach(line -> {
            sb.append(" ".repeat(MAX)).append("|");
            List<Boolean> points = line.getPoints().stream().map(Point::getValue).collect(Collectors.toList());
            points.forEach(point -> sb.append(point ? "-".repeat(6) : " ".repeat(6)).append("|"));
            sb.append("\n");
        });
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    public static void printPlayers(List<Player> players) {
        StringBuilder sb = new StringBuilder();
        players.forEach(player -> sb.append(" ".repeat(getCountOfSpace(player.getName().length()))).append(player.getName()));
        System.out.println(sb);
    }

    public static void printPrizes(List<Prize> prizes) {
        StringBuilder sb = new StringBuilder();
        prizes.forEach(player -> sb.append(" ".repeat(getCountOfSpace(player.getWorth()))).append(player.getWorth()));
        System.out.println(sb);
    }

    private static int getCountOfSpace(String input) {
        if (input.equals("꽝")) {
            return getCountOfSpace(input.length());
        }
        return input.length();
    }

    private static int getCountOfSpace(int length) {
        return MAX - length;
    }

    public static void printResult(Map<Player, Prize> result) {
        if (result == null || result.isEmpty()) {
            throw new IllegalArgumentException("result is null or empty");
        }
        System.out.println();
        System.out.println("실행 결과");

        StringBuilder sb = new StringBuilder();
        if (result.size() == 1) {
            printOneResult(result, sb);
            return;
        }
        printAllResult(result, sb);
    }

    private static void printAllResult(Map<Player, Prize> result, StringBuilder sb) {
        result.forEach((key, value) -> sb.append(key.getName())
                .append(" : ")
                .append(value.getWorth())
                .append('\n'));
        System.out.println(sb);
    }

    private static void printOneResult(Map<Player, Prize> result, StringBuilder sb) {
        sb.append(result.values().stream().findFirst().map(Prize::getWorth).orElseThrow(IllegalArgumentException::new))
                .append('\n');
        System.out.println(sb);
    }
}
