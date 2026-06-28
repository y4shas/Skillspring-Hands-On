CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_id      IN NUMBER,
    p_name    IN VARCHAR2,
    p_dob     IN DATE,
    p_balance IN NUMBER
) AS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer ' || p_name || ' with ID ' || p_id || ' added successfully.');

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Customer with ID ' || p_id || ' already exists. Insert skipped.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Failed to add customer ' || p_name || '. Reason: ' || SQLERRM);
END AddNewCustomer;
/
