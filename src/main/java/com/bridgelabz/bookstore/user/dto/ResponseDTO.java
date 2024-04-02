package com.bridgelabz.bookstore.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class ResponseDTO {
    private String message;
    private Object object;
}
