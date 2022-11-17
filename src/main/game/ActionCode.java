package main.game;

public final class ActionCode {

    private ActionCode() {

    }

    public static final String PLACE_CARD_COMMAND = "placeCard";
    public static final String END_TURN_COMMAND = "endPlayerTurn";
    public static final String GET_CARDS_IN_HAND_COMMAND = "getCardsInHand";
    public static final String GET_PLAYER_DECK_COMMAND = "getPlayerDeck";
    public static final String GET_HERO_PLAYER_COMMAND = "getPlayerHero";
    public static final String GET_PLAYER_TURN = "getPlayerTurn";
    public static final String GET_PLAYER_MANA_COMMAND = "getPlayerMana";
    public static final String GET_CARDS_ON_TABLE = "getCardsOnTable";
    public static final String ATTACK_CARD_COMMAND = "cardUsesAttack";
    public static final String USE_ENVIRONMENT_COMMAND = "useEnvironmentCard";
    public static final String EMPTY = "empty";
    public static final String GET_CARD_AT_INDEX_COMMAND = "getCardAtPosition";
    public static final String USE_CARD_ABILITY_COMMAND = "cardUsesAbility";
    public static final String ATTACK_HERO_COMMAND = "useAttackHero";
    public static final String GET_ENVIRONMENT_CARDS_IN_HAND_COMMAND = "getEnvironmentCardsInHand";
    public static final String GET_FROZEN_CARDS_COMMAND = "getFrozenCardsOnTable";
    public static final String USE_HERO_ABILITY_COMMAND = "useHeroAbility";
    public static final String GAMES_PLAYED_COMMAND = "getTotalGamesPlayed";
    public static final String PLAYER_ONE_WINS_COMMAND = "getPlayerOneWins";
    public static final String PLAYER_TWO_WINS_COMMAND = "getPlayerTwoWins";
}
