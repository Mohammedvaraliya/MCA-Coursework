## 📘 Problem Explanation

You're given a table with three columns:

- **Book**: Name of the book
- **Sold**: Number of copies sold
- **Date**: Date of sale

Your task is to write an SQL query that:

- Finds the **latest date** in the table.
- Calculates the **total number of books sold** for each book **on that latest date**.
- Displays the result as a list of books with their total sales.

**Important constraint**: You must **not hardcode** the date (e.g., '25-Jun-2016') in your query.

---

### 🧠 Step-by-Step Solution

#### ✅ Step 1: Find the Latest Date

Use the `MAX()` function to get the most recent date:

```sql
SELECT MAX(Date) FROM books;
```

#### ✅ Step 2: Filter Records by Latest Date

Use the result from Step 1 to get only the rows from that date:

```sql
SELECT * FROM books
WHERE Date = (SELECT MAX(Date) FROM books);
```

#### ✅ Step 3: Group by Book Name

Group the filtered records by book name so you can total the sales:

```sql
GROUP BY Book
```

#### ✅ Step 4: Sum the Sales

Use `SUM(Sold)` to calculate total sales for each book:

```sql
SELECT Book, SUM(Sold) AS Total
FROM books
WHERE Date = (SELECT MAX(Date) FROM books)
GROUP BY Book;
```

---

### ✅ Final SQL Query

```sql
SELECT Book, SUM(Sold) AS Total
FROM books
WHERE Date = (SELECT MAX(Date) FROM books)
GROUP BY Book;
```

---

### 🧾 Output Example

Based on the sample data, the output would be:

| Book         | Total |
| ------------ | ----- |
| Emma         | 30    |
| Jane Eyre    | 25    |
| Oliver Twist | 5     |
| Kim          | 0     |
