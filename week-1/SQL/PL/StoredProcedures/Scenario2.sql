CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department      IN VARCHAR2,
    p_bonus_percentage IN NUMBER
) AS
    v_count NUMBER := 0;
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_percentage / 100)
    WHERE Department = p_department;

    v_count := SQL%ROWCOUNT;

    IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Bonus of ' || p_bonus_percentage || '% applied to ' || v_count || ' employee(s) in department: ' || p_department);
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Failed to update bonuses for department ' || p_department || '. Reason: ' || SQLERRM);
END UpdateEmployeeBonus;
/
