<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/fe609bcd-43f2-4dba-8e3e-5f72728a4a21" width="50%" height="50%"/>

## 기획 의도
<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/35c2c004-6de6-46f0-81ed-14daee2be5b0" width="80%" height="80%"/>

🪴**반려식물**: 친구처럼 정서적인 교감과 위안을 얻는 식물을 뜻하는 신조어
- 코로나 장기화로 집에서 보내는 시간이 늘어나면서 반려식물에 대한 수요 증가 → **식물을 중심으로 한 온라인 커뮤니티**를 형성하기 위해 
- 어떤 식물을 키울지 고민하고 있거나, 관리가 어려워 식물이 금방 죽지는 않을까 걱정하는 사람들에게 **알맞는 식물을 추천**해주기 위해
<br/>

## 프로젝트 소개 

> 사용자의 생활습관, 식물 양육 환경 등을 알 수 있는 질문지들을 통해 나에게 알맞는 식물을 추천받을 수 있습니다. 또한 다양한 식물에 대한 지식을 식물 사전에서 손쉽게 얻을 수 있습니다. 마지막으로 커뮤니티를 통해 식물 관리 시 주의사항 등 식물 관련 실생활 정보들을 다른 사람들과 함께 나눌 수 있습니다.
<br/>

### 개발 스택
<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/265354b4-ecbb-4b99-b14c-2965ad2a65f6" width="70%" height="70%"/>
<br/>

### DB 설계
![image](https://github.com/PlantForYou/PlantForYou/assets/65665065/d87a4868-d72a-44ed-bf9d-f1de2721f0f3)
<br/>

### API 명세
https://plantforyou.github.io/PlantForYou/
<br/>
<br/>

## 구현 기능 
### 구글 연동 로그인 
- 구글 연동 로그인으로 별도의 회원가입 없이도 서비스 이용 가능 

### 식물 추천
- 총 11개의 질문으로 구성
- 질문지를 기반으로 사용자의 원하는 **볕양**, **난이도**, **사이즈**, **식용 여부**, **독성 여부**를 판단하여 매칭되는 식물 1개를 추천
- 총 54개의 식물들을 분류기준에 맞게 정리 
- 각 질문의 선택지마다 점수를 부여하고 기준별로 합산하여 식물 분류표에 매칭
<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/57627961-495c-4b56-a5f6-899f16d4d6a5" width="60%" height="60%"/>

### 식물 사전
- 식물 사전을 통해 식물에 관한 지식 전달
- 특정 개수의 식물에 대해 필요한 볕양, 양육 난이도, 식물 사이즈, 식물의 간략한 기능 등의 정보 제공

### 커뮤니티
- 커뮤니티를 통해 식물에 관한 경험, 노하우 등을 다른 사람들과 공유
- 로그인 시에만 이용 가능한 서비스
- 게시글 등록, 수정, 삭제 기능
- 게시글별 좋아요, 스크랩 기능과 댓글, 대댓글 등록 및 삭제 기능

<br/>

## 주요 화면
### 메인 페이지
<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/8908a211-2b61-474f-b39e-f6ff95f96326" width="80%" height="80%"/>
<br/>

### 메뉴 목록
<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/30f5389f-a43d-43aa-9ce3-42e5464f729d" width="20%" height="20%"/>
<br/>

### 반려식물 추천
<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/142077d6-2a21-4666-9257-d113a33cf9f9" width="80%" height="80%"/>
<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/a50e7e74-d85e-46ad-a901-2db77edaa110" width="40%" height="40%"/> <img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/386dc1e2-c82a-49e2-ab87-00206cdb8c9f" width="40%" height="40%"/>
<br/>

### 식물 사전 목록
<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/8b87b76c-eeb6-4146-9c76-8a697c4e29a3" width="80%" height="80%"/>
<br/>

### 식물 사전 상세 
<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/5293e17c-b009-4d84-b985-ed5182ab70a0" width="80%" height="80%"/>
<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/930dedba-0e66-47c0-991e-25ee221e90c0" width="80%" height="80%"/>
<br/>

### 커뮤니티 목록
<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/0192873f-f163-41eb-8216-1a4173c4baab" width="80%" height="80%"/>
<br/>

### 커뮤니티 상세
<img src="https://github.com/PlantForYou/PlantForYou/assets/65665065/3e3ef96a-3e6f-4c84-a28a-66b566161a96" width="80%" height="80%"/>
<br/>
<br/>

## 팀원 및 역할
| 팀원 | 역할 |
|------------|----|
| 김다은(팀장) | 커뮤니티&식물 추천 API, 구글 로그인, API 연동, 배포 |
| 김지은 | 메인 화면, 식물 추천 화면 |
| 이혜린 | 메인 화면, 커뮤니티 화면 |
| 진유진 | 구글 로그인 |
<br/>
