DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.CustomerID, c.Name, a.AccountID, a.AccountType,
               t.TransactionID, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE t.TransactionDate >= TRUNC(SYSDATE, 'MM')
          AND t.TransactionDate < ADD_MONTHS(TRUNC(SYSDATE, 'MM'), 1)
        ORDER BY c.CustomerID, t.TransactionDate;

    v_prev_customer_id NUMBER := -1;
BEGIN
    FOR rec IN GenerateMonthlyStatements LOOP
        IF rec.CustomerID != v_prev_customer_id THEN
            IF v_prev_customer_id != -1 THEN
                DBMS_OUTPUT.PUT_LINE('--------------------------------------');
            END IF;
            DBMS_OUTPUT.PUT_LINE('===== MONTHLY STATEMENT =====');
            DBMS_OUTPUT.PUT_LINE('Customer ID  : ' || rec.CustomerID);
            DBMS_OUTPUT.PUT_LINE('Customer Name: ' || rec.Name);
            DBMS_OUTPUT.PUT_LINE('Account ID   : ' || rec.AccountID || ' (' || rec.AccountType || ')');
            DBMS_OUTPUT.PUT_LINE('Transactions:');
            v_prev_customer_id := rec.CustomerID;
        END IF;

        DBMS_OUTPUT.PUT_LINE('  Date: ' || TO_CHAR(rec.TransactionDate, 'DD-MON-YYYY') ||
            ' | Type: ' || rec.TransactionType ||
            ' | Amount: ' || rec.Amount);
    END LOOP;

    IF v_prev_customer_id != -1 THEN
        DBMS_OUTPUT.PUT_LINE('--------------------------------------');
    ELSE
        DBMS_OUTPUT.PUT_LINE('No transactions found for the current month.');
    END IF;
END;
/
