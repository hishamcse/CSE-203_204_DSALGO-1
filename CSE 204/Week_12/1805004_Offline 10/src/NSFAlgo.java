/* ----------------- Inefficient algorithm (Not used in offline) ----------------- */

//  O(n * s * f) algorithm

// for(int dice=1;dice<=totalDices;dice++){         // dices
//    for(int sum=1;sum<=totalSum;sum++){                     // sum
//        for(int face=1;face<=faces[dice]&&sum>=face;face++){   // faces
//              diceSumLookUp[dice][sum]+=diceSumLookUp[dice-1][sum-face]%MOD;
//           }
//       }
// }