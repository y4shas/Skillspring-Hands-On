CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id  IN NUMBER,
    p_percentage   IN NUMBER
) AS
    v_employee_name Employees.Name%TYPE;
    v_old_salary    Employees.Salary%TYPE;
    v_new_salary    Employees.Salary%TYPE;
BEGIN
    SELECT Name, Salary INTO v_employee_name, v_old_salary
    FROM Employees
    WHERE EmployeeID = p_employee_id;

    v_new_salary := v_old_salary + (v_old_salary * p_percentage / 100);

    UPDATE Employees
    SET Salary = v_new_salary
    WHERE EmployeeID = p_employee_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for employee ' || v_employee_name ||
        '. Old Salary: ' || v_old_salary ||
        ', New Salary: ' || v_new_salary);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Employee with ID ' || p_employee_id || ' not found. Salary update skipped.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Failed to update salary for employee ID ' || p_employee_id || '. Reason: ' || SQLERRM);
END UpdateSalary;
/
