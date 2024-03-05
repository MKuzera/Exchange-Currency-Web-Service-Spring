package com.SpringCourse.WebServiceSpring.Currency;

import com.SpringCourse.WebServiceSpring.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    public List<Currency> findAllByUser(User user);
}
