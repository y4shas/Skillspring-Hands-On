DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, AccountType, Balance
        FROM Accounts
        FOR UPDATE;

    v_annual_fee CONSTANT NUMBER := 500;
BEGIN
    FOR rec IN ApplyAnnualFee LOOP
        UPDATE Accounts
        SET Balance = Balance - v_annual_fee,
            LastModified = SYSDATE
        WHERE CURRENT OF ApplyAnnualFee;

        DBMS_OUTPUT.PUT_LINE('Annual fee of ' || v_annual_fee ||
            ' deducted from Account ID: ' || rec.AccountID ||
            ' (' || rec.AccountType || ')' ||
            '. Previous Balance: ' || rec.Balance ||
            ', New Balance: ' || (rec.Balance - v_annual_fee));
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Annual maintenance fee applied to all accounts.');
END;
/
