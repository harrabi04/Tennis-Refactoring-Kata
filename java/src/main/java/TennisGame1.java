
public class TennisGame1 implements TennisGame {


    private static final String ADVANTAGE_PREFIX = "Advantage ";
    private static final String WIN_PREFIX = "Win for ";
    private static final String SCORE_SEPARATOR = "-";

    private final Player player1;
    private final Player player2;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    public void wonPoint(String playerName) {
        if (player1.getName().equals(playerName)) {
            player1.incrementScore();
        } else if (player2.getName().equals(playerName)) {
            player2.incrementScore();
        } else {
            throw new IllegalArgumentException("Unknown Player" + playerName);
        }

    }

    public String getScore() {
        if (player1.getScore() == player2.getScore()) {
            return getTiedScore(player1.getScore());
        } else if (player1.getScore() >= 4 || player2.getScore() >= 4) {
            return getAdvantageOrWinner();
        }
        return getScoreToName(player1.getScore()) + SCORE_SEPARATOR + getScoreToName(player2.getScore());
    }

    private String getAdvantageOrWinner() {
        int pointsDiff = player1.getScore() - player2.getScore();

        if (pointsDiff == 1) {
            return ADVANTAGE_PREFIX + player1.getName();
        }
        if (pointsDiff == -1) {
            return ADVANTAGE_PREFIX + player2.getName();
        }

        return WIN_PREFIX + (pointsDiff > 0
                ? player1.getName()
                : player2.getName());
    }

    private String getTiedScore(int score) {
        return switch (score) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };
    }

    private String getScoreToName(int score) {
        return switch (score) {
            case 0 -> "Love";
            case 1 -> "Fifteen";
            case 2 -> "Thirty";
            case 3 -> "Forty";
            default -> throw new IllegalArgumentException("Score cannot be " + score);
        };
    }

    private static class Player {
        public Player(String name) {
            this.name = name;
        }

        private final String name;
        private int score;

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public void incrementScore() {
            this.score++;
        }
    }
}
