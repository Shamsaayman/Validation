package org.example.q3validation.Controller;
import jakarta.validation.Valid;
import org.example.q3validation.Api.ApiResponse;
import org.example.q3validation.Model.EventSystem;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/event")
public class EventSystemController {
    ArrayList<EventSystem>events= new ArrayList<>();

    @GetMapping("/display")
    public ResponseEntity getEvents(){
        return ResponseEntity.status(200).body(events);
    }
    @PostMapping("/create")
    public ResponseEntity createEvent(@RequestBody @Valid EventSystem event, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event Created"));
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid EventSystem event, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiResponse("Event Updated"));
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index){
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Event Deleted"));
    }

    @PutMapping("/capacity/{index}")
    public ResponseEntity changeCapacity(@PathVariable int index, @RequestBody @Valid EventSystem event, Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.get(index).setCapacity(event.getCapacity());
        return ResponseEntity.status(200).body(new ApiResponse("Capacity Changed"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity searchEvent(@PathVariable String id){
        ArrayList<EventSystem>search= new ArrayList<>();
        for(EventSystem event: events){
            if(event.getId().contains(id)){
                search.add(event);
            }
        }
        return ResponseEntity.status(200).body(search);
    }


}
