package lu.karpychev.trailfinder.model;

import lombok.*;

import java.util.UUID;

@Data
public class User {

    private UUID id;
    private String firstName;
    private String secondName;

}
