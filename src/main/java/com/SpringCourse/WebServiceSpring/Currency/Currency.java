package com.SpringCourse.WebServiceSpring.Currency;

import com.SpringCourse.WebServiceSpring.User.User;
import jakarta.persistence.*;

@Entity
public class Currency {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private CurrencyType currencyType;
        private double val;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        public Currency() {
        }

        public Currency(Integer id, CurrencyType currencyType, double val) {
            this.id = id;
            this.currencyType = currencyType;
            this.val = val;
        }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public double getVal() {
        return val;
    }

    public void setValue(double val) {
        this.val = val;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
