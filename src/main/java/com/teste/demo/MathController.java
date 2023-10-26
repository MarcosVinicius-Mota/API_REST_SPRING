package com.teste.demo;


import com.teste.demo.services.MathService;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private final MathService mathService;

    @Autowired
    public MathController(MathService service){
        this.mathService = service;
    }

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
        return mathService.sum(numberOne, numberTwo);
    }

    @RequestMapping(value = "/subtract/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtract(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
        return mathService.subtract(numberOne, numberTwo);
    }

    @RequestMapping(value = "/multiply/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiply(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
        return mathService.multiply(numberOne, numberTwo);
    }


    @RequestMapping(value = "/divide/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double divide(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
        return mathService.divide(numberOne, numberTwo);
    }

    @RequestMapping(value = "/sqrt/{number}", method = RequestMethod.GET)
    public Double sqrt(@PathVariable(value = "number") String number) throws Exception{
        return mathService.sqrt(number);
    }

    @RequestMapping(value = "/media/{numbers}", method = RequestMethod.GET)
    public Double media(@PathVariable String[] numbers) throws Exception{
        return mathService.media(numbers);
    }

}
