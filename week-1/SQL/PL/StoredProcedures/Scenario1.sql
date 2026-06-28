CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    v_count NUMBER := 0;
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01),
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';

    v_count := SQL%ROWCOUNT;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest of 1% applied to ' || v_count || ' savings account(s).');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Failed to process monthly interest. Reason: ' || SQLERRM);
END ProcessMonthlyInterest;
/
