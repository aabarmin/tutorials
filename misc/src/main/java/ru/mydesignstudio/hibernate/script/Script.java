package ru.mydesignstudio.hibernate.script;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
@Entity
@Table(name = "SCRIPT")
public class Script {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SCRIPT_ID")
    private int id;

    @Column(name = "SCRIP_TITlE")
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "script", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("COMMAND_ORDER ASC")
    private List<ScriptCommand> commands = new ArrayList<>();
}
