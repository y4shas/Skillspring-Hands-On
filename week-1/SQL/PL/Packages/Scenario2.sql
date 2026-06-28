CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_employee_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_position    IN VARCHAR2,
        p_salary      IN NUMBER,
        p_department  IN VARCHAR2,
        p_hire_date   IN DATE
    );

    PROCEDURE UpdateEmployeeDetails(
        p_employee_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_position    IN VARCHAR2,
        p_salary      IN NUMBER,
        p_department  IN VARCHAR2
    );

    FUNCTION CalculateAnnualSalary(
        p_employee_id IN NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_employee_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_position    IN VARCHAR2,
        p_salary      IN NUMBER,
        p_department  IN VARCHAR2,
        p_hire_date   IN DATE
    ) AS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_employee_id, p_name, p_position, p_salary, p_department, p_hire_date);

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee ' || p_name || ' hired with ID: ' || p_employee_id || ' in department: ' || p_department);

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Employee ID ' || p_employee_id || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('ERROR: Failed to hire employee. Reason: ' || SQLERRM);
    END HireEmployee;

    PROCEDURE UpdateEmployeeDetails(
        p_employee_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_position    IN VARCHAR2,
        p_salary      IN NUMBER,
        p_department  IN VARCHAR2
    ) AS
    BEGIN
        UPDATE Employees
        SET Name = p_name,
            Position = p_position,
            Salary = p_salary,
            Department = p_department
        WHERE EmployeeID = p_employee_id;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Employee ID ' || p_employee_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Employee ID ' || p_employee_id || ' details updated successfully.');
        END IF;

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('ERROR: Failed to update employee. Reason: ' || SQLERRM);
    END UpdateEmployeeDetails;

    FUNCTION CalculateAnnualSalary(
        p_employee_id IN NUMBER
    ) RETURN NUMBER AS
        v_monthly_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_monthly_salary
        FROM Employees
        WHERE EmployeeID = p_employee_id;

        RETURN v_monthly_salary * 12;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END CalculateAnnualSalary;

END EmployeeManagement;
/
