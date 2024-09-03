### 설명
code drawer 라는 웹앱은 직역하면 코드서랍이라는 의미를 가지며 자주 사용되는, 혹은 기억하고 싶은 코드를 간략한 설명과 함께 저장 및 조회 할 수 있습니다.
그리고 사람들이 올린 코드를 조회하여 서로 공유할 수 있으며 open ai api를 이용하여 자신이 작성한 코드에대한 설명과 제안을 받을 수 있는 기능이 존재합니다.    

url : www.wookhamhappyday.com    
test 계정 : test@gmail.com     /     rhcnwkd5%

### 사용기술
__- server__
- oracle cloud vm (centOS 7)
  
__- back end__
- java 17  
- springboot 3.3.1  
- spring security  + jwt
- spring jpa  
- spring validation
- oracle cloud database

__- front end__  
- thymeleaf
- tailwindcss
- quil editor, ace editor

__- etc__
- github action 이용하여 ci/cd 구현
- open ai api 연동


### erd
https://www.erdcloud.com/d/FecKrWFRe6iYKAJ76

### screenshot
<img width="485" alt="image" src="https://github.com/user-attachments/assets/43456600-ba01-48da-bfd6-671754c19bcf">
<img width="633" alt="image" src="https://github.com/user-attachments/assets/985a4299-bfc9-4481-957c-a42dc0459b4b">
<img width="626" alt="image" src="https://github.com/user-attachments/assets/be1899da-0ec1-4816-b289-3d730ce42297">



### todo  
~erd 설계~  
~프로젝트 생성 및 기본설정~      
~entity들 생성~      
~security 기본구성 및 회원 로그인, 가입 구현~      
~jwt 토큰 인증 및 인가 구현 -> BasicAuthenticationFilter 이슈가 있음... ~    
~category 로직 구현~    
~member 로직 구현 (탈퇴 시)~    
~post 로직 구현~    
~post 조회시 pagination 기능 필요 10개씩 -> pageable 사용하여 간단구현~    
~spring validation 적용하여 formModel 필드 유효성 체크~    
~front server cors 설정~    

    
frontend 프로젝트 구성.    
~vue 프로젝트 생성 및 라우터 적용~    
~vue router navigation gard 이용하여 접근권한 설정~    
~vue pinia 사용하여 상태관리 적용(회원정보)~    
~login 페이지 구현 및 axios로 backend에 로그인 요청~    
~thymeleaf로 변경.~    
~jwt 토큰을 header에 담아 요청하는것을 쿠키 방식으로 변경~    
~카테고리 및 리스트 생성 기능 추가~    
~게시글 삭제 및 수정 기능 추가~    
~게시글의 조회수, 좋아요, 즐겨찾기 컬럼추가 및 기능추가~    
~게시글 contents, description항목에 에디터 달기~    
~codemirror 이용하여 code editor 기능추가~    
~search 메뉴 및 페이지 생성. 다른 유저의 게시글포함하여 서치함.~    
~search메뉴의 상세 요소 구현~    
~search 메뉴에서 조회된 리스트 및 상세에 사용자명 표시~    
~보관함페이지 구현~    
~ai asistance 기능 구현~    

~댓글기능 추가~     
~회원 비밀번호변경~ 
~회원 탈퇴 기능~
~마이페이지 기능~    
검색 기능   
현재 페이지 이동없이 비동기 통신으로 각 게시글의 데이터를 가져오고있다 만약 특정 게시글을 공유하기위해서는 ??       
전체 카테고리 키워드를 수집하여 특정 키워드에대한 기술내용을 openai를 이용해 특정시간에 자동적으로 포스팅하게하는 기능.



