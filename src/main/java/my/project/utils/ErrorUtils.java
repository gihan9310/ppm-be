package my.project.utils;


import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorUtils {

    public  static Map<String ,String> errorFlields(List<FieldError> fieldErrors){

        Map<String ,String> map =  new HashMap<>();
        for (FieldError e: fieldErrors) {
           map.put(e.getField(),e.getDefaultMessage()) ;
        }
        return map;
    }

}
