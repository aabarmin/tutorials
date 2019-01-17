package ru.mydesignstudio.hibernate.script;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(
        name = "SCRIPT_COMMAND",
        uniqueConstraints = @UniqueConstraint(columnNames = {
                "SCRIPT_ID",
                "COMMAND_ORDER"
        })
)
@ToString(exclude = {"script"})
public class ScriptCommand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SCRIPT_COMMAND_ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "SCRIPT_ID")
    private Script script;

    @Column(name = "COMMAND_ORDER")
    private int order;

    @Column(name = "COMMAND_TITLE")
    private String title;
}
