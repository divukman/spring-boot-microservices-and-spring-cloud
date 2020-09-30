package tech.dimitar.app.ws.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ExampleException extends Exception {
    private final String message;
}
