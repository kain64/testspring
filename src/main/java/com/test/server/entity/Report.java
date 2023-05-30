package com.test.server.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Report {
    /**
     * Report unique id
     */
    @Getter
    @Setter
    private String id = UUID.randomUUID().toString();
    /**
     * Report description
     */
    @Getter @Setter
    private String description;
    /**
     * Creation date
     */
    @Getter @Setter
    private Date creationDate;
    /**
     * Date finish date
     */
    @Getter @Setter
    private Date finishDate;

    /**
     * current Report status
     */
    @Setter @Getter
    @DBRef
    private Employee reporter;

    @Override
    public String toString() {
        return "Report{" +
                "description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", finishDate=" + finishDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
