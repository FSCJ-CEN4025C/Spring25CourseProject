package edu.fscj.cen3024c.financialclarity;

// Entity and repostirory imports
import edu.fscj.cen3024c.financialclarity.entity.Expenses;
import edu.fscj.cen3024c.financialclarity.entity.Income;
import edu.fscj.cen3024c.financialclarity.entity.RepaymentPlan;
import edu.fscj.cen3024c.financialclarity.entity.Category;
import edu.fscj.cen3024c.financialclarity.entity.User;
import edu.fscj.cen3024c.financialclarity.repository.ExpensesRepository;
import edu.fscj.cen3024c.financialclarity.repository.IncomeRepository;
import edu.fscj.cen3024c.financialclarity.repository.UserRepository;
import edu.fscj.cen3024c.financialclarity.service.UserService;
import edu.fscj.cen3024c.financialclarity.repository.RepaymentPlanRepository;

import edu.fscj.cen3024c.financialclarity.entity.Budget;
import edu.fscj.cen3024c.financialclarity.repository.BudgetRepository;
import edu.fscj.cen3024c.financialclarity.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import edu.fscj.cen3024c.financialclarity.entity.CategoryType;
import edu.fscj.cen3024c.financialclarity.entity.Period;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("edu.fscj.cen3024c.financialclarity.repository")
public class FinancialClarityApplication implements CommandLineRunner {


	@Autowired
	private UserRepository usersRepository;
    @Autowired
    private UserService userService;

	@Autowired
	private CategoryRepository categoryRepository;

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

		// Create User
		User user1 = new User();
		user1.setUsername("test");
		user1.setEmail("test");
	
		String password = "test";
		byte[] salt = userService.generateSalt();
        byte[] hashedPassword = userService.hashPassword(password, salt);
		user1.setHash(hashedPassword);
		user1.setSalt(salt);

		user1.setAge(20);

		usersRepository.save(user1);

		// Create Category
		Category category1 = new Category();
		category1.setUser(user1);
		category1.setName("Rent");
		category1.setType(CategoryType.EXPENSE);
		categoryRepository.save(category1);

		Category category2 = new Category();
		category2.setUser(user1);
		category2.setName("Software Dev");
		category2.setType(CategoryType.INCOME);
		categoryRepository.save(category2);

		Category category3 = new Category();
		category3.setUser(user1);
		category3.setName("Consulting");
		category3.setType(CategoryType.INCOME);
		categoryRepository.save(category3);
		
		Category category4 = new Category();
		category4.setUser(user1);
		category4.setName("Taco Bell");
		category4.setType(CategoryType.EXPENSE);
		categoryRepository.save(category4);


		

		// Create Budget
		Budget budget = new Budget();
		budget.setUser(user1);
		budget.setCategoryId(category1.getId());
		budget.setAmount(112.0);
		budget.setPeriod(Period.MONTHLY);
		budget.setStartDate(new Date());
		budget.setEndDate(new Date());
		budgetRepository.save(budget);


		// Create Income
		Income income1 = new Income();
		income1.setCategory(category2);
		income1.setUser(user1);
		income1.setName("Paycheck 1");
		income1.setAmount(1200.00f);
		incomeRepository.save(income1);

		// Create Expenses
		Expenses expense1 = new Expenses();
		expense1.setCategory(category1);
		expense1.setUser(user1);
		expense1.setName("Netflix_test");
		expense1.setAmount(500f);
		expensesRepository.save(expense1);


		Expenses expense2 = new Expenses();
		expense2.setCategory(category4);
		expense2.setUser(user1);
		expense2.setName("Tacos 1");
		expense2.setAmount(405.0f);
		expensesRepository.save(expense2);

		Expenses expense3 = new Expenses();
		expense3.setCategory(category4);
		expense3.setUser(user1);
		expense3.setName("Tacos 2");
		expense3.setAmount(100.0f);
		expensesRepository.save(expense3);

		// // Create RepaymentPlan
		// RepaymentPlan repaymentPlan = new RepaymentPlan();
		// repaymentPlan.setUser(user1);
		// repaymentPlan.setPlanName("Auto Loan Payment");
		// repaymentPlan.setTotalExpense(19999.99f);
		// repaymentPlan.setPayment(450.00f);
		// repaymentPlan.setTimeLine("40 months");
		// repaymentPlan.setCategory("Auto");


		// // Print all Users
		// List<User> users = usersRepository.findAll();
		// System.out.println("All users in the database:");
		// users.forEach(user -> System.out.println(user.getUsername()));

		// // Print all Budgets
		// List<Budget> budgets = budgetRepository.findAll();
		// System.out.println("All Budgets in the database:");
		// budgets.forEach(b -> System.out.println(b.getBudgetName()));

		// // Print all expenses
		// List<Expenses> expenses = expensesRepository.findAll();
		// System.out.println("All expenses in the database:");
		// expenses.forEach(expense -> System.out.println(expense.getName() + " " + expense.getAmount()));

		// // Print all Income
		// List<Income> incomes = incomeRepository.findAll();
		// System.out.println("All incomes in the database:");
		// incomes.forEach(income -> System.out.println(income.getName() + " " + income.getAmount()));

		// // Print all Repayment plan
		// List<RepaymentPlan> repaymentPlans = repaymentPlanRepository.findAll();
		// System.out.println("All repayment plan in the database:");
		// repaymentPlans.forEach(income -> System.out.println(repaymentPlan.getPlanName() + " " + repaymentPlan.getTotalExpense()));

	}
}
