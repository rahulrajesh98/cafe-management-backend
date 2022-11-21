package com.cafe.inn.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NamedQuery(name = "User.getAllCategory" ,query = "select c from Category c ")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
