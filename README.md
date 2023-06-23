# Spring-CRUD-과제-Lv2 : Blog
> 🏁 **Goal:** 회원가입, 로그인 기능이 추가된 나만의 블로그 백엔드 서버 만들기 <br><br>
> 🍀 TIL 일일회고 (작성 중) <br>

## 목차
* [프로젝트 개요](#프로젝트-개요)
* [프로젝트 진행 상태](#프로젝트-진행-상태)
* [기술 스택](#기술-스택)
* [주요 기능 소개](#주요-기능-소개)
* [CREATE TABLE](#CREATE-TABLE)
* [Api 명세](#Api-명세)
* [ER 다이어그램](#ER-다이어그램)
* [페이지 스크린샷](#페이지-스크린샷)
* [도움을 주신 분들](#도움을-주신-분들)
* [Contact](#contact)
<!-- * [License](#license) -->


## 프로젝트 개요
### 📛 프로젝트명
> Spring 활용 과제 CRUD Lv2 : Blog <br>
> 회원가입, 로그인 기능이 추가된 나만의 블로그 백엔드 서버 만들기

### 🥅 달성하고자 하는 목표 
> 심화된 Spring을 학습하고, 학습한 것들을 토대로 회원가입과 로그인 기능이 추가된 나만의 블로그 백엔드 서버를 만들어본다.<br>
> 인증/인가를 이해하고 JWT를 활용하여 게시글을 처리하며, JPA 연관관계를 이해하고 회원과 게시글을 구현하는 것을 목표로 한다. <br>


## 프로젝트 진행 상태
> 🚩 2023-06-19 `프로젝트 시작`<br>
> 🚩 2023-06-23 `프로젝트 완료`<br>
> 🚩 2023-06-24~ `프로젝트 보충 시작`<br>


## 기술 스택
<div align=left>
<img src="https://img.shields.io/badge/Java-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
<img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens">
</div>
<div>
<img src="https://img.shields.io/badge/mysql-003545.svg?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
</div>

## 주요 기능 소개
> ☑️ 전체 게시글 목록 조회 <br>
> ☑️ 게시글 작성 <br>
> ☑️ 선택한 게시글 조회 <br>
> ☑️ 선택한 게시글 수정 <br>
> ☑️ 선택한 게시글 삭제 <br>

## CREATE TABLE
```
create table post (
    id int not null auto_increment,

    createdAt DATETIME not null,
    modifiedAt DATETIME not null,

    title varchar(255) not null,
    content varchar(500) not null,
    username varchar(10) not null,

    primary key (id)
);
```
```
create table user (
    id int not null auto_increment,

    username varchar(10) not null,
    password varchar(15) not null,

    primary key (id)
);
```


## Api 명세
|구분|Method|URL|Request Head|Request|Response|Response Header|
|--|--|--|--|--|--|--|
|회원가입|`POST`|`/blog/auth/signup`|-|{<br>"username":"user1234",<br>"password":"Password1234"<br>}|{<br>"msg":"회원가입 성공",<br>"statusCode":200<br>}|-|
|로그인|`POST`|`/blog/auth/login`|-|{<br>"username":"user1234",<br>"password":"Password1234"<br>}|{<br>"msg":"로그인 성공",<br>"statusCode":200<br>}|Authorization:<br>Bearer <br>eyJhbGciOiJIUzI1NiJ9.eyJzdW<br>IiOiJ1c2VyMTIzNCIsImV4cCI<br>6MTY4NzUzNTUyNywiaWF0I<br>joxNjg3NTMxOTI3fQ.ZUDqo<br>EhmxqEMj8B6AreqdiCbDo4e<br>sMaraS_U2k6GNuM|
|게시글 작성|`POST`|`/blog/write`|Authorization:<br>Bearer <br>eyJhbGciOiJIUzI1NiJ9.eyJzdW<br>IiOiJ1c2VyMTIzNCIsImV4cCI<br>6MTY4NzUzNTUyNywiaWF0I<br>joxNjg3NTMxOTI3fQ.ZUDqo<br>EhmxqEMj8B6AreqdiCbDo4e<br>sMaraS_U2k6GNuM|{<br>"title":"제목1",<br>"content":"내용1"<br>}|{<br>"createdAt":"2023-06-23T12:56:36.821474",<br>"modifiedAt":"2023-06-23T12:56:36.821474"<br>"id":1,<br>"title":"게시글1"<br>"content":"내용1"<br>"username":"user1234"<br>}||
|전체 게시글 목록 조회|`GET`|`/blog/posts`|-|{<br>"title":"제목1",<br>"content":"내용1"<br>}|{<br>{<br>"createdAt":"2023-06-24T12:56:36.821474",<br>"modifiedAt":"2023-06-24T12:56:36.821474"<br>"id":2,<br>"title":"게시글2"<br>"content":"내용2"<br>"username":"user1234"<br>},<br>{<br>"createdAt":"2023-06-23T12:56:36.821474",<br>"modifiedAt":"2023-06-23T12:56:36.821474"<br>"id":1,<br>"title":"게시글1"<br>"content":"내용1"<br>"username":"user1234"<br>}<br>}|-|
|선택한 게시글 조회|`GET`|`/blog/posts/{id}`|-|{<br>"createdAt":"2023-06-24T12:56:36.821474",<br>"modifiedAt":"2023-06-24T12:56:36.821474"<br>"id":2,<br>"title":"게시글2"<br>"content":"내용2"<br>"username":"user1234"<br>}|-|
|선택한 게시글 수정|`PUT`|`/blog/posts/{id}`|Authorization:<br>Bearer <br>eyJhbGciOiJIUzI1NiJ9.eyJzdW<br>IiOiJ1c2VyMTIzNCIsImV4cCI<br>6MTY4NzUzNTUyNywiaWF0I<br>joxNjg3NTMxOTI3fQ.ZUDqo<br>EhmxqEMj8B6AreqdiCbDo4e<br>sMaraS_U2k6GNuM|{<br>"title":"제목2수정",<br>"content":"내용2수정"<br>}|{<br>"createdAt":"2023-06-24T12:56:36.821474",<br>"modifiedAt":"2023-06-25T12:56:36.821474"<br>"id":2,<br>"title":"게시글2수정"<br>"content":"내용2수정"<br>"username":"user1234"<br>}|-|
|선택한 게시글 삭제|`DELETE`|`/blog/posts/{id}`|Authorization:<br>Bearer <br>eyJhbGciOiJIUzI1NiJ9.eyJzdW<br>IiOiJ1c2VyMTIzNCIsImV4cCI<br>6MTY4NzUzNTUyNywiaWF0I<br>joxNjg3NTMxOTI3fQ.ZUDqo<br>EhmxqEMj8B6AreqdiCbDo4e<br>sMaraS_U2k6GNuM|-|{<br>"msg":"게시글 삭제 성공",<br>"statusCode":200<br>}|-|

![API 명세]()


## ER 다이어그램


## 페이지 스크린샷
#### 게시글 전체 조회 `/blog/posts`
![]()
#### 선택 게시글 수정 `/blog/posts/{id}`
![]()
#### 선택 게시글 수정 실패 `blog/posts/{id}`
![]()
#### 선택 게시글 삭제 `/blog/posts/{id}`
![]()
#### 게시글 작성 `/blog/write`
![]()
#### 선택 게시글 조회 `blog/posts/{id}`
![]()


## 도움을 주신 분들
프로젝트 진행에 있어 아래의 분들께 도움을 받았습니다 <br>

> 🤝 스파르타코딩클럽 [내일배움캠프](https://nbcamp.spartacodingclub.kr/) 소속 매니저 및 튜터분들이 진행에 도움을 주셨습니다<br>
> 🤝 이 프로젝트는 내일배움캠프 Spring 숙련 강의를 기반으로 만들어졌습니다<br>
> 
> 🤝 `README` templates from [README-cheetsheet](https://github.com/ritaly/README-cheatsheet)<br>
> 🤝 `.gitignore` templates from [gitignore.io](https://gitignore.io)


## Contact
👩‍💻 최정은 - [Blog](https://velog.io/@temprmn) / [GitHub](https://github.com/jungeun5-choi/)<br>
