package com.codewithchristian.libraryapi.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BookRequest {

    @NotBlank(message = "Title cannot be empty")
    @ApiModelProperty(notes = "Book Title")
    @Size(min = 2, message = "Title cannot be less than 3 characters")
    private String bookTitle;

    @NotBlank(message = "Author cannot be empty")
    @ApiModelProperty(notes = "Book Author")
    @Size(min = 2, message = "Book Author name cannot be less than 3 characters")
    private String bookAuthor;

    @NotNull
    @ApiModelProperty(notes = "Book Quantity")
    private Integer quantity;

    @NotNull
    @ApiModelProperty(notes = "Category ID")
    private Long categoryId;
}
