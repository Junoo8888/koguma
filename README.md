# Project Koguma

>Naver Cloud Camp
>
>클라우드기반 자바 웹 & 데브옵스 개발자 과정 2기 Team 'FiveGuys'
>
>김성윤, 김태현, 송인찬, 엄준호, 현상철



### 목차

---

#### 1. [프로젝트 소개](#프로젝트-소개)

#### 2. [프로젝트 산출물](#프로젝트-산출물)

#### 3. [역할 및 담당 업무](#역할-및-담당-업무)

#### 4. [담당 구현 기능](#담당-구현-기능)

#### 5. [DevOps](#DevOps)

#### 6. [Cloud Architecture](#Cloud-Architecture)

#### 7. [Server Architecture](#Server-Architectrue)

#### 8. [ERD](#ERD)

---



### 프로젝트 소개 

>고구마 - 동네인증 기반 중고 거래 플랫폼 

- 고구마는 주변 이웃들과 중고 거래를 할 때, 이웃 간 의사소통을 위한 채팅과 통화, 송금를 지원합니다. 안전한 거래를 위해 고구마 페이를 등록해 보세요.
- 우리 동네의 다양한 정보와 이야기를 좋아요와 댓글로 나누어요. 
- 마음에 드는 모임에 가입하여 활동하거나 직접 모임을 개설해서 모임장이 되어보세요. 이웃끼리 만나서 서로의 경험을 공유할 수 있습니다.



#### 주요기능

```
· 동네 이웃간의 중고거래 가능
· 중고거래를 위한 1대1 채팅 지원
· 채팅시, 편의를 위한 영상통화 지원
· 근처 이웃간의 소통을 위한 동네생활 제공
· 동네이웃 누구나 참여할수 있는 다양한 모임 제공
· 장터, 동네생활, 모임의 통합검색 지원
· 이웃간 팔로잉, 차단 지원
```



---



#### 프로젝트 산출물

- 소스코드

  GitHub - [ ](https://github.com/Junoo8888/koguma)

- [프로젝트 보고서](https://drive.google.com/file/d/1G_ryKSDVcYQxO4aP78PcuSiRdwua2EPE/view?usp=drive_link)

  - 프로젝트 개요 : 시스템 개요, 업무 영역, 예상 효과, 기술적 고려사항, 개발 이력, Architectures
  - 분석 산출물 : Use Case Modeling, Application Modeling, 화면분석, 업무분석
  - 설계 산출물 : Application Modeling, 화면 분석, 테이블 정의서

- 프로젝트 발표 영상

  
  
  [<img src="https://github.com/ungyuun/koguma/assets/95204319/e3d53e1b-03ac-45d9-b77f-a67c1ae45d02" alt="유튜브썸네일" style="zoom:50%;" />](https://www.youtube.com/watch?v=ySCuN_o_ssg)

``` 
발표 담당 부분 타임라인
기술 설명 : 00:04:40 ~ 00:08:40
```

 

- 시연영상

  [<img src="https://github.com/ungyuun/koguma/assets/95204319/4400df2b-4baa-412e-9a17-b10fbeea4dc7" alt="koguma_thumbnail" style="zoom:50%;" />](https://youtu.be/QbFXbEj1Bzg?si=aAa8oKgPy77te4h)



---



#### 역할 및 담당 업무

- 데브옵스 관리자 및 문서총괄 : CI/CD, DB 서버 구축, 프로젝트 문서 담당

- 담당 서브시스템 : 모임, 그룹채팅


---



#### 담당 구현 기술



##### 1. 내 동네 설정

>GeoLocation을 사용해 사용자의 위도 경도 가져와 reverseGeoCoder를 통해 행정동 추출.
>
>동네 추가, 동네 삭제, 검색반경 설정가능 [2~5km]

<img src="https://github.com/ungyuun/koguma/assets/95204319/d6a0659f-bc21-49ee-bbfa-2d3273a089db" alt="location" width="20%" />   <img src="https://github.com/ungyuun/koguma/assets/95204319/f6d2fb8f-455f-4388-bfa5-dbe440009617" alt="location2" width="20%" />   <img src="https://github.com/ungyuun/koguma/assets/95204319/2ea94fec-489a-411b-b43e-4fb2487f8bf9" alt="location3" width="20%" />


##### 2. 모임 

> 모임 서비스는 모임만을 위한 일정 및 게시판, 그룹 채팅을 이용할 수 있다. 
>
> 모임 카테고리는 고정되어있으며, 게시판 및 일정은 모임 별 상이함.



<img src="https://github.com/Junoo8888/Algorithm/assets/141202143/577b73de-055e-44ea-a3bd-991a7e872084" alt="club_create" width="18.5%" />  <img src="https://github.com/Junoo8888/Algorithm/assets/141202143/d591e16a-bedf-4cb6-b12d-f6c92d532816" alt="club_list" width="20%" /> 




##### 3. 내 위치기반 모임 검색

>GeoLocation을 활용한 내 위치, 지도 모임 검색
>

<img src="https://github.com/ungyuun/koguma/assets/141202143/9bad1c72-1acb-4e4b-bfe5-a9a030ae0f8a" alt="club_geo1" width="20%"/> <img src="https://github.com/ungyuun/koguma/assets/141202143/a81c7607-77b8-4f98-bb09-1a202e6d5511" alt="club_geo2" width="20%"/>   





##### 4. 그룹채팅

> Node.js 를 이용한 각 모임별 그룹채팅
>
> 모임에 가입한 사람만 해당 모임에 채팅방에서 채팅이 가능하다. 



<img src="https://github.com/ungyuun/koguma/assets/141202143/565e1113-76cd-416b-a9aa-7e305ceb292b" alt="club_chat" width="20%" />  


##### 5. 모임별 게시판

> 각 모임만 해당 모임 만을 위한 게시판을 사용할 수 있다. 
>
> 카테고리는 모임별로 다르게 추가/삭제가 가능하다. 



<img src="https://github.com/ungyuun/koguma/assets/141202143/d92b3f21-ad8f-4feb-b00c-6acc4c47b856" alt="good_review" width="20%" />   <img src="https://github.com/ungyuun/koguma/assets/141202143/52fa2a77-27ff-4756-8407-51105ff923c5" alt="bad_review" width="20%" />  <img src="https://github.com/ungyuun/koguma/assets/141202143/a5ec1b87-a8a4-443c-8b74-9ae0a2f99fc6" alt="bad_review" width="20%" />  





#### DevOps

![koguma_devops_toolchain](https://github.com/ungyuun/koguma/assets/95204319/c16a01b3-6725-48d3-9dbe-58ddf6051929)

---



#### Cloud Archtecture

![koguma_cloud_architecture](https://github.com/ungyuun/koguma/assets/95204319/45422ea6-9256-4636-bf8f-c9d5f108aa0b)



---



#### Server Architectrue

![koguma_server_architecture](https://github.com/ungyuun/koguma/assets/95204319/5856c19d-4399-4dc6-89bc-c87203b2f089)



---



#### ERD

![koguma_erd_diagram](https://github.com/ungyuun/koguma/assets/95204319/2d626637-b545-4568-99d6-cea4691279ac)

