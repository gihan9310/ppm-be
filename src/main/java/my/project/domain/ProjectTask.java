package my.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * |** @author 'Gihan Rathnayaka'**|
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false,unique = true)
    private  String projectSeq;
    @NotBlank(message = "Please Include project summary.")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;

//    Many to one With back Log
    @Column(updatable = false)
    private String projectId;

    private LocalDate dueDate;
//    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime create_At;
    private LocalDateTime update_At;

    @ManyToOne(fetch = FetchType.EAGER
    )
    @JoinColumn(name = "backlog_id",updatable = false,nullable = false)
    @JsonIgnore
    private BackLog backLog;

    @PrePersist
    protected  void onCreate(){
        this.create_At =  LocalDateTime.now();
    }

    @PreUpdate
    protected  void onUpdate(){
        this.update_At =  LocalDateTime.now();
    }
}
