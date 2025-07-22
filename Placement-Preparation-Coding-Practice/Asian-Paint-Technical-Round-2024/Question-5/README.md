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

---

## 🧠 What Pattern Is This?

This belongs to the **Column Unpivot + Aggregation Pattern**. Specifically:

- **Unpivoting Columns**: Converting multiple columns into multiple rows
- **Aggregation**: Counting frequency of each value (grouped count)

### 🔧 SQL Techniques Used:

- `UNION ALL`: Merges values from two columns into one set
- `GROUP BY`: Groups identical values
- `COUNT(*)`: Gets the total occurrence

---

### 1️⃣ **Find Total Count of Each Skill from Resume Table**

📌 **Table**: `Resumes(skill1, skill2, skill3)`
🎯 **Goal**: Count how many resumes mention each skill.

```sql
SELECT Skill, COUNT(*) AS Total_Count
FROM (
    SELECT skill1 AS Skill FROM Resumes
    UNION ALL
    SELECT skill2 FROM Resumes
    UNION ALL
    SELECT skill3 FROM Resumes
) AS AllSkills
GROUP BY Skill;
```

---

### 2️⃣ **Get Count of Each Product Bought**

📌 **Table**: `Purchases(Product1, Product2)`
🎯 **Goal**: Count how many times each product was bought.

```sql
SELECT Product, COUNT(*) AS PurchaseCount
FROM (
    SELECT Product1 AS Product FROM Purchases
    UNION ALL
    SELECT Product2 FROM Purchases
) AS AllProducts
GROUP BY Product;
```

---

### 3️⃣ **Tag Participation Count (Multiple Columns)**

📌 **Table**: `Posts(Tag1, Tag2, Tag3)`
🎯 **Goal**: Count how many times each tag was used.

```sql
SELECT Tag, COUNT(*) AS Total_Tag_Usage
FROM (
    SELECT Tag1 AS Tag FROM Posts
    UNION ALL
    SELECT Tag2 FROM Posts
    UNION ALL
    SELECT Tag3 FROM Posts
) AS AllTags
GROUP BY Tag;
```

---

### 4️⃣ **Survey Answers Aggregation**

📌 **Table**: `Survey(Q1, Q2, Q3)`
🎯 **Goal**: How many times each answer choice was selected.

```sql
SELECT Answer, COUNT(*) AS Frequency
FROM (
    SELECT Q1 AS Answer FROM Survey
    UNION ALL
    SELECT Q2 FROM Survey
    UNION ALL
    SELECT Q3 FROM Survey
) AS AllAnswers
GROUP BY Answer;
```

---

### 5️⃣ **Vote Count by Party**

📌 **Table**: `Votes(Vote1, Vote2)`
🎯 **Goal**: Total votes received by each party.

```sql
SELECT Party, COUNT(*) AS Total_Votes
FROM (
    SELECT Vote1 AS Party FROM Votes
    UNION ALL
    SELECT Vote2 FROM Votes
) AS AllVotes
GROUP BY Party;
```

---

## 📚 Similar Practice Questions

| Question                | Description                              | Sample SQL                                        |
| ----------------------- | ---------------------------------------- | ------------------------------------------------- |
| Skill Count             | Count resumes mentioning each skill      | `SELECT skill1 UNION ALL skill2 UNION ALL skill3` |
| Product Purchase Count  | Total times each product bought          | `SELECT product1 UNION ALL product2`              |
| Tag Usage               | How many times each tag is used in posts | `SELECT tag1 UNION ALL tag2 UNION ALL tag3`       |
| Survey Answer Frequency | Total responses per answer option        | `SELECT Q1 UNION ALL Q2 UNION ALL Q3`             |
| Voting Results          | Count total votes per party              | `SELECT vote1 UNION ALL vote2`                    |

---

## 🧠 Pattern

This is a classic **Unpivot & Grouping Pattern** used to analyze multi-column categorical data by turning them into rows and performing aggregation.

---
