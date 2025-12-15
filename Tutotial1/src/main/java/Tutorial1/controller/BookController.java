package Tutorial1.controller;

import Tutorial1.beans.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
//@ResponseBody
@RestController // Controller and ResponseBody combined on a class level
@RequestMapping("/api") // Base URL
public class BookController {

    @RequestMapping("/hello")
    // @ResponseBody
    public String hello() {
        return "Hello World";
    }

    @RequestMapping()
    // @ResponseBody
    public String home() {
        return "Welcome to my book store";
    }

//   @RequestMapping(value = {"/book", "/books", "/getbook"},
    @GetMapping(value = {"/book", "/books", "/getbook"}
            // GetMapping is a shortcut for @RequestMapping(value = {"/book", "/books", "/getbook"},)
//        method = RequestMethod.GET
//            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, // produces is a response header
//            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} // consumes is a request header
    )
    // @ResponseBody
    public Book getBook() {
        Book book = new Book(1, "The Art of Computer Programming", "978-0132350884");
        return book;
    }

    @PostMapping(value = "/createbook")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        System.out.println(book.getId() + " " + book.getTitle() + " " + book.getDescription());
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    //http://localhost:8081/api/deletebook/1
    @PutMapping(value = "/updatebook/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        // PathVariable is a shortcut for @PathVariable("id")
        System.out.println(id + " " + updatedBook.getTitle() + " " + updatedBook.getDescription());
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletebook/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Book> deleteBook(@PathVariable int id) {
        System.out.println(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
