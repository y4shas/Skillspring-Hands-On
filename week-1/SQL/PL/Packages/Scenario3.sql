CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(
        p_account_id  IN NUMBER,
        p_customer_id IN NUMBER,
        p_account_type IN VARCHAR2,
        p_balance     IN NUMBER
    );

    PROCEDURE CloseAccount(
        p_account_id IN NUMBER
    );

    FUNCTION GetTotalBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_account_id  IN NUMBER,
        p_customer_id IN NUMBER,
        p_account_type IN VARCHAR2,
        p_balance     IN NUMBER
    ) AS
        v_customer_exists NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_customer_exists
        FROM Customers
        WHERE CustomerID = p_customer_id;

        IF v_customer_exists = 0 THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Customer ID ' || p_customer_id || ' does not exist. Cannot open account.');
            RETURN;
        END IF;

        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_account_type, p_balance, SYSDATE);

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account ID ' || p_account_id || ' opened for Customer ID ' || p_customer_id ||
            ' (' || p_account_type || ') with initial balance: ' || p_balance);

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Account ID ' || p_account_id || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('ERROR: Failed to open account. Reason: ' || SQLERRM);
    END OpenAccount;

    PROCEDURE CloseAccount(
        p_account_id IN NUMBER
    ) AS
        v_account_balance NUMBER;
        v_account_type    VARCHAR2(50);
    BEGIN
        SELECT Balance, AccountType INTO v_account_balance, v_account_type
        FROM Accounts
        WHERE AccountID = p_account_id;

        DELETE FROM Accounts
        WHERE AccountID = p_account_id;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account ID ' || p_account_id ||
            ' (' || v_account_type || ') closed successfully.' ||
            ' Final balance was: ' || v_account_balance);

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Account ID ' || p_account_id || ' not found.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('ERROR: Failed to close account. Reason: ' || SQLERRM);
    END CloseAccount;

    FUNCTION GetTotalBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER AS
        v_total_balance NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total_balance
        FROM Accounts
        WHERE CustomerID = p_customer_id;

        RETURN v_total_balance;

    EXCEPTION
        WHEN OTHERS THEN
            RETURN NULL;
    END GetTotalBalance;

END AccountOperations;
/
