package com.practice.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
/*	@Query("from Account acc where acc.customer.mobile=:mobile")
	Account getAccountDetailsByMobile(Integer mobile);
*/
	@Query("select acc from Account acc,Customer c where acc.customer.cid=c.cid and c.mobile=?1")
	Account getAccountDetailsByMobile(Long mobile);
	@Query("select acc from Account acc where acc.cardno=?1 and acc.cvv=?2")
	Account getAccountDetails(Long cardno,Integer cvv,Date exp);
	
	Account findByAccontno(Long accontno);
	@Query("select acc from Account acc,Customer c where acc.customer.cid=c.cid and acc.accontno=?1")
	Account getAccountDetailsByAccountNo(Long accountno);

}
