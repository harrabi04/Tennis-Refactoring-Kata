import java.util.Objects;

public class TennisGame3 implements TennisGame {

    private static final String DEUCE = "Deuce";
    private static final String ALL_SUFFIX = "-All";
    private static final String SCORE_SEPARATOR = "-";
    private static final String WIN_FOR_PREFIX = "Win for ";
    private static final String ADVANTAGE_PREFIX = "Advantage ";

    private static final String[] scoreDescription = new String[]{"Love", "Fifteen", "Thirty", "Forty"};

    private int player1Score;
    private int player2Score;
    private final String player2Name;
    private final String player1Name;

    public TennisGame3(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }


    public String getScore() {
        if (isDraw()) return handleDraw();
        if (isPreDeuce()) return formatScore();
        return handleEndGame();
    }

    private String formatScore() {
        return scoreDescription[player1Score] + SCORE_SEPARATOR + scoreDescription[player2Score];
    }

    private String handleEndGame() {
        String winningPlayer = player1Score > player2Score ? player1Name : player2Name;
        return isAdvantage() ? formatAdvantageMessage(winningPlayer) : formatWinningMessage(winningPlayer);
    }

    private static String formatWinningMessage(final String winningPlayer) {
        return WIN_FOR_PREFIX + winningPlayer;
    }

    private static String formatAdvantageMessage(final String winningPlayer) {
        return ADVANTAGE_PREFIX + winningPlayer;
    }

    private String handleDraw() {
        return isPreDeuce() ? formatTiedScore() : DEUCE;
    }

    private String formatTiedScore() {
        return scoreDescription[player1Score] + ALL_SUFFIX;
    }

    private boolean isPreDeuce() {
        return player1Score < 4
                && player2Score < 4
                && !(player1Score == 3 && player2Score == 3);
    }

    private boolean isAdvantage() {
        return Math.abs(player1Score - player2Score) == 1;
    }

    private boolean isDraw() {
        return player1Score == player2Score;
    }

    public void wonPoint(String playerName) {
        if (Objects.equals(playerName, player1Name)) {
            this.player1Score++;
        } else if (Objects.equals(playerName, player2Name)) {
            this.player2Score++;
        } else {
            throw new IllegalArgumentException("Player Name Invalid:" + playerName);
        }
    }
}
