package dev.abarmin.beanstalk.quotes.provider.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "QUOTES")
public class Quote {
    @Id
    @Column(name = "QUOTE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 4096)
    @Column(name = "QUOTE_TEXT")
    private String text;
}
