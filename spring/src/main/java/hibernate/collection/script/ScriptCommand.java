package hibernate.collection.script;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;

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
public class ScriptCommand implements Cloneable {
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

    @SneakyThrows
    public ScriptCommand getClone() {
        return (ScriptCommand) clone();
    }
}
