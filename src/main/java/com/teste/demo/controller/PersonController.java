package com.teste.demo.controller;

import com.teste.demo.data.dto.v1.PersonDTO;
import com.teste.demo.services.PersonService;

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
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find all People", description = "Find all people", tags = {"People"},
        responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
            )),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
       })
    public List<PersonDTO> findAll(){
        return personService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a Person", description = "Finds a Person", tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                        content = @Content(
                            schema = @Schema(implementation = PersonDTO.class)
                         )),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })

    public PersonDTO findById(@PathVariable(value = "id") Long id){
        return personService.findById(id);
    }

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
        produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})

    @Operation(summary = "Insert new Person", description = "Adds a new Person in JSON, XML or YML", tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = PersonDTO.class)
                            )),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })



    public PersonDTO create(@RequestBody PersonDTO personVO){
        return personService.createPerson(personVO);
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})

    @Operation(summary = "Updates a Person", description = "Updates a Person", tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = PersonDTO.class)
                            )),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })

    public PersonDTO update(@RequestBody PersonDTO personVO){
        return personService.updatePerson(personVO);
    }
    @Operation(summary = "Deletes a Person", description = "Deletes a Person", tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
         personService.delete(id);
         return ResponseEntity.noContent().build();
    }
}
