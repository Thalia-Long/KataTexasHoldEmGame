/*
Comparing two poker hands and determing which hand wins
Reference to this kata game can be found here: http://codingdojo.org/cgi-bin/index.pl?KataPokerHands
 */
package katatexasholdem;

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author hienlong
 */
public class KataTexasHoldEm {

    public static void main(String[] args) {

        KataTexasHoldEm KTHE = new KataTexasHoldEm();
        String teamWin = null;
        String wInput, bInput;
        int whiteCards[];
        char whiteSuits[];
        int blackCards[];
        char blackSuits[];
        int[] remainingValue;
        int whiteMaxFrequency, blackMaxFrequency;
        int whiteSecondMaxFrequency, blackSecondMaxFrequency;
        int whiteMaxValue, blackMaxValue;
        int whiteSecondMaxValue, blackSecondMaxValue;
        int whiteRank, blackRank;
        boolean whiteIsFlush, blackIsFlush;
        HashMap<Integer, Integer> whiteCardsFrequency;
        HashMap<Integer, Integer> blackCardsFrequency;
        final int HIGH_CARD = 1,
                PAIR = 2,
                TWO_PAIR = 3,
                THREE_OF_AKIND = 4,
                STRAIGHT = 5,
                FLUSH = 6,
                FULL_HOUSE = 7,
                FOUR_OF_AKIND = 8,
                STRAIGHT_FLUSH = 9;

        /**
         * Evaluate the white hand
         */
        System.out.println("Please enter the cards for White Hand: ");
        wInput = KTHE.GetInput();
        whiteCards = KTHE.convertToArrayOfValues(wInput);
        whiteSuits = KTHE.convertToArrayOfSuits(wInput);

        Arrays.sort(whiteCards);
        whiteIsFlush = KTHE.evaluatePokerSuits(whiteSuits);
        whiteCardsFrequency = KTHE.convertCardsArrayToHashMap(whiteCards);
        whiteRank = KTHE.evaluatePokerCards(whiteCards, whiteSuits, whiteCardsFrequency);

        /**
         * Evaluate the white hand
         */
        System.out.println("Please enter the cards for Black Hand: ");
        bInput = KTHE.GetInput();
        blackCards = KTHE.convertToArrayOfValues(bInput);
        blackSuits = KTHE.convertToArrayOfSuits(bInput);
        Arrays.sort(blackCards);
        blackIsFlush = KTHE.evaluatePokerSuits(blackSuits);
        blackCardsFrequency = KTHE.convertCardsArrayToHashMap(blackCards);
        blackRank = KTHE.evaluatePokerCards(blackCards, blackSuits, blackCardsFrequency);

        //compare the single cards if their ranks are equal
        remainingValue = KTHE.highCardComparation(whiteCards, blackCards);
        //Compare two hands to see which hand wins
        if (whiteRank > blackRank) {
            teamWin = "white wins with " + KTHE.rankToString(whiteRank);
        }
        if (whiteRank < blackRank) {
            teamWin = "black wins with " + KTHE.rankToString(blackRank);
        } else {
            if (whiteRank == HIGH_CARD && blackRank == HIGH_CARD) {
                switch (remainingValue[0]) {
                    case 0:
                        if (remainingValue[1] > 10) {

                            teamWin = "white wins - with high card: " + KTHE.stringValueCards(remainingValue[1]);
                        } else {
                            teamWin = "white wins - with high card: " + remainingValue[1];
                        }
                        break;
                    case 1:
                        if (remainingValue[1] > 10) {

                            teamWin = "black wins - with high card: " + KTHE.stringValueCards(remainingValue[1]);
                        } else {
                            teamWin = "black wins - with high card: " + remainingValue[1];
                        }

                        break;
                    default:
                        teamWin = "tie";
                        break;
                }
            }

            if (whiteRank == TWO_PAIR && blackRank == TWO_PAIR) {
                whiteMaxValue = KTHE.maxValue(whiteCardsFrequency, 2);
                whiteSecondMaxValue = KTHE.secondMaxValue(whiteCardsFrequency, 2);
                blackMaxValue = KTHE.maxValue(blackCardsFrequency, 2);
                blackSecondMaxValue = KTHE.secondMaxValue(blackCardsFrequency, 2);

                if (whiteMaxValue > blackMaxValue) {
                    teamWin = "white wins with higher pair of " + whiteMaxValue;
                } else if (whiteMaxValue < blackMaxValue) {
                    teamWin = "black wins with higher pair of " + blackMaxValue;
                } else {
                    if (whiteSecondMaxValue > blackSecondMaxValue) {
                        teamWin = "white wins with higher card " + whiteSecondMaxValue;
                    } else if (whiteSecondMaxValue < blackSecondMaxValue) {
                        teamWin = "black wins with higher card " + whiteSecondMaxValue;
                    } else {
                        switch (remainingValue[0]) {
                            case 0:

                                if (remainingValue[1] > 10) {

                                    teamWin = "white wins - with high card: " + KTHE.stringValueCards(remainingValue[1]);
                                } else {
                                    teamWin = "white wins - with high card: " + remainingValue[1];
                                }
                                break;
                            case 1:
                                if (remainingValue[1] > 10) {

                                    teamWin = "white wins - with high card: " + KTHE.stringValueCards(remainingValue[1]);
                                } else {
                                    teamWin = "white wins - with high card: " + remainingValue[1];
                                }
                                break;
                            default:
                                teamWin = "tie";
                                break;
                        }
                    }

                }
            }
            if (whiteRank == PAIR && blackRank == PAIR) {
                whiteMaxValue = KTHE.maxValue(whiteCardsFrequency, 2);
                blackMaxValue = KTHE.maxValue(blackCardsFrequency, 2);
                if (whiteMaxValue > blackMaxValue) {
                    teamWin = "white wins with higher pair of " + whiteMaxValue;
                } else if (whiteMaxValue < blackMaxValue) {
                    teamWin = "black wins with higher pair of " + blackMaxValue;
                } else {
                    switch (remainingValue[0]) {
                        case 0:

                            if (remainingValue[1] > 10) {

                                teamWin = "white wins - with high card: " + KTHE.stringValueCards(remainingValue[1]);
                            } else {
                                teamWin = "white wins - with high card: " + remainingValue[1];
                            }
                            break;
                        case 1:
                            if (remainingValue[1] > 10) {

                                teamWin = "white wins - with high card: " + KTHE.stringValueCards(remainingValue[1]);
                            } else {
                                teamWin = "white wins - with high card: " + remainingValue[1];
                            }
                            break;
                        default:
                            teamWin = "tie";
                            break;
                    }
                }
            }
            if (whiteRank == THREE_OF_AKIND && blackRank == THREE_OF_AKIND) {
                whiteMaxValue = KTHE.maxValue(whiteCardsFrequency, 3);
                blackMaxValue = KTHE.maxValue(blackCardsFrequency, 3);
                if (whiteMaxValue > blackMaxValue) {
                    teamWin = "white wins with high card of tripple cards " + whiteMaxValue;
                } else if (whiteMaxValue < blackMaxValue) {
                    teamWin = "black winswith high card of tripple cards " + blackMaxValue;
                } else {
                    switch (remainingValue[0]) {
                        case 0:

                            if (remainingValue[1] > 10) {

                                teamWin = "white wins - with high card: " + KTHE.stringValueCards(remainingValue[1]);
                            } else {
                                teamWin = "white wins - with high card: " + remainingValue[1];
                            }
                            break;
                        case 1:
                            if (remainingValue[1] > 10) {

                                teamWin = "white wins - with high card: " + KTHE.stringValueCards(remainingValue[1]);
                            } else {
                                teamWin = "white wins - with high card: " + remainingValue[1];
                            }
                            break;
                        default:
                            teamWin = "tie";
                            break;
                    }
                }
            }
            if (whiteRank == STRAIGHT && blackRank == STRAIGHT) {
                if (whiteCards[4] > blackCards[4]) {
                    teamWin = "white wins with higher card: " + whiteCards[4];
                } else if (whiteCards[4] < blackCards[4]) {
                    teamWin = "black wins with higher card: " + blackCards[4];
                } else {
                    teamWin = "tie";
                }
            }
            if (whiteRank == FLUSH && blackRank == FLUSH) {
                switch (remainingValue[0]) {
                    case 0:
                        if (remainingValue[1] > 10) {

                            teamWin = "white wins - with high card: " + KTHE.stringValueCards(remainingValue[1]);
                        } else {
                            teamWin = "white wins - with high card: " + remainingValue[1];
                        }
                        break;
                    case 1:
                        if (remainingValue[1] > 10) {

                            teamWin = "white wins - with high card: " + KTHE.stringValueCards(remainingValue[1]);
                        } else {
                            teamWin = "white wins - with high card: " + remainingValue[1];
                        }
                        break;
                    default:
                        teamWin = "tie";
                        break;
                }
            }
            if (whiteRank == FULL_HOUSE && blackRank == FULL_HOUSE) {
                whiteMaxValue = KTHE.maxValue(whiteCardsFrequency, 3);
                blackMaxValue = KTHE.maxValue(blackCardsFrequency, 3);
                whiteSecondMaxValue = KTHE.maxValue(whiteCardsFrequency, 2);
                blackSecondMaxValue = KTHE.maxValue(blackCardsFrequency, 2);
                if (whiteMaxValue > blackMaxValue) {
                    teamWin = "white wins with higher tripple cards of " + whiteMaxValue;
                } else if (whiteMaxValue < blackMaxValue) {
                    teamWin = "black wins with higher tripple cards of " + blackMaxValue;
                } else {
                    if (whiteSecondMaxValue > blackSecondMaxValue) {
                        teamWin = "white wins with higher double cards of " + whiteSecondMaxValue;
                    } else if (whiteSecondMaxValue < blackSecondMaxValue) {
                        teamWin = "black wins with higher double cards of " + blackSecondMaxValue;
                    } else {
                        teamWin = "tie";
                    }
                }

            }
            if (whiteRank == FOUR_OF_AKIND && blackRank == FOUR_OF_AKIND) {
                whiteMaxValue = KTHE.maxValue(whiteCardsFrequency, 4);
                blackMaxValue = KTHE.maxValue(blackCardsFrequency, 4);
                if (whiteMaxValue > blackMaxValue) {
                    teamWin = "white wins with the higher quaterble cards of " + whiteMaxValue;
                } else if (whiteMaxValue < blackMaxValue) {
                    teamWin = "black wins with the higher quaterble cards of " + blackMaxValue;
                } else {
                    teamWin = "tie";
                }

            }
            if (whiteRank == STRAIGHT_FLUSH && blackRank == STRAIGHT_FLUSH) {
                if (whiteCards[4] > blackCards[4]) {
                    teamWin = "white wins with the higher card: " + whiteCards[4];
                } else if (whiteCards[4] < blackCards[4]) {
                    teamWin = "black wins with the higher card: " + blackCards[4];
                } else {
                    teamWin = "tie";
                }
            }
        }
        System.out.println(teamWin);
    }
//Get input from user and validate the input

    String GetInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        input = input.replace(" ", "").trim();
        int j = input.toCharArray().length;
        if (j != 10) {
            System.out.println("You enter incorrect input. Please enter it again: ");
            input = GetInput();
        } else {
            for (int i = 0; i < 10; i++) {
                char aChar;
                aChar = input.charAt(i);
                if ((i % 2) == 0 && "23456789TJQKAtjqka".indexOf(aChar) == -1) {
                    System.out.println("You enter incorrect input. Card value has to be one of these '2 3 4 5 6 7 8 9 T J Q K A'. Please enter it again: ");
                    input = GetInput();
                } else if ((i % 2) == 1 && "CDHScdhs".indexOf(aChar) == -1) {
                    System.out.println("You enter incorrect input. Card suit has to be one of these 'C D H S'. Please enter it again: ");
                    input = GetInput();

                }
            }
        }
        return input;
    }
//Convert the input to array of value cards. Such as 2 3 4 5 6

    int[] convertToArrayOfValues(String in) {
        int[] valueCards = new int[5];
        int n = 0;
        String input;
        input = in.replace(" ", "").trim();
        int j = input.toCharArray().length;
        if (j != 10) {
            System.out.println("You enter incorrect input. Please enter it again: ");
            input = GetInput();
            valueCards = convertToArrayOfValues(input);

        } else {
            for (char a : input.toCharArray()) {

                if (Character.isDigit(a) == true) {

                    int k = Character.getNumericValue(a);
                    valueCards[n] = k;
                    n = n + 1;
                    continue;

                }
                if (a == 'T' || a == 't') {
                    valueCards[n] = 10;
                    n = n + 1;
                    continue;

                }
                if (a == 'J' || a == 'j') {
                    valueCards[n] = 11;
                    n = n + 1;
                    continue;
                }
                if (a == 'Q' || a == 'q') {
                    valueCards[n] = 12;
                    n = n + 1;
                    continue;
                }
                if (a == 'K' || a == 'k') {
                    valueCards[n] = 13;
                    n = n + 1;
                    continue;
                }
                if (a == 'A' || a == 'a') {
                    valueCards[n] = 14;
                    n = n + 1;
                }
            }
        }

        return valueCards;
    }
//Convert the input to array of suits such as C D H S

    char[] convertToArrayOfSuits(String in) {
        int n = 0;
        String input;
        input = in.replace(" ", "").trim();
        char[] suits = new char[5];
        int j = input.toCharArray().length;
        if (j != 10) {
            System.out.println("You enter incorrect input. Please enter it again: ");
            input = GetInput();
            suits = convertToArrayOfSuits(input);

        } else {
            for (char a : input.toCharArray()) {

                if (a == 'C' || a == 'c' || a == 'D' || a == 'd' || a == 'H' || a == 'h' || a == 'S' || a == 's') {
                    suits[n] = a;
                    n = n + 1;

                }
            }
        }
        return suits;
    }
// Convert the poker cards to hashmap that contains each unique card and its occurencies

    HashMap<Integer, Integer> convertCardsArrayToHashMap(int pokerCards[]) {
        HashMap<Integer, Integer> cardsFrequency;
        cardsFrequency = new HashMap<>();
        for (int a : pokerCards) {
            if (cardsFrequency.containsKey(a)) {
                cardsFrequency.put(a, cardsFrequency.get(a) + 1);
            } else {
                cardsFrequency.put(a, 1);
            }
        }
        return cardsFrequency;
    }

    //Determining the rank of a poker hand
    int evaluatePokerCards(int pokerCards[], char pokerSuits[], HashMap<Integer, Integer> cardsFrequency) {

        int Rank = 1; //Rank is HIGH CARD by default
        int max;
        int secondMax;
        max = maxFrequency(cardsFrequency);
        secondMax = secondMaxFrequency(cardsFrequency);

        if (pokerCards[4] - pokerCards[0] == 4) {
            //STRAIGHT
            Rank = 5;
        }
        if (evaluatePokerSuits(pokerSuits) == true) {
            //FLUSH
            Rank = 6;
            if (pokerCards[4] - pokerCards[0] == 4) {
                //STRAIGHT_FLUSH
                Rank = 9;
            }
        }
        if (max == 4) {
            //FOUR_OF_AKIND
            Rank = 8;
        }
        if (max == 3) {
            //THREE_OF_AKIND
            Rank = 4;
            if (secondMax == 2) {
                //FULL_HOUSE
                Rank = 7;
            }

        }
        if (max == 2 && cardsFrequency.size() == 3) {
            //TWO_PAIR
            Rank = 3;
        }
        if (max == 2 && cardsFrequency.size() == 4) {
            //PAIR
            Rank = 2;
        }

        return Rank;
    }
    //Determining if a poker hand has Flush rank

    boolean evaluatePokerSuits(char suits[]) {
        boolean flush = true;
        for (int i = 0; i < 4; i++) {
            if (suits[i] != suits[i + 1]) {
                flush = false;
                break;
            }
        }
        return flush;
    }

    //Find the maximum count in the HashMap that contains the cards, and their frequencies
    int maxFrequency(HashMap<Integer, Integer> cards) {

        int maximum = 0;
        for (int value : cards.values()) {
            if (value > maximum) {
                maximum = value;
            }
        }
        return maximum;
    }

    //Find the second maximum count in the HashMap that contains the cards, and their frequencies
    int secondMaxFrequency(HashMap<Integer, Integer> cards) {
        int secondMax = 0;
        int max;
        max = maxFrequency(cards);
        for (int value : cards.values()) {
            if (value > secondMax && value != max) {
                secondMax = value;
            }
        }
        return secondMax;

    }
//Find the maximum value of their highest rank. For example, the highest value of the , double, tripple cards

    int maxValue(HashMap<Integer, Integer> cards, int frequency) {
        int max = 0;
        for (int n : cards.keySet()) {
            if (cards.get(n) == frequency) {
                max = n;
            }
        }
        return max;
    }
//Find the second maximum value of their highest rank. For example, the highest value of the , double, tripple cards

    int secondMaxValue(HashMap<Integer, Integer> cards, int frequency) {
        int secondMax = 0;
        for (int n : cards.keySet()) {
            if ((cards.get(n) == frequency) && (cards.get(n) != maxValue(cards, frequency))) {
                secondMax = n;
            }
        }
        return secondMax;
    }
//Decide which team wins by comparing their single cards

    int[] highCardComparation(int whiteCards[], int blackCards[]) {
        int[] team = new int[2];
        for (int i = 4; i >= 0; i--) {
            if (whiteCards[i] > blackCards[i]) {
                team[0] = 0;
                team[1] = whiteCards[i];
                break;
            }
            if (whiteCards[i] < blackCards[i]) {
                team[0] = 1;
                team[1] = blackCards[i];
                break;
            } else {
                team[0] = 2;
            }
        }
        return team;
    }
//Convert to the original J, Q, K, A from their integer values

    String stringValueCards(int v) {
        String value = null;
        switch (v) {

            case 11:
                value = "Jack";
                break;
            case 12:
                value = "Queen";
                break;
            case 13:
                value = "King";
                break;
            case 14:
                value = "Ace";
                break;
        }
        return value;
    }
// Convert the rank of each team to its name for their rank

    String rankToString(int v) {
        String value = null;
        switch (v) {
            case 1:
                value = "High Card";
                break;
            case 2:
                value = "Pair";
                break;
            case 3:
                value = "Two Pair";
                break;
            case 4:
                value = "Three of A Kind";
                break;
            case 5:
                value = "Straight";
                break;
            case 6:
                value = "Flush";
                break;
            case 7:
                value = "Full House";
                break;
            case 8:
                value = "Four of A Kind";
                break;
            case 9:
                value = "Straight Flush";
                break;
        }
        return value;
    }
}
