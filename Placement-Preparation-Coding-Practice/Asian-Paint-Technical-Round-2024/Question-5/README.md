## 🧩 Problem Explanation

You're given a table called `StudentInterests` with three columns:

- `Student`: Student ID (e.g., S1, S2, …)
- `Int1`: First domain of interest (e.g., DS, OS, Networks)
- `Int2`: Second domain of interest

### 🎯 Goal:

Write an SQL query that:

- Lists each **unique domain** of interest.
- Shows the **total number of students** interested in that domain (whether it's their first or second interest).

---

## 🛠️ Step-by-Step Solution

### ✅ Step 1: Combine Both Interest Columns

We need to treat `Int1` and `Int2` as one combined list of interests. Use `UNION ALL` to stack them:

```sql
SELECT Int1 AS Interest FROM StudentInterests
UNION ALL
SELECT Int2 AS Interest FROM StudentInterests
```

This gives us a single column with all interests from both `Int1` and `Int2`.

---

### ✅ Step 2: Count Each Interest

Now group by the interest and count how many times each appears:

```sql
SELECT Interest, COUNT(*) AS Total_Students
FROM (
    SELECT Int1 AS Interest FROM StudentInterests
    UNION ALL
    SELECT Int2 AS Interest FROM StudentInterests
) AS AllInterests
GROUP BY Interest;
```

---

## ✅ Final SQL Query

```sql
SELECT Interest, COUNT(*) AS Total_Students
FROM (
    SELECT Int1 AS Interest FROM StudentInterests
    UNION ALL
    SELECT Int2 AS Interest FROM StudentInterests
) AS AllInterests
GROUP BY Interest;
```

---

## 🧾 Output

| Interest | Total_Students |
| -------- | -------------- |
| DB       | 3              |
| DS       | 4              |
| Networks | 4              |
| OS       | 5              |

---

## 🧠 Simple Explanation

- You stack both interest columns into one list.
- Then you count how many times each domain appears.
- That gives you the total number of students interested in each domain.
