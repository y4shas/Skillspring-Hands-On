CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_account_balance NUMBER;
    negative_deposit  EXCEPTION;
    exceed_balance    EXCEPTION;
BEGIN
    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount < 0 THEN
        RAISE negative_deposit;
    END IF;

    IF :NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_account_balance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID;

        IF :NEW.Amount > v_account_balance THEN
            RAISE exceed_balance;
        END IF;
    END IF;

EXCEPTION
    WHEN negative_deposit THEN
        RAISE_APPLICATION_ERROR(-20001, 'ERROR: Deposit amount cannot be negative. Transaction rejected.');
    WHEN exceed_balance THEN
        RAISE_APPLICATION_ERROR(-20002, 'ERROR: Withdrawal amount exceeds available account balance. Transaction rejected.');
END CheckTransactionRules;
/
