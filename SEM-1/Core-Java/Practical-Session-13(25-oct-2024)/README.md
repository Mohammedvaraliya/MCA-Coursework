### **An explanation of the logic employed in the code.…**

1. **Example 1: Decimal = 10**
   1. **Steps of Conversion:**
      1. Start with `decimal = 10`.
      2. While `decimal > 0`, perform the following steps:
         1. **Step 1**: `10 % 2 = 0` (remainder = 0). Append `0` to `binary`.
            Update `decimal = 10 / 2 = 5`.
            Binary so far: `"0"`.
         2. **Step 2**: `5 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 5 / 2 = 2`.
            Binary so far: `"01"`.
         3. **Step 3**: `2 % 2 = 0` (remainder = 0). Append `0` to `binary`.
            Update `decimal = 2 / 2 = 1`.
            Binary so far: `"010"`.
         4. **Step 4**: `1 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 1 / 2 = 0`.
            Binary so far: `"0101"`.
      3. At this point, `decimal = 0`, so exit the loop.
      4. Reverse the string `"0101"` to get `"1010"`.
   2. **Result:**
      1. Binary representation of `10` is **1010**.
2. **Example 2: Decimal = 31**
   1. **Steps of Conversion:**
      1. Start with `decimal = 31`.
      2. While `decimal > 0`, perform the following steps:
         1. **Step 1**: `31 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 31 / 2 = 15`.
            Binary so far: `"1"`.
         2. **Step 2**: `15 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 15 / 2 = 7`.
            Binary so far: `"11"`.
         3. **Step 3**: `7 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 7 / 2 = 3`.
            Binary so far: `"111"`.
         4. **Step 4**: `3 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 3 / 2 = 1`.
            Binary so far: `"1111"`.
         5. **Step 5**: `1 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 1 / 2 = 0`.
            Binary so far: `"11111"`.
      3. At this point, `decimal = 0`, so exit the loop.
      4. Reverse the string `"11111"` to get `"11111"` (no change).
   2. **Result:**
      1. Binary representation of `31` is **11111**.
3. **Example 3: Decimal = 7**
   1. **Steps of Conversion:**
      1. Start with `decimal = 7`.
      2. While `decimal > 0`, perform the following steps:
         1. **Step 1**: `7 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 7 / 2 = 3`.
            Binary so far: `"1"`.
         2. **Step 2**: `3 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 3 / 2 = 1`.
            Binary so far: `"11"`.
         3. **Step 3**: `1 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 1 / 2 = 0`.
            Binary so far: `"111"`.
      3. At this point, `decimal = 0`, so exit the loop.
      4. Reverse the string `"111"` to get `"111"` (no change).
   2. **Result:**
      1. Binary representation of `7` is **111**.
4. **Example 4: Decimal = 0**
   1. **Steps of Conversion:**
      1. Start with `decimal = 0`.
      2. Since `decimal = 0`, the special case is handled:
         Return `"0"` directly.
   2. **Result:**
      1. Binary representation of `0` is **0**.
5. **Example 5: Decimal = 25**
   1. **Steps of Conversion:**
      1. Start with `decimal = 25`.
      2. While `decimal > 0`, perform the following steps:
         1. **Step 1**: `25 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 25 / 2 = 12`.
            Binary so far: `"1"`.
         2. **Step 2**: `12 % 2 = 0` (remainder = 0). Append `0` to `binary`.
            Update `decimal = 12 / 2 = 6`.
            Binary so far: `"10"`.
         3. **Step 3**: `6 % 2 = 0` (remainder = 0). Append `0` to `binary`.
            Update `decimal = 6 / 2 = 3`.
            Binary so far: `"100"`.
         4. **Step 4**: `3 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 3 / 2 = 1`.
            Binary so far: `"1001"`.
         5. **Step 5**: `1 % 2 = 1` (remainder = 1). Append `1` to `binary`.
            Update `decimal = 1 / 2 = 0`.
            Binary so far: `"10011"`.
      3. At this point, `decimal = 0`, so exit the loop.
      4. Reverse the string `"10011"` to get `"11001"`.
   2. **Result:**
      1. Binary representation of `25` is **11001**.
