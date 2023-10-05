# Happyhouse_frontend

17반 2조 권순석 추준성

해피하우스 백엔드 repository

### 1. Controller

- MemberController @RequestMapping("/users")

  - 회원한명정보조회 @GetMapping ("{userid}") (default - **userid를 RequestParam**으로 넘김)

  - 회원가입 @PostMapping

  - 로그인 @PostMapping("/signin")

  - 회원정보수정 @PutMapping("{userid}")

  - 회원탈퇴 @DeleteMapping("{userid}")

  - 이메일 중복 검사 @GetMapping("/email-check") 

  - Id 중복 검사 @GetMapping("/id-check")

  - password 검사 @GetMapping("/pwd-check") 
  
  <관리자용>

  - 전체회원리스트 @GetMapping



- HouseMapController @RequestMapping("/map")

  - 매매정보검색(검색창) @GetMapping("/search?search_type={type}&search_info={info}")

  - 매매정보조회(셀렉트박스) 
    - 시도 @GetMapping("/sido")
    - 구군 @GetMapping("/gugun")
    - 동 @GetMapping("/dong")
    - 아파트 @GetMapping("/apt")

    - 구군 기준 아파트 찾기 @GetMapping("/gugun/apt") 
    - 동 기준 아파트 찾기 @GetMapping("/dong/apt") 

  <관리자용>

  - 매매정보등록 (관리자용) @PostMapping

  - 매매정보수정 (관리자용) @PutMapping("{aptCode}")

  - 매매정보삭제 (관리자용) @DeleteMapping("{aptCode}")

    

- QnaController @RequestMapping("/qna")

  - 특정유저문의조회(리스트) @GetMapping("/list/{userid}) (default - **userid를 RequestParam**으로 넘김) 

  - 문의상세정보 (리스트 중 하나 클릭) @GetMapping("{articleno}")

  - 문의등록 @PostMapping

  - 문의수정 @PutMapping("{articleno}")

  - 문의삭제 @DeleteMapping("{articleno})"

  <관리자용>

  - 전체문의조회(관리자용) @GetMapping

  - 답변등록 (관리자용) @PostMapping("/answer")

  - 답변수정 (관리자용) @PutMapping("/answer")

  - 답변삭제 (관리자용) @DeleteMapping("/answer")

    

- NoticeController @RequestMapping("/notices") 

  - 글 목록 @GetMapping (default)
  - 글 조회 @GetMapping("/{articleno}")

  <관리자용>

  - 글 등록 (관리자용) @PostMapping("/register")

  - 글 수정 (관리자용) @PutMapping("/update")

  - 글 삭제 (관리자용) @DeleteMapping("/delete/{articleno}")
