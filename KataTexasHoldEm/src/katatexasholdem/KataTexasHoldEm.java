/*
Comparing two poker hands and determing which hand wins
Reference to this kata game can be found here: http://codingdojo.org/cgi-bin/index.pl?KataPokerHands
 */
package katatexasholdem;

import java.io.IOException;
import java.util.Scanner;
import java.lang.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
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
        int whiteCards[] = new int[5];
        char whiteSuits[] = new char[5];
        int blackCards[] = new int[5];
        char blackSuits[] = new char[5];
        int remainingValue = 0;
        int whiteMaxFrequency = 0, blackMaxFrequency = 0;
        int whiteSecondMaxFrequency = 0, blackSecondMaxFrequency = 0;
        int whiteMaxValue = 0, blackMaxValue = 0;
        int whiteSecondMaxValue = 0, blackSecondMaxValue = 0;
        int whiteRank = 0, blackRank = 0;
        boolean whiteIsFlush, blackIsFlush;
        HashMap<Integer, Integer> whiteCardsFrequency = new HashMap<>();
        HashMap<Integer, Integer> blackCardsFrequency = new HashMap<>();
        final int HIGH_CARD = 1,
                PAIR = 2,
                TWO_PAIR = 3,
                THREE_OF_AKIND = 4,
                STRAIGHT = 5,
                FLUSH = 6,
                FULL_HOUSE = 7,
                FOUR_OF_AKIND = 8,
                STRAIGHT_FLUSH = 9;

        //Evaluate the white hand
        System.out.println("Please enter the cards for White Hand: ");
        wInput = KTHE.GetInput();
        whiteCards = KTHE.ConvertToArrayOfValues(wInput);
        whiteSuits = KTHE.convertToArrayOfSuits(wInput);
        Arrays.sort(whiteCards);
        whiteIsFlush = KTHE.EvaluatePokerSuits(whiteSuits);
        whiteCardsFrequency = KTHE.ConvertCardsArrayToHashMap(whiteCards);
        whiteRank = KTHE.EvaluatePokerCards(whiteCards, whiteSuits, whiteCardsFrequency);

        //Evaluate the black hand
        System.out.println("Please enter the cards for Black Hand: ");
        bInput = KTHE.GetInput();
        blackCards = KTHE.ConvertToArrayOfValues(bInput);
        blackSuits = KTHE.convertToArrayOfSuits(bInput);
        Arrays.sort(blackCards);
        blackIsFlush = KTHE.EvaluatePokerSuits(blackSuits);
        blackCardsFrequency = KTHE.ConvertCardsArrayToHashMap(whiteCards);
        blackRank = KTHE.EvaluatePokerCards(whiteCards, whiteSuits, whiteCardsFrequency);

        //compare the single cards if their ranks are equal
        remainingValue = KTHE.highCardComparation(whiteCards, blackCards);
        //Compare two hands to see which hand wins
        if (whiteRank > blackRank) {
            teamWin = "white wins";
        }
        if (whiteRank < blackRank) {
            teamWin = "black wins";
        } else {
            if (whiteRank == HIGH_CARD && blackRank == HIGH_CARD) {
                switch (remainingValue) {
                    case 0:
                        teamWin = "white wins";
                        break;
                    case 1:
                        teamWin = "black wins";
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
                    teamWin = "white wins";
                }
                if (whiteMaxValue < blackMaxValue) {
                    teamWin = "black wins";
                } else {
                    if (whiteSecondMaxValue > blackSecondMaxValue) {
                        teamWin = "white wins";
                    }
                    if (whiteSecondMaxValue < blackSecondMaxValue) {
                        teamWin = "black wins";
                    } else {
                        if (remainingValue == 0) {
                            teamWin = "white wins";
                        }
                        if (remainingValue == 1) {
                            teamWin = "black wins";
                        } else {
                            teamWin = "tie";
                        }
                    }

                }
            }
            if (whiteRank == PAIR && blackRank == PAIR) {
                whiteMaxValue = KTHE.maxValue(whiteCardsFrequency, 2);
                blackMaxValue = KTHE.maxValue(blackCardsFrequency, 2);
                if (whiteMaxValue > blackMaxValue) {
                    teamWin = "white wins";
                }
                if (whiteMaxValue < blackMaxValue) {
                    teamWin = "black wins";
                } else {
                    if (remainingValue == 0) {
                        teamWin = "white wins";
                    }
                    if (remainingValue == 1) {
                        teamWin = "black wins";
                    } else {
                        teamWin = "tie";
                    }
                }
            }
            if (whiteRank == THREE_OF_AKIND && blackRank == THREE_OF_AKIND) {
                whiteMaxValue = KTHE.maxValue(whiteCardsFrequency, 3);
                whiteMaxValue = KTHE.maxValue(whiteCardsFrequency, 3);
                if (whiteMaxValue > blackMaxValue) {
                    teamWin = "white wins";
                }
                if (whiteMaxValue < blackMaxValue) {
                    teamWin = "black wins";
                } else {
                    if (remainingValue == 0) {
                        teamWin = "white wins";
                    }
                    if (remainingValue == 1) {
                        teamWin = "black wins";
                    } else {
                        teamWin = "tie";
                    }
                }
            }
            if (whiteRank == STRAIGHT && blackRank == STRAIGHT) {
                if (whiteCards[0] > blackCards[0]) {
                    teamWin = "white wins";
                }
                if (whiteCards[0] < blackCards[0]) {
                    teamWin = "black wins";
                } else {
                    teamWin = "tie";
                }
            }
            if (whiteRank == FLUSH && blackRank == FLUSH) {
                if (remainingValue == 0) {
                        teamWin = "white wins";
                    }
                    if (remainingValue == 1) {
                        teamWin = "black wins";
                    } else {
                        teamWin = "tie";
                    }
            }
            if (whiteRank == FULL_HOUSE && blackRank == FULL_HOUSE) {
                whiteMaxValue = KTHE.maxValue(whiteCardsFrequency, 3);
                blackMaxValue = KTHE.maxValue(blackCardsFrequency, 3);
                whiteSecondMaxValue = KTHE.maxValue(whiteCardsFrequency, 2);
                blackSecondMaxValue = KTHE.maxValue(blackCardsFrequency, 2);
                if (whiteMaxValue > blackMaxValue) {
                    teamWin = "white wins";
                }
                if (whiteMaxValue < blackMaxValue) {
                    teamWin = "black wins";
                } else {
                    teamWin = "tie";
                }

            }
            if (whiteRank == FOUR_OF_AKIND && blackRank == FOUR_OF_AKIND) {
                whiteMaxValue = KTHE.maxValue(whiteCardsFrequency, 4);
                blackMaxValue = KTHE.maxValue(blackCardsFrequency, 4);
                if (whiteMaxValue > blackMaxValue) {
                    teamWin = "white wins";
                }
                if (whiteMaxValue < blackMaxValue) {
                    teamWin = "black wins";
                } else {
                    teamWin = "tie";
                }

            }
            if (whiteRank == STRAIGHT_FLUSH && blackRank == STRAIGHT_FLUSH) {
                if (whiteCards[0] > blackCards[0]) {
                    teamWin = "white wins";
                }
                if (whiteCards[0] < blackCards[0]) {
                    teamWin = "black wins";
                } else {
                    teamWin = "tie";
                }
            }
        }
        System.out.println(teamWin);
    }

    String GetInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input;
    }

    int[] ConvertToArrayOfValues(String input) {
        int[] valueCards = new int[5];
        int n = 0;

        for (char c : input.toCharArray()) {

            if (Character.isDigit(c) == true) {

                int k = Character.getNumericValue(c);
                //Check if the digit is number 10
                if (k == 1) {
                    char m = input.charAt(n + 1);
                    if (Character.isDigit(m)) {
                        k = 10;
                        n = n + 1;
                    }
                }
                valueCards[n] = k;
                n = n + 1;
                continue;

            }
            if (c == 'T' || c == 't') {
                valueCards[n] = 11;
                n = n + 1;
                continue;

            }
            if (c == 'J' || c == 'j') {
                valueCards[n] = 12;
                n = n + 1;
                continue;
            }
            if (c == 'Q' || c == 'q') {
                valueCards[n] = 13;
                n = n + 1;
                continue;
            }
            if (c == 'K' || c == 'k') {
                valueCards[n] = 14;
                n = n + 1;
                continue;
            }
            if (c == 'A' || c == 'a') {
                valueCards[n] = 15;
                n = n + 1;
            }
        }
        return valueCards;
    }

    char[] convertToArrayOfSuits(String input) {
        int n = 0;
        char[] suits = new char[5];
        for (char c : input.toCharArray()) {

            if (c == 'C' || c == 'c' || c == 'D' || c == 'd' || c == 'H' || c == 'h' || c == 'S' || c == 's') {
                suits[n] = c;
                n = n + 1;
            }
        }
        return suits;
    }

    HashMap<Integer, Integer> ConvertCardsArrayToHashMap(int pokerCards[]) {
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

    /**
     * Determining the rank of a poker hand
     */
    int EvaluatePokerCards(int pokerCards[], char pokerSuits[], HashMap<Integer, Integer> cardsFrequency) {
        int Rank = 0;
        int max;
        int secondMax;
        max = maxFrequency(cardsFrequency);
        secondMax = secondMaxFrequency(cardsFrequency);

        //STRAIGHT 
        if (pokerCards[0] - pokerCards[4] == 4) {
            Rank = 5;
        } //FLUSH
        if (EvaluatePokerSuits(pokerSuits) == true) {
            //FLUSH
            Rank = 6;
            if (pokerCards[0] - pokerCards[4] == 4) {
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
        } else {
            //HIGH_CARD
            Rank = 1;
        }

        return Rank;
    }
    //Determining if a poker hand has Flush rank

    boolean EvaluatePokerSuits(char suits[]) {
        boolean flush = true;
        for (int i = 0; i < 3; i++) {
            if (suits[i] != suits[i + 1]) {
                flush = false;
                break;
            }
        }
        return flush;
    }

    //Loop through hashmap to find the maximum count
    int maxFrequency(HashMap<Integer, Integer> cards) {

        int maximum = 0;
        for (int value : cards.values()) {
            if (value > maximum) {
                maximum = value;
            }
        }
        return maximum;
    }

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

    int maxValue(HashMap<Integer, Integer> cards, int frequency) {
        int max = 0;
        for (int n : cards.keySet()) {
            if (cards.get(n) == frequency) {
                max = n;
            }
        }
        return max;
    }

    int secondMaxValue(HashMap<Integer, Integer> cards, int frequency) {
        int secondMax = 0;
        for (int n : cards.keySet()) {
            if ((cards.get(n) == frequency) && (cards.get(n) != maxValue(cards, frequency))) {
                secondMax = n;
            }
        }
        return secondMax;
    }

    int highCardComparation(int whiteCards[], int blackCards[]) {
        int team = 3;
        for (int i = 4; i >= 0; i--) {
            if (whiteCards[i] > blackCards[i]) {
                team = 0;
                break;
            }
            if (whiteCards[i] < blackCards[i]) {
                team = 1;
                break;
            } else {
                team = 2;
            }
        }
        return team;
    }
//I think use the remaining comparation method to compare the high card case

//     String highCardComparation(int whiteCards[], int blackCards[]) {
//        String teamWin = null;
//        if (whiteCards[4] > blackCards[4]) {
//            teamWin = "white";
//        }
//        if (whiteCards[4] < blackCards[4]) {
//            teamWin = "black";
//        } else {
//            if (whiteCards[3] > blackCards[3]) {
//                teamWin = "white";
//            }
//            if (whiteCards[3] < blackCards[3]) {
//                teamWin = "black";
//            } else {
//                if (whiteCards[2] > blackCards[2]) {
//                    teamWin = "white";
//                }
//                if (whiteCards[2] < blackCards[2]) {
//                    teamWin = "black";
//                } else {
//                    if (whiteCards[1] > blackCards[1]) {
//                        teamWin = "white";
//                    }
//                    if (whiteCards[1] < blackCards[1]) {
//                        teamWin = "black";
//                    } else {
//                        if (whiteCards[0] > blackCards[0]) {
//                            teamWin = "white";
//                        }
//                        if (whiteCards[0] < blackCards[0]) {
//                            teamWin = "black";
//                        } else {
//                            teamWin = "both";
//                        }
//                    }
//                }
//            }
//
//        }
//        return teamWin;
//    }

}
