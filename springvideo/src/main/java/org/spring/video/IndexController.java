package org.spring.video;
import java.util.HashMap;
import java.util.Map;

import org.spring.video.domain.*;
import org.spring.video.feign.ExtractClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.convert.converter.Converter;
@RestController
@RequestMapping(value = "/console")
public class IndexController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ExtractClient extractClient;

	@RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
		 
		return "Hello Everybody.";
		
    }
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@RequestParam String name) {
		
		User u = userRepo.findUser(name);
		if(u != null)
			return "userName:" +u.getName() + ",age:" + u.getAge();
		else
			return "not found user.";
		
    }
	
	
	@RequestMapping(value = "/extractUser", method = RequestMethod.GET)
    @ResponseBody
    public String extractUser(@RequestParam String name) {
		
		String u = extractClient.extract(name);
		return u != null? u: "not found user.";
		
    }
	
	
	@RequestMapping(value = "/putUser", method = RequestMethod.GET)
    @ResponseBody
    public String putUser(@RequestParam String name, @RequestParam String age) {
		User u = userRepo.findUser(name);
		if(u == null){
			u = new User();
			u.setName(name);
		}
		u.setAge(Integer.parseInt(age) );
		userRepo.save(u);
		return "modify succesful.";
		
    }
	
	@RequestMapping(value = "/listUser", method = RequestMethod.GET)
    @ResponseBody
    public Object putUser(@RequestParam(required=false, defaultValue="0") int pageNo) {
		Sort dataSort = new Sort(Direction.ASC, "name");
		PageRequest pr = new PageRequest(pageNo, 20, dataSort);
		Page<User> p = userRepo.findAll(pr);
		Page<Map<String, String>> pmap = p.map(new Converter<User, Map<String, String>>(){
					public Map<String, String> convert(User source) {
						Map<String, String>  dmap = new HashMap<String, String>();
						dmap.put("name", source.getName());
						dmap.put("age", source.getAge()+"");
						return dmap;
					}
		});
		
		return pmap;
		
    }
	
 
}
