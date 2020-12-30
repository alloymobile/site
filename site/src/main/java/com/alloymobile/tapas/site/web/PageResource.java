package com.alloymobile.tapas.site.web;

import com.alloymobile.tapas.site.exception.NotFoundException;
import com.alloymobile.tapas.site.service.impl.page.PageService;
import com.alloymobile.tapas.site.service.impl.page.dto.PageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("site/api/v1")
@Tag(name = "Page", description = "The Page API")
public class PageResource {

    private PageService pageService;

    public PageResource(PageService pageService){
        this.pageService = pageService;
    }

    //get one page by Id
    @Operation(summary = "Find page by ID", description = "Returns a single page", tags = { "Page" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = PageDTO.class))),
            @ApiResponse(responseCode = "404", description = "Page not found") })
    @GetMapping(value = "/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PageDTO> readPageById(@Nullable @PathVariable(name="pageId") Long pageId) {
        return pageService.readPageById(pageId).orElseThrow(NotFoundException::new);
    }

//    //Get all page
//    @Operation(summary = "Find all pages", description = "Get all pages", tags = { "Page" })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation",
//                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PageDTO.class)))) })
//    @GetMapping( produces = MediaTypes.HAL_JSON_VALUE)
//    public @ResponseBody ResponseEntity<ResponseEntity<PageDTO>> readAllPage(@Nullable Pageable pageable ) {
//        return pageService.readAllPage(pageable).orElseThrow(NotFoundException::new);
//    }

    //add one page
    @Operation(summary = "Add a new page", description = "It will add a new page", tags = { "Page" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Page created",
                    content = @Content(schema = @Schema(implementation = PageDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Page already exists") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<PageDTO> createPage(@Nullable @RequestBody PageDTO newObject ) {
        return pageService.createPage(newObject).orElseThrow(NotFoundException::new);
    }

    //update a page by id
    @Operation(summary = "Update an existing page", description = "It will update the page object", tags = { "Page" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Page not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @PutMapping(value = "/{pageId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody ResponseEntity<PageDTO> updatePageById(@Nullable @PathVariable("pageId") Long id,@Nullable @RequestBody PageDTO updatedObject) {
        return pageService.updatePageById(id, updatedObject).orElseThrow(NotFoundException::new);
    }

    //delete a page by id
    @Operation(summary = "Deletes a page", description = "It will delete page when provided id", tags = { "Page" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Page not found") })
    @DeleteMapping(value = "/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deletePageById(@Nullable @PathVariable("pageId") Long id) {
        pageService.deletePageById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
