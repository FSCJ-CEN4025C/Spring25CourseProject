package edu.fscj.cen3024c.financialclarity;

// Entity and repostirory imports
import edu.fscj.cen3024c.financialclarity.entity.Expenses;
import edu.fscj.cen3024c.financialclarity.entity.Income;
import edu.fscj.cen3024c.financialclarity.entity.RepaymentPlan;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.repository.ExpensesRepository;
import edu.fscj.cen3024c.financialclarity.repository.IncomeRepository;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import edu.fscj.cen3024c.financialclarity.repository.RepaymentPlanRepository;

import edu.fscj.cen3024c.financialclarity.entity.Budget;
import edu.fscj.cen3024c.financialclarity.repository.BudgetRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("edu.fscj.cen3024c.financialclarity.repository")
public class FinancialClarityApplication implements CommandLineRunner {


	@Autowired
	private UserRepository usersRepository;
	@Autowired
	private IncomeRepository incomeRepository;
	@Autowired
	private ExpensesRepository expensesRepository;
	@Autowired
	private RepaymentPlanRepository repaymentPlanRepository;

	@Autowired
	private BudgetRepository budgetRepository;

	public static void main(String[] args) {
		SpringApplication.run(FinancialClarityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		// Create User
//		User user1 = new User();
//		user1.setUsername("Cole");
//		user1.setAge(20);
//		user1.setEmail("test@example.com");
//		user1.setHash("testhash");
//		user1.setSalt("testsalt");
//		user1.setTotalIncome("1000");
//		user1.setTotalExpences("1000");
//		usersRepository.save(user1);
//
//		// Create Budget
//		Budget budget = new Budget();
//		budget.setUserId(user1.getId()); // Set the userId to the id of the saved user
//		budget.setBudgetName("Monthly Budget");
//		budget.setTimeCreate(new Date()); // Current date and time
//		budgetRepository.save(budget);
//
//		// Create Income
//		Income income1 = new Income();
//		income1.setUserId(user1.getId());
//		income1.setName("Paycheck 1");
//		income1.setAmount(1200.00f);
//		incomeRepository.save(income1);
//
//		// Create Expenses
//		Expenses expense1 = new Expenses();
//		expense1.setName("Netflix_test");
//		expense1.setUserId(user1.getId());
//		expense1.setAmount(7.99f);
//		expensesRepository.save(expense1);
//
//		// Create RepaymentPlan
//		RepaymentPlan repaymentPlan = new RepaymentPlan();
//		repaymentPlan.setUserId(user1.getId());
//		repaymentPlan.setPlanName("Auto Loan Payment");
//		repaymentPlan.setTotalExpense(19999.99f);
//		repaymentPlan.setPayment(450.00f);
//		repaymentPlan.setTimeLine("40 months");
//		repaymentPlan.setCategory("Auto");
//
//
//		// Print all Users
//		List<User> users = usersRepository.findAll();
//		System.out.println("All users in the database:");
//		users.forEach(user -> System.out.println(user.getUsername()));
//
//		// Print all Budgets
//		List<Budget> budgets = budgetRepository.findAll();
//		System.out.println("All Budgets in the database:");
//		budgets.forEach(b -> System.out.println(b.getBudgetName()));
//
//		// Print all expenses
//		List<Expenses> expenses = expensesRepository.findAll();
//		System.out.println("All expenses in the database:");
//		expenses.forEach(expense -> System.out.println(expense.getName() + " " + expense.getAmount()));
//
//		// Print all Income
//		List<Income> incomes = incomeRepository.findAll();
//		System.out.println("All incomes in the database:");
//		incomes.forEach(income -> System.out.println(income.getName() + " " + income.getAmount()));
//
//		// Print all Repayment plan
//		List<RepaymentPlan> repaymentPlans = repaymentPlanRepository.findAll();
//		System.out.println("All repayment plan in the database:");
//		repaymentPlans.forEach(income -> System.out.println(repaymentPlan.getPlanName() + " " + repaymentPlan.getTotalExpense()));

	}
}
