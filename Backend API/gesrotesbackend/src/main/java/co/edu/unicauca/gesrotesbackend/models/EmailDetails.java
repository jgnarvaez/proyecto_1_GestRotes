package co.edu.unicauca.gesrotesbackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailDetails {
 
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}