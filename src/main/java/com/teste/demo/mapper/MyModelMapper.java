package com.teste.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyModelMapper extends ModelMapper {

    public <O, D> List<D> convertList(List<O> source, Class<D> destiny){
        List<D> list = new ArrayList<>();
        for (O o : source){
            list.add(map(o, destiny));
        }
        return list;
    }

}
