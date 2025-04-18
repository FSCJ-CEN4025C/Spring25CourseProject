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
import java.time.LocalDateTime;
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

		Income income2 = new Income();
		income2.setCategory(category2);
		income2.setUser(user1);
		income2.setName("Paycheck 2");
		income2.setAmount(250.00f);
		income2.setCreatedAt( LocalDateTime.now().minusDays(1));
		incomeRepository.save(income2);

		Income income3 = new Income();
		income3.setCategory(category2);
		income3.setUser(user1);
		income3.setName("Paycheck 3");
		income3.setAmount(250.00f);
		income3.setCreatedAt( LocalDateTime.now());
		incomeRepository.save(income3);

		// Create Expenses
		Expenses expense1 = new Expenses();
		expense1.setCategory(category1);
		expense1.setUser(user1);
		expense1.setName("Rent payment 1");
		expense1.setAmount(500f);
		expense1.setCreatedAt( LocalDateTime.now().minusDays(1));
		expensesRepository.save(expense1);

		Expenses expense4 = new Expenses();
		expense4.setCategory(category1);
		expense4.setUser(user1);
		expense4.setName("Rent payment 1");
		expense4.setAmount(500f);
		expense4.setCreatedAt( LocalDateTime.now());
		expensesRepository.save(expense4);

		Expenses expense2 = new Expenses();
		expense2.setCategory(category4);
		expense2.setUser(user1);
		expense2.setName("Tacos 1");
		expense2.setAmount(405.0f);
		expense2.setCreatedAt( LocalDateTime.now().minusDays(1));
		expensesRepository.save(expense2);

		Expenses expense3 = new Expenses();
		expense3.setCategory(category4);
		expense3.setUser(user1);
		expense3.setName("Tacos 2");
		expense3.setAmount(100.0f);
		expense3.setCreatedAt( LocalDateTime.now());
		expensesRepository.save(expense3);


	}
}
