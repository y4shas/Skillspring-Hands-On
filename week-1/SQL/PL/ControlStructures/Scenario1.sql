DECLARE
    CURSOR cur_customers IS
        SELECT c.CustomerID, c.Name, c.DOB
        FROM Customers c;

    v_age NUMBER;
BEGIN
    FOR rec IN cur_customers LOOP
        v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12);

        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - (InterestRate * 0.01)
            WHERE CustomerID = rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Applied 1% discount to loans for customer: ' || rec.Name);
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Loan interest rate discount applied for senior customers.');
END;
/
