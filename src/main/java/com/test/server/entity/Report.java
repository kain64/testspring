package com.test.server.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;


public class Report {
    /**
     * Report unique id
     */
    @Id
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
     * reporter
     */
    @Setter @Getter
    @DBRef
    private Employee reporter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(description, report.description) && Objects.equals(creationDate, report.creationDate) && Objects.equals(finishDate, report.finishDate) && Objects.equals(reporter, report.reporter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, creationDate, finishDate, reporter);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", finishDate=" + finishDate +
                ", reporter=" + reporter +
                '}';
    }
}
