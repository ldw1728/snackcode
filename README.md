
###사용기술
__- back end__
- java 17  
- springboot 3.3.1  
- spring security  + jwt
- spring jpa  
- spring validation
- mysql 

__- front end__  
- vue.js


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
게시글 삭제 및 수정 기능 추가
게시글의 조회수, 좋아요, 즐겨찾기 컬럼추가 및 기능추가
게시글 contents, description항목에 에디터 달기




