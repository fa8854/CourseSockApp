package my.course.sockapp.controller;


import my.course.sockapp.dto.SockRequest;
import my.course.sockapp.exception.InSufficientSockQuantity;
import my.course.sockapp.exception.InvalidSockRequestException;
import my.course.sockapp.model.Colour;
import my.course.sockapp.model.Size;
import my.course.sockapp.model.Sock;
import my.course.sockapp.service.SockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sock")
public class SockController {
    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }


    @ExceptionHandler(InvalidSockRequestException.class)
    public ResponseEntity<String> handleInvalidException(InvalidSockRequestException invalidSockRequestException) {
        return ResponseEntity.badRequest().body(invalidSockRequestException.getMessage());
    }

    @ExceptionHandler(InSufficientSockQuantity.class)
    public ResponseEntity<String> handleInSufficientException(InSufficientSockQuantity inSufficientSockQuantity) {
        return ResponseEntity.badRequest().body(inSufficientSockQuantity.getMessage());
    }

    @PostMapping
    public void addSocks(@RequestBody SockRequest sockRequest) {
        sockService.addSock(sockRequest);
    }

    @PutMapping
    public void issueSock(SockRequest sockRequest) {
        sockService.issueSock(sockRequest);
    }

    @GetMapping
    public int getSocksCount(@RequestParam(required = false, name = "colour")Colour colour,
                             @RequestParam(required = false, name = "size")Size size,
                             @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                             @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return sockService.getSockQuantity(colour, size, cottonMin, cottonMax);
    }


    @DeleteMapping
    public void removeDefectiveSocks(@RequestBody SockRequest sockRequest) {
        sockService.removeDefectiveSocks(sockRequest);
    }

}
