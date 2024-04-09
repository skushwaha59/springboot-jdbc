### Employees Table:

1. **What is the employee number of John Doe?**
   
   ```sql
   SELECT emp_no FROM employees WHERE first_name = 'John' AND last_name = 'Doe';
   ```

2. **When was Jane Smith born?**
   
   ```sql
   SELECT birth_date FROM employees WHERE first_name = 'Jane' AND last_name = 'Smith';
   ```

3. **What is the gender of employee with emp_no 10001?**
   
   ```sql
   SELECT gender FROM employees WHERE emp_no = 10001;
   ```

4. **Who was hired on 1990-12-17?**
   
   ```sql
   SELECT * FROM employees WHERE hire_date = '1990-12-17';
   ```

5. **How many employees were hired in 1992?**
   
   ```sql
   SELECT COUNT(*) FROM employees WHERE hire_date BETWEEN '1992-01-01' AND '1992-12-31';
   ```

6. **Who are the employees with a first name starting with 'A'?**
   
   ```sql
   SELECT * FROM employees WHERE first_name LIKE 'A%';
   ```

7. **How many employees are there in total?**
   
   ```sql
   SELECT COUNT(*) FROM employees;
   ```

8. **List the employees in alphabetical order of their last names.**
   
   ```sql
   SELECT * FROM employees ORDER BY last_name;
   ```

9. **Who is the youngest employee?**
   
   ```sql
   SELECT * FROM employees ORDER BY birth_date DESC LIMIT 1;
   ```

10. **What is the average age of employees?**
   
    ```sql
    SELECT AVG(DATEDIFF(CURRENT_DATE, birth_date)) AS average_age FROM employees;
    ```

11. **How many male employees are there?**
    
    ```sql
    SELECT COUNT(*) FROM employees WHERE gender = 'M';
    ```

12. **Who is the longest-serving employee?**
    
    ```sql
    SELECT * FROM employees ORDER BY hire_date LIMIT 1;
    ```

13. **List the employees hired before 1990-01-01.**
    
    ```sql
    SELECT * FROM employees WHERE hire_date < '1990-01-01';
    ```

14. **Who is the oldest employee?**
    
    ```sql
    SELECT * FROM employees ORDER BY birth_date LIMIT 1;
    ```

15. **How many female employees were hired in 1995?**
    
    ```sql
    SELECT COUNT(*) FROM employees WHERE gender = 'F' AND YEAR(hire_date) = 1995;
    ```

These SQL queries can be used to retrieve specific information about the employees from the Employees Table in the database.

### Departments Table:

16. **What is the name of the department with dept_no 'd001'?**
   
   ```sql
   SELECT dept_name FROM departments WHERE dept_no = 'd001';
   ```

17. **How many departments are there?**
   
   ```sql
   SELECT COUNT(*) FROM departments;
   ```

18. **List the departments in alphabetical order.**
   
   ```sql
   SELECT * FROM departments ORDER BY dept_name;
   ```

19. **Which department has the most employees?**
   
   ```sql
   SELECT d.dept_name
   FROM departments d
   JOIN dept_emp de ON d.dept_no = de.dept_no
   GROUP BY d.dept_no
   ORDER BY COUNT(de.emp_no) DESC
   LIMIT 1;
   ```

20. **List the departments with more than 100 employees.**
   
   ```sql
   SELECT d.dept_name
   FROM departments d
   JOIN dept_emp de ON d.dept_no = de.dept_no
   GROUP BY d.dept_no
   HAVING COUNT(de.emp_no) > 100;
   ```

21. **How many departments have 'engineering' in their name?**
    
    ```sql
    SELECT COUNT(*) FROM departments WHERE dept_name LIKE '%engineering%';
    ```

22. **List the departments with no employees.**
    
    ```sql
    SELECT d.dept_name
    FROM departments d
    LEFT JOIN dept_emp de ON d.dept_no = de.dept_no
    WHERE de.emp_no IS NULL;
    ```

23. **Which department has the highest department number?**
    
    ```sql
    SELECT dept_name
    FROM departments
    ORDER BY dept_no DESC
    LIMIT 1;
    ```

### Dept_manager Table:

24. **Who managed the department 'd001'?**

   ```sql
   SELECT e.first_name, e.last_name
   FROM employees e
   JOIN dept_manager dm ON e.emp_no = dm.emp_no
   WHERE dm.dept_no = 'd001';
   ```

25. **Who is the current manager of the department 'd002'?**

   ```sql
   SELECT e.first_name, e.last_name
   FROM employees e
   JOIN dept_manager dm ON e.emp_no = dm.emp_no
   WHERE dm.dept_no = 'd002' AND dm.to_date = '9999-01-01';
   ```

26. **How many managers have managed multiple departments?**

   ```sql
   SELECT COUNT(*) 
   FROM (
       SELECT emp_no
       FROM dept_manager
       GROUP BY emp_no
       HAVING COUNT(DISTINCT dept_no) > 1
   ) AS multi_dept_managers;
   ```

27. **List the managers who managed 'd003' after 1995.**

   ```sql
   SELECT e.first_name, e.last_name
   FROM employees e
   JOIN dept_manager dm ON e.emp_no = dm.emp_no
   WHERE dm.dept_no = 'd003' AND dm.from_date > '1995-01-01';
   ```

28. **Who managed the most departments?**

   ```sql
   SELECT e.first_name, e.last_name
   FROM employees e
   JOIN (
       SELECT emp_no, COUNT(DISTINCT dept_no) AS dept_count
       FROM dept_manager
       GROUP BY emp_no
       ORDER BY dept_count DESC
       LIMIT 1
   ) AS dm ON e.emp_no = dm.emp_no;
   ```

29. **How many managers have managed only one department?**

   ```sql
   SELECT COUNT(*) 
   FROM (
       SELECT emp_no
       FROM dept_manager
       GROUP BY emp_no
       HAVING COUNT(DISTINCT dept_no) = 1
   ) AS single_dept_managers;
   ```


### Dept_emp Table:

30. **How many employees are currently in department 'd004'?**

   ```sql
   SELECT COUNT(*) 
   FROM dept_emp
   WHERE dept_no = 'd004' AND to_date = '9999-01-01';
   ```

31. **List the employees who were in department 'd003' in 2000.**

   ```sql
   SELECT e.first_name, e.last_name
   FROM employees e
   JOIN dept_emp de ON e.emp_no = de.emp_no
   WHERE de.dept_no = 'd003' AND de.from_date <= '2000-12-31' AND de.to_date >= '2000-01-01';
   ```

32. **Who is the longest-serving employee in department 'd005'?**

   ```sql
   SELECT e.first_name, e.last_name
   FROM employees e
   JOIN (
       SELECT emp_no, DATEDIFF(MAX(to_date), MIN(from_date)) AS service_length
       FROM dept_emp
       WHERE dept_no = 'd005'
       GROUP BY emp_no
       ORDER BY service_length DESC
       LIMIT 1
   ) AS emp_service ON e.emp_no = emp_service.emp_no;
   ```

33. **How many employees have worked in more than one department?**

   ```sql
   SELECT COUNT(*) 
   FROM (
       SELECT emp_no
       FROM dept_emp
       GROUP BY emp_no
       HAVING COUNT(DISTINCT dept_no) > 1
   ) AS multi_dept_employees;
   ```

34. **List the employees who have never changed departments.**

   ```sql
   SELECT e.first_name, e.last_name
   FROM employees e
   WHERE NOT EXISTS (
       SELECT 1
       FROM dept_emp de
       WHERE e.emp_no = de.emp_no
       GROUP BY de.emp_no
       HAVING COUNT(DISTINCT de.dept_no) > 1
   );
   ```


### Titles Table:

35. **What is the current job title of employee with emp_no 10002?**

   ```sql
   SELECT title
   FROM titles
   WHERE emp_no = 10002 AND to_date = '9999-01-01';
   ```

36. **List all the job titles held by employee with emp_no 10003.**

   ```sql
   SELECT title
   FROM titles
   WHERE emp_no = 10003;
   ```

37. **Who held the job title 'Senior Engineer' in 1995?**

   ```sql
   SELECT e.first_name, e.last_name
   FROM employees e
   JOIN titles t ON e.emp_no = t.emp_no
   WHERE title = 'Senior Engineer' AND from_date <= '1995-12-31' AND to_date >= '1995-01-01';
   ```

38. **How many different job titles are there?**

   ```sql
   SELECT COUNT(DISTINCT title) AS total_job_titles
   FROM titles;
   ```

39. **List the job titles in alphabetical order.**

   ```sql
   SELECT DISTINCT title
   FROM titles
   ORDER BY title ASC;
   ```

40. **How many employees have the job title 'Manager'?**

   ```sql
   SELECT COUNT(DISTINCT emp_no) AS total_managers
   FROM titles
   WHERE title = 'Manager';
   ```

### Salaries Table:

41. **What is the current salary of employee with emp_no 10004?**

   ```sql
   SELECT salary
   FROM salaries
   WHERE emp_no = 10004 AND to_date = '9999-01-01';
   ```

42. **List the salaries of employee with emp_no 10005 over the years.**

   ```sql
   SELECT from_date, to_date, salary
   FROM salaries
   WHERE emp_no = 10005;
   ```

43. **Who had the highest salary in 1990?**

   ```sql
   SELECT e.first_name, e.last_name, s.salary
   FROM employees e
   JOIN salaries s ON e.emp_no = s.emp_no
   WHERE from_date <= '1990-12-31' AND to_date >= '1990-01-01'
   ORDER BY salary DESC
   LIMIT 1;
   ```

44. **How many employees have had a salary increase of more than 100%?**

   ```sql
   SELECT COUNT(DISTINCT s1.emp_no) AS total_employees
   FROM salaries s1
   JOIN salaries s2 ON s1.emp_no = s2.emp_no
   WHERE s1.salary > s2.salary * 2 AND s1.from_date > s2.to_date;
   ```

45. **List the employees who have had the same salary for more than 5 years.**

   ```sql
   SELECT e.first_name, e.last_name, s.from_date, s.to_date, s.salary
   FROM employees e
   JOIN salaries s ON e.emp_no = s.emp_no
   WHERE to_date - from_date >= INTERVAL '5 year';
   ```


### General Questions:

46. **How many employees have a salary less than $50,000?**

    ```sql
    SELECT COUNT(*)
    FROM salaries
    WHERE salary < 50000;
    ```

47. **List the employees who have never been managers.**

    ```sql
    SELECT e.first_name, e.last_name
    FROM employees e
    WHERE e.emp_no NOT IN (SELECT dm.emp_no FROM dept_manager dm);
    ```

48. **Who is the highest-paid employee currently?**

    ```sql
    SELECT e.first_name, e.last_name, s.salary
    FROM employees e
    JOIN salaries s ON e.emp_no = s.emp_no
    WHERE s.to_date = '9999-01-01'
    ORDER BY s.salary DESC
    LIMIT 1;
    ```

49. **How many employees have had the job title 'Engineer'?**

    ```sql
    SELECT COUNT(*)
    FROM titles
    WHERE title = 'Engineer';
    ```

50. **List the employees who were hired before their birth date.**

    ```sql
    SELECT e.first_name, e.last_name
    FROM employees e
    WHERE e.hire_date < e.birth_date;
    ```

51. **Who are the employees who have never changed job titles?**

    ```sql
    SELECT e.first_name, e.last_name
    FROM employees e
    WHERE e.emp_no NOT IN (SELECT t.emp_no FROM titles t GROUP BY t.emp_no HAVING COUNT(DISTINCT title) > 1);
    ```

52. **How many employees have had a salary increase every year?**

    ?

53. **List the employees who have had more than 5 job titles.**

    ```sql
    SELECT e.first_name, e.last_name
    FROM employees e
    JOIN titles t ON e.emp_no = t.emp_no
    GROUP BY e.emp_no
    HAVING COUNT(DISTINCT t.title) > 5;
    ```

54. **Who is the employee with the highest number of job title changes?**

    ```sql
    SELECT e.first_name, e.last_name
    FROM employees e
    JOIN titles t ON e.emp_no = t.emp_no
    GROUP BY e.emp_no
    ORDER BY COUNT(DISTINCT t.title) DESC
    LIMIT 1;
    ```

55. **How many employees have had a salary decrease?**

   ?

56. **List the employees who have had a salary decrease.**

   ?

57. **Who is the employee with the lowest current salary?**

    ```sql
    SELECT e.first_name, e.last_name, s.salary
    FROM employees e
    JOIN salaries s ON e.emp_no = s.emp_no
    WHERE s.to_date = '9999-01-01'
    ORDER BY s.salary ASC
    LIMIT 1;
    ```

58. **How many employees have had a salary increase every year?**

    ?

65. **List the employees who have had the same job title for more than 10 years.**

    ```sql
    SELECT e.first_name, e.last_name, t.title, t.from_date, t.to_date
    FROM employees e
    JOIN titles t ON e.emp_no = t.emp_no
    WHERE t.to_date = '9999-01-01'
    GROUP BY e.emp_no, t.title
    HAVING DATEDIFF('2022-04-05', t.from_date) >= 3652; -- 10 years in days
    ```

66. **Who is the employee with the longest job title?**

    ?
