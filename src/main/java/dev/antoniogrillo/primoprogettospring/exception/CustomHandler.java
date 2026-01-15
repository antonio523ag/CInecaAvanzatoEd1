package dev.antoniogrillo.primoprogettospring.exception;

import dev.antoniogrillo.primoprogettospring.dto.response.MessageDTO;
import dev.antoniogrillo.primoprogettospring.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@RequiredArgsConstructor
public class CustomHandler {
    private final MessageMapper mapper;

    @ExceptionHandler(UtenteNonTrovatoException.class)
    public ResponseEntity<MessageDTO> utenteNonTrovato(UtenteNonTrovatoException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapper.toMessageDTO(e.getMessage(),404));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<MessageDTO> handlerSQLIntegrityViolation(SQLIntegrityConstraintViolationException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(mapper.toMessageDTO(e.getMessage(),409));
    }
}
