package org.example;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private Long id;
    private String name;
    private int published;
    private int price;
    private Long authorId;








     public Book(String name, int published, int price, Long authorId) {
         this.name = name;
         this.published = published;
         this.price = price;
         this.authorId = authorId;
     }
}
