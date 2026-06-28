DECLARE
    v_col_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_col_exists
    FROM user_tab_columns
    WHERE table_name = 'CUSTOMERS'
      AND column_name = 'ISVIP';

    IF v_col_exists = 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE Customers ADD (IsVIP VARCHAR2(5) DEFAULT ''FALSE'')';
    END IF;
END;
/

DECLARE
    CURSOR cur_customers IS
        SELECT CustomerID, Name, Balance
        FROM Customers;
BEGIN
    FOR rec IN cur_customers LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Customer ' || rec.Name || ' marked as VIP. Balance: ' || rec.Balance);
        ELSE
            UPDATE Customers
            SET IsVIP = 'FALSE'
            WHERE CustomerID = rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Customer ' || rec.Name || ' is not VIP. Balance: ' || rec.Balance);
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('VIP flag update completed for all customers.');
END;
/
