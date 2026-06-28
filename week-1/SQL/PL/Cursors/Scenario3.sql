DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, CustomerID, LoanAmount, InterestRate
        FROM Loans
        FOR UPDATE;

    v_new_rate NUMBER;
BEGIN
    FOR rec IN UpdateLoanInterestRates LOOP
        v_new_rate := rec.InterestRate + 0.5;

        UPDATE Loans
        SET InterestRate = v_new_rate
        WHERE CURRENT OF UpdateLoanInterestRates;

        DBMS_OUTPUT.PUT_LINE('Loan ID: ' || rec.LoanID ||
            ' | Customer ID: ' || rec.CustomerID ||
            ' | Old Rate: ' || rec.InterestRate || '%' ||
            ' | New Rate: ' || v_new_rate || '%');
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Annual interest rate increase of 0.5% applied to all loans.');
END;
/
