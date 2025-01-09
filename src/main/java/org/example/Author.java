package org.example;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String country;
    private LocalDate dateOfBirth;

    public Author(String firstname, String lastname, String email, String country, LocalDate dateOfBirth) {}
}
