# 📘 SQL Challenge: Latest-Day Book Sales

## 🧩 Problem Statement

You're given a table named `books` with the following columns:

| Column | Type    | Description                 |
| ------ | ------- | --------------------------- |
| Book   | VARCHAR | Name of the book            |
| Sold   | INTEGER | Number of copies sold       |
| Date   | DATE    | Date when the book was sold |

### 🎯 Task

Write an SQL query that:

1. Finds the **latest date** in the `books` table.
2. Calculates the **total number of books sold for each book on that latest date**.
3. Returns a list with the book name and total sales.

**⚠️ Constraint:**

> You must **not hardcode** the date (like `'2023-06-25'`).
> Your solution must dynamically adapt to whatever the latest date is.

---

## ✅ Step-by-Step Solution

### 🧠 Step 1: Find the Latest Date

Use the `MAX()` function to get the most recent date:

```sql
SELECT MAX(Date) FROM books;
```

```sql
-- Alternative way to get the latest date
SELECT Date FROM books ORDER BY Date DESC LIMIT 1;
```

---

### 🧠 Step 2: Filter Records for the Latest Date

Filter the table to only include rows where the date equals the latest date:

```sql
SELECT * FROM books
WHERE Date = (SELECT MAX(Date) FROM books);
```

---

### 🧠 Step 3: Group by Book and Sum Sales

Group the result by `Book` and calculate total sales using `SUM(Sold)`:

```sql
SELECT Book, SUM(Sold) AS Total
FROM books
WHERE Date = (SELECT MAX(Date) FROM books)
GROUP BY Book;
```

---

## ✅ Final SQL Query

```sql
SELECT Book, SUM(Sold) AS Total
FROM books
WHERE Date = (SELECT MAX(Date) FROM books)
GROUP BY Book;
```

---

## ✅ Sample Output

Suppose the data in the `books` table looks like:

| Book         | Sold | Date       |
| ------------ | ---- | ---------- |
| Emma         | 10   | 2023-07-20 |
| Jane Eyre    | 5    | 2023-07-20 |
| Emma         | 20   | 2023-07-22 |
| Jane Eyre    | 20   | 2023-07-22 |
| Oliver Twist | 5    | 2023-07-22 |
| Kim          | 0    | 2023-07-22 |

The query returns:

| Book         | Total |
| ------------ | ----- |
| Emma         | 20    |
| Jane Eyre    | 20    |
| Oliver Twist | 5     |
| Kim          | 0     |

---

## 📐 Pattern in This Problem

This is a classic **SQL aggregation + filtering with dynamic subquery** problem.

### Concepts Involved:

- `MAX()` to get latest value
- `GROUP BY` to aggregate results
- `SUM()` to total values
- **Subqueries** for dynamic filtering
- Filtering by derived values (non-hardcoded logic)

---

## 🔁 Related Question Variants (with Query Examples)

### 1. 🔎 **Top-selling book on the latest date**

```sql
SELECT Book, SUM(Sold) AS Total
FROM books
WHERE Date = (SELECT MAX(Date) FROM books)
GROUP BY Book
ORDER BY Total DESC
LIMIT 1;
```

---

### 2. 📅 **Total sales on each date**

```sql
SELECT Date, SUM(Sold) AS TotalSales
FROM books
GROUP BY Date
ORDER BY Date DESC;
```

---

### 3. 🥇 **Overall best-selling book**

```sql
SELECT Book, SUM(Sold) AS Total
FROM books
GROUP BY Book
ORDER BY Total DESC
LIMIT 1;
```

---

### 4. 📘 **Book with zero sales on the latest date**

```sql
SELECT Book
FROM books
WHERE Date = (SELECT MAX(Date) FROM books)
GROUP BY Book
HAVING SUM(Sold) = 0;
```

---

### 5. 📊 **Daily sales breakdown per book**

```sql
SELECT Date, Book, SUM(Sold) AS DailyTotal
FROM books
GROUP BY Date, Book
ORDER BY Date DESC;
```

---

## 🧠 Summary

| Feature               | Implemented? |
| --------------------- | ------------ |
| Dynamic date handling | ✅           |
| Aggregation (SUM)     | ✅           |
| Grouping (GROUP BY)   | ✅           |
| Filtering by subquery | ✅           |
| Constraint respected  | ✅           |

---
