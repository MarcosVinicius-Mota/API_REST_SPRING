package com.teste.demo.controller;


import com.teste.demo.data.dto.v1.BookDTO;
import com.teste.demo.model.Book;
import com.teste.demo.services.BookService;
import com.teste.demo.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Books", description = "Endpoint for Managing Books")
public class BookController {


    @SuppressWarnings("FieldMayBeFinal")
    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_XML
            }
    )

    @Operation(
            summary = "Finds All Books", description = "Returns a List of All Books", tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
                    )),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )

    public List<BookDTO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = {
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_XML,
                MediaType.APPLICATION_XML
            }
    )

    @Operation(
            summary = "Find a Book", description = "Finds a Book with required ID", tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = BookDTO.class)
                    )),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public BookDTO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_XML
            },
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_XML
            }
    )
    @Operation(
            summary = "Insert new Book", description = "Adds a new Book in JSON, XML or YML", tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = BookDTO.class)
                    )),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public BookDTO createBook(@RequestBody BookDTO bookDTO){
        return service.createBook(bookDTO);
    }

    @PutMapping(
            consumes = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_XML
            },
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_XML
            }
    )

    @Operation(
            summary = "Updates a Book", description = "Updates a Book", tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = BookDTO.class)
                    )),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public BookDTO updateBook(@RequestBody BookDTO bookDTO){
        return service.update(bookDTO);
    }


    @DeleteMapping(value = "/{id}")

    @Operation(
            summary = "Deletes a Book", description = "Deletes a Book", tags = {"Books"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
