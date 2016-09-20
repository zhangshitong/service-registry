package org.spring.service;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/console")
public class ExtractController {
	
	@RequestMapping(value = "/extract", method = RequestMethod.GET)
    @ResponseBody
    public String extractUser(@RequestParam String name) {
	
		
		return "{name:"+name +", fee: 99}" ;
 		
		
		
		
    }
	
 
}
