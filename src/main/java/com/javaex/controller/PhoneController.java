package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	// 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[PhoneController.list]");
		
		//Dao 사용
		PhoneDao phoneDao = new PhoneDao();
		
		//Dao의 메소드로 데이터 가져오기
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList);
		
		//model담기 (택배박스에 담는 것과 비슷)
		model.addAttribute("personList", personList);
		
		return "/WEB-INF/views/list.jsp";
	}

	// 쓰기폼
	@RequestMapping(value = "/wform", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("[PhoneController.wform]");
		return "/WEB-INF/views/writeForm.jsp";
	}

	// 쓰기
	/*
	 * //파라미터를 1개씩 받을 때
	 * 
	 * @RequestMapping(value="/write", method= {RequestMethod.GET,
	 * RequestMethod.POST}) public String write(@RequestParam("name") String name,
	 * 
	 * @RequestParam("hp") String hp,
	 * 
	 * @RequestParam("company") String company) {
	 * System.out.println("[PhoneController.write]"); System.out.println(name);
	 * System.out.println(hp); System.out.println(company);
	 * 
	 * PersonVo personVo = new PersonVo(name, hp, company);
	 * System.out.println(personVo); return ""; }
	 */

	
	  //Vo로 묶어서 받을 때
	  @RequestMapping(value="/write", method= {RequestMethod.GET,RequestMethod.POST}) 
	  public String write(@ModelAttribute PersonVo personVo) {
		  System.out.println("[PhoneController.write]");
	  
		  //@ModelAttribute --> new PersonVo() // --> 기본생성자 + setter
		  System.out.println(personVo);
		  
		  PhoneDao phoneDao = new PhoneDao();
		  
		  //Dao의 personInsert
		  int count =  phoneDao.personInsert(personVo);
		  
		  System.out.println(count+"건 등록하였습니다");
		  
		  return "redirect:/list"; 
	  }
	  //삭제
	  @RequestMapping(value="/delete", method= {RequestMethod.GET,RequestMethod.POST})
	  public String delete(@RequestParam("personId") int personId) {
		  System.out.println("[PhoneController.delete]");
		  
		  PhoneDao phoneDao = new PhoneDao();
		  
		  //Dao의 personDelete
		  int count = phoneDao.personDelete(personId);
		  
		  System.out.println(count+"건 삭제하였습니다");
		  
		  return "redirect:/list";
	  }
	  
	  
	  
	  //수정폼
	  @RequestMapping(value = "/uform", method = { RequestMethod.GET, RequestMethod.POST })
	  public String updateForm(@RequestParam("personId") int personId, Model model) {
		  System.out.println("[PhoneController.uform]");
			
		  PhoneDao phoneDao = new PhoneDao();
			
		  //dao 에서 한사람(id)의 정보 가져오기
		  PersonVo personVo = phoneDao.getPerson(personId);
		  System.out.println(personVo.toString()); // 확인용
			
		  model.addAttribute("personVo", personVo);
	      return "/WEB-INF/views/updateForm.jsp";
		}
	  
	  
	  //수정
	  @RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	  public String update(@ModelAttribute PersonVo personVo) {
		  System.out.println("[PhoneController.update]");
		  
		  PhoneDao phoneDao = new PhoneDao();
		  int count = phoneDao.personUpdate(personVo);
		  System.out.println(count+"건 수정하였습니다");
		  
		  
		  return "redirect:/list";
	  }
	 
	  /*
	// 파라미터가 있을때도 있고 없을때도 있고
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp,
						@RequestParam(value = "company", required = false, defaultValue = "-1") String company) {
		System.out.println("[PhoneController.write]");
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);

		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);
		return "";
	}
	*/
	
	/*
	@RequestMapping(value="/board/read/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@PathVariable("no") int boardNo) {
		
		System.out.println("PathVariable [read]");
		//localhost:8088/phonebook3/board/read/1
		System.out.println(boardNo);
		return "";
	}
	@RequestMapping("/test")
	public String test() {
		System.out.println("/phonebook3/test");
		return "/WEB-INF/views/test.jsp"; // DispatChServlet 야 ! "/WEB-INF/views/test.jsp"를 포워드 해조 !
	
	}
	*/
}
