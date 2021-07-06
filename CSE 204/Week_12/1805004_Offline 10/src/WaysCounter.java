/* --------------------------- Counts the number of ways to get required sum ------------------------- */

public class WaysCounter {

    private static final long MOD = (long) (1e9) + 7;

    public int diceSumCounter(int totalDices, int totalSum, int[] faces) {
        long[][] diceSumLookUp = new long[totalDices + 1][totalSum + 1];    // [dice][sum] = # ways to get sum with # dice
        diceSumLookUp[0][0] = 1;

        for (int dice = 1; dice <= totalDices; dice++) {         // dices
            int face = faces[dice];
            for (int sum = dice; sum <= totalSum; sum++) {                     // sum
                diceSumLookUp[dice][sum] = diceSumLookUp[dice][sum - 1] + diceSumLookUp[dice - 1][sum - 1] % MOD;

                if (sum > face) {
                    diceSumLookUp[dice][sum] -= diceSumLookUp[dice - 1][sum - face - 1] % MOD;
                }
            }
        }

        return (int) (diceSumLookUp[totalDices][totalSum] % MOD);
    }
}