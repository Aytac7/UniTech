package com.example.unitech.repository;


import com.example.unitech.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Long> {

    List<Currency> findByUpdatedDateBefore(LocalDateTime localDateTime);


}
