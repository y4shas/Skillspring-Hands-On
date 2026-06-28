CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_source_balance NUMBER;
    insufficient_balance EXCEPTION;
BEGIN
    SELECT Balance INTO v_source_balance
    FROM Accounts
    WHERE AccountID = p_from_account
    FOR UPDATE;

    IF v_source_balance < p_amount THEN
        RAISE insufficient_balance;
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_from_account;

    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Successfully transferred ' || p_amount ||
        ' from account ' || p_from_account ||
        ' to account ' || p_to_account || '.');

EXCEPTION
    WHEN insufficient_balance THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Source account ' || p_from_account ||
            ' has insufficient balance. Available: ' || v_source_balance ||
            ', Required: ' || p_amount);
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Account not found. Transfer aborted.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Transfer failed. Reason: ' || SQLERRM);
END TransferFunds;
/
