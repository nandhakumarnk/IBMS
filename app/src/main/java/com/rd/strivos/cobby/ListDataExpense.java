package com.rd.strivos.cobby;

/**
 * Created by COBURG DESIGN on 08-01-2016.
 */
public class ListDataExpense {

    public int id = -1;
    public String projectName;
    public String expenseType;
    public String expenseAmount;
    public String projectType;
    public String status;

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        id = Id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String ProjectName) {
        projectName = ProjectName;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String ExpenseType) {
        this.expenseType = ExpenseType;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(String ExpenseAmount) {
        this.expenseAmount = ExpenseAmount;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String ProjectType) {
        this.projectType = ProjectType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }
}
