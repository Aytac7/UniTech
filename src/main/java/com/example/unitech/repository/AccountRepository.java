package com.example.unitech.repository;


import com.example.unitech.entity.Account;
import com.example.unitech.wrapper.AccountWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {


    @Query("select new com.example.unitech.wrapper.AccountWrapper(a.id,a.accountNumber,a.status,a.balance)" +
            " from Account a where a.fkUserId=:userId and a.status='A'")
    List<AccountWrapper> allActiveAccountByUserId(Long userId);

    Account findByAccountNumber(Long accountNumber);





}
