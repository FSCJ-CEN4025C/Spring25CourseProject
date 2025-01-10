package edu.fscj.cen3024c.financialclarity.repository;

import edu.fscj.cen3024c.financialclarity.entity.RepaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepaymentPlanRepository extends JpaRepository<RepaymentPlan, Integer> {
    // Find all Incomes
    List<RepaymentPlan> findAll();
    RepaymentPlan findByPlanName(String planName);
    void deleteByPlanName(String planName);

}
