CREATE TABLE AuditLog (
    TransactionID   NUMBER,
    AccountID       NUMBER,
    TransactionDate DATE,
    Amount          NUMBER,
    TransactionType VARCHAR2(50),
    LogDate         DATE
);

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, TransactionDate, Amount, TransactionType, LogDate)
    VALUES (:NEW.TransactionID, :NEW.AccountID, :NEW.TransactionDate, :NEW.Amount, :NEW.TransactionType, SYSDATE);
END LogTransaction;
/
