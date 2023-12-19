package com.ch.spring.member.controller;

public class MemberSummary {
	
	// Dependency Injection
	/* [ 인젝션 종류 ]
	 * - 필드인젝션 (필드에 @Autowired)
	 * @Autowired
	 * private MemberService memberService;
	 * 
	 * - 세터인젝션 (세터 만들어 @Autowired)
	 * @Autowired
	 * public void setMemberService(MemberService memberService) {
	 * 		this.memberService = memberService;
	 * }
	 * 
	 * - 생성자인젝션 (생성자 만들어 @Autowired 혹은 생략하고 클래스에 @RequiredArgsConstructor)
	 * private final MemberService memberService;
	 * @Autowired
	 * public MemberService(MemberService memberService) {
	 * 		this.memberService = memberService;
	 * }
	 * 혹은
	 * @RequiredArgsConstructor
	 * public class MemberController { ... 클래스 상단에 어노테이션 ... }
	 * private final MemberService memberService;
	 */
	
	// HandlerMapping등록
	/* [ 요청은 스프링이 관리, 디스패처 서블릿(프론트 서블릿) 관련 설정은 web.xml ]
	 * @RequestMapping, @GetMapping, @PostMapping ...
	 */
	
	// 요청 시 Parameter를 받는 방법
	/* [ 전달값을 받는 방법 ]
	 * 1. 기존 HttpServletRequest 이용
	 * @RequestMapping(value="mapping.va")
	 * public void method(HttpServletRequest request) {
	 * 		request.getParameter("keyName");
	 * }
	 * 
	 * 2. @RequestParam 어노테이션 이용 / 기존 getParameter("키값")을 대체
	 * @RequestMapping(value="mapping.va")
	 * public void method(@RequestParam(value="키값") String something,
	 * 					  @RequestParam(value="키값", defaultValue="기본값) String somethingElse) {
	 * 		기본값 지정 가능 (required속성도 있긴 함 기본 false)
	 * }
	 * 
	 * 3. @ReqeustParam 어노테이션 생략
	 * *** 요청 키값과 매개변수명이 동일해야 자동으로 값이 주입됨
	 * @RequestMapping(value="mapping.va")
	 * public void method(String something, String somethingElse) {
	 * 		키값이 매개변수 명(something, somethingElse)과 동일해야함 (매개변수 명을 키 값으로 사용)
	 * }
	 * 
	 * 4. 커맨드 객체 방식 / 여러개의 값을 객체로  받는 방법 / (@ModelAttribute 생략)
	 * *** 키(name속성)값과 필드명이 동일해야함
	 *     기본생성자와 setter를 이용하므로 이 둘은 필수 (setter로 바인딩)
	 * @RequestMapping(value="mapping.va")
	 * public void method(Member member) {
	 * 		기본생성자로 member객체 생성
	 * 		키(name속성)값과 같은 이름의 setter를 찾아 세팅함
	 * }
	 * 
	 * -----
	 * 5. @PathVariable 어노테이션 이용
	 * 메소드의 파라미터에 path variable을 바인딩 하기 위해 사용
	 * Restful Controller(URL에 데이터 포함)일 때 자주 사용됨
	 * 
	 * 6. @RequestBody 어노테이션 이용
	 * HTTP요청 본문에 JSON, XML등 형식의 데이터가 넘어올 때 사용
	 * 
	 * 
	 * Memo.
	 * 요청 방식과 상관없이 데이터의 형식(Content type)에 따라
	 * @RequestParam의 경우 Content type: application/x-www-form-urlencoded를
	 * @RequestBody의 경우 Content type : application/json을 받음
	 * 
	 * 스프링은 매개변수 바인딩을 함 / 참조자료형의 경우 스프링이 자동으로 @ModelAttribute어노테이션을 붙임
	 * @ModelAttribute 어노테이션의 역할 중 하나는 커맨드객체 초기화
	 * 그리고 어노테이션을 달면 내가 객체를 생성하지 않아도 화면으로 넘길 수 있게 해줌
	 * 
	 */
	
	// 응답을 돌려보내는 방법
	/* [ 응답페이지로 포워딩 혹은 url재요청 하는 방법 ]
	 * 1. Model객체 이용
	 * 응답 뷰로 포워딩할 데이터를 맵 형식(key-value)으로 담을 수 있는 영역
	 * Medel객체는 requestScope
	 * 데이터 추가 시 addAttribute()메소드 이용
	 * @RequestMapping("mapping.va")
	 * public String method(Model model) {
	 * 		model.addAttribute("successMsg", "SUCCESS");
	 * 		return "common/nameOfPage";
	 * }
	 * 
	 * 2. MedelAndView객체 이용
	 * Model(key-value를 담는 공간)과 View(응답 뷰에 대한 정보를 담는 공간)을 결합한 형태의 객체
	 * 데이터 추가 시 addObject()메소드 이용
	 * @RequestMapping("mapping.va")
	 * public ModelAndView method(ModelAndView modelAndView) {
	 * 		modelAndView.addObject("member", Member member)
	 * 					.setViewName("redirect:/");
	 * }
	 * 
	 * 
	 * Memo.
	 * 만들어준 model + view는 핸들러 어댑터를 통해 DispatcherServlet으로 반환됨
	 * 그 후 viewResolver 거치며(render(), view로 가서 getViewName()으로 문자열만 꺼냄)
	 * prefix, suffic이 붙음 ("/WEB-INF/views/common/nameOfPage.jsp")
	 * 
	 * 포워딩의 경우 return "common/nameOfPage";
	 * 리디렉팅의 경우 return "redirect:/요청할url"; (localhost:/8001/appName과 같음)
	 * 
	 * View는 Interface이기때문에 단독 사용 불가, model과 결합해 ModelAndView로 이용해야함
	 */
	
	
}
