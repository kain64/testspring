package com.test.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Employee task
 */
@Document()
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    /**
     * task unique id
     */
    @Getter
    @Setter
    private String id = UUID.randomUUID().toString();
    /**
     * Task description
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
     * real date finish date
     */
    @Getter @Setter
    private Date reaCreationDate;
    /**
     * current task status
     */
    @Setter @Getter
    private Status status;

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", finishDate=" + finishDate +
                ", reaCreationDate=" + reaCreationDate +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
